import { motion } from 'framer-motion';
import Navbar from '../components/Navbar';
import TransportList from '../components/TransportList';

const Transport = () => {
    return (
        <div className="min-h-screen bg-slate-900 text-white selection:bg-cyan-500/30">
            <Navbar />

            <main className="max-w-7xl mx-auto px-4 py-8 pt-24">
                <motion.div
                    initial={{ opacity: 0, y: 20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 0.5 }}
                >
                    <div className="mb-8">
                        <h1 className="text-4xl font-bold bg-clip-text text-transparent bg-gradient-to-r from-cyan-400 to-purple-500">
                            Transport Services
                        </h1>
                        <p className="text-slate-400 mt-2 text-lg">
                            Campus commute management and scheduling
                        </p>
                    </div>

                    <TransportList />
                </motion.div>
            </main>
        </div>
    );
};

export default Transport;
