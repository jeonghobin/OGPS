<template>
    <div>
        <div class="d-flex justify-content-center">
            <h1 class="mt-2"><mark class="highlight-bottom">여행 후기</mark></h1>
        </div>   
        <div class="d-flex justify-content-center mt-3 mb-3 roundlist" style="height: 900px; background-color: rgba(255, 255, 255, 0.55);
        margin-left: 130px; margin-right: 130px; padding-top: 10px;">
        <div class="row" style="height: 90%; width: 90%;">
            <div style="width: 100%; color: white;">
                <hr style="border: solid 1px white; ">
                <h3> Trip Review </h3>
                <hr style="border: solid 1px white;">
            </div>
            <div class="row d-flex justify-content-center" style=" height: 30%; width: 100%;">
                <div style="width: 80%;">
                    <h4 class="d-flex justify-content-start" style="color: white;"> Best Review </h4>
                    <div v-for="item in items" :key="item.articleNo">
                        <div v-if="item.articleNo === bestNo">
                            <router-link :to="{ name: 'reviewview', params:{article : item} }">
                                <b-card no-body class="overflow-hidden" style=" border: solid 7px white; border-radius: 20px; color: black;">
                                    <b-row no-gutters>
                                        <b-col>
                                            <img :src="fileObjectUrl" @error="replaceImg" width="600px" height="200px" class="rounded-0" >
                                        </b-col>
                                        <b-col>
                                            <b-card-body ><h2>{{ item.subject }}</h2>
                                            <b-card-text>
                                                <!-- <h4>{{ item.content }}</h4> -->
                                            </b-card-text>
                                            </b-card-body>
                                        </b-col>
                                    </b-row>
                                </b-card>
                            </router-link>
                        </div>
                    </div>
                </div>
            </div>
            <div class="overflow-auto mt-4" style="width: 100%; height: 50%;">
                <div class="d-flex flex-column bd-highlight">
                    <div class="p-2 bd-highlight">
                    <h3 class="d-flex justify-content-center ml-3" style="color: white;"> Our's Trip Review </h3> 
                    <button v-if="userInfo" type="button" class="btn btn-primary d-flex justify-content-end" @click="creategroup">Create Review</button>
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
                            <router-link style="color: rebeccapurple;" :to="{ name: 'reviewview', params:{article : row.item} }">{{ row.value }}</router-link>
                        </template>
                    </b-table>
                    </div>
        
                </div>
            </div>
            <div class="p-2 bd-highlight d-flex justify-content-center" style="width: 100%;">
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
</template>

<script>
import http from '@/api/http'
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
    name: 'ReviewList',
    components: {},
    data() {
        return {
        perPage: 5,
        currentPage: 1,
        bestNo: 0,
        fileObjectUrl: '',
        items: [],
            fields: [
                {
                    key:'userId',
                    label:'작성자'
                },
                {
                    key:'subject',
                    label:'제목'
                },
                {
                    key:'registerTime',
                    label:'등록일'
                },
                {
                    key:'hit',
                    label:'조회수'
                },
                {
                    key:'heart',
                    label:'좋아요'
                },
            ],
        };
    },
    created() {
        http.get('/api/review')
        .then(response => {
            this.items = response.data.data;
            this.bestNo = response.data.bestNo;
            const fileNo = response.data.fileNo;
            // console.log(response.data.fileNo);
            if (fileNo) {
                http.get(`/api/rfile/detail/${fileNo}`, {
                    responseType: "blob"
                }).then(response => {
                        const blob = new Blob([response.data]);
                        this.fileObjectUrl = window.URL.createObjectURL(blob);
                })
            }

        })
    },
    methods: {
        showtotal(){
            this.items =[];
            http.get('/api/review')
            .then(response => {
            this.items = response.data.data;
            })
        },
        showmy(){
            this.items=[];
            http.get(`/api/review/${this.userInfo.userId}`)
            .then(response => {
            this.items = response.data.data;
            })
        },
        creategroup(){
            this.$router.push("/review/write");
        },
        replaceImg(e) {
            e.target.src = require(`@/assets/main/main2.jpg`);
        },
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
</style>