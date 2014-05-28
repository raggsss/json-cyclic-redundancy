package json.redundancy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A ClassUtil is a utility class which is used to check data type of 
 * a variable. It also provides to check primitiveWrapper data type.
 * A provision of date format the user want is also provided with
 * the method setRequiredFormatter method. User can set the date format he/she wants.
 * getFormattedDate method is used to retrieve the date user has set the previously,
 * otherwise by-default the format is dd-MM-yyyy.
 * 
 * @author Rahul V Shirsat
 */
public class ClassUtil {

	private static final Map<Class<?>, Class<?>> primitiveWrapperType = new HashMap(8);
	//private static final Map<Class<?>, Class<?>> otherType = new HashMap(2);
	private static final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat requiredFormatter = new SimpleDateFormat("dd-MM-yyyy");
	
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
	
	/**
	 * This method is used to set the date format 
	 * @param s
	 */
	public static void setRequiredFormatter(String s) {
		requiredFormatter = new SimpleDateFormat(s);
	}
	/**
	 * returns the date format
	 * @return
	 */
	public static DateFormat getRequiredFormatter() {
		return requiredFormatter;
	}
	/**
	 * This method is used to check whether data type is primitive-wrapper
	 * @param clazz
	 * @return boolean type
	 */
	public static boolean isPrimitiveWrapper(Class<?> clazz) {
		return primitiveWrapperType.containsKey(clazz);
	}
	/**
	 * This method is used to check whether data type is
	 * primitive-wrapper or only primitive 
	 * @param clazz
	 * @return boolean type
	 */
	public static boolean isPrimitiveOrWrapper(Class<?> clazz) {
		return (clazz.isPrimitive() || isPrimitiveWrapper(clazz));
	}
	/**
	 * This method is used to get formatted date (by default format is dd-MM-yyyy)
	 * @param o
	 * @return formatted date
	 */
	public static String getFormattedDate(Object o) {
		
		Date date = null;
		String requiredDate = null;
		try {
			//System.out.println("here date : "+o.toString());
			Date d = formatter.parse(o.toString());
			date = requiredFormatter.parse(requiredFormatter.format(d));
			//System.out.println(requiredFormatter.format(date));
			requiredDate = requiredFormatter.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requiredDate;
	}
}