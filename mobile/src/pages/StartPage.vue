<template>
  <q-page class="flex flex-center">
    <div class="q-pa-md">
      <div class="row justify-center">
        <h4>Wallet UP</h4>
      </div>
      <q-form
        @submit="callLogin"
        class="q-gutter-md"
      >
        <q-input
          filled
          v-model="data.username"
          label="Nombre de usuario"
          lazy-rules
          :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
        />

        <q-input
          filled
          v-model="data.password"
          label="Contraseña"
          lazy-ruless
          type="password"
          :rules="[
            val => val !== null && val !== '' || 'Este campo es requerido',
          ]"
        />

        <div class="row justify-center">
            <q-btn label="Ingresar" type="submit" color="primary" class="full-width"/>
          </div>
          <div class="row justify-center">
            <q-btn v-on:click="goToRegister" label="Soy nuevo" color="primary"  class="full-width"/>
          </div>
      </q-form>

  </div>
  </q-page>
</template>

<script>
import { DO_LOGIN } from '../store/login/types'
import { mapActions } from 'vuex'
import { CLEAR_NOTIFICATION, OFF_SHOW_NOTIFICATION } from 'src/store/notifications/types'

export default {
  name: 'StartPage',
  data () {
    return {
      data: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    ...mapActions('login', [DO_LOGIN]),
    ...mapActions('notifications', [CLEAR_NOTIFICATION, OFF_SHOW_NOTIFICATION]),
    goToRegister () {
      this.$router.push('/register')
    },
    callLogin () {
      this.doLogin(this.data)
    }
  },
  computed: {
    notification: function () {
      return this.$store.state.notifications.notification
    },
    showNotification: function () {
      return this.$store.state.notifications.showNotification
    }
  },
  watch: {
    showNotification (showNotification) {
      if (showNotification) {
        if (this.notification.code === 0) {
          this.$q.notify({
            message: this.notification.message,
            color: 'positive'
          })
          this.clearNotification()
          this.offShowNotification()
        } else {
          this.$q.notify({
            message: this.notification.message,
            color: 'negative'
          })
          this.clearNotification()
          this.offShowNotification()
        }
      }
    }
  }
}
</script>
