async function testLoginAndProtected(baseUrl, name) {
    console.log(`\nTesting ${name} (${baseUrl})...`);
    try {
        // 1. Login
        const loginRes = await fetch(`${baseUrl}/api/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username: 'username', password: 'password' })
        });

        if (!loginRes.ok) {
            console.error(`[FAIL] ${name} Login Failed:`, loginRes.status);
            return;
        }

        const data = await loginRes.json();
        const token = data.token;
        console.log(`[PASS] ${name} Login Success. Token: ${token ? 'YES' : 'NO'}`);

        if (!token) return;

        // 2. Protected Endpoint
        const meRes = await fetch(`${baseUrl}/api/auth/me`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (meRes.ok) {
            const meData = await meRes.json();
            console.log(`[PASS] ${name} Protected Access (/me) Success. User: ${meData.username}`);
        } else {
            console.error(`[FAIL] ${name} Protected Access Failed:`, meRes.status);
            const text = await meRes.text();
            console.error('Response:', text);
        }

    } catch (error) {
        console.error(`[FAIL] ${name} Error:`, error.message);
    }
}

async function run() {
    await testLoginAndProtected('http://localhost:2020/resourcivo', 'Backend Direct');
    await testLoginAndProtected('http://localhost:3003', 'Frontend Proxy');
}

run();
