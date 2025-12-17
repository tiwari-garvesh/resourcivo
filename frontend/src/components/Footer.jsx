import { Github, Twitter, Linkedin } from 'lucide-react';

const Footer = () => {
    return (
        <footer className="py-12 border-t border-white/5 bg-[#0a0f1c]">
            <div className="max-w-7xl mx-auto px-4">
                <div className="grid grid-cols-1 md:grid-cols-4 gap-12 mb-12">
                    <div className="col-span-1 md:col-span-2">
                        <div className="flex items-center gap-2 mb-6">
                            <div className="w-8 h-8 bg-primary rounded-lg flex items-center justify-center font-bold text-white">R</div>
                            <span className="text-xl font-bold">Resourcivo</span>
                        </div>
                        <p className="text-slate-400 max-w-sm mb-6">
                            Empowering educational institutions with modern, efficient, and beautiful management tools.
                        </p>
                        <div className="flex gap-4">
                            {[Github, Twitter, Linkedin].map((Icon, i) => (
                                <a key={i} href="#" className="w-10 h-10 rounded-full bg-white/5 flex items-center justify-center text-slate-400 hover:bg-primary hover:text-white transition-all">
                                    <Icon className="w-5 h-5" />
                                </a>
                            ))}
                        </div>
                    </div>

                    <div>
                        <h4 className="font-bold mb-6">Product</h4>
                        <ul className="space-y-4 text-slate-400">
                            <li><a href="#" className="hover:text-primary transition-colors">Features</a></li>
                            <li><a href="#" className="hover:text-primary transition-colors">Pricing</a></li>
                            <li><a href="#" className="hover:text-primary transition-colors">Showcase</a></li>
                            <li><a href="#" className="hover:text-primary transition-colors">Roadmap</a></li>
                        </ul>
                    </div>

                    <div>
                        <h4 className="font-bold mb-6">Legals</h4>
                        <ul className="space-y-4 text-slate-400">
                            <li><a href="#" className="hover:text-primary transition-colors">Privacy Policy</a></li>
                            <li><a href="#" className="hover:text-primary transition-colors">Terms of Service</a></li>
                            <li><a href="#" className="hover:text-primary transition-colors">Cookie Policy</a></li>
                        </ul>
                    </div>
                </div>

                <div className="border-t border-white/5 pt-8 text-center text-slate-500 text-sm">
                    <p>&copy; {new Date().getFullYear()} Resourcivo Inc. All rights reserved.</p>
                </div>
            </div>
        </footer>
    );
};

export default Footer;
