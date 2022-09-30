<template>
  <div>
    <div style="display: flex;flex-direction: column;align-items: center;justify-content: center;height: 100%;">
      <h1>Text Area</h1>
   <v-textarea></v-textarea>
  </div>
  </div>
</template>

<script>

const axios = require('axios');
export default {
  name: "CloudFunctionsPage",
  data() {
    return {
      message: null
    };
  },
  created() {
    this.alertUserAdded()
  },
  methods: {
    async alertUserAdded(){
      await axios.get('http://localhost:5001/sportclubmanagement-b9725/us-central1/userAdded').then(
        response => {
          this.message = response.data;
          alert(this.message);
        }
      )
    }
  }
}
</script>

<style scoped>

</style>
