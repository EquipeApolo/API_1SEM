<template>
    <a href class="profile">
        <div class="profile__info">
            <span class="profile__name">{{ user.name }}</span>
            <a href @click.prevent="logOut()" class="profile__logout">Sair</a>
        </div>
    </a>
</template>

<script>
import { LogoutMixin } from '@/helpers/Mixins.js';

export default {
    name: 'Profile',
    mixins: [LogoutMixin],
    computed: {
        user() {
            return this.$store.state.user;
        }
    }
}
</script>

<style lang="scss">
    .profile {
        text-decoration: none;
        display: flex;
        align-items: center;

        &__info {
            display: flex;
            flex-direction: column;
            text-align: right;
            color: $color-black;
        }

        &__name {
            font-weight: $font-weight-semibold;
            font-size: 16px;
        }

        &__logout {
            align-self: flex-end;
            margin-top: 4px;
            font-weight: 600;
        }
    }
</style>