import { useState, useEffect } from 'react';
import Navbar from '../components/Navbar';
import directoryService from '../services/directory.service';
import { User, Users, BookOpen, Search } from 'lucide-react';
import { motion } from 'framer-motion';

const Directory = () => {
    const [activeTab, setActiveTab] = useState('student'); // student or faculty
    const [items, setItems] = useState([]);
    const [loading, setLoading] = useState(false);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        fetchData();
    }, [activeTab]);

    const fetchData = async () => {
        setLoading(true);
        try {
            let data = [];
            const filters = {}; // Empty filters for now to get all
            if (activeTab === 'student') {
                data = await directoryService.searchStudents(filters);
            } else {
                data = await directoryService.searchFaculty(filters);
            }
            setItems(data || []);
        } catch (error) {
            console.error("Failed to fetch directory data", error);
        } finally {
            setLoading(false);
        }
    };

    const filteredItems = items.filter(item =>
        (item.firstName + ' ' + item.lastName).toLowerCase().includes(searchTerm.toLowerCase()) ||
        (item.username || '').toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="min-h-screen">
            <Navbar />
            <main className="max-w-7xl mx-auto p-6 md:p-8 pt-24">
                <div className="flex flex-col md:flex-row justify-between items-end mb-8 gap-4">
                    <div>
                        <h1 className="text-3xl font-bold title-gradient">People Directory</h1>
                        <p className="text-slate-400">Connect with Students and Faculty</p>
                    </div>

                    <div className="flex gap-4 items-center">
                        <div className="relative">
                            <Search className="absolute left-3 top-2.5 text-slate-400 w-4 h-4" />
                            <input
                                type="text"
                                placeholder="Search by name..."
                                className="glass-input pl-10 h-10 w-64 text-sm"
                                value={searchTerm}
                                onChange={(e) => setSearchTerm(e.target.value)}
                            />
                        </div>

                        <div className="flex bg-slate-900/50 p-1 rounded-xl border border-white/5">
                            <button
                                onClick={() => setActiveTab('student')}
                                className={`flex items-center gap-2 px-4 py-2 rounded-lg text-sm font-medium transition-all ${activeTab === 'student' ? 'bg-primary text-white shadow-lg shadow-primary/25' : 'text-slate-400 hover:text-white hover:bg-white/5'}`}
                            >
                                <BookOpen className="w-4 h-4" /> Students
                            </button>
                            <button
                                onClick={() => setActiveTab('faculty')}
                                className={`flex items-center gap-2 px-4 py-2 rounded-lg text-sm font-medium transition-all ${activeTab === 'faculty' ? 'bg-primary text-white shadow-lg shadow-primary/25' : 'text-slate-400 hover:text-white hover:bg-white/5'}`}
                            >
                                <Users className="w-4 h-4" /> Faculty
                            </button>
                        </div>
                    </div>
                </div>

                {loading ? (
                    <div className="text-center p-10 text-slate-400">Loading directory...</div>
                ) : (
                    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                        {filteredItems.map((person) => (
                            <motion.div
                                key={person.id}
                                initial={{ opacity: 0 }}
                                animate={{ opacity: 1 }}
                                className="glass-panel p-6 hover:bg-white/5 transition-colors flex items-center gap-4"
                            >
                                <div className={`w-14 h-14 rounded-full flex items-center justify-center text-xl font-bold text-white shadow-lg ${activeTab === 'student' ? 'bg-gradient-to-br from-blue-500 to-cyan-500' : 'bg-gradient-to-br from-purple-500 to-pink-500'}`}>
                                    {person.firstName?.[0]}{person.lastName?.[0]}
                                </div>

                                <div>
                                    <h3 className="text-lg font-bold">{person.firstName} {person.lastName}</h3>
                                    <p className="text-xs text-slate-400 mb-1">@{person.username}</p>
                                    <span className="inline-block px-2 py-0.5 rounded bg-white/10 text-[10px] text-slate-300 uppercase tracking-wider">
                                        {activeTab}
                                    </span>
                                </div>
                            </motion.div>
                        ))}
                    </div>
                )}
            </main>
        </div>
    );
};

export default Directory;
