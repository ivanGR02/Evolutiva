package population;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Random_Utilities {
	
	private static ThreadLocalRandom rand;
	
	static public ThreadLocalRandom getInstance() {
		if(rand==null) {
			rand=ThreadLocalRandom.current();
		}
		return rand;
	}
	
}
