<template>
  <div>
  <div style="alignment: center;">
    <v-file-input
        accept="image/*"
        label="File input"
        v-model="image"
    ></v-file-input>
  <v-btn @click="uploadFile">Upload</v-btn>
  </div>
    <v-item-group>
      <v-container>
        <v-row>
          <v-col
              v-for="(imageUrl,index) in allImages"
              :key="index"
              cols="12"
              md="4"
          >
            <v-item v-slot="{ active, toggle }">
              <v-card
                  class="d-flex align-center"
                  height="200"
                  @click="toggle"
              >
                <v-img
                    :src="imageUrl"
                    :lazy-src="imageUrl"
                    aspect-ratio="1"
                    max-height="200"
                ></v-img>

              </v-card>
            </v-item>
          </v-col>
        </v-row>
      </v-container>
    </v-item-group>

  </div>
</template>

<script>
import {initializeApp} from "firebase/app";
import {getAuth} from "firebase/auth";
import {getStorage,ref,uploadBytes,listAll} from "firebase/storage";

// TODO: Replace the following with your app's Firebase project configuration
// See: https://firebase.google.com/docs/web/learn-more#config-object
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


// Initialize Cloud Storage and get a reference to the service
// eslint-disable-next-line no-unused-vars
import  swal from 'sweetalert';
const storage = getStorage(app);
const auth = getAuth(app);
export default {
  name: "Deneme",
  data() {
    return {
      message: "Hello from Deneme Page",
      image: null,
      imageurl: null,
      allImages: [],
      imageCount : 0
    };
  },
  methods: {
    onFileChange(e) {
      const file = e.target.files[0];
      console.log(file);
    },
    uploadFile() {

      const id = localStorage.getItem("uid");
      const listRef = ref(storage, `${id}/${this.imageCount++}`);
      // eslint-disable-next-line no-unused-vars
      uploadBytes(listRef, this.image).then((snapshot) => {
        swal("Success", "File uploaded successfully", "success");
        console.log('Uploaded a blob or file!');
      });
    },

    showPicture() {

      const id = localStorage.getItem("uid");
      const listRef = ref(storage, `${id}`);
      listAll(listRef).then((res) => {
        res.items.forEach((itemRef) => {
          // All the items under listRef.
          this.imageCount++;
          console.log(itemRef._location.path);
          this.imageurl = `https://${itemRef.storage._host}/v0/b/${itemRef.storage._bucket.bucket}/o/${id}%2F${itemRef.name}?alt=media&token=301bf17b-9a3e-4565-9d13-c817426291a1`;
          console.log(this.imageurl);
          this.allImages.push(this.imageurl);
        });
      });
    }
  },
  mounted() {
    this.showPicture();
    localStorage.setItem("uid",auth.currentUser.uid);
  }
}
</script>

<style scoped>

</style>
