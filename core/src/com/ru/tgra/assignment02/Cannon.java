package com.ru.tgra.assignment02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

public class Cannon {
	
	ModelMatrix orientation;
	Vector3D velocity;
	
	Box box;
	ArrayList<CannonBall> balls;
	
	public Cannon()
	{
		orientation = new ModelMatrix();
		orientation.loadIdentityMatrix();
		orientation.addTranslation(Gdx.graphics.getWidth() / 2, 60.0f, 0);
		velocity = new Vector3D(0, 0, 0);
        balls = new ArrayList<CannonBall>();
		
		box = new Box();
		box.x0 = -0.50f;
		box.y0 = -0.50f;
		box.x = 0.50f;
		box.y = 0.50f;
	}
	
	public void update(float deltaTime)
	{
		//Rotation for the cannon
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && (orientation.getB().y > 0 || orientation.getB().x > 0)) {
			orientation.addRotationZ(90.0f * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (orientation.getB().y > 0 || orientation.getB().x < 0)) {
			orientation.addRotationZ(-90.0f * deltaTime);
		}
		
		//The cannonball
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            balls.add(new CannonBall(orientation));
		}
	}
	
	public void display(int colorLoc, float deltaTime)
	{
        for (CannonBall ball : balls){
            ball.display(colorLoc, deltaTime);
        }

		drawCannon(colorLoc);
	}
	
	private void drawCannon(int colorLoc)
	{
		ModelMatrix.main.pushMatrix();
		
		ModelMatrix.main.addTransformation(orientation.matrix);
		ModelMatrix.main.addScale(20.0f, 20.0f, 1);
		
		Gdx.gl.glUniform4f(colorLoc, 0.35f, 0.35f, 0.35f, 1);
		ModelMatrix.main.pushMatrix();
		ModelMatrix.main.addScale(2.0f, 0.5f, 1);
		ModelMatrix.main.addTranslation(0.0f, -0.9f, 0);
		ModelMatrix.main.setShaderMatrix();
		RectangleGraphic.drawSolidSquare(box);
		ModelMatrix.main.popMatrix();
		
		Gdx.gl.glUniform4f(colorLoc, 0.35f, 0.35f, 0.35f, 1);
		ModelMatrix.main.pushMatrix();
		ModelMatrix.main.addScale(0.4f, 2.5f, 1);
		ModelMatrix.main.addTranslation(2.0f, -0.2f, 0);
		ModelMatrix.main.setShaderMatrix();
		RectangleGraphic.drawSolidSquare(box);
		ModelMatrix.main.popMatrix();
		
		Gdx.gl.glUniform4f(colorLoc, 0.35f, 0.35f, 0.35f, 1);
		ModelMatrix.main.pushMatrix();
		ModelMatrix.main.addScale(0.4f, 2.5f, 1);
		ModelMatrix.main.addTranslation(-2.0f, -0.2f, 0);
		ModelMatrix.main.setShaderMatrix();
		RectangleGraphic.drawSolidSquare(box);
		ModelMatrix.main.popMatrix();
		
		Gdx.gl.glUniform4f(colorLoc, 0.412f, 0.412f, 0.412f, 1);
		ModelMatrix.main.pushMatrix();
		ModelMatrix.main.addScale(1.0f, 5.0f, 1);
		ModelMatrix.main.setShaderMatrix();
		RectangleGraphic.drawSolidSquare(box);
		ModelMatrix.main.popMatrix();
		
		
		ModelMatrix.main.popMatrix();
	}

}