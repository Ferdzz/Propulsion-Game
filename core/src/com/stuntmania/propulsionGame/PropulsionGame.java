package com.stuntmania.propulsionGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.stuntmania.propulsionGame.screens.MainMenuScreen;

public class PropulsionGame extends Game {

	public static PropulsionGame game;
	
	@Override
	public void create() {
		game = this;
		Gdx.graphics.setTitle("Propulsion Game");
		this.setScreen(new MainMenuScreen());
	}
}
