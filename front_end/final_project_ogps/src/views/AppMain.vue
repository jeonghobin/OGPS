<template>
    <div class="margin">
    <div class="d-flex justify-content-center">
      <div>
        <h1 class="mt-2">Logo</h1>
        <h2 class="mt-2">Our Group Plan's</h2>
      </div>
    </div>    
      <div class="d-flex justify-content-center mb-3">
        <div class="col-4 pt-2 pb-2">
          <div class="overflow-auto transparent-background" style="height: 100%;">

          </div>
        </div>
        <div class="col-6 pt-2 pb-2">
          <b-carousel
            id="carousel-1"
            v-model="slide"
            :interval="4000"
            controls
            indicators
            background="#ababab"
            img-width="1024"
            img-height="480"
            style="text-shadow: 1px 1px 2px #333;"
            @sliding-start="onSlideStart"
            @sliding-end="onSlideEnd"
          >
            <!-- Text slides with image -->
            <b-carousel-slide
              caption="First slide"
              text="Nulla vitae elit libero, a pharetra augue mollis interdum."
              img-src="https://picsum.photos/1024/480/?image=52"
            ></b-carousel-slide>

            <!-- Slides with custom text -->
            <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=54">
              <h1>Hello world!</h1>
            </b-carousel-slide>

            <!-- Slides with image only -->
            <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=58"></b-carousel-slide>

            <!-- Slides with img slot -->
            <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
            <b-carousel-slide>
              <template #img>
                <img
                  class="d-block img-fluid w-100"
                  width="1024"
                  height="480"
                  src="https://picsum.photos/1024/480/?image=55"
                  alt="image slot"
                >
              </template>
            </b-carousel-slide>

            <!-- Slide with blank fluid image to maintain slide aspect ratio -->
            <b-carousel-slide caption="Blank Image" img-blank img-alt="Blank image">
              <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eros felis, tincidunt
                a tincidunt eget, convallis vel est. Ut pellentesque ut lacus vel interdum.
              </p>
            </b-carousel-slide>
          </b-carousel>

          <!-- <p class="mt-4">
            Slide #: {{ slide }}<br>
            Sliding: {{ sliding }}
          </p> -->
        </div>
      </div>

      <h1>인기 여행지</h1>
      <div class="d-flex justify-content-around mt-1">
        <div class="row">
          <div class="col-4">
            <b-card
              title="Card Title"
              img-src="https://picsum.photos/600/300/?image=25"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 24rem;"
              class="mb-2"
            >
              <b-card-text>
                Some quick example text to build on the card title and make up the bulk of the card's content.
              </b-card-text>

              <b-button href="#" variant="primary">Go somewhere</b-button>
            </b-card>
          </div>
          <div class="col-4">
            <b-card
              title="Card Title"
              img-src="https://picsum.photos/600/300/?image=25"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 24rem;"
              class="mb-2"
            >
              <b-card-text>
                Some quick example text to build on the card title and make up the bulk of the card's content.
              </b-card-text>

              <b-button href="#" variant="primary">Go somewhere</b-button>
            </b-card>
          </div>
          <div class="col-4">
            <b-card
              title="Card Title"
              img-src="https://picsum.photos/600/300/?image=25"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 24rem;"
              class="mb-2"
            >
              <b-card-text>
                Some quick example text to build on the card title and make up the bulk of the card's content.
              </b-card-text>

              <b-button href="#" variant="primary">Go somewhere</b-button>
            </b-card>
          </div>
        </div>
      </div>  


      <div class="row justify-content-center mb-3 mt-3">
          <div class="overflow-auto transparent-background" style="width: 79%;">
            <h1>인기 후기</h1>
          </div>
          <div class="overflow-auto transparent-background " style="width: 79%;">

          </div>
      </div>

    </div> 
</template>

<script>
export default {
  data() {
    return {
      slide: 0,
      sliding: null
    }
  },
  methods: {
    onSlideStart(slide) {
      this.sliding = true
      console.log(slide)
    },
    onSlideEnd(slide) {
      this.sliding = false
      console.log(slide)
    }
  }
}
</script>

<style scoped>
.transparent-background {
    background-color: rgba(255,255,255,0.5);
     /* 0부터 1까지의 값을 사용하며, 1에 가까울수록 불투명해집니다. */
}

.margin{
  margin-left: auto;
  margin-right: auto;
  width: 80%;
}
</style>