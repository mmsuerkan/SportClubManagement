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

export default {
  name: "Deneme",
  data() {
    return {
      message: "Hello from Deneme Page",
      image: null,
      imageurl: null,
      allImages: [],
    };
  },
  methods: {
    onFileChange(e) {
      const file = e.target.files[0];
      console.log(file);
    },
    uploadFile() {

      const storage = getStorage(app);

      // eslint-disable-next-line no-unused-vars
      uploadBytes(ref(storage, 'car/'), this.image).then((snapshot) => {
        console.log('Uploaded a blob or file!');
      });
    },

    showPicture() {
      const storage = getStorage(app);
      const listRef = ref(storage, 'car/');
      listAll(listRef).then((res) => {
        res.items.forEach((itemRef) => {
          // All the items under listRef.
          console.log(itemRef._location.path);
          this.imageurl = 'https://'+itemRef.storage._host +'/v0/b/'+itemRef.storage._bucket.bucket+'/o/car%2F'+itemRef.name+'?alt=media&token=a58a67e7-e125-4e9d-8747-53b5262c769e'
          this.allImages.push(this.imageurl);
        });
      });
    }
  },
  mounted() {
    this.showPicture();
  }
}
</script>

<style scoped>

</style>
