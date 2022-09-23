<template>
  <nav>
    <v-toolbar app class="primary" dense>
      <v-app-bar-nav-icon @click="drawer = !drawer" />
      <v-spacer></v-spacer>

      <v-toolbar-title>
        Sports Management
      </v-toolbar-title>
      <v-spacer></v-spacer>

      <v-toolbar-items style="margin-top: 20px;">
        {{ date }}
      </v-toolbar-items>
    </v-toolbar>

    <v-navigation-drawer app width="200px" v-model="drawer" left>
      <template>
        <v-list-item two-line>
          <v-list-item-avatar>
            <img src="https://randomuser.me/api/portraits/women/81.jpg">
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>username</v-list-item-title>
            <v-list-item-subtitle>coach/director</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </template>
      <v-divider></v-divider>
      <v-list nav dense class="primary" style="height: 640px">
        <v-list-item
            v-for="item in items"
            :key="item.text"
            link
            @click="$router.push(item.path)"
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title class="text--secondary">{{ item.text }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <template v-slot:append>
        <div>
          <v-btn block @click="logout">
            Çıkış yap
          </v-btn>
        </div>
      </template>
    </v-navigation-drawer>
  </nav>
</template>

<script>
import moment from "moment";

export default {
  mounted() {
    this.date = moment().format("DD/MM/YYYY");
  },
  data() {
    return {
      date: "",
      drawer: false,
      items: [
        {
          id: 1,
          text: "Kulüp",
          path: "/club",
          icon: "mdi-home"
        },
        {
          id: 2,
          text: "Koordinatörler",
          path: "/club/1/coordinators",
          icon: "mdi-account-group"
        },
        {
          id: 3,
          text: "Şubeler",
          path: "/club/1/branches",
          icon: "mdi-source-branch"
        }
      ]
    }
  },
  methods: {
    logout() {
      this.$router.push("/")
    }
  }
}
</script>