<template>
  <div>
  
      <img :src="fileObjectUrl" width="100px" height="100px" alt="이미지">
      <!-- <div  class="col-1" v-for="image in images" :key="image.imageUrl">
        <img :src="image.imageUrl" width="100px" height="100px" alt="이미지">
      </div> -->
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'ReviewView',
  data() {
    return {
      articleNo: 0,
      images: [],
      fileObjectUrl: '',
    };
  },
  methods: {

  },
  created() {
    this.articleNo = this.$route.params.articleNo;
    axios.get(`http://localhost:9001/api/review/${this.articleNo}`, {
      responseType: "blob"
    })
      .then(response => {
        console.log(response.data);
         // 다운로드(서버에서 전달 받은 데이터) 받은 바이너리 데이터를 blob으로 변환합니다.
        const blob = new Blob([response.data]);
        // 특정 타입을 정의해야 경우에는 옵션을 사용해 MIME 유형을 정의 할 수 있습니다.
        // const blob = new Blob([this.content], {type: 'text/plain'})

        // blob을 사용해 객체 URL을 생성합니다.
        this.fileObjectUrl = window.URL.createObjectURL(blob);
      })
      .catch(error => {
        console.error(error);
      });
  }
};
</script>