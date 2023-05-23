<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col class="d-flex justify-content-center mr-5">
        <b-alert variant="secondary" show style="width: 70%;"><h3>회원정보 수정</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3 mb-4 ml-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-model="isRegisterError.state">{{isRegisterError.info}}</b-alert>
            <b-form-group label="이름:" label-for="userName">
              <b-form-input
                id="userName"
                v-model="userInfo.userName"
                required
                placeholder="이름 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="아이디:" label-for="userId">
              <b-form-input
                id="userId"
                v-model="userInfo.userId"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
                readonly
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userPassword">
              <b-form-input
                type="password"
                id="userPassword"
                v-model="userInfo.userPassword"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일:" label-for="userEmail">
              <b-form-input
                type="email"
                id="userEmail"
                v-model="userInfo.userEmail"
                required
                placeholder="이메일 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button type="button" variant="primary" class="m-1" @click="update" style="width: 20%;">정보수정</b-button>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserModify",
  data() {
    return {
      isRegisterError: {
        state: false,
        info: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["duplicate","userInfo"])
  },
  methods: {
    ...mapActions(memberStore, ["memberUpdate"]),
    async update() {
      if (this.userInfo.userName === null) {
        this.isRegisterError.state = true;
        this.isRegisterError.info = "'이름'을 작성해주세요";
      }else if (this.userInfo.userPassword === null) {
        this.isRegisterError.state = true;
        this.isRegisterError.info = "'비밀번호'를 작성해주세요";
      }else if (this.userInfo.userEmail === null) {
        this.isRegisterError.state = true;
        this.isRegisterError.info = "'이메일'을 작성해주세요";
      }else{
        this.isRegisterError.state = false;
        await this.memberUpdate(this.userInfo);
      } 
    },
    movePage() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style></style>

