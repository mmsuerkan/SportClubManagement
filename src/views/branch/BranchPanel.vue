<template>
  <div>
    <PanelHeader style="margin-bottom: 2%"/>

    <h3 style="padding-left: 1%"> {{ clubName }} - Şubeler </h3>

    <div>
      <template>
        <v-toolbar flat>
          <v-spacer></v-spacer>
          <v-dialog v-model="dialog" max-width="520px">
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                  color="primary"
                  dark
                  class="mb-2"
                  v-bind="attrs"
                  v-on="on"
              >
                Yeni Şube Ekle
              </v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="text-h5">{{ formTitle }}</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-col>
                    <v-row><span> Şube Bilgileri </span></v-row>
                    <v-row sm="6" md="4">
                      <v-text-field v-model="editedItem.branchName" label="Şube ismi" />
                    </v-row>
                  </v-col>
                </v-container>
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn class="accent" text @click="save">
                  Kaydet
                </v-btn>
                <v-btn class="secondary" text @click="close">
                  Vazgeç
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-toolbar>
      </template>
    </div>

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
// eslint-disable-next-line no-unused-vars
import {getDatabase, ref, push, set, onChildAdded, onChildChanged,onChildRemoved} from "firebase/database";

export default {
  components: {PanelHeader},
  data() {
    return {
      teamList: [],
      dialog: false,
      editedItem: {
        branchName: ''
      },
      clubName:""
    }
  },
  created() {
    const db = getDatabase();
    const reference = ref(db, this.$route.path);// todo:trainer panelde değişecek
    onChildAdded(reference, (snapshot) => {
      const data = snapshot.val();
      this.teamList.push(
          {
            key: snapshot.key,
            text: data.branchName
          }
      );
    });
    onChildChanged(reference, (snapshot) => {
      const data = snapshot.val();
      this.teamList.forEach((item) => {
        if (item.key === snapshot.key) {
          item.text = data.branchName;
        }
      });
    });

    onChildRemoved(reference, (snapshot) => {

      this.teamList.forEach((item) => {
        if (item.key === snapshot.key) {
          this.teamList.splice(this.teamList.indexOf(item), 1);
        }
      });
    });
  //todo:club name getirilecek
    // onChildAdded(ref(db, 'clubs/' + this.$router.history.current.params.clubid), (snapshot) => {
    //   const data = snapshot.val();
    //   console.log("clubName",data);
    //   this.clubName = data.clubName;
    // });
  },
  methods: {
    toggle(item) {
      this.$router.push("/branches/"+item.key +"/groups");
    },
    edit() {
      this.isEdit = !this.isEdit;
    },
    save() {
      const db = getDatabase();
      const teamRef = ref(db, this.$route.path);
      const newTeamRef = push(teamRef);
      set(newTeamRef, {
        branchName: this.editedItem.branchName
      });
      this.close();
    },
    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.teamList)
        this.editedIndex = -1
      })
    }
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'Yeni Şube' : 'Şube Düzenle'
    },
  }
}
</script>
