package net.fap.beecloud.client;

import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.MovePlayerPacket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TestClient {

	private int port1;
	private int port2;
	private DatagramSocket ds;


	public TestClient(int server_port) {
		try {
			this.port1 = server_port;
			this.port2 = this.port1 + 1;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						receive();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
			MovePlayerPacket movePlayerPacket = new MovePlayerPacket();
			movePlayerPacket.x = 0;
			movePlayerPacket.y = 0;
			movePlayerPacket.z = 0;
			send(movePlayerPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//接收消息方法
	private void receive() throws IOException {
		DatagramSocket ds = new DatagramSocket(port2);
		while (true) {
			byte[] bytes = new byte[1024];
			DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
			ds.receive(dp);
			String pk1 = new String(dp.getData(), 0, dp.getLength());
			MovePlayerPacket movePlayerPacket = new MovePlayerPacket();
			movePlayerPacket.putString(pk1);
			//System.out.println("收到来自转发器的数据包: "+movePlayerPacket.toString());
		}

	}

	private void send(DataPacket dataPacket) throws IOException {
		DatagramSocket ds = new DatagramSocket();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		byte[] bytes = dataPacket.toString().getBytes();
		InetAddress address = InetAddress.getByName("127.0.0.1");
		DatagramPacket dp = new DatagramPacket(bytes, bytes.length, address, port1);
		//System.out.println("发出数据包: "+dataPacket.toString());
		ds.send(dp);
		ds.close();
	}

}
