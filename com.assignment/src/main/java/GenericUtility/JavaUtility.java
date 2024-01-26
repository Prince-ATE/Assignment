package GenericUtility;

import java.util.Date;

public class JavaUtility {

	public static String getName() {
		return new Date().toString().replace(" ", "_").replace(":", "_");
	}
}
