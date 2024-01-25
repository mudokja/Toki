<script setup>
import { ref } from 'vue'

const drawer = ref(null)
</script>

<template>
    <v-navigation-drawer
        v-model="drawer"
        location="right"
        temporary
    >

    <div style="display: flex; align-items: center; margin-top: 10px; margin-bottom: 10px;">
        <img src="@/assets/profile_assets/프로필.png" style="width: 40px; height: 40px; border-radius: 50%; margin-left: 10px;">
        <div>
            <h4 style="margin-left: 10px;">5반 유저</h4>
        </div>
    </div>

    <v-divider></v-divider>

    <v-list density="compact" nav>
      <v-list-item value="home">
        <div style="display: flex; align-items: center;">
          <img src="@/assets/profile_assets/토키 로고.png" alt="토키 로고" style="width: 45px; height: 37px; margin-right: 16px; margin-left: -8px;">
          <div>
            <h5>home</h5>
          </div>

        </div>
      </v-list-item>
      <v-list-item prepend-icon="mdi-forum" title="About" value="about"></v-list-item>
    </v-list>
    </v-navigation-drawer>

    
    <v-btn
        color="primary"
        @click.stop="drawer = !drawer"
        
    >
    Toggle
    </v-btn>

</template>

<style scoped>

</style>