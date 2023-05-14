<template>
      <div class="row">
        <div class="mb-4 text-center col-2">
          <img src="@/assets/img/ssafy-logo-black.png" alt="" />
        </div>
        <div class="mb-4 ml-auto text-right col-10">
          <div>
            <div class="ms-auto col-12 align-self-end">
              <img src="@/assets/img/ssafy-logo-small.png" width="150px" alt="" />
              <img src="@/assets/img/tour-api-logo.png" width="150px" alt="" />
              <img src="@/assets/img/visit-korea-logo.png" width="150px" alt="" />
              <img src="@/assets/img/mcst-logo.png" width="150px" alt="" />
            </div>
          </div>
          <div class="text-center">
            <a href="#" class="nav-link text-secondary" style="font-weight: bold">대표 : Hi SSAM 주소: 212, Teheran-ro, Gangnam-gu, Seoul, Republic of Korea</a>

            <a href="#" class="nav-link text-secondary" style="font-weight: bold">대표번호: 1588-3357 사업자번호 : 123-4567-987</a>
          </div>
        </div>
      </div>
  </template>
  
  
  <script>
  export default {
    name: "TheHeader",
  };
  </script>
  
  <style scope>
  img {
    width: 150px;
  }
  
  .header {
    margin: auto;
    margin-top: 20px;
    background-color: aquamarine;
    width: 90%;
    height: 20%;
    border-radius: 10px;
    box-shadow: 5px px 10px rgba(159, 157, 157, 0.3);
  }
  
  a {
    font-weight: bold;
    color: #2c3e50;
  }
  
  a:hover {
    color: #42b983;
  }
  </style>
  