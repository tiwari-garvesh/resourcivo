import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { LogOut, LayoutDashboard, User, GraduationCap, FlaskConical, Users } from 'lucide-react';

const Navbar = () => {
    const { user, logout } = useAuth();
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate('/login');
    };

    return (
        <nav className="fixed top-0 left-0 right-0 z-50 p-4">
            <div className="max-w-7xl mx-auto glass-panel border-none bg-opacity-80 backdrop-blur-md px-6 py-3 flex justify-between items-center">
                {/* Logo */}
                <Link to="/" className="flex items-center gap-3 group">
                    <div className="w-10 h-10 bg-gradient-to-br from-primary to-purple-600 rounded-xl flex items-center justify-center font-bold text-white shadow-lg shadow-primary/30 group-hover:scale-105 transition-transform">
                        R
                    </div>
                    <span className="text-xl font-bold title-gradient">
                        Resourcivo
                    </span>
                </Link>

                {/* Actions */}
                <div className="flex items-center gap-4">
                    {user ? (
                        <>
                            <div className="hidden md:flex items-center gap-1">
                                <Link to="/dashboard" className="flex items-center gap-2 text-sm text-slate-300 hover:text-white px-3 py-2 rounded-lg hover:bg-white/5 transition-all">
                                    <LayoutDashboard className="w-4 h-4" />
                                    Dashboard
                                </Link>
                                <Link to="/academic" className="flex items-center gap-2 text-sm text-slate-300 hover:text-white px-3 py-2 rounded-lg hover:bg-white/5 transition-all">
                                    <GraduationCap className="w-4 h-4" />
                                    Academic
                                </Link>
                                <Link to="/resources" className="flex items-center gap-2 text-sm text-slate-300 hover:text-white px-3 py-2 rounded-lg hover:bg-white/5 transition-all">
                                    <FlaskConical className="w-4 h-4" />
                                    Resources
                                </Link>
                                <Link to="/directory" className="flex items-center gap-2 text-sm text-slate-300 hover:text-white px-3 py-2 rounded-lg hover:bg-white/5 transition-all">
                                    <Users className="w-4 h-4" />
                                    Directory
                                </Link>
                            </div>

                            <div className="h-6 w-[1px] bg-white/10 mx-2 hidden md:block"></div>

                            <div className="flex items-center gap-3">
                                <Link to="/profile" className="hidden md:block text-right hover:opacity-80 transition-opacity">
                                    <p className="text-sm font-medium text-white">{user.username}</p>
                                    <p className="text-[10px] uppercase tracking-wider text-slate-400">
                                        {user.roles?.[0]?.replace('ROLE_', '')}
                                    </p>
                                </Link>
                                <Link to="/profile" className="bg-slate-700 p-2 rounded-full hover:bg-slate-600 transition-colors">
                                    <User className="w-5 h-5 text-slate-300" />
                                </Link>
                                <button
                                    onClick={handleLogout}
                                    className="p-2 hover:bg-red-500/10 hover:text-red-400 rounded-lg transition-colors"
                                    title="Logout"
                                >
                                    <LogOut className="w-5 h-5" />
                                </button>
                            </div>
                        </>
                    ) : (
                        <div className="flex items-center gap-4">
                            <Link to="/login" className="text-sm font-medium text-slate-300 hover:text-white transition-colors">
                                Sign In
                            </Link>
                            <Link
                                to="/login"
                                className="btn-primary text-sm px-6 py-2 shadow-lg shadow-primary/20"
                            >
                                Get Started
                            </Link>
                        </div>
                    )}
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
