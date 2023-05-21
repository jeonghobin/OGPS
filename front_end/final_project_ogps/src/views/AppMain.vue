<template>
    <div class="margin">
    <div class="d-flex justify-content-center">
      <div>
        <img src="@/assets/img/main_1.png" width="400px" height="175px">
        <h4 class="mt-3 mb-2" style="color:#ede9e9;">Our Group Plan's</h4>
      </div>
    </div>    
      <div class="d-flex justify-content-center mb-3 mt-1">
        <div class="col-4 pt-2 pb-2">
            <!-- 로그인 후 -->
            <div class="transparent-background ml-4 mt-3" v-if="userInfo" style="height: 90%; width: 90%;">
              <div class="overflow-auto"  >
                <h1>내 정보</h1>
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
          <div class="p-3">
            <b-carousel
              id="carousel-1"
              v-model="slide"
              :interval="4000"
              controls
              indicators
              background="#ababab"
              img-width="1024"
              img-height="480"
              style="text-shadow: 1px 1px 2px #333;"
              @sliding-start="onSlideStart"
              @sliding-end="onSlideEnd"
            >
              <!-- Text slides with image -->
              <b-carousel-slide
                caption="First slide"
                text="Nulla vitae elit libero, a pharetra augue mollis interdum."
                img-src="https://picsum.photos/1024/480/?image=52"
              ></b-carousel-slide>

              <!-- Slides with custom text -->
              <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=54">
                <h1>Hello world!</h1>
              </b-carousel-slide>

              <!-- Slides with image only -->
              <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=58"></b-carousel-slide>

              <!-- Slides with img slot -->
              <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
              <b-carousel-slide>
                <template #img>
                  <img
                    class="d-block img-fluid w-100"
                    width="1024"
                    height="480"
                    src="https://picsum.photos/1024/480/?image=55"
                    alt="image slot"
                  >
                </template>
              </b-carousel-slide>

              <!-- Slide with blank fluid image to maintain slide aspect ratio -->
              <b-carousel-slide caption="Blank Image" img-blank img-alt="Blank image">
                <p>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eros felis, tincidunt
                  a tincidunt eget, convallis vel est. Ut pellentesque ut lacus vel interdum.
                </p>
              </b-carousel-slide>
            </b-carousel>

            <!-- <p class="mt-4">
              Slide #: {{ slide }}<br>
              Sliding: {{ sliding }}
            </p> -->
          </div>
        </div>
      </div>

      <h1>인기 여행지</h1>
      <div class="d-flex justify-content-around mt-1">
        <div class="row">
          <div class="col-4">
            <b-card
              title="Card Title"
              img-src="https://picsum.photos/600/300/?image=25"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 24rem;"
              class="mb-2"
            >
              <b-card-text>
                Some quick example text to build on the card title and make up the bulk of the card's content.
              </b-card-text>

              <b-button href="#" variant="primary">Go somewhere</b-button>
            </b-card>
          </div>
          <div class="col-4">
            <b-card
              title="Card Title"
              img-src="https://picsum.photos/600/300/?image=25"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 24rem;"
              class="mb-2"
            >
              <b-card-text>
                Some quick example text to build on the card title and make up the bulk of the card's content.
              </b-card-text>

              <b-button href="#" variant="primary">Go somewhere</b-button>
            </b-card>
          </div>
          <div class="col-4">
            <b-card
              title="Card Title"
              img-src="https://picsum.photos/600/300/?image=25"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 24rem;"
              class="mb-2"
            >
              <b-card-text>
                Some quick example text to build on the card title and make up the bulk of the card's content.
              </b-card-text>

              <b-button href="#" variant="primary">Go somewhere</b-button>
            </b-card>
          </div>
        </div>
      </div>  


      <div class="row justify-content-center mb-3 mt-3">
          <div class="overflow-auto transparent-background" style="width: 79%;">
            <h1>인기 후기</h1>
          </div>
          <div class="overflow-auto transparent-background " style="width: 79%;">

          </div>
      </div>

    </div> 
</template>

<script>
import { mapState, mapActions } from "vuex";

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
      sliding: null
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        // this.$router.push({ name: "AppMain" });
      }
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

</style>