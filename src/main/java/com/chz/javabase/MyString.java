package com.chz.javabase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class MyString {

	@Test
	public void testString() {
		/*
		 * String s1 = new String("1");
		 * 这行代码做的事情是；JVM首先在字符串池中查找有没有"1"这个字符串对象，可知在字符串常量池中没有“1”，
		 * 则首先在字符串池中创建一个"1"字符串对象，然后再在堆中创建一个"1"字符串对象，然后将堆中这个"1"字符串对象的地址返回赋给s1引用，
		 * 这样，s1指向了堆中创建的这个"1"字符串对象；
		 * 
		 * String s3 = s1.intern(); JDk1.7及其之后的情况，
		 * 首先会去字符串常量池中检查是否包含一个等于“1”的字符串，因为在字符串常量池中已经有字符串“1”对象， 所以此时s3指向的是方法区中的对象；
		 * 
		 * String s2 = "1";这句代码首先会去字符串的常量池中查否包含一个等于“1”的字符串，
		 * 因为字符串常量池中已经存在方法区中了，所以s2指向的是字符串常量池中的对象。
		 */
		String s1 = new String("1");
		String s3 = s1.intern();
		String s2 = "1";

		assertFalse(s1 == s2);
		assertFalse(s1 == s3);
		assertTrue(s2 == s3);

		/*
		 * 分析在String str1 = new String("ja") + new String("va");
		 * 
		 * 代码中做的事情就是在字符串常量池中存储了"ja"和"va"对象，在堆里面存储的是"java"对象，
		 * 
		 * String str2 = str1.intern();
		 * 
		 * 在字符串常量池中寻找有没有“java”对象，由于JVM的特殊性在JVM启动的时候调用了一些方法，
		 * 在常量池中已经生成了“java”字符串常量，所以s3直接返回字符串常量池中已经存在的“java”, s4也是同样的道理
		 * 
		 */
		String str1 = new String("ja") + new String("va");
		String str2 = str1.intern();
		String str3 = "java";
		assertFalse(str1 == str2);
		assertTrue(str2 == str3);
	}

	@Test
	public void StringMethod() {
		assertTrue("abc".contains("ab"));

		assertEquals("def", "abcdef".substring(3));

		assertEquals("mile", "smiles".substring(1, 5));

		assertEquals(0, "smiles".indexOf("s"));
	}

	@Test
	public void reverseString() {
		// 反转字符串
		String str = "hello";

		String[] s = str.split("");
		List<String> list = Arrays.asList(s);
		Collections.reverse(list);
		System.out.println(list);

		System.out.println(new StringBuilder("hello").reverse().toString());

	}

	@Test
	public void reverseString2() {
		char[] s = { 'h', 'e', 'l', 'l', 'o' };
		int count = s.length / 2;
		char temp;
		int index = 0;
		while (count > 0) {
			temp = s[s.length - 1 - index];
			s[s.length - 1 - index] = s[index];
			s[index] = temp;
			index++;
			count--;
		}

		for (char c : s) {
			System.out.println("reverseString:" + c);
		}
	}

}
