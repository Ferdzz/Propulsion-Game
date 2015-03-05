package com.stuntmania.propulsionGame.server;

import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;
import com.stuntmania.propulsionGame.PropulsionGame;

public class ConnectionListener implements ConnectionRequestListener {

	@Override
	public void onConnectDone(ConnectEvent e) {
		if (e.getResult() == WarpResponseResultCode.SUCCESS) {
			PropulsionGame.warpController.warpClient.createRoom("testroom", "host", 2, null);
			System.out.println("connect success");
		} else {
			System.out.println("connect failed");
		}
	}

	@Override
	public void onDisconnectDone(ConnectEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onInitUDPDone(byte arg0) {
		// TODO Auto-generated method stub

	}

}
