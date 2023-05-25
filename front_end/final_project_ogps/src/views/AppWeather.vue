<template>
    <div>
        <div class="d-flex justify-content-center animate__animated animate__backInDown">
            <h1 class="mt-2"><mark class="highlight-bottom">날씨!!</mark></h1>
        </div>
        <div class="row ml-0 mr-0 mt-4 mb-4" style="padding-left: 130px; padding-right: 130px;">
            <div class="col-8">
                <div id="map" class="roundmap" style="width: 100%; height: 900px"></div>
            </div>
            <div class="col-4">
                <div class="overflow-auto transparent-background roundlist d-flex justify-content-center"
                    style="height: 900px;">
                    <div class="pl-5 pr-5 pt-3 pb-2" v-if="clickName" :key="clickName">
                        <div class="row transparent-background roundlist">
                            <h1 class="d-flex justify-content-start ml-4 mt-2" style="color: rgb(67, 83, 175);">현재 {{ clickName }}날씨</h1>
                            <div class="row ml-3">
                                <div style="width: 45%;">
                                    <img :src="`https://openweathermap.org/img/w/${currentWeather.icon}.png`" width="200px"
                                        height="200px" />
                                </div>
                                <div class="col mt-4" style="display: flex; flex-direction: column; align-items: center;">
                                    <h1 style="font-size: 50px;">{{ currentWeather.temp }}°C</h1>
                                    <h3>{{ currentWeather.temp_min }}°C / {{ currentWeather.temp_max }}°C</h3>
                                </div>
                            </div>
                        </div>

                        <div v-for="(item) in city" :key="item">
                            <div v-if="item.krName === clickName">
                                <h2 class="d-flex justify-content-start mt-4 mb-3" style="color: rgb(67, 83, 175);">{{ clickName }} 주간 날씨</h2>
                                <div v-for="(day) in Days" :key="day" class="row">
                                    {{ item.citydata.list[day].dt_txt.slice(0, 10) }}
                                    <div class="col-3 d-flex justify-content-center">
                                        <img :src="`https://openweathermap.org/img/w/${item.citydata.list[day].weather[0].icon}.png`"
                                            width="55px" height="55px" />
                                    </div>
                                    <div class="col-6">
                                        {{ item.citydata.list[day].main.temp_min }}°C / {{ item.citydata.list[day].main.temp_max }}°C
                                        <!-- 상태 : {{ item.citydata.list[i].weather[0].main }} -->
                                    </div>
                                    <hr style="width: 100%;">
                                </div>
                            </div>
                        </div>
                    </div>
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
            map: null,
            message: '',
            positions: [],
            weathers: [],
            componentName: '',
            Days: [0,8,16,24,32,39],
            city: [
                { cityName: "Daejeon", krName: "대전", citydata: {} },
                {cityName:"Seoul",krName:"서울",citydata:{}},
                {cityName:"Daegu",krName:"대구",citydata:{}},
                {cityName:"Busan",krName:"부산",citydata:{}},
                {cityName:"Jeju City",krName:"제주",citydata:{}},
                {cityName:"Jeonju",krName:"전주",citydata:{}},
                {cityName:"Gwangju",krName:"광주",citydata:{}},
                {cityName:"Mokpo",krName:"목포",citydata:{}},
                {cityName:"Gangneung",krName:"강릉",citydata:{}},
                {cityName:"Yeongju",krName:"영주",citydata:{}},
                {cityName:"Chungju",krName:"충주",citydata:{}},
            ],
            markers: [],
            clickName: '',
            currentWeather: {},
        };
    },
    created() {
        // const APIkey="738c7a052467891d557b4a94a3c4e388";//hobin
        const APIkey = "5c626f0f896eb28affce874e1a543f07"; //somyeong

        // Promise.all()을 사용하여 비동기 호출을 처리
        const requests = this.city.map((cityItem) => {
            return axios.get(
                `https://api.openweathermap.org/data/2.5/forecast?q=${cityItem.cityName}&appid=${APIkey}&units=metric`
            );
        });

        Promise.all(requests)
            .then((responses) => {
                // 모든 응답 데이터 처리
                responses.forEach((response, index) => {
                    const cityItem = this.city[index];

                    // 응답 데이터를 해당 cityItem에 할당
                    cityItem.citydata = response.data;

                    console.log("created get axios: ", cityItem.citydata);
                });
            }).then(this.makeMakers)
            .catch((error) => {
                console.error(error);
            });


    },
    mounted() {
        if (window.kakao && window.kakao.maps) {
            // 카카오 객체가 있고, 카카오 맵그릴 준비가 되어 있다면 맵 실행
            this.loadMap();
        } else {
            // 없다면 카카오 스크립트 추가 후 맵 실행
            this.loadScript();
        }
    },
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
                center: new window.kakao.maps.LatLng(37.450701, 126.570667), // 지도의 중심좌표
                level: 13, // 지도의 레벨(확대, 축소 정도)
            };

            this.map = new window.kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴

        },

        makeMakers() {
            this.positions = [];
            // console.log(this.map);
            this.city.forEach((area) => {
                console.log("created get makeMakers")
                let markerInfo = {
                    weather: area.citydata.list[2].weather,
                    time: area.citydata.list[2].dt_txt,
                    name: area.krName,
                    icon: area.citydata.list[2].weather[0].icon,
                    temp: area.citydata.list[2].main.temp,
                    latlng: new window.kakao.maps.LatLng(area.citydata.city.coord.lat, area.citydata.city.coord.lon),
                };
                this.positions.push(markerInfo);
            });

            for (var i = 0; i < this.markers.length; i++) {
                this.markers[i].setMap(null);
            }
            this.markers = [];
            var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
            for (i = 0; i < this.positions.length; i++) {
                var imageSize = new window.kakao.maps.Size(24, 35);
                var markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize);

                var marker = new window.kakao.maps.Marker({
                    // map: this.map,
                    position: this.positions[i].latlng,
                    title: this.positions[i].name,
                    image: markerImage,

                });
                marker.setMap(this.map);
                this.markers[i] = marker;

                // console.log(this.positions[i].img);
                var iwContent =
                    `<div style="padding: 5px; display: flex;">
                    <div style="width: 80px; height: 80px; border-radius: 50%; overflow: hidden;">
                        <img style="width: 100%; height: 100%; object-fit: cover;"
                        src="https://openweathermap.org/img/w/${this.positions[i].icon}.png"
                        onerror="this.src='https://via.placeholder.com/55x55'" width="55px" height="55px" />
                    </div>
                    <div style="margin-left: 10px;">
                        <div>
                        <h3>${this.positions[i].name}</h3>
                        </div>
                        <div>
                        <h4>${this.positions[i].temp}°C</h4>
                        </div>
                    </div>
                    </div>`;

                this.infowindow = new window.kakao.maps.InfoWindow({
                    content: iwContent
                });


                window.kakao.maps.event.addListener(marker, "mouseover", this.makeOverListener(this.map, marker, this.infowindow));
                window.kakao.maps.event.addListener(marker, 'mouseout', this.makeOutListener(this.infowindow))
                window.kakao.maps.event.addListener(marker, 'click', this.makeclickListener(marker))
            }
            this.map.setCenter(this.positions[0].latlng);
        },
        makeOverListener(map, marker, infowindow) {
            return function () {
                infowindow.open(map, marker);
            }
        },
        makeOutListener(infowindow) {
            return function () {
                infowindow.close();
            }
        },
        makeclickListener(marker) {
            return () => {

                this.clickName = marker.Gb;
                console.log(this.clickName);

                for (let i = 0; i < this.city.length; i++) {
                    if (this.city[i].krName === marker.Gb) {

                        this.clickName = marker.Gb;
                        console.log(this.clickName);

                        this.currentWeather = {
                            weather: this.city[i].citydata.list[2].weather,
                            time: this.city[i].citydata.list[2].dt_txt,
                            icon: this.city[i].citydata.list[2].weather[0].icon,
                            temp: this.city[i].citydata.list[2].main.temp,
                            temp_max: this.city[i].citydata.list[2].main.temp_max,
                            temp_min: this.city[i].citydata.list[2].main.temp_min,
                        }
                    }
                }

            };
        },
    },
};
</script>

<style scoped>
.transparent-background {
    background-color: rgba(255, 255, 255, 0.5);
    /* 0부터 1까지의 값을 사용하며, 1에 가까울수록 불투명해집니다. */
}

.abc {
    padding-right: 130px;
}

.bbc {
    padding-left: 130px;
}

.roundmap {
    border-radius: 30px;
}

.roundlist {
    border-radius: 30px;
}</style>