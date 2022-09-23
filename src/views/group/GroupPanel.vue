<template>
  <div>
    <PanelHeader style="margin-bottom: 2%"/>

    <h3 style="margin-bottom: 2%; padding-left: 1%"> selected şube adı - Gruplar </h3>

    <v-sheet class="mx-auto">
      <v-slide-group
          v-model="model"
          class="pa-4 primary"
          show-arrows
          center-active
      >
        <v-slide-item
            v-for="group in groupList"
            :key="group"
        >
          <v-card
              class="ma-4"
              color="grey lighten-3"
              height="200"
              width="300"
              elevation="8"
              @click="toggle"
          >
            <br/>
            <center>
              <v-img
                  src="@/assets/anadolu-efes-logo.png"
                  height="100px"
                  max-width="75px"
              ></v-img>

              <br/>
              <span style="align-content: center"> {{ group.text }} </span>
            </center>
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </div>
</template>

<script>
import PanelHeader from "@/components/PanelHeader";

export default {
  components: {PanelHeader},
  data() {
    return {
      groupList: [
        {
          id: 1,
          text: "Türkkonut - 10-11 Yaş Grubu"
        },
        {
          id: 2,
          text: "Türkkonut - 11-12 Yaş Grubu"
        },
        {
          id: 3,
          text: "Türkkonut - 12-13 Yaş Grubu"
        },
        {
          id: 4,
          text: "Türkkonut - 13-14 Yaş Grubu"
        },
        {
          id: 5,
          text: "Türkkonut - 14-15 Yaş Grubu"
        }
      ]
    }
  },
  methods: {
    toggle(item) {
      let id = item.id
      this.$router.push("/groups/" +{ id }+ "/students" )
    }
  },
  mounted() {

  }
}
</script>