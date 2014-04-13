package de.techlogic.heroes.util;

import java.util.HashMap;
import java.util.Set;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import de.techlogic.heroes.model.Bullet;
import de.techlogic.heroes.model.Entity;

public class CollisionListener implements ContactListener {

	private HashMap<Bullet, Entity> contacts;

	public CollisionListener() {
		contacts = new HashMap<Bullet, Entity>();
	}

	@Override
	public void beginContact(Contact contact) {

		Object a = contact.getFixtureA().getBody().getUserData();
		Object b = contact.getFixtureB().getBody().getUserData();

		boolean testA = a instanceof Bullet;
		boolean testB = b instanceof Bullet;

		if (testA != testB) {

			if (testA == true) {
				if (((Bullet) a).getEntity() != (Entity) b) {
					contacts.put((Bullet) a, (Entity) b);
				}
			} else {
				if (((Bullet) b).getEntity() != (Entity) a) {
					contacts.put((Bullet) b, (Entity) a);
				}
			}

		}
	}

	@Override
	public void endContact(Contact contact) {
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}

	public void informBullets() {
		Set<Bullet> s = contacts.keySet();
		for (Bullet b : s) {
			b.collidateWith(contacts.get(b));

		}

		contacts.clear();

	}
}
