<template>
  
  <div class="footer">
    © footer입니다.
  </div>
  
  
</template>

<script>
export default {
  name: 'TheFooter',
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

<style scoped>
  .footer{
    display: flex;
    height: 50px;
    justify-content : center;
    /* 세로축 가운데 정렬 */
    align-items : center;
    background-color: white;
  }
</style>