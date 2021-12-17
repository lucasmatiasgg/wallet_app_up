<template>
  <q-page class="flex justify-center">
      <div class="q-pa-md" >
      <div v-if="destinations.length !== 0">
          <div class="row justify-center">
            <h4>Agenda</h4>
          </div>
          <div class="row justify-center">
              <div class="colum">
                <q-btn
                  v-on:click="addDestinationDialog = true"
                  color="primary"
                  icon="add"
                  label="Agregar nuevo destinatario"
                  class="full-width"
                  />
              </div>
          </div>
          <DestinationTable :destinations="destinations"/>

        <q-dialog v-model="addDestinationDialog" persistent>
          <AddNewDestination
            @closeNewDestination="addDestinationDialog = false"
          />
        </q-dialog>
      </div>

      <div v-else>
        <div class="row">
          <h5>No tenes cuentas agendadas</h5>
        </div>
        <div class="row ">
                <q-btn
                  @click="addDestinationDialog = true"
                  color="primary"
                  icon="add"
                  label="Agregar nuevo destinatario"
                  class="full-width"
                  />
          </div>
          <q-dialog v-model="addDestinationDialog" persistent>
          <AddNewDestination
            @closeNewDestination="addDestinationDialog = false"
          />
        </q-dialog>
      </div>
    </div>
  </q-page>
</template>

<script>
import { mapActions } from 'vuex'
import { ref } from 'vue'
import { CLEAR_NOTIFICATION, OFF_SHOW_NOTIFICATION } from 'src/store/notifications/types'
import AddNewDestination from '../components/AddNewDestination'
import { GET_DESTINATIONS } from 'src/store/transfers/types'
import DestinationTable from '../components/DestinationsTable'

export default {
  name: 'Destinations',
  setup () {
    return {
      addDestinationDialog: ref(false)
    }
  },
  data () {
    return {
      data: {
      }
    }
  },
  components: {
    AddNewDestination,
    DestinationTable
  },
  mounted: function () {
    this.getDestinations(this.$store.state.login.userIdLogged)
  },
  computed: {
    destinations: function () {
      return this.$store.state.transfers.destinationsList
    },
    notification: function () {
      return this.$store.state.notifications.notification
    },
    showNotification: function () {
      return this.$store.state.notifications.showNotification
    }
  },
  methods: {
    ...mapActions('notifications', [CLEAR_NOTIFICATION, OFF_SHOW_NOTIFICATION]),
    ...mapActions('transfers', [GET_DESTINATIONS])
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
