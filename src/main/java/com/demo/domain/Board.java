package com.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Board {
	private int idx;
	private String userId;
	private String userName;
	private String userEmail;
	private String userPhone;
	private String title;
	private String content;
	private int hit;
	private int point;
	private String kind;
	private String fileName;
	private String regDate;
	private String modDate;
}
