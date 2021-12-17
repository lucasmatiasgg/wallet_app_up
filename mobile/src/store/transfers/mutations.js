import { ADD_DESTINATION, PUSH_DESTINATION, REMOVE_DESTINATION } from './types'

export default {
  [ADD_DESTINATION] (state, recipients) {
    state.destinationsList = recipients
  },
  [PUSH_DESTINATION] (state, recipient) {
    state.destinationsList.push(recipient)
  },
  [REMOVE_DESTINATION] (state, cvu) {
    const index = state.destinationsList.findIndex(val => val.cvu === cvu)
    if (index > -1) {
      state.destinationsList.splice(index, 1)
    }
  }
}
