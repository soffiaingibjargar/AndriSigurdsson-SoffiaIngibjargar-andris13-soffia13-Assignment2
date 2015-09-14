package com.ru.tgra.assignment02;

import java.util.ArrayList;

public class BoxGraphic {

	ModelMatrix orientation;
	ArrayList<Box> boxes;
	
	public BoxGraphic() 
	{
		orientation = new ModelMatrix();
		orientation.loadIdentityMatrix();
		
		boxes = new ArrayList<Box>();
	}
	
	public void display(int colorLoc)
	{
		ModelMatrix.main.pushMatrix();
		ModelMatrix.main.addTransformation(orientation.matrix);
		
		ModelMatrix.main.popMatrix();
	}
}
