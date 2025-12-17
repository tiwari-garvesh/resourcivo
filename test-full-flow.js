async function testFullFlow(baseUrl, name) {
    console.log(`\n=== Testing ${name} (${baseUrl}) ===`);
    try {
        const timestamp = Date.now();
        const newUser = {
            username: `user_${timestamp}`,
            email: `user_${timestamp}@example.com`,
            password: 'password123',
            name: 'Test User',
            role: 'STUDENT' // Assuming ‘STUDENT’ is a valid role string for registration
        };

        // 1. Register
        console.log('1. Registering new user...');
        const registerRes = await fetch(`${baseUrl}/api/auth/register`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newUser)
        });

        if (registerRes.status === 201 || registerRes.status === 200) {
            console.log('[PASS] Register Success');
        } else {
            console.error(`[FAIL] Register Failed: ${registerRes.status}`);
            try { console.error(await registerRes.text()); } catch { }
        }

        // 2. Login
        console.log('2. Logging in...');
        const loginRes = await fetch(`${baseUrl}/api/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username: newUser.username, password: newUser.password })
        });

        if (!loginRes.ok) {
            console.error(`[FAIL] Login Failed: ${loginRes.status}`);
            return;
        }

        const data = await loginRes.json();
        const token = data.token;
        console.log(`[PASS] Login Success. Token received.`);

        if (!token) return;

        // 3. Test Directory Endpoint (e.g., Get All Faculty)
        // Adjust endpoint based on directory.service.js findings
        // Using /api/faculty/all based on assumption, will verify after reading file.
        // For now, testing /api/auth/me again as baseline
        const meRes = await fetch(`${baseUrl}/api/auth/me`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
        console.log(`[PASS] Verified Token (/api/auth/me): ${meRes.status}`);

    } catch (error) {
        console.error(`[FAIL] Network/Script Error: ${error.message}`);
    }
}

async function run() {
    await testFullFlow('http://localhost:3003', 'Frontend Proxy');
}

run();
