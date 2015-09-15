package com.ru.tgra.assignment02;

import com.badlogic.gdx.math.Vector2;

public class Vector3D {

	public float x;
	public float y;
	public float z;
	
	public Vector3D(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		
	}
	
	public void scale(float S)
	{
		x *= S;
		y *= S;
		z *= S;
	}
	
	public void add(Vector3D v2)
	{
		x += v2.x;
		y += v2.y;
		z += v2.z;
	}

    public Vector2 cast2D(){
        return new Vector2(x, y);
    }
}