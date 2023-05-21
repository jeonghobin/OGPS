<template>
    <div>
      <div>
        <input type="file" multiple ref="fileInput" @change="handleFileUpload">
        <button @click="uploadImage">글 등록</button>
      </div>
      <div class="row">
        <div  class="col-1" v-for="image in images" :key="image.imageUrl">
          <img :src="image.imageUrl" width="100px" height="100px" alt="이미지">
        </div>
      </div>
    </div>
</template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'ReviewWrite',
    data() {
      return {
        articleNo: 0,
        imageUrl: '',
        images: [],
      };
    },
    methods: {
      handleFileUpload(event) {
        this.images = event.target.files;
        for (var i = 0; i < this.images.length; i++){
          //이미지 미리 보기
          this.images[i].imageUrl =  URL.createObjectURL(this.images[i]);
        }
      },
      uploadImage() {
        

        axios.post('http://localhost:9001/api/review', {
            userId: 123,
            subject: '테스트',
            content: '중임'
        }).then(response => {
            console.log(response.data.message);
            this.articleNo = response.data.articleNo;

          if (this.images) {
              for (var i = 0; i < this.images.length; i++) {
                const formData = new FormData();
                formData.append('upfile', this.images[i]);
                formData.append('articleNo', this.articleNo);

                axios.post('http://localhost:9001/api/rfile', formData, {
                  header: {
                    'Content-Type': 'multipart/form-data'
                  }
                  })
                  .then(response => {
                    console.log(response.data.message);
                  })
                  .catch(error => {
                    console.error(error);
                  });
              }
            }

        }).catch(error => {
              console.error(error);
        });


      }
    }
  };
  </script>