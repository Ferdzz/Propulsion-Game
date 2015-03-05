package com.stuntmania.propulsionGame.server;

import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveRoomInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.RoomRequestListener;
import com.stuntmania.propulsionGame.PropulsionGame;

public class RoomListener implements RoomRequestListener {

	@Override
	public void onGetLiveRoomInfoDone(LiveRoomInfoEvent event) {
		
	}

	@Override
	public void onJoinRoomDone(RoomEvent e) {
		if(e.getResult() == WarpResponseResultCode.SUCCESS) {
			System.out.println("Joined room");
			PropulsionGame.warpController.isConnected = true;
			PropulsionGame.warpController.roomId = e.getData().getId();
		} else {
			System.out.println("Error joining room");
		}
	}

	@Override
	public void onLeaveRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		PropulsionGame.warpController.isConnected = false;
		PropulsionGame.warpController.roomId = null;
	}

	@Override
	public void onLockPropertiesDone(byte arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSetCustomRoomDataDone(LiveRoomInfoEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSubscribeRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnSubscribeRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnlockPropertiesDone(byte arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpdatePropertyDone(LiveRoomInfoEvent arg0) {
		// TODO Auto-generated method stub

	}

}
