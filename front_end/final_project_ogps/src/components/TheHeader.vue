<template>
    <div>
        <b-navbar toggleable="lg" type="light" variant="light">
        <b-navbar-brand href="#">O! G.P.S</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav >
                <b-nav-item href="#">전국 관광지</b-nav-item>
                <b-nav-item href="#">공지사항</b-nav-item>
                <b-nav-item href="#">그룹플랜</b-nav-item>
                <b-nav-item href="#">여행후기</b-nav-item>
            </b-navbar-nav>

            <!-- Right aligned nav items -->
            <b-navbar-nav class="ml-auto">


                <b-nav-item-dropdown right>
                    <!-- Using 'button-content' slot -->
                    <template #button-content>
                        <em>내 정보</em>
                    </template>
                    <b-dropdown-item href="#">정보 수정</b-dropdown-item>
                    <b-dropdown-item href="#">회원 탈퇴</b-dropdown-item>
                </b-nav-item-dropdown>
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

<style scoped></style>