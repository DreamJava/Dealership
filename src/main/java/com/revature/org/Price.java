package com.revature.org;

import java.io.Serializable;

public class Price implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2512991573535384233L;
	private int value;
	
	public Price(int value){
		setValue(value);
	}
	
	private void setValue(int value) {
		this.value = value;
	}

	public int getAmount() {
		return value;
	}
}
