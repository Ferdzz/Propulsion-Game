package com.stuntmania.propulsionGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.net.Socket;
import com.stuntmania.propulsionGame.screens.MainMenuScreen;
import com.stuntmania.propulsionGame.server.Server;

public class PropulsionGame extends Game {

	public static PropulsionGame game;
	public static Server server;
	public static Socket clientSocket;
	public static boolean isHost;
	
	@Override
	public void create() {
		game = this;
		Gdx.graphics.setTitle("Propulsion Game");
		this.setScreen(new MainMenuScreen());
	}
}
