import { useState, useEffect } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { X, Save, AlertCircle } from 'lucide-react';

const TransportForm = ({ isOpen, onClose, onSubmit, initialData }) => {
    const [formData, setFormData] = useState({
        vehicleName: '',
        registrationNumber: '',
        vehicleType: 'Bus',
        company: '',
        color: '',
        parkingNumber: '',
        routes: '',
        purchaseDate: '',
        totalSeats: '',
        driverName: '',
        driverContact: {
            mobileNumber: '',
            email: ''
        },
        description: '',
        isActive: true,
        departureTime: '08:00',
        arrivalTime: '09:00',
        returnTime: '17:00'
    });

    useEffect(() => {
        if (initialData) {
            // Populate form if editing
            setFormData({
                ...initialData,
                driverContact: {
                    mobileNumber: initialData.driverContact?.primaryNumber || '',
                    email: initialData.driverContact?.primaryEmail || ''
                },
                // Format times to HH:mm for input type="time"
                departureTime: initialData.departureTime?.substring(0, 5),
                arrivalTime: initialData.arrivalTime?.substring(0, 5),
                returnTime: initialData.returnTime?.substring(0, 5)
            });
        }
    }, [initialData]);

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        if (name.includes('.')) {
            const [parent, child] = name.split('.');
            setFormData(prev => ({
                ...prev,
                [parent]: { ...prev[parent], [child]: value }
            }));
        } else {
            setFormData(prev => ({
                ...prev,
                [name]: type === 'checkbox' ? checked : value
            }));
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Append :00 to times for LocalTime format if needed by backend, though HH:mm usually works if backend uses ISO.
        // Let's ensure format is HH:mm:ss
        const payload = {
            ...formData,
            totalSeats: parseInt(formData.totalSeats),
            // Map form fields to backend DTO structure
            driverContact: {
                primaryNumber: parseInt(formData.driverContact.mobileNumber),
                primaryEmail: formData.driverContact.email
            },
            departureTime: formData.departureTime.length === 5 ? formData.departureTime + ':00' : formData.departureTime,
            arrivalTime: formData.arrivalTime.length === 5 ? formData.arrivalTime + ':00' : formData.arrivalTime,
            returnTime: formData.returnTime.length === 5 ? formData.returnTime + ':00' : formData.returnTime
        };
        onSubmit(payload);
    };

    if (!isOpen) return null;

    return (
        <AnimatePresence>
            <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-black/50 backdrop-blur-sm">
                <motion.div
                    initial={{ opacity: 0, scale: 0.95 }}
                    animate={{ opacity: 1, scale: 1 }}
                    exit={{ opacity: 0, scale: 0.95 }}
                    className="glass-panel w-full max-w-2xl max-h-[90vh] overflow-y-auto"
                >
                    <div className="p-6 border-b border-white/10 flex justify-between items-center sticky top-0 bg-slate-900/95 backdrop-blur z-10">
                        <h2 className="text-xl font-bold title-gradient">
                            {initialData ? 'Edit Transport' : 'Add New Transport'}
                        </h2>
                        <button onClick={onClose} className="p-1 hover:bg-white/10 rounded-full transition-colors">
                            <X className="w-5 h-5 text-slate-400" />
                        </button>
                    </div>

                    <form onSubmit={handleSubmit} className="p-6 space-y-6">
                        {/* Vehicle Details */}
                        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                            <div>
                                <label className="text-xs text-slate-400">Vehicle Name *</label>
                                <input name="vehicleName" value={formData.vehicleName} onChange={handleChange} className="glass-input mt-1" required />
                            </div>
                            <div>
                                <label className="text-xs text-slate-400">Registration Number *</label>
                                <input name="registrationNumber" value={formData.registrationNumber} onChange={handleChange} className="glass-input mt-1" required />
                            </div>
                            <div>
                                <label className="text-xs text-slate-400">Type *</label>
                                <select name="vehicleType" value={formData.vehicleType} onChange={handleChange} className="glass-input mt-1">
                                    <option value="Bus">Bus</option>
                                    <option value="Van">Van</option>
                                    <option value="Minibus">Minibus</option>
                                </select>
                            </div>
                            <div>
                                <label className="text-xs text-slate-400">Total Seats *</label>
                                <input type="number" name="totalSeats" value={formData.totalSeats} onChange={handleChange} className="glass-input mt-1" required min="1" />
                            </div>
                        </div>

                        {/* Route & Timing */}
                        <div>
                            <label className="text-xs text-slate-400">Routes (Description) *</label>
                            <input name="routes" value={formData.routes} onChange={handleChange} className="glass-input mt-1" placeholder="e.g. City Center -> Campus" required />
                        </div>
                        <div className="grid grid-cols-3 gap-4">
                            <div>
                                <label className="text-xs text-slate-400">Departure *</label>
                                <input type="time" name="departureTime" value={formData.departureTime} onChange={handleChange} className="glass-input mt-1" required />
                            </div>
                            <div>
                                <label className="text-xs text-slate-400">Arrival *</label>
                                <input type="time" name="arrivalTime" value={formData.arrivalTime} onChange={handleChange} className="glass-input mt-1" required />
                            </div>
                            <div>
                                <label className="text-xs text-slate-400">Return *</label>
                                <input type="time" name="returnTime" value={formData.returnTime} onChange={handleChange} className="glass-input mt-1" required />
                            </div>
                        </div>

                        {/* Driver Info */}
                        <div className="border-t border-white/5 pt-4">
                            <h3 className="text-sm font-semibold text-slate-300 mb-3">Driver Details</h3>
                            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                                <div>
                                    <label className="text-xs text-slate-400">Driver Name *</label>
                                    <input name="driverName" value={formData.driverName} onChange={handleChange} className="glass-input mt-1" required />
                                </div>
                                <div>
                                    <label className="text-xs text-slate-400">Mobile</label>
                                    <input name="driverContact.mobileNumber" value={formData.driverContact.mobileNumber} onChange={handleChange} className="glass-input mt-1" placeholder="+123..." />
                                </div>
                                <div>
                                    <label className="text-xs text-slate-400">Email</label>
                                    <input type="email" name="driverContact.email" value={formData.driverContact.email} onChange={handleChange} className="glass-input mt-1" placeholder="driver@example.com" />
                                </div>
                            </div>
                        </div>

                        <div className="flex justify-end gap-3 pt-4 border-t border-white/5">
                            <button type="button" onClick={onClose} className="px-4 py-2 rounded-lg text-sm font-medium text-slate-400 hover:text-white hover:bg-white/5 transition-colors">
                                Cancel
                            </button>
                            <button type="submit" className="btn-primary flex items-center gap-2">
                                <Save className="w-4 h-4" />
                                {initialData ? 'Update Vehicle' : 'Create Vehicle'}
                            </button>
                        </div>
                    </form>
                </motion.div>
            </div>
        </AnimatePresence>
    );
};

export default TransportForm;
