<script setup>
import BaseInput from '../base/BaseInput.vue';
import BlackButton from '../buttons/BlackButton.vue';
import { click } from '@/js/Click';
import { ref, defineEmits } from 'vue';
const props = defineProps({
    state:Boolean
});
const loginprop=ref();
loginprop.value=props.state
console.log("dqppq",props.state);
const emit =defineEmits(["click-change"]);
function clickc(){
    console.log("눌렷냐");
emit("click-change")
}
</script>

<template>
   
    <div><BaseInput/><BlackButton v-on:click="clickc()"/></div>

    
</template>

<style scoped>


</style>