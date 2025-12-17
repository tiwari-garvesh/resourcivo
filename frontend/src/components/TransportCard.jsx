import { Bus, Clock, MapPin, Users, Edit2, Trash2 } from 'lucide-react';
import { motion } from 'framer-motion';

const TransportCard = ({ transport, onBook, userRole, isAdmin, onEdit, onDelete }) => {
    const {
        vehicleName, registrationNumber, routes,
        departureTime, returnTime, availableSeats, totalSeats
    } = transport;

    const isFull = availableSeats === 0;

    return (
        <motion.div
            whileHover={{ y: -5 }}
            className="glass-panel p-6 flex flex-col justify-between h-full group relative"
        >
            {isAdmin && (
                <div className="absolute top-4 right-4 flex gap-2 opacity-0 group-hover:opacity-100 transition-opacity">
                    <button onClick={onEdit} className="p-2 bg-slate-700/50 hover:bg-primary/20 hover:text-primary rounded-lg transition-colors">
                        <Edit2 className="w-4 h-4" />
                    </button>
                    <button onClick={onDelete} className="p-2 bg-slate-700/50 hover:bg-red-500/20 hover:text-red-400 rounded-lg transition-colors">
                        <Trash2 className="w-4 h-4" />
                    </button>
                </div>
            )}

            <div>
                <div className="flex justify-between items-start mb-4">
                    <div className="bg-primary/20 p-3 rounded-xl">
                        <Bus className="w-8 h-8 text-primary" />
                    </div>
                    <span className={`px-3 py-1 rounded-full text-xs font-medium ${isFull ? 'bg-red-500/20 text-red-200' : 'bg-green-500/20 text-green-200'
                        }`}>
                        {isFull ? 'Full' : `${availableSeats} Seats Left`}
                    </span>
                </div>

                <h3 className="text-xl font-bold mb-1">{vehicleName}</h3>
                <p className="text-sm text-slate-400 mb-4">{registrationNumber}</p>

                <div className="space-y-3 mb-6">
                    <div className="flex items-center gap-2 text-sm text-slate-300">
                        <MapPin className="w-4 h-4 text-primary" />
                        <span>{routes}</span>
                    </div>
                    <div className="flex items-center gap-2 text-sm text-slate-300">
                        <Clock className="w-4 h-4 text-primary" />
                        <span>{departureTime} - {returnTime}</span>
                    </div>
                    <div className="flex items-center gap-2 text-sm text-slate-300">
                        <Users className="w-4 h-4 text-primary" />
                        <span>Capacity: {totalSeats}</span>
                    </div>
                </div>
            </div>

            {!isAdmin && (
                <button
                    onClick={() => onBook(transport)}
                    disabled={isFull || (userRole !== 'ROLE_STUDENT' && userRole !== 'ROLE_FACULTY')}
                    className={`w-full py-2 rounded-lg font-bold shadow-lg transition-all ${isFull
                        ? 'bg-slate-700 text-slate-500 cursor-not-allowed'
                        : 'btn-primary'
                        }`}
                >
                    {isFull ? 'Sold Out' : 'Book Seat'}
                </button>
            )}
        </motion.div>
    );
};

export default TransportCard;
