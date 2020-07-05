package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dao.UserUtil;


    // TODO: Auto-generated Javadoc
/**
     * The Class UserUtilTest.
     *
     * @date 2020Äê7ÔÂ4ÈÕ
     * @author zengcongying
     * @version v1.0
     */
    
public class UserUtilTest {
	
	/** The driver name. */
	private static String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	private static String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=zcy01430&useSSL=true&CharacterEncoding=GBK";
	
	/** The uul. */
	UserUtil uul = new UserUtil(driverName, uri);
	
	/**
	 * Testsave user info.
	 */
	@Test
	public void testsaveUserInfo() {
		String sql="insert into user_info values('zcy','123')";
		int num=uul.saveUserInfo(sql);
		assertEquals(1,num);
	}
	
	/**
	 * Testsave user history.
	 */
	@Test
	public void testsaveUserHistory() {
		String sql="insert into user_history values('zcy',	'2020-07-03 21:53:16',	'DAI',	'L')";
		int num=uul.saveUserHistory(sql);
		assertEquals(1,num);
	}
	
	/**
	 * Testshow user info.
	 */
	@Test
	public void testshowUserInfo() {
		String sql="select * from user_history where userName='z'";
		int num=uul.showUserInfo(sql);
		assertEquals(4,num);
	}
}
