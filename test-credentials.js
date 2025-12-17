async function testCredentials(u, p) {
    console.log(`Testing Login: '${u}' / '${p}'`);
    try {
        const res = await fetch('http://localhost:3003/api/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username: u, password: p })
        });

        if (res.ok) console.log(`  -> SUCCESS`);
        else console.log(`  -> FAILED: ${res.status} ${await res.text()}`);
    } catch (e) {
        console.log(`  -> NETWORK ERROR: ${e.message}`);
    }
}

async function run() {
    await testCredentials('username', 'password'); // Expected: Success
    await testCredentials('Username', 'password'); // Expected: Fail
    await testCredentials('username', 'Password'); // Expected: Fail
    await testCredentials('admin', 'admin');       // Expected: Fail
}

run();
