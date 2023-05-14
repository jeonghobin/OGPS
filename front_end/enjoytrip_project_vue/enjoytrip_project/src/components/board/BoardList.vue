<template>
  <div class="container-fluid mt-5">
    <div class="row">
      <div class="row text-center">
        <div class="col m-5">
          <h1
            style="font-weight: bold; text-decoration-line: underline; text-decoration-thickness: 6px; text-decoration-color: #a207df">
            공지 확인해요!!!</h1>
        </div>
      </div>
      <div class="row justify-content-between ps-2 pe-2 ms-2 ms-2">
        <div class="col-auto">
          <a type="button" id="write-button" class="btn btn-outline-primary" style="font-weight: bold"
            href="/board/write"> 글쓰기</a>
        </div>
        <div class="col-auto">
          <form id="form-search" ref="formsearch">
            <input type="hidden" name="action" value="information" />
            <input type="hidden" name="pgno" value="1" />
            <div class="row pe-2 me-2">
              <select v-model="key" class="col form-select me-1" name="key" id="key" aria-label=".form-select example">
                <option selected value="">검색조건</option>
                <option value="article_no">글번호</option>
                <option value="subject">제목</option>
                <option value="user_id">작성자</option>
              </select> <input type="text" name="word" id="word" placeholder="검색어입력..." v-model="search" class="col form-control me-1" />
              <button @click="searchList" type="button" id="btn-search" class="col btn btn-outline-secondary me-2">검색</button>
            </div>
          </form>
        </div>

        <div class="row mt-3">
          <div class="d-flex justify-content-center">
            <table class="table table-striped table-bordered text-center mb-5">
              <thead>
                <tr>
                  <th scope="col">글번호</th>
                  <th scope="col">제목</th>
                  <th scope="col">작성자</th>
                  <th scope="col">작성일</th>
                  <th scope="col">조회수</th>
                </tr>
              </thead>
              <tbody>
                <board-list-item v-for="article in articles" :key="article.article_no"
                  :article="article"></board-list-item>
              </tbody>
            </table>
          </div>

          <form id="form-param" method="get" action="">
            <input type="hidden" id="p-action" name="action" value="">
            <input type="hidden" id="p-pgno" name="pgno" value="">
            <input type="hidden" id="p-key" name="key" value="">
            <input type="hidden" id="p-word" name="word" value="">
          </form>
        </div>
      </div>
    </div>
    <div class="d-flex justify-content-center">
					<nav aria-label="Page navigation example" v-html="navigator"></nav>
				</div>
    <div class="row"></div>
  </div>
</template>

<script>
import http from "@/api/http";
import BoardListItem from "@/components/board/BoardListItem.vue";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: [],
      navigator : "",
      pgno : 0,
      key : 0,
      word : 0,
      search : ""
    };
  },
  created() {
    // 비동기
    // TODO : 글목록 얻기.
    http.get(`/api/notice?pgno=1&key=&word=`).then((response) => {
      console.log(response)
      this.navigator = response.data.navigation.navigator;
      this.articles = response.data.posts;
      this.pgno = response.data.pgno;
      this.key = response.data.key;
      this.word = response.data.word;
    });

  },
  methods: {
    movePage() {
      this.$router.push({ name: "boardwrite" });
    },
    searchList(){
        http.get(`/api/notice?pgno=1&key=${this.key}&word=${this.search}`).then((response) => {
        console.log(response)
        this.navigator = response.data.navigation.navigator;
        this.articles = response.data.posts;
        this.pgno = response.data.pgno;
        this.key = response.data.key;
        this.word = response.data.word;
      });
    }
  },
};
</script>

<style></style>
