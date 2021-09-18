import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store'
import Landing from '../views/Landing.vue';
import Home from '../views/Home.vue';

Vue.use(VueRouter)

const routes = [
	{
		path: '/',
		name: 'Landing',
		component: Landing,
		meta: {
		public: true
		}
	},
	{
		path: '/home',
		name: 'Home',
		component: Home,
		meta: {
			public: false
		}
	},
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
});

router.beforeEach((to, from, next) => {
	if (!to.meta.public && !store.getters.isLoggedIn) {
		return next({ path: '/' });
	}
	else if (to.meta.public && store.getters.isLoggedIn) {
		return next({ path: '/home' });
	}

  	next();
});

export default router
