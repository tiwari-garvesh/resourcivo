import requests
import json
import time

BASE_URL = "http://localhost:2020/resourcivo"
API_DOCS_URL = f"{BASE_URL}/api-docs"

def register_and_login():
    # Register
    register_url = f"{BASE_URL}/api/auth/register"
    user_data = {
        "username": "test_user_" + str(int(time.time())),
        "email": f"test_{int(time.time())}@example.com",
        "password": "password123",
        "role": "STUDENT"
    }
    
    print(f"Registering user: {user_data['username']}")
    try:
        resp = requests.post(register_url, json=user_data)
        print(f"Registration Response: {resp.status_code} - {resp.text}")
    except Exception as e:
        print(f"Registration error: {e}")

    # Login
    login_url = f"{BASE_URL}/api/auth/login"
    login_data = {
        "username": user_data["username"],
        "password": user_data["password"]
    }
    
    print("Logging in...")
    resp = requests.post(login_url, json=login_data)
    if resp.status_code == 200:
        token = resp.json().get("token")
        print("Login successful, token received.")
        return token
    else:
        print(f"Login failed: {resp.text}")
        return None

def test_endpoints(token):
    print(f"Fetching API docs from {API_DOCS_URL}...")
    try:
        resp = requests.get(API_DOCS_URL)
        if resp.status_code != 200:
            print(f"Failed to fetch API docs: {resp.status_code}")
            return
        
        api_docs = resp.json()
        paths = api_docs.get("paths", {})
        
        headers = {
            "Authorization": f"Bearer {token}",
            "Content-Type": "application/json"
        }
        
        results = []
        
        print(f"Found {len(paths)} paths. Testing GET endpoints...")
        
        for path, methods in paths.items():
            if "get" in methods:
                # Handle path parameters
                test_path = path.replace("{id}", "1").replace("{userId}", "1").replace("{studentId}", "1")
                
                # Skip other complex parameters for now
                if "{" in test_path:
                    print(f"Skipping complex path: {path}")
                    continue
                
                url = f"{BASE_URL}{test_path}"
                try:
                    r = requests.get(url, headers=headers)
                    status = r.status_code
                    # 200 OK, 404 Not Found (if ID 1 doesn't exist), 403 Forbidden (role mismatch) are all "working" in the sense that the endpoint is reachable.
                    # 500 is a failure.
                    result = "PASS" if status < 500 else "FAIL"
                    print(f"[{result}] {status} - {path}")
                    results.append((path, status, result))
                except Exception as e:
                    print(f"[FAIL] Error - {path}: {e}")
                    results.append((path, "ERR", "FAIL"))
                    
        print("\nSummary:")
        pass_count = sum(1 for _, _, r in results if r == "PASS")
        print(f"Total GET Endpoints Tested: {len(results)}")
        print(f"Passed (reachable): {pass_count}")
        print(f"Failed (500/Error): {len(results) - pass_count}")
        
    except Exception as e:
        print(f"Error testing endpoints: {e}")

if __name__ == "__main__":
    token = register_and_login()
    if token:
        test_endpoints(token)
    else:
        print("Cannot proceed without token.")
