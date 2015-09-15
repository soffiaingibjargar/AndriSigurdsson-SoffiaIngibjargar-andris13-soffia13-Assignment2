package com.ru.tgra.assignment02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.Random;

public class Goal {
	boolean on = false;
	boolean win = false;
	float wintime;
	public ModelMatrix orientation;
    Random rand;
	public float x;
	public float y;

	public Goal()
	{
		wintime = 0;
		this.orientation = new ModelMatrix();
		this.orientation.loadIdentityMatrix();
        rand = new Random();
		
	}

	public void win(){
		wintime = 30;
		win = true;
	}
	
	public void display(int colorLoc)
	{

		if(Gdx.input.isKeyJustPressed(Input.Keys.A) && !on){
			on = true;
			orientation.addTranslation(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0);
			x = Gdx.input.getX();
			y = Gdx.graphics.getHeight() - Gdx.input.getY();
		}
		
		if(on){
			if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
				on = false;
				orientation.loadIdentityMatrix();
			}
			else {
				ModelMatrix.main.pushMatrix();
				
				ModelMatrix.main.addTransformation(orientation.matrix);
				if(win){
					Gdx.gl.glUniform4f(colorLoc, rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1);
                    wintime--;
				} else {
                    Gdx.gl.glUniform4f(colorLoc, 0.780f, 0.082f, 0.522f, 1);
                }

                if (wintime == 0 && win){
                    win = false;
                }
				ModelMatrix.main.addScale(40.0f, 40.0f, 1.0f);
				ModelMatrix.main.setShaderMatrix();
				CircleGraphic.drawSolidCircle();
				
				ModelMatrix.main.popMatrix();
				
			}
		}
	}

}