<template>
  <div class="home">
    <h1>Login</h1>
    <p>
      <input type="text" v-model="username" placeholder="Username" />
    </p>
    <p>
      <input type="password" v-model="password" placeholder="Password" />
    </p>
    <p>
      <button @click="login">Login</button>
    </p>

  </div>
</template>

<script>
// @ is an alias to /src
import { initializeApp } from 'firebase/app';
import { getAuth,signInWithEmailAndPassword } from 'firebase/auth';

const firebaseConfig = {
  apiKey: "AIzaSyAXl00PzhSpPFfMzhDNxcPvSEMNFKBRYhs",
  authDomain: "sportclubmanagement-b9725.firebaseapp.com",
  projectId: "sportclubmanagement-b9725",
  storageBucket: "sportclubmanagement-b9725.appspot.com",
  messagingSenderId: "191724501308",
  appId: "1:191724501308:web:336cade08b6bcc39a60639",
  measurementId: "G-SLF67F6NDR"
};

const firebaseApp = initializeApp(firebaseConfig);

export const auth = getAuth(firebaseApp);

export default {
  name: 'Home',
  components: {

  },
  data() {
    return {
      username: 'sportcmapp@gmail.com',
      password: 'S102019m',
    }
  },
  methods: {
    login() {
      console.log("username",this.username);
      console.log("password",this.password);
      signInWithEmailAndPassword(auth, this.username, this.password)
        .then((userCredential) => {
          // Signed in
          // eslint-disable-next-line no-unused-vars
          const user = userCredential.user;
          alert("Login Success");
          this.$router.push({name: 'Club Page'});
          // ...
        })
        .catch((error) => {
          // eslint-disable-next-line no-unused-vars
          const errorCode = error.code;
          // eslint-disable-next-line no-unused-vars
          const errorMessage = error.message;
          alert(errorCode + " " + errorMessage);
        });
    }
  }
}
</script>
