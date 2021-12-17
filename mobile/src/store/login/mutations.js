import { SET_EXCHANGE_TOKEN, SET_USER_NAME, CLEAN_LOGIN, SET_USER_ID } from './types'

export default {
  [SET_EXCHANGE_TOKEN] (state, token) {
    state.exchangeToken = token
  },
  [SET_USER_NAME] (state, userName) {
    state.userNameLogged = userName
  },
  [SET_USER_ID] (state, userId) {
    state.userIdLogged = userId
  },
  [CLEAN_LOGIN] (state) {
    state.exchangeToken = ''
    state.userNameLogged = ''
  }
}
