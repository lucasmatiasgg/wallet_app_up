<template>
  <q-page class="flex flex-center">
    <div class="q-pa-md">
      <div class="row justify-center">
        <h4>Registrate</h4>
      </div>
    <div>
      <q-form
        @submit="doRegister"
        class="q-gutter-md"
      >
        <q-input
          filled
          v-model="user.username"
          label="Nombre de usuario"
          lazy-rules
          :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
        />

        <q-input
          filled
          v-model="user.password"
          label="Contraseña"
          lazy-rules
          type="password"
          :rules="[
            val => val !== null && val !== '' || 'Este campo es requerido',
          ]"
        />

        <q-input
          filled
          v-model="user.passwordConfirm"
          label="Confirmar contraseña"
          type="password"
          lazy-rules
          :rules="[
            val => val !== null && val !== '' || 'Este campo es requerido',
            val => val === user.password || 'Las contraseñas no son iguales',
          ]"
        />

        <q-input
          filled
          v-model="user.firstName"
          label="Nombre"
          lazy-rules
          :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
        />

        <q-input
          filled
          v-model="user.lastName"
          label="Apellido"
          lazy-rulexs
          :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
        />

        <q-input
          filled
          v-model="user.dni"
          label="D.N.I"
          lazy-rules
          :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
        />

        <q-input
          filled
          v-model="user.address"
          label="Direccion"
          lazy-rules
          :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
        />

        <div class="row justify-center">
            <q-btn label="Confirmar" type="submit" color="primary" class="full-width"/>
          </div>
          <div class="row justify-center">
            <q-btn v-on:click="goBack" label="Volver" color="primary" class="full-width"/>
          </div>
      </q-form>
    </div>
  </div>
  </q-page>
</template>

<script>
import { CREATE_USER } from '../store/user/types'
import { mapActions } from 'vuex'
import { CLEAR_NOTIFICATION, OFF_SHOW_NOTIFICATION } from 'src/store/notifications/types'

export default {
  name: 'Register',
  data () {
    return {
      user: {
        username: null,
        password: null,
        firstName: null,
        lastName: null,
        dni: null,
        address: null
      }
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
  methods: {
    ...mapActions('user', [CREATE_USER]),
    ...mapActions('notifications', [CLEAR_NOTIFICATION, OFF_SHOW_NOTIFICATION]),
    goBack () {
      this.$router.push('/')
    },
    doRegister () {
      this.createUser(this.user)
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
