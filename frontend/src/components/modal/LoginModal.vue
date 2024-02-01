<script setup>
import { useCounterStore } from '@/stores/counter';
import axios from 'axios';
import { ref, inject, onMounted } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute()

const dialogState = inject('dialogState')

const closeDialog = () => {
    dialogState.value = !dialogState.value
    dialog2.value = !dialog2.value
}



const dialog2 = ref(false);
const username = ref(null);
const password = ref(null);
const store = useCounterStore()

const login = () => {
    const store = useCounterStore()
    const payload = {
        username: username.value,
        password: password.value,
        
    }
    store.login(payload)
}


const loginWithKakao = () => {
    const loginURL = `https://i10b205.p.ssafy.io/oauth2/authorization/kakao`;
    console.log(loginURL)

    window.location.href = loginURL
}

const loginWithNaver = () => {
    const clientID = import.meta.env.VITE_NAVER_CLIENT_ID
    const redirectURI = import.meta.env.VITE_NAVER_REDIRECT_URI
    const loginURL = `https://i10b205.p.ssafy.io/oauth2/authorization/naver`;
    // const loginURL = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${clientID}&redirect_uri=${redirectURI}&state=1234`
    // localStorage.setItem()
    console.log(loginURL)
    
    window.location.href = loginURL
}

// onMounted(() => {
//     const accessToken = route.params.atk
//     const refreshToken = route.params.rtk

// })
</script>

<template>
    <div>
        <v-dialog v-model="dialogState" width="auto">
            <v-card width="500" height="700" justify="center">
                <v-sheet height="300" class="mx-auto">
                    <v-card-text class="text-center">
                        <v-btn class="mt-4 mr-0 text-h5" color="primary" width="320" height="65" @click="dialogState = false, dialog2 = true">
                            계정으로 로그인
                        </v-btn>
                    </v-card-text>
                    <v-card-text class="text-center">
                        <button id="naverIdLogin_loginButton" href="#">
                            <img src="https://static.nid.naver.com/oauth/big_g.PNG" width=320 @click="loginWithNaver">
                        </button>
                    </v-card-text>
                    <v-card-text class="text-center">
                        <button @click="loginWithKakao">
                            <img src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg" width="320" />
                        </button>
                    </v-card-text>
                </v-sheet>
            </v-card>
        </v-dialog>
        <v-dialog v-model="dialog2" width="auto">
            <v-card width="500" height="700">
                <v-toolbar color="rgba(0, 0, 0, 0)">
                    <v-btn class="ma-2" icon @click="closeDialog">
                        <v-icon>mdi-arrow-left</v-icon>
                    </v-btn>
                    <v-toolbar-title class="text-h6">
                        계정으로 로그인
                    </v-toolbar-title>
                </v-toolbar>
                <v-sheet width="300" class="mx-auto">
                    <v-form @submit.prevent="login">
                        <br>
                        <v-text-field label="Username or Email" placeholder="johndoe@gmail.com" type="email" id="username" v-model.trim="username">
                        </v-text-field>
                        <br>
                        <v-text-field v-model.trim="password" label="password" id="password" type="password">
                        </v-text-field>
                        <br>
                        <v-btn block class="mb-8" color="blue" size="large" variant="tonal" type="submit">
                            Log In
                        </v-btn>
                    </v-form>
                </v-sheet>
            </v-card>
        </v-dialog>
    </div>
</template>

<style scoped>
</style>
