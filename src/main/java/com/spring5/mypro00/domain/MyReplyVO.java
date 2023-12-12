package com.spring5.mypro00.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MyReplyVO {
	private long rno;
	private String rcontent;
	private String rwriter;
	private Date rregDate;
	private Date rmodDate;
	private long bno;
	private long prno;
	
	private int rdelFlag;
	private int lvl;
}
