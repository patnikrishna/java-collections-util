package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import static com.util.Constants.*;

/**
 * 
 * @author Krishna
 * 
 *         This class is for utility methods related to Java Collections that
 *         are not provided by JDK or Apache Commons
 */
public class CollectionsUtil {

	final public static <T> T getByFieldName(Collection<T> collection,
			Class<T> typeClass, String name, Object value)
			throws Exception {
		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException(COLLECTION_TYPES.Collection
					+ CANNOT_BE_NULL_MESSAGE);
		}
		if (typeClass == null) {
			throw new IllegalArgumentException(TYPE_CLASS
					+ CANNOT_BE_EMPTY_MESSAGE);
		}
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException(VARIABLE_NAME
					+ CANNOT_BE_NULL_MESSAGE);
		}

		Field field = typeClass.getDeclaredField(name);

		Method getterMethod = typeClass.getMethod(getMethodName(name,field.getClass()));

		Iterator<T> iterator = collection.iterator();
		while (iterator.hasNext()) {
			T object = iterator.next();
			Object objectValue = getterMethod.invoke(object, new Object[0]);

			if (value.getClass().isAssignableFrom(objectValue.getClass())) {
				if (objectValue.equals(value)) {
					return object;
				}
			} else {
				throw new IllegalArgumentException();
			}

		}

		return null;
	}

	final public static Object getByKeyFromMap(Map<?, ?> map,
			Class<?> typeClass, String name, Object key)
			throws Exception {
		if (map == null || map.isEmpty()) {
			throw new IllegalArgumentException(COLLECTION_TYPES.Map
					+ CANNOT_BE_NULL_MESSAGE);
		}
		if (typeClass == null) {
			throw new IllegalArgumentException(TYPE_CLASS
					+ CANNOT_BE_EMPTY_MESSAGE);
		}
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException(VARIABLE_NAME
					+ CANNOT_BE_NULL_MESSAGE);
		}

		Field field = typeClass.getDeclaredField(name);

		Method getterMethod = typeClass.getMethod(getMethodName(name,field.getClass()));

		Iterator<?> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			Object object = iterator.next();
			Object objectValue = getterMethod.invoke(object, new Object[0]);

			if (key.getClass().isAssignableFrom(objectValue.getClass())) {
				if (objectValue.equals(key)) {
					return object;
				}
			} else {
				throw new IllegalArgumentException();
			}

		}

		return null;
	}

	final public static Object getByValueFromMap(Map<?, ?> map,
			Class<?> typeClass, String name, Object value)
			throws Exception {
		if (map == null || map.isEmpty()) {
			throw new IllegalArgumentException(COLLECTION_TYPES.Map
					+ CANNOT_BE_NULL_MESSAGE);
		}
		if (typeClass == null) {
			throw new IllegalArgumentException(TYPE_CLASS
					+ CANNOT_BE_EMPTY_MESSAGE);
		}
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException(VARIABLE_NAME
					+ CANNOT_BE_NULL_MESSAGE);
		}

		Field field = typeClass.getDeclaredField(name);
		Method getterMethod = typeClass.getMethod(getMethodName(name,
				field.getClass()));

		Iterator<?> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			Object valueObject = map.get(key);

			Object val = getterMethod.invoke(valueObject, new Object[0]);

			if (value.getClass().isAssignableFrom(val.getClass())) {
				if (val.equals(value)) {
					return valueObject;
				}
			} else {
				throw new IllegalArgumentException();
			}

		}

		return null;
	}

	public static String capitalize(String str) {
		StringBuilder sb = new StringBuilder(str.length());
		sb.append(Character.toUpperCase(str.charAt(0)));
		sb.append(str.substring(1));
		return sb.toString();
	}

	private static String getMethodName(String name,
			Class<? extends Object> clazz) {
		String startsWith = null;
		if (clazz != java.lang.Boolean.class)
			startsWith = GET;
		else
			startsWith = IS;
		return startsWith + capitalize(name);
	}

	public static String convertToString(Object object)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		if (null != object) {
			Class<? extends Object> clazz = object.getClass();
			Field[] fields = clazz.getDeclaredFields();
			Method method = null;
			String fieldName = null;
			if (null != fields && fields.length > 0) {
				StringBuilder builder = new StringBuilder();
				for (Field field : fields) {
					fieldName = field.getName();
					method = clazz.getMethod(getMethodName(fieldName,
							field.getClass()));
					builder.append(fieldName).append(EQUAL)
							.append(method.invoke(object, null)).append(COMMA);
				}
				return builder.toString();
			}
		}
		return null;
	}
}