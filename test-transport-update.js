// Native fetch in Node 18+

const baseUrl = 'http://localhost:2020/resourcivo';

async function run() {
    console.log('=== Verifying Transport Update Fix ===');

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
        const token = loginData.accessToken;
        const headers = {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        };
        console.log('[PASS] Login successful');

        // 2. Create Vehicle
        console.log('\n2. Creating Vehicle...');
        const createPayload = {
            vehicleName: "Debug Bus",
            registrationNumber: "DBG-001",
            vehicleType: "Bus",
            totalSeats: 20,
            driverName: "Debug Driver",
            driverContact: {
                primaryNumber: 9999999999,
                primaryEmail: "debug@test.com"
            },
            routes: "Debug Route",
            departureTime: "08:00:00",
            arrivalTime: "09:00:00",
            returnTime: "17:00:00",
            isActive: true
        };

        const createRes = await fetch(`${baseUrl}/api/transport`, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(createPayload)
        });

        if (!createRes.ok) {
            const err = await createRes.text();
            throw new Error(`Create failed: ${createRes.status} - ${err}`);
        }
        const createdVehicle = await createRes.json();
        console.log('[PASS] Vehicle Created. ID:', createdVehicle.id);

        // 3. Fetch Vehicle to check DTO Response
        console.log('\n3. Fetching Vehicle to check DTO...');
        const getRes = await fetch(`${baseUrl}/api/transport/${createdVehicle.id}`, {
            method: 'GET',
            headers: headers
        });
        const vehicleData = await getRes.json();

        if (!vehicleData.driverContact) {
            console.error('[FAIL] driverContact is MISSING in response!');
            console.log('Received:', JSON.stringify(vehicleData, null, 2));
            process.exit(1);
        } else {
            console.log('[PASS] driverContact is present:', vehicleData.driverContact);
        }

        // 4. Update Vehicle (mimic Frontend Update)
        console.log('\n4. Updating Vehicle...');
        const updatePayload = {
            ...createPayload,
            vehicleName: "Debug Bus Updated",
            // Use values from DTO to mimic "Form uses initialData"
            driverContact: {
                primaryNumber: vehicleData.driverContact.primaryNumber,
                primaryEmail: vehicleData.driverContact.primaryEmail
            }
        };

        const updateRes = await fetch(`${baseUrl}/api/transport/${createdVehicle.id}`, {
            method: 'PUT',
            headers: headers,
            body: JSON.stringify(updatePayload)
        });

        if (!updateRes.ok) {
            const err = await updateRes.text();
            console.error(`[FAIL] Update failed: ${updateRes.status} - ${err}`);
            process.exit(1);
        }

        const updatedVehicle = await updateRes.json();
        if (updatedVehicle.vehicleName === "Debug Bus Updated") {
            console.log('[PASS] Update Successful. Name:', updatedVehicle.vehicleName);
        } else {
            console.error('[FAIL] Name not updated!');
        }

        // Cleanup
        await fetch(`${baseUrl}/api/transport/${createdVehicle.id}`, { method: 'DELETE', headers: headers });

    } catch (e) {
        console.error('[ERROR]', e.message);
        process.exit(1);
    }
}

run();
