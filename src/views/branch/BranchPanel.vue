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

      <v-card class="primary" style="margin-top: 3%">
        <v-card-actions>
          <h4 style="padding-left: 1%"> Şube Envanter Listesi </h4>
          <v-btn icon @click="show = !show">
            <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
          </v-btn>
        </v-card-actions>
        <v-expand-transition>
          <div v-show="show">
            <v-card-text>
              <v-list nav dense>
                <v-list-item
                    v-for="item in items"
                    :key="item.id"
                >
                  <v-list-item-content v-if="!isEdit">
                    <v-list-item-title class="text--secondary">{{ item.text }}</v-list-item-title>
                  </v-list-item-content>

                  <input v-if="isEdit" type="text" v-model="item.text" /> <!-- todo input span width verilecek -->
                </v-list-item>
              </v-list>
            </v-card-text>
          </div>
          <div>
            <!-- todo sağa yaslı iki buton olacak:
             todo save (basınca isEdit: false, backende gidecek update yapacak, liste render edilecek)
             todo cancel (basınca isEdit: false, liste eski haline getirilecek) -->
          </div>
        </v-expand-transition>
      </v-card>
    </v-sheet>
  </div>
</template>

<script>
import PanelHeader from "@/components/PanelHeader";

export default {
  components: {PanelHeader},
  data() {
    return {
      teamList: [
        {
          id: 1,
          text: "Anadolu Efes Türkkonut Şubesi"
        },
        {
          id: 2,
          text: "Anadolu Efes Eryaman Şubesi"
        },
        {
          id: 3,
          text: "Anadolu Efes İncek Şubesi"
        },
        {
          id: 4,
          text: "Eryaman 4"
        },
        {
          id: 5,
          text: "Eryaman 5"
        }
      ],
      show: false,
      items: [
        {
          id: 1,
          text: "basketbol topu: 13 adet"
        },
        {
          id: 2,
          text: "forma: 12 adet"
        },
        {
          id: 3,
          text: "liste adetlerle string "
        },
        {
          id: 4,
          text: "basketbol topu: 13 adet"
        },
        {
          id: 5,
          text: "forma: 12 adet"
        }
      ],
      editedItems: [],
      isEdit: true
    }
  },
  methods: {
    toggle(item) {
      let id = item.id
      this.$router.push("/branches/" +{ id }+ "/groups" )
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
