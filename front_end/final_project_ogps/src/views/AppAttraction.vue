<template>
    <div class="row mr-0 ml-0">
        <div class="col-6 pl-2 pr-2 pt-2 pb-2">
            <div class="mr-0 ml-0 mb-2 bg-light">
                fdsfdsfds
            </div>
            <div class="mr-0 ml-0">
                <div id="map" style="width: 100%; height: 900px"></div>
            </div>
        </div>
        <div class="col-6 pl-2 pr-2 pt-2 pb-2">
            <div class="overflow-auto transparent-background" style="height: 100%;">
                <div>
                    
                    <p class="mt-3">Current Page: {{ currentPage }}</p>
                    <b-table
                    id="my-table"
                    :items="items"
                    :per-page="perPage"
                    :current-page="currentPage"
                    small
                    ></b-table>
                    <b-pagination
                    v-model="currentPage"
                    :total-rows="rows"
                    :per-page="perPage"
                    aria-controls="my-table"
                    ></b-pagination>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
// import http from '@/api/http'

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
            perPage: 20,
            currentPage: 1,
            items: [
              { id: 1, first_name: 'Fred', last_name: 'Flintstone' },
              { id: 2, first_name: 'Wilma', last_name: 'Flintstone' },
              { id: 3, first_name: 'Barney', last_name: 'Rubble' },
              { id: 4, first_name: 'Betty', last_name: 'Rubble' },
              { id: 5, first_name: 'Pebbles', last_name: 'Flintstone' },
              { id: 6, first_name: 'Bamm Bamm', last_name: 'Rubble' },
              { id: 7, first_name: 'The Great', last_name: 'Gazzoo' },
              { id: 8, first_name: 'Rockhead', last_name: 'Slate' },
              { id: 9, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 10, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 11, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 12, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 13, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
              { id: 14, first_name: 'Pearl', last_name: 'Slaghoople' },
            ]
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
.content{
    background-color: white; /* 내용의 배경색을 설정합니다. */
    opacity: 1;
}

</style>