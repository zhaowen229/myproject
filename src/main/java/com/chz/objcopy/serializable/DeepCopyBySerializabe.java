package com.chz.objcopy.serializable;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DeepCopyBySerializabe {

	@Test
	public void testDeepCopy() {
		DeepAddress DeepAddress = new DeepAddress();
		DeepAddress.setAddress("上海");

		DeepStudent s1 = new DeepStudent();
		s1.setNumber(111);
		s1.setAddr(DeepAddress);

		DeepStudent s2 = (DeepStudent) s1.deepCopyBySerialization();
		s2.setNumber(222);

		DeepAddress.setAddress("浦东");// s1地址发生改变

		assertEquals("s1 number:", 111, s1.getNumber());
		assertEquals("s2 number:", 222, s2.getNumber());

		assertEquals("s1 DeepAddress:", "浦东", s1.getAddr().getAddress());
		assertEquals("s2 DeepAddress:", "上海", s2.getAddr().getAddress());

	}

}
