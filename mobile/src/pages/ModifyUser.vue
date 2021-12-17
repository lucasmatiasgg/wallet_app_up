<template>
  <q-page class="flex justify-center">
    <div class="q-pa-md">
      <h4>Modificar datos</h4>
      <q-form
        @submit="callModifyUser"
        class="q-gutter-md"
      >
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
    </q-form>

  </div>
  </q-page>
</template>

<script>
import { GET_USER_INFO, MODIFY_USER } from '../store/user/types'
import { mapActions } from 'vuex'
import { CLEAR_NOTIFICATION, OFF_SHOW_NOTIFICATION } from 'src/store/notifications/types'

export default {
  name: 'ModifyUser',
  data () {
    const userInfo = this.$store.getters['user/getUserInfo']
    return {
      user: {
        firstName: userInfo.firstName,
        lastName: userInfo.lastName,
        dni: userInfo.dni,
        address: userInfo.address,
        userId: userInfo.id
      }
    }
  },
  mounted: function () {
    this.getUserInfo(this.$store.state.login.userNameLogged)
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
    ...mapActions('user', [GET_USER_INFO, MODIFY_USER]),
    ...mapActions('notifications', [CLEAR_NOTIFICATION, OFF_SHOW_NOTIFICATION]),
    callModifyUser () {
      this.modifyUser(this.user)
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
