<template>
    <div>

        <v-btn
            color="primary"
            @click.stop="oepnSideVar"
        
        >
        Toggle
        </v-btn>
        
        <SideVarModal/>

    </div>
    <link href="https://cdn.jsdelivr.net/npm/@mdi/font@5.x/css/materialdesignicons.min.css" rel="stylesheet">
  </template>
<script setup>

import { ref, provide } from 'vue';
import SideVarModal from '@/components/modal/SideVarModal.vue';

const drawer = ref(false)

const oepnSideVar = () => {
    drawer.value = true
    
}
provide('drawerState', drawer)


</script>

<style>

</style>