<template>
    <div>
        <div class="d-flex justify-content-center animate__animated animate__backInDown">
            <h1 class="mt-2"><mark class="highlight-bottom">그룹 플랜</mark></h1>
        </div>   
        <div class="mt-3 mb-3 roundlist animate__animated animate__backInLeft" style="height: 900px; background-color: rgba(255, 255, 255, 0.5);
        margin-left: 130px; margin-right: 130px; padding-top: 10px;">
            <div  class="row ml-2 mr-3">
                <button type="button" class="col-2 btn btn-light ml-3" @click="showtotal">전체 그룹</button>
                <button type="button" class="col-2 btn btn-light ml-3" @click="showmy">내 그룹</button>
                <button type="button" class="col-2 btn btn-primary ml-auto" @click="creategroup">그룹 생성</button>
            </div>
            <div class="overflow-auto" style="height: 100%;">
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
                    >
                        <template #cell(subject)="row">
                            <router-link :to="{ name: 'groupview', params:{groupNo : row.item.groupNo} }">{{ row.value }}</router-link>
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
        
        
        </div>
    </div>
</template>

<script>
import http from '@/api/http'
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
    name: 'GroupList',
    components: {},
    data() {
        return {
            perPage: 18,
        currentPage: 1,
        items: [],
        fields:[
                {
                    key:'groupNo',
                    label:'그룹번호',
                },
                {
                    key:'subject',
                    label:'그룹명'
                },
                {
                    key:'userId',
                    label:'방장'
                },
                {
                    key:'registerTime',
                    label:'등록일'
                },
                {
                    key:'startTime',
                    label:'여행시작일'
                },
                {
                    key:'endTime',
                    label:'여행종료일'
                },
                {
                    key:'memberCnt',
                    label:'인원'
                },
            ],
        };
    },
    created() {
        http.get('/api/group')
        .then(response => {
            this.items = response.data.data;
        })
    },
    methods: {
        showtotal(){
            this.items =[];
            http.get('/api/group')
            .then(response => {
            this.items = response.data.data;
            })
        },
        showmy(){
            this.items=[];
            http.get(`/api/group/${this.userInfo.userId}`)
            .then(response => {
            this.items = response.data.data;
            })
        },
        creategroup(){
            this.$router.push("/group/write");
        }
    },
    computed: {
      rows() {
        return this.items.length
      },
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
</style>