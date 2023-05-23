<template>
    <div>
        <div class="d-flex justify-content-center animate__animated animate__backInDown">
                <h1 class="mt-2"><mark class="highlight-bottom">계획 작성하기</mark></h1>
        </div>
        <div class="d-flex justify-content-center animate__animated animate__backInDown">
            <div class="form-group mb-3" style="margin: auto; width: 15%;">
                <label for="exampleInputEmail1">계획명</label>
                <input v-model="plan.subject" type="text" style="border-radius:10px" class="form-control" ref="subject" aria-describedby="emailHelp">
            </div>
        </div>
        <div class="row mr-0 ml-0">
            <div class="col-4">
                <div class="mt-3 mb-3 roundlist animate__animated animate__backInUp test" style="height: 900px; background-color: rgba(255, 255, 255, 0.5); padding-top: 15px; padding-right:5px; margin-left:130px">
                    <div class="row">
                        <div class="input-group mb-3 ml-4 mr-4">
                        <input type="text" ref="searchword" class="form-control" style="border-radius:10px" placeholder="검색할 지역을 입력하시오..." aria-label="Recipient's username" aria-describedby="button-addon2" @keydown.enter="searchattraction">
                        <div class="input-group-append">
                            <button class="btn btn-secondary border-0" type="button" style="border-radius:10px" id="button-addon2" @click="searchattraction">검색</button>
                        </div>
                        </div>
                    </div>
                    <div class="row">
                        <ul class="list-group list-group-flush ml-4 mr-4 mt-2 roundlist">
                        <li class="list-group-item mb-1 mt-1 pt-4 roundlist" v-for="index in attractions" :key="index.content_id"><div class="d-flex justify-content-center"><div class="mr-1"><img :src="index.first_image" @error="replaceImg" alt="" width="100px" height="100px" class="roundlist"></div><div><strong>{{ index.title }}</strong><br>{{index.addr1}}</div><div class="ml-auto"><button class="btn btn-success mb-3 mr-2 mt-4" type="button" @click="pushdata(index)">추가</button></div></div> </li>
                        </ul>
                    </div>

                </div>
            </div>    
            <div class="col-4 pt-2 pb-2 bbc animate__animated animate__backInUp">
                <div class="mr-0 ml-0">
                    <div id="map" class="roundmap" style="width: 100%; height: 900px"></div>
                </div>
            </div>
            <div class="col-4 abc animate__animated animate__backInUp ">
                <div class="mt-3 mb-3 roundlist test" style="height: 900px; background-color: rgba(255, 255, 255, 0.5); padding-top: 15px; margin-right:130px">
                    <div class="d-flex justify-content-center">
                        <h2>경로</h2>
                    </div>
                    
                        <ul class="list-group list-group-flush ml-4 mr-4 mt-2 roundlist">
                            <draggable class="roundlist" v-model="paths">
                            <li class="list-group-item mb-1 mt-1 roundlist" v-for="(index) in paths" :key="index.contentId"><div class="d-flex justify-content-center"><div class="mr-1"><img :src="index.first_image" @error="replaceImg" alt="" width="100px" height="100px" class="roundlist"></div><div><strong>{{ index.title }}</strong><br>{{index.addr1}}
                            </div>
                            <div class="ml-auto"><button class="btn btn-danger mb-3 mr-4 mt-4" type="button" @click="removedata(index)">삭제</button></div></div>
                            <div class="d-flex justify-content-center ml-1 mt-1">
                                <div>
                                    <textarea class="form-control" name="" v-model="index.memo" id="" cols="50" rows="1" style="width:100%"></textarea>
                                </div>
                            </div>
                            </li>
                        </draggable>
                        </ul>
                    
                </div>
            </div>
        </div>
        <div class="d-flex justify-content-end animate__animated animate__backInRight">
            <button class="btn btn-primary mb-3 mr-4" style="border-radius:10px; font-size:25px;" type="button" @click="modifyplan">수정</button>
            <button class="btn btn-success mb-3" style="border-radius:10px; font-size:25px; margin-right:130px;" type="button" @click="movegroup">취소</button>
        </div>
    </div>
</template>

<script>
import http from '@/api/http';
import { mapState } from "vuex";
const memberStore = "memberStore";
import draggable from 'vuedraggable'

export default {
    name: 'GroupPlanModify',
    components: { draggable},
    data() {
        return {
            message: '',
            groupNo : this.$route.params.groupNo,
            planNo : this.$route.params.planNo,
            attractions:[],
            memos:[],
            paths1:[],
            paths:[],
            positions:[],
            markers:[],
            plan:{},
        };
    },
    created() {
        http.get(`/api/groupplan/${this.groupNo}/${this.planNo}`)
        .then(response => {
            this.plan = response.data.plan;
            this.paths1=response.data.paths;
            this.memos=response.data.memos;
            console.log(response.data);
            for(var i=0;i<this.paths1.length;i++){
                this.paths.push({
                    contentId:`${this.paths1[i].content_id}`,
                    title:this.paths1[i].title,
                    addr1:this.paths1[i].addr1,
                    first_image:this.paths1[i].first_image,
                    memo:this.memos[i],
                });
            }
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
        movegroup(){
            this.$router.push({name : 'groupplanview' ,params:{groupNo:this.groupNo,planNo:this.planNo}});
        },
        searchattraction(){
            this.attractions=[];
            let word = this.$refs.searchword.value;
            http.get(`/attraction/search/${word}`)
            .then(response => {
                console.log(response.data);
                this.attractions = response.data.attractions;
                this.makeMarkers();
            })
        },
        makeMarkers(){
            this.positions=[];
            this.attractions.forEach((area)=>{
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
                        object-fit: cover;" src="${this.positions[i].img}" width=55px height=55px/>
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
        pushdata(index){
            this.paths.push({
                contentId:`${index.content_id}`,
                title:index.title,
                addr1:index.addr1,
                first_image:index.first_image,
                memo:"",
            })
        },
        removedata(index){
            this.paths=this.paths.filter((element)=>element.contentId!==index.contentId);
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
        modifyplan(){
            if(this.$refs.subject.value!==""){
            http.put(`/api/groupplan/${this.groupNo}/${this.planNo}`,{
                subject : this.$refs.subject.value,
                userId: this.userInfo.userId,
                paths:this.paths,
            })
            .then(response=>{
                alert(response.data.rsmsg);
            })
            .then(()=>{
                this.$router.push({name : 'groupplanview' ,params:{groupNo:this.groupNo,planNo:this.planNo}});
            })
            }else{
                alert("계획명을 작성하세요")
            }
        },
        replaceImg(e) {
            e.target.src = require(`@/assets/main/main2.jpg`);
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
    },
    computed:{
        ...mapState(memberStore, ["userInfo"]),
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
.test{
    overflow-y: scroll;
    overflow-x: hidden;
}
.test::-webkit-scrollbar{
    width: 20px;
}
.test::-webkit-scrollbar-thumb{
    background-color: rgb(169, 248, 244); /*스크롤바의 색상*/
    background-clip: padding-box;
    border: 2px solid transparent;
    border-radius: 30px;
}
.test::-webkit-scrollbar-track{
    background-color: rgb(255, 255, 255);
    border-radius: 30px;
}
</style>