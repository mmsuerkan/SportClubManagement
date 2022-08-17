<template>
  <v-app>
    <v-main id="main" :style="{background: $vuetify.theme.themes[theme].background}">
      <router-link to="/">Home </router-link> |
      <router-link to="/list">Player List </router-link>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'App',
  computed:{
    theme(){
      return (this.$vuetify.theme.dark) ? 'dark' : 'light'
    }
  }
};
</script>