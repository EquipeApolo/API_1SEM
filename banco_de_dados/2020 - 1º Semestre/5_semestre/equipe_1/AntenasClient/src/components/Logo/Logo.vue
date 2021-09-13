<template>
    <div class="logo" :class="`logo--${ variant }`">
        <h1 class="title">Antenas</h1>
        <h2 class="subtitle">
            Um projeto FATEC
        </h2>
    </div>
</template>

<script>

export default {
    name: 'Logo',
    props: {
        variant: String
    }
}
</script>

<style lang="scss" scoped>
    .logo {
        display: inline-flex;
        align-items: center;
        flex-direction: column;
        color: white;

        .title {
            font-weight: $font-weight-semibold;
            font-size: 24px;
            margin-bottom: 4px;
        }

        .subtitle {
            font-weight: $font-weight-light;
            font-size: 12px;
            margin-bottom: 4px;
        }

        &--blue {
            color: $color-blue;
        }
    }
</style>