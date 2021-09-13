import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import utils from './helpers/Utils'
import MenuIcon from 'vue-material-design-icons/Menu.vue';
import http from '@/helpers/Http'


Vue.prototype.$http = http;

Vue.component('menu-icon', MenuIcon);
Vue.use({
	install() {
		Vue.utls = utils;
		Vue.prototype.$utils = utils;
	}
});

Vue.config.productionTip = false;

if (store.getters.isLoggedIn) {
  	store.dispatch('loadCurrentUserInfo').then(() => store.dispatch('loadProjects'));
}

new Vue({
	router,
	store,
	render: h => h(App)
}).$mount('#app')
