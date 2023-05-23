<template>
  <div>
    <div class="d-flex justify-content-center">
        <h1 class="mt-2"><mark class="highlight-bottom">{{ article.subject }}</mark></h1>
    </div>
    <div class="d-flex justify-content-center mt-3 mb-3" style="height: 900px; margin-left: 130px; margin-right: 130px; padding-top: 10px;">
        <!-- 카드 -->
          <div class="middle-content" style="height: 92%; width: 700px; background-color: white;">
            <b-card style="width: 100%; height: 100%;">
              <div class="row pl-4 pt-1 justify-content-start">
                <img src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
                    width="40px" height="50px" style="border-radius: 100%;" />
                <h1 class="col-2">{{article.userId}}</h1>
              </div>
              <div v-if="fileInfo.length>=1">
                <b-carousel
                  id="carousel-1"
                  :interval="4000"
                  controls
                  indicators
                  background="#ababab"
                  img-width="800"
                  img-height="400px"
                  style="text-shadow: 1px 1px 2px #333;"
                >

                  <b-carousel-slide v-if="fileInfo.length>=1">
                    <template #img>
                      <img
                        class="d-block img-fluid"
                        style="width: 800px; height: 400px;"
                        :src="fileObjectUrl1"
                        alt="image slot"
                      >
                    </template>
                  </b-carousel-slide>

                  <b-carousel-slide v-if="fileInfo.length>=2">
                    <template #img>
                      <img
                        class="d-block img-fluid"
                        style="width: 800px; height: 400px;"
                        :src="fileObjectUrl2"
                        alt="image slot"
                      >
                    </template>
                  </b-carousel-slide>

                  <b-carousel-slide v-if="fileInfo.length>=3">
                    <template #img>
                      <img
                        class="d-block img-fluid"
                        style="width: 800px; height: 400px;"
                        :src="fileObjectUrl3"
                        alt="image slot"
                      >
                    </template>
                  </b-carousel-slide>

                  <b-carousel-slide v-if="fileInfo.length>=4">
                    <template #img>
                      <img
                        class="d-block img-fluid"
                        style="width: 800px; height: 400px;"
                        :src="fileObjectUrl4"
                        alt="image slot"
                      >
                    </template>
                  </b-carousel-slide>

                  <b-carousel-slide v-if="fileInfo.length>=5">
                    <template #img>
                      <img
                        class="d-block img-fluid"
                        style="width: 800px; height: 400px;"
                        :src="fileObjectUrl5"
                        alt="image slot"
                      >
                    </template>
                  </b-carousel-slide>
                </b-carousel>
              </div>

                <div v-if="userInfo" class="d-flex mt-2 ml-2 justify-content-start">
                  <div class="row">
                    <button
                      class="btn btn-lg"
                      @click="toggleHeart"
                      style="outline: none;"
                    >
                      <i class="fas fa-heart" :class="{ 'text-danger': isHearted }" style="color: gray;"></i>
                    </button>
                    <div class="middle-content" style="height: 100%;">{{ heatCnt }}</div>
                  </div>
                </div>

                <b-card-text style="margin-bottom: 5%; padding-left: 2%; padding-right: 3%; text-align: start; width: 100%; height: 18%; overflow: auto;">
                  {{ article.content }}
                </b-card-text>

                <div class="d-flex justify-content-end">
                      <b-button class="mr-2" @click="moveUpdate" href="#" variant="primary" style="width: 70px;">수정</b-button>
                      <b-button class="mr-2" @click="deleteReview" variant="danger" style="width: 70px;">삭제</b-button>
                      <b-button @click="movelist" variant="success" style="width: 70px;">목록</b-button>
                </div>
                
              </b-card>
          </div>
          <div class="middle-content" style="height: 92%; width: 500px; background-color: white;">
          <!-- 댓글  -->
	<div class="col-lg-8 col-md-10 col-sm-12">
		<h5>댓글: {{ comments.length }}</h5>
		<hr>
		<form v-if="userInfo" class="form-comment" id="form-comment" action="" method="post">
			<div class="d-flex">
				<textarea class="form-control mb-3" id="comment"
					rows="3" placeholder="댓글을 입력해 주세요"
					style="overflow-y: scroll; resize: none"></textarea>
				<button type="button" id="btn-comment"
					class="btn btn-outline-primary mb-3 ms-3">등록</button>
			</div>
		</form>

		<div>
			<c:forEach var="cpost" items="${cposts}">
				<div class="row-md-5">
					<form action="" method="post" class="form-manager">
						<div class="row">
						<hr>
							<div class="col-1 ">
								<img class="user-img mr=3 justify-content-end"
									src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
									width="50px" height="50px" style="border-radius: 100%" />
							</div>
							<div class="col-9">
								<div class="row">
									<h5 class="col-1" style="display: inline">{{ comments.userId }}</h5>
									{{ comments.comment }}
									<div v-if="userInfo.userId==article.userId">
										<a class="col-1"
											href="${root}/free/comdelete?article_no=${post.article_no}&comment_no=${cpost.comment_no}">삭제</a>
									</div>
								</div>
								<h6>{{ comments.comment }}</h6>
								<h6>{{ comments.memoTime }}</h6>
							</div>
						</div>
					</form>
				</div>
			</c:forEach>
		</div>
	</div>
          </div>
    </div>
  </div>
</template>

<script>
import http from '@/api/http'
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: 'ReviewView',
  data() {
    return {
      articleNo: 0,
      fileObjectUrl1: '',
      fileObjectUrl2: '',
      fileObjectUrl3: '',
      fileObjectUrl4: '',
      fileObjectUrl5: '',
      fileInfo: [],
      article: '',
      isHearted: false,
      comments: [],
      heatCnt:0
    };
  },
  methods: {
    toggleHeart() {
      this.isHearted = !this.isHearted;
      var state = 0;
      if (this.isHearted) {
        state = 1;
        this.heatCnt = this.heatCnt + 1;
      } else {
        this.heatCnt = this.heatCnt - 1;
      }
      console.log(this.userInfo.userId);
          http.post(`/api/rheart`,{
            userId: this.userInfo.userId,
            articleNo: this.articleNo,
            state: state,
          }).then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.error(error);
          });
    },
    movelist(){
      this.$router.push("/review");
    },
    moveUpdate(){
      this.$router.push({name:'reviewupdate',params:{
          articleNo: this.articleNo
      }})
    },
    deleteReview() {

      http.delete(`/api/review/${this.articleNo}`)
      .then(response => {
        console.log(response.data);
        this.$router.push("/review/list");
      })
      .catch(error => {
        console.error(error);
      });
    },
  },
  computed: {
    heartStatus() {
      return this.isHearted ? 'Hearted' : 'Not hearted';
    },
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    //getBoard,getComment
    this.articleNo = this.$route.params.articleNo;
    console.log(this.articleNo);
    http.get(`/api/review/${this.articleNo}`)
      .then(response => {
        console.log(response.data);
        this.article = response.data.review;
        
        this.comments = response.data.comment;
      })
      .catch(error => {
        console.error(error);
      });

      //get heart
      http.post(`/api/rheart/get`,{
        userId: this.userInfo.userId,
        articleNo: this.articleNo,
      }).then(response => {
        console.log(response.data);

        console.log(response.data.hstate)
        if (response.data.hstate == 1) 
          this.isHearted = true;
        else  this.isHearted = false;

        this.heatCnt = response.data.hcnt;
      })
      .catch(error => {
        console.error(error);
      });
    
      //get file
      http.get(`/api/rfile/${this.articleNo}`)
        .then(response => {
          this.fileInfo = response.data.fileInfo;
          // console.log(this.fileInfo.length);

          for (let i = 0; i < this.fileInfo.length; i++) {
            http.get(`/api/rfile/detail/${this.fileInfo[i].idx}`, {
                responseType: "blob"
            }).then(response => {
                console.log(response.data.size);
                    const blob = new Blob([response.data]);
                    if(i==0)
                    this.fileObjectUrl1 = window.URL.createObjectURL(blob);
                    if(i==1)
                    this.fileObjectUrl2 = window.URL.createObjectURL(blob);
                    if(i==2)
                    this.fileObjectUrl3 = window.URL.createObjectURL(blob);
                    if(i==3)
                    this.fileObjectUrl4 = window.URL.createObjectURL(blob);
                    if(i==4)
                    this.fileObjectUrl5 = window.URL.createObjectURL(blob);
            }) 
          }

        })
        .catch(error => {
          console.error(error);
      });
  }
  
};

</script>

<style scoped>
@import "~@fortawesome/fontawesome-free/css/all.css";

  .roundlist{
      border-radius: 30px;
  }
  .middle-content{
  display: flex;
  flex-flow: row nowrap;
  justify-content: center;
  align-items: center;
}

</style>