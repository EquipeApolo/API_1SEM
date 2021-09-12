<template>
    <ul class="timeline">
        <li 
            class="timeline__phase" 
            :class="{ 'timeline__phase--active': phase == progress }" 
            v-for="(currentPhase, phase) in phases" 
            :key="`phase-${phase}`">

            <span>{{ phase }}. {{ currentPhase }}</span>
        </li>
    </ul>
</template>

<script>

export default {
    name: 'Timeline',
    props: {
        progress: Number
    },
    computed: {
        phases() {
            return this.$store.state.phases;
        }
    },
    data() {
        return { };
    },
    methods: {
    
    }
}
</script>

<style lang="scss" scoped>
    .timeline {
        display: flex;
        flex-direction: column;
        color: white;
        width: 100%;
        justify-content: center;
        font-size: 16px;

        &__phase {
            font-weight: $font-weight-light;
            margin: 4px 0;
            font-size: 14px;

            &--active {
                font-weight: $font-weight-semibold;
                max-width: inherit;
            }
        }
    }
</style>