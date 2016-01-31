import static org.junit.Assert.*;

import org.junit.Test;

public class mantest {

	@Test
	public void test() {
		Manager junit = new Manager();
		int result = junit.count;
		assertEquals(1,result);
	}

}