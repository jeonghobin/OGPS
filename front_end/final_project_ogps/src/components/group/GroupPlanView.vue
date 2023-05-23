<template>
    <div>
        <div class="d-flex justify-content-center animate__animated animate__backInDown">
            <h1 class="mt-2"><mark class="highlight-bottom">그룹 플랜</mark></h1>
        </div>
        <div class="mt-3 mb-3 roundlist animate__animated animate__backInLeft test" style="height: 900px; background-color: rgba(255, 255, 255, 0.5);
        margin-left: 130px; margin-right: 130px; padding-top: 10px;">
            <div class="d-flex justify-content-center">
                <h2>제목 : {{ plan.subject }} / 좋아요 수 : {{plan.heart}}</h2>
            </div>
            <div class="d-flex justify-content-center mt-3 mb-3">
                <h2>[경로]</h2>
            </div>
            
            <div class="row ml-5">
                <div class="col-2 card mt-3 mb-5 ml-3 mr-4 pt-2 roundlist" v-for="(index,i) in paths" :key="i" style="width: 250px; height: 400px;">
                    <img :src="index.first_image" @error="replaceImg" style="border-radius:30px" class="card-img-top" alt="..." width="100px" height="100px">
                    <div class="card-body">
                        <h5 class="card-title"><strong>{{ index.title }}</strong></h5>
                        <p class="card-text">{{ index.addr1 }}</p>
                        <div class="border" style="border-radius:30px">{{memos[i]}}</div>
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-center mt-5">
                <button type="button" class="btn btn-success mr-2" style="border-radius:10px; font-size:25px;" @click="updateheart">좋아요</button>
            </div>
            <div class="d-flex justify-content-center mt-5 mb-3">
                <button v-if="this.userInfo.userId===this.plan.userId" type="button" class="btn btn-info mr-2" style="border-radius:10px; font-size:25px;" @click="deleteplan">계획 수정</button>
                <button type="button"  class="btn btn-primary mr-2" style="border-radius:10px; font-size:25px;" @click="movegroup">목록으로</button>
                <button v-if="this.userInfo.userId===this.plan.userId" type="button" class="btn btn-danger mr-2" style="border-radius:10px; font-size:25px;" @click="deleteplan">계획 삭제</button>
            </div>
        </div>
    </div>
</template>

<script>
import http from '@/api/http';
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
    name: 'GroupPlanView',
    components: {},
    data() {
        return {
            planNo: this.$route.params.planNo,
            groupNo : this.$route.params.groupNo,
            plan:{},
            paths:[],
            memos:[],

        };
    },
    created() {
        http.get(`/api/groupplan/${this.groupNo}/${this.planNo}`)
        .then(response => {
            this.plan = response.data.plan;
            this.paths=response.data.paths;
            this.memos=response.data.memos;
            console.log(response.data);
        })

    },
    methods: {
        movegroup(){
            this.$router.push({name:'groupview',params:{
                groupNo: this.groupNo
            }})
        },
        deleteplan(){
            if(confirm("계획을 삭제하시겠습니까?")){
                http.delete(`/api/groupplan/${this.groupNo}/${this.planNo}`)
                .then(response => {
                    alert(response.data.rsmsg);
                    
                })
                .then(() => {
                    this.$router.push({
                        name: 'groupview', params: {
                            groupNo: this.groupNo
                        }
                    })
                })
            }
        },
        updateheart(){
            http.post(`/api/groupplan/${this.groupNo}/${this.planNo}/${this.userInfo.userId}`)
            .then(response=>{
                alert(response.data.rsmsg);
                http.get(`/api/groupplan/${this.groupNo}/${this.planNo}`)
                .then(response => {
                    this.plan = response.data.plan;
                    console.log(response.data);
                })

            })
        },
        replaceImg(e) {
            e.target.src = require(`@/assets/main/main2.jpg`);
        },
    },
    computed:{
        ...mapState(memberStore, ["userInfo"]),
    }
};
</script>

<style scoped>
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