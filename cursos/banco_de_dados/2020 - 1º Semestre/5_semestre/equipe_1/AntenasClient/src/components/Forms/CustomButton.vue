<template>
    <button 
        :id="id" 
        @click="$emit('click', $event.target.value)" 
        class="custom-button"
        :class="`custom-button--${variant}`"
        :type="type"
        :disabled="disabled">
        
        <slot></slot>
    </button>
</template>

<script>
export default {
    name: 'CustomButton',
    props: {
        variant: String,
        type: String,
        id: String,
        disabled: Boolean
    }
}
</script>

<style lang="scss">
    .custom-button {
        border: none;
        padding: spacing(1) spacing(2);
        border-radius: 4px;
        background-color: $color-blue;
        color: white;
        font-family: 'Open Sans', sans-serif;
        font-weight: $font-weight-semibold;
        box-shadow: shadow-depth(2);
        transition: box-shadow .2s ease-in-out, background-color .2s ease-in-out;
        cursor: pointer;

        &:disabled {
            box-shadow: none;
            background-color: $color-gray;
            cursor: default;
        }

        &--white {
            background-color: white;
            color: $color-blue;
        }

        &--red {
            background-color: $color-red;
            color: white;
        }
    }
</style>