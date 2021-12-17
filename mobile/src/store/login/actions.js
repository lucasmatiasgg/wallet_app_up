import { CLEAN_LOGIN, DO_LOGIN, DO_LOGOUT, SET_EXCHANGE_TOKEN, SET_USER_NAME, SET_USER_ID } from './types'
import { api } from 'boot/axios'
import router from '../../router'

export default {
  [DO_LOGIN]: ({ commit }, data) => {
    console.log('request login')
    api.post('/user/validateCredentials', {
      userName: data.username,
      password: data.password
    })
      .then((response) => {
        const notification = {
          message: response.data.data.message,
          code: response.data.data.statusCode
        }
        if (response.data.data.statusCode === 0) {
          commit(SET_EXCHANGE_TOKEN, response.data.data.exchangeToken)
          commit(SET_USER_NAME, data.username)
          commit(SET_USER_ID, response.data.data.userId)

          router.push('/desktop')
        } else {
          commit('notifications/onShowNotification', '', { root: true })
          commit('notifications/setNotification', notification, { root: true })
          router.push('/')
        }
      })
      .catch((error) => {
        console.log(error, 'Error Catch.')
        const notification = {
          message: 'Ocurrió un error procesando la operación',
          code: 99
        }
        commit('notifications/onShowNotification', '', { root: true })
        commit('notifications/setNotification', notification, { root: true })
      })
  },
  [DO_LOGOUT]: ({ commit }) => {
    console.log('Logout - Limpiando store')
    commit(CLEAN_LOGIN)
    router.push('/')
  }
}
