import { GET_TRANSACTIONS, SET_TRANSACTIONS } from './types'
import { api } from 'boot/axios'
import state from '../login/state'

export default {
  [GET_TRANSACTIONS]: ({ commit }, userId) => {
    console.log('GET CC')
    const config = {
      headers: { Authorization: `Bearer ${state.exchangeToken}` }
    }

    api.get('/transactions/getAllTransactions/' + userId, config)
      .then((response) => {
        if (response.data.data.statusCode !== 0) {
          console.log('Error. Ver como manejarlo')
        } else {
          commit(SET_TRANSACTIONS, response.data.data.transactions)
        }
      })
      .catch(() => {
        console.log('Error. Ver como manejarlo')
      })
  }
}
