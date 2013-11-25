package annas.graph.observable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This class provides a mechanism for intercepting called on a graph.
 * 
 * 
 * @author scsswi
 */
public abstract class DynamicProxy implements InvocationHandler {

	private Object obj;

	public DynamicProxy(Object obj) {
		super();
		this.obj = obj;
	}

	public Object invoke(Object proxy, Method m, Object[] args)
			throws Throwable {

		try {
			this.beforeMethod(this.obj, m, args);
			Object retval = m.invoke(obj, args);
			this.afterMethod(this.obj, m, args, retval);
			return retval;
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * Method called before the action is called on the object.
	 * 
	 * @param obj
	 * @param m
	 *            Method called
	 * @param args
	 *            arguments passed to the method
	 */
	protected abstract void beforeMethod(Object obj, Method m, Object[] args);

	/**
	 * Method called after the action is called on the object.
	 * 
	 * @param obj
	 *            Objected the method was called on
	 * @param m
	 *            Method called
	 * @param args
	 *            arguments passed to the method
	 * @param retval
	 *            return value from the method
	 */
	protected abstract void afterMethod(Object obj, Method m, Object[] args,
			Object retval);

}
