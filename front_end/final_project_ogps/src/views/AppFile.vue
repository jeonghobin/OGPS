<template>
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
</template>

<script>
export default {
  name: 'AppFile',
  components: {},
  data() {
    return {
      message: '',
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>