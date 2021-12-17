import { SET_TRANSACTIONS } from './types'

export default {
  [SET_TRANSACTIONS] (state, transactions) {
    state.transactionsList = transactions
  }
}
