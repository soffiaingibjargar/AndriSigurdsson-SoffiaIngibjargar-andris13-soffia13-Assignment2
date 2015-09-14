package com.ru.tgra.assignment02;

import com.badlogic.gdx.Gdx;

public class CannonBall {
	
	public ModelMatrix orientation;
	public Vector3D velocity;
	public boolean visible = false;
	
	public CannonBall()
	{
		
	}
	
	public void update(float deltaTime)
	{
		orientation.AddTranslationBaseCoords(velocity.x * deltaTime, velocity.y * deltaTime, 0);
	}
	
	public void display(int colorLoc)
	{
		ModelMatrix.main.pushMatrix();
		
		ModelMatrix.main.addTransformation(orientation.matrix);
		Gdx.gl.glUniform4f(colorLoc, 1.0f, 1.0f, 0.0f, 1);
		ModelMatrix.main.addScale(9.0f, 9.0f, 1.0f);
		ModelMatrix.main.setShaderMatrix();
		CircleGraphic.drawSolidCircle();
		
		ModelMatrix.main.popMatrix();
	}

}