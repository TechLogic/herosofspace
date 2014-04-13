package de.techlogic.heroes.model;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import de.techlogic.heroes.view.Space;

public class Bullet extends MoveableEntity {

	private Entity entity;
	protected int damage;
	private final long CREATIONTIME;
	protected int LIFETIME;  

	public Bullet(Space space, MoveableEntity entity, float witdh,
			float height, float speed) {
		super(space, witdh, height, speed);
		CREATIONTIME = System.currentTimeMillis();

		this.entity = entity;
		texture = new Texture("data/bullet.png");
		bodyDef.gravityScale = 0;
		bodyDef.bullet = true;
		bodyDef.position.set(entity.getPostition());
		bodyDef.angle = entity.getRotation();
		bodyDef.active = true;
		bodyDef.awake = true;
		
		fixtureDef.filter.categoryBits =(short) 20;

		velocity = ((Shootable) entity).getDirection().cpy();
		velocity.nor();
		velocity.scl(speed);

		bodyDef.position.add(velocity.cpy().nor()
				.scl(height / 2 + entity.getFixture().getShape().getRadius()));

		fixtureDef.density = 0;
		fixtureDef.restitution = 0;
		fixtureDef.isSensor = true;
		fixtureDef.friction = 0;
		PolygonShape s = new PolygonShape();
		s.setAsBox(width / 2, height / 2);
		fixtureDef.shape = s;
		damage = 1;
		LIFETIME = 2500;

	}
	
	@Override
	public void createLights(RayHandler rayHandler) {
		PointLight p = new PointLight(rayHandler, 1000);
		p.setColor(0,0,1f,1f);
		p.attachToBody(body, 0, 0);
		p.setSoft(true);
		p.setDistance(2.5f);
		lights = new  Light[]{p};
}

	public int getDamage() {
		return damage;
	}

	public Entity getEntity() {
		return entity;
	}

	@Override
	public void setBody(Body body) {
		super.setBody(body);
		body.setUserData(this);

	}

	public void collidateWith(Entity e) {
		destroy = true;

		e.hit(damage);

	}

	@Override
	public void update() {
		super.update();
		applyForce();
		if (System.currentTimeMillis() - CREATIONTIME >= LIFETIME) {
			destroy();
		}
	}
	
	protected void applyForce(){
		body.applyForce(velocity, body.getPosition(), true);
	}

	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
	}
}
