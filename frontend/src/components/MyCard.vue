<script setup>
import { onMounted } from "vue"

const props = defineProps({
  item: {
    type: [Number, Object],
    required: true,
  },
  index: Number
})



</script>

<template>
      <v-card
    class="mx-auto"
    max-width="400"
  >
    <v-img
      class="align-end text-white"
      height="300"
      src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
      cover
    >
      
    </v-img>

    <v-card-subtitle class="pt-4">
      Number {{ props.item }}
    </v-card-subtitle>

    <v-card-text>
      <div>Whitehaven Beach</div>

      <div>Whitsunday Island, Whitsunday Islands</div>
    </v-card-text>

    <v-card-actions>
      <v-btn color="orange">
        Share
      </v-btn>

      <v-btn color="orange">
        Explore
      </v-btn>
    </v-card-actions>
  </v-card>

</template>

<style lang="scss" scoped>

</style>