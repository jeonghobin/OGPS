<template>
    <div>
        <div class="d-flex justify-content-center animate__animated animate__backInDown">
            <h1 class="mt-2"><mark class="highlight-bottom">그룹 생성</mark></h1>
        </div>
        <div class="mt-3 mb-3 roundlist animate__animated animate__backInLeft" style="height: 900px; background-color: rgba(255, 255, 255, 0.5);
        margin-left: 130px; margin-right: 130px; padding-top: 100px;">
            <form>
                <div class="form-group mb-5" style="margin: auto; width: 30%;">
                    <label for="exampleInputEmail1">그룹명</label>
                    <input type="text" class="form-control" ref="subject" aria-describedby="emailHelp">
                </div>
                <div class="form-group mb-5" style="margin: auto; width: 30%;">
                    <label for="exampleInputPassword1">아이디</label>
                    <input type="text" class="form-control" ref="userId" :value="this.userInfo.userId" readonly>
                </div>
                <div class="form-group mb-5" style="margin: auto; width: 30%;">
                    <label for="exampleInputPassword1">여행시작일</label>
                    <input type="date" class="form-control" ref="startTime">
                </div>
                <div class="form-group mb-5" style="margin: auto; width: 30%;">
                    <label for="exampleInputPassword1">여행종료일</label>
                    <input type="date" class="form-control" ref="endTime">
                </div>
                <div class="form-group mb-5" style="margin: auto; width: 30%;">
                    <label for="exampleInputPassword1">모집인원</label>
                    <input type="number" class="form-control" ref="memberCnt" min="2" max="8" step="1">
                </div>
                
                <button type="button" style="border-radius:10px; font-size:25px;" class="btn btn-primary mr-2" @click="creategroup">생성</button>
                <button type="button" style="border-radius:10px; font-size:25px;" class="btn btn-danger mr-2" @click="movelist">취소</button>
                
            </form>
        </div>
    </div>
</template>

<script>
import { mapState } from 'vuex';
import http from '@/api/http';
const memberStore = "memberStore";
export default {
    name: 'GroupWrite',
    components: {},
    data() {
        return {
            groupdata:{
                subject: "",
                userId:"",
                startTime:"",
                endTime:"",
                memberCnt:"",
            }
        };
    },
    created() {},
    methods: {
        movelist(){
            this.$router.push("/group");
        },
        creategroup(){
            this.groupdata.subject = this.$refs.subject.value;
            this.groupdata.userId = this.$refs.userId.value;
            this.groupdata.startTime=this.$refs.startTime.value;
            this.groupdata.endTime=this.$refs.endTime.value;
            this.groupdata.memberCnt=this.$refs.memberCnt.value;
            console.log(this.groupdata.subject);
            http.post("/api/group", this.groupdata)
            .then(response=>{
                console.log(response.data)
            })
            .then(()=>{
                this.$router.push("/group");
            }
            )
        }
    },
    computed:{
        ...mapState(memberStore,["userInfo"]),
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
</style>