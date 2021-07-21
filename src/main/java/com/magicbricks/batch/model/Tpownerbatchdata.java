package com.magicbricks.batch.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Tpownerbatchdata {
	private Integer id;
	private String category;
	private Long propid;
	private Short ptype;
	private Long budget;
	private Long oid;
	private Integer city;
	private Integer bedroom;
	private Integer locality;
	private Date createdate;
	private Long photoid;
	private Double score;
	private String fname;
	private String email;
	private Long mobile;
	private Short anscount;
	private String pmtsource;
}