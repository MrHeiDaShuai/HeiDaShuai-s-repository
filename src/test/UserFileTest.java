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
 * @date 2020��7��4��
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
		flag=uf.createUser("˧���",flag);
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
		flag=uf.saveUser("�ڴ�˧", "2020-07-04 10:45:28", "DAI", "W", flag);
		assertEquals(true,flag);
	}
	
	/**
	 * Testopen user.
	 */
	@Test
	public void testopenUser() {
		boolean flag=false;
		flag=uf.openUser("�ڴ�˧", flag);
		assertEquals(true,flag);	
	}
}
