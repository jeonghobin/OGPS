<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-model="isRegisterError.state">{{isRegisterError.info}}를 확인해주세요.</b-alert>
            <b-form-group label="이름:" label-for="userName">
              <b-form-input
                id="userName"
                v-model="user.userName"
                required
                placeholder="이름 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="아이디:" label-for="userId">
              <b-form-input
                id="userId"
                v-model="user.userId"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userPassword">
              <b-form-input
                type="password"
                id="userPassword"
                v-model="user.userPassword"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일:" label-for="userEmail">
              <b-form-input
                type="email"
                id="userEmail"
                v-model="user.userEmail"
                required
                placeholder="이메일 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button type="button" variant="primary" class="m-1" @click="register">회원가입</b-button>
            <b-button type="button" variant="success" class="m-1" @click="movePage">로그인</b-button>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserRegister",
  data() {
    return {
      isRegisterError: {
        state: false,
        info: null,
      },
      user: {
        userName: null,
        userId: null,
        userPassword: null,
        userEmail: null,
      },
    };
  },
  methods: {
    ...mapActions(memberStore, ["memberRegister"]),
    async register() {
      if (this.user.userName === null) {
        this.isRegisterError.state = true;
        this.isRegisterError.info = "'이름'";
      }else if (this.user.userId === null) {
        this.isRegisterError.state = true;
        this.isRegisterError.info = "'아이디'";
      }else if (this.user.userPassword === null) {
        this.isRegisterError.state = true;
        this.isRegisterError.info = "'비밀번호'";
      }else if (this.user.userEmail === null) {
        this.isRegisterError.state = true;
        this.isRegisterError.info = "'이메일'";
      }else{
        this.isRegisterError.state = false;
        
        await this.memberRegister(this.user);
     
        if (this.isLogin) {
          this.$router.push({ name: "main" });
        }
      } 
    },
    movePage() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style></style>

