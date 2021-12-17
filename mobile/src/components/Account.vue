<template>
  <div class="q-pa-md">
    <q-card class="my-card">
      <q-card-section >
        <div class="text-h5">Cuenta</div>
        <div class="text-h6">Tu saldo: $ {{ accountBalance }}</div>
      </q-card-section>

      <q-tabs v-model="tab" class="text-primary">
        <q-tab label="Alias" name="one" />
        <q-tab label="CVU" name="two" />
      </q-tabs>

      <q-separator />

      <q-tab-panels v-model="tab" animated>
        <q-tab-panel name="one">
          <template v-if="this.alias !== ''">
          {{ alias }}
          <q-btn flat round color="primary" icon="content_copy" @click="copyAlias"/>
          </template>
          <template v-else>
            Aún no definiste tu alias
          </template>
          <q-btn flat round color="primary" icon="edit" @click="editAliasDialog = true"/>
        </q-tab-panel>

        <q-tab-panel name="two">
          {{ cvu }}
          <q-btn flat round color="primary" icon="content_copy" @click="copyCVU"/>
        </q-tab-panel>
      </q-tab-panels>
    </q-card>

    <q-dialog v-model="editAliasDialog" persistent>
          <EditAlias
            :currentAlias="alias"
            @closeEditAlias="editAliasDialog = false"
    />
    </q-dialog>
  </div>
</template>

<script>
import { defineComponent, ref } from 'vue'
import { copyToClipboard } from 'quasar'
import EditAlias from '../components/EditAlias.vue'

export default defineComponent({
  name: 'Account',
  setup () {
    return {
      tab: ref('one'),
      editAliasDialog: ref(false)
    }
  },
  computed: {
    alias: function () {
      return this.$store.state.user.alias
    }
  },
  components: {
    EditAlias
  },
  props: {
    accountBalance: {
      type: Number,
      default: 0
    },
    cvu: {
      type: String,
      default: '0'
    }
  },
  mounted: function () {
    console.log('ACCOUNT MOUNTED: ' + this.cvu)
    console.log('ACCOUNT MOUNTED: ' + this.accountBalance)
  },
  methods: {
    copyAlias () {
      copyToClipboard(this.alias)
        .then(() => {
          this.$q.notify({
            color: 'positive',
            position: 'top',
            message: 'Copiado!',
            icon: 'check_circle'
          })
        })
        .catch(() => {
        })
    },
    copyCVU () {
      copyToClipboard(this.cvu)
        .then(() => {
          this.$q.notify({
            color: 'positive',
            position: 'top',
            message: 'Copiado!',
            icon: 'check_circle'
          })
        })
        .catch(() => {
        })
    }
  }
})
</script>

<style lang="sass" scoped>
.my-card
  width: 120%
  max-width: 320px
</style>
