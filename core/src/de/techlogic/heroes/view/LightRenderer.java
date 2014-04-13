package de.techlogic.heroes.view;



import box2dLight.Light;
import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

import de.techlogic.heroes.model.Entity;

public class LightRenderer {

	public static RayHandler rayHandler;
	private OrthographicCamera cam;

	public LightRenderer(Space space, OrthographicCamera cam) {
		this.cam = cam;
		rayHandler = new RayHandler(space.getWorld());
		
	
		rayHandler.setAmbientLight(new Color(0, 0, 0, 0.5f));
		for (Entity e : space.getEntities()) {
			e.createLights(rayHandler);
	

		}
		space.getShip().createLights(rayHandler);
		rayHandler.setBlur(true);
		rayHandler.setBlurNum(2);
		Light.setContactFilter((short)10, (short)10, (short)10);
		Gdx.gl.glDepthMask(false);

	}

	public RayHandler getRayHandler() {
		return rayHandler;
	}

	public void render() {
		rayHandler.setCombinedMatrix(cam.combined, cam.position.x,
				cam.position.y, cam.viewportWidth / cam.zoom,
				cam.viewportHeight / cam.zoom);
		
		//rayHandler.updateAndRender();
		
	}

}
