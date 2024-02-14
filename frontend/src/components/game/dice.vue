<template>
  <div class="dice-game">
    <h1>주사위 게임</h1>
    <button @click="startGame" :disabled="rolling || result !== null" class="roll-button">{{ rolling ? '굴리는 중...' : '주사위 굴리기' }}</button>
    <div class="dice-container">
      <div v-if="rolling" class="rolling-number">
        <span>{{ currentNumber }}</span>
      </div>
      <div v-else-if="result !== null" class="result">
        <span>{{ result }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    const currentNumber = ref(1);
    const rolling = ref(false);
    const result = ref(null);

    const startGame = () => {
      if (rolling.value || result.value !== null) return;

      rolling.value = true;
      let number = 1;
      const rollInterval = setInterval(() => {
        number++;
        if (number > 6) {
          number = 1;
        }
        currentNumber.value = number;
      }, 300); // 0.3초마다 숫자가 변함

      setTimeout(() => {
        clearInterval(rollInterval);
        rolling.value = false;
        result.value = currentNumber.value;
      }, 3000); // 3초 후에 애니메이션이 멈추고 결과가 출력됨
    };

    return {
      currentNumber,
      rolling,
      result,
      startGame
    };
  }
};
</script>

<style scoped>
.dice-game {
  text-align: center;
}

.roll-button {
  margin-top: 20px;
  padding: 10px 20px;
  font-size: 16px;
  background-color: #F9C95E;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 5px;
  transition: background-color 0.3s;
}

.roll-button:hover {
  background-color: #F9B02C;
}

.dice-container {
  display: inline-block;
}

.rolling-number {
  font-size: 36px;
  font-weight: bold;
  border: 2px solid #333;
  border-radius: 10px;
  padding: 20px;
  animation: rollAnimation 0.3s infinite;
}

@keyframes rollAnimation {
  0% { opacity: 0.1; }
  50% { opacity: 1; }
  100% { opacity: 0.1; }
}

.result {
  font-size: 36px;
  font-weight: bold;
  margin-top: 20px;
}
</style>