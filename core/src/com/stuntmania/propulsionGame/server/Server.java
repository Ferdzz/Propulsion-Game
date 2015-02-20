package com.stuntmania.propulsionGame.server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.Socket;

public class Server extends Thread {

	public static final int PORT = 26774;

	private ServerSocket serverSocket;
	private Socket player1;
	private Socket player2;

	@Override
	public void run() {
		serverSocket = Gdx.net.newServerSocket(Protocol.TCP, PORT, null);
		player1 = serverSocket.accept(null);
		System.out.println("Player 1 connected");
		player2 = serverSocket.accept(null);
		System.out.println("Player 2 connected");
	}

	public void disposeSockets() {
		try {
			serverSocket.dispose();
			player1.dispose();
			player2.dispose();
		} catch (Exception e) {
			serverSocket = null;
			player1 = null;
			player2 = null;
		}
	}
	
	public int numberOfOnlinePlayer() {
		int number = 0;
		if (player1 != null)
			number++;
		if (player2 != null)
			number++;

		return number;
	}
}
