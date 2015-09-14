package com.ru.tgra.assignment02;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public class BoxGraphic {

	ModelMatrix orientation;
	ArrayList<Box> boxes;
	
	Box box;
	
	public BoxGraphic() 
	{
		orientation = new ModelMatrix();
		orientation.loadIdentityMatrix();
		
		box = new Box();
		box.x0 = 200.0f;
		box.y0 = 200.0f;
		box.x = 300.0f;
		box.y = 300.0f;
		boxes = new ArrayList<Box>();
	}
	
	public void update()
	{
		ModelMatrix.main.addTransformation(orientation.matrix);
		
		
		
	}
	
	public void display(int colorLoc)
	{
		ModelMatrix.main.pushMatrix();

		Gdx.gl.glUniform4f(colorLoc, 1.0f, 0.078f, 0.576f, 1);
		RectangleGraphic.drawSolidSquare(box);
		
		ModelMatrix.main.popMatrix();
	}
}
