<template>
    <div class="header">
        <b-navbar toggleable="lg" type="light" variant="white">
        <b-navbar-brand href="#" to="/"><img src="@/assets/OGPS.jpg" width="70px" height="60px"/></b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav >
                <b-nav-item href="#" to="/attraction">전국 관광지</b-nav-item>
                <b-nav-item to="/group">그룹플랜</b-nav-item>
                <b-nav-item to="/review">여행후기</b-nav-item>
                <b-nav-item>날씨</b-nav-item>
            </b-navbar-nav>

            
            <!-- after login -->
            <b-navbar-nav class="ml-auto" v-if="userInfo">
                <b-nav-item class="align-self-center">
                    {{ userInfo.userName }}({{ userInfo.userId }})님 환영합니다.
                </b-nav-item>
                <b-nav-item class="align-self-center">
                <b-icon icon="person-fill"></b-icon>
                <router-link :to="{ name: 'mypage' }" class="link align-self-center">내정보보기</router-link>
                </b-nav-item> 
                <b-nav-item class="align-self-center link" @click.prevent="onClickLogout">
                  <b-icon icon="power"></b-icon>로그아웃
                </b-nav-item> 
                <b-nav-item>
                    <b-button :class="{'animate__animated animate__bounce animate__infinite': condition}" v-b-toggle.sidebar-right variant="outline-info" @click="noticeview"><img src="@/assets/img/alarm.png" width="20px" height="20px"></b-button>
                    <b-sidebar id="sidebar-right" title="알림" right shadow>
                    <div class="px-3 py-2">
                        <ul style="list-style-type: none;">
                          <li class="mb-3" v-for="(index,i) in notices" :key="i">{{ index.userId }}님이 {{ index.groupNo }}그룹에 참가요청을 보냈습니다.<br>
                            <button type="button" style="font-size: 5px; width: 20px; height: 20px;" @click="joinmember(index.userId,index.groupNo)">O</button><button type="button" style="font-size: 5px; width: 20px; height: 20px;" @click="deletenotice(index.userId,index.groupNo)">X</button></li>
                        </ul>  
                    </div>
                    </b-sidebar>
                </b-nav-item>
            </b-navbar-nav>
            <!-- before login -->
            <b-navbar-nav class="ml-auto" v-else>
              <b-icon icon="unlock-fill" variant="primary" width="20px" height="19px"></b-icon><router-link :to="{ name: 'AppMain' }" class="ml-1 mr-3"> Login </router-link>
            </b-navbar-nav>
        </b-collapse>
    </b-navbar>
    </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
import http from "@/api/http";
import "animate.css";
const memberStore = "memberStore";

export default {
  name: "TheHeaderNavbar",
  data() {
    return {
      notices:[],
      condition:false,
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  created(){
    http.get(`/api/groupmember/${this.userInfo.userId}`)
      .then(response => {
        this.notices = response.data.notices;
        if(this.notices.length>0){
          this.condition=true;
        }
      })
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "AppMain" });
      console.log(this.userInfo.userId);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userId);
      this.userLogout(this.userInfo.userId);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "AppMain" });
    },
    noticeview(){
      // http.get(`/api/groupmember/${this.userInfo.userId}`)
      // .then(response => {
      //   this.notices = response.data.notices;
      //   if(this.notices.length>0){
      //     this.condition=true;
      //   }
      // })
    },
    joinmember(userId1,groupNo1){
      if(confirm("참가 승인하시겠습니까?")){
        http.post('/api/groupmember',{
          userId : `${userId1}`,
          groupNo : `${groupNo1}`
        })
        .then(response => {
          alert(response.data.rsmsg);
          http.get(`/api/groupmember/${this.userInfo.userId}`)
          .then(response => {
            this.notices = response.data.notices;
            if(this.notices.length==0){
              this.condition=false;
            }
          })
        })
      }
    },
    deletenotice(userId1,groupNo1){
      if(confirm("거절하시겠습니까?")){
        http.delete('/api/groupmember',{
          data:{
            userId : `${userId1}`,
            groupNo : `${groupNo1}`
          }
        })
        .then(response => {
          alert(response.data.rsmsg);
          http.get(`/api/groupmember/${this.userInfo.userId}`)
          .then(response => {
            this.notices = response.data.notices;
            if(this.notices.length==0){
              this.condition=false;
            }
          })
        })
      }
    },
  },
};
</script>

<style scoped>
#logo {
  width: 120px;
}
.header{
  margin-left: auto;
  margin-right: auto;
  width: 90%;
  
}
</style>
