package com.ru.tgra.assignment02;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

import java.nio.FloatBuffer;

import com.badlogic.gdx.utils.BufferUtils;

public class Assignment02Game extends ApplicationAdapter {

	private FloatBuffer modelMatrixBuffer;
	private FloatBuffer projectionMatrix;

	private int renderingProgramID;
	private int vertexShaderID;
	private int fragmentShaderID;

	private int positionLoc;

	private int modelMatrixLoc;
	private int projectionMatrixLoc;

	private int colorLoc;
	
	private BoxGraphic boxGraphic;
    private LineGraphic lineGraphic;
	
	private Box boxes;
	private Cannon cannon;
	
	@Override
	public void create () {

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		String vertexShaderString;
		String fragmentShaderString;

		vertexShaderString = Gdx.files.internal("shaders/simple2D.vert").readString();
		fragmentShaderString =  Gdx.files.internal("shaders/simple2D.frag").readString();

		vertexShaderID = Gdx.gl.glCreateShader(GL20.GL_VERTEX_SHADER);
		fragmentShaderID = Gdx.gl.glCreateShader(GL20.GL_FRAGMENT_SHADER);
	
		Gdx.gl.glShaderSource(vertexShaderID, vertexShaderString);
		Gdx.gl.glShaderSource(fragmentShaderID, fragmentShaderString);
	
		Gdx.gl.glCompileShader(vertexShaderID);
		Gdx.gl.glCompileShader(fragmentShaderID);

		renderingProgramID = Gdx.gl.glCreateProgram();
		
	
		Gdx.gl.glAttachShader(renderingProgramID, vertexShaderID);
		Gdx.gl.glAttachShader(renderingProgramID, fragmentShaderID);
	
		Gdx.gl.glLinkProgram(renderingProgramID);

		positionLoc				= Gdx.gl.glGetAttribLocation(renderingProgramID, "a_position");
		Gdx.gl.glEnableVertexAttribArray(positionLoc);

		modelMatrixLoc			= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_modelMatrix");
		projectionMatrixLoc	= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_projectionMatrix");

		colorLoc				= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_color");

		Gdx.gl.glUseProgram(renderingProgramID);

		float[] mm = new float[16];

		mm[0] = 1.0f; mm[4] = 0.0f; mm[8] = 0.0f; mm[12] = 0.0f;
		mm[1] = 0.0f; mm[5] = 1.0f; mm[9] = 0.0f; mm[13] = 0.0f;
		mm[2] = 0.0f; mm[6] = 0.0f; mm[10] = 1.0f; mm[14] = 0.0f;
		mm[3] = 0.0f; mm[7] = 0.0f; mm[11] = 0.0f; mm[15] = 1.0f;

		modelMatrixBuffer = BufferUtils.newFloatBuffer(16);
		modelMatrixBuffer.put(mm);
		modelMatrixBuffer.rewind();

		Gdx.gl.glUniformMatrix4fv(modelMatrixLoc, 1, false, modelMatrixBuffer);

		//COLOR IS SET HERE
		Gdx.gl.glUniform4f(colorLoc, 1.0f, 0.0f, 0, 1);


		//VERTEX ARRAY IS FILLED HERE
		RectangleGraphic.create(positionLoc);
		CircleGraphic.create(positionLoc);
		
		//SincGraphic.create(positionLoc);
		//CoordFrameGraphic.create(positionLoc);
		cannon = new Cannon();
		ModelMatrix.main = new ModelMatrix();
		ModelMatrix.main.loadIdentityMatrix();
		
		boxGraphic = new BoxGraphic();
		lineGraphic = new LineGraphic();
		
	}
	
	
	private void update()
	{
		float deltaTime = Gdx.graphics.getDeltaTime();
		
		boxGraphic.update();
        lineGraphic.update();
		cannon.update(deltaTime);
	}
		
	
	private void display()
	{	
		Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		OrthographicProjection2D(0, 1024, 0, 768);
		
		Gdx.gl.glUniform4f(colorLoc, 1.0f, 1.0f, 1.0f, 1);
		
		ModelMatrix.main.loadIdentityMatrix();
		ModelMatrix.main.setShaderMatrix(modelMatrixLoc);
		float deltaTime = Gdx.graphics.getDeltaTime();

		
		boxGraphic.display(colorLoc);
        lineGraphic.display(colorLoc);
		cannon.display(colorLoc, deltaTime);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//put the code inside the update and display methods, depending on the nature of the code
		update();
		display();

	}


	private void clearModelMatrix()
	{
		modelMatrixBuffer.put(0, 1.0f);
		modelMatrixBuffer.put(1, 0.0f);
		modelMatrixBuffer.put(2, 0.0f);
		modelMatrixBuffer.put(3, 0.0f);
		modelMatrixBuffer.put(4, 0.0f);
		modelMatrixBuffer.put(5, 1.0f);
		modelMatrixBuffer.put(6, 0.0f);
		modelMatrixBuffer.put(7, 0.0f);
		modelMatrixBuffer.put(8, 0.0f);
		modelMatrixBuffer.put(9, 0.0f);
		modelMatrixBuffer.put(10, 1.0f);
		modelMatrixBuffer.put(11, 0.0f);
		modelMatrixBuffer.put(12, 0.0f);
		modelMatrixBuffer.put(13, 0.0f);
		modelMatrixBuffer.put(14, 0.0f);
		modelMatrixBuffer.put(15, 1.0f);

		Gdx.gl.glUniformMatrix4fv(modelMatrixLoc, 1, false, modelMatrixBuffer);
	}
	private void setModelMatrixTranslation(float xTranslate, float yTranslate)
	{
		modelMatrixBuffer.put(12, xTranslate);
		modelMatrixBuffer.put(13, yTranslate);

		Gdx.gl.glUniformMatrix4fv(modelMatrixLoc, 1, false, modelMatrixBuffer);
	}
	private void setModelMatrixScale(float xScale, float yScale)
	{
		modelMatrixBuffer.put(0, xScale);
		modelMatrixBuffer.put(5, yScale);

		Gdx.gl.glUniformMatrix4fv(modelMatrixLoc, 1, false, modelMatrixBuffer);
	}
	
	private void OrthographicProjection2D(float left, float right, float bottom, float top) {
		
		float[] pm = new float[16];

		pm[0] = 2.0f / (right - left); pm[4] = 0.0f; pm[8] = 0.0f; pm[12] = -(right + left) / (right - left);
		pm[1] = 0.0f; pm[5] = 2.0f / (top - bottom); pm[9] = 0.0f; pm[13] = -(top + bottom) / (top - bottom);
		pm[2] = 0.0f; pm[6] = 0.0f; pm[10] = 1.0f; pm[14] = 0.0f;
		pm[3] = 0.0f; pm[7] = 0.0f; pm[11] = 0.0f; pm[15] = 1.0f;

		projectionMatrix = BufferUtils.newFloatBuffer(16);
		projectionMatrix.put(pm);
		projectionMatrix.rewind();
		Gdx.gl.glUniformMatrix4fv(projectionMatrixLoc, 1, false, projectionMatrix);

	}
}