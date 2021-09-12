<template>
    <form @submit.prevent="submit()" class="project-update-form">
        <h5 class="project-update-form__title">
            {{ getFormTitle() }}
        </h5>

        <fieldset class="project-update-form__section" v-if="updatedProject.progress === 3 && $store.getters.isRepresentative">
            <CustomInput
                class="project-update-form__field"
                label="Descrição completa" 
                v-model="updatedProject.completeDescription" 
            />
            <CustomInput
                class="project-update-form__field"
                label="Descrição da técnologia" 
                v-model="updatedProject.technologyDescription" 
            />
        </fieldset>

        <fieldset class="project-update-form__section" v-if="updatedProject.progress === 5 && $store.getters.isCadi && !updatedProject.meeting.chosenDate">
            <CustomInput
                class="project-update-form__field"
                label="Local da reunião - CEP" 
                v-model="updatedProject.meeting.address.zip"
            />
            <CustomInput
                class="project-update-form__field"
                label="Local da reunião - Cidade" 
                v-model="updatedProject.meeting.address.city"
            />
            <CustomInput
                class="project-update-form__field"
                label="Local da reunião - Rua" 
                v-model="updatedProject.meeting.address.street"
            />
            <CustomInput
                class="project-update-form__field"
                label="Local da reunião - Numero" 
                v-model="updatedProject.meeting.address.number"
            />

            <ul class="project-update-form__possible-dates" v-if="updatedProject.meeting.possibleDate.length">
                <span class="title">Datas possíveis cadastradas:</span>
                <li class="project-update-form__possible-date" v-for="possibleDate in updatedProject.meeting.possibleDate || []" :key="possibleDate.datetime">
                    <span>{{ getFormatedDate(possibleDate.dateTime) }}</span>
                    <a @click.prevent="removePossibleDate(index)" href>Remover</a>
                </li>
            </ul>

            <CustomInput
                class="project-update-form__field"
                label="Data da reunião Ex: 20/05/2020 14:30"
                v-model="newPossibleDate"
            />
            <CustomButton :disabled="!dateInputIsValid()" type="button" @click="addPossibleDate()" class="form-button">
                Adicionar possível data
            </CustomButton>
        </fieldset>

        <fieldset class="project-update-form__section" v-if="updatedProject.progress === 5 && $store.getters.isRepresentative && updatedProject.meeting.possibleDate">
            <CustomSelect
                class="project-update-form__field"
                label="Data da reunião" 
                v-model="chosenDate"
                :options="getMeetingOptions()"
            />
        </fieldset>

        <fieldset class="project-update-form__section" v-if="project.progress === 6 && $store.getters.isCadi">
            <CustomSelect
                class="project-update-form__field"
                label="Professor responsável" 
                v-model="selectedTeacher"
                :options="getTeachersOptions()"
            />
        </fieldset>

        <fieldset class="project-update-form__section" v-if="project.progress === 6 && $store.getters.isTeacher">
            <ul class="project-update-form__possible-dates" v-if="updatedProject.meeting.possibleDate.length">
                <span class="title">Alunos inclusos:</span>
                <li class="project-update-form__possible-date" v-for="(student, index) in updatedProject.students || []" :key="student">
                    <span>{{ getStudentName(student) }}</span>
                    <a @click.prevent="removeStudent(index)" href v-if="updatedProject.studentResponsible !== student">
                        Remover
                    </a>
                    <span v-else>Representante</span>
                </li>
            </ul>

            <CustomSelect
                class="project-update-form__field"
                label="Selecionar aluno:" 
                v-model="selectedStudent"
                :options="getStudentsOptions()"
            />
        </fieldset>

        <fieldset class="project-update-form__section" v-if="updatedProject.progress === 6 && $store.getters.isStudent">
            <CustomInput
                class="project-update-form__field"
                label="Link para o seu projeto:" 
                v-model="deliver.link" 
            />
            <CustomTextarea
                class="project-update-form__field"
                label="Comentários:"
                v-model="deliver.comment"
                :rows="4"
            />
            <ul class="project-update-form__possible-dates" v-if="updatedProject.meeting.possibleDate.length">
                <span class="title">Membros:</span>
                {{ deliver.students }}
                <li class="project-update-form__possible-date" v-for="(student, index) in deliver.students || []" :key="student">
                    <span>{{ getStudentName(student) }}</span>
                    <a @click.prevent="removeMember(index)" href v-if="student !== $store.state.user.id">
                        Remover
                    </a>
                    <span v-else>Você</span>
                </li>
            </ul>
            <CustomSelect
                class="project-update-form__field"
                label="Selecionar membros:" 
                v-model="selectedMember"
                :options="getStudentsOptions(true)"
            />
            <CustomButton type="button" class="form-button" :disabled="!selectedMember" @click="selectMember()">
                Adicionar membro
            </CustomButton>
        </fieldset>

        <fieldset class="project-update-form__section">
            <CustomButton class="form-button" :disabled="!isValid" v-if="!isToApproveOrDeny() && project.progress === 6 && $store.getters.isTeacher && project.studentResponsible">
                Adicionar aluno
            </CustomButton>
            <CustomButton class="form-button" :disabled="!isValid" v-else-if="!isToApproveOrDeny()">
                Enviar atualização
            </CustomButton>
            <CustomButton type="button" @click="submit(true)" class="form-button" v-if="isToApproveOrDeny()">
                Aprovar
            </CustomButton>
            <CustomButton type="button" @click="submit(false)" variant="red" class="form-button" v-if="isToApproveOrDeny()">
                Rejeitar
            </CustomButton>
        </fieldset>
    </form>
</template>

<script>
import EventBus from '@/helpers/EventBus.js'
import ProjectService from '@/services/ProjectService.js';
import UserService from '@/services/UserService.js';
import CustomInput from '@/components/Forms/CustomInput.vue'
import CustomButton from '@/components/Forms/CustomButton.vue'
import CustomSelect from '@/components/Forms/CustomSelect.vue'
import CustomTextarea from '@/components/Forms/CustomTextarea.vue'

export default {
    name: 'ProjectUpdateForm',
    props: {
        project: Object
    },
    components: {
        CustomInput,
        CustomButton,
        CustomSelect,
        CustomTextarea,
    },
    mounted() {
        UserService
            .getTeacherUsers()
            .then(teachers => this.teachers = teachers);

        UserService
            .getStudentsUsers()
            .then(students => this.students = students);
    },
    computed: {
        updatedProject() {
            return this.project;
        },
        isValid() {
            let project = this.updatedProject;
            return {
                3: this.$store.getters.isRepresentative && project.completeDescription && project.technologyDescription,
                5: (this.$store.getters.isRepresentative && this.chosenDate) || (this.$store.getters.isCadi && project.meeting.possibleDate && project.meeting.possibleDate.length && project.meeting.address),
                6: (this.$store.getters.isCadi && this.selectedTeacher) || 
                    (this.$store.getters.isTeacher && this.selectedStudent) ||
                    (this.$store.getters.isStudent && this.deliver.link)
            }[project.progress];
        }
    },
    methods: {
        selectMember() {
            this.deliver.students.push(+this.selectedMember);
            this.selectedMember = null;
        },
        removeMember(index) {
            this.deliver.students.splice(index, 1);
        },
        getStudentName(studentId) {
            let student = this.students.filter(student => student.id === studentId)[0];
            return student ? student.name : '';
        },
        removeStudent(index) {
            this.updatedProject.students.splice(index, 1);
        },
        getFormTitle() {
            if (this.updated) {
                return 'Atualizações enviadas com sucesso!';
            }
            else if (this.$store.getters.isRepresentative) {
                return 'Atualize as informações do seu projeto para prosseguir:';
            }
            else if (this.$store.getters.isCadi && [2, 4].includes(this.project.progress)) {
                return 'Leia as especificações do projeto e decida se ele está apto a continuar:';
            }
            else if (this.$store.getters.isCadi && this.project.progress === 5) {
                return 'Escolha opções de datas para uma reunião com o representante do projeto:';
            }
            else if (this.$store.getters.isCadi && this.project.progress === 6) {
                return 'Escolha um professor cadastrado para ser o responsável por este projeto:';
            }
            else if (this.$store.getters.isTeacher && this.project.progress === 6) {
                return 'Escolha um aluno responsável para o projeto:';
            }
        },
        getMeetingOptions() {
            return this.updatedProject.meeting.possibleDate.map(option => ({ 
                label: this.getFormatedDate(option.dateTime), 
                value: option.dateTime 
            }));
        },
        getTeachersOptions() {
            return [{ value: null }, ...this.teachers.map(teacher => ({ label: teacher.name, value: teacher.id }))];
        },
        getStudentsOptions() {
            return [
                { value: null }, 
                ...this.students
                    .filter(student => student.id !== this.$store.state.user.id && 
                        !this.deliver.students.includes(student.id) &&
                        ((inProjectOnly && this.project.students.includes(student.id)) || (!inProjectOnly && !this.project.students.includes(student.id))))
                    .map(student => ({ label: student.name, value: student.id }))
            ];
        },
        isToApproveOrDeny() {
            return this.$store.getters.isCadi && [2, 4].includes(this.project.progress);
        },
        addPossibleDate() {
            if (!this.updatedProject.meeting.possibleDate) {
                this.updatedProject.meeting.possibleDate = [];
            }

            let datetime = this.newPossibleDate.split(' ');
            let date = datetime[0].split('/');
            let time = datetime[1];

            let dateObject = new Date(`${date[1]}/${date[0]}/${date[2]} ${time}`);
            this.updatedProject.meeting.possibleDate.push({ dateTime: dateObject });
            this.newPossibleDate = '';
        },
        submit(approved) {
            if (this.$store.getters.isCadi && this.updatedProject.progress == 6) {
                this.updatedProject.teacher = this.selectedTeacher;;
            }
            else if (this.$store.getters.isTeacher) {
                this.updatedProject.studentResponsible = this.students.filter(student => student.id == this.selectedStudent)[0].id;
                this.updatedProject.students.push(this.updatedProject.studentResponsible);
            }

            if (this.chosenDate) {
                this.updatedProject.meeting.chosenDate = this.chosenDate;
            }
            if (this.deliver.link) {
                this.updatedProject.deliver.push(this.deliver);
            }

            ProjectService.updateProject(this.updatedProject, approved).then(() => {
                this.selectedTeacher = null;
                this.selectedStudent = null;
                this.deliver = {};
                this.updated = true;
                setTimeout(() => this.updated = false, 5000);
            });
        },
        dateInputIsValid() {
            return /^(\d{2})\/(\d{2})\/(\d{4})\s(\d{2})\:(\d{2})/.test(this.newPossibleDate);
        },
        forceTwoChars(number) {
            return ("0" + number).slice(-2);
        },
        getFormatedDate(dateRaw) {
            let date = new Date(dateRaw);
            let day = this.forceTwoChars(date.getDate());
            let month = this.forceTwoChars(date.getMonth());
            let year = date.getFullYear();
            let hour = this.forceTwoChars(date.getHours());
            let minute = this.forceTwoChars(date.getMinutes());
            return `${day}/${month}/${year} ${hour}:${minute}`;
        },
        removePossibleDate(index) {
            this.updatedProject.meeting.possibleDate.splice(index, 1);
        }
    },
    data() {
        return {
            newPossibleDate: '',
            updated: false,
            teachers: [],
            students: [],
            selectedTeacher: null,
            selectedMember: null,
            deliver: {
                link: '',
                comment: '',
                responsible: this.$store.state.user.id,
                students: [this.$store.state.user.id]
            },
            selectedStudent: null,
            chosenDate: undefined
        };
    }
}
</script>

<style lang="scss" scoped>
    .project-update-form {
        background-color: $color-blue-extra-light;
        border: solid 1px $color-blue-light;
        padding: spacing(1);
        border-radius: 3px;

        &__title {
            font-weight: $font-weight-semibold;
            margin-bottom: spacing(2);
            color: $color-blue;
        }

        &__possible-dates {
            margin: spacing(1) 0;
        }

        &__possible-date {
            margin: spacing(1) 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        &__section {
            margin-bottom: spacing(2);

            .form-button {
                margin-right: spacing(2);

                &:last-child {
                    margin-right: 0;
                }
            }
        }

        &__field {
            margin-bottom: spacing(1);
        }

        &__section,
        &__field {

            &:last-child {
                margin-bottom: 0;
            }
        }
    }
</style>