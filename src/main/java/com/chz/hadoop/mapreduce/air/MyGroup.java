package com.chz.hadoop.mapreduce.air;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MyGroup extends WritableComparator {
	public MyGroup() {
		super(MyKey.class, true);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public int compare(WritableComparable a, WritableComparable b) {
		MyKey aKey = (MyKey) a;
		MyKey bKey = (MyKey) b;

		// 根据比较结果对数据分组
		int r1 = Integer.compare(aKey.getYear(), bKey.getYear());
		if (r1 == 0) {
			return Integer.compare(aKey.getMonth(), bKey.getMonth());
		}
		return r1;
	}

}
