package de.techlogic.heroes.model;

import box2dLight.Light;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import de.techlogic.heroes.view.Space;

public abstract class Entity {
	protected float width;
	protected float height;
	protected BodyDef bodyDef;
	protected FixtureDef fixtureDef;
	protected Body body;
	protected Fixture fixture;
	protected Texture texture;
	protected Space space;
	protected int health;
	protected boolean destroy = false;
	protected Light[] lights;

	public Fixture getFixture() {
		return fixture;
	}

	public boolean getDestory() {
		return destroy;
	}

	public void setBody(Body body) {
		this.body = body;
		fixture = body.createFixture(fixtureDef);
		space.getEntities().add(this);
	}

	public Body getBody() {
		return body;
	}

	public int getHealth() {
		return health;
	}

	public Entity(Space space, float width, float height) {
		super();
		this.width = width;
		this.height = height;
		bodyDef = new BodyDef();
		fixtureDef = new FixtureDef();
		this.space = space;
		health = 1;
		fixtureDef.filter.categoryBits = (short)10;
	}

	public BodyDef getBodyDef() {
		return bodyDef;
	}

	public FixtureDef getFixtureDef() {
		return fixtureDef;
	}

	/**
	 * @return the postition
	 */
	public Vector2 getPostition() {
		return body.getPosition();
	}

	/**
	 * @param postition
	 *            the postition to set
	 */
	public void setPostition(Vector2 position) {
		body.setTransform(position, body.getAngle());
	}

	/**
	 * @return the witdh
	 */
	public float getWitdh() {
		return width;
	}

	/**
	 * @param witdh
	 *            the witdh to set
	 */
	public void setWitdh(float witdh) {
		this.width = witdh;
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	public void draw(SpriteBatch batch) {
		batch.draw(texture, getPostition().x, getPostition().y, getWitdh(),
				getHeight());
	}

	public void update() {

	}

	public void destroy() {
		body.getWorld().destroyBody(body);
		space.getEntities().remove(this);
		if (lights != null) {
			for (Light l : lights) {
				l.remove();
			}
		}
	}

	public void hit(int damage) {
		health -= damage;
		if (health <= 0) {
			destroy = true;
		}
	}

	public void createLights(RayHandler rayHandler) {
	}

}
