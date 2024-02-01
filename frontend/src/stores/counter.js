import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'
import axios from 'axios'


export const useCounterStore = defineStore('counter', () => {
  const count = ref(0)
  const doubleCount = computed(() => count.value * 2)
  function increment() {
    count.value++
  }

  const token = ref(null)

  const setToken = (newToken) => {
    token.value = newToken
    localStorage.setItem('accessToken', newToken)
  }

  const clearToken = () => {
    token.value = null
  }

  return { count, doubleCount, increment, token, setToken, clearToken }
}, { persist: true })
