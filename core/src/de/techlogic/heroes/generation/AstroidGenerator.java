package de.techlogic.heroes.generation;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;

public class AstroidGenerator {

	
	public Texture createAstroid(){
		int radius = 50;
		
		Pixmap map = new Pixmap(radius*2, radius*2, Format.RGB888);
		map.setColor(Color.WHITE);
		map.fillCircle(radius, radius,radius);
		map.setColor(Color.CLEAR);
		
		int x,y;
		x=0;
		y=0;
		//x = radius+ sinus(grad)*radius
		//y = radius+ cosinus(180-grad)*radius
		//while grad < 360
		map.fillCircle(x, y,1);
		
		Texture texture = new Texture(map);
		return texture;
	}
}
