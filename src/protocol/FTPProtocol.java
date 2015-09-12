package protocol;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

public class FTPProtocol {

	static Logger logger = Logger.getLogger(FTPProtocol.class);
	
	public static String eol = "\r\n";
	
	public static String url = "plmrdm.localdomain";
	public static String user = "ftp";
	public static String password = "ftp";
	public static String path = "";

	public static void main(String[] args) throws Exception {

		Socket socket = new Socket(url, 21);

		// get the is and os, how to work about them.
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();

		// read info from the input stream, it is the response from server.
		byte[] buffer = new byte[10000];
		int length = is.read(buffer);
		String s = new String(buffer, 0, length);
		logger.info(s);

		// send the user
		String str = "USER " + user + eol;
		os.write(str.getBytes());
		// get the response.
		length = is.read(buffer);
		s = new String(buffer, 0, length);
		logger.info(s);

		// send the password.
		str = "PASS " + password + eol;
		os.write(str.getBytes());
		// get the response.
		length = is.read(buffer);
		s = new String(buffer, 0, length);
		logger.info(s);

		// send command.
		str = "CWD " + path + eol;
		os.write(str.getBytes());
		// get the response.
		length = is.read(buffer);
		s = new String(buffer, 0, length);
		logger.info(s);

		// set mode
		str = "EPSV ALL" + eol;
		os.write(str.getBytes());
		// get the response.
		length = is.read(buffer);
		s = new String(buffer, 0, length);
		logger.info(s);

		// 得到被动监听信息
		str = "EPSV" + eol;
		os.write(str.getBytes());
		// 得到返回值
		length = is.read(buffer);
		s = new String(buffer, 0, length);
		logger.info(s);

		// 取得FTP被动监听的端口号
		logger.info("debug----------------");
		String portlist = s.substring(s.indexOf("(|||") + 4, s.indexOf("|)"));
		logger.info(portlist);

		// 实例化ShowList线程类，链接FTP被动监听端口号
		ShowList sl = new ShowList();
		sl.port = Integer.parseInt(portlist);
		sl.start();
		// 执行LIST命令
		str = "LIST"  + eol;
		os.write(str.getBytes());
		// 得到返回值 length = is.read(buffer);
		s = new String(buffer, 0, length);
		logger.info(s);

		// 关闭链接
		is.close();
		os.close();
		socket.close();
	}
}

// 得到被动链接信息类，这个类是多线程的
class ShowList extends Thread {
	public int port = 21;

	public void run() {
		try {
			Socket socket = new Socket(FTPProtocol.url, this.port);
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			byte[] buffer = new byte[10000];
			int length = is.read(buffer);
			String s = new String(buffer, 0, length);
			FTPProtocol.logger.info(s);
			// 关闭链接
			is.close();
			os.close();
			socket.close();
		} catch (Exception ex) {
		}
	}
}
