<template>
  <v-app-bar class="primary" dense >
    <img class="mr-3" :src="require('@/assets/logo.png')" height="40" style="border-radius: 8px"/>
    <v-toolbar-title>
      Sports Management Project
    </v-toolbar-title>

    <v-spacer></v-spacer>
    <v-btn @click="logout">
      Çıkış yap
    </v-btn>
  </v-app-bar>
</template>

<script>
export default {
  methods: {
    logout() {
      this.$router.push("/")
    }
  }
}
</script>