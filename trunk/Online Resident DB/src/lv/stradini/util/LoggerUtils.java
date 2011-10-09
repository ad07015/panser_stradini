package lv.stradini.util;


public class LoggerUtils {
	// returns the class (without the package if any)
	public static String getClassName(Class<? extends Object> c) {
		String FQClassName = c.getName();
		int firstChar;
		firstChar = FQClassName.lastIndexOf('.') + 1;
		if (firstChar > 0) {
			FQClassName = FQClassName.substring(firstChar);
		}
		return FQClassName;
	}
}
