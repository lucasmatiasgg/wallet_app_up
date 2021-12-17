<template>
  <q-card>
     <q-card-section class="row q-mx-md">
      <div class="text-h6">Editar alias</div>
    </q-card-section>
    <form v-on:submit="submitForm">
      <q-card-section class="q-mx-md q-gutter-md">
        <q-input
            class="uppercase"
            filled
            ref="alias"
            type="text"
            maxlength="20"
            v-model="alias"
            :modelValue="alias"
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
          @click="callEditAlias"
        />
      </q-card-actions>
    </form>
  </q-card>
</template>

<script>
import { EDIT_ALIAS } from 'src/store/user/types'
import { mapActions } from 'vuex'

export default {
  name: 'EditAlias',
  components: {
  },
  data () {
    return {
      alias: this.currentAlias
    }
  },
  props: {
    currentAlias: {
      type: String,
      default: ''
    }
  },
  methods: {
    ...mapActions('user', [EDIT_ALIAS]),
    submitForm () {
    },
    callEditAlias () {
      const data = {
        alias: this.alias,
        idUser: this.$store.state.login.userIdLogged
      }
      this.editAlias(data)
      this.$emit('closeEditAlias')
    }
  }
}
</script>

<style>
    .uppercase input {
        text-transform: uppercase;
    }
</style>
