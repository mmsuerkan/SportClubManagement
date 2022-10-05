<template>
  <div>
    <PanelHeader style="margin-bottom: 2%"/>

    <h3 style="margin-bottom: 2%; padding-left: 1%"> selected şube adı - Gruplar </h3>

    <!-- todo: coordinator user grup create edebilmeli -->
    <!-- todo: coordinator user ilk grup olusturmada gruba antrenor assign edebilmeli -->

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
      ],
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
      isEdit: true,
      show: false
    }
  },
  methods: {
    edit() {
      this.isEdit = !this.isEdit;
    },
    toggle(item) {
      let id = item.id
      this.$router.push("/groups/" +{ id }+ "/students" )
    }
  },
  mounted() {

  }
}
</script>