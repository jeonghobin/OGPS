<template>
  <header style="font-family: 'Black Han Sans', sans-serif">
		<nav class="navbar navbar-light bg-light navbar-expand-lg">
			<div class="section-content p-1 mt-3 container-fluid">
				<a class="navbar-brand text-primary fw-bold"
					href="/"> <img src="@/assets/img/trip-logo.png"
					alt="" width="130px" />
				</a>
				<div class="collapse navbar-collapse" style="font-weight: bold"
					id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-lg-0">
			<li class="nav-item d-flex">
			<a class="nav-link"><router-link to="/attraction">전국 관광지 정보 </router-link></a>
            <a class="nav-link"><router-link to="/board"> 공지사항 </router-link></a>
            <a class="nav-link"><router-link to="/freeboard"> 자유게시판</router-link></a>
          </li>
			</ul>
			<ul class="navbar-nav mb-2 me-2 mb-lg-0 before-login "
				style="display: flex">
				<li class="nav-item">
					<button type="button" class="nav-link bg-light"
						aria-current="page" data-bs-toggle="modal"
						data-bs-target="#signup-modal" style="border-width: 0">
						회원가입</button>
				</li>
				<li class="nav-item">
					<button type="button" class="nav-link bg-light"
						aria-current="page" data-bs-toggle="modal"
						data-bs-target="#signin-modal" style="border-width: 0">
						로그인</button>
				</li>
			</ul>
				</div>
			</div>
		</nav>
	</header>
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
