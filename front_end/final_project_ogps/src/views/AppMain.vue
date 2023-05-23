<template>
    <div class="margin">
    <div class="d-flex justify-content-center animate__animated animate__fadeInDown">
      <div>
        <img class="mt-1" src="@/assets/OGPS.png" width="200px" height="175px">
        <h4 class="mb-2" style="color:#ede9e9;">Our Group Plan's</h4>
      </div>
    </div>    
      <div class="d-flex justify-content-center mb-3 mt-1 animate__animated animate__fadeIn aaa">
        <div class="col-4 pt-2 pb-2">
            <!-- 로그인 후 -->
            <div class="transparent-background" v-if="userInfo" style="height: 95%; width: 90%;">
              <div class="d-flex justify-content-center">
                <h1>내 정보</h1>
              </div>
              <div class="d-flex justify-content-center">
                <div class="mt-4 mr-2">아이콘</div>
                <div>
                  <div>
                    이름 : {{ userInfo.userName }}
                  </div>
                  <div>
                    이메일 : {{ userInfo.userEmail }}
                  </div>
                  <div>
                    등록일 : {{ userInfo.joinDate }}
                  </div>
                </div>
              </div>
              <div class="row mt-3" style="width: 100%;">
                <div class="col-2 ml-auto pl-0 pr-0">
                  <button type="button" style="border-radius:10px; font-size:18px;" class="btn btn-primary">수정</button>
                </div>
                <div class="col-2 pl-0 pr-0">
                  <button type="button" style="border-radius:10px; font-size:18px;" class="btn btn-danger">삭제</button>
                </div>
              </div>
            </div>
            <!-- 로그인 전-->
            <div class="d-flex justify-content-center" v-else>
              <div class="overflow-auto"  style="height: 100%; width: 70%;">
                <h1 class="mr-2" style="color: #ddd7d7;"><b-icon icon="unlock-fill" variant="primary" width="40px" height="40px"></b-icon> Login</h1>
                      <b-form class="text-left mt-3">
                        <b-alert show variant="danger" v-if="isLoginError">아이디 또는 비밀번호를 확인하세요.</b-alert>
                        <b-form-group label-for="userId">
                          <b-form-input
                            id="userId"
                            v-model="user.userId"
                            required
                            placeholder="아이디 입력...."
                            @keyup.enter="confirm"
                          ></b-form-input>
                        </b-form-group>
                        <b-form-group  label-for="userPassword">
                          <b-form-input
                            type="password"
                            id="userPassword"
                            v-model="user.userPassword"
                            required
                            placeholder="비밀번호 입력...."
                            @keyup.enter="confirm"
                          ></b-form-input>
                        </b-form-group>
                        <b-checkbox style="color:#ede9e9;">아이디 저장</b-checkbox>
                        <b-button type="button" variant="primary" class="m-1" style="width: 97%;" @click="confirm">로그인</b-button>
                        <!-- <b-button type="button" variant="success" class="m-1" @click="movePage">회원가입</b-button> -->
                        <a type="button" class="m-1 d-flex justify-content-end" @click="movePage" style="color: #ede9e9;">회원가입</a>
                      </b-form>
              </div>
          </div>
        </div>

        <!-- card -->
        <div class="col-6 pt-2 pb-2 flex">
          <div class="middle-content" style="background-color: white;">
            <b-card style="width: 100%; height: 100%;">
              <div>
                <b-carousel
                  id="carousel-1"
                  :interval="4000"
                  controls
                  indicators
                  background="#ababab"
                  img-width="800"
                  img-height="240px"
                  style="text-shadow: 1px 1px 2px #333;"
                >

                  <b-carousel-slide>
                    <template #img>
                      <img
                        class="d-block img-fluid"
                        style="width: 800px; height: 240px;"
                        src="@/assets/img/busan1.jpeg"
                        alt="image slot"
                      >
                    </template>
                  </b-carousel-slide>

                  <b-carousel-slide>
                    <template #img>
                      <img
                        class="d-block img-fluid"
                        style="width: 800px; height: 240px;"
                        src="@/assets/img/busan3.jpeg"
                        alt="image slot"
                      >
                    </template>
                  </b-carousel-slide>

                  <b-carousel-slide>
                    <template #img>
                      <img
                        class="d-block img-fluid"
                        style="width: 800px; height: 240px;"
                        src="@/assets/img/deagu5.jpeg"
                        alt="image slot"
                      >
                    </template>
                  </b-carousel-slide>

                  <b-carousel-slide>
                    <template #img>
                      <img
                        class="d-block img-fluid"
                        style="width: 800px; height: 240px;"
                        src="@/assets/img/seoul1.jpeg"
                        alt="image slot"
                      >
                    </template>
                  </b-carousel-slide>

                </b-carousel>
              </div>
              </b-card>
          </div>
        </div>
      </div>

      <h1 class="animate__animated animate__fadeIn bbb">인기 여행지</h1>
      <div class="d-flex justify-content-around mt-1 animate__animated animate__fadeIn bbb">
        <div class="row">
          <div class="col-4">
            <b-card
              :title="attractions[0].title"
              :img-src="attractions[0].first_image || require('@/assets/img/jeju2.jpeg')"
              img-alt="대체 이미지"
              img-top
              tag="article"
              style="max-width: 24rem;"
              class="mb-2"
            >
            <template #img="{src,alt}">
              
                <img :src="src" :alt="alt" @error="handleImage" >
              
            </template>
              <b-card-text>
                {{attractions[0].addr1}}
              </b-card-text>

              <b-button href="#" variant="primary">Go somewhere</b-button>
            </b-card>
          </div>
          <div class="col-4">
            <b-card
              :title="attractions[1].title"
              :img-src="attractions[1].first_image || require('@/assets/img/busan1.jpeg')"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 24rem;"
              class="mb-2"
            >
              <b-card-text>
                {{attractions[1].addr1}}
              </b-card-text>

              <b-button href="#" variant="primary">Go somewhere</b-button>
            </b-card>
          </div>
          <div class="col-4">
            <b-card
              :title="attractions[2].title"
              :img-src="attractions[2].first_image || require('@/assets/img/busan3.jpeg')"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 24rem;"
              class="mb-2"
            >
              <b-card-text>
                {{attractions[2].addr1}}
              </b-card-text>

              <b-button href="#" variant="primary">Go somewhere</b-button>
            </b-card>
          </div>
        </div>
      </div>   


      <div class="row justify-content-center mb-3 mt-3 animate__animated animate__fadeIn ccc">
          <div class="overflow-auto transparent-background" style="width: 79%;">
            <h1>인기 후기</h1>
          </div>
          <div class="overflow-auto transparent-background " style="width: 79%;">

          </div>
      </div>

    </div> 
</template>

<script>
import "animate.css";
import { mapState, mapActions } from "vuex";
import http from "@/api/http";
const memberStore = "memberStore";

export default {
  name: "AppMain",
  data() {
    return {
      // isLoginError: false,
      user: {
        userId: null,
        userPassword: null,
      },
      slide: 0,
      sliding: null,
      attractions:[],
    };
  },
  created(){
    http.get('/attraction/rank')
    .then(response =>{
      console.log(response.data.attractions);
      this.attractions=response.data.attractions;
    })
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo","notice"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo", "getNotice"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        await this.getNotice(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        // this.$router.push({ name: "AppMain" });
      }
    },
    handleImage(event) {
      event.target.src = '@/assets/main/main2.jpg';
      event.target.alt = 'Fallback Image';
    },
    movePage() {
      this.$router.push({ name: "join" });
    },
    onSlideStart(slide) {
      this.sliding = true
      console.log(slide)
    },
    onSlideEnd(slide) {
      this.sliding = false
      console.log(slide)
    }
  },
};
</script>

<style scoped>
.transparent-background {
    background-color: rgba(255,255,255,0.5);
    border-radius: 25px;
     /* 0부터 1까지의 값을 사용하며, 1에 가까울수록 불투명해집니다. */
}

.margin{
  margin-left: auto;
  margin-right: auto;
  width: 80%;
}

.flex{
  display: flex;
  flex-flow: row;
  justify-content: center;
  align-items: center;
}
.aaa{
  animation-delay: 0.5s;
}
.bbb{
  animation-delay: 1s;
}
.ccc{
  animation-delay: 1.5s;
}

</style>