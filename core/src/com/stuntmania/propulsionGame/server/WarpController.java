package com.stuntmania.propulsionGame.server;

import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.stuntmania.propulsionGame.PropulsionGame;

public class WarpController {

	public WarpClient warpClient;

	public WarpController() {
		initAppwarp();
		warpClient.addConnectionRequestListener(new ConnectionListener(this));
		// warpClient.addChatRequestListener(new ChatListener());
		// warpClient.addZoneRequestListener(new ZoneListener());
		warpClient.addRoomRequestListener(new RoomListener());
		warpClient.addNotificationListener(new NotificationListener());
	}

	private void initAppwarp() {
		try {
			WarpClient.initialize(PropulsionGame.KEY, PropulsionGame.SECRET);
			warpClient = WarpClient.getInstance();
			warpClient.enableTrace(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
