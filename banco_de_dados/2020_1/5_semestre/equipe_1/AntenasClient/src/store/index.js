import Vue from 'vue'
import Vuex from 'vuex'
import UserService from '@/services/UserService.js'
import ProjectService from '@/services/ProjectService.js'
import { updateProject } from '@/helpers/Utils.js';
import EventBus from '@/helpers/EventBus.js';

Vue.use(Vuex)

const state = {
	phases: {
		1: 'Cadastro Inicial',
		2: 'Avaliação Inicial',
		3: 'Cadastro Detalhado',
		4: 'Avaliação Detalhada',
		5: 'Reunião',
		6: 'Entrega'
	},
	projects: [],
	selectedProject: null,
	user: {},
	token: localStorage.getItem('token')
};

const mutations = {
	SET_CURRENT_USER(state, { token, user }) {
		state.user = user;
		state.token = token;
		localStorage.setItem('token', token);
	},
	LOGOUT_CURRENT_USER(state) {
		state.token = null;
		localStorage.removeItem('token');
	},
	ADD_PROJECTS(state, projects) {
		state.projects = state.projects.concat(projects);
	},
	UPDATE_PROJECT(state, project) {
		for (let index = 0; index < state.projects.length; index++) {
			
			if (state.projects[index].id === project.id) {
				updateProject(state.projects[index], project);
				EventBus.$emit('PROJECT-UPDATED');
				break;
			}
		}
	},
	SELECT_PROJECT(state, projectId) {
		state.selectedProject = state.projects.filter(project => project.id === projectId)[0];
	},
	DESELECT_PROJECT(state) {
		state.selectedProject = null;
	},
};

const actions = {
	loadCurrentUserInfo({ commit, state }) {
		return new Promise((resolve, reject) => {

			UserService
				.getUserInfo()
				.then(user => {
					commit('SET_CURRENT_USER', { token: user.token, user });
					resolve();
				})
				.catch(err => {
					console.log(err);
					reject();
				})
		})
	},
	loadProjects({ commit, state }) {
		return new Promise((resolve, reject) => {

			const token = state.token;

			ProjectService
				.getProjects()
				.then(projects => {
					commit('ADD_PROJECTS', projects);
					resolve();
				})
				.catch(err => {
					console.log(err);
					reject();
				});
		})
	}
};

const getters = {
	isLoggedIn(state) {
		return !!state.token;
	},
	isCadi(state) {
		return state.user.role === 'CADI';
	},
	isStudent(state) {
		return state.user.role === 'STUDENT';
	},
	isRepresentative(state) {
		return state.user.role === 'REPRESENTATIVE';
	},
	isTeacher(state) {
		return state.user.role === 'TEACHER';
	},
};

export default new Vuex.Store({
	state,
	mutations,
	actions,
	getters
})
