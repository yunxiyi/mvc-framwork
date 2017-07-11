package com.puhui.util;

/**
 * Created by puhui on 2017/7/11.
 */
public class StringUtils {

	public static String constructorLog(String className, Object [] args){
		StringBuilder arg = new StringBuilder(className + "(");
		for(Object p : args){
			String simpleName = p.getClass().getSimpleName();
			arg.append(simpleName);
			arg.append(", ");
		}
		arg = arg.replace(arg.length() - 2, arg.length() - 1, "");
		arg.append(")");

		return arg.toString();
	}

	public static boolean equals(String a, String b){
		if(a == null || b == null){
			return false;
		}
		return a.equals(b);
	}
}
