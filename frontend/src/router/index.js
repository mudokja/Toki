import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/room',
      name: 'room',
      component: () => import('../views/RoomView.vue')
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('../views/ProfileView.vue')
    },
    {
      path: '/success',
      name: 'success',
      component: () => import('../views/SuccessView.vue')
    },
    {
      path: '/main',
      name: 'main',
      component: () => import('../views/MainView.vue')
    },
  ]
})

router.beforeEach((to, from, next) => {
  console.log(to.query)
  const { resultCode, token } = to.query
  if (resultCode && token) {
    if (resultCode === '200') {
      localStorage.setItem('accessToken', token)
      next('/success')
    } else {
      next('/')
    }
  } else {
    next()
  }
})

export default router
