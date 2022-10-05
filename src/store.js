import Vue from 'vue';
import Vuex from 'vuex';
import VuexPersistence from 'vuex-persist';

Vue.use(Vuex);

const vuexLocal = new VuexPersistence({
    storage: window.localStorage
})

const store = new Vuex.Store({
    state: {
        userName: null,
        clubId: null
    },
    getters: {
        userName(state) {
            return state.userName;
        },
        clubId(state) {
            return state.clubId
        }
    },
    mutations: {
        setUserName(state, data) {
            state.userName = data;
        },
        setClubId(state, data) {
            state.clubId = data;
        }
    },
    plugins: [vuexLocal.plugin]
});

export default store