<template>
  <q-page class="flex justify-center">
      <div class="q-pa-md" >
        <div class="col">
          <h4>Transferencias</h4>
          <h5>Saldo disponible: $ {{accountInfo.accountBalance}}</h5>

          <q-form
            @submit="callProcessTransfer"
            class="q-gutter-md"
          >

            <div class="q-gutter-sm">
              <q-radio size="sm" v-model="target" val="cvuRadio" label="CVU" />
              <q-radio size="sm" v-model="target" val="aliasRadio" label="Alias" />
              <q-radio size="sm" v-model="target" val="aliasDestinations" label="Agenda" />
            </div>

            <template v-if="target === 'cvuRadio'">
              <q-input
                filled
                v-model="data.cvu"
                type="tel"
                maxlength="22"
                label="CVU"
                lazy-rules
                :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
              />
            </template>
            <template v-if="target === 'aliasRadio'">
              <q-input
                class="uppercase"
                filled
                v-model="data.aliasCVU"
                label="Alias CVU"
                lazy-rulexs
                :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
              />
            </template>

            <template v-if="target === 'aliasDestinations'">
              <q-select
                  outlined
                  v-model="data.destination"
                  emit-value
                  :options="destinations"
                  label="Destinatarios"
                  :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
                />
            </template>
            <q-input
              filled
              prefix="$"
              v-model="data.amount"
              label="Importe"
              lazy-rules
              :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
            />

            <div class="row justify-left">
                <q-btn label="Confirmar" type="submit" color="primary" class="full-width"/>
            </div>
          </q-form>
        </div>
      </div>
  </q-page>
</template>

<script>
import { PROCESS_TRANFER, GET_DESTINATIONS } from '../store/transfers/types'
import { mapActions } from 'vuex'
import { ref } from 'vue'
import { CLEAR_NOTIFICATION, OFF_SHOW_NOTIFICATION } from 'src/store/notifications/types'

export default {
  name: 'Transfers',
  setup () {
    return {
      target: ref('cvuRadio')
    }
  },
  data () {
    return {
      data: {
        cvu: '',
        aliasCVU: '',
        amount: 0,
        destination: [],
        desti: ''
      }
    }
  },
  computed: {
    accountInfo: function () {
      return this.$store.state.user.userAccount
    },
    notification: function () {
      return this.$store.state.notifications.notification
    },
    showNotification: function () {
      return this.$store.state.notifications.showNotification
    },
    destinations: function () {
      console.log('Transfer - DestinationsList: ' + JSON.stringify(this.$store.state.transfers.destinationsList))
      const destinationNames = this.$store.state.transfers.destinationsList.map((data) => data.name)
      return destinationNames
    }
  },
  methods: {
    ...mapActions('transfers', [PROCESS_TRANFER, GET_DESTINATIONS]),
    ...mapActions('notifications', [CLEAR_NOTIFICATION, OFF_SHOW_NOTIFICATION]),
    callProcessTransfer () {
      const destinationData = this.$store.state.transfers.destinationsList.find(({ name }) => name === this.data.destination)
      const data = {
        cvu: destinationData ? destinationData.cvu : this.data.cvu,
        userId: this.$store.state.login.userIdLogged,
        amount: this.data.amount,
        aliasCVU: destinationData ? destinationData.aliasCVU : this.data.aliasCVU
      }
      this.processTransfer(data)
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

<style>
    .uppercase input {
        text-transform: uppercase;
    }
</style>
