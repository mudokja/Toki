<!-- <script setup>
import { RouterLink, RouterView } from 'vue-router'
import BaseButton from './components/base/BaseButton.vue';
import BlackButton from './components/buttons/BlackButton.vue';
import GrayButton from './components/buttons/GrayButton.vue';
import ImageButton from './components/buttons/ImageButton.vue';
import BaseModal from './components/base/BaseModal.vue';
import { click } from './js/Click';
import { onMounted, ref } from 'vue';

const change =ref(false);
const clickChange=()=>{
click(change
)
console.log("dsjbjkg",change.value);}

</script>

<template>
  <header>
    <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="125" height="125" />

    <div class="wrapper">
      <HelloWorld msg="You did it!" />

      <nav>
        <RouterLink to="/"><BlackButton type="Home"/></RouterLink>
        <RouterLink to="/room"><GrayButton type="Room"/></RouterLink>
        <RouterLink to="/profile"><ImageButton type="Profile" /></RouterLink>
        <GrayButton type="로그아웃" v-on:click="clickChange()"></GrayButton>
        
      
      </nav>
    </div>
    <div v-if="change==true">모달눌림<BaseModal  v-on:state="change" v-on:clickchange="clickChange" /></div>
  </header>

  <RouterView />
</template>


<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style> -->





<script setup>
import { useRoute } from 'vue-router';
import HeaderView from './views/HeaderView.vue';
import FooterView from './views/FooterView.vue';
import { computed } from 'vue';


const route = useRoute()
const isRoomView = computed(() => route.name === 'room')

</script>

<template>
  <v-app style="padding-top: 70px;">
    <HeaderView v-if="!isRoomView" />
    <RouterView />
    <FooterView v-if="!isRoomView" />
  </v-app>
  <link href="https://cdn.jsdelivr.net/npm/@mdi/font@5.x/css/materialdesignicons.min.css" rel="stylesheet">
</template>

<style scoped>

</style>