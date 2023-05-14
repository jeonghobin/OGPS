<template>
        <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="sky">글보기</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="row my-2">
            <h2 class="text-secondary px-5">{{ article.article_no }}. {{ article.subject }}</h2>
          </div>
          <div class="row">
            <div class="col-md-8">
              <div class="clearfix align-content-center">
                <img
                  class="avatar me-2 float-md-start bg-light p-2"
                  src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
                  width="10px"
                  height="50px"
                />
                <p>
                  <span class="fw-bold">{{ article.user_id }}</span> <br />
                  <span class="text-secondary fw-light"> {{ article.register_time }} 조회 : {{ article.hit }} </span>
                </p>
              </div>
            </div>
            <div class="divider mb-3"></div>
            <div class="text-secondary">
              {{ article.content }}
            </div>
            <div class="divider mt-3 mb-3"></div>
            <div class="d-flex justify-content-end">
              <button type="button" id="btn-list" class="btn btn-outline-primary mb-3" @click="moveList">
                글목록
              </button>
              <button type="button" id="btn-mv-modify" class="btn btn-outline-success mb-3 ms-1"  @click="moveModifyArticle">
                글수정
              </button>
              <button type="button" id="btn-delete" class="btn btn-outline-danger mb-3 ms-1" @click="deleteArticle">
                글삭제
              </button>
            </div>
          </div>
        </div>
      </div>
</template>

<script>
import http from "@/api/http";

export default {
  name: "BoardView",
  data() {
    return {
      articleno: Number,
      article: Object,
    };
  },
  created() {
    // 비동기
    // TODO : 글번호에 해당하는 글정보 얻기.
    this.articleno = this.$route.params.articleno;
    console.log(this.articleno);
    http.get(`/api/notice/${this.articleno}`).then((response) => {
      console.log(response)
      this.article = response.data.resValue;
    });
  },
  methods: {
    moveModifyArticle() {
      console.log("글수정 하러가자!!!");
      this.$router.push({ name: "boardmodify", params: { articleno: this.article.article_no } });
    },
    deleteArticle() {
      console.log("글삭제 하러가자!!!");
      http.delete(`/api/notice/${this.articleno}`).then((response) => {
        console.log(response);
      })
      .then(()=>{
        this.$router.push({ name: "boardlist" });
      });
      
    },
    moveList() {
      console.log("글목록 보러가자!!!");
      this.$router.push({ name: "boardlist" });
    },
  },
};
</script>

<style></style>
