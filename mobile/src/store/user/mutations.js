import { CLEAN_USER, SET_USER_ACCOUNT_INFO, SET_USER_INFO, SET_ALIAS } from './types'

export default {
  [SET_USER_INFO] (state, user) {
    state.user = user
  },
  [SET_USER_ACCOUNT_INFO] (state, account) {
    state.userAccount = account
  },
  [CLEAN_USER] (state) {
    state.user = {}
    state.userAccount = {}
  },
  [SET_ALIAS] (state, alias) {
    state.alias = alias
  }
}
