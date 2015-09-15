package com.ru.tgra.assignment02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class CannonBall {
	
	public ModelMatrix orientation;
	public Vector3D velocity;
	public boolean live;

	public CannonBall(ModelMatrix orientation, float power)
	{
		this.orientation = new ModelMatrix();
		this.orientation.loadIdentityMatrix();
		this.orientation.addTransformation(orientation.matrix);
		this.orientation.addTranslation(0, 50.0f, 0);
		this.live = true;
		velocity = orientation.getB();
		velocity.scale(600.0f * power);
	}
	
	public void update(float deltaTime, CircleGraphic circleGraph, BoxGraphic boxGraph, LineGraphic lineGraph)
	{
        velocity.y -= 8;
        collisions(deltaTime, circleGraph, boxGraph, lineGraph);
		orientation.AddTranslationBaseCoords(velocity.x * deltaTime, velocity.y * deltaTime, 0);
	}
	
	public int display(int colorLoc, float deltaTime, CircleGraphic circleGraph, BoxGraphic boxGraph, LineGraphic lineGraph, Goal goal)
	{
		ModelMatrix.main.pushMatrix();
        update(deltaTime, circleGraph, boxGraph, lineGraph);
		ModelMatrix.main.addTransformation(orientation.matrix);
		Gdx.gl.glUniform4f(colorLoc, 1.0f, 1.0f, 0.0f, 1);
		ModelMatrix.main.addScale(9.0f, 9.0f, 1.0f);
		ModelMatrix.main.setShaderMatrix();
		CircleGraphic.drawSolidCircle();
		ModelMatrix.main.popMatrix();
		return score(goal);
	}

    private void collide(float deltaTime, ArrayList<Line> lines){
        float minHitValue = Float.MAX_VALUE;
        Line minLine = null;
        for (Line line : lines){
            Vector2 B = line.u();
            Vector2 n = line.getNormal();
            Point3D origin = orientation.getOrigin();
            Vector2 A = new Vector2(origin.x, origin.y);
            Vector2 c = velocity.cast2D();
            float tHit = n.dot((B.sub(A)))/n.dot(c);
            c.scl(tHit, tHit);
            Vector2 pHit = A.add(c);
            if (tHit - deltaTime <= 1.0e-8f && tHit > 0){
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
            velocity.scale(0.8f);

            if (deltaTime > 1.0e-10f ){
                collide(deltaTime - minHitValue, lines);
            }
        }

    }

    private void collisions(float deltaTime, CircleGraphic circleGraph, BoxGraphic boxGraph, LineGraphic lineGraph){
        ArrayList<Line> all = new ArrayList<Line>(lineGraph.getLines());
        all.addAll(boxGraph.getLines());
        collide(deltaTime, all);
    }
    
    public int score(Goal goal)
	{
		if(goal.on) {
			if(Math.pow((goal.x - orientation.getOrigin().x),2) + Math.pow((orientation.getOrigin().y - goal.y),2) <= Math.pow((9.0f + 40.0f),2)){
                goal.win();
				live = false;
                return 1;
			}
		}
        return 0;
	}

}