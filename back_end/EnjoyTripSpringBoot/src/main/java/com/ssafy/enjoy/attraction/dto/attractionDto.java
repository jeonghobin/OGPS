package com.ssafy.enjoy.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class attractionDto {
	int content_id;
	int content_type_id;
	String title;
	String addr1;
	String addr2;
	String zipcode;
	String tel;
	String first_image;
	String first_image2;
	int readcount;
	int sido_code;
	int gugun_code;
	double latitude;
	double longitude;
	String mlevel;
	int hit;
}
