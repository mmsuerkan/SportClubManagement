<template>
  <div>
    <ClubHeader style="margin-bottom: 2%"/>
    <div style="margin-bottom: 2%">
      <center>
        <v-img
            src="@/assets/anadolu-efes-logo.png"
            max-height="100px"
            max-width="75px"
        ></v-img>
      </center>
    </div>
    <v-container class="primary rounded" >
      <v-layout wrap>
        <v-flex xs10 md>
          <v-row height="200px">
            <v-col>
              <h1 class="font-italic"> Hoşgeldin {{ user }}! </h1>
              <span class="text--secondary font-weight-medium"> Ankara - {{ getDate }} </span>
            </v-col>
          </v-row>

          <v-row style="margin-left: 40%">
            <h2 class="font-weight-light"> Bugün hangi işlemi yapmak istersin? </h2>
          </v-row>

          <v-row height="200px">
            <v-col style="margin-left: 10%; padding-left: 7%">
              <v-card class="ma-2 secondary" shaped width="340px">
                <v-card-title class="justify-center">
                  <router-link :to=branchPath style="text-decoration:none; color: white">
                    <span> Kulüp şubelerini incele </span>
                  </router-link>
                </v-card-title>
              </v-card>
            </v-col>

            <v-col>
              <v-card class="ma-2 secondary" shaped width="340px">
                <v-card-title class="justify-center">
                  <router-link :to=trainerPath style="text-decoration:none; color: white">
                    <span> Kulüp antrenörlerini incele </span>
                  </router-link>
                </v-card-title>
              </v-card>
            </v-col>
          </v-row>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import moment from "moment";
import ClubHeader from "@/views/club/ClubHeader";
import { mapGetters } from "vuex";

export default {
  components: {
    ClubHeader
  },
  data() {
    return {
      user: ""
    }
  },
  mounted() {
    this.user = this.userName;
  },
  methods: {},
  computed: {
    getDate() {
      return moment().seconds(0).milliseconds(0);
    },
    branchPath() {
      return '/club/' + 1 + '/branches'
    },
    trainerPath() {
      return '/club/' + 1 + '/trainers'
    },
    ...mapGetters(["userName", "clubId"])
  }
}
</script>