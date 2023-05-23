package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import controller.ProductController;

public class ServerHandle extends Thread {
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	private ProductController pdController;

	public ServerHandle(Socket socket) {
		this.socket = socket;
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			pdController = new ProductController();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			int nameLength = dis.readInt();
			byte[] nameBytes = new byte[nameLength];
			dis.readFully(nameBytes);

			int passLength = dis.readInt();
			byte[] passBytes = new byte[passLength];
			dis.readFully(passBytes);

			String name = new String(nameBytes);
			String pass = new String(passBytes);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
