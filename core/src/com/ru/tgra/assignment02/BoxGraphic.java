package com.ru.tgra.assignment02;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class BoxGraphic {

	ModelMatrix orientation;
	ArrayList<Box> boxes;
    ArrayList<Line> lines;
	boolean mouse = false;
	
	private float x0, y0, x, y;


	public BoxGraphic()
	{
		orientation = new ModelMatrix();
		orientation.loadIdentityMatrix();
        lines = new ArrayList<Line>();
		boxes = new ArrayList<Box>();
        Box box = new Box(-1, -1, Gdx.graphics.getWidth() + 1, Gdx.graphics.getHeight() + 1);
        for (Line line : box.getLines()) lines.add(line);
	}
	
	public void update()
	{
		ModelMatrix.main.addTransformation(orientation.matrix);
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			if(!mouse) {
				mouse = true;
				x0 = Gdx.input.getX();
				y0 = Gdx.graphics.getHeight() - Gdx.input.getY() ;
			}
			x = Gdx.input.getX();
			y = Gdx.graphics.getHeight() - Gdx.input.getY();
		}
		
		if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mouse) {
			mouse = false;
			Box box = new Box(x0, y0, x, y);
            for (Line line : box.getLines()) lines.add(line);
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
		if(mouse) {
			RectangleGraphic.drawSolidSquare(new Box(x0, y0, x, y));
		}
		
		ModelMatrix.main.popMatrix();
	}

    public ArrayList<Line> getLines(){
        return lines;
    }
}
