<template>
    <div>

        <v-btn
            style="width: 90px"
            color="primary"
            @click="openDialog"
        >
            login
        </v-btn>
        <LoginModal/>

    </div>

  </template>
<script setup>

import { ref, provide } from 'vue';
import LoginModal from '@/components/modal/LoginModal.vue'

const dialog = ref(false)

const openDialog = () => {
    dialog.value = true
    
}
provide('dialogState', dialog)


</script>

<style>

</style>