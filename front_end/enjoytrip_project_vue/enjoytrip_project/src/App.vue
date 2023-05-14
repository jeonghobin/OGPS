<template>
  <div id="app">
      <the-header></the-header>
      <router-view></router-view>
      <the-footer></the-footer>
  </div>
</template>


<script>
import TheHeader from "@/components/TheHeader.vue";
import TheFooter from "@/components/TheFooter.vue";

export default({
  components:{
    TheHeader,
    TheFooter
  },
})
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  width: 100%;
  height: 100%;
  color: #2c3e50;
}
</style>
