import { motion } from 'framer-motion';

import { ArrowRight, Sparkles } from 'lucide-react';
import { Link } from 'react-router-dom';

const Hero = () => {
    return (
        <section className="relative pt-32 pb-20 px-6 overflow-hidden">
            {/* Background Blobs */}
            <div className="absolute top-20 left-0 w-96 h-96 bg-primary/20 rounded-full blur-3xl animate-pulse-slow"></div>
            <div className="absolute bottom-0 right-0 w-[500px] h-[500px] bg-secondary/10 rounded-full blur-3xl animate-pulse-slow" style={{ animationDelay: '2s' }}></div>

            <div className="max-w-7xl mx-auto flex flex-col items-center text-center relative z-10">
                <motion.div
                    initial={{ opacity: 0, y: 20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 0.8 }}
                >
                    <div className="inline-flex items-center gap-2 px-4 py-2 rounded-full bg-white/5 border border-white/10 backdrop-blur-md mb-8 hover:bg-white/10 transition-colors cursor-default">
                        <Sparkles className="w-4 h-4 text-yellow-400" />
                        <span className="text-sm text-slate-300">The Future of Campus Management</span>
                    </div>
                </motion.div>

                <motion.h1
                    initial={{ opacity: 0, y: 20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 0.8, delay: 0.2 }}
                    className="text-5xl md:text-7xl font-extrabold tracking-tight mb-6"
                >
                    <span className="bg-clip-text text-transparent bg-gradient-to-r from-white via-slate-200 to-slate-400">
                        Manage your Campus
                    </span>
                    <br />
                    <span className="bg-clip-text text-transparent bg-gradient-to-r from-primary to-purple-500 text-glow">
                        Like Never Before
                    </span>
                </motion.h1>

                <motion.p
                    initial={{ opacity: 0, y: 20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 0.8, delay: 0.4 }}
                    className="text-lg md:text-xl text-slate-400 max-w-2xl mb-10 leading-relaxed"
                >
                    Resourcivo brings Transport, Academic, and Resource management into a single, beautiful dashboard.
                    Experience efficiency with a touch of modern elegance.
                </motion.p>

                <motion.div
                    initial={{ opacity: 0, y: 20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 0.8, delay: 0.6 }}
                    className="flex flex-col sm:flex-row gap-4"
                >
                    <Link to="/register" className="btn-primary flex items-center gap-2 group">
                        Get Started Now
                        <ArrowRight className="w-4 h-4 group-hover:translate-x-1 transition-transform" />
                    </Link>
                    <Link to="/login" className="btn-secondary">
                        Sign In via Portal
                    </Link>
                </motion.div>

                {/* Floating Preview Card */}
                <motion.div
                    initial={{ opacity: 0, scale: 0.9 }}
                    animate={{ opacity: 1, scale: 1 }}
                    transition={{ duration: 1, delay: 0.8 }}
                    className="mt-20 relative w-full max-w-4xl"
                >
                    <div className="absolute inset-0 bg-gradient-to-t from-dark to-transparent z-20 h-40 bottom-0 pointer-events-none"></div>
                    <div className="glass-panel p-2 animate-float">
                        <div className="bg-slate-900/80 rounded-xl overflow-hidden aspect-[16/9] border border-white/5 relative group">
                            <div className="absolute inset-0 flex items-center justify-center">
                                <p className="text-slate-500 font-mono text-sm">Dashboard Preview</p>
                            </div>
                            {/* Abstract UI Lines */}
                            <div className="absolute top-4 left-4 right-4 h-12 bg-white/5 rounded-lg"></div>
                            <div className="absolute top-20 left-4 w-48 h-64 bg-white/5 rounded-lg"></div>
                            <div className="absolute top-20 left-56 right-4 h-32 bg-white/5 rounded-lg"></div>
                            <div className="absolute top-56 left-56 right-4 h-28 bg-white/5 rounded-lg"></div>
                        </div>
                    </div>
                </motion.div>
            </div>
        </section>
    );
};

export default Hero;
