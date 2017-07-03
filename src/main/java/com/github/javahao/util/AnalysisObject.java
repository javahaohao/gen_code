package com.github.javahao.util;


import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class AnalysisObject {
	private static final String SETTER_PREFIX = "set";

	private static final String GETTER_PREFIX = "get";
	/**
     * 辅助方法
     * @param objParam 要操作的对象
     * @param columnMethod 要在对象中执行的方法
     * @return	返回执行结果
     */
    public static Object getResult(Object objParam,String columnMethod){
    	try {
			for(String methodStr: columnMethod.trim().split("[.]")){
				//拼接要获得的方法
				methodStr = "get"+methodStr.substring(0, 1).toUpperCase() + methodStr.substring(1);
				//获得方法
				Method method = objParam.getClass().getMethod(methodStr, new Class[]{});
				//执行方法，获取返回值
				objParam = method.invoke(objParam, new Object[] {});
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return objParam;
    }

	/**
	 * *直接调用对象方法, 无视private/protected修饰符.
	 * 用于一次性调用的情况，否则应使用getAccessibleMethod()函数获得Method后反复调用.
	 * 同时匹配方法名+参数类型，
	 * @param obj 执行对象
	 * @param methodName 执行方法名称
	 * @param parameterTypes 方法参数类型
	 * @param args 方法参数内容
	 * @return 返回执行结果
	 */
	public static Object invokeMethod(final Object obj, final String methodName, final Class<?>[] parameterTypes,
			final Object[] args) {
		Method method = getAccessibleMethod(obj, methodName, parameterTypes);
		if (method==null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		}

		try {
			return method.invoke(obj, args);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 调用Setter方法, 仅匹配方法名。
	 * 支持多级，如：对象名.对象名.方法
	 * @param obj 执行对象
	 * @param propertyName 属性名称
	 * @param value 属性值
	 */
	public static void invokeSetter(Object obj, String propertyName, Object value) {
		Object object = obj;
		String[] names = StringUtils.split(propertyName, ".");
		for (int i=0; i<names.length; i++){
			if(i<names.length-1){
				String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(names[i]);
				object = invokeMethod(object, getterMethodName, new Class[] {}, new Object[] {});
			}else{
				String setterMethodName = SETTER_PREFIX + StringUtils.capitalize(names[i]);
				invokeMethodByName(object, setterMethodName, new Object[] { value });
			}
		}
	}

	/**
	 * 直接调用对象方法, 无视private/protected修饰符，
	 * 用于一次性调用的情况，否则应使用getAccessibleMethodByName()函数获得Method后反复调用.
	 * 只匹配函数名，如果有多个同名函数调用第一个。
	 * @param obj 执行对象
	 * @param methodName 执行方法名
	 * @param args 方法参数
	 * @return 返回值
	 */
	public static Object invokeMethodByName(final Object obj, final String methodName, final Object[] args) {
		Method method = getAccessibleMethodByName(obj, methodName);
		if (method==null) {
			return null;
		}

		try {
			return method.invoke(obj, args);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问.
	 * 如向上转型到Object仍无法找到, 返回null.
	 * 只匹配函数名。
	 *
	 * 用于方法需要被多次调用的情况. 先使用本函数先取得Method,然后调用Method.invoke(Object obj, Object... args)
	 * @param obj 获取对象
	 * @param methodName 方法名称
	 * @return 返回获取到的方法
	 */
	public static Method getAccessibleMethodByName(final Object obj, final String methodName) {
		for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
			Method[] methods = searchType.getDeclaredMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					makeAccessible(method);
					return method;
				}
			}
		}
		return null;
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问.
	 * 如向上转型到Object仍无法找到, 返回null.
	 * 匹配函数名+参数类型。
	 *
	 * 用于方法需要被多次调用的情况. 先使用本函数先取得Method,然后调用Method.invoke(Object obj, Object... args)
	 * @param obj 容器
	 * @param methodName 方法名称
	 * @param parameterTypes 方法参数类型
	 * @return 返回获取到的方法
	 */
	public static Method getAccessibleMethod(final Object obj, final String methodName,
			final Class<?>... parameterTypes) {
		for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
			try {
				Method method = searchType.getDeclaredMethod(methodName, parameterTypes);
				makeAccessible(method);
				return method;
			} catch (NoSuchMethodException e) {
				// Method不在当前类定义,继续向上转型
				continue;// new add
			}
		}
		return null;
	}

	/**
	 * 改变private/protected的方法为public，尽量不调用实际改动的语句，避免JDK的SecurityManager抱怨。
	 * @param method 要设置的方法
	 */
	public static void makeAccessible(Method method) {
		if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers()))
				&& !method.isAccessible()) {
			method.setAccessible(true);
		}
	}
}
