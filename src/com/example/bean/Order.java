package com.example.bean;

import java.util.List;

public class Order {
 
	
	private String orderId;
	private List<Goods> goods;

	public List<Goods> getGoods() {
		return goods;
	}
	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	
	
	
}
