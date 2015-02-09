package com.stuntmania.propulsionGame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stuntmania.propulsionGame.PropulsionGame;
import com.stuntmania.propulsionGame.server.Server;

public class MainMenuScreen implements Screen {

	private Skin skin;
	private Stage stage;
	private SpriteBatch batch;
	private ParticleEffect particle;
	
	private Texture logo;
	private Texture background;
	private Texture parallax;
	
	private Table firstTable;
	private Table optionsTable;
	
	private TextButton playNow;
	private TextButton options;
	private TextButton exitGame;
	
	private TextButton closeOptions;

	@Override
	public void show() {
		stage = new Stage();
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

		//First menu
		firstTable = new Table();
		firstTable.setFillParent(true);
		playNow = new TextButton("Play Now", skin);
		playNow.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				new Server().start();
				PropulsionGame.game.setScreen(new GameScreen(true));
			}
		});
		options = new TextButton("Options", skin);
		options.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addActor(optionsTable);
				firstTable.remove();
			}
		});
		exitGame = new TextButton("Exit Game", skin);
		exitGame.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		firstTable.add(playNow);
		firstTable.row();
		firstTable.add(options);
		firstTable.row();
		firstTable.add(exitGame);
		firstTable.setY(10 / Gdx.graphics.getHeight());
		stage.addActor(firstTable);
		
		//Options menu
		optionsTable = new Table();
		optionsTable.setFillParent(true);
		closeOptions = new TextButton("Close", skin);
		closeOptions.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addActor(firstTable);
				optionsTable.remove();
			}
		});	
		optionsTable.add(closeOptions);
		
		logo = new Texture(Gdx.files.internal("img/logos/1.png"));
		background = new Texture(Gdx.files.internal("img/background.png"));
		parallax = new Texture(Gdx.files.internal("img/parallax.png"));
		
		particle = new ParticleEffect();
		particle.load(Gdx.files.internal("particles/Title.p"), Gdx.files.internal("particles"));
		particle.setPosition((Gdx.graphics.getWidth() / 2) - (logo.getWidth() / 2) + 80, Gdx.graphics.getHeight() - logo.getHeight() + 25);
		particle.start();

		Gdx.input.setInputProcessor(stage);
		stage.setDebugAll(true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(parallax, Gdx.input.getX() / 10 - 100, Gdx.input.getY() / 10 - 100 , parallax.getWidth(), parallax.getHeight());
		particle.draw(batch, delta * 2 / 3);
		batch.draw(logo, (Gdx.graphics.getWidth() / 2) - (logo.getWidth() / 2), Gdx.graphics.getHeight() - logo.getHeight());
		batch.end();

		stage.act(delta);
		stage.draw();
		
		if(particle.isComplete()) {
			particle.start();
		}
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
		particle.setPosition((Gdx.graphics.getWidth() / 2) - (logo.getWidth() / 2) + 80, Gdx.graphics.getHeight() - logo.getHeight() + 25);
		batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {
		this.dispose();
	}

	@Override
	public void dispose() {
		stage.dispose();
		particle.dispose();
		batch.dispose();
		skin.dispose();
		logo.dispose();
		background.dispose();
	}
}
