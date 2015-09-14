package com.ru.tgra.assignment02;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class BoxGraphic {

	ModelMatrix orientation;
	ArrayList<Box> boxes;
	Box box;
	boolean mouse = false;
	
	public BoxGraphic() 
	{
		orientation = new ModelMatrix();
		orientation.loadIdentityMatrix();

		box = new Box();
		boxes = new ArrayList<Box>();
	}
	
	public void update()
	{
		ModelMatrix.main.addTransformation(orientation.matrix);
		
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			if(mouse == false) {
				System.out.println(Gdx.input.getX());
				System.out.println(Gdx.graphics.getHeight() - Gdx.input.getY());
				mouse = true;
				box.x0 = Gdx.input.getX();
				box.y0 = Gdx.graphics.getHeight() - Gdx.input.getY() ;
			}
			box.x = Gdx.input.getX();
			box.y = Gdx.input.getY();
			
		}
		
		if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mouse == true) {
			mouse = false;
			
		}
		
	}
	
	public void display(int colorLoc)
	{
		ModelMatrix.main.pushMatrix();

		Gdx.gl.glUniform4f(colorLoc, 1.0f, 0.078f, 0.576f, 1);
		for(Box box : boxes){
			RectangleGraphic.drawSolidSquare(box);
		}

		RectangleGraphic.drawSolidSquare(box);
		
		ModelMatrix.main.popMatrix();
	}
}
