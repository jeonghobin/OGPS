<template>
  <div id="app">
    <the-header></the-header>
    <div class="background">
      <router-view/>
      <h1>1</h1>
      <h1>1</h1>
      <h1>1</h1>
      <h1>1</h1>
      <h1>1</h1>
      <h1>1</h1>
      <h1>1</h1>
      <h1>1</h1>
      <h1>1</h1>
      <h1>1</h1>
      <h1>1</h1>
      <h1>1</h1>
      <h1>1</h1>
      <h1>1</h1>
      
    </div>
    <the-footer></the-footer>
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
body{
  margin: 0;
}
.background{
  height: 100%;
  overflow: hidden;
  margin:0;
  padding-top: 15px;
  background-image: url("@/assets/main/main6.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
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
