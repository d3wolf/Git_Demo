package protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import org.apache.log4j.Logger;

public class SMTPProtocol {

	static Logger logger = Logger.getLogger(SMTPProtocol.class);

	public static String eol = "\r\n";
	public static String url = "plmrdm.localdomain";

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket(url, 25);
		InputStream is = null;
		OutputStream os = null;
		try {
			// get the is and os, how to work about them.
			is = socket.getInputStream();
			os = socket.getOutputStream();

			// read info from the input stream, it is the response from server.
			byte[] buffer = new byte[10000];
			int length = is.read(buffer);
			String s = new String(buffer, 0, length);
			logger.info("S:" + s);

			if (s.startsWith("220")) {
				String helo = "HELO " + url + eol;
				logger.info("C:" + helo);
				os.write(helo.getBytes());
				// get the response.
				length = is.read(buffer);
				s = new String(buffer, 0, length);
				logger.info("S:" + s);

				if (s.startsWith("250")) {
					String mailFrom = "MAIL FROM:<test1@test.mail.com>" + eol;
					logger.info("C:" + mailFrom);
					os.write(mailFrom.getBytes());
					length = is.read(buffer);
					s = new String(buffer, 0, length);
					logger.info("S:" + s);

					if (s.startsWith("250")) {
						String rcptTo = "RCPT TO:<test2@test.mail.com>" + eol;
						logger.info("C:" + rcptTo);
						os.write(rcptTo.getBytes());
						length = is.read(buffer);
						s = new String(buffer, 0, length);
						logger.info("S:" + s);

						if (s.startsWith("250")) {
							String data = "DATA" + eol;
							logger.info("C:" + data);
							os.write(data.getBytes());
							length = is.read(buffer);
							s = new String(buffer, 0, length);
							logger.info("S:" + s);

							StringBuilder sb = new StringBuilder();
							sb.append("From:<test1@test.mail.com>" + eol);
							sb.append("To:<test2@test.mail.com>" + eol);
							sb.append("Subject:test" + eol);
							sb.append("Date:" + new Date() + eol);
							sb.append("Content-Type:text/plain;charset=\"GB2312\"" + eol);
							sb.append(eol);
							sb.append("test email from smtp.");
							sb.append(eol + "." + eol);

							logger.info("C:" + sb.toString());
							os.write(sb.toString().getBytes());
							length = is.read(buffer);
							s = new String(buffer, 0, length);
							logger.info("S:" + s);

						}
					}
				}
			}
		} finally {
			System.out.println("---close");
			is.close();
			os.close();
			socket.close();
		}
	}
}
