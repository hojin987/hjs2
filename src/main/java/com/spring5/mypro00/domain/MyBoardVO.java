package com.spring5.mypro00.domain;

import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 생성자 
//@EqualsAndHashCode
@ToString
//@Data //위에 것들 중 @AllArgsConstructor만 제외한 다섯가지 한번에!
public class MyBoardVO {
   
   private long bno ;
   private String btitle ;
   private String bcontent ;
   private String bwriter ;
   private Date bregDate ;
   private Timestamp bmodDate ;
   private int bviewCnt ;
   private int breplyCnt ;
   private int bdelFlag ;
   
}
