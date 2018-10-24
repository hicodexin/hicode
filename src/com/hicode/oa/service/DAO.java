package com.hicode.oa.service;

public interface DAO<T> {
	
	boolean do_insert(T t);
	boolean do_delete(T t);
	boolean do_update(T t);

}
