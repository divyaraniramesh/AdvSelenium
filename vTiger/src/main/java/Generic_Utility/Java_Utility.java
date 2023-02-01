package Generic_Utility;

import java.util.Random;

public class Java_Utility {
	/**
	 * this method is used for avoiding Duplicate value
	 * @return
	 * @author Admin
	 */
	public int getRandomNum() {
		Random r=new Random();
		int ranNum = r.nextInt(1000);
	return ranNum;	
	}

}
