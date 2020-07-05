package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import dao.UserFile;

// TODO: Auto-generated Javadoc
/**
 * The Class UserFileTest.
 *
 * @date 2020年7月4日
 * @author zengcongying
 * @version v1.0
 */
public class UserFileTest {
	
	/** The uf. */
	UserFile uf=new UserFile();
	
	/**
	 * Testcreate user.
	 */
	@Test
	public void testcreateUser() {
		boolean flag=false;
		flag=uf.createUser("帅大黑",flag);
		assertEquals(true,flag);
	}
	
	/**
	 * Testsave user.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testsaveUser() throws IOException {
		boolean flag=false;
		flag=uf.saveUser("黑大帅", "2020-07-04 10:45:28", "DAI", "W", flag);
		assertEquals(true,flag);
	}
	
	/**
	 * Testopen user.
	 */
	@Test
	public void testopenUser() {
		boolean flag=false;
		flag=uf.openUser("黑大帅", flag);
		assertEquals(true,flag);	
	}
}
