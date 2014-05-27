package json.redundancy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import json.redundancy.ClassUtils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonRedundancyRemoval {

	@SuppressWarnings("unchecked")
	public JSONArray prepareJSON(List<?> list) {

		if (list == null || list.isEmpty()) {
			return new JSONArray();
		}

		JSONArray row = new JSONArray();
		Class<? extends Object> clazz = list.get(0).getClass();

		Field[] declaredFields = clazz.getDeclaredFields();

		for (Object o : list) {
			JSONObject json = new JSONObject();
			JSONObject parent = new JSONObject();

			for (Field f0 : declaredFields) {
				// Level zero object manipulation
				try {
					f0.setAccessible(true);

					if (ClassUtils.isPrimitiveOrWrapper(f0.getType())
							|| isStringType(f0.getType())) {
						parent.put(f0.getName(), f0.get(o));
						// System.out.println(o.getClass().getSimpleName() +
						// " : " + f0.getName() + " : " + f0.get(o));
					} else if (isDateType(f0.getType())) {
						parent.put(f0.getName(),
								ClassUtils.getFormattedDate(f0.get(o)));
					} else {

						JSONObject child1 = new JSONObject();
						Field[] declaredFields1 = f0.getType()
								.getDeclaredFields();
						Object o1 = f0.get(o);

						if (o1 == null)
							continue;

						for (Field f1 : declaredFields1) {
							// Level One object manipulation
							f1.setAccessible(true);
							if (ClassUtils.isPrimitiveOrWrapper(f1.getType())
									|| isStringType(f1.getType())) {
								child1.put(f1.getName(), f1.get(o1));
								// System.out.println(o1.getClass().getSimpleName()
								// + " : " + f1.getName() + " : " + f1.get(o1));
							} else if (isDateType(f1.getType())) {
								child1.put(f1.getName(),
										ClassUtils.getFormattedDate(f1.get(o1)));
							} else if (f0.getType() != f1.getType()) {

								Field[] declaredFields2 = f1.getType()
										.getDeclaredFields();
								Object o2 = f1.get(o1);

								if (o2 == null)
									continue;

								JSONObject child2 = new JSONObject();
								// Level Two object manipulation
								for (Field f2 : declaredFields2) {

									f2.setAccessible(true);
									if (ClassUtils.isPrimitiveOrWrapper(f2
											.getType())
											|| isStringType(f2.getType())) {
										child2.put(f2.getName(), f2.get(o2));
										// System.out.println(o2.getClass().getSimpleName()
										// + " : " + f2.getName() + " : " +
										// f2.get(o2));
									} else if (isDateType(f2.getType())) {
										child2.put(f2.getName(), ClassUtils
												.getFormattedDate(f2.get(o2)));
									} else if (f1.getType() != f2.getType()) {

										Field[] declaredFields3 = f2.getType()
												.getDeclaredFields();
										Object o3 = f2.get(o2);

										if (o3 == null)
											continue;

										JSONObject child3 = new JSONObject();

										// Level three object manipulation
										for (Field f3 : declaredFields3) {

											f3.setAccessible(true);
											if (ClassUtils
													.isPrimitiveOrWrapper(f3
															.getType())
													|| isStringType(f3
															.getType())) {
												child3.put(f3.getName(),
														f3.get(o3));
												// System.out.println(o3.getClass().getSimpleName()
												// + " : " + f3.getName() +
												// " : " + f3.get(o3));
											} else if (isDateType(f3.getType())) {
												child3.put(
														f3.getName(),
														ClassUtils
																.getFormattedDate(f3
																		.get(o3)));
											}
										}
										child2.put(f2.getName(), child3);
									}
									child1.put(f1.getName(), child2);
								}
								parent.put(f1.getName(), child2);
							}
						}
						parent.put(f0.getName(), child1);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			json.put(o.getClass().getSimpleName(), parent);
			row.add(json);
		}
		return row;
	}

	@Deprecated
	public JSONObject loopOver() {
		return null;
	}

	public JSONArray prepareJSON(Object o) {

		List<Object> list = new ArrayList<Object>();
		list.add(o);

		return prepareJSON(list);
	}

	public static boolean isDateType(Class<?> type) {
		if ("java.util.Date".equals(type.getName()))
			return true;
		return false;
	}

	public static boolean isStringType(Class<?> type) {
		if ("java.lang.String".equals(type.getName()))
			return true;
		return false;
	}
}
