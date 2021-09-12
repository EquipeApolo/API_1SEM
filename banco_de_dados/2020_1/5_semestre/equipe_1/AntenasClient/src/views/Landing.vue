<template>
  <div class="landing">
    <div class="landing__wrapper">
      <header class="landing__header">
            <Logo variant="blue" />
            <div class="actions">
                <CustomButton class="button" @click="goSignUp()">
                    Se cadastrar
                </CustomButton>
                <CustomButton variant="white" class="button" @click="goLogin()">
                    Entrar
                </CustomButton>
            </div>
      </header>
      <div class="landing__body">
          <div class="landing__info">
            <h3 class="title">
                <span>Conectando</span>
                <span class="big">
                    os <strong>melhores alunos</strong>
                </span>
                <span class="big">
                    às <strong>melhores empresas</strong>
                </span>
            </h3>

            <img class="illustration" src="@/assets/images/business_deal.svg" alt="Pessoas fazendo negócio">
          </div>
          <LoginForm :signUp="signUp" class="landing__login" />
      </div>
    </div>
  </div>
</template>

<script>
import Logo from '@/components/Logo/Logo.vue'
import LoginForm from '@/components/LoginForm.vue'
import CustomButton from '@/components/Forms/CustomButton.vue'

export default {
  name: 'Landing',
  components: {
      Logo,
      LoginForm,
      CustomButton
  },
  data() {
      return {
          signUp: true
      };
  },
  methods: {
      goLogin() {
          this.signUp = false;
      },
      goSignUp() {
          this.signUp = true;
      }
  }
}
</script>

<style lang="scss" scoped>
  .landing {

    &__header {
        display: flex;
        align-items: center;
        justify-content: space-between;
        height: 100px;

        .actions {
            display: flex;

            .button {
                margin-right: spacing(2);

                &:last-child {
                    margin-right: 0;
                }
            }
        }
    }

    &__body {
        display: flex;
        justify-content: space-between;
        margin-top: spacing(5);
    }

    &__login {
        align-self: flex-start;
    }

    &__info {
        .title {
            display: flex;
            flex-direction: column;
            line-height: 2em;
            font-size: 24px;
            font-weight: $font-weight-light;
            
            strong {
                font-weight: $font-weight-semibold;
                color: $color-blue;
            }

            .big {
                font-size: 29px;
            }
        }

        .illustration {
            margin-top: spacing(6);
            max-width: 300px;
        }
    }

    &__wrapper {
      max-width: 1080px;
      width: 90%;
      margin: 0 auto;
    }
  }
</style>