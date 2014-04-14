package de.techlogic.heroes.generation;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class AstroidGenerator {

	
	public Texture createAstroid(){
		int radius = 10;
		final Color[] ASTROIDCOLORS= {Color.GRAY,Color.LIGHT_GRAY,Color.DARK_GRAY,new Color(139f/255f,69f/255f,19f/255f,1f),new Color(139f/255f,119f/255f,101f/255f,1f),new Color(139f/255f,125f/255f,107f/255f,1f),new Color(139f/255f,90f/255f,43f/255f,1f),new Color(238f/255f,223f/255f,204f/255f,1f)};
		Random random = new Random();
		
		Pixmap map = new Pixmap(radius*2, radius*2, Format.RGBA8888);
		Pixmap.setBlending(Blending.None);
		map.setColor(Color.CLEAR);
		map.fill();
		map.setColor(ASTROIDCOLORS[random.nextInt(ASTROIDCOLORS.length)]);
		map.fillCircle(radius, radius,radius);
		
		
		int x,y;
		x=0;
		y=0;
		
		map.setColor(Color.LIGHT_GRAY);
		Vector2 vector = new Vector2(radius, radius);
		for(int i =random.nextInt(25)+25  ;i>0;i--){
			Vector2 pos = new Vector2(random.nextInt(2*radius), random.nextInt(2*radius));
			if(pos.dst(vector)<radius-1){
			map.setColor(ASTROIDCOLORS[random.nextInt(ASTROIDCOLORS.length)]);
			map.fillCircle((int)pos.x, (int)pos.y,1);
			}
		}
		
		map.setColor(Color.CLEAR);
		double degree = 0;
		while(degree < 360){
		x = (int) (Math.sin(degree)*radius)+radius;
		y = (int) (Math.cos(degree)*radius)+radius;
		map.fillCircle(x, y,1);
		degree += random.nextInt(5)+10;
		}
		map.setColor(Color.CLEAR);
		
		Texture texture = new Texture(map);
		
		
		
		return texture;
	}
}
