<template>
  <div class="q-pa-md">
    <q-card class="my-card">
      <q-card-section>
        <div class="text-h6">Movimientos</div>
      </q-card-section>

      <q-markup-table>
        <thead>
          <tr>
            <th class="text-left">Fecha</th>
            <th class="text-left">Movimiento</th>
            <th class="text-left">Importe $</th>
          </tr>
        </thead>
        <MovementsBodyTable
            v-for="data in transactions"
            :key="data.id"
            v-bind="data"
        />
      </q-markup-table>
    </q-card>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import MovementsBodyTable from './MovementsBodyTable.vue'
import { useQuasar } from 'quasar'

export default defineComponent({
  setup () {
    const $q = useQuasar()
    $q.screen.setSizes({ sm: 300, md: 500, lg: 1000, xl: 2000 })
  },
  components: {
    MovementsBodyTable
  },
  props: {
    transactions: {
      type: Array,
      default: null
    }
  }
})
</script>

<style lang="sass" scoped>
.my-card
  width: 120%
  max-width: 320px
</style>
