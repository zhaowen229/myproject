package com.chz.objcopy;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

/**
 * （1）
 * 
 * 1.原对象实现 Cloneable 接口
 * 
 * 2.原对象中的引用类型的属性实现 Cloneable 接口
 * 
 * 3.如果原对象中的引用类型的属性对象也包含引用类型，该引用类型也要实现Cloneable 接口
 * 
 * 有多少个引用类型，我们就要重写多少次，如果存在很多引用类型，那么代码量显然会很大，所以这种方法不太合适。
 */
public class DeepCopy {

	@Test
	public void testDeepCopy() {
		Address address = new Address();
		address.setAddress("北京");

		Student stu1 = new Student();
		stu1.setNumber(333);
		stu1.setAddr(address);

		// 在深克隆中，无论原型对象的成员变量是值类型还是引用类型，都将复制一份给克隆对象，
		// 深克隆将原型对象的所有引用对象也复制一份给克隆对象。

		// 简单来说，在深克隆中，除了对象本身被复制外，对象所包含的所有成员变量也将复制。
		Student student2 = stu1.clone();
		student2.setNumber(666);

		// 修改地址 发现之后stu1的地址发生改变，stu2保存不变，完成深拷贝
		address.setAddress("丰台");

		assertEquals("sut1 number:", 333, stu1.getNumber());
		assertEquals("sut2 number:", 666, student2.getNumber());

		assertEquals("sut1 address:", "丰台", stu1.getAddr().getAddress());
		assertEquals("sut2 address:", "北京", student2.getAddr().getAddress());
	}

	public void testThirdTools() {
		Student stu1 = new Student();
		stu1.setNumber(12345);
		Student stu2 = new Student();
		try {
			BeanUtils.copyProperties(stu2, stu1);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}

class Student implements Cloneable {
	private int number;

	private Address addr;

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public Student clone() {
		Student stu = null;
		try {
			stu = (Student) super.clone();// 浅拷贝
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
//		stu.addr = addr.clone(); // 深度复制

		stu.setAddr(stu.getAddr().clone());
		return stu;
	}

}
