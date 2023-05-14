<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<c:if test="${post eq null}">
	<script>
		alert("글이 삭제되었거나 부적절한 URL 접근입니다.");
		location.href = "${root}/board?action=information";
	</script>
</c:if>
<div class="row justify-content-center">
	<div class="col-lg-8 col-md-10 col-sm-12">
		<h2 class="my-3 py-3 shadow-sm bg-light text-center">
			<mark class="sky">글보기</mark>
		</h2>
	</div>
	<div class="col-lg-8 col-md-10 col-sm-12">
		<div class="row my-2">
			<h2 class="text-secondary px-5">${post.article_no}.
				${post.subject}</h2>
		</div>
		<div class="row">
			<div class="col-md-8">
				<div class="clearfix align-content-center">
					<img class="avatar me-2 float-md-start bg-light p-2"
						src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
					<p>
						<span class="fw-bold">${post.user_id}</span> <br /> <span
							class="text-secondary fw-light"> ${post.register_time} 조회
							: ${post.hit} </span>
					</p>
				</div>
			</div>
			<div class="divider mb-3"></div>
			<div class="text-secondary">${post.content}</div>
			<div class="divider mt-3 mb-3"></div>
			<div class="d-flex justify-content-end">
				<button type="button" id="btn-list"
					class="btn btn-outline-primary mb-3">글목록</button>
				<c:if test="${userInfo.user_id eq post.user_id}">
					<button type="button" id="btn-mv-modify"
						class="btn btn-outline-success mb-3 ms-1">글수정</button>
					<button type="button" id="btn-delete"
						class="btn btn-outline-danger mb-3 ms-1">글삭제</button>
				</c:if>
			</div>
		</div>
	</div>

	<!-- 댓글  -->
	<div class="col-lg-8 col-md-10 col-sm-12">
		<h5>댓글 (${csize})</h5>
		<hr>
		<form class="form-comment" id="form-comment" action="" method="post">
			<div class="d-flex">
				<textarea class="form-control mb-3" id="comment" name="comment"
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
									<h5 class="col-1" style="display: inline">${cpost.user_id}</h5>
									${cpost.memo_time}
									<c:if test="${userInfo.user_id eq cpost.user_id}">
										<a class="col-1"
											href="${root}/free/comdelete?article_no=${post.article_no}&comment_no=${cpost.comment_no}">삭제</a>
									</c:if>
								</div>
								<h6>${cpost.comment}</h6>
							</div>
						</div>
					</form>
				</div>
			</c:forEach>
		</div>
	</div>

</div>
<script>
	document.querySelector("#btn-comment").addEventListener("click",
					function() {
						if (!document.querySelector("#comment").value) {
							alert("댓글 입력!!");
							return;
						}else if (${userInfo.user_id eq null}) {
							alert("댓글을 입력하려면 로그인 먼저 해주세요!!");
							return;
						} else {
							let form = document.querySelector("#form-comment");
							form
									.setAttribute("action",
											"${root}/free/comwrite?article_no=${post.article_no}&user_id=${userInfo.user_id}");
							form.submit();
						}
					});

	document.querySelector("#btn-list").addEventListener("click", function() {
		location.href = "${root}/free/list?pgno=1&key=&word=";
	});

	document.querySelector("#btn-mv-modify")
			.addEventListener(
					"click",
					function() {
						location.href = "${root}/free/modify?article_no=${post.article_no}";
					});

	document.querySelector("#btn-delete").addEventListener("click", function() {
		location.href = "${root}/free/delete?article_no=${post.article_no}";
	});
</script>
<%@ include file="../common/footer.jsp"%>
<!-- 스크립트 로드 -->
<script type="text/javascript" src="${root}/js/main.js"></script>
</body>
</html>