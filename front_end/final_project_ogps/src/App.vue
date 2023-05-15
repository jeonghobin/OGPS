<template>
  <div id="app">
    <div class="background">
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
      <h1>1</h1>
      <h1>2</h1>
      <h1>3</h1>
      
    </nav>
    <router-view/>
    </div>
  </div>
</template>

<style>
body{
  margin: 0;
}
.background{
  height: 120vh;
  /* overflow: hidden; */
  margin:0;
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
