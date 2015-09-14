package com.ru.tgra.assignment02;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class BoxGraphic {

	ModelMatrix orientation;
	ArrayList<Box> boxes;
	boolean mouse = false;
	
	float x0;
	float y0;
	float x;
	float y;
	
	public BoxGraphic() 
	{
		orientation = new ModelMatrix();
		orientation.loadIdentityMatrix();

		boxes = new ArrayList<Box>();
	}
	
	public void update()
	{
		ModelMatrix.main.addTransformation(orientation.matrix);
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			if(mouse == false) {
				mouse = true;
				x0 = Gdx.input.getX();
				y0 = Gdx.graphics.getHeight() - Gdx.input.getY() ;
			}
			x = Gdx.input.getX();
			y = Gdx.graphics.getHeight() - Gdx.input.getY();
		}
		
		if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mouse == true) {
			mouse = false;
			Box box = new Box(x0, y0, x, y);
			boxes.add(box);
			
		}
		
	}
	
	public void display(int colorLoc)
	{
		ModelMatrix.main.pushMatrix();

		Gdx.gl.glUniform4f(colorLoc, 1.0f, 0.078f, 0.576f, 1);
		for(Box box : boxes){
			RectangleGraphic.drawSolidSquare(box);
		}
		if(mouse == true) {
			RectangleGraphic.drawSolidSquare(new Box(x0, y0, x, y));
		}
		
		ModelMatrix.main.popMatrix();
	}
}
