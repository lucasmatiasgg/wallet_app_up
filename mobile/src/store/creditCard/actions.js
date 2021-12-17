import { ADD_CREDIT_CARD, GET_CREDIT_CARDS, DELETE_CREDIT_CARD, PUSH_CREDIT_CARD, REMOVE_CARD } from './types'
import { api } from 'boot/axios'
import state from '../login/state'

export default {
  [ADD_CREDIT_CARD]: ({ commit }, data) => {
    console.log('add CC')
    console.log(JSON.stringify(data.card))

    const config = {
      headers: { Authorization: `Bearer ${state.exchangeToken}` }
    }
    api.post('/creditCard/add', {
      idUser: data.idUser,
      number: data.card.number,
      name: data.card.name,
      expirationDate: data.card.expirationDate,
      brand: data.card.brand,
      verificationCode: data.card.verificationCode
    }, config)
      .then((response) => {
        const notification = {
          message: response.data.data.message,
          code: response.data.data.statusCode
        }
        if (response.data.data.statusCode === 0) {
          commit('notifications/onShowNotification', '', { root: true })
          commit('notifications/setNotification', notification, { root: true })
          commit(PUSH_CREDIT_CARD, data.card)
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
  [GET_CREDIT_CARDS]: ({ commit }, userId) => {
    console.log('GET CC')
    const config = {
      headers: { Authorization: `Bearer ${state.exchangeToken}` }
    }
    api.get('/creditCard/getAll/' + userId, config)
      .then((response) => {
        if (response.data.data.statusCode === 0) {
          commit(ADD_CREDIT_CARD, response.data.data.creditCards)
        } else {
          console.log('Error. Ver como manejarlo')
        }
      })
      .catch((error) => {
        console.log(error, 'Error Catch. Ver como manejarlo')
      })
  },
  [DELETE_CREDIT_CARD]: ({ commit }, cardNumber) => {
    console.log('DELETE CC')
    const config = {
      headers: { Authorization: `Bearer ${state.exchangeToken}` }
    }
    api.delete('/creditCard/delete/' + cardNumber, config)
      .then((response) => {
        const notification = {
          message: response.data.data.message,
          code: response.data.data.statusCode
        }
        if (response.data.data.statusCode === 0) {
          commit('notifications/onShowNotification', '', { root: true })
          commit('notifications/setNotification', notification, { root: true })
          commit(REMOVE_CARD, cardNumber)
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
