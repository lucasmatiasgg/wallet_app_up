import { ADD_CREDIT_CARD, PUSH_CREDIT_CARD, REMOVE_CARD } from './types'

export default {
  [ADD_CREDIT_CARD] (state, creditCards) {
    state.creditCardList = creditCards
  },
  [PUSH_CREDIT_CARD] (state, card) {
    state.creditCardList.push(card)
  },
  [REMOVE_CARD] (state, number) {
    const index = state.creditCardList.findIndex(val => val.number === number)
    if (index > -1) {
      state.creditCardList.splice(index, 1)
    }
  }
}
