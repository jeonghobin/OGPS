<!-- <template>
  <div>
    <div class="information">
      <p>이미지를 업로드하세요.</p>
      <button @click="$refs.fileRef.click">선택</button>
      <input type="file" @change="selectFile" multiple accept="image/*" ref="fileRef" hidden/>
    </div>
    <div class="images" v-if="files.length > 0">
      <div v-for="fileName in files" :key="fileName" class="image">
        <img :src="`${backendUrl}/image/${fileName}`" alt="이미지">
      </div>
    </div>
  </div>
</template> -->

<template>
    <div>
      <div>
        <input type="file" multiple accept="imageUrl/*" ref="fileInput" @change="handleFileUpload">
        <button @click="uploadImage">업로드</button>
      </div>
      <div v-if="imageUrl">
        <h1>{{ imageUrl.saveFile }}</h1>
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
  
          axios.post('http://localhost:9001/api/review', formData, {
                header: {
                    'Content-Type':'multipart/form-data'
                }
            })
            .then(response => {
              this.imageUrl = response.data.imageUrl;
              console.log(this.imageUrl);
            })
            .catch(error => {
              console.error(error);
            });
        }
      }
    }
  };
  </script>