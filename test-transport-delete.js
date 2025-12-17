// Native fetch in Node 18+
const baseUrl = 'http://localhost:2020/resourcivo';

async function run() {
    console.log('=== Verifying Transport Delete ===');

    // 1. Login
    console.log('\n1. Logging in...');
    try {
        const loginRes = await fetch(`${baseUrl}/api/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username: 'username', password: 'password' })
        });

        if (!loginRes.ok) throw new Error(`Login failed: ${loginRes.status}`);
        const loginData = await loginRes.json();
        const token = loginData.token;
        console.log('Token received:', token ? token.substring(0, 10) + '...' : 'UNDEFINED');
        const headers = {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        };
        console.log('[PASS] Login successful');

        // 2. Create Vehicle to Delete
        console.log('\n2. Creating Vehicle to Delete...');
        const createPayload = {
            vehicleName: "Delete Me",
            registrationNumber: "DEL-777",
            vehicleType: "Bus",
            totalSeats: 20,
            driverName: "Temp Driver",
            driverContact: { primaryNumber: 9999999999, primaryEmail: "del@test.com" },
            routes: "Temp Route",
            departureTime: "10:00:00",
            arrivalTime: "11:00:00",
            returnTime: "12:00:00",
            isActive: true
        };

        const createRes = await fetch(`${baseUrl}/api/transport`, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(createPayload)
        });
        if (!createRes.ok) throw new Error(`Create failed: ${createRes.status}`);
        const createdVehicle = await createRes.json();
        console.log('[PASS] Vehicle Created. ID:', createdVehicle.id);

        // 3. Delete Vehicle
        console.log('\n3. Deleting Vehicle...');
        const deleteRes = await fetch(`${baseUrl}/api/transport/${createdVehicle.id}`, {
            method: 'DELETE',
            headers: headers
        });

        if (!deleteRes.ok) throw new Error(`Delete failed: ${deleteRes.status}`);
        console.log('[PASS] Delete Request Successful');

        // 4. Verify Deletion
        console.log('\n4. Verifying Deletion...');
        const getRes = await fetch(`${baseUrl}/api/transport/${createdVehicle.id}`, {
            method: 'GET',
            headers: headers
        });

        if (getRes.status === 404 || getRes.status === 204) {
            console.log('[PASS] Vehicle Not Found (as expected)');
        } else { // It might also return 200 with empty body or null if not handled as exception
            // Let's check if it exists in list
            const listRes = await fetch(`${baseUrl}/api/transport`, { headers });
            const list = await listRes.json();
            const exists = list.some(v => v.id === createdVehicle.id);
            if (!exists) console.log('[PASS] Vehicle gone from list');
            else console.error('[FAIL] Vehicle still in list!');
        }

    } catch (e) {
        console.error('[ERROR]', e.message);
        process.exit(1);
    }
}

run();
