import { CLEAR_NOTIFICATION, OFF_SHOW_NOTIFICATION } from './types'

export default {
  [CLEAR_NOTIFICATION]: ({ commit }) => {
    commit(CLEAR_NOTIFICATION, {})
  },
  [OFF_SHOW_NOTIFICATION]: ({ commit }) => {
    commit(OFF_SHOW_NOTIFICATION, false)
  }
}
