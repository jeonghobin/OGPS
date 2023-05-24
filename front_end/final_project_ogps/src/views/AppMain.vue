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
            <div class="transparent-background" v-if="userInfo" style="height: 100%; width: 100%;">
              <div class="mb-3 d-flex justify-content-center">
                <h1>내 정보</h1>
              </div>
              <div style="height: 100%; width: 100%;">
                <div class="row">
                  <div class="col-5 d-flex justify-content-center">
                    <img src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
                    width="90%" height="100%" />
                  </div> 
                  <div class="col-7" style="width: 100%;">
                    <div>
                      <div class="row d-flex justify-content-start">
                        <div class="row">
                          <h4>이름 :&nbsp; </h4>
                          <h4>{{ userInfo.userName }}</h4>
                        </div>
                        <div class="row" style="width: 100%;">
                          <h4>이메일 :&nbsp; </h4>
                          <h4 >{{ userInfo.userEmail }}</h4>
                        </div>
                        <div class="row">
                          <h4>등록일 :&nbsp; </h4>
                          <h4>{{ userInfo.joinDate }}</h4>
                        </div>
                      </div>
                    </div>
                  </div>
                <div class="row d-flex justify-content-end mb-3" style="width: 100%;">
                  <div class="col-4">
                    <b-button variant="primary"  style="width: 100%;" class="mr-1" :to="{ name: 'modify' }">정보수정</b-button>
                  </div>
                  <div class="col-4">
                    <b-button variant="danger"  style="width: 100%;" @click="mDelete">회원탈퇴</b-button>
                  </div>
                </div>
              </div>
            </div>
          </div>
            <!-- 로그인 전-->
            <div class="d-flex justify-content-center transparent-background pt-3 pb-3" v-else>
              <div class="overflow-auto"  style="height: 100%; width: 70%;">
                <h1 class="mr-2" style="color: #ddd7d7;"><b-icon icon="unlock-fill" variant="primary" width="40px" height="40px"></b-icon> Login</h1>
                      <b-form class="text-left mt-3">
                        <b-alert show variant="danger" v-if="isLoginError">아이디 또는 비밀번호를 확인해주세요.</b-alert>
                        <b-form-group label-for="userId">
                          <b-form-input
                            id="userId"
                            v-model="user.userId"
                            required
                            placeholder="아이디 입력...."
                            @keyup.enter="loginconfirm"
                          ></b-form-input>
                        </b-form-group>
                        <b-form-group  label-for="userPassword">
                          <b-form-input
                            type="password"
                            id="userPassword"
                            v-model="user.userPassword"
                            required
                            placeholder="비밀번호 입력...."
                            @keyup.enter="loginconfirm"
                          ></b-form-input>
                        </b-form-group>
                        <b-button type="button" variant="primary" class="m-1" style="width: 97%;" @click="loginconfirm">로그인</b-button>
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

      <h1 class="animate__animated animate__fadeIn bbb">
        <mark class="highlight-bottom" style="color: white;">인기 여행지</mark>
      </h1>
      <div class="d-flex justify-content-around mt-4 animate__animated animate__fadeIn bbb">
        <div class="row">
          <div class="col-4">
            <b-card
              :title="attractions[0].title"
              :img-src="attractions[0].first_image || require('@/assets/img/jeju2.jpeg')"
              img-alt="대체 이미지"
              img-top
              img-height="230px"
              tag="article"
              style="max-width: 24rem; height: 24rem;"
              class="mb-2"
            >
              <b-card-text>
                {{attractions[0].addr1}}
              </b-card-text>
            </b-card>
          </div>
          <div class="col-4">
            <b-card
              :title="attractions[1].title"
              :img-src="attractions[1].first_image || require('@/assets/img/busan1.jpeg')"
              img-alt="Image"
              img-top
              img-height="230px"
              tag="article"
              style="max-width: 24rem;  height: 24rem;"
              class="mb-2"
            >
              <b-card-text>
                {{attractions[1].addr1}}
              </b-card-text>
            </b-card>
          </div>
          <div class="col-4">
            <b-card
              :title="attractions[2].title"
              :img-src="attractions[2].first_image || require('@/assets/img/busan3.jpeg')"
              img-alt="Image"
              img-top
              img-height="230px"
              tag="article"
              style="max-width: 24rem;  height: 24rem;"
              class="mb-2"
            >
              <b-card-text>
                {{attractions[2].addr1}}
              </b-card-text>
            </b-card>
          </div>
        </div>
      </div>   

      <h1 class="animate__animated animate__fadeIn ccc mt-2">
        <mark class="highlight-bottom" style="color: white;">인기 후기</mark>
      </h1>
      <div class="row justify-content-center mb-3 mt-3 animate__animated animate__fadeIn ccc">
          <!-- <div class="overflow-auto transparent-background " style="width: 79%;"> -->
            <div class="overflow-auto mt-2 mb-2 test " style="width: 100%; height: 53%;  ">
                <div class="d-flex flex-column bd-highlight">
                    <div class="p-2 bd-highlight roundlist d-flex justify-content-center" style="background-color: rgba(255, 255, 255, 0.5); width: 100%;">
                    <b-table 
                    id="my-table"
                    :items="items"
                    :fields="fields"
                    :per-page="perPage"
                    :current-page="currentPage"
                    style="width: 95%;"
                    small
                    >
                        <template #cell(subject)="row">
                            <router-link style="color: rebeccapurple;" :to="{ name: 'reviewview', params:{articleNo : row.item.articleNo} }">{{ row.value }}</router-link>
                        </template>
                    </b-table>
                    </div>
        
                </div>
            <!-- </div> -->
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
      user: {
        userId: null,
        userPassword: null,
      },
      slide: 0,
      sliding: null,
      attractions: [],
      items: [],
        fields: [
            {
                key:'userId',
                label:'작성자'
            },
            {
                key:'subject',
                label:'제목'
            },
            {
                key:'registerTime',
                label:'등록일'
            },
            {
                key:'hit',
                label:'조회수'
            },
            {
                key:'heart',
                label:'좋아요'
            },
        ],
    };
  },
  created() {
    
    http.get('/attraction/rank')
    .then(response =>{
      console.log(response.data.attractions);
      this.attractions=response.data.attractions;
    }),

    http.get('/api/review/best')
        .then(response => {
            this.items = response.data.data;
        })
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo","notice"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo", "getNotice","memberUpdate","memberDelete"]),
    async mDelete() {
      if(confirm("회원 탈퇴 하시겠습니까?"))
      await this.memberDelete(this.userInfo.userId);
    },
    async loginconfirm() {
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