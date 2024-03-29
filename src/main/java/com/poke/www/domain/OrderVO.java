package com.poke.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class OrderVO {
	
	private int order_id;
	private String memberId;
	private int productId;
	private String orderDate;
	
	private String name;
	private int price;
	
	private int point;
	
}
