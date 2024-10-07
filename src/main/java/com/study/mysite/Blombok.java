package com.study.mysite;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@Data
@RequiredArgsConstructor
@Getter
@Setter
public class Blombok {
	private String id;
	private int num;
	
	private final String name;
	private final int code;
	
//	public Blombok(String name, int code) {
//		this.name = name;
//		this.code = code;
//	}
	
	public static void main(String[] args) {
//		Blombok lombok = new Blombok();
//		lombok.setId("kimssam");
//		lombok.setNum(10000);
//		
//		System.out.println(lombok.getId());
//		System.out.println(lombok.getNum());
		
		Blombok lombok2 = new Blombok("cuty",1);
		System.out.println(lombok2.getName());
		System.out.println(lombok2.getCode());
		
		
	}

	
}
