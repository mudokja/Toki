<template>
  <v-container class="mt-3" fluid>
    <v-row>
      <v-col offset="3" cols="6">
        <v-card class="mx-auto">
          <div class="mt-7 mb-4 mr-10">
            <ProfileBlacklistCreateModal/>
          </div>
          <v-list :items="items" item-props lines="three">
            <template v-slot:subtitle="{ subtitle }">
              <div class="d-flex justify-space-between">
                <div v-html="subtitle"></div>
                <v-btn class="ma-1 ml-7" color="blue-grey-lighten-5">블랙리스트 삭제</v-btn>
              </div>
            </template>
          </v-list>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
  
  <script setup>
  import { ref } from 'vue'
  import ProfileBlacklistCreateModal from '@/components/modal/ProfileBlacklistCreateModal.vue';

  const items = ref([
    { type: 'subheader', title: '블랙 리스트' },
    {
      prependAvatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
      title: '아이디 or 닉네임',
      subtitle: `<span class="text-primary">자기 소개</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?`,
    },
    { type: 'divider', inset: true },
    {
      prependAvatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
      title: '아이디 or 닉네임',
      subtitle: `<span class="text-primary">자기 소개</span> &mdash; Wish I could come, but I'm out of town this weekend.`,
    },
    { type: 'divider', inset: true },
    {
      prependAvatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
      title: '아이디 or 닉네임',
      subtitle:
        '<span class="text-primary">자기 소개</span> &mdash; Do you have Paris recommendations? Have you ever been?',
    },
    { type: 'divider', inset: true },
    {
      prependAvatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
      title: '아이디 or 닉네임',
      subtitle:
        '<span class="text-primary">자기 소개</span> &mdash; Have any ideas about what we should get Heidi for her birthday?',
    },
    { type: 'divider', inset: true },
    {
      prependAvatar: 'https://cdn.vuetifyjs.com/images/lists/5.jpg',
      title: '아이디 or 닉네임',
      subtitle:
        '<span class="text-primary">자기 소개</span> &mdash; We should eat this: Grate, Squash, Corn, and tomatillo Tacos.',
    },
  ])
  </script>
  
  <style scoped>

  </style>