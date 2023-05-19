<template>
    <div>
      <div>
        <input type="file" ref="fileInput" @change="handleFileUpload">
        <button @click="uploadImage">업로드</button>
      </div>
      <div v-if="imageUrl">
        <img :src="imageUrl" alt="이미지">
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        file: null,
        imageUrl: '',
      };
    },
    methods: {
      handleFileUpload(event) {
        this.file = event.target.files[0];
      },
      uploadImage() {
        if (this.file) {
          const formData = new FormData();
          formData.append('upfile', this.file);
          formData.append('userId', 123);
          formData.append('subject', '글');
          formData.append('content', '작성중');
          console.log(this.file);
  
          axios
            .post('http://localhost:9001/api/review', formData, {
                header: {
                    'Content-Type':'multipart/form-data'
                }
            })
            .then(response => {
              this.imageUrl = response.data.imageUrl;
            })
            .catch(error => {
              console.error(error);
            });
        }
      }
    }
  };
  </script>