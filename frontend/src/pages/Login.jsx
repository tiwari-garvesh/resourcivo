import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { User, Lock, ArrowRight } from 'lucide-react';
import { motion } from 'framer-motion';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const { login } = useAuth();
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            await login(username.trim(), password.trim());
            navigate('/dashboard');
        } catch (err) {
            setError(err.response?.data?.message || 'Invalid credentials. Please try again.');
        }
    };

    return (
        <div className="min-h-screen flex items-center justify-center p-4">
            <motion.div
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                className="glass-panel p-8 w-full max-w-md"
            >
                <div className="text-center mb-8">
                    <h1 className="text-3xl font-bold title-gradient mb-2">Welcome Back</h1>
                    <p className="text-slate-400">Sign in to access Resourcivo</p>
                </div>

                {error && (
                    <div className="bg-red-500/20 text-red-200 p-3 rounded-lg mb-6 text-sm border border-red-500/30">
                        {error}
                    </div>
                )}

                <form onSubmit={handleSubmit} className="space-y-6">
                    <div className="space-y-2">
                        <label className="text-sm font-medium text-slate-300">Username</label>
                        <div className="relative">
                            <User className="absolute left-3 top-3 text-slate-400 w-5 h-5" />
                            <input
                                type="text"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                                className="glass-input pl-10"
                                placeholder="Enter your username"
                                required
                            />
                        </div>
                    </div>

                    <div className="space-y-2">
                        <label className="text-sm font-medium text-slate-300">Password</label>
                        <div className="relative">
                            <Lock className="absolute left-3 top-3 text-slate-400 w-5 h-5" />
                            <input
                                type="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                className="glass-input pl-10"
                                placeholder="Enter your password"
                                required
                            />
                        </div>
                    </div>

                    <button type="submit" className="w-full btn-primary flex items-center justify-center gap-2 group">
                        Sign In
                        <ArrowRight className="w-4 h-4 group-hover:translate-x-1 transition-transform" />
                    </button>
                </form>
            </motion.div>
        </div>
    );
};

export default Login;
