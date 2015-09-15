package com.ru.tgra.assignment02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class CannonBall {
	
	public ModelMatrix orientation;
	public Vector3D velocity;

	public CannonBall(ModelMatrix orientation)
	{
		this.orientation = new ModelMatrix();
		this.orientation.loadIdentityMatrix();
		this.orientation.addTransformation(orientation.matrix);
		this.orientation.addTranslation(0, 50.0f, 0);
		velocity = orientation.getB();
		velocity.scale(200.0f);
	}
	
	public void update(float deltaTime, CircleGraphic circleGraph, BoxGraphic boxGraph, LineGraphic lineGraph)
	{
        collisions(deltaTime, circleGraph, boxGraph, lineGraph);
		orientation.AddTranslationBaseCoords(velocity.x * deltaTime, velocity.y * deltaTime, 0);
	}
	
	public void display(int colorLoc, float deltaTime, CircleGraphic circleGraph, BoxGraphic boxGraph, LineGraphic lineGraph)
	{
		ModelMatrix.main.pushMatrix();
        update(deltaTime, circleGraph, boxGraph, lineGraph);
		ModelMatrix.main.addTransformation(orientation.matrix);
		Gdx.gl.glUniform4f(colorLoc, 1.0f, 1.0f, 0.0f, 1);
		ModelMatrix.main.addScale(9.0f, 9.0f, 1.0f);
		ModelMatrix.main.setShaderMatrix();
		CircleGraphic.drawSolidCircle();
		ModelMatrix.main.popMatrix();
	}

    private void collisions(float deltaTime, CircleGraphic circleGraph, BoxGraphic boxGraph, LineGraphic lineGraph){
        float minHitValue = Float.MAX_VALUE;
        Line minLine = null;
        ArrayList<Line> all = new ArrayList<Line>(lineGraph.getLines());
        all.addAll(boxGraph.getLines());
        for (Line line : all){
            Vector2 B = line.u();
            Vector2 n = line.getNormal();
            Point3D origin = orientation.getOrigin();
            Vector2 A = new Vector2(origin.x, origin.y);
            Vector2 c = velocity.cast2D();
            float tHit = n.dot((B.sub(A)))/n.dot(c);
            c.scl(tHit, tHit);
            Vector2 pHit = A.add(c);
            //System.out.printf("Phit %f %f,  Thit %f\n", pHit.x, pHit.y, tHit);
            if (tHit - deltaTime <= 0 && tHit >= 0){
                if(line.isBetween(pHit)){
                    minHitValue = Math.min(tHit, minHitValue);
                    minLine = line;
                }
            }
        }
        if (minHitValue != Float.MAX_VALUE){
            Vector2 c = velocity.cast2D();
            Vector2 n = minLine.getNormal();
            float var = 2 * c.dot(n)/n.dot(n);
            c.sub(n.scl(var, var));
            velocity = new Vector3D(c.x, c.y, 0);
        }

    }

}