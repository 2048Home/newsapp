package com.example.bean;

import java.io.Serializable;

public class Address implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 505099149208263151L;
	private String city;
	private String cquyu;
	private String xiaoqu;
	private String lianxiren;
	private String dianhua;
	private String xiangxidizhi;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCquyu() {
		return cquyu;
	}

	public void setCquyu(String cquyu) {
		this.cquyu = cquyu;
	}

	public String getXiaoqu() {
		return xiaoqu;
	}

	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}

	public String getLianxiren() {
		return lianxiren;
	}

	public void setLianxiren(String lianxiren) {
		this.lianxiren = lianxiren;
	}

	public String getDianhua() {
		return dianhua;
	}

	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}

	public String getXiangxidizhi() {
		return xiangxidizhi;
	}

	public void setXiangxidizhi(String xiangxidizhi) {
		this.xiangxidizhi = xiangxidizhi;
	}

}
