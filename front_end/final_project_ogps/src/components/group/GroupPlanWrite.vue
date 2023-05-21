<template>
    <div>
        <div class="d-flex justify-content-center animate__animated animate__backInDown">
                <h1 class="mt-2"><mark class="highlight-bottom">계획 작성하기</mark></h1>
        </div>
        <div class="row mr-0 ml-0">
            <div class="col-3">
                <div class="mt-3 mb-3 roundlist animate__animated animate__backInUp" style="height: 900px; background-color: rgba(255, 255, 255, 0.5); padding-top: 100px;"></div>
            </div>    
            <div class="col-4 pt-2 pb-2 bbc animate__animated animate__backInUp">
            
                <div class="mr-0 ml-0">
                    <div id="map" class="roundmap" style="width: 100%; height: 900px"></div>
                </div>
            </div>
            <div class="col-5 abc animate__animated animate__backInUp ">
                <div class="mt-3 mb-3 roundlist" style="height: 900px; background-color: rgba(255, 255, 255, 0.5); padding-top: 100px;"></div>
            </div>
        </div>
        <div class="d-flex justify-content-end animate__animated animate__backInRight">
            <button class="btn btn-primary mb-3 mr-3" type="button">작성</button>
            <button class="btn btn-success mb-3 mr-3" type="button">목록으로</button>
        </div>
    </div>
</template>

<script>
export default {
    name: 'GroupPlanWrite',
    components: {},
    data() {
        return {
            message: '',
        };
    },
    created() {},
    methods: {
        loadScript() {
            const script = document.createElement("script");
            script.src =
                "//dapi.kakao.com/v2/maps/sdk.js?appkey=50edb2dcb6a758bf6c0338bb4e845bac&autoload=false"; // &autoload=false api를 로드한 후 맵을 그리는 함수가 실행되도록 구현
            script.onload = () => window.kakao.maps.load(this.loadMap); // 스크립트 로드가 끝나면 지도를 실행될 준비가 되어 있다면 지도가 실행되도록 구현

            document.head.appendChild(script); // html>head 안에 스크립트 소스를 추가
            },

        loadMap() {
            const container = document.getElementById("map"); // 지도를 담을 DOM 영역
            const options = {
                // 지도를 생성할 때 필요한 기본 옵션
                center: new window.kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                level: 3, // 지도의 레벨(확대, 축소 정도)
            };

            this.map = new window.kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴
		},
    },
    mounted(){
        if (window.kakao && window.kakao.maps) {
        // 카카오 객체가 있고, 카카오 맵그릴 준비가 되어 있다면 맵 실행
        this.loadMap();
        } else {
        // 없다면 카카오 스크립트 추가 후 맵 실행
        this.loadScript();
        }
    }
};
</script>

<style scoped>
.roundmap{
    border-radius: 30px;
    margin-top: 10px;
}
.roundlist{
    border-radius: 30px;
}
.highlight-bottom {
    background: linear-gradient(to top, rgb(207, 250, 219) 18%, transparent 40%);
    color: rgb(218, 247, 223);
}
.animate__animated.animate__backInUp{
    /* --animate-duration: 2s; */
    animation-delay: 1s;
}

</style>