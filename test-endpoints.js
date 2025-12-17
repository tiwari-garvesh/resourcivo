async function testEndpoints(baseUrl) {
    console.log(`\n=== Testing Endpoints on ${baseUrl} ===`);
    let token;

    // 1. Login to get token
    try {
        const loginRes = await fetch(`${baseUrl}/api/auth/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Origin': 'http://localhost:3003'
            },
            body: JSON.stringify({ username: 'username', password: 'password' })
        });

        if (!loginRes.ok) {
            console.error(`[FAIL] Login Failed: ${loginRes.status}`);
            return;
        }
        const data = await loginRes.json();
        token = data.token;
        console.log('[PASS] Login Success');
    } catch (e) {
        console.error(`[FAIL] Network Error during Login: ${e.message}`);
        return;
    }

    const headers = {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
    };

    // 2. Test Student Search (Directory)
    // Formerly /api/student/search (SecurityConfig had /api/students/**)
    console.log('\nTesting Student Search (/api/student/search)...');
    try {
        const res = await fetch(`${baseUrl}/api/student/search`, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify({}) // Empty filter
        });
        if (res.ok) console.log(`[PASS] Student Search: ${res.status}`);
        else console.error(`[FAIL] Student Search: ${res.status} - ${await res.text()}`);
    } catch (e) { console.error(`[FAIL] Student Search Error: ${e.message}`); }

    // 3. Test Library Books (Resources)
    // Formerly /api/librarybook vs /api/library-book. Fixed to /api/library-book.
    // Service calls GET /library-book (proxy -> /api/library-book)
    console.log('\nTesting Library Books (/api/library-book)...');
    try {
        const res = await fetch(`${baseUrl}/api/library-book`, {
            method: 'GET',
            headers: headers
        });
        if (res.ok) console.log(`[PASS] Library Books (GET): ${res.status}`);
        else {
            // Try fallback search?
            console.log(`[INFO] GET failed (${res.status}), trying SEARCH...`);
            const searchRes = await fetch(`${baseUrl}/api/library-book/search`, {
                method: 'POST',
                headers: headers,
                body: JSON.stringify({})
            });
            if (searchRes.ok) console.log(`[PASS] Library Books (SEARCH): ${searchRes.status}`);
            else console.error(`[FAIL] Library Books All Failed: ${searchRes.status} ${await searchRes.text()}`);
        }
    } catch (e) { console.error(`[FAIL] Library Books Error: ${e.message}`); }

    // 4. Test Inventory (Resources)
    // /api/inventory-item
    console.log('\nTesting Inventory (/api/inventory-item)...');
    try {
        const res = await fetch(`${baseUrl}/api/inventory-item`, {
            method: 'GET',
            headers: headers
        });
        if (res.ok) console.log(`[PASS] Inventory (GET): ${res.status}`);
        else console.error(`[FAIL] Inventory: ${res.status}`);
    } catch (e) { console.error(`[FAIL] Inventory Error: ${e.message}`); }

    // 5. Test Transport (Get Available)
    console.log('\nTesting Transport Available (/api/transport/available)...');
    try {
        const res = await fetch(`${baseUrl}/api/transport/available`, {
            method: 'GET',
            headers: headers
        });
        if (res.ok) console.log(`[PASS] Transport Available: ${res.status}`);
        else console.error(`[FAIL] Transport Available: ${res.status}`);
    } catch (e) { console.error(`[FAIL] Transport Error: ${e.message}`); }

    // 6. Test Academic (Course Search)
    console.log('\nTesting Course Search (/api/course/search)...');
    try {
        const res = await fetch(`${baseUrl}/api/course/search`, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify({})
        });
        if (res.ok) console.log(`[PASS] Course Search: ${res.status}`);
        else console.error(`[FAIL] Course Search: ${res.status}`);
    } catch (e) { console.error(`[FAIL] Course Search Error: ${e.message}`); }
}

async function run() {
    await testEndpoints('http://localhost:3003');
}

run();
