package com.stuntmania.propulsionGame.server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.Socket;

public class Server extends Thread {
	
	public static final int PORT = 26774;

	private ServerSocket serverSocket;
	private Socket player1;
	
	@Override
	public void run() {
		serverSocket = Gdx.net.newServerSocket(Protocol.TCP, PORT, null);
		player1 = serverSocket.accept(null);
	}
}
