<template>
    <div>
        <div class="d-flex justify-content-center animate__animated animate__backInDown">
            <h1 class="mt-2"><mark class="highlight-bottom">날씨!!</mark></h1>
        </div>
        <div class="row ml-0 mr-0 mt-4 mb-4" style="padding-left: 130px; padding-right: 130px;">
            <div class="col-6">
                <div id="map" class="roundmap" style="width: 100%; height: 900px"></div>
            </div>
            <div class="col-6">
                <div class="overflow-auto transparent-background roundlist" style="height: 100%;">
                    <!-- http://api.openweathermap.org/data/2.5/forecast?id=524901&appid=738c7a052467891d557b4a94a3c4e388 -->
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'AppWeather',
    components: {},
    data() {
        return {
            map:null,
            message: '',
            positions:[],
            weathers:[],
            city:[
                {lat:37.566826,lon:126.9786567,citydata:{}},
                {lat:37.40864282648822,lon:126.65071862847725,citydata:{}},
                {lat:37.2911,lon:127.0089,citydata:{}},
                {lat:36.3519957815787,lon:127.39131469478555,citydata:{}},
                {lat:37.15818414766273,lon:128.928560966107,citydata:{}},
                {lat:37.791688035246636,lon:128.82867301427635,citydata:{}},
                {lat:35.871148697228875,lon:128.61345034272617,citydata:{}},
                {lat:35.5372,lon:129.3167,citydata:{}},
                {lat:35.185997613083536,lon:129.0662809358643,citydata:{}},
                {lat:35.90493196781132,lon:127.17357575637105,citydata:{}},
                {lat:35.166611792579545,lon:126.84603104436039,citydata:{}},
                {lat:34.823630139082525,lon:126.39766650967137,citydata:{}},
                {lat:33.5097,lon:126.5219,citydata:{}},
            ],
            // Mokpo :[],
        };
    },
    created() {
        const APIkey="738c7a052467891d557b4a94a3c4e388";
        for(var i=0;i<this.city.length;i++){
            let lat = this.city[i].lat;
            let lon = this.city[i].lon;
            
            axios.get(`https://api.openweathermap.org/data/2.5/forecast?lat=${lat}&lon=${lon}&appid=${APIkey}&units=metric`)
            .then(response =>{
                // console.log(response.data);
                console.log(response.data.city.name);
                
                // this.city[i].citydata=response.data;
            })
        }
    },
    mounted(){
        if (window.kakao && window.kakao.maps) {
        // 카카오 객체가 있고, 카카오 맵그릴 준비가 되어 있다면 맵 실행
        this.loadMap();
        } else {
        // 없다면 카카오 스크립트 추가 후 맵 실행
        this.loadScript();
        }
    },
    methods: {
        wait(sec) {
            let start = Date.now(), now = start;
            while (now - start < sec * 1000) {
                now = Date.now();
            }
        },
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
                center: new window.kakao.maps.LatLng(37.450701, 126.570667), // 지도의 중심좌표
                level: 13, // 지도의 레벨(확대, 축소 정도)
            };

            this.map = new window.kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴
            var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
            var imageSize = new window.kakao.maps.Size(24,35);
            var markerImage = new window.kakao.maps.MarkerImage(imageSrc,imageSize);
            
            var poss = [
            {
            // 서울
            lating: new window.kakao.maps.LatLng(37.566826, 126.9786567),
            },
            {
            // 인천
            lating: new window.kakao.maps.LatLng(37.40864282648822, 126.65071862847725),
            },
            {
            // 수원
            lating: new window.kakao.maps.LatLng(37.2911, 127.0089),
            },
            {
            // 대전
            lating: new window.kakao.maps.LatLng(36.3519957815787, 127.39131469478555),
            },
            {
            // 태백
            lating: new window.kakao.maps.LatLng(37.15818414766273, 128.928560966107),
            },
            {
            // 강릉
            lating: new window.kakao.maps.LatLng(37.791688035246636, 128.82867301427635),
            },
            {
            // 대구
            lating: new window.kakao.maps.LatLng(35.871148697228875, 128.61345034272617),
            },
            {
            // 울산
            lating: new window.kakao.maps.LatLng(35.5372, 129.3167),
            },
            {
            // 부산
            lating: new window.kakao.maps.LatLng(35.185997613083536, 129.0662809358643),
            },
            {
            // 전주
            lating: new window.kakao.maps.LatLng(35.90493196781132, 127.17357575637105),
            },
            {
            // 광주
            lating: new window.kakao.maps.LatLng(35.166611792579545, 126.84603104436039),
            },
            {
            // 목포
            lating: new window.kakao.maps.LatLng(34.823630139082525, 126.39766650967137),
            },
            {
            // 제주도
            lating: new window.kakao.maps.LatLng(33.5097, 126.5219),
            },
            ];

            this.positions = poss;
            
            for(var i=0;i<this.positions.length;i++){
                var marker = new window.kakao.maps.Marker({
                    // map: this.map,
                    position:this.positions[i].lating,
                    image: markerImage,
                });
                marker.setMap(this.map);
            }
        },
    },
};
</script>

<style scoped>
.transparent-background {
    background-color: rgba(255,255,255,0.5);
     /* 0부터 1까지의 값을 사용하며, 1에 가까울수록 불투명해집니다. */
}
.abc{
    padding-right: 130px;
}
.bbc{
    padding-left: 130px;
}
.roundmap{
    border-radius: 30px;
}
.roundlist{
    border-radius: 30px;
}
</style>