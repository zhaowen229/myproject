package com.chz.javabase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IntegerAnd_int {

	@Test
	// Integer、int面试
	public void javaBaseType() {

		// 1、由于Integer变量实际上是对一个Integer对象的引用，所以两个通过new生成的Integer变量永远是不相等的（因为new生成的是两个对象，其内存地址不同）。

		Integer i1 = new Integer(100);
		Integer j1 = new Integer(100);
		assertFalse(i1 == j1);

		// 2、Integer变量和int变量比较时，只要两个变量的值是向等的，则结果为true（因为包装类Integer和基本数据类型int比较时，
		// java会自动拆包装为int，然后进行比较，实际上就变为两个int变量的比较）
		Integer i = new Integer(100);
		int j = 100;
		assertTrue(i == j);

		Integer i2 = 101;
		int j2 = 101;
		assertTrue(i2 == j2);

		// 3、非new生成的Integer变量和new Integer()生成的变量比较时，结果为false。
		// （因为非new生成的Integer变量指向的是java常量池中的对象，而new Integer()生成的变量指向堆中新建的对象，两者在内存中的地址不同）
		Integer ii = new Integer(100);
		Integer jj = 100;// 存储在常量池
		assertFalse(ii == jj);

//		如果不太理解上面的例子因为可能会考虑到数值不在缓存范围，结合下面这个考虑一下，超出存储范围相当于 new 新的实例对象，所以这种肯定是有两个不同的对象，所以不相等。
		Integer ii3 = new Integer(200);
		Integer jj3 = 200;
		assertFalse(ii3 == jj3);
		assertTrue(ii3.equals(jj3));

		// 4、对于两个非new生成的Integer对象，进行比较时，如果两个变量的值在区间:
		// 如果数值在-128到127之间，则比较结果为true，否则，比较结果为false
		Integer i4 = 100;
		Integer j4 = 100;
		assertTrue(i4 == j4);

		Integer i5 = 128;
		Integer j5 = 128;
		assertFalse(i5 == j5);
		assertTrue(i5.equals(j5));
		// 对于第4条的原因：
		// java在编译Integer i = 100 ;时，会翻译成为Integer i = Integer.valueOf(100)；，而java
		// API中对Integer类型的valueOf的定义如下：
		/**
		 * public static Integer valueOf(int i){
		 * 
		 * assert IntegerCache.high >= 127; if (i>= IntegerCache.low && i
		 * <=IntegerCache.high)
		 * 
		 * { return IntegerCache.cache[i + (-IntegerCache.low)]; }
		 * 
		 * return new Integer(i);
		 * 
		 * }
		 */

		// java对于-128到127之间的数，会进行缓存，Integer i = 127时，会将127进行缓存，下次再写Integer j =
		// 127时，就会直接从缓存中取，就不会new了

//		Integer类的equals方法源码: 比较两个对象的值
//		public boolean equals(Object obj) {
//	        if (obj instanceof Integer) {
//	            return value == ((Integer)obj).intValue();
//	        }
//	        return false;
//	    }
	}

}