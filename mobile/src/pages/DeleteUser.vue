<template>
  <q-page class="flex justify-center">
    <div class="q-pa-md">
      <h4>Baja de usuario</h4>
      <br>
    <h5>Para poder dar de baja la cuenta debe poseer saldo $0.00</h5>
    <h5>Presionando el botón "Baja" se eliminará su usuario y cuenta. Esta acción no puede deshacerse.</h5>
    <br>
    <q-btn label="Baja" color="negative" @click="deleteUserDialog = true" class="full-width"/>
    <q-dialog v-model="deleteUserDialog" persistent>
        <q-card>
            <q-card-section class="row items-center">
              <q-avatar icon="delete" color="primary" text-color="white" />
              <span class="q-ml-sm">Seguro que desea el eliminar su usuario y cuenta?</span>
            </q-card-section>

            <q-card-actions align="right">
              <q-btn flat label="Cancelar" color="primary" v-close-popup />
              <q-btn flat label="ELIMINAR" color="primary" v-close-popup @click="callDeleteuser"/>
            </q-card-actions>
        </q-card>
    </q-dialog>

  </div>
  </q-page>
</template>

<script>
import { DELETE_USER } from '../store/user/types'
import { mapActions } from 'vuex'
import { ref } from 'vue'
import { CLEAR_NOTIFICATION, OFF_SHOW_NOTIFICATION } from 'src/store/notifications/types'

export default {
  name: 'DeleteUser',
  setup () {
    return {
      deleteUserDialog: ref(false)
    }
  },
  data () {
    const userInfo = this.$store.getters['user/getUserInfo']
    return {
      user: {
        userId: userInfo.id
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
    ...mapActions('user', [DELETE_USER]),
    callDeleteuser () {
      console.log('SE VA EL USER: ' + this.user.userId)
      this.deleteUser(this.user.userId)
    },
    ...mapActions('notifications', [CLEAR_NOTIFICATION, OFF_SHOW_NOTIFICATION])
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
