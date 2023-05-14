<template>
  <div class="container-fluid mt-5">
	<div class="row text-center">
		<div class="col m-5">
			<h1
				style="font-weight: bold; text-decoration-line: underline; text-decoration-thickness: 6px; text-decoration-color: rgb(189, 201, 255)">
				공지사항 수정!!</h1>
		</div>
	</div>

	<div class="d-flex justify-content-center">
		<div class="container-md">
			<div class="row justify-content-center">
				<div class="col-md-6 p-3 text-center align-self-center"
					style="background-color: rgb(229, 245, 255); border-radius: 10px; border: 1px solid rgb(195, 221, 255)">
					글 수정</div>
			</div>
			<div class="row mt-3 justify-content-center">
				<form class="post-form mb-2 col-md-6" id="form-modify" action="" method="post">
					<input type="hidden" name="postno" v-model="articleno">
					<div class="row mb-3">
						<label for="title" class="form-label">제목:</label> 
            <input
								type="text" class="form-control" id="title" name="title"
								placeholder="제목 입력..." v-model="article.subject" />
					</div>
					<div class="row mb-3">
						<label for="context" class="form-label">내용:</label>
						<textarea class="form-control" id="context" name="context" rows="8"
								style="overflow-y: scroll; resize: none" v-model="article.content"> </textarea>
					</div>
				</form>
			</div>
			<div class="row">
				<div class="d-lg-block col-3"></div>
				<div class="col-lg-6">
					<div class="d-flex ps-0">
						<button type="button" id="btn-modify-post"
							class="col-md-3 col-lg-2 btn btn-primary btn-sm me-1" @click="checkValue">글 수정</button>
						<button type="button"
							class="col-md-3 btn col-lg-2 btn-outline-danger btn-sm post-cancel" @click="moveList">목록</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</template>

<script>
import http from "@/api/http";

export default {
  name: "BoardModify",
  data() {
    return {
      articleno: Number,
      article: Object,
    };
  },
  methods: {
    // 입력값 체크하기 - 체크가 성공하면 modifyArticle 호출
    checkValue() {
      // 사용자 입력값 체크하기
      // 작성자아이디, 제목, 내용이 없을 경우 각 항목에 맞는 메세지를 출력
      let err = true;
      let msg = "";
        !this.article.subject &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.subject.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      // 만약, 내용이 다 입력되어 있다면 modifyArticle 호출
      else this.modifyArticle();
    },
    modifyArticle() {
      console.log(this.articleno + "번 글수정 하러가자!!!!");
      // 비동기
      // TODO : 글번호에 해당하는 글정보 수정.

      http.put(`/api/notice`, {
        "postno": this.articleno,
        "context": this.article.content,
        "title": this.article.subject
      }).then((response) => {
      console.log(response)
      this.moveList();
    });
    },

    moveList() {
      console.log("글목록 보러가자!!!");
      this.$router.push({ name: "boardlist" });
    },
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
};
</script>

<style></style>
