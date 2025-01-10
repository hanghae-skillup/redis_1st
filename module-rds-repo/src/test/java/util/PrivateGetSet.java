package util;

import java.lang.reflect.Field;

public abstract class PrivateGetSet {

	public static <T> Object getValue(T t, String fieldName ) {
		try{
			Field targetField = t.getClass().getDeclaredField(fieldName);
			targetField.setAccessible(true);
			return targetField.get(t);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public static <T, R> R getValue(T targetObject, String fieldName, Class<R> returnType) {
		try {
			Field targetField = targetObject.getClass().getDeclaredField(fieldName);
			targetField.setAccessible(true);
			return returnType.cast(targetField.get(targetObject));
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException("Unable to access field: " + fieldName, e);
		}
	}

	public static <T> void setValue(T t, String fieldName, Object targetValue){
		try {
			t.getClass().getField(fieldName).set(t, targetValue);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
