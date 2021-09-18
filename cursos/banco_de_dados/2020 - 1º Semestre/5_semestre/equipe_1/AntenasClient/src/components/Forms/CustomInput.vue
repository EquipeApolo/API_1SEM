<template>
    <div class="custom-input">
        <label v-if="label" :for="id" class="custom-input__label">{{ label }}</label>
        <input 
            class="custom-input__input" 
            @input="$emit('input', $event.target.value)"
            :type="type" 
            :placeholder="placeholder"
            :value="value"
            :id="id"
        />
    </div>
</template>

<script>
export default {
    name: 'CustomInput',
    props: {
        variant: String,
        type: String,
        label: String,
        placeholder: String,
        value: String,
        id: String
    }
}
</script>

<style lang="scss">
    .custom-input {

        flex-grow: 1;

        &__label {
            margin-bottom: spacing(1);
            display: block;
        }

        &__input {
            padding: spacing(1) spacing(2);
            border: solid 1px $color-gray-light;
            font-family: 'Work Sans', sans-serif;
            border-radius: 4px;
            font-size: 14px;
            flex-grow: 1;
            width: calc(100% - #{spacing(2)} * 2);

            &::placeholder {
                color: $color-gray;
            }

            &[type=search] {
                padding-left: spacing(5);
            }
        }
    }
</style>