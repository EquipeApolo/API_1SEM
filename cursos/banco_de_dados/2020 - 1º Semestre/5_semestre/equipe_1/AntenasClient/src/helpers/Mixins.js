export const LogoutMixin = {
    methods: {
        logOut() {
            this.$store.commit('LOGOUT_CURRENT_USER');
            this.$router.push('/');
        }
    }
}