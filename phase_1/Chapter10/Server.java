//package com.example.socketsever;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(4666);
		while (true) {
		
			Socket socket   = serverSocket.accept();
			OutputStream os = socket.getOutputStream();
			os.write("This is Server".getBytes("utf-8"));
			os.close();
			socket.close();	
		}
		
	}
}

/*命令行：javac Server.java
java Server
app里需要填内网地址
*/


