package sample.less02.spring_controller.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class SampleDTO {
	
	private String name ;
	private Integer age ;
	
	public SampleDTO() {
		System.out.println("SampleDTO의 기본생성자1..........");
	}
	
	public SampleDTO(String name, Integer age) {
		this.name = name ;
		this.age = age ;
		System.out.println("SampleDTO의 모든 필드 초기화 생성자..........");
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("SampleDTO클래스이 setName메서드입니다");
	}

	public void setAge(Integer age) {
		this.age = age;
		System.out.println("SampleDTO클래스이 setAge메서드입니다");
	}
	
	

}
