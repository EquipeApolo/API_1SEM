<template>
  <div class="home">
    <Toolbar />
    <div class="home__wrapper">
      <Projects 
        class="home__projects"
        @create="createProject"
      />
      
      <ProjectView
        v-if="!creating"
        class="home__project-view"
        @create="createProject"
      />
      
      <ProjectCreation 
        v-if="creating"
        class="home__project-view" 
        @close="stopCreation" 
      />
    </div>
  </div>
</template>

<script>
import Toolbar from '@/components/Toolbar/Toolbar.vue'
import Projects from '@/components/Project/Projects.vue'
import ProjectView from '@/components/Project/ProjectView.vue'
import ProjectCreation from '@/components/Project/ProjectCreation.vue'
import EventBus from '@/helpers/EventBus.js'

export default {
  name: 'Home',
  components: {
    Toolbar,
    Projects,
    ProjectView,
    ProjectCreation
  },
  methods: {
    deselectProject() {
      this.selectedProject = {};
    },
    createProject() {
      this.creating = true;
      this.deselectProject();
    },
    stopCreation() {
      this.creating = false;
    }
  },
  data() {
    return {
      selectedProject: {},
      creating: false
    }
  }
}
</script>

<style scoped lang="scss">
  .home {
    
    &__wrapper {
      display: flex;
      max-width: 1080px;
      width: 90%;
      margin: spacing(4) auto;
      height: calc(100vh - 70px - #{spacing(4)} * 2);
    }

    &__projects,
    &__project-view {
      height: 100%;
    }

    &__project-view {
      margin-left: spacing(4);
      flex-grow: 1;
    }
  }
</style>