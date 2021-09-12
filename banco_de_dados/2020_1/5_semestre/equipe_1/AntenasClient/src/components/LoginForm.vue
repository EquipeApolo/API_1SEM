<template>
    <form class="login-form" @submit="proceed($event)">
        <h5 class="login-form__title">{{ getTitleMessage() }}</h5>
        
        <div class="login-form__field" v-if="signUp">
            <CustomInput 
                v-model="name" 
                id="name" 
                label="Nome completo"/>
        </div>
        <div class="login-form__field">
            <CustomInput 
                v-model="email" 
                id="email" 
                label="E-mail"/>
        </div>
        <div class="login-form__field">
            <CustomInput 
                v-model="password" 
                id="password" 
                label="Senha"
                type="password" />
        </div>
        <div class="login-form__field" v-if="signUp">
            <CustomSelect 
                v-model="role"
                :options="roles"
                id="role" 
                label="Cargo"/>
        </div>
        <div class="login-form__field">
            <CustomButton type="submit" :disabled="!canProceed()">
                <span v-if="signUp">Se cadastrar</span>
                <span v-if="!signUp">Entrar</span>
            </CustomButton>
        </div>
    </form>
</template>

<script>
import CustomInput from '@/components/Forms/CustomInput.vue';
import CustomButton from '@/components/Forms/CustomButton.vue';
import CustomSelect from '@/components/Forms/CustomSelect.vue';
import UserService from '@/services/UserService.js';


export default {
    name: 'LoginForm',
    props: {
        signUp: Boolean
    },
    components: {
        CustomInput,
        CustomButton,
        CustomSelect,
    },
    data() {
        return {
            justSignedUp: false,
            email: '',
            name: '',
            password: '',
            role: '',
            roles: [
                { value: 'STUDENT', label: 'Aluno'},
                { value: 'TEACHER', label: 'Professor'},
                { value: 'REPRESENTATIVE', label: 'Representante'},
                { value: 'CADI', label: 'CADI'},
            ]
        };
    },
    methods: {
        getTitleMessage() {
            if (this.signUp) {
                return 'Faça seu cadastro na plataforma';
            }
            else if (this.justSignedUp) {
                return 'Cadastro feito com sucesso! Entre na sua conta';
            }
            else {
                return 'Entre na sua conta';
            }
        },
        canProceed() {
            if (this.signUp) {
                return this.email && this.name && this.password && this.role;
            }
            else {
                return this.email && this.password;
            }
        },
        proceed(event) {
            
            event.preventDefault();
            
            if (this.signUp) {
                UserService
                    .registUser({
                        name: this.name,
                        password: this.password,
                        role: this.role,
                        email: this.email
                    })
                    .then(() => this.justSignedUp = true);
            }
            else {
                UserService
                    .authenticateUser({
                        email: this.email,
                        password: this.password
                    })
                    .then(() => this.$router.push('/home'));
            }
        }
    }
}
</script>

<style lang="scss" scoped>
    .login-form {
        width: 300px;
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: spacing(3);
        border-radius: 4px;
        background-color: white;
        box-shadow: shadow-depth(2);

        &__title {
            margin-bottom: spacing(3);
            font-size: 16px;
            font-weight: $font-weight-semibold;
        }

        &__field {
            display: flex;
            justify-content: center;
            align-self: center;
            margin-bottom: spacing(2);
            width: 100%;

            &:last-child {
                margin-bottom: 0;
            }
        }
    }
</style>