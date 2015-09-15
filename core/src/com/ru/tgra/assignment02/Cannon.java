package com.ru.tgra.assignment02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

public class Cannon {
	private ModelMatrix orientation;
    private int score;
    private int scoreFive;
	private Vector3D velocity;
	private Box box;
	private ArrayList<CannonBall> balls;
    private float power;
    private boolean charging;

	public Cannon()
	{
        score = 0;
        scoreFive = 0;
		orientation = new ModelMatrix();
		orientation.loadIdentityMatrix();
		orientation.addTranslation(Gdx.graphics.getWidth() / 2, 60.0f, 0);
		velocity = new Vector3D(0, 0, 0);
        balls = new ArrayList<CannonBall>();
        power = 0.0f;
        charging = false;
		box = new Box(-0.50f, -0.50f, 0.50f, 0.50f);
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
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            charging = true;
            power += 0.1;
            power = Math.min(10.0f, power);
		} else if (charging){
            charging = false;
            balls.add(new CannonBall(orientation, power));
            power = 0.0f;
        }

        if(score >= 5){
            score -= 5;
            scoreFive += 1;
        }

	}
	
	public void display(int colorLoc, float deltaTime, CircleGraphic circleGraph, BoxGraphic boxGraph, LineGraphic lineGraph, Goal goal)
	{
        for (CannonBall ball : balls){
        	if(ball.live){
                score += ball.display(colorLoc, deltaTime, circleGraph, boxGraph, lineGraph, goal);
        	}
        	else ball = null;
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

        for (int i = 0; i < score; i++){
            Gdx.gl.glUniform4f(colorLoc, 0.95f, 0.85f, 0.00f, 1);
            ModelMatrix.main.pushMatrix();
            ModelMatrix.main.addScale(0.2f, 1f, 1);
            ModelMatrix.main.addTranslation(5.0f + (2 * i) + 5 , -0.2f, 0);
            ModelMatrix.main.setShaderMatrix();
            RectangleGraphic.drawSolidSquare(box);
            ModelMatrix.main.popMatrix();
        }

        for (int i = 0; i < scoreFive; i++){
            Gdx.gl.glUniform4f(colorLoc, 0.95f, 0.0f, 0.00f, 1);
            ModelMatrix.main.pushMatrix();
            ModelMatrix.main.addScale(0.2f, 1f, 1);
            ModelMatrix.main.addTranslation(-10.0f - (4 * i) - 5, -0.2f, 0);
            ModelMatrix.main.setShaderMatrix();
            RectangleGraphic.drawSolidSquare(box);
            ModelMatrix.main.addScale(3.6f, 1f, 1);
            ModelMatrix.main.addRotationZ(45.0f);
            ModelMatrix.main.setShaderMatrix();
            RectangleGraphic.drawSolidSquare(box);
            ModelMatrix.main.popMatrix();
        }

		Gdx.gl.glUniform4f(colorLoc, 0.35f, 0.35f, 0.35f, 1);
		ModelMatrix.main.pushMatrix();
		ModelMatrix.main.addScale(0.4f, 2.5f, 1);
		ModelMatrix.main.addTranslation(-2.0f, -0.2f, 0);
		ModelMatrix.main.setShaderMatrix();
		RectangleGraphic.drawSolidSquare(box);
		ModelMatrix.main.popMatrix();

        Gdx.gl.glUniform4f(colorLoc, 1.0f, 1.0f - (1.0f*(power/8)), 0, 1);
        ModelMatrix.main.pushMatrix();
        ModelMatrix.main.addScale(0.8f, 15.0f * (power/2), 1);
        ModelMatrix.main.addTranslation(0, 0.5f, 1);
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