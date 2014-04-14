package de.techlogic.heroes.view;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import de.techlogic.heroes.HeroesOfSpace;
import de.techlogic.heroes.generation.AstroidGenerator;
import de.techlogic.heroes.model.Astroid;
import de.techlogic.heroes.model.Enemy;
import de.techlogic.heroes.model.Entity;
import de.techlogic.heroes.model.Ship;
import de.techlogic.heroes.model.Wall;
import de.techlogic.heroes.util.CollisionListener;

public class Space {
	private World world;
	private Ship ship;
	private List<Entity> entities;
	private CollisionListener listener;
	private Enemy e;
	private int levelWidth;

	public Space(HeroesOfSpace game) {
		world = new World(new Vector2(0, 0), true);
		listener = new CollisionListener();
		world.setContactListener(listener);
		levelWidth = 5000;
		entities = new CopyOnWriteArrayList<Entity>();

		ship = new Ship(this, 10, 10, 10, 0);
		ship.setBody(world.createBody(ship.getBodyDef()));
		Gdx.input.setInputProcessor(new InputHandler(this));
		entities.add(ship);
		Random rnd = new Random();
		AstroidGenerator generator = new AstroidGenerator();

		for (int i = 0; i <= rnd.nextInt(500) + 1000; i++) {
			Vector2 pos = new Vector2(rnd.nextInt(levelWidth / 2 - 200),
					rnd.nextInt(levelWidth / 2 - 200));
			if (rnd.nextBoolean()) {
				pos.x *= -1;
			}
			if (rnd.nextBoolean()) {
				pos.y *= -1;
			}
			Texture texture = generator.createAstroid();
			Astroid a = new Astroid(this, pos, rnd.nextInt(10),rnd.nextFloat(), 0,texture);
			a.setBody(world.createBody(a.getBodyDef()));

		}

//		for (int i = 0; i <= rnd.nextInt(100) + 100; i++) {
//			Vector2 pos = new Vector2(rnd.nextInt(levelWidth / 2 - 200),
//					rnd.nextInt(levelWidth / 2 - 200));
//			if (rnd.nextBoolean()) {
//				pos.x *= -1;
//			}
//			if (rnd.nextBoolean()) {
//				pos.y *= -1;
//			}
//			e = new Enemy(this, pos, 12.5f, 12.5f, 10,rnd.nextFloat()) {
//			};

		e = new Enemy(this, new Vector2(100, 100),12.5f,12.5f,10,1) {
		};
		
			e.setBody(world.createBody(e.getBodyDef()));
			createLevelBorders();
	//	}

	}

	public int getLevelWidth() {
		return levelWidth;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public Ship getShip() {
		return ship;
	}

	private void createLevelBorders() {
		Wall left = new Wall(this, new Vector2(-1 * levelWidth / 2, 0), 1,
				levelWidth / 2);
		Wall right = new Wall(this, new Vector2(levelWidth / 2, 0), 1,
				levelWidth / 2);
		Wall down = new Wall(this, new Vector2(0, -1 * levelWidth / 2),
				levelWidth / 2, 1);

		Wall up = new Wall(this, new Vector2(0, levelWidth / 2),
				levelWidth / 2, 1);

		left.setBody(world.createBody(left.getBodyDef()));
		right.setBody(world.createBody(right.getBodyDef()));
		up.setBody(world.createBody(up.getBodyDef()));
		down.setBody(world.createBody(down.getBodyDef()));

	}

	public void update() {

		world.step(1 / 60f, 6, 2);
		ship.update();
		for (Entity e : entities) {
			e.update();
		}
		listener.informBullets();
		for (Entity e : entities) {
			if (e.getDestory()) {
				e.destroy();
			}
		}

	}

	public Enemy getE() {
		return e;
	}

	public void dispose() {

	}

	public World getWorld() {
		return world;
	}
}
