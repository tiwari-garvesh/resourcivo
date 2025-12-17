import { useState, useEffect } from 'react';
import transportService from '../services/transport.service';
import TransportCard from './TransportCard';
import TransportForm from './TransportForm';
import { useAuth } from '../context/AuthContext';
import { motion, AnimatePresence } from 'framer-motion';
import { Search, Plus } from 'lucide-react';

const TransportList = () => {
    const [transports, setTransports] = useState([]);
    const [loading, setLoading] = useState(true);
    const [searchTerm, setSearchTerm] = useState('');
    const { user } = useAuth();

    // Modal State
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [editingTransport, setEditingTransport] = useState(null);

    const userRole = user?.roles?.[0] || 'GUEST';
    const userId = user?.id;
    const isAdmin = userRole === 'ROLE_ADMIN' || userRole === 'ROLE_TRANSPORT_MANAGER';

    useEffect(() => {
        fetchTransports();
    }, []);

    const fetchTransports = async () => {
        try {
            const data = await transportService.getAllTransports();
            setTransports(data);
        } catch (error) {
            console.error("Failed to fetch transports", error);
        } finally {
            setLoading(false);
        }
    };

    const handleBook = async (transport) => {
        if (!userId) return;
        try {
            if (userRole === 'ROLE_STUDENT') {
                await transportService.bookTransportStudent(userId, transport.id, "Main Campus");
                alert("Booking Successful!");
            } else if (userRole === 'ROLE_FACULTY') {
                await transportService.bookTransportFaculty(userId, transport.id, "Main Campus");
                alert("Booking Successful!");
            } else {
                alert("Action not allowed for this role.");
            }
            fetchTransports();
        } catch (error) {
            alert(error.response?.data?.message || "Booking Failed");
        }
    };

    // Admin Actions
    const handleCreate = () => {
        setEditingTransport(null);
        setIsModalOpen(true);
    };

    const handleEdit = (transport) => {
        setEditingTransport(transport);
        setIsModalOpen(true);
    };

    const handleDelete = async (id) => {
        if (window.confirm('Are you sure you want to delete this transport?')) {
            try {
                await transportService.deleteTransport(id);
                fetchTransports(); // Refresh
            } catch (error) {
                console.error("Failed to delete", error);
                alert("Failed to delete transport");
            }
        }
    };

    const handleFormSubmit = async (data) => {
        try {
            if (editingTransport) {
                await transportService.updateTransport(editingTransport.id, data);
            } else {
                await transportService.createTransport(data);
            }
            setIsModalOpen(false);
            fetchTransports();
        } catch (error) {
            console.error("Form submission error", error);
            alert(error.response?.data?.message || "Operation failed");
        }
    };

    const filteredTransports = transports.filter(t =>
        t.vehicleName.toLowerCase().includes(searchTerm.toLowerCase()) ||
        t.routes.toLowerCase().includes(searchTerm.toLowerCase())
    );

    if (loading) return <div className="text-center p-10 text-slate-400">Loading transports...</div>;

    return (
        <div>
            <div className="flex flex-col md:flex-row justify-between items-center mb-8 gap-4">
                <div>
                    <h2 className="text-2xl font-bold title-gradient">Available Transports</h2>
                    <p className="text-slate-400 text-sm">Manage and book your campus ride</p>
                </div>

                <div className="flex items-center gap-4">
                    <div className="relative">
                        <Search className="absolute left-3 top-2.5 text-slate-400 w-4 h-4" />
                        <input
                            type="text"
                            placeholder="Search routes..."
                            className="glass-input pl-10 h-10 w-64 text-sm"
                            value={searchTerm}
                            onChange={(e) => setSearchTerm(e.target.value)}
                        />
                    </div>

                    {isAdmin && (
                        <button
                            onClick={handleCreate}
                            className="btn-primary flex items-center gap-2 px-4 py-2 text-sm"
                        >
                            <Plus className="w-4 h-4" /> Add Vehicle
                        </button>
                    )}
                </div>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                {filteredTransports.map(transport => (
                    <TransportCard
                        key={transport.id}
                        transport={transport}
                        onBook={handleBook}
                        userRole={userRole}
                        isAdmin={isAdmin}
                        onEdit={() => handleEdit(transport)}
                        onDelete={() => handleDelete(transport.id)}
                    />
                ))}
                {filteredTransports.length === 0 && (
                    <div className="col-span-3 text-center py-12 text-slate-500 bg-white/5 rounded-2xl border border-dashed border-white/10">
                        <p>No transports found matching your search.</p>
                    </div>
                )}
            </div>

            {/* Admin Modal */}
            <TransportForm
                isOpen={isModalOpen}
                onClose={() => setIsModalOpen(false)}
                onSubmit={handleFormSubmit}
                initialData={editingTransport}
            />
        </div>
    );
};

export default TransportList;
