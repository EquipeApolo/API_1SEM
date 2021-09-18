import http from '../helpers/Http'
import store from '../store/index'

const routeMap = {
    'STUDENT': '/student',
    'CADI': '/cadi',
    'REPRESENTATIVE': '/entrepreneur',
    'TEACHER': '/teacher'
};

export default {

    authenticateUser({ email, password }) {
        return http
            .post('/login', { email, password })
            .then(res => {
                localStorage.setItem('USER_ID', res.data.id);
                localStorage.setItem('USER_ROLE', res.data.authorizations[0].name);
                store.commit('SET_CURRENT_USER', {
                    token: res.data.token,
                    user: {
                        name: res.data.name,
                        role: res.data.authorizations[0].name,
                        email: res.data.email,
                        id: res.data.id
                    }
                });
            });
    },

    registUser({ name, email, password, role }) {
        const user = { 
            name, 
            email, 
            password, 
            role
        };
        
        return http.post(routeMap[role], user);
    },

    getUserInfo() {
        const userId = localStorage.getItem('USER_ID');
        const userRole = localStorage.getItem('USER_ROLE');
        const token = localStorage.getItem('token');

        return http
            .get(`${routeMap[userRole]}/${userId}`)
            .then(res => {

                const user = res.data;

                return {
                    id: user.id,
                    name: user.name,
                    email: user.email,
                    token: token,
                    role: userRole
                };
            });
    },

    getTeacherUsers() {

        return http
            .get('/teacher')
            .then(res => {

                const teachers = res.data;

                return teachers.map(teacher => ({
                    id: teacher.id,
                    email: teacher.email,
                    name: teacher.name,
                    authorizations: teacher.authorizations[0].name
                }));
            });
    },

    getStudentsUsers() {

        return http
            .get('/student')
            .then(res => {

                const students = res.data;

                return students.map(teacher => ({
                    id: teacher.id,
                    email: teacher.email,
                    name: teacher.name,
                    role: teacher.authorizations[0].name
                }));
            });
    }
};