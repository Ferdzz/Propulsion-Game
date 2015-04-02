package com.stuntmania.propulsionGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.net.Socket;
import com.stuntmania.propulsionGame.screens.GameScreen;
import com.stuntmania.propulsionGame.screens.MainMenuScreen;
import com.stuntmania.propulsionGame.server.Server;
import com.stuntmania.propulsionGame.server.WarpController;

public class PropulsionGame extends Game {

	public static final String KEY = "8b6b2039251bc51bad90ac2ef0a3c86a6e4836e4f8e32de09f5e7a506fd0aeb9";
	public static final String SECRET = "848aa2e373324a227fa7ff43f71fce60892e177b53a6dcf42ee6e79f613efc73";
	
	public static PropulsionGame game;
//	public static Server server;
//	public static Socket clientSocket;
	public static WarpController warpController = new WarpController();
	public static boolean isHost;
	
	@Override
	public void create() {
		game = this;
		Gdx.graphics.setTitle("Propulsion Game");
		this.setScreen(new GameScreen());
	}
}
