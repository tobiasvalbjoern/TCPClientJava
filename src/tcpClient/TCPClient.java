package tcpClient;

import java.io.*;
import java.net.*;

class TCPClient {
	public static void main(String argv[]) throws Exception {
		String sentence;
		String modifiedSentence;
		final BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		final Socket clientSocket = new Socket("localhost", 12900);
		final DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		final BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		try {
			while (true) {
				sentence = inFromUser.readLine();
				outToServer.writeBytes(sentence + '\n');
				modifiedSentence = inFromServer.readLine();
				if (modifiedSentence == null) {
					System.out.println("Connection closed\n");
					break;
				} else {
					System.out.println("FROM SERVER: " + modifiedSentence);
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				inFromUser.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				outToServer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
