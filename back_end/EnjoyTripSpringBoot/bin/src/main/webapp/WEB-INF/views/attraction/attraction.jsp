<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<!-- 상단 navbar end -->
<script src="${root}/js/key.js"></script>
<!-- 카카오 맵 -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=50edb2dcb6a758bf6c0338bb4e845bac&libraries=services,clusterer,drawing">
    </script>
<!-- 중앙 center content end -->
<div class="col-md" style="margin: 10px">
	<div class="alert mt-3 text-center"
		style="font-family: 'Black Han Sans', sans-serif; font-size: 80px"
		role="alert">우리동네 구석구석!!!</div>
	<!-- 관광지 검색 start -->
	<form class="d-flex my-3" onsubmit="return false;" role="search">
		<select id="search-area" class="form-select me-2"
			onchange="showValue(this)">
			<option value="0" selected>검색 할 지역 선택</option>
		</select> <select id="search-town" class="form-select me-2">
			<option value="0" selected>구군 선택</option>
		</select> <select id="search-content-id" class="form-select me-2">
			<option value="0" selected>관광지 유형</option>
			<option value="12">관광지</option>
			<option value="14">문화시설</option>
			<option value="15">축제공연행사</option>
			<option value="25">여행코스</option>
			<option value="28">레포츠</option>
			<option value="32">숙박</option>
			<option value="38">쇼핑</option>
			<option value="39">음식점</option>
		</select>

		<button id="btn-search" class="btn btn-outline-success" type="button">검색</button>
	</form>
	<!-- kakao map start -->
	<div id="map" class="mt-3" style="width: 100%; height: 600px"></div>
	<!-- kakao map end -->
	<div class="row" style="font-family: 'Black Han Sans', sans-serif">
		<table class="table table-striped" style="display: none">
			<thead>
				<tr>
					<th>대표이미지</th>
					<th>관광지명</th>
					<th>주소</th>
					<th>위도</th>
					<th>경도</th>
				</tr>
			</thead>
			<tbody id="trip-list"></tbody>
		</table>
	</div>
	<!-- 관광지 검색 end -->
</div>
<!-- </div> -->
<!-- 중앙 content end -->

<script>
      // index page 로딩 후 전국의 시도 설정.
      let areaUrl =
        "${root}/attraction";

      // fetch(areaUrl, { method: "GET" }).then(function (response) { return response.json() }).then(function (data) { makeOption(data); });
      fetch(areaUrl, { method: "GET" })
        .then((response) => response.json())
        .then((data) => makeOption(data));

      function makeOption(data) {
        let areas = data;

        // console.log(areas.name);

        let sel = document.getElementById("search-area");

        areas.forEach((area) => {
          let opt = document.createElement("option");

          opt.setAttribute("value", area.sido_code);

          opt.appendChild(document.createTextNode(area.sido_name));

          sel.appendChild(opt);
        });
      }

      const showValue = (target) => {
        let sel = document.getElementById("search-town");
        sel.innerHTML = "";

        const value = target.value;
        const code = target.options[target.selectedIndex].value;

        let townUrl ="${root}/attraction/"+code;
          /* "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
          serviceKey +
          "&numOfRows=30&pageNo=1&MobileOS=ETC&MobileApp=AppTest&areaCode=" +
          code +
          "&_type=json"; */

        fetch(townUrl, { method: "GET" })
          .then((response) => response.json())
          .then((data) => maketownOption(data));

        function maketownOption(data) {
          let areas = data;

          let sel = document.getElementById("search-town");

          areas.forEach((area) => {
            let opt = document.createElement("option");

            opt.setAttribute("value", area.gugun_code);

            opt.appendChild(document.createTextNode(area.gugun_name));

            sel.appendChild(opt);
          });
        }
      };

      // 검색 버튼을 누르면..
      // 지역, 유형, 검색어 얻기.
      // 위 데이터를 가지고 공공데이터에 요청.
      // 받은 데이터를 이용하여 화면 구성.
      document.getElementById("btn-search").addEventListener("click", () => {
        let areaCode = 0;
        let townCode = 0;
        let contentTypeId = 0;

        if (parseInt(document.getElementById("search-area").value)) areaCode = document.getElementById("search-area").value;
        if (parseInt(document.getElementById("search-town").value)) townCode = document.getElementById("search-town").value;
        if (parseInt(document.getElementById("search-content-id").value)) contentTypeId = document.getElementById("search-content-id").value;

        let searchUrl ="${root}/attraction/"+contentTypeId+"/"+areaCode+"/"+townCode;
          /* "https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=" +
          serviceKey +
          "&numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=" +
          contentTypeId +
          "&areaCode=" +
          areaCode +
          "&sigunguCode=" +
          townCode; */

        fetch(searchUrl)
          .then((response) => response.json())
          .then((data) => makeList(data));
      });

      var positions; // marker 배열.
      var markers = [];
      function makeList(data) {
        document.querySelector("table").setAttribute("style", "display: ;");
        let trips = data;
        let tripList = ``;
        positions = [];

        trips.forEach((area) => {
        	  tripList += '<tr onclick="moveCenter(area.latitude, area.longitude);">'
        	  tripList += '<td><img src='+area.first_image+' width=100px onerror="this.src=\'${root}/img/no_image.jpg\';" width=100px></td>'
        	  tripList += '<td>'+area.title+'</td>'
        	  tripList += '<td>'+area.addr1+' '+area.addr2+'</td>'
        	  tripList += '<td>'+area.latitude+'</td>'
        	  tripList += '<td>'+area.longitude+'</td>'
        	  tripList += '</tr>';
        
          // 마커 객체 생성
          let markerInfo = {
            title: area.title,
            latlng: new kakao.maps.LatLng(area.latitude, area.longitude), // 마커 위도, 경도
          };
          positions.push(markerInfo);
        });
        document.getElementById("trip-list").innerHTML = tripList;

        displayMarker();
      }

      // 카카오지도
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
          level: 5, // 지도의 확대 레벨
        };

      // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
      var map = new kakao.maps.Map(mapContainer, mapOption);

      function displayMarker() {
        // 기존의 마커를 삭제합니다
        for (var i = 0; i < markers.length; i++) {
          markers[i].setMap(null);
        }

        // 마커 이미지의 이미지 주소입니다
        var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; // 관광지

        for (var i = 0; i < positions.length; i++) {
          // 마커 이미지의 이미지 크기 입니다
          var imageSize = new kakao.maps.Size(24, 35);

          // 마커 이미지를 생성합니다
          var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage, // 마커 이미지
            clickable: true, // 마커 클릭을 가능하게 함
          });

          markers[i] = marker;

          // 마커에 클릭이벤트를 등록합니다
          kakao.maps.event.addListener(marker, "click", function () {
            // 해당 관광지의 이름 출력
            alert(this.Gb);
          });
        }
        // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
        map.setCenter(positions[0].latlng);
      }

      function moveCenter(lat, lng) {
        map.setCenter(new kakao.maps.LatLng(lat, lng));
      }
    </script>
<%@ include file="../common/footer.jsp" %>
<script src="${root}/js/main.js"></script>
</body>
</html>
