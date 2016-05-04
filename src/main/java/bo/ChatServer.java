package bo;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dao.UserDaoImpl;
import dt.User;

public class ChatServer {
	public static void main(String[] args) throws IOException {
		Socket socket;	
		@SuppressWarnings("resource")
		ServerSocket serversocket = new ServerSocket(8000);

		System.out.println("Chat Room Server started at " + new Date());

		while (true) {

			socket = serversocket.accept();
			new Thread(new HandleClients(socket)).start();
		}
	}

}

class HandleClients implements Runnable {

	private Socket socket;

	public HandleClients(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public synchronized void run() {
		UserDaoImpl user = new UserDaoImpl();
		try {
			DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
			DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

			// outputToClient.writeUTF("Welcome!");
			String username = "";

			outputToClient.writeUTF("Unesite korisnicko ime: ");
			username = inputFromClient.readUTF();
			outputToClient.writeUTF("Unesite sifru:");
			String pass = inputFromClient.readUTF();
			System.out.println("chack user " + user.checkExist(username));
			if ((user.validate(username, pass))) {
				outputToClient.writeUTF("Dobrodosli !");

			} else if ((!user.validate(username, pass))) {
				outputToClient.writeUTF("Nije vazece korisnicko ime.");
				run();
			} else {

				outputToClient.writeUTF("Korisnik dodan u bazu");
				user.addUser(new User(username, pass));
				outputToClient.writeUTF("Dobrodosli u ");
			}

			while (true) {
				
				String sentence = inputFromClient.readUTF();
				outputToClient.writeUTF(username + " : " + sentence);
				System.out.println(username + " : " + sentence);
//				outputToClient.flush();
				
			}

		} catch (Exception ss) {
			System.err.println(ss);
		}

	}

}
