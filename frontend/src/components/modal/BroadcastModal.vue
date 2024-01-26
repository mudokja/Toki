<script setup>
import { ref, inject } from 'vue';

const broadcastDialog = inject('broadcastState')


const ageLimit = ref('')

const category = ['hello', 'hi', 'good']

const gender = ['남자', '여자']
const genderDisabled = ref(true)
const genderCatch = ref('')
const genderToggle = () => {
    genderDisabled.value = !genderDisabled.value
    genderCatch.value = ''
}


const btn1 = ref(false)
const btn2 = ref(false)
const btn3 = ref(false)

const show = ref(false)

const hashTag = ref([]);

const newHashtag = ref('');

const addHashtag = (newTag) => {
    if (newTag.trim() && !hashTag.value.includes(newTag) && hashTag.value.length < 10) {
        hashTag.value.push(newTag);
        newHashtag.value = ''
    } else {
        newHashtag.value = ''
    }
}

const removeHashtag = (removeTag) => {
    hashTag.value = hashTag.value.filter(tag => tag !== removeTag);
}





</script>

<template>
    <div>
        <v-dialog v-model="broadcastDialog" width="auto">
            <v-card width="800" height="800" justify="center">
                <v-sheet>
                    <v-container class="d-flex align-center" style="margin-top: 50px">
                        <v-col 
                            cols="2"
                            offset="1"
                            >
                            <img 
                                src="@/assets/profile_assets/토키 로고.png" 
                                alt="로고 이미지" 
                                width="150px"
                            >
                        </v-col>
                        <v-col
                            offset="2" 
                        >
                            <p 
                                class="font-weight-bold text-h2 text-light-blue-lighten-2"
                            >                                
                                토키 만들기
                            </p>
                        </v-col>
                    </v-container>           
                </v-sheet>
                
                <v-container 
                    style="width: 80%;"
                >
                    <v-text-field
                        label="제목"
                        placeholder="Tom & Toms"
                        class="text-black"
                        variant="outlined"
                        rounded="lg"
                        base-color="blue"
                        color="light-blue-lighten-1"
                    >

                    </v-text-field>
                </v-container>
                <v-container
                    class="d-flex"
                    style="width: 90%;"
                >
                    <v-col>
                        <v-combobox
                            clearable
                            label="카테고리"
                            :items="category"
                            class="text-black"
                            variant="outlined"
                            rounded="lg"
                            base-color="blue"
                            color="light-blue-lighten-1"
                        >
        
                        </v-combobox>
                    </v-col>
                        
                    <v-col>
                        <v-text-field                            
                            placeholder="해시태그"
                            class="text-black"
                            variant="outlined"
                            rounded="lg"
                            base-color="blue"
                            color="light-blue-lighten-1"
                            v-model.trim="newHashtag"
                            @keyup.enter="addHashtag(newHashtag)"
                            maxlength="20"                            
                        >
                        
                        <template v-slot:append>
                            <v-btn
                                color="black"
                                height="55"                                
                                rounded="lg"
                                @click="addHashtag(newHashtag)"
                                
                            >
                                추가
                            </v-btn>
                        </template>

                        </v-text-field>
                    </v-col>                 
                </v-container>

                <div style="margin-left: 10%;">
                    <v-chip
                        variant="outlined"
                        color="light-blue-lighten-1"
                        closable
                        label
                        v-for="tag in hashTag"
                        :key="tag"
                        class="ma-2"
                        
                        @click:close="removeHashtag(tag)"
                        @click="removeHashtag(tag)"
                    >
                        {{ tag }} 
                    </v-chip>                                        
                </div>

                <v-container 
                    class="d-flex justify-end" 
                    style="margin-left: -3%;"
                    >
                    
                        <v-btn 
                            class="mx-5 font-weight-bold"
                            stacked                            
                            variant="text"
                            color="primary"
                            @click="active = !actiive"
                            
                        >
                          뱃지 착용
                        </v-btn>

                        <v-btn 
                            class="mx-5"
                            stacked
                            
                        >
                            악성 유저
                        </v-btn>

                        <v-btn 
                            class="mx-5"
                            stacked
                            
                            
                        >
                            캠 on
                        </v-btn>

                </v-container>

                <v-container
                    class="d-flex"
                    style="width: 90%;"
                >
                    <v-col
                        cols="5"
                    >
                        <v-text-field
                            placeholder="비밀번호 입력"
                            class="text-black"
                            variant="outlined"
                            rounded="lg"
                            base-color="blue"
                            color="light-blue-lighten-1"
                            maxlength="20"
                            :append-inner-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                            :type="show ? 'text' : 'password'"
                            @click:append-inner="show = !show"
                        >
                        </v-text-field>
                    </v-col>
                        
                    <v-col
                        cols="3"
                    >
                        <v-text-field
                            placeholder="나이 제한 입력"
                            class="text-black"
                            variant="outlined"
                            rounded="lg"
                            base-color="blue"
                            color="light-blue-lighten-1"
                            type="number"
                            min="0"
                            max="100"                            
                            v-model.number="ageLimit"
                        >

                        </v-text-field>            
                    </v-col>

                    <v-col
                        cols="3"
                    >
                        <v-combobox                        
                            label="성별 제한"
                            :items="gender"
                            class="text-black"
                            variant="outlined"
                            rounded="lg"
                            base-color="blue"
                            color="light-blue-lighten-1"
                            :disabled="genderDisabled"
                            v-model.trim="genderCatch"
                        >
                        </v-combobox>
                     </v-col>
                    <v-col
                        cols="1"
                    >
                        <v-checkbox-btn 
                            @click="genderToggle"
                            class="d-flex justify-center align-center"
                        >
                        </v-checkbox-btn>
                    </v-col>

                    
                </v-container>

            </v-card>
        </v-dialog>
    </div>
</template>

<style scoped>
</style>
