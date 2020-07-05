package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


    // TODO: Auto-generated Javadoc
/**
     * The Class UserFile.
     *IO流文件操作类
     * @date 2020年7月4日
     * @author zengcongying
     * @version v1.0
     */
    
public class UserFile {
	
	    /**
    	 * Instantiates a new user file.
    	 */
	    
	public UserFile() {
	}

	
	
	    /**
    	 * Creates the user.
    	 *新建文本文档与用户名相对应
    	 * @param userName the user name
    	 */
	    
	public void createUser(String userName) {
		File file = new File("历史信息\\" + userName + ".txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	
	//方法重载，用于junit测试
	public boolean createUser(String userName,boolean flag) {
		File file = new File("历史信息\\" + userName + ".txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
				flag=true;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return flag;
	}
	
	    /**
    	 * Save user.
    	 *保存用户信息到文本文件
    	 * @param userName the user name
    	 * @param time the time
    	 * @param pkName the pk name
    	 * @param W_L the w l
    	 * @throws IOException Signals that an I/O exception has occurred.
    	 */
	    
	public void saveUser(String userName, String time, String pkName, String W_L) throws IOException {
		FileOutputStream fos = null;
		try {
			File file = new File("历史信息\\" + userName + ".txt");
			fos = new FileOutputStream(file, true);
			String str3 = "用户名：" + userName + "         时间：" + time + "          人机难度：" + pkName + "          胜负状况："
					+ W_L + "\n";
			byte[] buf = str3.getBytes();// 把字符串转换成字节数组
			for (byte b : buf)
				fos.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 4.关闭输出流
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//方法重载，用于junit测试
	public boolean saveUser(String userName, String time, String pkName, String W_L,boolean flag) throws IOException {
		FileOutputStream fos = null;
		try {
			File file = new File("历史信息\\" + userName + ".txt");
			fos = new FileOutputStream(file, true);
			String str3 = "用户名：" + userName + "         时间：" + time + "          人机难度：" + pkName + "          胜负状况："
					+ W_L + "\n";
			byte[] buf = str3.getBytes();// 把字符串转换成字节数组
			for (byte b : buf)
				fos.write(b);
			flag=true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 4.关闭输出流
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	
	
	    /**
    	 * Open user.
    	 *打开与用户名相对应的文本文件，查看对战历史信息
    	 * @param userName the user name
    	 */
	    
	public void openUser(String userName) {
		File file = new File("历史信息\\" + userName + ".txt");
		String path = file.getAbsolutePath();
		try {
			Process process = Runtime.getRuntime().exec("cmd.exe /c notepad " + path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//方法重载，用于junit测试
	public boolean openUser(String userName,boolean flag) {
		File file = new File("历史信息\\" + userName + ".txt");
		String path = file.getAbsolutePath();
		try {
			Process process = Runtime.getRuntime().exec("cmd.exe /c notepad " + path);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
