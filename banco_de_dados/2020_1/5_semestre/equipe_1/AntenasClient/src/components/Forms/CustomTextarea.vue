<template>
    <div class="custom-textarea">
        <label v-if="label" :for="id" class="custom-textarea__label">{{ label }}</label>
        <textarea 
            class="custom-textarea__textarea"
            :class="getResizeClass()"
            @input="$emit('input', $event.target.value)" 
            :placeholder="placeholder" 
            :id="id" 
            :rows="rows" 
            :cols="cols" 
            :value="value">
        </textarea>
    </div>
</template>

<script>
export default {
    name: 'CustomTextarea',
    props: {
        variant: String,
        type: String,
        label: String,
        placeholder: String,
        value: String,
        rows: Number,
        cols: Number,
        resize: {
            type: [Boolean, String],
            default: false
        },
        id: String
    },
    methods: {
        getResizeClass() {
            let base = 'custom-textarea__textarea--resize-';
            let resizeMap = {};
            
            resizeMap[base + 'off'] = !this.resize;
            resizeMap[base + 'all'] = this.resize === true;
            resizeMap[base + 'horizontal'] = this.resize === 'horizontal';
            resizeMap[base + 'vertical'] = this.resize === 'vertical';
            
            return resizeMap;
        }
    }
}
</script>

<style lang="scss">
    .custom-textarea {

        flex-grow: 1;

        &__label {
            margin-bottom: spacing(1);
            display: block;
        }

        &__textarea {
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

            &--resize-off {
                resize: none;
            }

            &--resize-all {
                resize: inherit;
            }

            &--resize-horizontal {
                resize: horizontal;
            }

            &--resize-vertical {
                resize: vertical;
            }
        }
    }
</style>