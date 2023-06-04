package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Notify;
import model.Orders;
import model.Product_Price_Views;
import model.User;
import service.OrdersService;
import service.ProductService;
import service.UserService;

public class ServerHandle extends Thread {
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	private ProductService pdController;
	private Gson gson;
	private UserService userService;
	private ProductService productService;
	private OrdersService ordersService;

	public ServerHandle(Socket socket) {
		this.socket = socket;
		gson = new Gson();
		userService = new UserService();
		productService = new ProductService();
		ordersService = new OrdersService();
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			pdController = new ProductService();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {

			while (true) {
				int jsonLength = dis.readInt();

				byte[] jsonBytes = new byte[jsonLength];
				dis.readFully(jsonBytes);
				String json = new String(jsonBytes);

				JsonElement jsonElement = JsonParser.parseString(json);
				JsonObject jsonObject = jsonElement.getAsJsonObject();
				String notifyMode = jsonObject.get("notify").getAsString();
				if (notifyMode.equals("Sv_login")) {
					handleLoginRequest(jsonObject);
				}
				if (notifyMode.equals("Create-order")) {
					handleCreateOrderRequest(jsonObject);
				}
				if (notifyMode.equals("Create-order-detail")) {
					handleCreateDetailRequest(jsonObject);
				}
//				if (notifyMode.equals("get-sum-end")) {
//					getSum(jsonObject);
//				}
				if(notifyMode.equals("load-view-order")) {
					System.out.println(jsonObject);
				}
			}
		} catch (IOException e) {

		}

	}

	public void handleLoginRequest(JsonObject jsonObject) throws IOException {
		JsonElement data = jsonObject.get("data");
		JsonObject dataObject = data.getAsJsonObject();
		System.out.println(dataObject);
		String userName = dataObject.get("userName").getAsString();
		String password = dataObject.get("passWord").getAsString();
		User user = userService.getUsers(userName, password);
		Notify notifyModel = new Notify();
		if (user != null) {
			List<Product_Price_Views> pViews = productService.getAllProduct();
			notifyModel.setNotify("login-success");
			notifyModel.setData(pViews);
			notifyModel.setContent(user.getId() + "");
		} else {
			notifyModel.setNotify("login-failed");
			notifyModel.setContent("Vui lòng kiểm tra tài khoản (mật khẩu)");
		}

		String notifyToClient = gson.toJson(notifyModel);
		byte[] jsonUserBytes = notifyToClient.getBytes();

		synchronized (dos) {
			dos.writeInt(jsonUserBytes.length);
			dos.write(jsonUserBytes);
			dos.flush();
		}
	}

	public void handleCreateOrderRequest(JsonObject jsonObject) throws IOException {

		JsonElement data = jsonObject.get("data");
		JsonObject dataObject = data.getAsJsonObject();

		String date = dataObject.get("date").getAsString();
		int idUser = dataObject.get("id_user").getAsInt();
		int type = dataObject.get("type").getAsInt();

		Orders orders = new Orders();
		orders.setDate(date);
		orders.setId_user(idUser);
		orders.setType(type);
		ordersService.createOrder(orders);
		int idOrder = ordersService.getIdOrder(idUser);
		Notify notifyModel = new Notify();
		notifyModel.setNotify("getID-order");
		notifyModel.setData(idOrder);
		String notifyToClient = gson.toJson(notifyModel);
		byte[] jsonUserBytes = notifyToClient.getBytes();

		synchronized (dos) {
			dos.writeInt(jsonUserBytes.length);
			dos.write(jsonUserBytes);
			dos.flush();
		}
	}

	public void handleCreateDetailRequest(JsonObject jsonObject) {
		JsonElement data = jsonObject.get("data");
		JsonObject dataObject = data.getAsJsonObject();
	}

	public void getSum(JsonObject jsonObject) throws IOException {
		int id = jsonObject.get("data").getAsInt();
		int sumEnd = productService.getSumEnd(id);

		Notify notifyModel = new Notify();
		notifyModel.setNotify("sum-end-product");
		notifyModel.setData(sumEnd);

		String notifyToClient = gson.toJson(notifyModel);
		byte[] jsonUserBytes = notifyToClient.getBytes();

		synchronized (dos) {
			dos.writeInt(jsonUserBytes.length);
			dos.write(jsonUserBytes);
			dos.flush();
		}

	}

	public void viewsOrderdetail(JsonObject jsonObject) {
		JsonElement data = jsonObject.get("data");
		JsonObject dataObject = data.getAsJsonObject();
		System.out.println(dataObject);
	}
}
