package com.hello.objcopy.serializable;

import java.io.Serializable;

import com.hello.objcopy.Address;

public class DeepAddress implements Serializable {
	private static final long serialVersionUID = 4923734260911739889L;
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public Address clone() {
		Address addr = null;
		try {
			addr = (Address) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return addr;
	}

}
