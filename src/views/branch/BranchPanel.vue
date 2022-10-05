<template>
  <div>
    <PanelHeader style="margin-bottom: 2%"/>

    <h3 style="margin-bottom: 2%; padding-left: 1%"> BACKENDDEN KULÜP ADI GELECEK - Şubeler </h3>

    <v-sheet class="mx-auto">
      <v-slide-group
          v-model="model"
          class="pa-4 primary"
          show-arrows
          center-active
      >
        <v-slide-item
            v-for="team in teamList"
            :key="team"
        >
          <v-card
              class="ma-4"
              color="grey lighten-3"
              height="200"
              width="300"
              elevation="8"
              @click="toggle(team)"
          >
            <br/>
            <center>
              <v-img
                  src="@/assets/anadolu-efes-logo.png"
                  height="100px"
                  max-width="75px"
              ></v-img>

              <br/>
              <span style="align-content: center"> {{ team.text }} </span>
            </center>
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </div>
</template>

<script>
import PanelHeader from "@/components/PanelHeader";
import {getDatabase, ref} from "firebase/database";

export default {
  components: {PanelHeader},
  data() {
    return {
      teamList: []
    }
  },
  mounted() {
    console.log(this.$router.history.current.params.clubid)
    this.getTeamList();
  },
  methods: {
    getTeamList() {
      const db = getDatabase();
      ref(db, '1');

      this.teamList = [];
    },
    toggle(item) {
      let id = item.id
      this.$router.push("/branches/" + {id} + "/groups")
    },
    edit() {
      this.isEdit = !this.isEdit;
    },
    save() {

    },
    cancel() {

    }
  },
}
</script>
