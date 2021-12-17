<template>
  <div class="q-pa-md row items-start q-gutter-md">
    <q-card class="my-card bg-secondary text-white">
      <q-card-section>
        <div class="text-h6">Tarjeta de credito</div>
        <div class="text-subtitle2">***** **** **** {{ number.substring(number.length - 4, number.length) }}</div>
      </q-card-section>

      <q-card-section>
        <div class="text-subtitle3">{{ state }}</div>
        <div class="text-subtitle3">Vencimiento: {{ expirationDate }}</div>
      </q-card-section>

      <q-separator dark />

      <q-card-actions>
        <q-btn label="Eliminar" @click="deleteCardDialog = true" />
      </q-card-actions>
    </q-card>

    <q-dialog v-model="deleteCardDialog" persistent>
        <q-card>
            <q-card-section class="row items-center">
            <q-avatar icon="delete" color="primary" text-color="white" />
            <span class="q-ml-sm">Seguro que desea el eliminar la tarjeta de credito?</span>
            </q-card-section>

            <q-card-actions align="right">
            <q-btn flat label="Cancelar" color="primary" v-close-popup />
            <q-btn flat label="ELIMINAR" color="primary" v-close-popup @click="callDeleteCreditCard"/>
            </q-card-actions>
        </q-card>
    </q-dialog>
  </div>
</template>

<script>
import { DELETE_CREDIT_CARD, GET_CREDIT_CARDS } from 'src/store/creditCard/types'
import { defineComponent, ref } from 'vue'
import { mapActions } from 'vuex'

export default defineComponent({
  setup () {
    return {
      deleteCardDialog: ref(false)
    }
  },
  props: {
    number: {
      type: String,
      default: ''
    },
    expirationDate: {
      type: String,
      default: ''
    },
    state: {
      type: String,
      default: 'Vigente'
    }
  },
  methods: {
    ...mapActions('creditCard', [DELETE_CREDIT_CARD, GET_CREDIT_CARDS]),
    callDeleteCreditCard () {
      console.log('eliminando...')
      this.deleteCreditCard(this.number)
      this.deleteCardDialog = false
      this.getCreditCards(this.$store.state.login.userIdLogged)
    }
  }
})
</script>

<style lang="sass" scoped>
.my-card
  width: 120%
  max-width: 320px
</style>
