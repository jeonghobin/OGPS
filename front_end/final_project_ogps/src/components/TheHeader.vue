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

            <!-- Right aligned nav items -->
            <b-navbar-nav class="ml-auto">
                <b-nav-item disabled>
                    ssafy 님
                </b-nav-item>
                <div>
                    <b-button v-b-toggle.sidebar-right><img src="@/assets/img/alarm.png" width="20px" height="20px"></b-button>
                    <b-sidebar id="sidebar-right" title="Sidebar" right shadow>
                    <div class="px-3 py-2">
                        <p>
                        Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis
                        in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.
                        </p>
                        <b-img src="https://picsum.photos/500/500/?image=54" fluid thumbnail></b-img>
                    </div>
                    </b-sidebar>
                </div>
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