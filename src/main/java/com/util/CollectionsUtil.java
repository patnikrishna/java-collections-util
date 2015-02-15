package com.util;

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

	final public static <T> T getByVariableValue(Collection<T> collection,
			Class<T> typeClass, String variableName, Object value)
			throws Exception {
		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException(COLLECTION_TYPES.Collection
					+ CANNOT_BE_NULL_MESSAGE);
		}
		if (typeClass == null) {
			throw new IllegalArgumentException(TYPE_CLASS
					+ CANNOT_BE_EMPTY_MESSAGE);
		}
		if (variableName == null || variableName.isEmpty()) {
			throw new IllegalArgumentException(VARIABLE_NAME
					+ CANNOT_BE_NULL_MESSAGE);
		}

		Method getterMethod = typeClass.getMethod(GET
				+ capitalize(variableName));

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

	final public static Object getByKeyAsVariableValue(Map<?, ?> map,
			Class<?> typeClass, String variableName, Object key)
			throws Exception {
		if (map == null || map.isEmpty()) {
			throw new IllegalArgumentException(COLLECTION_TYPES.Map
					+ CANNOT_BE_NULL_MESSAGE);
		}
		if (typeClass == null) {
			throw new IllegalArgumentException(TYPE_CLASS
					+ CANNOT_BE_EMPTY_MESSAGE);
		}
		if (variableName == null || variableName.isEmpty()) {
			throw new IllegalArgumentException(VARIABLE_NAME
					+ CANNOT_BE_NULL_MESSAGE);
		}

		Method getterMethod = typeClass.getMethod(GET
				+ capitalize(variableName));

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

	final public static Object getByValueAsVariableValue(Map<?, ?> map,
			Class<?> typeClass, String variableName, Object value)
			throws Exception {
		if (map == null || map.isEmpty()) {
			throw new IllegalArgumentException(COLLECTION_TYPES.Map
					+ CANNOT_BE_NULL_MESSAGE);
		}
		if (typeClass == null) {
			throw new IllegalArgumentException(TYPE_CLASS
					+ CANNOT_BE_EMPTY_MESSAGE);
		}
		if (variableName == null || variableName.isEmpty()) {
			throw new IllegalArgumentException(VARIABLE_NAME
					+ CANNOT_BE_NULL_MESSAGE);
		}

		Method getterMethod = typeClass.getMethod(GET
				+ capitalize(variableName));

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

	private static String capitalize(String str) {
		StringBuilder sb = new StringBuilder(str.length());
		sb.append(Character.toUpperCase(str.charAt(0)));
		sb.append(str.substring(1));
		return sb.toString();
	}
}