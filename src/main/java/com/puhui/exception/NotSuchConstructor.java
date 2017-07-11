package com.puhui.exception;

/**
 * Created by puhui on 2017/7/11.
 */
public class NotSuchConstructor extends Exception {
	public NotSuchConstructor(){
		super();
	}

	public NotSuchConstructor(String s) {
		super(s);
	}
}
