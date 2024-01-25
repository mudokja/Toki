<template>
    <v-footer class="footer bg-indigo-lighten-1">
      <div class="footer-content">
        <div class="text-h4 text-left mt-4 mb-6">
          토키
        </div>

        <div class="text-left">
          웹 페이지 하단 내용
          <br>
          R. iqsaum stauts foedldovnsd Loremfsi ipsoumed fopsnfoe 
          <br>
          Fpdpsee
        </div>
      </div>
    </v-footer>
    <link href="https://cdn.jsdelivr.net/npm/@mdi/font@5.x/css/materialdesignicons.min.css" rel="stylesheet">
</template>
  
<script setup>

</script>
  
<style scoped>
.footer {
  height: 200px; /* 고정된 높이 값을 설정 */
  background-image: url('@/assets/profile_assets/Footer.png');
  background-size: cover;
  background-position: center bottom;
  background-repeat: no-repeat;
  z-index: 0; 
}

.footer-content {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

/* 화면 크기에 상관없이 푸터를 하단에 고정시키기 */
html {
  position: relative;
  min-height: 100%;
}

body {
  margin-bottom: 200px; /* 푸터 높이만큼 여백을 추가 */
}

.footer {
  position: absolute;
  bottom: 0;
  width: 100%; /* 뷰포트의 전체 너비를 차지하도록 설정 */
}
</style>
