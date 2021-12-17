<template>
  <q-page class="flex justify-center">
    <div class="q-pa-md">
      <h4>Modificar contraseña</h4>
      <q-form
        @submit="modifyUser"
        class="q-gutter-md"
      >
        <q-input
          filled
          v-model="user.firstName"
          label="Contraseña actual"
          lazy-rules
          :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
        />

        <q-input
          filled
          v-model="user.firstName"
          label="Nueva contraseña"
          lazy-rules
          :rules="[ val => val && val.length > 0 || 'Este campo es requerido']"
        />

        <q-input
          filled
          v-model="user.lastName"
          label="Repetir contraseña"
          lazy-rulexs
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
import { MODIFY_USER } from '../store/user/types'
import { mapActions } from 'vuex'

export default {
  name: 'ModifyPassword',
  data () {
    return {
      user: {
        firstName: null,
        lastName: null,
        dni: null,
        address: null
      }
    }
  },
  methods: {
    ...mapActions('user', [MODIFY_USER]),
    modifyUser (event) {
      this.modifyUser(this.user)
    }
  }
}
</script>
