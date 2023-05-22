<template>
    <div>
        <div class="d-flex justify-content-center animate__animated animate__backInDown">
            <h1 class="mt-2"><mark class="highlight-bottom">그룹</mark></h1>
        </div> 
        <div class="mt-3 mb-3 roundlist animate__animated animate__backInLeft" style="height: 900px; background-color: rgba(255, 255, 255, 0.5);
        margin-left: 200px; margin-right: 200px; padding-top: 10px; ">
            맴버( {{members.length}} / {{ group.memberCnt }} )<span v-if="memberOk!=='NO'"><button v-if="userInfo.userId!==members[0].userId" type="button" style="border-radius:10px; font-size:15px;" class="btn btn-danger ml-2" @click="exitmember">그룹나가기</button></span>
            <div class="d-flex justify-content-center">
                <div style="border: 1px solid black; width: 150px; height:70px; overflow-y: scroll; border-radius:10px; background-color:white;">
                    <ol>
                        <li v-for="member in members" :key="member.userId">
                            {{ member.userId }}<button v-if="member.grade===0&&userInfo.userId===members[0].userId" type="button" style="font-size: 5px; width: 20px; height: 20px;" @click="deletemember(member.userId)">X</button></li>
                    </ol>
                </div>
            </div>
            <div class="d-flex justify-content-end mb-2" style="margin-top: 100px;">
                <div v-if="members.length!==group.memberCnt&&memberOk==='NO'">
                    <button type="button" style="border-radius:10px; font-size:25px;" class="btn btn-primary mr-5" @click="joinsubmit">참여하기</button>
                </div>
                <div v-if="memberOk==='OK'">
                    <button type="button" style="border-radius:10px; font-size:25px;" class="btn btn-primary mr-5" @click="movewrite">계획 작성하기</button>
                </div>
            </div>
            <div class="overflow-auto">
                <div class="d-flex flex-column bd-highlight">
                    <div class="p-2 bd-highlight bg-white ml-3 mr-3 roundlist pl-3 pr-3">
                    <b-table
                    id="my-table"
                    :items="items"
                    :fields="fields"
                    :per-page="perPage"
                    :current-page="currentPage"
                    small
                    >
                        <template #cell(subject)="row">
                            <router-link :to="{ name: 'groupplanview', params:{planNo : row.item.planNo,groupNo: row.item.groupNo} }">{{ row.value }}</router-link>
                        </template>
                    </b-table>
                    </div>
                    <div class="p-2 bd-highlight d-flex justify-content-center">
                    <b-pagination class=""
                    v-model="currentPage"
                    :total-rows="rows"
                    :per-page="perPage"
                    aria-controls="my-table"
                    ></b-pagination>
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-center" style="margin-top: 80px;">
                <div v-if="ownerOk==='OK'">
                    <button type="button" style="border-radius:10px; font-size:25px;" class="btn btn-primary mr-2" @click="modifygroup">그룹 수정</button>
                    <button type="button" style="border-radius:10px; font-size:25px;" class="btn btn-danger" @click="deletegroup">그룹 삭제</button>
                </div>
            </div>
            <div v-if="memberOk==='OK'" class="d-flex justify-content-center mt-4">
                <b-button v-b-toggle.sidebar-1 style="border-radius:10px; font-size:25px;">소통</b-button>
                <b-sidebar id="sidebar-1" title="소통" right shadow>
                <div class="px-3 py-2" style="border: 1px solid black; width: 300px; height:700px; margin-left: 10px; display:flex;flex-direction: column-reverse;overflow-y:auto; border-radius:10px">
                    <ul style="list-style-type: none; padding-left:0px">
                        <li class="mb-4" v-for="index in comments" :key="index.commentNo"><div :style="index.userId===userInfo.userId ? 'margin-left:120px;':'margin-right:120px; padding-right:20px;'"><span style="font-weight: bold;">{{ index.userId }}</span> : {{ index.comment }} <br><span style="font-size: small;">{{ index.memoTime }}</span></div></li>
                    </ul>
                </div>
                <div class="fixed-bottom mb-3">
                    <div class="input-group">
                    <input type="text" style="border-radius:10px" class="form-control" ref="chat" placeholder="내용을 입력하시오..." @keyup.enter="submitcomment" aria-label="Recipient's username" aria-describedby="button-addon2">
                    <div class="input-group-append">
                        <button style="border-radius:10px;" class="btn btn-secondary" type="button" id="button-addon2" @click="submitcomment">작성</button>
                    </div>
                    </div>
                </div>
                </b-sidebar>
            </div>
            <div class="d-flex justify-content-center" style="margin-top: 70px;">
                <button class="btn btn-success" style="border-radius:10px; font-size:25px;" type="button" @click="movegrouplist">목록으로</button>
            </div>

        </div>
    </div>
</template>

<script>
import http from '@/api/http';
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
    name: 'GroupView',
    components: {},
    data() {
        return {
            groupNo:this.$route.params.groupNo,
            members:[],
            group:{},
            comments:[],
            memberOk:"NO",
            ownerOk:"NO",
            perPage: 3,
            currentPage: 1,
            items: [],
            fields:[
                {
                    key:'planNo',
                    label:'계획',
                },
                {
                    key:'subject',
                    label:'계획명'
                },
                {
                    key:'userId',
                    label:'계획작성자'
                },
                {
                    key:'heart',
                    label:'좋아요'
                },
            ],
        };
    },
    created() {
        this.groupNo=this.$route.params.groupNo;
        console.log(this.groupNo);
        http.get(`/api/groupplan/${this.groupNo}`)
            .then(response => {
                this.group = response.data.group;
                this.members = response.data.members;
                this.items = response.data.plans;
                this.comments = response.data.comments;
                console.log(response.data);
                this.members.forEach((member) => {
                    if (member.userId === this.userInfo.userId) {
                        this.memberOk = 'OK';
                    }
                });
                if(this.members[0].userId===this.userInfo.userId){
                    console.log("ok");
                    this.ownerOk='OK';
                }
            });
    },
    methods: {
        submitcomment(){
            console.log(this.$refs.chat.value);
            
            http.post(`/api/groupplan/comment/${this.groupNo}`,{
                userId : this.userInfo.userId,
                comment : this.$refs.chat.value
            })
            .then(response => {
                console.log(response.data.rsmsg);
                this.$refs.chat.value="";
            });
        },
        joinsubmit(){
            if(confirm("참여하시겠습니까?")){
            http.post(`/api/groupmember/${this.groupNo}/${this.userInfo.userId}`)
            .then(response => {
                console.log(response.data);
                alert("신청완료");
            })
            .catch(()=>{
                alert("참여신청 실패");
            })
        }
        },
        modifygroup(){
            console.log("modifygroup")
            this.$router.push({name : 'groupmodify', params: {groupNo:this.groupNo}});
        },
        deletegroup(){
            console.log("deletegroup")
            if(confirm("삭제 하시겠습니까?")){
            http.delete(`/api/group/${this.groupNo}`)
            .then(response => {
                console.log(response.data);
            })
            .then(()=>{
                this.$router.push('/group');
            })
            }
        },
        movegrouplist(){
            this.$router.push('/group');
        },
        deletemember(userId1){
            if(confirm("맴버를 삭제하시겠습니까?")){
                http.delete(`/api/groupmember/${this.groupNo}/${userId1}`)
                .then(response => {
                    http.get(`/api/groupplan/${this.groupNo}`)
                    .then(response => {
                        this.members = response.data.members;
                    });
                    alert(response.data.rsmsg);
                })
            }
        },
        movewrite(){
            this.$router.push({name:'groupplanwrite',params:{groupNo:this.groupNo}});
        },
        exitmember(){
            if(confirm("정말로 나가시겠습니까?")){
                http.delete(`/api/groupmember/${this.groupNo}/${this.userInfo.userId}`)
                .then(response => {
                    http.get(`/api/groupplan/${this.groupNo}`)
                    .then(response => {
                        this.members = response.data.members;
                    });
                    alert(response.data.rsmsg);
                })
                .then(()=>{
                    this.$router.push("/group");
                })
            }
        }
    },
    computed: {
        rows() {
            return this.items.length
        },
        ...mapState(memberStore, ["userInfo"]),
    },
    updated(){
        setTimeout(()=>{
            http.get(`/api/groupplan/${this.groupNo}`)
                .then(response => {
                    this.comments = response.data.comments;
                    console.log("update");
                });
        },1000);
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