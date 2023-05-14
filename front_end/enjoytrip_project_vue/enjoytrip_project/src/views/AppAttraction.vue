<template>
    <div class="col-md" style="margin: 10px">
	<div class="alert mt-3 text-center"
		style="font-family: 'Black Han Sans', sans-serif; font-size: 80px"
		role="alert">우리동네 구석구석!!!</div>
	<!-- 관광지 검색 start -->
	<form class="d-flex my-3" onsubmit="return false;" role="search">
		<select id="search-area" class="form-select me-2"
			@change="showValue" ref="searcharea">
			<option value="0" selected>
                    지역
                </option>
			<option v-for="i in sido" :key="i.sido_code" :value="i.sido_code">{{i.sido_name}}</option>
		</select> <select id="search-town" class="form-select me-2" ref="searchtown">
			<option v-for="i in gugun" :key="i.gugun_code" :value="i.gugun_code">{{i.gugun_name}}</option>
		</select> <select id="search-content-id" class="form-select me-2" ref="searchcontentid">
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

		<button id="btn-search" class="btn btn-outline-success" type="button" @click="search">검색</button>
	</form>
	<!-- kakao map start -->
	<div id="map" class="mt-3" style="width: 100%; height: 600px"></div>
	<!-- kakao map end -->
	<div class="row" style="font-family: 'Black Han Sans', sans-serif">
		<table class="table table-striped" style="display: none" ref="table">
			<thead>
				<tr>
					<th>대표이미지</th>
					<th>관광지명</th>
					<th>주소</th>
					<th>위도</th>
					<th>경도</th>
				</tr>
			</thead>
			<tbody id="trip-list">
				<attraction-list v-for="area in trips" :key="area.title" :area="area"></attraction-list>
				<!-- <attraction-list v-for="(area, i) in trips" :key="i" :area="area"></attraction-list> -->
			</tbody>
		</table>
	</div>
	<!-- 관광지 검색 end -->
</div>
</template>

<script>
import http from '@/api/http'

import AttractionList from '@/components/attraction/AttractionList.vue';
export default {
    name: "AppAttraction",
	components:{
		AttractionList,
	},
    data()
        {
        return {
            map:null,
			sido:[],
			gugun:[],
			trips:[],
			positions:[],
			markers:[]
        };
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
                center: new window.kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                level: 3, // 지도의 레벨(확대, 축소 정도)
            };

            this.map = new window.kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴
		},
		showValue(e){
			let code = e.target.value;
			console.log(e.target.value);
			http.get(`/attraction/${code}`)
			.then(response => {
				this.gugun = response.data;
			})

		},
		search(){
			console.log(this.$refs.searchtown);
			let areaCode = this.$refs.searcharea.value;
			let townCode = this.$refs.searchtown.value;
			let contentTypeId = this.$refs.searchcontentid.value;
			console.log(areaCode,townCode,contentTypeId);
			http.get(`/attraction/${contentTypeId}/${areaCode}/${townCode}`)
			.then(response => {
				this.trips = response.data;
				console.log(this.trips);
				this.$refs.table.style = "display: ;";
				console.log(this.$refs.table,'dsf');
				this.positions=[];
				this.markers=[];
				this.trips.forEach((area) => {
				let markerInfo = {
					title: area.title,
					latlng: new window.kakao.maps.LatLng(area.latitude, area.longitude), // 마커 위도, 경도
				};
				this.positions.push(markerInfo);
				});
				for (var i = 0; i < this.markers.length; i++) {
					this.markers[i].setMap(null);
				}
				var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; // 관광지
				for (i = 0; i < this.positions.length; i++) {
            // 마커 이미지의 이미지 크기 입니다
			var imageSize = new window.kakao.maps.Size(24, 35);

            // 마커 이미지를 생성합니다
			var markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize);

          // 마커를 생성합니다
        var marker = new window.kakao.maps.Marker({
            map: this.map, // 마커를 표시할 지도
            position: this.positions[i].latlng, // 마커를 표시할 위치
            title: this.positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage, // 마커 이미지
            clickable: true, // 마커 클릭을 가능하게 함
        });

        this.markers[i] = marker;

          // 마커에 클릭이벤트를 등록합니다
        window.kakao.maps.event.addListener(marker, "click", function () {
            // 해당 관광지의 이름 출력
            alert(this.Gb);
        });
        }
        // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
        this.map.setCenter(this.positions[0].latlng);
			});
		},
		

    },
	created(){
		console.log('fdsfds');
		http.get('/attraction')
		.then(response => {
			console.log(response.data);
			this.sido = response.data;
		})
	}

} 

</script>