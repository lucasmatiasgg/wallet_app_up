<template>
  <div class="q-pa-md">
    <q-table
      :rows="destinations"
      :columns="columns"
      row-key="name"
      selection="multiple"
      v-model:selected="selected"
      :filter="filter"
      grid
      hide-header
    >
      <template v-slot:top-right>
        <q-input borderless dense debounce="300" v-model="filter" placeholder="Search">
          <template v-slot:append>
            <q-icon name="search" />
          </template>
        </q-input>
      </template>

      <template v-slot:item="props">
        <div
          class="q-pa-xs col-xs-12 col-sm-6 col-md-4 col-lg-3 grid-style-transition"
          :style="props.selected ? 'transform: scale(0.95);' : ''"
        >
          <q-card :class="props.selected ? 'bg-grey-2' : ''">
            <q-card-section>
              <q-checkbox dense v-model="props.selected" :label="props.row.name" />
            </q-card-section>
            <q-separator />
            <q-list dense>
              <q-item v-for="col in props.cols.filter(col => col.name !== 'desc')" :key="col.name">
                <q-item-section>
                  <q-item-label>{{ col.label }}</q-item-label>
                </q-item-section>
                <q-item-section side>
                  <q-item-label caption>{{ col.value }}</q-item-label>
                </q-item-section>
              </q-item>
            </q-list>
            <div v-if="props.selected">
              <q-btn flat label="ELIMINAR" color="negative" @click="deleteDestinationDialog = true"/>
            </div>
          </q-card>
          <q-dialog v-model="deleteDestinationDialog" persistent>
            <q-card>
                <q-card-section class="row items-center">
                <q-avatar icon="delete" color="primary" text-color="white" />
                <span class="q-ml-sm">Seguro que desea el eliminar el destinatario</span>
                </q-card-section>

                <q-card-actions align="right">
                <q-btn flat label="Cancelar" color="primary" v-close-popup />
                <q-btn flat label="ELIMINAR" color="primary" v-close-popup @click="callDeleteDestination"/>
                </q-card-actions>
            </q-card>
        </q-dialog>
        </div>
      </template>

    </q-table>
  </div>
</template>

<script>
import { defineComponent, ref } from 'vue'
import { mapActions } from 'vuex'
import { DELETE_DESTINATION, GET_DESTINATIONS } from 'src/store/transfers/types'

const columns = [
  { name: 'name', align: 'center', label: 'Name', field: 'name', sortable: true },
  { name: 'cvu', label: 'CVU', field: 'cvu', sortable: true },
  { name: 'aliaCVU', label: 'Alias', field: 'aliasCVU' }
]

export default defineComponent({
  setup () {
    return {
      filter: ref(''),
      selected: ref([]),
      columns,
      deleteDestinationDialog: ref(false)
      // rows
    }
  },
  props: {
    destinations: {
      type: Array,
      default: null
    }
  },
  methods: {
    ...mapActions('transfers', [GET_DESTINATIONS, DELETE_DESTINATION]),
    callDeleteDestination () {
      console.log('eliminando...')
      this.deleteDestination(this.selected[0].cvu)
      this.selected = ref([])
      this.deleteCardDialog = false
      this.getDestinations(this.$store.state.login.userIdLogged)
    }
  }
})
</script>

<style lang="sass">
.grid-style-transition
  transition: transform .28s, background-color .28s
</style>
