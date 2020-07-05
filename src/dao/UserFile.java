package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


    // TODO: Auto-generated Javadoc
/**
     * The Class UserFile.
     *IO���ļ�������
     * @date 2020��7��4��
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
    	 *�½��ı��ĵ����û������Ӧ
    	 * @param userName the user name
    	 */
	    
	public void createUser(String userName) {
		File file = new File("��ʷ��Ϣ\\" + userName + ".txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	
	//�������أ�����junit����
	public boolean createUser(String userName,boolean flag) {
		File file = new File("��ʷ��Ϣ\\" + userName + ".txt");
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
    	 *�����û���Ϣ���ı��ļ�
    	 * @param userName the user name
    	 * @param time the time
    	 * @param pkName the pk name
    	 * @param W_L the w l
    	 * @throws IOException Signals that an I/O exception has occurred.
    	 */
	    
	public void saveUser(String userName, String time, String pkName, String W_L) throws IOException {
		FileOutputStream fos = null;
		try {
			File file = new File("��ʷ��Ϣ\\" + userName + ".txt");
			fos = new FileOutputStream(file, true);
			String str3 = "�û�����" + userName + "         ʱ�䣺" + time + "          �˻��Ѷȣ�" + pkName + "          ʤ��״����"
					+ W_L + "\n";
			byte[] buf = str3.getBytes();// ���ַ���ת�����ֽ�����
			for (byte b : buf)
				fos.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 4.�ر������
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//�������أ�����junit����
	public boolean saveUser(String userName, String time, String pkName, String W_L,boolean flag) throws IOException {
		FileOutputStream fos = null;
		try {
			File file = new File("��ʷ��Ϣ\\" + userName + ".txt");
			fos = new FileOutputStream(file, true);
			String str3 = "�û�����" + userName + "         ʱ�䣺" + time + "          �˻��Ѷȣ�" + pkName + "          ʤ��״����"
					+ W_L + "\n";
			byte[] buf = str3.getBytes();// ���ַ���ת�����ֽ�����
			for (byte b : buf)
				fos.write(b);
			flag=true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 4.�ر������
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
    	 *�����û������Ӧ���ı��ļ����鿴��ս��ʷ��Ϣ
    	 * @param userName the user name
    	 */
	    
	public void openUser(String userName) {
		File file = new File("��ʷ��Ϣ\\" + userName + ".txt");
		String path = file.getAbsolutePath();
		try {
			Process process = Runtime.getRuntime().exec("cmd.exe /c notepad " + path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//�������أ�����junit����
	public boolean openUser(String userName,boolean flag) {
		File file = new File("��ʷ��Ϣ\\" + userName + ".txt");
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
