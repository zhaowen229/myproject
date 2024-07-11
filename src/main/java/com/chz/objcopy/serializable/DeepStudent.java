package com.chz.objcopy.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DeepStudent implements Serializable {
	private static final long serialVersionUID = -4579351684679637686L;
	private int number;
	private DeepAddress addr;

	public DeepAddress getAddr() {
		return addr;
	}

	public void setAddr(DeepAddress addr) {
		this.addr = addr;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Object deepCopyBySerialization() {
		try {
			// 写入流中
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
			// 从流中读取
			ObjectInputStream ios = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
			return ios.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;// TODO
		}
	}

}
