package com.ru.tgra.assignment02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.nio.FloatBuffer;

import com.badlogic.gdx.utils.BufferUtils;

public class CircleGraphic {
	
	private static FloatBuffer vertexBuffer;
	private static int vertexPointer;
	private static int verticesPerCircle = 60;

	public static void create(int vertexPointer) {
		CircleGraphic.vertexPointer = vertexPointer;
		
		//VERTEX ARRAY IS FILLED HERE
		vertexBuffer = BufferUtils.newFloatBuffer(2*verticesPerCircle);
		
		float f = 0.0f;
		for(int i = 0; i < verticesPerCircle; i++)
		{
			vertexBuffer.put(2*i, (float)Math.cos(f));
			vertexBuffer.put(2*i + 1, (float)Math.sin(f));
			
			f += 2.0 * Math.PI / (double)verticesPerCircle;
			
		}
		vertexBuffer.rewind();

	}
	
	public static void drawSolidCircle() {
		
		Gdx.gl.glVertexAttribPointer(vertexPointer, 2, GL20.GL_FLOAT, false, 0, vertexBuffer);
		Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_FAN, 0, verticesPerCircle);
		
	}
	
}