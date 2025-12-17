import api from './api';

const searchStudents = async (filters = {}) => {
    const response = await api.post('/student/search', filters);
    return response.data;
};

const searchFaculty = async (filters = {}) => {
    const response = await api.post('/faculty/search', filters);
    return response.data;
};

const directoryService = {
    searchStudents,
    searchFaculty
};

export default directoryService;
