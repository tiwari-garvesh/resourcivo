import Navbar from '../components/Navbar';
import TransportList from '../components/TransportList';
import { motion } from 'framer-motion';

const Dashboard = () => {
    return (
        <div className="min-h-screen">
            <Navbar />

            {/* Main Content */}
            <main className="max-w-7xl mx-auto p-6 md:p-8 pt-24">
                <motion.div
                    initial={{ opacity: 0, y: 10 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ delay: 0.1 }}
                >
                    <TransportList />
                </motion.div>
            </main>
        </div>
    );
};

export default Dashboard;
