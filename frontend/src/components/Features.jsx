import { motion } from 'framer-motion';
import { Bus, BookOpen, FlaskConical, Users, Calendar, ShieldCheck } from 'lucide-react';

const features = [
    {
        icon: Bus,
        title: "Smart Transport",
        desc: "Real-time bus tracking, seat booking, and automated route management.",
        color: "text-blue-400"
    },
    {
        icon: BookOpen,
        title: "Academic Suite",
        desc: "Manage courses, subjects, and classroom allocations effortlessly.",
        color: "text-purple-400"
    },
    {
        icon: FlaskConical,
        title: "Resource Center",
        desc: "Track library books, lab equipment, and inventory in one place.",
        color: "text-teal-400"
    },
    {
        icon: Calendar,
        title: "Scheduling",
        desc: "Advanced timetable management for students and faculty.",
        color: "text-pink-400"
    },
    {
        icon: Users,
        title: "People Directory",
        desc: "Centralized profiles for students, faculty, and staff.",
        color: "text-orange-400"
    },
    {
        icon: ShieldCheck,
        title: "Secure Access",
        desc: "Role-based security ensuring data privacy and safety.",
        color: "text-emerald-400"
    }
];

const Features = () => {
    return (
        <section className="py-24 relative z-10 bg-slate-900/50">
            <div className="max-w-7xl mx-auto px-4">
                <div className="text-center mb-16">
                    <h2 className="text-3xl md:text-4xl font-bold mb-4">Everything you need</h2>
                    <p className="text-slate-400 max-w-2xl mx-auto">
                        A comprehensive suite of tools designed to streamline every aspect of educational institution management.
                    </p>
                </div>

                <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
                    {features.map((feature, index) => (
                        <motion.div
                            key={index}
                            initial={{ opacity: 0, y: 20 }}
                            whileInView={{ opacity: 1, y: 0 }}
                            transition={{ delay: index * 0.1 }}
                            viewport={{ once: true }}
                            whileHover={{ y: -5 }}
                            className="glass-panel p-8 hover:bg-white/5 transition-colors group"
                        >
                            <div className={`w-12 h-12 rounded-lg bg-white/5 flex items-center justify-center mb-6 group-hover:scale-110 transition-transform ${feature.color}`}>
                                <feature.icon className="w-6 h-6" />
                            </div>
                            <h3 className="text-xl font-bold mb-3">{feature.title}</h3>
                            <p className="text-slate-400 leading-relaxed">
                                {feature.desc}
                            </p>
                        </motion.div>
                    ))}
                </div>
            </div>
        </section>
    );
};

export default Features;
