<template>
    <div>
        <div class="d-flex justify-content-center">
            <h1 class="mt-2"><mark class="highlight-bottom">여행 후기</mark></h1>
        </div>   
        <div class="mt-3 mb-3 roundlist" style="height: 900px; background-color: rgba(255, 255, 255, 0.5);
        margin-left: 130px; margin-right: 130px; padding-top: 10px;">
            <div  class="row ml-2 mr-3">
                <button type="button" class="col-2 btn btn-primary ml-auto" @click="creategroup">여행 후기 작성하기</button>
            </div>
            <div class="overflow-auto" style="height: 100%;">
                <div class="d-flex flex-column bd-highlight">
                    <div class="p-2 bd-highlight">
                    </div>
                    <div class="p-2 bd-highlight">
                        <div v-for="item in items" :key="item.articleNo">
                            <router-link :to="{ name: 'reviewview' }">
                                <b-card img-src="https://placekitten.com/100/100" img-alt="Card image" img-right>
                                <b-card-text>
                                    <h2>제목:{{ item.subject }}</h2>
                                    <h3>내용:{{ item.content }}</h3>
                                    <h4>작성 날짜:{{ item.registerTime }}</h4>
                                    <h4>조회수:{{ item.hit }}   추천수:{{ item.heart }}</h4>
                                </b-card-text>
                                </b-card>
                            </router-link>
                        </div>
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
    name: 'ReviewList',
    components: {},
    data() {
        return {
        perPage: 5,
        currentPage: 1,
        items: [],
        };
    },
    created() {
        http.get('/api/review')
        .then(response => {
            this.items = response.data.data;
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
</style>