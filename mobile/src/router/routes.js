
const routes = [
  {
    path: '/',
    component: () => import('layouts/LoginLayout.vue'),
    children: [
      { path: '', component: () => import('src/pages/StartPage.vue') },
      { path: '/register', component: () => import('src/pages/Register.vue') }
    ]
  },
  {
    path: '/desktop',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('src/pages/Desktop.vue') },
      { path: '/desktop/modifyUser', component: () => import('src/pages/ModifyUser.vue') },
      { path: '/desktop/modifyPassword', component: () => import('src/pages/ModifyPassword.vue') },
      { path: '/desktop/deleteUser', component: () => import('src/pages/DeleteUser.vue') },
      { path: '/desktop/transfers', component: () => import('src/pages/Transfers.vue') },
      { path: '/desktop/creditCard', component: () => import('src/pages/CreditCard.vue') },
      { path: '/desktop/cashIn', component: () => import('src/pages/CashIn.vue') },
      { path: '/desktop/destinations', component: () => import('src/pages/Destinations.vue') }
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
