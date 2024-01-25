<template>
    <div>

        <v-btn
            color="primary"
            @click="openDialog"
        >
            login
        </v-btn>
        <LoginModal/>

    </div>
    <link href="https://cdn.jsdelivr.net/npm/@mdi/font@5.x/css/materialdesignicons.min.css" rel="stylesheet">
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