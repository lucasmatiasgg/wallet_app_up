<template>
  <q-layout view="hHh LpR fFf">

    <q-header elevated class="bg-primary text-white">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />

        <q-toolbar-title>
          <q-avatar>
            <img src="../assets/wallet-icon-26.jpeg">
          </q-avatar>
          Wallet UP
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

  <q-drawer show-if-above v-model="leftDrawerOpen" side="left" behavior="overlay" elevated  >
      <q-scroll-area class="fit">
          <q-list>
            <template :key="index" v-for="(menuItem, index) in menuList">
              <q-item clickable :active="menuItem.label === 'Outbox'" v-ripple>
                <q-item-section avatar>
                  <q-icon :name="menuItem.icon" color="primary"/>
                </q-item-section>
                <q-item-section v-on:click="handleClick(menuItem.key)">
                  {{ menuItem.label }}
                </q-item-section>
              </q-item>
              <q-separator :key="'sep' + index"  v-if="menuItem.separator" />
            </template>

          </q-list>
        </q-scroll-area>
    </q-drawer>
    <q-page-container>
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script>
import { DO_LOGOUT } from 'src/store/login/types'
import { defineComponent, ref } from 'vue'
import { mapActions } from 'vuex'

const menuList = [
  {
    icon: 'account_balance_wallet',
    key: 'desktop',
    label: 'Cuenta',
    separator: true
  },
  {
    icon: 'send_to_mobile',
    key: 'transfers',
    label: 'Transferencias',
    separator: true
  },
  {
    icon: 'savings',
    key: 'cashIn',
    label: 'Ingresar dinero',
    separator: true
  },
  {
    icon: 'view_agenda',
    key: 'destinations',
    label: 'Agenda destinatarios',
    separator: true
  },
  {
    icon: 'credit_card',
    key: 'crediCard',
    label: 'Tarjetas',
    separator: true
  },
  {
    icon: 'account_circle',
    key: 'user',
    label: 'Modificar datos de usuario',
    separator: true
  },
  {
    icon: 'clear',
    key: 'delete',
    label: 'Baja de usuario',
    separator: true
  },
  {
    icon: 'logout',
    key: 'logout',
    label: 'Logout',
    separator: true
  }
]

export default defineComponent({
  name: 'MainLayout',
  data () {
    return {
      left: false,
      menuList
    }
  },
  setup () {
    const leftDrawerOpen = ref(false)

    return {
      menuListToShow: menuList,
      leftDrawerOpen,
      toggleLeftDrawer () {
        leftDrawerOpen.value = !leftDrawerOpen.value
      }
    }
  },
  methods: {
    ...mapActions('login', [DO_LOGOUT]),
    handleShoppingClick: function (event) {
      this.$router.push('/cart')
    },
    handleLogout: function (event) {
      this.$router.push('/')
    },
    handleClick: function (key) {
      console.log(key)
      switch (key) {
        case 'desktop':
          this.$router.push('/desktop')
          break
        case 'user':
          this.$router.push('/desktop/modifyUser')
          break
        case 'password':
          this.$router.push('/desktop/modifyPassword')
          break
        case 'delete':
          this.$router.push('/desktop/deleteUser')
          break
        case 'transfers':
          this.$router.push('/desktop/transfers')
          break
        case 'crediCard':
          this.$router.push('/desktop/creditCard')
          break
        case 'cashIn':
          this.$router.push('/desktop/cashIn')
          break
        case 'destinations':
          this.$router.push('/desktop/destinations')
          break
        case 'logout':
          this.doLogOut()
          break
        default:
          this.$router.push('/desktop')
      }
    }
  }
})
</script>
