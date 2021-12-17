import { CREATE_USER, DELETE_USER, MODIFY_USER, GET_USER_INFO, SET_USER_INFO, SET_USER_ACCOUNT_INFO, CLEAN_USER, EDIT_ALIAS, SET_ALIAS } from './types'
import { api } from 'boot/axios'
import state from '../login/state'
import router from 'src/router'

export default {
  [CREATE_USER]: ({ commit }, data) => {
    console.log('createUser service')
    api.post('/user/createUser', {
      userName: data.username,
      password: data.password,
      firstName: data.firstName,
      lastName: data.lastName,
      isCommerce: data.isCommerce,
      socialReason: data.socialReason,
      address: data.address,
      cuit: data.cuit,
      dni: data.dni
    })
      .then((response) => {
        const notification = {
          message: response.data.data.message,
          code: response.data.data.statusCode
        }
        if (response.data.data.statusCode !== 0) {
          commit('notifications/onShowNotification', '', { root: true })
          commit('notifications/setNotification', notification, { root: true })
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
  [DELETE_USER]: ({ commit }, userId) => {
    console.log('Deleting user')
    const config = {
      headers: { Authorization: `Bearer ${state.exchangeToken}` }
    }

    api.delete('/user/delete/' + userId, config)
      .then((response) => {
        const notification = {
          message: response.data.data.message,
          code: response.data.data.statusCode
        }
        if (response.data.data.statusCode === 0) {
          commit('notifications/onShowNotification', '', { root: true })
          commit('notifications/setNotification', notification, { root: true })
          commit(CLEAN_USER)
          router.push('/')
        } else {
          commit('notifications/onShowNotification', '', { root: true })
          commit('notifications/setNotification', notification, { root: true })
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
  [MODIFY_USER]: ({ commit }, data) => {
    console.log('Modifying user')
    const config = {
      headers: { Authorization: `Bearer ${state.exchangeToken}` }
    }
    console.log(JSON.stringify(data))

    api.put('/user/modify/' + data.userId, {
      firstName: data.firstName,
      lastName: data.lastName,
      address: data.address,
      dni: data.dni
    },
    config).then((response) => {
      const notification = {
        message: response.data.data.message,
        code: response.data.data.statusCode
      }
      if (response.data.data.statusCode === 0) {
        commit('notifications/onShowNotification', '', { root: true })
        commit('notifications/setNotification', notification, { root: true })
      } else {
        commit('notifications/onShowNotification', '', { root: true })
        commit('notifications/setNotification', notification, { root: true })
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
  [GET_USER_INFO]: ({ commit }, userName) => {
    const config = {
      headers: { Authorization: `Bearer ${state.exchangeToken}` }
    }
    api.get('/user/getUserInfo/' + userName, config).then((response) => {
      if (response.data.data.statusCode === 0) {
        console.log('OK')
        commit(SET_USER_INFO, response.data.data.userInfo)
        commit(SET_USER_ACCOUNT_INFO, response.data.data.accountInfo)
        commit(SET_ALIAS, response.data.data.accountInfo.aliasCVU)
      } else {
        console.log('Error. Ver como manejarlo')
      }
    })
      .catch(() => {
        console.log('Error. Ver como manejarlo')
      })
  },
  [EDIT_ALIAS]: ({ commit }, data) => {
    console.log('setAlias service')
    const config = {
      headers: { Authorization: `Bearer ${state.exchangeToken}` }
    }
    api.post('/user/setAlias', {
      aliasCVU: data.alias,
      idUser: data.idUser
    }, config)
      .then((response) => {
        if (response.data.data.statusCode === 0) {
          commit(SET_ALIAS, data.alias)
        } else {
          console.log('Error. Ver como manejarlo')
        }
      })
      .catch(() => {
        console.log('Error. Ver como manejarlo')
      })
  }
}
