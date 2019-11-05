package com.hello.test;

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

	@Test
	public void of() {
		Optional<Schema> obj = Optional.of(Schema.create(Type.STRING));//获取Optional value为null时 throw nullPointException,所以在使用时应确定所给的value不为null
		System.out.println("of==success");
		Optional<Schema> objnull = Optional.of(null);
		System.out.println("of==failure...nullPointException");
	}
	
	@Test
	public void ofNotNull() {
		Optional<Schema> optionalObj = Optional.ofNullable(Schema.create(Type.INT));
		System.out.println("of==success==Optional"+optionalObj.get());
		
		Optional<Schema> emptyOptionalA = Optional.ofNullable(null);
		Optional<Schema> emptyOptionalB = Optional.empty();
		System.out.println("of==success==emptyOptional");
//		
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
		System.out.println(optional.isPresent());//true
		
		Optional<Schema> optionalException = Optional.empty();
		System.out.println(optionalException.isPresent());//false
	}
	
//	@Test
//	public void orElse() {
//		Optional<Schema> optional = Optional.ofNullable(null).orElse(Schema.create(Type.FLOAT));
//		System.out.println(optional.isPresent());//true
//	}
	
	

}
