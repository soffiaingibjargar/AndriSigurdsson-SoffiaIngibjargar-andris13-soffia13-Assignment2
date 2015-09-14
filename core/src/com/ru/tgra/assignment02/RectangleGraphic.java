package com.ru.tgra.assignment02;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.nio.FloatBuffer;

import com.badlogic.gdx.utils.BufferUtils;

public class RectangleGraphic {
	
	private static FloatBuffer vertexBuffer;
	private static int vertexPointer;

	public static void create(int vertexPointer) {
		RectangleGraphic.vertexPointer = vertexPointer;
		vertexBuffer = BufferUtils.newFloatBuffer(8);
	}
	
	public static void drawSolidSquare(Box box) {
		//VERTEX ARRAY IS FILLED HERE
				float[] array = {box.x0, box.y0,
								box.x0, box.y,
								box.x, box.y,
								box.x, box.y0};

				vertexBuffer.put(array);
				vertexBuffer.rewind();
		
		Gdx.gl.glVertexAttribPointer(vertexPointer, 2, GL20.GL_FLOAT, false, 0, vertexBuffer);
		Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_FAN, 0, 4);
		
	}
}