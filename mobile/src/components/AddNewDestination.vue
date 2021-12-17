<template>
  <q-card>
     <q-card-section class="row q-mx-md">
      <div class="text-h6">Agregar nuevo destinatario</div>
    </q-card-section>
    <form v-on:submit="submitForm">
      <q-card-section class="q-mx-md q-gutter-md">
        <q-input
            filled
            class="col"
            ref="name"
            maxlength="30"
            label="Nombre"
            v-model="newAccount.name"
            @update:modelValue="event => $emit('update:name', event)"
            :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
        />
        <q-input
            filled
            class="col"
            ref="cvu"
            type="tel"
            maxlength="22"
            label="CVU"
            v-model="newAccount.cvu"
            :modelValue="newAccount.cvu"
            @update:modelValue="event => $emit('update:cvu', event)"
            :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
        />
       <q-input
            filled
            class="col"
            ref="alias"
            maxlength="30"
            label="Alias CVU"
            v-model="newAccount.alias"
            @update:modelValue="event => $emit('update:alias', event)"
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
          @click="callAddNewDestination"
        />
      </q-card-actions>
    </form>
  </q-card>
</template>

<script>
import { mapActions } from 'vuex'
import { GET_DESTINATIONS, ADD_DESTINATION } from 'src/store/transfers/types'

export default {
  name: 'AddNewDestination',
  data () {
    return {
      newAccount: {
        name: '',
        cvu: '',
        alias: ''
      }
    }
  },
  methods: {
    ...mapActions('transfers', [ADD_DESTINATION, GET_DESTINATIONS]),
    submitForm: function () {
      console.log(this.newAccount.cvu)
    },
    callAddNewDestination () {
      console.log('DESTINATION DATA:' + JSON.stringify(this.newAccount))
      const data = {
        idUser: this.$store.state.login.userIdLogged,
        newAccount: {
          name: this.newAccount.name,
          cvu: this.newAccount.cvu,
          alias: this.newAccount.alias
        }
      }
      this.addDestination(data)
      console.log(JSON.stringify(data))
      this.$emit('closeNewDestination')
    }
  }
}
</script>
