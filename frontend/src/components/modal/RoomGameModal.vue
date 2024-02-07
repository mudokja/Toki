<script setup>
import { ref, defineProps } from 'vue';


const props = defineProps({
    roomGameDialog: {
        type: Boolean,
        required: true,
    }
})

const emit = defineEmits(['update:roomGameDialog'])
const selectedGame = ref('')

function closeModal() {
  emit('update:roomGameDialog', false)
}

function selectGame(gameName) {
    selectedGame.value = gameName
}


</script>

<template>
    <div>
        <v-dialog
            v-model="props.roomGameDialog"
            @click:outside="closeModal"
            width="auto"
            height="1000px"                        
        >
            <v-sheet 
                width="1000px" 
                height="1000px" 
                color="black"                
                style="border-radius: 20px; border: 1px solid white;"
            >
                <v-toolbar
                    color="black"                    
                >
                    <v-toolbar-title                        
                        style="margin-left: 50px;"
                    >
                        게임
                    </v-toolbar-title>
                </v-toolbar>

                <v-row
                    class="d-flex align-center justify-center"
                    style="margin-left: 10%; margin-right: 10%; margin-top: 25%; text-align: center;"             
                >
                    <v-col
                    >
                        <v-btn
                            color="white"
                            icon=""
                            size="150px"
                            @click="selectGame('ladder')"
                        >
                            <v-icon
                                class="mdi mdi-ladder"
                                size="100px"
                            >

                            </v-icon>

                        </v-btn>
                        <br>
                        <br>
                        <v-text class="text-h6" style="white-space: nowrap;">
                            사다리 게임
                        </v-text>
                    </v-col>


                    <v-col
                    >
                        <v-btn
                            color="white"
                            icon=""
                            size="150px"
                            @click="selectGame('dice')"
                        >
                            <v-icon
                                class="mdi mdi-dice-5"
                                size="100px"
                            >

                            </v-icon>

                        </v-btn>
                        <br>
                        <br>
                        <v-text class="text-h6" style="white-space: nowrap;">
                            주사위 게임
                        </v-text>
                    </v-col>
                    <v-col
                    >
                        <v-btn
                            color="white"
                            icon=""
                            size="150px"
                            @click="selectGame('drawLots')"
                        >
                            <v-icon
                                class="mdi mdi-archive"
                                size="100px"
                            >

                            </v-icon>

                        </v-btn>
                        <br>
                        <br>
                        <v-text class="text-h6" style="white-space: nowrap;">
                            제비 뽑기
                        </v-text>
                    </v-col>
                    <v-col
                    >
                        <v-btn
                            color="white"
                            icon=""
                            size="150px"
                            @click="selectGame('Taboo')"
                        >
                            <v-icon
                                class="mdi mdi-alphabetical-variant"
                                size="100px"
                            >

                            </v-icon>

                        </v-btn>
                        <br>
                        <br>
                        <v-text class="text-h6" style="white-space: nowrap;">
                            단어 맞추기
                        </v-text>
                    </v-col>
                </v-row>
            </v-sheet>
        </v-dialog>
    </div>
</template>

<style scoped>

</style>