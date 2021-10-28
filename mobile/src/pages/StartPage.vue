<template>
  <q-page class="flex flex-center">
    <!-- <img
      alt="Quasar logo"
      src="~assets/quasar-logo-vertical.svg"
      style="width: 200px; height: 200px"
    > -->
    <div class="q-pa-md" style="max-width: 400px">

    <q-form
      @submit="doLogin"
      class="q-gutter-md"
    >
      <q-input
        filled
        v-model="username"
        label="Nombre de usuario"
        lazy-rules
        :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
      />

      <q-input
        filled
        v-model="password"
        label="Contraseña"
        lazy-rules
        :rules="[
          val => val !== null && val !== '' || 'Este campo es requerido',
        ]"
      />

      <div class="row justify-center">
          <q-btn label="Ingresar" type="submit" color="secondary"/>
        </div>
        <div class="row justify-center">
          <q-btn v-on:click="goToRegister" label="Soy nuevo" color="secondary" />
        </div>
    </q-form>

  </div>
  </q-page>
</template>

<script>
import { DO_LOGIN } from '../store/login/types'
import { mapActions } from 'vuex'

export default {
  name: 'StartPage',
  data () {
    return {
      username: null,
      password: null,
      accept: false
    }
  },
  methods: {
    ...mapActions([DO_LOGIN]),
    goToRegister () {
      this.$router.push('/register')
    },
    doLogin () {
      this.$store.dispatch(DO_LOGIN,
        {
          username: this.username,
          password: this.password
        })
    }
  }
}
</script>
