<template>
  <q-layout view="lHr lpR lFr">

    <q-page-container>
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script>

import { defineComponent } from 'vue'

export default defineComponent({
  name: 'LoginLayout',

  components: {

  },

  setup () {
  }
})
</script>
