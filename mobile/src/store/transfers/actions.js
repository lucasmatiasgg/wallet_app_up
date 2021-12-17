import { PROCESS_TRANFER, PROCESS_CASH_IN, ADD_DESTINATION, GET_DESTINATIONS, DELETE_DESTINATION, PUSH_DESTINATION, REMOVE_DESTINATION } from './types'
import { api } from 'boot/axios'
import state from '../login/state'

export default {
  [PROCESS_TRANFER]: ({ commit }, data) => {
    console.log('Transfer CVU')

    const config = {
      headers: { Authorization: `Bearer ${state.exchangeToken}` }
    }
    api.post('/payments/processTransferCVU', {
      amount: data.amount,
      originUserId: data.userId,
      cvu: data.cvu,
      aliasCVU: data.aliasCVU
    }, config)
      .then((response) => {
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
  [PROCESS_CASH_IN]: ({ commit }, data) => {
    console.log('Cash IN')
    console.log(JSON.stringify(data))

    const config = {
      headers: { Authorization: `Bearer ${state.exchangeToken}` }
    }
    api.post('/payments/processCashIn', {
      amount: data.amount,
      originUserId: data.userId,
      verificationCode: data.card.verificationCode,
      expiry: data.card.expirationDate,
      name: data.card.name,
      number: data.card.number,
      brand: 'xxx'
    }, config)
      .then((response) => {
        const notification = {
          message: response.data.data.message,
          code: response.data.data.statusCode
        }
        if (response.data.data.statusCode === 0) {
          commit('notifications/onShowNotification', '', { root: true })
          commit('notifications/setNotification', notification, { root: true })
        } else {
          console.log('Error invocando servicio.')
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
  [ADD_DESTINATION]: ({ commit }, data) => {
    console.log('add destination')
    console.log(JSON.stringify(data.newAccount))

    const config = {
      headers: { Authorization: `Bearer ${state.exchangeToken}` }
    }
    api.post('/recipients/add', {
      idUser: data.idUser,
      name: data.newAccount.name,
      cvu: data.newAccount.cvu,
      aliasCVU: data.newAccount.alias
    }, config)
      .then((response) => {
        const notification = {
          message: response.data.data.message,
          code: response.data.data.statusCode
        }
        if (response.data.data.statusCode === 0) {
          commit('notifications/onShowNotification', '', { root: true })
          commit('notifications/setNotification', notification, { root: true })
          commit(PUSH_DESTINATION, data.newAccount)
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
  [GET_DESTINATIONS]: ({ commit }, userId) => {
    console.log('GET DESTINATION')
    const config = {
      headers: { Authorization: `Bearer ${state.exchangeToken}` }
    }
    api.get('/recipients/getAll/' + userId, config)
      .then((response) => {
        if (response.data.data.statusCode === 0) {
          commit(ADD_DESTINATION, response.data.data.recipients)
        } else {
          console.log('Error')
        }
      })
      .catch((error) => {
        console.log(error, 'Error Catch')
      })
  },
  [DELETE_DESTINATION]: ({ commit }, cvu) => {
    console.log('DELETE DESTINATION')
    const config = {
      headers: { Authorization: `Bearer ${state.exchangeToken}` }
    }
    api.delete('/recipients/delete/' + cvu, config)
      .then((response) => {
        const notification = {
          message: response.data.data.message,
          code: response.data.data.statusCode
        }
        if (response.data.data.statusCode === 0) {
          commit('notifications/onShowNotification', '', { root: true })
          commit('notifications/setNotification', notification, { root: true })
          commit(REMOVE_DESTINATION, cvu)
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
  }
}
