package bo;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client  {

	public static void main(String[] args) throws UnknownHostException, IOException {

		// create a socket to connect to the server
		// 166.160.106.234

		Socket socket = new Socket("localhost", 8000);

		DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
		DataInputStream fromServer = new DataInputStream(socket.getInputStream());

		Scanner input = new Scanner(System.in);

	while(true){
			String serverSentence = fromServer.readUTF();
			System.out.println(" " + serverSentence);

			toServer.writeUTF(input.nextLine());
			toServer.flush();
	}

	}
}
