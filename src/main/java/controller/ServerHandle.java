package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.DetailView;
import model.Notify;
import model.OrderDetail;
import model.Orders;
import model.Product_Price_Views;
import model.Stock;
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
	private int id_User;

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
				System.out.println(jsonLength);
				byte[] jsonBytes = new byte[jsonLength];
				dis.readFully(jsonBytes);
				String json = new String(jsonBytes);
				System.out.println(json);
				JsonElement jsonElement = JsonParser.parseString(json);
				JsonObject jsonObject = jsonElement.getAsJsonObject();
				String notifyMode = jsonObject.get("notify").getAsString();

				if (notifyMode.equals("Sv_login")) {
					handleLoginRequest(jsonObject);
				}
				if (notifyMode.equals("Create-order")) {
					handleCreateOrderRequest(jsonObject);
				}

				if (notifyMode.equals("send-view")) {
					JsonElement data = jsonObject.get("data");
					JsonObject dataObject = data.getAsJsonObject();

					int idPrice = dataObject.get("idPrice").getAsInt();
					id_User = dataObject.get("idUser").getAsInt();
					DetailView detailView = productService.getView(idPrice);
					detailView.setIdUser(id_User);
					detailView.setIdPrice(idPrice);
					int sumEnd = productService.getSumEnd(detailView.getIdproduct());
					detailView.setQuantity(sumEnd);
					Notify notify = new Notify();
					notify.setNotify("view-detail");
					notify.setData(detailView);

					String notifyToClient = gson.toJson(notify);
					byte[] jsonUserBytes = notifyToClient.getBytes();

					synchronized (dos) {
						dos.writeInt(jsonUserBytes.length);
						dos.write(jsonUserBytes);
						dos.flush();
					}

				}
				if (notifyMode.equals("Create-order-dt")) {
					handleCreateOrderRequest(jsonObject);
				}

			}
		} catch (IOException e) {

		}

	}

	public void handleLoginRequest(JsonObject jsonObject) throws IOException {
		JsonElement data = jsonObject.get("data");
		JsonObject dataObject = data.getAsJsonObject();
		String userName = dataObject.get("userName").getAsString();
		String password = dataObject.get("passWord").getAsString();
		User user = userService.getUsers(userName, password);
		Notify notifyModel = new Notify();
		if (user != null) {
			List<Product_Price_Views> pViews = productService.getAllProduct();
			notifyModel.setNotify("login-success");
			notifyModel.setData(pViews);
			notifyModel.setContent(user.getId() + "");
			id_User = user.getId();
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
		System.out.println(dataObject);
		int quantity = dataObject.get("quantity").getAsInt();
		int idPrice = dataObject.get("id_price").getAsInt();
		int idProduct = dataObject.get("id_product").getAsInt();

		int sumEnd = productService.getSumEnd(idProduct);

		LocalDate currentDate = LocalDate.now();

		Orders orders = new Orders();
		orders.setDate(currentDate.toString());
		orders.setId_user(id_User);
		orders.setType(1);
		int idOrder = ordersService.getIdOrder(id_User, currentDate.toString());
		if (idOrder == -1) {
			idOrder = ordersService.createOrder(orders);
		}

		OrderDetail detail = new OrderDetail();
		detail.setDate(currentDate.toString());
		detail.setId_order(idOrder);
		detail.setId_price(idPrice);
		detail.setId_product(idProduct);
		int idOrderDetail = ordersService.insertOrderdetail(detail);

		Stock stock = new Stock();
		stock.setId_order_detail(idOrderDetail);
		stock.setSum_begin(sumEnd);
		stock.setSum_end(sumEnd - quantity);
		stock.setRatio_unit(1);

		ordersService.insertStock(stock);

	}

}
