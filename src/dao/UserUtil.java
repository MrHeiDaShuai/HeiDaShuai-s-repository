package dao;

import java.sql.*;

    // TODO: Auto-generated Javadoc
/**
    * @ClassName: DBConnection
    * @Description: ���ݿ������������
    * @author dell
    * @date 2020��7��4��
    *
    */
    
class DBConnection {
	    /**
	    * @Title: getConnection
	    * @Description: ͨ��������������ͳһ��Դ��λ���������ݿ������
	    * @param @param driverName
	    * @param @param uri
	    * @param @return    ����
	    * @return Connection    ��������
	    * @throws
	    */
	    
	public static Connection getConnection(String driverName, String uri) {
		Connection conn = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(uri);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return conn;
		}
	}
}



    /**
     * The Class UserUtil.
     *���ݿ������
     * @date 2020��7��4��
     * @author zengcongying
     * @version v1.0
     */
    
public class UserUtil {
	
	/** The pstmt. */
	private PreparedStatement pstmt;
	
	/** The stmt. */
	private Statement stmt;
	
	/** The rs. */
	private static ResultSet rs;
	
	/** The conn. */
	private  Connection conn = null;
	
	    /**
    	 * Instantiates a new user util.
    	 *
    	 * @param driverName the driver name
    	 * @param uri the uri
    	 */
	    
	public   UserUtil(String driverName, String uri) {
		 conn = DBConnection.getConnection(driverName, uri);
	}
	
	
	
	    /**
    	 * Save user info.
    	 *�����û���Ϣ
    	 * @param user the user
    	 * @param password the password
    	 */
	    
	public void saveUserInfo(String user, String password) {
		String sql="insert into user_info values('"+user+"',"+"'"+password+"')";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//�������أ�����junit����
	public int saveUserInfo(String sql) {
		//String sql="insert into user_info values('"+user+"',"+"'"+password+"')";
		int x = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			x=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	
	    /**
    	 * Save user history.
    	 *�����û���ս��Ϣ
    	 * @param userName the user name
    	 * @param time the time
    	 * @param pkName the pk name
    	 * @param W_L the w l
    	 */
	    
	public void saveUserHistory(String userName, String time,String pkName,String W_L) {
		String sql="insert into user_history values('"+userName+"',"+"'"+time+"',"+"'"+pkName+"',"+"'"+W_L+"')";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//�������أ�����junit����
	public int saveUserHistory(String sql) {
		//String sql="insert into user_history values('"+userName+"',"+"'"+time+"',"+"'"+pkName+"',"+"'"+W_L+"')";
		int x=0;
		try {
			pstmt = conn.prepareStatement(sql);
			x=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	    /**
    	 * Creat table.
    	 *������
    	 * @param sql the sql
    	 */
	    
	public void creatTable(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	    /**
    	 * Show user info.
    	 *�������SQL��ѯ����ѯ�������û���Ϣ
    	 * @param sql the sql
    	 * @param user the user
    	 */
	    
	public void showUserInfo(String sql,String user) {
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			int colCount = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colCount; i++)
					System.out.print(rs.getString(i));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//�������أ�����junit����
	public int  showUserInfo(String sql) {
		int colCount=0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			colCount = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colCount; i++)
					System.out.print(rs.getString(i));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return colCount;
	}
	
	
	    /**
    	 * Query user info.
    	 * ��ѯ�û���Ϣ�����ؽ����
    	 * @param sql the sql
    	 * @return the result set
    	 */
	    
	public ResultSet queryUserInfo(String sql) {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return rs;
		}
	}
	
	
	
	    /**
    	 * Close all.
    	 * �ͷ���Դ
    	 */
	    
	public void closeAll() {
		try {
			if (!(rs == null)) {
				rs.close();
				pstmt.close();
				stmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws SQLException the SQL exception
	 */
	public static void main(String []args) throws SQLException {
		String driverName = "com.mysql.jdbc.Driver";
		String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=zcy01430&useSSL=true&?useUnicode=true&CharacterEncoding=GBK";
		String sql;
		UserUtil dbm=new UserUtil(driverName,uri);
		//sql="select * from user_history where userName='z'";
		//String sql2="create table user_history(userName varchar(20),time varchar(20),pkName varchar(20),W_L char(2));";
		//dbm.creatTable(sql2);		
		//dbm.saveUserInfo("����ӱ", "123456");
		/*rs=dbm.queryUserInfo(sql);
		if(rs.next()) {
			System.out.println(000);
		}*/
		//dbm.showUserInfo(sql, "z");
		//dbm.saveUserHistory("z", "2018-03-22-16-25-33", "EAI", "L");	
	}
}
