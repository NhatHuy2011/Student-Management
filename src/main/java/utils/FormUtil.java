/*
 * package utils;
 * 
 * import java.lang.reflect.InvocationTargetException;
 * 
 * import javax.servlet.http.HttpServletRequest;
 * 
 * import org.apache.commons.beanutils.BeanUtils;
 * 
 * public class FormUtil {
 * 
 * //Map request.getParameter to field object. Use for insert, update, delete...
 * 
 * @SuppressWarnings("unchecked") public static <T> T toModel(Class<T> model,
 * HttpServletRequest request) { T object = null; try { object =
 * model.getDeclaredConstructor().newInstance(); BeanUtils.populate(object,
 * request.getParameterMap()); } catch (InstantiationException |
 * IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
 * { System.out.print(e.getMessage()); } return object; } }
 * 
 */