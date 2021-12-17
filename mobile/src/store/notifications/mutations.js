import { SET_NOTIFICATION, CLEAR_NOTIFICATION, ON_SHOW_NOTIFICATION, OFF_SHOW_NOTIFICATION } from './types'

export default {
  [SET_NOTIFICATION] (state, data) {
    state.notification.message = data.message
    state.notification.code = data.code
  },
  [CLEAR_NOTIFICATION] (state) {
    state.notification = {}
  },
  [ON_SHOW_NOTIFICATION] (state) {
    state.showNotification = true
  },
  [OFF_SHOW_NOTIFICATION] (state) {
    state.showNotification = false
  }
}
