package json.redundancy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ClassUtils {

	private static final Map<Class<?>, Class<?>> primitiveWrapperType = new HashMap(8);
	private static final Map<Class<?>, Class<?>> otherType = new HashMap(2);
	private static final DateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
	
	static {
		primitiveWrapperType.put(Boolean.class, Boolean.TYPE);
	    primitiveWrapperType.put(Byte.class, Byte.TYPE);
	    primitiveWrapperType.put(Character.class, Character.TYPE);
	    primitiveWrapperType.put(Double.class, Double.TYPE);
	    primitiveWrapperType.put(Float.class, Float.TYPE);
	    primitiveWrapperType.put(Integer.class, Integer.TYPE);
	    primitiveWrapperType.put(Long.class, Long.TYPE);
	    primitiveWrapperType.put(Short.class, Short.TYPE);
	}
	
	public static boolean isPrimitiveWrapper(Class<?> clazz) {
		return primitiveWrapperType.containsKey(clazz);
	}
	public static boolean isPrimitiveOrWrapper(Class<?> clazz) {
		return (clazz.isPrimitive() || isPrimitiveWrapper(clazz));
	}
	public static String getFormattedDate(Object o) {
		
		Date date = null;
		
		try {
			date = formatter.parse(o.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date.toString();
	}
}