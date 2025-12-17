// Native fetch in Node 18+
const baseUrl = 'http://localhost:2020/resourcivo';

async function run() {
    console.log('=== Verifying Delete with Constraints ===');

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
        const headers = {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        };
        console.log('[PASS] Login successful');

        // 2. Create Vehicle
        console.log('\n2. Creating Vehicle...');
        const createPayload = {
            vehicleName: "Constraint Bus",
            registrationNumber: "CON-999",
            vehicleType: "Bus",
            totalSeats: 20,
            driverName: "Constraint Driver",
            driverContact: { primaryNumber: 8888888888, primaryEmail: "con@test.com" },
            routes: "Constraint Route",
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
        if (!createRes.ok) throw new Error(`Create failed: ${createRes.status}`);
        const createdVehicle = await createRes.json();
        console.log('[PASS] Vehicle Created. ID:', createdVehicle.id);

        // 3. Create a Dummy Student user (to book) - OR SKIP IF COMPLEX
        // Ideally we need a student. Let's assume there is one or Register one.
        // For speed, let's try the /api/auth/register
        console.log('\n3. Registering Student for Booking...');
        const studentUser = "student_" + Date.now();
        const regRes = await fetch(`${baseUrl}/api/auth/register`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                name: "Test Student",
                username: studentUser,
                email: studentUser + "@test.com",
                password: "password",
                role: ["student"]
            })
        });

        // If register fails usually means exists or other issue, but let's try login
        // If success status 201

        // Login as student
        const loginStudentRes = await fetch(`${baseUrl}/api/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username: studentUser, password: 'password' })
        });
        const studentData = await loginStudentRes.json();
        console.log('Student Login Data:', JSON.stringify(studentData));
        const studentToken = studentData.token;
        // Check both userId and id, just in case
        const studentId = studentData.userId || studentData.id;

        // 4. Book Transport (using student token)
        console.log('\n4. Booking Transport...');
        const bookHeaders = {
            'Authorization': `Bearer ${studentToken}`,
            'Content-Type': 'application/json'
        };

        const bookRes = await fetch(`${baseUrl}/api/transport/booking/student/${studentId}`, {
            method: 'POST',
            headers: bookHeaders,
            body: JSON.stringify({
                transportId: createdVehicle.id,
                pickupPoint: "Test Point"
            })
        });

        if (!bookRes.ok) {
            const txt = await bookRes.text();
            console.log(`[WARN] Booking failed: ${bookRes.status} - ${txt}`);
            // Proceed anyway to delete attempts
        } else {
            console.log('[PASS] Booking Successful');
        }

        // 5. Attempt Delete (Admin)
        console.log('\n5. Attempting Delete (Admin)...');
        const deleteRes = await fetch(`${baseUrl}/api/transport/${createdVehicle.id}`, {
            method: 'DELETE',
            headers: headers
        });

        if (!deleteRes.ok) {
            console.log(`[EXPECTED FAIL?] Delete failed: ${deleteRes.status}`);
            const errText = await deleteRes.text();
            console.log('Error Body:', errText);
        } else {
            console.log('[PASS] Delete Request Successful (Unexpected if constraint exists)');
        }

    } catch (e) {
        console.error('[ERROR]', e.message);
    }
}

run();
