
const routes = [
  {
    path: '/',
    component: () => import('layouts/LoginLayout.vue'),
    children: [
      { path: '', component: () => import('src/pages/StartPage.vue') }
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
