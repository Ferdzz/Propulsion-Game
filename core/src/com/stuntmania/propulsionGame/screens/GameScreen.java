package com.stuntmania.propulsionGame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.stuntmania.propulsionGame.BodyEditorLoader;
import com.stuntmania.propulsionGame.PropulsionGame;

public class GameScreen implements Screen, InputProcessor {

	private Body cthulu;
	private World world;
	private OrthographicCamera cam;
	private SpriteBatch batch;
	private Sprite image;
	private Box2DDebugRenderer debug;

	private boolean charging;
	private float charge;

	@Override
	public void show() {
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		world = new World(new Vector2(0, 0), true);
		batch = new SpriteBatch();

		BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("test.json"));
		BodyDef bd = new BodyDef();
		bd.position.set(600, 600);
		bd.type = BodyType.DynamicBody;
		bd.linearDamping = 0.5F;
		FixtureDef fd = new FixtureDef();
		fd.density = 0.0001F;
		fd.friction = 0.1F;
		fd.restitution = 0.2f;

		BodyDef bdw = new BodyDef();
		bdw.position.set(300, 300);
		bdw.type = BodyType.StaticBody;
		FixtureDef fdw = new FixtureDef();
		fdw.density = 1F;
		Shape shape = new PolygonShape();
		((PolygonShape) shape).setAsBox(30, 30);
		fdw.shape = shape;

		image = new Sprite(new Texture(Gdx.files.internal("img/cthulu.png")));
		cthulu = world.createBody(bd);
//		world.setContactListener(new Test());
		loader.attachFixture(cthulu, "Name", fd, image.getWidth());

		world.createBody(bdw).createFixture(fdw);

		debug = new Box2DDebugRenderer();

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		update(delta);
		draw(delta);
	}

	// UPDATING THE GAME -----------
	public void update(float delta) {
		world.step(delta, 10, 10);
		cam.update();

		image.setOriginCenter();
		image.setCenter(cthulu.getPosition().x, cthulu.getPosition().y);
		image.setRotation((float) Math.toDegrees(cthulu.getAngle()));

		if (charging) {
			charge += delta * 10000;
		}
	}

	// RENDERING THE GAME ----------
	public void draw(float delta) {
		batch.setProjectionMatrix(cam.combined);
			batch.begin();
			image.draw(batch);
			batch.end();
		

		debug.render(world, cam.combined);
	}

	@Override
	public void resize(int width, int height) {
		cam.setToOrtho(false, width, height);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Buttons.LEFT) {
			charging = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Buttons.LEFT) {
			Vector3 vec = new Vector3(screenX, screenY, 0);
			cam.unproject(vec);
			cthulu.applyForceToCenter(new Vector2(vec.x - cthulu.getPosition().x, vec.y - cthulu.getPosition().y).nor().scl(-charge), true);
			charging = false;
			charge = 0;
			return true;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
