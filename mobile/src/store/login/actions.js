import { DO_LOGIN, SET_EXCHANGE_TOKEN } from './types'
import { api } from 'boot/axios'

export default {
  [DO_LOGIN]: ({ commit }, data) => {
    console.log('request login')
    debugger
    api.post('/user/validateCredentials', {
      userName: data.username,
      password: data.password
    })
      .then((response) => {
        if (!response.data.status.success) {
          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: response.data.status.message,
            icon: 'report_problem'
          })
        } else {
          commit(SET_EXCHANGE_TOKEN, response.data.exchangeToken)
          // this.$store.commit(SET_CART_ID, response.data.cartId)
          this.$router.push('/desktop')
          // data = response.data
        }
      })
      .catch(() => {
        this.$q.notify({
          color: 'negative',
          position: 'top',
          message: 'Login failed',
          icon: 'report_problem'
        })
      })
  }
}
