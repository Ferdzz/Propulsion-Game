package com.stuntmania.propulsionGame.server;

import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;

public class ConnectionListener implements ConnectionRequestListener {

	WarpController callBack;

	public ConnectionListener(WarpController callBack) {
		this.callBack = callBack;
	}

	@Override
	public void onConnectDone(ConnectEvent e) {
		if (e.getResult() == WarpResponseResultCode.SUCCESS) {
			// callBack.onConnectDone(true);
			System.out.println("success connect");
		} else {
			System.out.println(e.getResult());
			// callBack.onConnectDone(false);
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
