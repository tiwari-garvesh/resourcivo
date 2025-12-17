import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import authService from '../services/auth.service';
import { User, Mail, Lock, ArrowRight, BookOpen, ShieldCheck } from 'lucide-react';
import { motion } from 'framer-motion';

const Register = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        name: '',
        username: '',
        email: '',
        password: '',
        role: 'ROLE_STUDENT' // Default
    });
    const [error, setError] = useState('');

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await authService.register(formData);
            navigate('/login?registered=true');
        } catch (err) {
            setError(err.response?.data?.message || 'Registration failed');
        }
    };

    return (
        <div className="min-h-screen flex items-center justify-center p-4 pt-20">
            <motion.div
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                className="glass-panel p-8 w-full max-w-md"
            >
                <div className="text-center mb-8">
                    <h1 className="text-3xl font-bold title-gradient mb-2">Create Account</h1>
                    <p className="text-slate-400">Join Resourcivo today</p>
                </div>

                {error && (
                    <div className="bg-red-500/20 text-red-200 p-3 rounded-lg mb-6 text-sm border border-red-500/30">
                        {error}
                    </div>
                )}

                <form onSubmit={handleSubmit} className="space-y-4">
                    <div className="space-y-1">
                        <label className="text-xs font-medium text-slate-300 ml-1">Full Name</label>
                        <div className="relative">
                            <User className="absolute left-3 top-3 text-slate-400 w-4 h-4" />
                            <input
                                name="name"
                                type="text"
                                placeholder="John Doe"
                                className="glass-input pl-10"
                                onChange={handleChange}
                                required
                            />
                        </div>
                    </div>

                    <div className="space-y-1">
                        <label className="text-xs font-medium text-slate-300 ml-1">Username</label>
                        <div className="relative">
                            <ShieldCheck className="absolute left-3 top-3 text-slate-400 w-4 h-4" />
                            <input
                                name="username"
                                type="text"
                                placeholder="johndoe123"
                                className="glass-input pl-10"
                                onChange={handleChange}
                                required
                            />
                        </div>
                    </div>

                    <div className="space-y-1">
                        <label className="text-xs font-medium text-slate-300 ml-1">Email</label>
                        <div className="relative">
                            <Mail className="absolute left-3 top-3 text-slate-400 w-4 h-4" />
                            <input
                                name="email"
                                type="email"
                                placeholder="john@example.com"
                                className="glass-input pl-10"
                                onChange={handleChange}
                                required
                            />
                        </div>
                    </div>

                    <div className="space-y-1">
                        <label className="text-xs font-medium text-slate-300 ml-1">Role</label>
                        <div className="grid grid-cols-2 gap-2">
                            <button
                                type="button"
                                onClick={() => setFormData({ ...formData, role: 'ROLE_STUDENT' })}
                                className={`p-2 rounded-lg border text-sm flex items-center justify-center gap-2 ${formData.role === 'ROLE_STUDENT' ? 'bg-primary/20 border-primary text-white' : 'border-white/10 text-slate-400 hover:bg-white/5'}`}
                            >
                                <BookOpen className="w-4 h-4" /> Student
                            </button>
                            <button
                                type="button"
                                onClick={() => setFormData({ ...formData, role: 'ROLE_FACULTY' })}
                                className={`p-2 rounded-lg border text-sm flex items-center justify-center gap-2 ${formData.role === 'ROLE_FACULTY' ? 'bg-primary/20 border-primary text-white' : 'border-white/10 text-slate-400 hover:bg-white/5'}`}
                            >
                                <User className="w-4 h-4" /> Faculty
                            </button>
                        </div>
                    </div>

                    <div className="space-y-1">
                        <label className="text-xs font-medium text-slate-300 ml-1">Password</label>
                        <div className="relative">
                            <Lock className="absolute left-3 top-3 text-slate-400 w-4 h-4" />
                            <input
                                name="password"
                                type="password"
                                placeholder="••••••••"
                                className="glass-input pl-10"
                                onChange={handleChange}
                                required
                            />
                        </div>
                    </div>

                    <button type="submit" className="w-full btn-primary mt-6 flex items-center justify-center gap-2 group">
                        Sign Up
                        <ArrowRight className="w-4 h-4 group-hover:translate-x-1 transition-transform" />
                    </button>
                </form>

                <p className="text-center text-slate-400 text-sm mt-6">
                    Already have an account? <Link to="/login" className="text-primary hover:underline">Sign In</Link>
                </p>
            </motion.div>
        </div>
    );
};

export default Register;
