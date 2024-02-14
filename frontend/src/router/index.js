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
      path: '/detail/:detail',
      name: 'detail',
      component: () => import('../views/DetailView.vue')
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/test',
      name: 'test',
      component: () => import('../views/TestView.vue')
    },
    {
      path: '/rooms',
      name: 'rooms',
      component: () => import('../views/DetailView.vue')
    },
    {
      path: '/rooms/:roomId',
      name: 'roomjoin',
      component: () => import('../views/RoomView.vue')
    },
    {
      path: '/rooms/:roomId/:roomMeetingId',
      name: 'roomMeeting',
      component: () => import('../views/RoomMeetingView.vue')
    },
    {
      path: '/rooms/search',
      name: 'search',
      component: () => import('../views/SearchHandlerView.vue')
    },
    {
      path: '/banners',
      name: 'banners',
      component: () => import('../views/BannerView.vue')
    },
    {
      path: '/test',
      name: 'test',
      component: () => import('../views/TestView.vue')
    }
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
