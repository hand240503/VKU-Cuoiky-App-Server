package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

	public void startServer(int port) {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Server được khởi tạo tại port" + port);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Vừa có kết nối tới");
				ServerHandle serverHandle = new ServerHandle(socket);
				serverHandle.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ServerMain main = new ServerMain();
		main.startServer(9999);
	}
}
