package de.techlogic.heroes.model;

import com.badlogic.gdx.math.Vector2;

import de.techlogic.heroes.view.Space;

public class Rockets extends Bullet {

	private MoveableEntity enemy;
	public Rockets(Space space, MoveableEntity entity,MoveableEntity enemy, float witdh,
			float height, float speed) {
		super(space, entity, witdh, height, speed);
		this.enemy = enemy;
		LIFETIME = 3500;
		damage = 3;
		
		
	}
	
	@Override
	protected void applyForce() {
		Vector2 temp = enemy.getPostition().cpy();
		temp.sub(this.getPostition());
		temp.nor().scl(speed);
		
		body.setLinearVelocity(temp);
		float angle = getPostition().angle()-enemy.getPostition().angle();
		angle = new Double (Math.toRadians(new Double(angle))).floatValue();
		body.setTransform(body.getPosition(), angle);
	}
	
}
