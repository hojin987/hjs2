package com.spring5.mypro00.common.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.spring5.mypro00.domain.MyMemberVO;

import lombok.extern.log4j.Log4j;

public class MyMemberUser extends User{
	
	private MyMemberVO mymember ;
	
	public MyMemberUser(MyMemberVO mymember) {
		super(mymember.getUserId(), 
			  mymember.getUserPw(),
			  mymember.getAuthorityList()	//List<MyAuthorityVO>
			  		  .stream()				//Stream<MyAuthorityVO>으로 변환
			  		  .map(myauth -> new SimpleGrantedAuthority(myauth.getAuthority()))	//Stream
			  		  .collect(Collectors.toList())
				);
		
		this.mymember = mymember;
		
	}
	
}
