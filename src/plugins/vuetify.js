import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        dark: false,
        themes: {
            light: {
                primary: '#E47041',
                secondary: '#161616',
                accent: '#B54213',
                background: '#FFF8F0'
            },
            dark: {
                primary: '#E47041',
                secondary: '#FFFFFF',
                accent: '#B54213',
                background: '#161616'
            }
        }
    }
});