<template>
    <div>
        <b-navbar toggleable="lg" type="light" variant="light">
        <b-navbar-brand href="#" to="/">O! G.P.S</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav >
                <b-nav-item href="#" to="/">전국 관광지</b-nav-item>
                <b-nav-item href="#" to="/fdfd">공지사항</b-nav-item>
                <b-nav-item>그룹플랜</b-nav-item>
                <b-nav-item>여행후기</b-nav-item>
                <b-nav-item>날씨</b-nav-item>
            </b-navbar-nav>

            <!-- after login -->
            <b-navbar-nav class="ml-auto" v-if="userInfo">
                <b-nav-item class="align-self-center">
                    {{ userInfo.username }}({{ userInfo.userid }})님 환영합니다.
                </b-nav-item>
                <b-nav-item class="align-self-center">
                    <router-link :to="{ name: 'mypage' }" class="link align-self-center">내정보보기</router-link>
                </b-nav-item>
                <b-nav-item class="align-self-center link" @click.prevent="onClickLogout">로그아웃</b-nav-item>
                <b-nav-item>
                    <b-button v-b-toggle.sidebar-right><img src="@/assets/img/alarm.png" width="20px" height="20px"></b-button>
                    <b-sidebar id="sidebar-right" title="Sidebar" right shadow>
                    <div class="px-3 py-2">
                        <p>
                        Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis
                        </p>
                        <b-img src="https://picsum.photos/500/500/?image=54" fluid thumbnail></b-img>
                    </div>
                    </b-sidebar>
                </b-nav-item>
            </b-navbar-nav>
            <!-- before login -->
            <b-navbar-nav class="ml-auto" v-else>
                <router-link :to="{ name: 'join' }" class="link"> <b-icon icon="person-circle"></b-icon> 회원가입</router-link>
                <router-link :to="{ name: 'login' }" class="link"> <b-icon icon="key"></b-icon> 로그인 </router-link>
            </b-navbar-nav>
        </b-collapse>
    </b-navbar>
    </div>
</template>

<script>
export default {
    name: 'TheHeader',
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
    
</style>