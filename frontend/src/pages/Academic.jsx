import { useState, useEffect } from 'react';
import Navbar from '../components/Navbar';
import academicService from '../services/academic.service';
import { BookOpen, GraduationCap, Users, Calendar } from 'lucide-react';
import { motion } from 'framer-motion';

const Academic = () => {
    const [courses, setCourses] = useState([]);
    const [subjects, setSubjects] = useState([]);
    const [classrooms, setClassrooms] = useState([]);
    const [activeTab, setActiveTab] = useState('courses');
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const [coursesData, subjectsData, classroomsData] = await Promise.all([
                academicService.getAllCourses(),
                academicService.getAllSubjects(),
                academicService.getAllClassrooms()
            ]);
            setCourses(coursesData);
            setSubjects(subjectsData);
            setClassrooms(classroomsData);
        } catch (error) {
            console.error("Failed to fetch academic data", error);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="min-h-screen">
            <Navbar />
            <main className="max-w-7xl mx-auto p-6 md:p-8 pt-24">
                <motion.div
                    initial={{ opacity: 0, y: 10 }}
                    animate={{ opacity: 1, y: 0 }}
                >
                    <div className="flex justify-between items-end mb-8">
                        <div>
                            <h1 className="text-3xl font-bold title-gradient">Academic Center</h1>
                            <p className="text-slate-400">Manage courses and curriculum</p>
                        </div>

                        <div className="flex bg-slate-900/50 p-1 rounded-xl border border-white/5">
                            <button
                                onClick={() => setActiveTab('courses')}
                                className={`px-4 py-2 rounded-lg text-sm font-medium transition-all ${activeTab === 'courses' ? 'bg-primary text-white shadow-lg shadow-primary/25' : 'text-slate-400 hover:text-white hover:bg-white/5'}`}
                            >
                                Courses
                            </button>
                            <button
                                onClick={() => setActiveTab('subjects')}
                                className={`px-4 py-2 rounded-lg text-sm font-medium transition-all ${activeTab === 'subjects' ? 'bg-primary text-white shadow-lg shadow-primary/25' : 'text-slate-400 hover:text-white hover:bg-white/5'}`}
                            >
                                Subjects
                            </button>
                            <button
                                onClick={() => setActiveTab('classrooms')}
                                className={`px-4 py-2 rounded-lg text-sm font-medium transition-all ${activeTab === 'classrooms' ? 'bg-primary text-white shadow-lg shadow-primary/25' : 'text-slate-400 hover:text-white hover:bg-white/5'}`}
                            >
                                Classrooms
                            </button>
                        </div>
                    </div>

                    {loading ? (
                        <div className="text-center p-10 text-slate-400">Loading academic data...</div>
                    ) : (
                        <>
                            {activeTab === 'courses' && (
                                <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                                    {courses.map(course => (
                                        <motion.div
                                            key={course.id}
                                            initial={{ opacity: 0 }}
                                            animate={{ opacity: 1 }}
                                            className="glass-panel p-6 hover:bg-white/5 transition-colors group"
                                        >
                                            <div className="flex justify-between items-start mb-4">
                                                <div className="bg-purple-500/20 p-3 rounded-xl text-purple-300">
                                                    <GraduationCap className="w-6 h-6" />
                                                </div>
                                                <span className="text-xs font-mono text-slate-500">ID: {course.id}</span>
                                            </div>
                                            <h3 className="text-xl font-bold mb-2">{course.name}</h3>
                                            <p className="text-slate-400 text-sm mb-4 line-clamp-2">{course.description}</p>

                                            <div className="flex items-center gap-4 text-xs text-slate-300 mt-4 pt-4 border-t border-white/5">
                                                <span className="flex items-center gap-1">
                                                    <Calendar className="w-3 h-3" /> {course.duration} Semesters
                                                </span>
                                                <span className="flex items-center gap-1">
                                                    <Users className="w-3 h-3" /> Students
                                                </span>
                                            </div>
                                        </motion.div>
                                    ))}
                                    {courses.length === 0 && <p className="text-slate-400 col-span-3 text-center py-10">No courses found.</p>}
                                </div>
                            )}

                            {activeTab === 'subjects' && (
                                <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                                    {subjects.map(subject => (
                                        <motion.div
                                            key={subject.id}
                                            initial={{ opacity: 0 }}
                                            animate={{ opacity: 1 }}
                                            className="glass-panel p-6 hover:bg-white/5 transition-colors"
                                        >
                                            <div className="flex justify-between items-start mb-4">
                                                <div className="bg-teal-500/20 p-3 rounded-xl text-teal-300">
                                                    <BookOpen className="w-6 h-6" />
                                                </div>
                                                <span className="bg-slate-700/50 px-2 py-1 rounded text-xs text-slate-300">
                                                    Credits: {subject.credits || 'N/A'}
                                                </span>
                                            </div>
                                            <h3 className="text-xl font-bold mb-1">{subject.name}</h3>
                                            <p className="text-xs text-primary mb-3 uppercase tracking-wider">CODE: {subject.code || 'N/A'}</p>

                                            <div className="mt-4 pt-4 border-t border-white/5">
                                                <p className="text-xs text-slate-400">Department</p>
                                                <p className="text-sm font-medium">{subject.departmentName || 'General'}</p>
                                            </div>
                                        </motion.div>
                                    ))}
                                    {subjects.length === 0 && <p className="text-slate-400 col-span-3 text-center py-10">No subjects found.</p>}
                                </div>
                            )}

                            {activeTab === 'classrooms' && (
                                <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                                    {classrooms.map(room => (
                                        <motion.div
                                            key={room.id}
                                            initial={{ opacity: 0 }}
                                            animate={{ opacity: 1 }}
                                            className="glass-panel p-6 hover:bg-white/5 transition-colors"
                                        >
                                            <div className="flex justify-between items-start mb-4">
                                                <div className="bg-orange-500/20 p-3 rounded-xl text-orange-300">
                                                    <Users className="w-6 h-6" /> {/* Using Users as icon for now */}
                                                </div>
                                                <span className={`px-2 py-1 rounded text-xs font-bold ${room.isAvailable ? 'bg-green-500/20 text-green-300' : 'bg-red-500/20 text-red-300'}`}>
                                                    {room.isAvailable ? 'AVAILABLE' : 'OCCUPIED'}
                                                </span>
                                            </div>
                                            <h3 className="text-xl font-bold mb-1">{room.name}</h3>
                                            <p className="text-sm text-slate-400 mb-4">Floor: {room.floor} • Capacity: {room.capacity}</p>

                                            <div className="flex gap-2 mt-4 pt-4 border-t border-white/5 flex-wrap">
                                                {room.hasProjector && (
                                                    <span className="text-[10px] bg-white/10 px-2 py-1 rounded text-slate-300">Projector</span>
                                                )}
                                                {room.hasSmartboard && (
                                                    <span className="text-[10px] bg-white/10 px-2 py-1 rounded text-slate-300">Smartboard</span>
                                                )}
                                                {!room.hasProjector && !room.hasSmartboard && (
                                                    <span className="text-[10px] text-slate-500">Standard Room</span>
                                                )}
                                            </div>
                                        </motion.div>
                                    ))}
                                    {classrooms.length === 0 && <p className="text-slate-400 col-span-3 text-center py-10">No classrooms found.</p>}
                                </div>
                            )}
                        </>
                    )}

                </motion.div>
            </main>
        </div>
    );
};

export default Academic;
