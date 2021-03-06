package com.game.shift;
import com.game.shift.graficos.Sprite;

public class Screen {
	public int width, height;
	private int xOffset, yOffset;
	public int[] pixels;
	
	public Screen(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
	}
	
	public void clear(){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = 0;
		}
	}
	
	public void render(){
		for (int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				pixels[x+y*width] = Sprite.universe.pixels[(x&15)+(y&15)* Sprite.universe.SIZE];
			}
		}
		for ( int y = 0; y < 9; y++){
			for(int x = 0; x < width; x++){
				pixels[x+y*width] = Sprite.informacion.pixels[(x&15)+(y&15)* Sprite.informacion.SIZE];
			}
		}
	}
	
	public void renderMob(int xp, int yp,  Sprite sprite, int px){
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y< px;y++){
			int ya= y+yp;
			for(int x = 0; x < px; x++){
				int xa = x +xp;
				if(xa <-px || xa >= width || ya < 0 || ya>= height) break;
				if(xa < 0) xa = 0;
				int col = sprite.pixels[x+y*px];
				if (col != 0xffffffff) pixels[xa+ya*width] = col;
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
