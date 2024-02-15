import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import { jwtDecode } from 'jwt-decode'
import { useAuthStore } from '@/stores/auth'

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
      path: '/profile/:tag',
      name: 'profile',
      component: () => import('../views/ProfileView.vue')
    },
    {
      path: '/success',
      name: 'success',
      component: () => import('../views/SuccessView.vue')
    },
    {
      path: '/detail/:detail',
      name: 'detail',
      component: () => import('../views/DetailView.vue')
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue')
    },
  ]
})

router.beforeEach((to, from, next) => {
  const authStore = useAuthStore()
  const { resultCode, token } = to.query

  if (resultCode && token) {
    if (resultCode === '200') {
      authStore.setToken(token)

      next('/')
      // router.go(0)
    } else {
      next('/success')
    }
  } else {
    next()
  }
})

export default router
