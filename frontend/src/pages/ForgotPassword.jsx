import { useState } from 'react';
import { Link } from 'react-router-dom';
import authService from '../services/auth.service';
import { Mail, ArrowRight, CheckCircle } from 'lucide-react';
import { motion } from 'framer-motion';

const ForgotPassword = () => {
    const [email, setEmail] = useState('');
    const [submitted, setSubmitted] = useState(false);
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await authService.forgotPassword(email);
            setSubmitted(true);
        } catch (err) {
            setError(err.response?.data?.message || 'Failed to send reset link');
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
                    <h1 className="text-2xl font-bold title-gradient mb-2">Reset Password</h1>
                    <p className="text-slate-400 text-sm">Enter your email to receive instructions</p>
                </div>

                {submitted ? (
                    <div className="text-center py-8">
                        <div className="w-16 h-16 bg-green-500/20 rounded-full flex items-center justify-center mx-auto mb-4">
                            <CheckCircle className="w-8 h-8 text-green-400" />
                        </div>
                        <h3 className="text-lg font-bold mb-2">Check your email</h3>
                        <p className="text-slate-400 text-sm mb-6">
                            We have sent a password reset link to <br /> <span className="text-white">{email}</span>
                        </p>
                        <Link to="/login" className="btn-primary w-full block text-center">
                            Back to Login
                        </Link>
                    </div>
                ) : (
                    <form onSubmit={handleSubmit} className="space-y-6">
                        {error && (
                            <div className="bg-red-500/20 text-red-200 p-3 rounded-lg text-sm">
                                {error}
                            </div>
                        )}

                        <div className="space-y-2">
                            <label className="text-sm font-medium text-slate-300">Email Address</label>
                            <div className="relative">
                                <Mail className="absolute left-3 top-3 text-slate-400 w-5 h-5" />
                                <input
                                    type="email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    className="glass-input pl-10"
                                    placeholder="Enter your registered email"
                                    required
                                />
                            </div>
                        </div>

                        <button type="submit" className="w-full btn-primary flex items-center justify-center gap-2 group">
                            Send Reset Link
                            <ArrowRight className="w-4 h-4 group-hover:translate-x-1 transition-transform" />
                        </button>

                        <div className="text-center">
                            <Link to="/login" className="text-sm text-slate-400 hover:text-white">Back to Login</Link>
                        </div>
                    </form>
                )}
            </motion.div>
        </div>
    );
};

export default ForgotPassword;
