import { ref } from 'vue'
import { defineStore } from 'pinia'
import { useAuthStore } from '@/stores/auth.js'
import { memberDetail, memberSimple } from '@/js/Member.js'

export const useUserStore = defineStore('user', () => {
  // pinia 유저 pk 가져오기
  let userPk = null
  if (useAuthStore().user === null || useAuthStore().user === undefined) {
    console.log('로그인 되지 않았습니다. / user.js')
  } else {
    userPk = useAuthStore().user.userId
  }
  // const userPk = 'c0a81fbc-8da0-1456-818d-a0b470800000'

  // GET 유저 정보 상세 조회
    // 유저 상세 정보 할당
  const memberDetailData = ref()
  const memberDetailAxios = function() {
    if (userPk === null || userPk === undefined) {
        console.log('로그인을 하지 않아서, 유저 상세 정보 조회 memberDetailAxios 함수가 작동하지 않습니다.')
      }
    else {
      memberDetail(
        userPk,
        ({data}) => {console.log(data, '[Success] user.js : memberDetailAxios'); memberDetailData.value = data;},
        (fail) => {console.log(fail, '[Error] user.js : memberDetailAxios');}
        )
    }
  }

  // GET 유저 정보 간단 조회
    // 유저 간단 정보 할당
  const memberSimpleData = ref()
  const memberSimpleAxios = function() {
    if (userPk === null || userPk === undefined) {
      console.log('로그인을 하지 않아서, 유저 간단 정보 조회 memberSimpleAxios 함수가 작동하지 않습니다.')
    }
    else {
      memberSimple(
        userPk,
        ({data}) => {console.log(data, '[Success] user.js : memberSimpleAxios'); memberSimpleData.value = data},
        (fail) => {console.log(fail, '[Error] user.js : memberSimpleAxios');}
      )
    }
  }

  return { memberDetailAxios, memberDetailData, memberSimpleAxios, memberSimpleData}
}, { persist: true })
