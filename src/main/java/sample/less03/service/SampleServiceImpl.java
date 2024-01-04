package sample.less03.service;

import org.springframework.stereotype.Service;

import sample.less03.aop.LogAdvice;

//AOP는 @Service 어노테이션이 명시된 클래스의 메서드에 대하여만 동작합니다.
//핵심 비즈니스 로직이 구현된 메서드의 클래스:Target

@Service
public class SampleServiceImpl implements SampleService {

//	//Spring AOP 사용하지 않고 로그를 남김1
//	@Override
//	public Integer doAdd(String str1, String str2) throws Exception {
//		
//		System.out.println("SampleService의 doAdd 메서드입니다.=========="); //부가기능콘솔에 남는 로그
//
//		return Integer.parseInt(str1) + Integer.parseInt(str2);
//	}

	
	//Spring AOP 사용하지 않고 로그를 남김2
//	private LogAdvice logAdvice ;
//	
//	public SampleServiceImpl(LogAdvice logAdvice) {
//		this.logAdvice = logAdvice ;
//	}
//	
//	@Override
//	public Integer doAdd(String str1, String str2) throws Exception {
//		
//		logAdvice.logBefore1();  //부가기능 실행
//
//		return Integer.parseInt(str1) + Integer.parseInt(str2);
//	}
	
	
	//Spring AOP 를 사용해서 남김1
	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		
		System.out.println("SampleService의 doAdd 메서드입니다.=========="); //부가기능콘솔에 남는 로그

		return Integer.parseInt(str1) + Integer.parseInt(str2);
	}
	
	@Override
	public Integer doAdd(Integer str1, Integer str2) throws Exception {
		
		//System.out.println("SampleService의 doAdd 메서드입니다.=========="); //부가기능콘솔에 남는 로그

		return str1 + str2;
	}
}
