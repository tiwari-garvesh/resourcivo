import { useState, useEffect } from 'react';
import Navbar from '../components/Navbar';
import resourceService from '../services/resource.service';
import { Book, FlaskConical, Box, Search } from 'lucide-react';
import { motion } from 'framer-motion';

const ResourceCenter = () => {
    const [activeTab, setActiveTab] = useState('library');
    const [items, setItems] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        fetchData();
    }, [activeTab]);

    const fetchData = async () => {
        setLoading(true);
        try {
            let data = [];
            if (activeTab === 'library') {
                data = await resourceService.getAllBooks();
            } else if (activeTab === 'inventory') {
                data = await resourceService.getAllInventoryObj();
            } else if (activeTab === 'labs') {
                data = await resourceService.getAllLabEquipment();
            }
            setItems(data || []);
        } catch (error) {
            console.error("Failed to fetch resource data", error);
            setItems([]);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="min-h-screen">
            <Navbar />
            <main className="max-w-7xl mx-auto p-6 md:p-8 pt-24">
                <div className="flex justify-between items-end mb-8">
                    <div>
                        <h1 className="text-3xl font-bold title-gradient">Resource Center</h1>
                        <p className="text-slate-400">Library, Inventory, and Labs</p>
                    </div>

                    <div className="flex bg-slate-900/50 p-1 rounded-xl border border-white/5">
                        <button
                            onClick={() => setActiveTab('library')}
                            className={`flex items-center gap-2 px-4 py-2 rounded-lg text-sm font-medium transition-all ${activeTab === 'library' ? 'bg-primary text-white shadow-lg shadow-primary/25' : 'text-slate-400 hover:text-white hover:bg-white/5'}`}
                        >
                            <Book className="w-4 h-4" /> Library
                        </button>
                        <button
                            onClick={() => setActiveTab('inventory')}
                            className={`flex items-center gap-2 px-4 py-2 rounded-lg text-sm font-medium transition-all ${activeTab === 'inventory' ? 'bg-primary text-white shadow-lg shadow-primary/25' : 'text-slate-400 hover:text-white hover:bg-white/5'}`}
                        >
                            <Box className="w-4 h-4" /> Inventory
                        </button>
                        <button
                            onClick={() => setActiveTab('labs')}
                            className={`flex items-center gap-2 px-4 py-2 rounded-lg text-sm font-medium transition-all ${activeTab === 'labs' ? 'bg-primary text-white shadow-lg shadow-primary/25' : 'text-slate-400 hover:text-white hover:bg-white/5'}`}
                        >
                            <FlaskConical className="w-4 h-4" /> Labs
                        </button>
                    </div>
                </div>

                {loading ? (
                    <div className="glass-panel p-10 text-center text-slate-400 animate-pulse">Loading resources...</div>
                ) : (
                    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                        {items.length > 0 ? items.map((item, idx) => (
                            <motion.div
                                key={item.id || idx}
                                initial={{ opacity: 0 }}
                                animate={{ opacity: 1 }}
                                className="glass-panel p-6 hover:bg-white/5 transition-colors"
                            >
                                <div className="flex justify-between items-start mb-4">
                                    <div className={`p-3 rounded-xl ${activeTab === 'library' ? 'bg-blue-500/20 text-blue-300' : activeTab === 'inventory' ? 'bg-orange-500/20 text-orange-300' : 'bg-green-500/20 text-green-300'}`}>
                                        {activeTab === 'library' ? <Book className="w-6 h-6" /> : activeTab === 'inventory' ? <Box className="w-6 h-6" /> : <FlaskConical className="w-6 h-6" />}
                                    </div>
                                    <span className={`text-xs px-2 py-1 rounded bg-slate-700/50 ${item.status === 'AVAILABLE' ? 'text-green-400' : 'text-slate-400'}`}>
                                        {item.status || 'Active'}
                                    </span>
                                </div>

                                <h3 className="text-lg font-bold mb-2">{item.title || item.name || 'Unknown Item'}</h3>

                                <div className="space-y-2 text-sm text-slate-400">
                                    {activeTab === 'library' && (
                                        <>
                                            <p>Author: {item.author}</p>
                                            <p>ISBN: {item.isbn}</p>
                                        </>
                                    )}
                                    {activeTab === 'inventory' && (
                                        <>
                                            <p>Category: {item.category}</p>
                                            <p>Quantity: {item.quantity}</p>
                                        </>
                                    )}
                                    {activeTab === 'labs' && (
                                        <>
                                            <p>Lab: {item.labName}</p>
                                            <p>Condition: {item.condition}</p>
                                        </>
                                    )}
                                </div>
                            </motion.div>
                        )) : (
                            <div className="col-span-3 text-center py-12 text-slate-500 bg-white/5 rounded-2xl border border-dashed border-white/10">
                                <Search className="w-12 h-12 mx-auto mb-4 opacity-50" />
                                <p>No items found in {activeTab}</p>
                            </div>
                        )}
                    </div>
                )}
            </main>
        </div>
    );
};

export default ResourceCenter;
