import api from './api';

const login = async (username, password) => {
    const response = await api.post('/auth/login', { username, password });
    if (response.data.token) {
        localStorage.setItem('token', response.data.token);
        localStorage.setItem('user', JSON.stringify(response.data));
    }
    return response.data;
};

const register = async (userData) => {
    const response = await api.post('/auth/register', userData);
    return response.data;
};

const forgotPassword = async (email) => {
    const response = await api.post('/auth/forgot-password', { email });
    return response.data;
};

const resetPassword = async (token, newPassword) => {
    const response = await api.post('/auth/reset-password', { token, newPassword });
    return response.data;
};

const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
};

const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem('user'));
};

const authService = {
    login,
    register,
    forgotPassword,
    resetPassword,
    logout,
    getCurrentUser
};

export default authService;
