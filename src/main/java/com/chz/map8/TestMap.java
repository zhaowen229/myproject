package com.chz.map8;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * https://blog.csdn.net/abc86319253/article/details/53020432
 */
public class TestMap {

	/**
	 * computeIfPresent
	 * 
	 * key存在并且key的value不等null时才执行后面的函数完成映射
	 */
	public void computeIfPresent() {
//test dev
		Map<String, Object> map = new HashMap<>();
		map.put("a", "a");
		map.put("b", null);

		map.computeIfPresent("a", (key, value) -> value + "1");
		map.computeIfPresent("b", (key, value) -> value + "1");
		map.computeIfPresent("c", (key, value) -> value + "1");

		for (Map.Entry<String, Object> n : map.entrySet()) {
			System.out.println(n);
		}
	}

	/**
	 * computeIfAbsent
	 * 
	 * 1.key不存在 执行函数完成映射(key=value)
	 * 
	 * 2.或者 key存在 但是key的value为null 执行函数完成映射
	 */
	public void computeIfAbsent() {
		Map<Object, Object> stringMap = new HashMap<>();
		stringMap.put("a", 123);
		stringMap.put("b", null);

		stringMap.computeIfAbsent("a", K -> new String("1"));
		stringMap.computeIfAbsent("b", K -> new String("2"));
		stringMap.computeIfAbsent("c", K -> "4");

		for (Map.Entry<Object, Object> n : stringMap.entrySet()) {
			System.out.println(n);
		}
	}

	public static void main(String[] args) {
		Map<String, Map<String, Object>> map1 = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();
		map2.put("2", 2);
		map1.put("1", map2);

		map1.forEach((key, value) -> System.out.println(""));

		map1.computeIfPresent("1", (key, map) -> {
			map.entrySet().forEach(entry -> System.out.println(entry.getValue()));
			return null;
		});

	}
	
	@Test
	public void test() {
		HashMap<String,String> map = new HashMap<>() ;
		map.put("1", "001");
		System.out.println(map.get("2"));
	}

}
