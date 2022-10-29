<template>
  <div>
    <PanelHeader style="margin-bottom: 2%"/>

    <h3 style="margin-bottom: 2%; padding-left: 1%"> selected şube adı - Gruplar </h3>

    <!-- todo: coordinator user grup create edebilmeli -->
    <!-- todo: coordinator user ilk grup olusturmada gruba antrenor assign edebilmeli -->
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
                Yeni Grup Ekle
              </v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="text-h5">{{ formTitle }}</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-col>
                    <v-row><span> Grup Bilgileri </span></v-row>
                    <v-row sm="6" md="4">
                      <v-text-field v-model="editedItem.groupName" label="Grup ismi" />
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
import {getDatabase, onChildAdded, onChildChanged, onChildRemoved, push, ref, set} from "firebase/database";

export default {
  components: {PanelHeader},
  data() {
    return {
      groupList: [

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
      show: false,
      dialog: false,
      editedItem: {
        groupName: ''
      },
    }
  },
  created() {
    const db = getDatabase();
    const reference = ref(db, this.$route.path);
    onChildAdded(reference, (snapshot) => {
      const data = snapshot.val();
      this.groupList.push(
          {
            key: snapshot.key,
            text: data.groupName
          }
      );
    });
    onChildChanged(reference, (snapshot) => {
      const data = snapshot.val();
      this.groupList.forEach((item) => {
        if (item.key === snapshot.key) {
          item.text = data.groupName;
        }
      });
    });

    onChildRemoved(reference, (snapshot) => {

      this.groupList.forEach((item) => {
        if (item.key === snapshot.key) {
          this.groupList.splice(this.groupList.indexOf(item), 1);
        }
      });
    });
  },
  methods: {
    edit() {
      this.isEdit = !this.isEdit;
    },
    toggle(item) {
      this.$router.push("/groups/" + item.key + "/students" )
    },
    save() {
      const db = getDatabase();
      const teamRef = ref(db, this.$route.path);
      const newTeamRef = push(teamRef);
      set(newTeamRef, {
        groupName: this.editedItem.groupName
      });
      this.close();
    },
    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.groupList)
        this.editedIndex = -1
      })
    }
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'Yeni Grup' : 'Grup Düzenle'
    },
  }
}
</script>
