package com.stuntmania.propulsionGame.server;

import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.stuntmania.propulsionGame.PropulsionGame;

public class WarpController {

	public WarpClient warpClient;
	public String roomId;
	public boolean isConnected;

	public WarpController() {
		initAppwarp();
		warpClient.addConnectionRequestListener(new ConnectionListener());
		// warpClient.addChatRequestListener(new ChatListener());
		warpClient.addZoneRequestListener(new ZoneListener());
		warpClient.addRoomRequestListener(new RoomListener());
		warpClient.addNotificationListener(new NotificationListener());
	}

	private void initAppwarp() {
		try {
			WarpClient.initialize(PropulsionGame.KEY, PropulsionGame.SECRET);
			WarpClient.enableTrace(true);
			warpClient = WarpClient.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
