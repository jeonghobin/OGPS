<template>
    <div>
    <div class="d-flex justify-content-center animate__animated animate__backInDown">
        <h1 class="mt-2"><mark class="highlight-bottom">전국 관광지 조회</mark></h1>
    </div>    
    <div class="row mr-0 ml-0">
        <div class="col-6 pr-3 pt-2 pb-2 bbc animate__animated animate__backInLeft">
            <form class="mr-0 ml-0 mb-2 row">
                <select class="col-3 form-control mr-3" style="border-radius:10px; font-size:25px;" @change="showValue" ref="SearchArea">
                <option value="0" selected>시도</option>
                <option v-for="i in sido" :key="i.sido_code" :value="i.sido_code">{{i.sido_name}}</option>
                </select>
                <select class="col-3 form-control mr-3" style="border-radius:10px; font-size:25px;" ref="SearchTown">
                <option value="0" selected>군구</option>
                <option v-for="i in gugun" :key="i.gugun_code" :value="i.gugun_code">{{i.gugun_name}}</option>
                </select>
                <select class=" col-3 form-control mr-3" style="border-radius:10px; font-size:25px;" ref="SearchContentId">
                <option value="0" selected>관광지 유형</option>
                    <option value="12">관광지</option>
                    <option value="14">문화시설</option>
                    <option value="15">축제공연행사</option>
                    <option value="25">여행코스</option>
                    <option value="28">레포츠</option>
                    <option value="32">숙박</option>
                    <option value="38">쇼핑</option>
                    <option value="39">음식점</option>
                </select>
                <button type="button" style="border-radius:10px; font-size:25px;" class="col-1 btn btn-primary ml-auto" @click="search">검색</button>
            </form>
            <div class="mr-0 ml-0">
                <div id="map" class="roundmap" style="width: 100%; height: 900px"></div>
            </div>
        </div>
        <div class="col-6 pl-2 pt-2 pb-2 abc animate__animated animate__backInRight ">
            <div class="overflow-auto transparent-background roundlist" style="height: 100%;">
                <div class="d-flex flex-column bd-highlight">
                    <div class="p-2 bd-highlight">
                    <h3 class="mt-3 ml-2 mr-2">{{ currentPage }}페이지</h3>
                    </div>
                    <div class="p-2 bd-highlight">
                    <b-table 
                    id="my-table"
                    :items="items"
                    :fields="fields"
                    :per-page="perPage"
                    :current-page="currentPage"
                    small
                    @row-clicked="handleRowClick"
                    >
                    <template #cell(first_image)="row">
                        <span v-html="row.value"></span>
                    </template>
                    </b-table>
                    </div>
                    <div class="p-2 bd-highlight d-flex justify-content-center">
                    <b-pagination class=""
                    v-model="currentPage"
                    pills
                    :total-rows="rows"
                    :per-page="perPage"
                    aria-controls="my-table"
                    ></b-pagination>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</template>

<script>
import http from '@/api/http'
import 'animate.css'
export default {
    name: 'AppAttraction',
    components: {},
    mounted(){
        if (window.kakao && window.kakao.maps) {
        // 카카오 객체가 있고, 카카오 맵그릴 준비가 되어 있다면 맵 실행
        this.loadMap();
        } else {
        // 없다면 카카오 스크립트 추가 후 맵 실행
        this.loadScript();
        }
    },
    data() {
        return {
            map:null,
			sido:[],
			gugun:[],
			positions:[],
            trips:[],
			markers:[],
            perPage: 7,
            currentPage: 1,
            items: [],
            fields:[
                {
                key:'first_image',
                label:'사진',
                formatter:value=>{
                    return `<div style="width: 90px;
                    height: 80px; 
                    border-radius: 20%;
                    overflow: hidden;">
                        <img style="width: 100%;
                        height: 100%;
                        object-fit: cover;" src="${value}" onerror="this.src='https://via.placeholder.com/100x100'"/>
                    </div>`
                }
                },
                {
                    key:'title',
                    label:'관광지명'
                },
                {
                    key:'addr1',
                    label:'주소'
                },
            ],
            infowindow: null,
        };
    },
    created() {
        http.get("/attraction")
        .then(response =>{
            console.log(response.data);
            this.sido = response.data;
        })
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
                center: new window.kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                level: 3, // 지도의 레벨(확대, 축소 정도)
            };

            this.map = new window.kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴
		},
        showValue(e){
            console.log(e.target.value);
            let code = e.target.value;
            http.get(`/attraction/${code}`)
            .then(response => {
                this.gugun = response.data;
            })
        },
        search(){
            let areaCode = this.$refs.SearchArea.value;
			let townCode = this.$refs.SearchTown.value;
			let contentTypeId = this.$refs.SearchContentId.value;
            http.get(`/attraction/${contentTypeId}/${areaCode}/${townCode}`)
            .then(response=>{
                this.items = response.data;
                this.makeMakers();
            })
        },
        makeMakers(){
            this.positions=[];
            console.log(this.map);
            this.items.forEach((area)=>{
                let markerInfo = {
                    img: area.first_image,
                    title : area.title,
                    addr1 : area.addr1,
                    latlng : new window.kakao.maps.LatLng(area.latitude, area.longitude),
                };
                this.positions.push(markerInfo);
            });
            for(var i=0;i<this.markers.length;i++){
                this.markers[i].setMap(null);
            }
            this.markers =[];
            var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
            for(i=0;i<this.positions.length;i++){
                var imageSize = new window.kakao.maps.Size(24,35);
                var markerImage = new window.kakao.maps.MarkerImage(imageSrc,imageSize);

                var marker = new window.kakao.maps.Marker({
                    // map: this.map,
                    position:this.positions[i].latlng,
                    title:this.positions[i].title,
                    image: markerImage,
                });
                marker.setMap(this.map);
                this.markers[i] = marker;
                console.log(this.positions[i].img);
                var iwContent = 
                `<div style="padding:5px; display:flex;">
                    <div style="width: 80px;
                    height: 80px; 
                    border-radius: 70%;
                    overflow: hidden;">
                        <img style="width: 100%;
                        height: 100%;
                        object-fit: cover;" src="${this.positions[i].img}" onerror="this.src='https://via.placeholder.com/55x55'" width=55px height=55px/>
                    </div>
                    <div>
                        <div>
                            <h4>${this.positions[i].title}</h4>
                        </div>
                        <div>
                            ${this.positions[i].addr1}
                        </div>    
                    </div>
                </div>`;


                
                this.infowindow = new window.kakao.maps.InfoWindow({
                    content : iwContent
                });


                window.kakao.maps.event.addListener(marker,"mouseover",this.makeOverListener(this.map,marker,this.infowindow));
                window.kakao.maps.event.addListener(marker,'mouseout',this.makeOutListener(this.infowindow))
            }
            this.map.setCenter(this.positions[0].latlng);
        },
        makeOverListener(map,marker,infowindow){
            return function(){
                infowindow.open(map,marker);
            }
        },
        makeOutListener(infowindow){
            return function(){
                infowindow.close();
            }
        },
        replaceImg(e) {
            e.target.src = require(`@/assets/main/main2.jpg`);
        },
        handleRowClick(item, index, event) {
            // 클릭된 행의 데이터 사용
            console.log(item); // 행의 데이터 출력
            console.log(index,event);
            this.map.setCenter(new window.kakao.maps.LatLng(item.latitude, item.longitude));
            // 필요한 로직 수행
            // ...
        },

    },
    computed: {
      rows() {
        return this.items.length
      }
    }
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
.highlight-bottom {
    background: linear-gradient(to top, rgb(207, 250, 219) 18%, transparent 40%);
    color: rgb(218, 247, 223);
}
.animate__animated.animate__backInLeft{
    /* --animate-duration: 2s; */
    animation-delay: 0.5s;
}
.animate__animated.animate__backInRight{
    /* --animate-duration: 3s; */
    animation-delay: 1s;
}

</style>