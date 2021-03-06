package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;
import com.game.shift.input.Keyboard;

public class Obstacle extends Mob {

	private Keyboard input;	
	private int lado_terreno = 1;
	private int vx = 1, vy = 1;
	
	

	public Obstacle(Keyboard input, int lado){
		this.input = input;
		this.lado_terreno = lado;
		posRandom();
	}

	public void update(Screen screen){
		move(screen);
	}
	
	public void move(Screen screen){
		if(collisionObstacle(screen)) {
			vx*= -1;
			vy *= -1;
		}
		if(collisionBarra(screen)) {
			vx*= -1;
		}
		if( x + vx >= Background.width_p)  vx *= -1;
		if( x + vx <= 0)  vx *= -1;
		if( y + vy >= Background.height_p)  vy *= -1;
		if( y + vy <= 9)  vy *= -1;
		
		x += vx;
		y += vy;
		
		
	}
	
	protected boolean collisionBarra(Screen screen){
		try{
			if(screen.pixels[x+y*Background.width_p] == 0xFF56E0FF)
				return true;
		} catch (Exception e){
			return true;
		}
		return false;
	}
	
	protected boolean collisionObstacle(Screen screen){
		try{
			if(screen.pixels[x+y*Background.width_p] == 0xFF7FFF8E) 
				return true;
			if(screen.pixels[x+y*Background.width_p] == 0xFFFF6890) 
				return true;
		} catch (Exception e){
			return true;
		}
		return false;
	}

	private void posRandom(){
		if(lado_terreno == 1)
	/*		switch (s){
				case 1:
					x = (int)(Math.random()*Background.width_p/4-3)+Background.width_p/4;
					break;
				case 2:
					x = (int)(Math.random()*Background.width_p/4-3);
					break;
		/*		case 3:
					x = (int)(Math.random()*Background.width_p/2-3)+Background.width_p/4;
					break;
				case 4:
					x = (int)(Math.random()*Background.width_p/2-3)+Background.width_p/4;
					break;
				}*/
			x = (int)(Math.random()*(Barra.posX - 3));
		if(lado_terreno == 2)
			x = (int)(Math.random()* Barra.posX + 3) + Barra.posX;
		y =(int)(Math.random()*Background.height_p)+10;
	}
	
	public void render(Screen screen){
		if(lado_terreno == 1)
			screen.renderMob(x, y, Sprite.obstaculo_blue, 4);
		if(lado_terreno == 2)
			screen.renderMob(x, y, Sprite.obstaculo_pink, 4);
	}
}