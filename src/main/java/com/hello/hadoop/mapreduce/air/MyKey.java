package com.hello.hadoop.mapreduce.air;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class MyKey implements WritableComparable<MyKey> {
	private int year;
	private int month;
	private double air;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public double getAir() {
		return air;
	}

	public void setAir(double air) {
		this.air = air;
	}

	// 序列化
	@Override
	public void write(DataOutput out) throws IOException {
		// 通过write方法写入序列化的数据流
		out.writeInt(year);
		out.writeInt(month);
		out.writeDouble(air);
	}

	// 反序列化
	@Override
	public void readFields(DataInput in) throws IOException {
		// 通过readFields方法从序列化的数据流中读出进行赋值
		year = in.readInt();
		month = in.readInt();
		air = in.readDouble();
	}

	@Override
	public int compareTo(MyKey o) {
		// 按照字典顺序进行比较
		return this == o ? 0 : -1;
	}

}
