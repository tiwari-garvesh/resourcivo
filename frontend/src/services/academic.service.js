import api from './api';

const getAllCourses = async () => {
    // Uses search endpoint with empty filter to get all courses
    const response = await api.post('/course/search', {});
    return response.data;
};

const getAllSubjects = async () => {
    const response = await api.get('/subject');
    return response.data;
};

const getStudentsInCourse = async (courseId) => {
    const response = await api.get(`/course/${courseId}/students`);
    return response.data;
};

const getAllClassrooms = async () => {
    const response = await api.get('/classroom');
    return response.data;
};

const academicService = {
    getAllCourses,
    getAllSubjects,
    getStudentsInCourse,
    getAllClassrooms
};

export default academicService;
