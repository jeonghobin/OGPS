<template>
  <div>
  {{ articleNo }}
   <!-- <div v-for="(imageData, index) in images" :key="index"  >
    <img :src="fileObjectUrl" alt="Image" :key="index" />
  </div>  -->
      <img :src="fileObjectUrl1" width="100px" height="100px" alt="이미지"> 
      <img :src="fileObjectUrl2" width="100px" height="100px" alt="이미지"> 
      <img :src="fileObjectUrl3" width="100px" height="100px" alt="이미지"> 
      <img :src="fileObjectUrl4" width="100px" height="100px" alt="이미지"> 
      <img :src="fileObjectUrl5" width="100px" height="100px" alt="이미지"> 
      <!-- <img v-for="(imageData, index) in fileInfo" :src="getImageUrl(imageData)" alt="Image" :key="index" />  -->
  </div>
</template>

<script>
import http from '@/api/http'
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
      images: [],
      fileInfo: [],
      size:0,
    };
  },
  methods: {

  },
  created() {
    //getBoard
    this.articleNo = this.$route.params.articleNo;
    http.get(`/api/review/${this.articleNo}`)
      .then(response => {
        console.log(response.data);
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
            
                if (response.data.size > 0) {
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
                } else {
                    if(i==0)
                    this.fileObjectUrl1 = '@/assets/img/main_1.png';
                    if(i==1)
                    this.fileObjectUrl2 = '@/assets/img/main_1.png';
                    if(i==2)
                    this.fileObjectUrl3 = '@/assets/img/main_1.png';
                    if(i==3)
                    this.fileObjectUrl4 = '@/assets/img/main_1.png';
                    if(i==4)
                    this.fileObjectUrl5 = '@/assets/img/main_1.png';
                }
                this.images[i] = this.fileObjectUrl1;
            })
            
          }
        

        })
        .catch(error => {
          console.error(error);
      });


  



  
  }
};
</script>