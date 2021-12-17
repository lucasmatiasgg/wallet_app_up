<template>
  <q-page class="flex">
    <div class="q-pa-md" >
      <div v-if="creditCards.length !== 0">
          <h4>Tarjetas de credito</h4>
          <div class="row">
              <div class="colum">
                <q-btn
                  v-on:click="addCreditCardDialog = true"
                  color="primary"
                  icon="add"
                  label="Agregar Tarjeta de Credito"
                  class="full-width"
                  />
              </div>
          </div>
          <WidgetCreditCard
              v-for="data in creditCards"
              :key="data.number"
              v-bind="data"/>
        <q-dialog v-model="addCreditCardDialog" persistent>
          <AddCreditCard
            @closeCreditCard="addCreditCardDialog = false"
          />
        </q-dialog>
      </div>

      <div v-else>
        <div class="row">
          <h5>No tenes tarjetas de credito asociadas</h5>
        </div>
        <div class="row ">
                <q-btn
                  @click="addCreditCardDialog = true"
                  color="primary"
                  icon="add"
                  label="Agregar Tarjeta de Credito"
                  class="full-width"
                  />
          </div>
          <q-dialog v-model="addCreditCardDialog" persistent>
          <AddCreditCard
            @closeCreditCard="addCreditCardDialog = false"
          />
        </q-dialog>
      </div>
    </div>
  </q-page>
</template>

<script>
import WidgetCreditCard from '../components/WidgetCreditCard.vue'
import AddCreditCard from '../components/AddCreditCard.vue'
import { mapActions } from 'vuex'
import { GET_CREDIT_CARDS } from 'src/store/creditCard/types'
import { ref } from 'vue'
import { CLEAR_NOTIFICATION, OFF_SHOW_NOTIFICATION } from 'src/store/notifications/types'

export default {
  name: 'CreditCard',
  setup () {
    return {
      addCreditCardDialog: ref(false)
    }
  },
  components: {
    WidgetCreditCard,
    AddCreditCard
  },
  data: () => ({
    minCardYear: new Date().getFullYear(),
    cardNumberMaxLength: 19,
    addCreditCard: false
  }),
  computed: {
    creditCards: function () {
      return this.$store.state.creditCard.creditCardList
    },
    notification: function () {
      return this.$store.state.notifications.notification
    },
    showNotification: function () {
      return this.$store.state.notifications.showNotification
    }
  },
  mounted: function () {
    this.getCreditCards(this.$store.state.login.userIdLogged)
  },
  methods: {
    ...mapActions('creditCard', [GET_CREDIT_CARDS]),
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
