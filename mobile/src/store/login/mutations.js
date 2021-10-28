import { SET_EXCHANGE_TOKEN } from './types'

export default {
  [SET_EXCHANGE_TOKEN] (state, data) {
    state.exchangeToken.push(data)
  }
}
