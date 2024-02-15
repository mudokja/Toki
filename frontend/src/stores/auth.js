import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { jwtDecode } from 'jwt-decode'



export const useAuthStore = defineStore('auth', () => {
    const accessToken = ref(null)
    const user = ref(null)
    const userTag = ref(null)

    const isAuthenticated = computed(() => !!accessToken.value)

    function setToken(newToken) {
        // 유저 태그 가져오기
        userTag.value = jwtDecode(newToken).userTag
        
        const decoded = jwtDecode(newToken).userId
        user.value = decoded
        accessToken.value = decoded.accessToken
        localStorage.setItem('accessToken', newToken)
        localStorage.setItem('userId', decoded)
        
        
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
        localStorage.removeItem('userId')
        localStorage.removeItem('auth')
    }

  return { accessToken, user, isAuthenticated, setToken, clearToken, userTag}
}, { persist: true })
