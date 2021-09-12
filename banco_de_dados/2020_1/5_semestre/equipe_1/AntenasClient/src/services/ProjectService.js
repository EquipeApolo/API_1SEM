import http from '../helpers/Http'
import store from '../store/index'
import projects from './Mocks.js'

export default {

    getProjects() {
        return http
            .get('/project/' + store.state.user.id)
            .then(res => {
                let projects = res.data

                let defaulProject = {
                    meeting: {
                        address: {
                            place: '',
                            number: '',
                            street: '',
                            neighborhood: '',
                            city: '',
                            zip: ''
                        },
                        chosenDate: null,
                        possibleDate: [],
                    },
                    students: [],
                    deliver: []
                }

                projects.forEach(project => {
                    if (project.meeting == null) {
                        project.meeting = defaulProject.meeting;
                    }
    
                    if (project.students == null) {
                        project.students = defaulProject.students;
                    }
    
                    if (project.deliver == null) {
                        project.deliver = defaulProject.deliver;
                    }
                });
                
                

                return projects;
            });
        
    },

    addProject(project) {
        return http.post('/project', project).then(res => {
            store.commit('ADD_PROJECTS', [res.data]);
            return res.data;
        });
    },

    updateProject(project, approved) {
        return new Promise(resolve => {

            for (let index = 0; index < projects.length; index++) {
                
                if (projects[index].id === project.id) {
    
                    if (store.getters.isCadi && [2, 4].includes(project.progress)) {
                        project.refused = !approved;
    
                        if (approved) {
                            project.progress = project.progress + 1;
                        }
                        else {
                            store.commit('DESELECT_PROJECT');
                        }
                    }
    
                    if (store.getters.isRepresentative && project.progress === 3) {
                        project.progress = 4;
                    }
    
                    projects[index] = project;
                    break;
                }
            }
            
            http.post("/project/update", project);
            resolve(project);
        });
    }
};