package com.hello.objcopy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ShallowCopy {

	@Test
	public void testCopy() {
		Person p1 = new Person("zhangsan", 20);

		// 将p1的引用赋值给p2，p1和p2指向内存堆中同一个对象。
		Person p2 = p1;
		p2.setAge(22);
		p2.setName("lisi");
		assertEquals(22, p1.getAge());
		assertEquals(22, p2.getAge());

		assertEquals("lisi", p1.getName());
		assertEquals("lisi", p2.getName());
	}

	@Test
	public void testShallowCopy() {
		Address addr = new Address();
		addr.setAddress("杭州市");

		Student stu1 = new Student();
		stu1.setNumber(123);
		stu1.setAddr(addr);

		// 浅拷贝
		// 浅拷贝 和 这种Student stu2 = stu1; 不同,stu1.clone() 基本类型和引用类型
		Student stu2 = stu1.clone();

		assertEquals("stu1 number:", 123, stu1.getNumber());
		assertEquals("stu2 number:", 123, stu2.getNumber());

		assertEquals("stu1 address:", "杭州市", stu1.getAddr().getAddress());
		assertEquals("stu2 address:", "杭州市", stu2.getAddr().getAddress());

		addr.setAddress("西湖区");
		stu2.setNumber(666);
		// 在浅克隆中，当对象被复制时只复制它本身和其中包含的值类型的成员变量，而引用类型的成员对象并没有复制
		assertEquals("stu1 number:", 123, stu1.getNumber());
		assertEquals("stu2 number:", 666, stu2.getNumber());

		assertEquals("stu1 address:", "西湖区", stu1.getAddr().getAddress());
		assertEquals("stu2 address:", "西湖区", stu2.getAddr().getAddress());

	}

	class Address {
		private String address;

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
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
				stu = (Student) super.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			return stu;
		}
	}
}

class Person {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
