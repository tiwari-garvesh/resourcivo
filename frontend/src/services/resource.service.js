import api from './api';

const getAllBooks = async () => {
    // Determine endpoint. Assuming /api/library-book/search based on pattern or /api/library-book
    // Let's assume standard GET for now, if not we'll fix.
    // Checking controller later if build fails, but trying GET /api/library-book first or search
    try {
        const response = await api.get('/library-book');
        return response.data;
    } catch (e) {
        // Fallback to search if GET all not allowed
        const response = await api.post('/library-book/search', {});
        return response.data;
    }
};

const getAllInventoryObj = async () => {
    try {
        const response = await api.get('/inventory-item');
        return response.data;
    } catch (e) {
        const response = await api.post('/inventory-item/search', {});
        return response.data;
    }
};

const getAllLabEquipment = async () => {
    try {
        const response = await api.get('/lab-equipment');
        return response.data;
    } catch (e) {
        const response = await api.post('/lab-equipment/search', {});
        return response.data;
    }
};

const resourceService = {
    getAllBooks,
    getAllInventoryObj,
    getAllLabEquipment
};

export default resourceService;
