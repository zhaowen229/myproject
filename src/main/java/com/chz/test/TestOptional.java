package com.chz.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
import org.junit.Test;

/**
 * https://www.jianshu.com/p/d81a5f7c9c4e
 * @author czhao1
 *
 */
public class TestOptional {
	//get Optional object way
	Optional<Schema> emptyOptionalA = Optional.ofNullable(null);
	Optional<Schema> emptyOptionalB = Optional.empty();
	Optional<String> emptyOptionalC = Optional.of("String");//确定value不为null时可以用这种方式,否者throw NullPointException

	@Test
	public void of() {
		Optional<Schema> obj = Optional.of(Schema.create(Type.STRING));//获取Optional value为null时 throw nullPointException,所以在使用时应确定所给的value不为null
		System.out.println("of==success");
		Optional<Schema> objnull = Optional.of(null);
		System.out.println("of==failure...nullPointException");
	}
	
	@Test
	public void ofNullable() {
		Optional<Schema> optionalObj = Optional.ofNullable(Schema.create(Type.INT));
		assertTrue(optionalObj.isPresent());
		
		Optional<Schema> optional = Optional.ofNullable(null).map(m -> Schema.create(Type.DOUBLE));
		System.out.println(optional.get());
	}
	
	@Test
	public void get() {
		Optional<Schema> optional = Optional.of(Schema.create(Type.FLOAT));
		optional.get();//得到一个value是Schema的Optional
		System.out.println("get==Optional<Schema>");
		Optional<Schema> optionalException = Optional.empty();
		optionalException.get();//throw NoSuchElementException
	}
	
	@Test
	public void isPresent() {
		//value != null 返回 true 否则返回false
		Optional<Schema> optional = Optional.of(Schema.create(Type.FLOAT));
		assertTrue(optional.isPresent());
		
		Optional<Schema> optionalException = Optional.empty();
		assertFalse(optionalException.isPresent());;
	}
	
	@Test
	public void orElse_orElseGet(){
		//value != null时  返回value 否则返回 other
		System.out.println(Optional.ofNullable(null).orElse(Schema.create(Type.FLOAT)));//下面这两个前面返回empty optional value == null, 所以返回other
		System.out.println(Optional.ofNullable(null).orElseGet(()->Schema.create(Type.FLOAT)));
		
		//value != null时  返回value 否则返回 抛出定义的异常
		try {
			//value != null
			Optional.ofNullable(Schema.create(Type.FLOAT)).orElseThrow(()->new NullPointerException());
			//throw NullPointerException
			Optional.ofNullable(null).orElseThrow(()->new NullPointerException());
		} catch (NullPointerException npe) {
			npe.getMessage();
		}
		
	}
	
	@Test
	public void orElseThrow(){
		//value != null时  返回value 否则返回 抛出定义的异常
		try {
			//value != null
			Optional.ofNullable(Schema.create(Type.FLOAT)).orElseThrow(()->new NullPointerException());
			//throw NullPointerException
			Optional.ofNullable(null).orElseThrow(()->new NullPointerException());
		} catch (NullPointerException npe) {
			npe.getMessage();
		}
		
	}
	
	@Test
	public void filter() {
		Optional<String> strOptional = Optional.of("test");
		assertEquals("test", strOptional.get());
		
		//filter 过滤作用  符合条件返回本身 否则返回一个空的Optional
		Optional<String> stremptyOptional = strOptional.filter(s->s !="test");
		assertFalse(stremptyOptional.isPresent());
	}
	
	@Test
	public void map() {
		//map的参数是一个函数式接口(lambda表达式)
		//value ==null 返回empty() 否者返回 map 之后的Optional
		//map()方法的参数为Function（函数式接口）对象，map()方法将Optional中的包装对象用Function函数进行运算，并包装成新的Optional对象（包装对象的类型可能改变）。
		Student student = new Student();

		Optional<Student> nameOptional = Optional.of(student);
		Optional<Integer> ageOptional = nameOptional.map(m->m.getAge());
		System.out.println(ageOptional.get());//3
		
		Optional<Student> emptyOptional = Optional.ofNullable(null);
		Optional<Integer> map_emptyOptional = emptyOptional.map(m->m.getAge());
		assertFalse(map_emptyOptional.isPresent());
		
	}
	
	@Test
	public void flatMap() {
		//value ==null return empty();otherwise return map之后的Optional
		Student student = new Student();

		Optional<Student> nameOptional = Optional.of(student);
		assertEquals("java8", nameOptional.get().getName());
		
		Optional<String> strOptional = nameOptional.flatMap(m->Optional.of("flatmap"));
		assertEquals("flatmap", strOptional.get());
	}
	
	@Test
	public void test() {
	}
	
	public class Student{
		private String name="java8";
		private int age=3;
		
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

}
