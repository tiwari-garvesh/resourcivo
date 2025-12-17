import { useState, useEffect } from 'react';
import Navbar from '../components/Navbar';
import { useAuth } from '../context/AuthContext';
import { User, Mail, Shield, Phone, MapPin } from 'lucide-react';
import { motion } from 'framer-motion';

const Profile = () => {
    const { user } = useAuth();
    const [profile, setProfile] = useState(user || {});

    // Implementation Note: In a real app we'd fetch /api/auth/me here to get latest details.
    // For now we use the stored user object.

    return (
        <div className="min-h-screen">
            <Navbar />
            <main className="max-w-4xl mx-auto p-6 md:p-8 pt-24">
                <motion.div
                    initial={{ opacity: 0, y: 10 }}
                    animate={{ opacity: 1, y: 0 }}
                    className="glass-panel p-8"
                >
                    <div className="flex items-center gap-6 mb-8 border-b border-white/10 pb-8">
                        <div className="w-24 h-24 bg-gradient-to-br from-primary to-purple-600 rounded-full flex items-center justify-center text-3xl font-bold text-white shadow-2xl">
                            {profile.username?.charAt(0).toUpperCase()}
                        </div>
                        <div>
                            <h1 className="text-3xl font-bold">{profile.username}</h1>
                            <span className="inline-block mt-2 px-3 py-1 rounded-full bg-primary/20 text-primary text-xs font-bold uppercase tracking-wider">
                                {profile.roles?.[0]?.replace('ROLE_', '')}
                            </span>
                        </div>
                    </div>

                    <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
                        <div className="space-y-6">
                            <h3 className="text-xl font-bold mb-4 flex items-center gap-2">
                                <User className="w-5 h-5 text-slate-400" /> Personal Info
                            </h3>

                            <div className="bg-white/5 p-4 rounded-lg space-y-1">
                                <label className="text-xs text-slate-400">Email Address</label>
                                <div className="flex items-center gap-2">
                                    <Mail className="w-4 h-4 text-slate-500" />
                                    <p>{profile.email}</p>
                                </div>
                            </div>

                            <div className="bg-white/5 p-4 rounded-lg space-y-1">
                                <label className="text-xs text-slate-400">User ID</label>
                                <div className="flex items-center gap-2">
                                    <Shield className="w-4 h-4 text-slate-500" />
                                    <p className="font-mono text-sm">{profile.id}</p>
                                </div>
                            </div>
                        </div>

                        <div className="space-y-6">
                            <h3 className="text-xl font-bold mb-4 flex items-center gap-2">
                                <Phone className="w-5 h-5 text-slate-400" /> Contact Details
                            </h3>

                            <div className="bg-white/5 p-4 rounded-lg space-y-4">
                                <p className="text-slate-400 text-sm italic">
                                    Additional contact information (Phone, Address) is managed by the administration or can be updated in the full edit mode (Coming Soon).
                                </p>
                                <button className="btn-primary w-full text-sm">
                                    Edit Profile
                                </button>
                            </div>
                        </div>
                    </div>

                </motion.div>
            </main>
        </div>
    );
};

export default Profile;
