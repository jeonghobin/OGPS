<template>
    <div>
        <div class="d-flex justify-content-center animate__animated animate__backInDown">
            <h1 class="mt-2"><mark class="highlight-bottom">그룹 수정</mark></h1>
        </div>
        <div class="mt-3 mb-3 roundlist animate__animated animate__backInLeft" style="height: 900px; background-color: rgba(255, 255, 255, 0.5); margin-left: 130px; margin-right: 130px; padding-top: 100px;">
            <form>
                <div class="form-group mb-5" style="margin: auto; width: 30%;">
                    <label for="exampleInputEmail1">그룹명</label>
                    <input type="text" class="form-control" ref="subject" v-model="group.subject" aria-describedby="emailHelp">
                </div>
                <div class="form-group mb-5" style="margin: auto; width: 30%;">
                    <label for="exampleInputPassword1">아이디</label>
                    <input type="text" class="form-control" ref="userId" v-model="group.userId" readonly>
                </div>
                <div class="form-group mb-5" style="margin: auto; width: 30%;">
                    <label for="exampleInputPassword1">여행시작일</label>
                    <input type="date" class="form-control" ref="startTime" v-model="group.startTime">
                </div>
                <div class="form-group mb-5" style="margin: auto; width: 30%;">
                    <label for="exampleInputPassword1">여행종료일</label>
                    <input type="date" class="form-control" ref="endTime" v-model="group.endTime">
                </div>
                <div class="form-group mb-5" style="margin: auto; width: 30%;">
                    <label for="exampleInputPassword1">모집인원</label>
                    <input type="number" class="form-control" ref="memberCnt" v-model="group.memberCnt" min="2" max="8" step="1">
                </div>
                
                <button type="button" class="btn btn-primary mr-2" style="border-radius:10px; font-size:25px;" @click="modifygroup">수정</button>
                <button type="button" class="btn btn-danger mr-2" style="border-radius:10px; font-size:25px;" @click="movegroup">취소</button>
                
            </form>
        </div>
    </div>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
    name: 'GroupModify',
    components: {},
    data() {
        return {
            groupNo: this.$route.params.groupNo,
            group:{}
        };
    },
    created() {
        http.get(`/api/groupplan/${this.groupNo}`)
        .then(response=>{
            this.group = response.data.group;
        })
    },
    methods: {
        modifygroup(){
            http.put('/api/group', {
                groupNo:`${this.group.groupNo}`,
                subject: this.group.subject,
                startTime: this.group.startTime,
                endTime : this.group.endTime,
                memberCnt : `${this.group.memberCnt}`
            })
            .then(response => {
                console.log(response.data);
            })
            .then(()=>{
                this.$router.push('/group');
            })
        },
        movegroup(){
            this.$router.push({name:"groupview", params:{groupNo:this.groupNo}});
        }
    },
    computed: {
      ...mapState(memberStore, ["userInfo"]),
    }
};
</script>

<style scoped>
.highlight-bottom {
    background: linear-gradient(to top, rgb(207, 250, 219) 18%, transparent 40%);
    color: rgb(218, 247, 223);
}
.animate__animated.animate__backInLeft{
    /* --animate-duration: 2s; */
    animation-delay: 0.5s;
}
</style>