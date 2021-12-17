<template>
  <q-card>
     <q-card-section class="row q-mx-md">
      <div class="text-h6">Agregar Tarjeta de credito</div>
    </q-card-section>
    <form v-on:submit="submitForm">
      <q-card-section class="q-mx-md q-gutter-md">
        <q-input
            filled
            class="col"
            ref="number"
            type="tel"
            maxlength="16"
            label="Numero"
            v-model="card.number"
            :modelValue="card.number"
            @update:modelValue="event => $emit('update:number', event)"
            :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
        />
        <q-input
            filled
            class="col"
            ref="name"
            maxlength="30"
            label="Nombre"
            v-model="card.name"
            @update:modelValue="event => $emit('update:name', event)"
            :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
        />
        <div>Vencimiento</div>
        <div class="row">
          <div class="col">
            <q-select
              options-cover
              filled
              transition-show="scale"
              transition-hide="scale"
              v-model="card.month"
              v-bind:options="monthList"
              label="Mes"
              :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
            />
          </div>
          <div class="col">
            <q-select
              options-cover
              filled
              transition-show="scale"
              transition-hide="scale"
              v-model="card.year"
              v-bind:options="yearList"
              label="Año"
              :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
            />
          </div>
        </div>
        <q-input
            filled
            class="col"
            ref="cvv"
            type="tel"
            maxlength="4"
            label="CVV"
            v-model="card.cvv"
            :modelValue="card.cvv"
            :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
        />
      </q-card-section>
      <q-card-actions align="right" class="  q-mb-sm q-px-md text-primary">
        <q-btn
          icon="cancel"
          flat
          color="negative"
          label="Cancelar"
          v-close-popup
        />
        <q-btn
          icon="check"
          flat
          color="primary"
          label="Aceptar"
          @click="callAddCreditCard"
        />
      </q-card-actions>
    </form>
  </q-card>
</template>

<script>
import { ADD_CREDIT_CARD, GET_CREDIT_CARDS } from 'src/store/creditCard/types'
import { mapActions } from 'vuex'

export default {
  name: 'AddCreditCard',
  data () {
    return {
      card: {
        number: '',
        name: '',
        month: '',
        year: '',
        cvv: ''
      },
      monthList: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
      yearList: ['2021', '2022', '2023', '2024', '2025', '2026', '2027', '2028', '2029', '2030', '2031', '2032']
    }
  },
  methods: {
    ...mapActions('creditCard', [ADD_CREDIT_CARD, GET_CREDIT_CARDS]),
    submitForm: function () {
      console.log(this.card.number)
    },
    callAddCreditCard () {
      console.log('CARD DATA:' + JSON.stringify(this.card))
      const data = {
        idUser: this.$store.state.login.userIdLogged,
        card: {
          number: this.card.number,
          name: this.card.name,
          expirationDate: this.card.month + '/' + this.card.year,
          brand: this.card.brand,
          verificationCode: this.card.cvv
        }
      }
      this.addCreditCard(data)
      this.$emit('closeCreditCard')
    }
  }
}
</script>
