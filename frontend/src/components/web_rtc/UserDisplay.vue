<script setup>
import { ref } from 'vue';
import { array } from 'i/lib/util';
const props = defineProps({userList:array})
console.log(props.userList)
const userList2 = ref({});
userList2.value=props.userList;
console.log("flkasnlkfn",userList2.value.user[0])
</script>

<template>
    <div >
        <video src="" width="200" height="200" id="display"></video>
    </div>
</template>

<style scoped>
#display{
    background-color: aqua;
}
</style>