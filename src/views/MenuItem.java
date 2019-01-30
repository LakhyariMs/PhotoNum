package views;

import java.lang.reflect.Method;

public class MenuItem {
	private Object obj;
	private String label;
	private String target;
	private boolean isExitItem;

	public MenuItem(String label) {
		this(label, null, null);
	} // cannot invoke this MenuItem ie. dummy item

	public MenuItem(String label, Object obj, String target) {
		this.label = label;
		this.obj = obj;
		this.target = target;
	}

	public String getLabel() {
		return label;
	}

	void invoke() {
		if (target == null)
			return;

		try {
			Method method = obj.getClass().getMethod(target);
			method.invoke(obj);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/* This method tells Menu if 'this' is an exit option or not. */
	boolean isExitItem() {
		return isExitItem;
	}

	/* Menu uses this method to tell 'this' it will be an exit option */
	void setExitItem(boolean isExitItem) {
		this.isExitItem = isExitItem;
	}

	public String toString() {
		return getLabel();
	}
}