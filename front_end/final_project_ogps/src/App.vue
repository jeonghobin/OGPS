<template>
  <div id="app">
    <div class="background">
      <the-header></the-header>
      <router-view/>
      <the-footer></the-footer>
    </div>
    
  </div>
</template>

<script>
import TheFooter from './components/TheFooter.vue'
import TheHeader from './components/TheHeader.vue'
export default {
  components:{
    TheHeader,
    TheFooter
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap');

.background{
  height: 100%;
  overflow: hidden;
  margin:0;
  padding-top: 15px;
  padding-bottom: 15px;
  background-image: url("@/assets/main/main6.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}
#app {
  font-family: 'NanumYeBbeunMinGyeongCe';
  font-size: 25px;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
