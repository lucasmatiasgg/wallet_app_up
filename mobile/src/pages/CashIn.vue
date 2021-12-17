<template>
  <q-page class="flex justify-center">
      <div class="q-pa-md" >
        <div class="col">
          <div class="row justify-center">
            <h4>Ingresar dinero</h4>
          </div>
          <div v-if="creditCards.length !== 0" class="q-pa-md items-start q-gutter-md">
            <q-form
              @submit="callCashIn"
              class="q-gutter-md"
            >

              <q-select
                outlined
                v-model="data.card"
                :options="creditCards"
                label="Tarjeta de crédito"
                :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
              />

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
          <div v-else>
            <div class="row">
              <h5>Para ingresar dinero tenés que registrar una tarjeta de crédito</h5>
            </div>
            <div class="row">
                <q-btn
                  @click="goToCreditCard"
                  color="primary"
                  label="Ir a tarjetas de crédito"
                  class="full-width"
                  />
            </div>
          </div>
        </div>
      </div>
  </q-page>
</template>

<script>
import { PROCESS_CASH_IN } from '../store/transfers/types'
import { GET_CREDIT_CARDS } from '../store/creditCard/types'
import { mapActions } from 'vuex'
import { ref } from 'vue'
import { CLEAR_NOTIFICATION, OFF_SHOW_NOTIFICATION } from 'src/store/notifications/types'

export default {
  name: 'CashIn',
  setup () {
    return {
      target: ref('cvuRadio'),
      model: ref(null)
    }
  },
  data () {
    return {
      data: {
        amount: 0,
        card: []
      }
    }
  },
  mounted: function () {
    this.getCreditCards(this.$store.state.login.userIdLogged)
  },
  computed: {
    accountInfo: function () {
      return this.$store.state.user.userAccount
    },
    creditCards: function () {
      console.log('CASH-IN - CreditCardList: ' + JSON.stringify(this.$store.state.creditCard.creditCardList))
      const creditCardNumbers = this.$store.state.creditCard.creditCardList.map((data) => data.number)
      console.log('CASH-IN - Numeros: ' + creditCardNumbers)
      return creditCardNumbers
    },
    notification: function () {
      return this.$store.state.notifications.notification
    },
    showNotification: function () {
      return this.$store.state.notifications.showNotification
    }
  },
  methods: {
    ...mapActions('transfers', [PROCESS_CASH_IN]),
    ...mapActions('creditCard', [GET_CREDIT_CARDS]),
    ...mapActions('notifications', [CLEAR_NOTIFICATION, OFF_SHOW_NOTIFICATION]),
    callCashIn () {
      const cardData = this.$store.state.creditCard.creditCardList.find(({ number }) => number === this.data.card)
      const data = {
        card: cardData,
        userId: this.$store.state.login.userIdLogged,
        amount: this.data.amount
      }
      console.log('CALL CASH IN: ' + JSON.stringify(data))
      this.processCashIn(data)
    },
    goToCreditCard () {
      this.$router.push('/desktop/creditCard')
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
