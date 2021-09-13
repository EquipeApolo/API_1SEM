<template>
    <div class="project-view">
        <div v-if="hasSelectedProject()" class="box project-view__project">
            <div class="box__header">
                <h4 class="title">{{ project.title }}</h4>
                <a href @click.prevent="closeProject()" class="close">
                    <i class="material-icons">close</i>
                </a>
            </div>
            <div class="box__body project-body">
                <ProjectStatus class="project-view__phase" :project="project" />
                
                <ProjectUpdateForm 
                    v-if="projectStatus === 'WAITING' && !project.refused"
                    :project="project"
                    class="project-view__form" 
                />
                
                <div class="project-view__info">
                    <div class="project-view__field" v-if="project.shortDescription">
                        <p class="label">Descrição breve:</p>
                        <p class="text">{{ project.shortDescription }}</p>
                    </div>
                    
                    <div class="project-view__field" v-if="project.completeDescription">
                        <p class="label">Descrição completa:</p>
                        <p class="text">{{ project.completeDescription }}</p>
                    </div>
                    
                    <div class="project-view__field" v-if="project.technologyDescription">
                        <p class="label">Descrição da tecnologia:</p>
                        <p class="text">{{ project.technologyDescription }}</p>
                    </div>

                    <div class="project-view__field" v-if="project.notes">
                        <p class="label">Notas adicionais:</p>
                        <p class="text">{{ project.notes }}</p>
                    </div>

                    <div class="project-view__field" v-if="project.meeting.chosenDate">
                        <p class="label">Reunião de projeto:</p>
                        <p class="text">
                            <strong>Local:</strong> {{ project.meeting.address.street }}, {{ project.meeting.address.number }} - {{ project.meeting.address.city }}
                        </p>
                        <p class="text">
                            <strong>Data e horario:</strong> {{ getDatetime(project.meeting.chosenDate) }}
                        </p>
                    </div>

                    <div class="project-view__field" v-if="$store.getters.isStudent && getStudentDeliver()">
                        <p class="label">Sua entrega:</p>
                        <p class="text">
                            <strong>Link do projeto:</strong> 
                            <a :href="getStudentDeliver().link" target="_blank">{{ getStudentDeliver().link }}</a>
                        </p>
                        <p class="text" v-if="getStudentDeliver().comment">
                            <strong>Comentarios:</strong> {{ getStudentDeliver().comment }}
                        </p>
                    </div>
                    <div class="project-view__field" v-else-if="project.deliver.length">
                        <p class="label">Entregas:</p>
                        <ul class="project-view__deliver">
                            <li class="project-view__deliver-item" v-for="deliver in project.deliver" :key="deliver.link">
                                <p>
                                    <strong>Link:</strong>
                                    <a :href="deliver.link" target="_blank">{{ deliver.link }}</a>
                                </p>    
                                <p v-if="deliver.comment">
                                    <strong>Comentarios:</strong>
                                    <span>{{ deliver.comment }}</span>
                                </p>    
                                <p>
                                    <strong>Membros:</strong>
                                    <span>{{ getMembersList(deliver.students) }}</span>
                                </p>    
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div v-else class="project-view__empty">
            <div class="content">
                <i class="material-icons icon">touch_app</i>
                <p class="text">Selecione um projeto ao lado para saber mais</p>
                <CustomButton 
                    class="button"
                    @click="createProject()"
                    v-if="$store.getters.isRepresentative"
                >
                    Criar projeto
                </CustomButton>
            </div>
        </div>
    </div>
</template>

<script>
import EventBus from '@/helpers/EventBus.js'
import CustomButton from '@/components/Forms/CustomButton.vue'
import ProjectStatus from '@/components/Project/ProjectStatus.vue'
import ProjectUpdateForm from '@/components/Project/ProjectUpdateForm.vue'
import UserService from '@/services/UserService.js';

export default {
    name: 'ProjectView',
	components: {
        CustomButton,
        ProjectStatus,
        ProjectUpdateForm,
    },
    computed: {
        projectStatus() {
            return this.$utils.getProjectStatus(this.project);
        },
        project() {
            return this.$store.state.selectedProject;
        }
    },
    mounted() {
        UserService
            .getStudentsUsers()
            .then(students => this.students = students);
    },
    methods: {
        getMembersList(ids) {
            let date = new Date(choosenDate);	            return ids.map(id => this.students.filter(student => student.id == id)[0].name).join(', ');
        },
        getStudentDeliver() {
            return this.project.deliver.filter(d => d.students.includes(this.$store.state.user.id))[0];
        },
        getDatetime(chosenDate) {
            let date = new Date(chosenDate);
            return `${date.getDate()}/${date.getMonth() + 1}/${date.getFullYear()} ${date.getHours()}:${date.getMinutes()}`;
        },
        hasSelectedProject() {
            return this.project !== null;
        },
        closeProject() {
            this.$store.commit('DESELECT_PROJECT');
        },
        createProject() {
            this.$emit('create');
        }
    },
	data() {
		return {
            students: []
        };
	}
}
</script>

<style scoped lang="scss">
.project-view {

    &__empty {
        
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100%;
        border: 2px dashed #D6D1D1;
        border-radius: 4px;

        .content {
            
            display: flex;
            flex-direction: column;
            align-items: center;
            color: #9E9E9E;

            .icon {
                font-size: 78px;
            }

            .text {
                max-width: 320px;
                font-size: 24px;
                font-weight: $font-weight-semibold;
                text-align: center;
                margin: spacing(3) 0;
                line-height: 1.4em;
            }
        }
    }

    &__project {
        height: 100%;

        .project-body {
            max-height: 100%;
            height: calc(100% - 53px - (#{spacing(2)} * 2));
            overflow-y: auto;
        }
    }

    &__field {

        margin-bottom: spacing(2);

        &--horizontal {
            display: flex;
            align-items: center;
        }
        
        .label {
            color: $color-gray-dark;
            font-weight: $font-weight-semibold;
            margin-bottom: spacing(1);
        }

        .text {
            line-height: 1.4em;
        }
    }

    &__link {
        padding: spacing(1);
        border: solid 1px #0084E3;
        border-radius: 4px;
        color: #0084E3;
        font-weight: $font-weight-semibold;
        margin-right: spacing(2);
        display: block;
    }

    &__phase {
        margin-bottom: spacing(2);
    }

    &__form {
        margin-bottom: spacing(2);
    }

    &__deliver-item {
        margin-bottom: spacing(2);
        line-height: 1.3em;
        padding: spacing(1) 0;
        border-bottom: solid 1px #ccc;
        strong {
            margin-right: spacing(1);
        }
        &:last-child {
            margin-bottom: 0;
            border-bottom: 0;
        }
    }
}
</style>
