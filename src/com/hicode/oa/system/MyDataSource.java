package com.hicode.oa.system;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MyDataSource extends UnpooledDataSourceFactory {

	public MyDataSource() {
		this.dataSource = new ComboPooledDataSource();
	}
}
