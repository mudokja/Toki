import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { jwtDecode } from 'jwt-decode'



export const useAuthStore = defineStore('auth', () => {
    const accessToken = ref(null)
    const user = ref(null)

    const isAuthenticated = computed(() => !accessToken.value)

    function setToken(newToken) {
        const decoded = jwtDecode(newToken)
        user.value = decoded
        accessToken.value = decoded.accessToken
        localStorage.setItem('accessToken', newToken)

        try {
            user.value = jwtDecode(newToken)
        } catch (error) {
            console.error('Token decoding failed:', error)
        }
    }


    function clearToken() {
        accessToken.value = null
        user.value = null
        localStorage.removeItem('accessToken')
    }

  return { accessToken, user, isAuthenticated, setToken, clearToken, }
}, { persist: true })
