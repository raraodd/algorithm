package datastructure;

public class StringComparator {
	
	public static int compare(String str1, String str2) {
		int comparison = 0;
		int c1, c2;
		
		for (int i = 0; i < str1.length() && i < str2.length(); i++) {
			c1 = str1.toLowerCase().charAt(i);
			c2 = str2.toLowerCase().charAt(i);
			comparison = c1 - c2;
			
			if (comparison != 0) {
				return comparison;
			}
		}
		
		if (str1.length() > str2.length()) {
			return 1;
		}
		else if (str1.length() < str2.length()) {
			return -1;
		}
		else {
			return comparison;
		}
	}
}
