<template>
    <div class="custom-select">
        <label v-if="label" :for="id" class="custom-select__label">{{ label }}</label>
        <select 
            @change="$emit('input', $event.target.value)" 
            :id="id" 
            class="custom-select__input"
            :value="value">
            
            <option 
                v-for="(option, index) in options" 
                :key="`option-${index}`" 
                :value="option.value">

                {{ option.label || option.value }}
            </option>
        </select>
    </div>
</template>

<script>
export default {
    name: 'CustomSelect',
    props: {
        variant: String,
        label: String,
        value: String,
        id: String,
        options: Array
    }
}
</script>

<style lang="scss">
    .custom-select {

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
            width: 100%;
        }
    }
</style>