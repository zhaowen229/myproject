package com.chz.hadoop.mapreduce.air;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MySort extends WritableComparator {
	public MySort() {
		super(MyKey.class, true);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public int compare(WritableComparable a, WritableComparable b) {
		MyKey aKey = (MyKey) a;
		MyKey bKey = (MyKey) b;

		int r1 = Integer.compare(aKey.getYear(), bKey.getYear());
		if (r1 == 0) {
			int r2 = Integer.compare(aKey.getMonth(), bKey.getMonth());
			if (r2 == 0) {
				// 月相等，按照温度倒序排序
				return -Double.compare(aKey.getAir(), bKey.getAir());
			}
			return r2;
		}
		return r1;
	}

}
