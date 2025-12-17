import api from './api';

// Get all transports (Student/Faculty view)
const getAllTransports = async () => {
    const response = await api.get('/transport');
    return response.data;
};

// Get available transports
const getAvailableTransports = async () => {
    const response = await api.get('/transport/available');
    return response.data;
};

// Book a transport (Student)
const bookTransportStudent = async (studentId, transportId, pickupPoint) => {
    const response = await api.post(`/transport/booking/student/${studentId}`, {
        transportId,
        pickupPoint
    });
    return response.data;
};

// Book a transport (Faculty)
const bookTransportFaculty = async (facultyId, transportId, pickupPoint) => {
    const response = await api.post(`/transport/booking/faculty/${facultyId}`, {
        transportId,
        pickupPoint
    });
    return response.data;
};

// Get active booking (Student)
const getStudentBooking = async (studentId) => {
    try {
        const response = await api.get(`/transport/booking/student/${studentId}`);
        return response.data;
    } catch (error) {
        if (error.response && error.response.status === 204) return null;
        throw error;
    }
};

// Cancel booking (Student)
const cancelStudentBooking = async (studentId) => {
    const response = await api.delete(`/transport/booking/student/${studentId}`);
    return response.data;
};

// Admin: Create Transport
const createTransport = async (transportData) => {
    const response = await api.post('/transport', transportData);
    return response.data;
};

// Admin: Update Transport
const updateTransport = async (id, transportData) => {
    const response = await api.put(`/transport/${id}`, transportData);
    return response.data;
};

// Admin: Delete Transport
const deleteTransport = async (id) => {
    await api.delete(`/transport/${id}`);
};

const transportService = {
    getAllTransports,
    getAvailableTransports,
    bookTransportStudent,
    bookTransportFaculty,
    getStudentBooking,
    cancelStudentBooking,
    createTransport,
    updateTransport,
    deleteTransport
};

export default transportService;
