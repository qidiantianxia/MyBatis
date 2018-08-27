package com.jike.entity;

public class User {

	private Integer uId;
	private String uName;
	private Integer uAge;

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public Integer getuAge() {
		return uAge;
	}

	public void setuAge(Integer uAge) {
		this.uAge = uAge;
	}

	@Override
	public String toString() {
		return "uId=" + uId + ",uName=" + uName + ",uAge=" + uAge;
	}

}
