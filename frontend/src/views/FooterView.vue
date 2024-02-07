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
  background-image: url('@/assets/profile_assets/Footer.png');
  background-size: cover;
  background-position: center bottom;
  background-repeat: no-repeat;
  z-index: 0; 
  position: absolute;
  bottom: 0;  
  width: 100%;



}

.footer-content {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}


/* html {
  position: relative;
  min-height: 100%;
}

body {
  margin-bottom: 200px; 
} */


</style>
