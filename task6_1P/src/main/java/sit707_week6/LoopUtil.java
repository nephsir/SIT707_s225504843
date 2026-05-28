package sit707_week6;

public class LoopUtil {
	
	 public static int sumUpToN(int n) {
		 int sum = 0;
	     for (int i = 1; i <= n; i++) {
	    	 sum = sum + i;
	    	 }
	     return sum;
	     }
	 
	 public static int countMultiplesOfThree(int n) {
	        int count = 0;
	        for (int i = 1; i <= n; i++) {
	            if (i % 3 == 0) {
	                count++;
	            }
	        }
	        return count;
	    }

}
