<template>
  <div>

    <!-- todo: sayfanın adı groupDetails olsa, selected student bilgisi studentPanel olsa daha mantıklı -->

    <PanelHeader style="margin-bottom: 2%"/>

    <h3 style="margin-bottom: 1%; padding-left: 1%"> selected group name </h3>

    <h4 style="margin-bottom: 1%; padding-left: 1%"> Grup Antrenörü </h4>

    <!-- todo: coordinator user'ın assign ettiği antrenör bilgisi, coordinator user tarafından bu sayfadan degistirilebilir -->

    <v-sheet class="mx-auto">
      <v-container class="primary" fluid>
        <input class="text--white" v-model="trainer" />
      </v-container>
    </v-sheet>

    <h4 style="margin-top: 2%; margin-bottom: 1%; padding-left: 1%"> Öğrenci Listesi </h4>

    <v-sheet class="mx-auto">
      <v-container class="primary" fluid>
        <v-data-table
            :headers="studentPanelHeaders"
            :items="studentList"
            class="elevation-1"
        >
          <template v-slot:top>
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
                    Yeni Öğrenci Ekle
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="text-h5">{{ formTitle }}</span>
                  </v-card-title>

                  <v-card-text>
                    <v-container>
                      <v-col>
                        <v-row><span> Öğrenci Bilgileri </span></v-row>
                        <v-row sm="6" md="4">
                          <v-text-field v-model="editedItem.tckn" label="TC Kimlik No"/>
                        </v-row>
                        <v-row sm="6" md="4">
                          <v-text-field v-model="editedItem.birthDate" label="Doğum Tarihi"/>
                        </v-row>
                        <v-row sm="4" md="2">
                          <v-text-field v-model="editedItem.nameSurname" label="Adı Soyadı"/>
                        </v-row>
                        <v-row sm="6" md="4">
                          <v-text-field v-model="editedItem.gender" label="Cinsiyeti"/>
                        </v-row>
                        <v-row>
                          <v-col></v-col>
                          <br/></v-row>
                        <v-row><span> Veli Bilgileri </span></v-row>
                        <v-row sm="6" md="4">
                          <v-text-field v-model="editedItem.parentNameSurname" label="Anne Adı Soyadı"/>
                        </v-row>
                        <v-row sm="6" md="4">
                          <v-text-field v-model="editedItem.phoneNumber" label="Telefonu"/>
                        </v-row>
                        <v-row sm="6" md="4">
                          <v-text-field v-model="editedItem.email" label="E-Posta Adresi"/>
                        </v-row>
                        <v-row sm="6" md="4">
                          <v-text-field v-model="editedItem.parentNameSurname2" label="Baba Adı Soyadı"/>
                        </v-row>
                        <v-row sm="6" md="4">
                          <v-text-field v-model="editedItem.phoneNumber2" label="Telefonu"/>
                        </v-row>
                        <v-row sm="6" md="4">
                          <v-text-field v-model="editedItem.email2" label="E-Posta Adresi"/>
                        </v-row>
                        <v-row sm="6" md="4">
                          <v-text-field v-model="editedItem.address" label="Veli Adresi"/>
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

          <template v-slot:item.actions="{ item }">
            <v-icon small class="mr-2" @click="editItem(item)">
              mdi-pencil
            </v-icon>
            <v-icon small class="mr-2" @click="deleteItem(item)">
              mdi-delete
            </v-icon>
          </template>

        </v-data-table>
      </v-container>
    </v-sheet>

    <v-dialog v-model="dialogDelete" max-width="520px">
      <v-card>
        <v-card-title class="text-h5">Seçili öğrenciyi silmek istediğinizden emin misiniz?</v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="accent" text @click="deleteItemConfirm">
            Sil
          </v-btn>
          <v-btn class="secondary" text @click="closeDelete">
            Vazgeç
          </v-btn>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import PanelHeader from "@/components/PanelHeader";

import {getDatabase, ref, child,set,remove,onChildAdded,onChildRemoved,onChildChanged} from "firebase/database";
export default {
  components: {PanelHeader},
  data() {
    return {
      studentPanelHeaders: [
        {text: 'Adı Soyadı', value: 'nameSurname', align: 'center', width: '15%'},
        {text: 'Doğum Tarihi', value: 'birthDate', align: 'center', sortable: false},
        {text: 'Cinsiyeti', value: 'gender', align: 'center', sortable: false},
        {text: 'TC Kimlik Numarası', value: 'tckn', align: 'center', sortable: false},
        {text: 'Veli Bilgileri', value: 'parentNameSurname', align: 'center', sortable: false},
        {text: 'Veli İletişim No', value: 'phoneNumber', align: 'center', sortable: false},
        {text: 'İşlem', value: 'actions', align: 'center', sortable: false}
      ],
      studentList: [],
      trainer:
          'pelinsu serimer, todo: beyaz card uzerinde olsun, en sağda trainer page routing button'
      ,
      editedIndex: -1,
      editedItem: {
        nameSurname: '',
        birthDate: null,
        gender: '',
        tckn: '',
        parentNameSurname: '',
        phoneNumber: null,
        email: '',
        parentNameSurname2: '',
        phoneNumber2: null,
        email2: '',
        address: ''
      },
      defaultItem: {
        nameSurname: '',
        birthDate: null,
        gender: '',
        tckn: '',
        parentNameSurname: '',
        phoneNumber: null,
        parentNameSurname2: '',
        phoneNumber2: null,
        email2: '',
        address: ''
      },
      dialog: false,
      dialogDelete: false
    }
  },
  methods: {
    editItem(item) {
      this.editedIndex = this.studentList.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },
    deleteItem(item) {
      this.editedIndex = this.studentList.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },
    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },
    save() {
      const db = getDatabase();
      const reference = ref(db, '/students/efes/turkkonut/10-11');

      if (this.editedIndex > -1) {
        Object.assign(this.studentList[this.editedIndex], this.editedItem)
        set(child(reference, this.users[this.editedIndex].tckn), this.editedItem);
      } else {
        set(child(reference, this.editedItem.tckn), this.editedItem);
        this.studentList.push(this.editedItem)
      }
      this.close()
    },
    deleteItemConfirm() {
      this.studentList.splice(this.editedIndex, 1);
      const db = getDatabase();
      const reference = ref(db, '/students/efes/turkkonut/10-11');
      //this.users.splice(this.editedIndex, 1);
      remove(child(reference, this.editedItem.tckn));
      this.closeDelete()
    },
    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      })
    }
  },
  created() {
    const db = getDatabase();
    const reference = ref(db, 'students/efes/turkkonut/10-11');
    onChildAdded(reference, (snapshot) => {
      const data = snapshot.val();
      this.users.push(data);
    });
    onChildRemoved(reference, (snapshot) => {
      const data = snapshot.val();
      this.users.splice(this.users.indexOf(data), 1);
    });
    onChildChanged(reference, (snapshot) => {
      const data = snapshot.val();
      this.users.splice(this.users.indexOf(data), 1);
      this.users.push(data);
    });
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'Anka Spor Basketbol Okulları Kayıt Formu' : 'Öğrenci Bilgilerini Düzenle'
    },
  }
}
// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyAXl00PzhSpPFfMzhDNxcPvSEMNFKBRYhs",
  authDomain: "sportclubmanagement-b9725.firebaseapp.com",
  projectId: "sportclubmanagement-b9725",
  storageBucket: "sportclubmanagement-b9725.appspot.com",
  messagingSenderId: "191724501308",
  appId: "1:191724501308:web:336cade08b6bcc39a60639",
  measurementId: "G-SLF67F6NDR"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
// eslint-disable-next-line no-unused-vars
const analytics = getAnalytics(app);
</script>