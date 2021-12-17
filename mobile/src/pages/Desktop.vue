<template>
  <q-page class="flex">
      <div class="q-pa-md">
          <div class="col-12 col-md">
            <h4>Hola {{user.firstName}} !</h4>
          </div>
            <div class="col-12 col-md">
              <!-- <div class="flex col-6"> -->
                <Account :accountBalance="accountInfo.accountBalance" :cvu="accountInfo.cvu"/>
              <!-- </div> -->
            </div>
          <div class="col-12 col-md">
            <MovementsTable :transactions ="transactions" />
          </div>

      </div>
  </q-page>
</template>

<script>
import { GET_USER_INFO } from '../store/user/types'
import { mapActions } from 'vuex'
import Account from '../components/Account.vue'
import MovementsTable from '../components/MovementsTable.vue'
import { GET_TRANSACTIONS } from 'src/store/transactions/types'

export default {
  name: 'Desktop',
  data () {
    const userInfo = this.$store.state.user.userInfo
    console.log('DESKTOP USER INFO')
    console.log(JSON.stringify(userInfo))
    return {
    }
  },
  components: {
    Account,
    MovementsTable
  },
  mounted: function () {
    this.getUserInfo(this.$store.state.login.userNameLogged)
    this.getTransactions(this.$store.state.login.userIdLogged)
  },
  computed: {
    user: function () {
      return this.$store.getters['user/getUserInfo']
    },
    accountInfo: function () {
      return this.$store.state.user.userAccount
    },
    transactions: function () {
      console.log('TRANSACTIONS:' + this.$store.state.transactions.transactionsList)
      return this.$store.state.transactions.transactionsList
    }
  },
  methods: {
    ...mapActions('user', [GET_USER_INFO]),
    ...mapActions('transactions', [GET_TRANSACTIONS])
  }
}
</script>
