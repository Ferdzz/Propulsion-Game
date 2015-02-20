package com.stuntmania.propulsionGame.screens;

import java.net.InetAddress;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
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
	private Texture parallax2;
	
	private Table firstTable;
	private Table joinTable;
	private Table hostTable;
	private Table optionsTable;
	private Table waitConnectTable;
	private Table creditsTable;
	
	private TextButton openHostTable;
	private TextButton openJoinTable;
	private TextButton openOptionsTable;
	private TextButton openCreditsTable;
	
	private TextButton exitGame;
	private TextButton startHosting;
	private TextButton join;
	
	private TextButton closeOptions;
	private TextButton closeHost;
	private TextButton closeJoin;
	private TextButton closeWaitConnect;
	private TextButton closeCredits;
	
	private TextField joinAddress;

	private Label joinInfo;
	private Label waitInfo;
	
	@Override
	public void show() {
		stage = new Stage();
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

		openHostTable = new TextButton("Host & play", skin);
		openHostTable.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				stage.addActor(hostTable);
				firstTable.remove();
			}
		});
		
		openJoinTable = new TextButton("Join a game", skin);
		openJoinTable.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addActor(joinTable);
				joinInfo.setText("Enter the IP of the server");
				firstTable.remove();
			}
		});
		
		openOptionsTable = new TextButton("Options", skin);
		openOptionsTable.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addActor(optionsTable);
				firstTable.remove();
			}
		});
		
		openCreditsTable = new TextButton("Credits", skin);
		openCreditsTable.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addActor(creditsTable);
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
		
		closeOptions = new TextButton("Close", skin);
		closeOptions.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addActor(firstTable);
				optionsTable.remove();
			}
		});	
		
		closeJoin = new TextButton("Cancel", skin);
		closeJoin.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addActor(firstTable);
				joinTable.remove();
			}
		});
		
		closeHost = new TextButton("Cancel", skin);
		closeHost.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addActor(firstTable);
				hostTable.remove();
			}
		});
		
		closeWaitConnect = new TextButton("Cancel", skin);
		closeWaitConnect.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				PropulsionGame.server.disposeSockets();
				stage.addActor(hostTable);
				waitConnectTable.remove();
			}
		});
		
		closeCredits = new TextButton("Close", skin);
		closeCredits.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.addActor(firstTable);
				creditsTable.remove();
			}
		});
		
		join = new TextButton("Join", skin);
		join.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					PropulsionGame.clientSocket = Gdx.net.newClientSocket(Protocol.TCP, joinAddress.getText(), Server.PORT, null);
				} catch (Exception e) {
					joinInfo.setText("Error joining game");
				}
			}
		});
		
		startHosting = new TextButton("2 players", skin);
		startHosting.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				PropulsionGame.server = new Server();
				PropulsionGame.server.start();
				stage.addActor(waitConnectTable);
				PropulsionGame.clientSocket = Gdx.net.newClientSocket(Protocol.TCP, "localhost", Server.PORT, null);
				hostTable.remove();
			}
		});
		
		joinInfo = new Label("Enter the IP of the server", skin);
		waitInfo = new Label("", skin);
		
		joinAddress = new TextField("", skin);
		joinAddress.setAlignment(Align.center);
		
		//Options table
		optionsTable = new Table();
		optionsTable.setFillParent(true);
		optionsTable.add(closeOptions).width(250);
		
		//Join table
		joinTable = new Table();
		joinTable.setFillParent(true);
		joinTable.add(joinInfo);
		joinTable.row();
		joinTable.add(joinAddress).width(400);
		joinTable.row();
		joinTable.add(join).width(250);
		joinTable.row();
		joinTable.add(closeJoin).width(250);
		
		//Host table
		hostTable = new Table();
		hostTable.setFillParent(true);
		hostTable.add(startHosting).width(250);
		hostTable.row();
		hostTable.add(closeHost).width(250);
		
		//First table
		firstTable = new Table();
		firstTable.setFillParent(true);
		firstTable.add(openHostTable).width(250);
		firstTable.row();
		firstTable.add(openJoinTable).width(250);
		firstTable.row();
		firstTable.add(openOptionsTable).width(250);
		firstTable.row();
		firstTable.add(openCreditsTable).width(250);
		firstTable.row();
		firstTable.add(exitGame).width(250);

		//Wait for the players to connect
		waitConnectTable = new Table() {
			@Override
			public void act(float delta) {
				waitInfo.setText(PropulsionGame.server.numberOfOnlinePlayer() + " / 2 players have joined");
				if(PropulsionGame.server.numberOfOnlinePlayer() == 2) {
					PropulsionGame.isHost = true;
					PropulsionGame.game.setScreen(new GameScreen());
				}
				super.act(delta);
			}
		};
		waitConnectTable.setFillParent(true);
		waitConnectTable.add(waitInfo);
		waitConnectTable.row();
		waitConnectTable.add(closeWaitConnect).width(250);
		
		//Credits table
		creditsTable = new Table();
		creditsTable.setFillParent(true);
		creditsTable.setSkin(skin);
		creditsTable.add("This game is powered by LibGDX, based on LWJGL");
		creditsTable.row();
		creditsTable.add("Programming: Frédéric Deschênes");
		creditsTable.row();
		creditsTable.add("Textures: Camille Archambault, Didier Camus, David Tremblay");
		creditsTable.row();
		creditsTable.add(closeCredits).width(250);
		
		//======================================================
		logo = new Texture(Gdx.files.internal("img/logos/1.png"));
		background = new Texture(Gdx.files.internal("img/mainmenu/background.png"));
		parallax = new Texture(Gdx.files.internal("img/mainmenu/parallax 1.png"));
		parallax2 = new Texture(Gdx.files.internal("img/mainmenu/parallax 2.png"));
		
		particle = new ParticleEffect();
		particle.load(Gdx.files.internal("particles/Title.p"), Gdx.files.internal("particles"));
		particle.setPosition((Gdx.graphics.getWidth() / 2) - (logo.getWidth() / 2) + 80, Gdx.graphics.getHeight() - logo.getHeight() + 25);
		particle.start();
		//======================================================
		
		stage.addActor(firstTable);
		Gdx.input.setInputProcessor(stage);
	//	stage.setDebugAll(true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(parallax, Gdx.input.getX() / 11 - 100, Gdx.input.getY() / 11 - 100 , Gdx.graphics.getWidth() * 1.3F, Gdx.graphics.getHeight() * 1.3F);
		batch.draw(parallax2, Gdx.input.getX() / 6 - 200, Gdx.input.getY() / 6 - 100 , Gdx.graphics.getWidth() * 1.2F, Gdx.graphics.getHeight() * 1.2F);
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
