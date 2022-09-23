import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        dark: false,
        themes: {
            light: {
                primary: '#e46428',
                secondary: '#464545',
                accent: '#fe8306',
                background: '#FFFFFF'
            },
            dark: {
                primary: '#e46428',
                secondary: '#FFFFFF',
                accent: '#fe8306',
                background: '#464545'
            }
        }
    }
});