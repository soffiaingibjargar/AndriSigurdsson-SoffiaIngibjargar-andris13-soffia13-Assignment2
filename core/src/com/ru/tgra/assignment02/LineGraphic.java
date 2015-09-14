package com.ru.tgra.assignment02;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.BufferUtils;

public class LineGraphic {
    ModelMatrix orientation;
    ArrayList<Line> lines;
    boolean mouse = false;
    private static FloatBuffer vertexBuffer;
    private static int vertexPointer;
    float x, y, x0, y0;

    public LineGraphic(){
        orientation = new ModelMatrix();
        orientation.loadIdentityMatrix();
        lines = new ArrayList<Line>();
        vertexBuffer = BufferUtils.newFloatBuffer(4);
    }

    public void update(){
        ModelMatrix.main.addTransformation(orientation.matrix);
        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            if(!mouse) {
                mouse = true;
                x0 = Gdx.input.getX();
                y0 = Gdx.graphics.getHeight() - Gdx.input.getY();
            }
            x = Gdx.input.getX();
            y = Gdx.graphics.getHeight() - Gdx.input.getY();
        }

        if(!Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && mouse == true) {
            mouse = false;
            Line line = new Line(x0, y0, x, y);
            lines.add(line);

        }
    }

    private void draw(float x, float y, float x0, float y0){
        float[] array = {x, y, x0, y0};
        vertexBuffer.put(array);
        vertexBuffer.rewind();
        Gdx.gl.glVertexAttribPointer(vertexPointer, 2, GL20.GL_FLOAT, false, 0, vertexBuffer);
        Gdx.gl.glDrawArrays(GL20.GL_LINES, 0, 4);

    }

    public void display(int colorLoc){
        ModelMatrix.main.pushMatrix();
        Gdx.gl.glUniform4f(colorLoc, 0.340f, 0.378f, 0.576f, 1);
        for (Line line: lines){
            draw(line.u.x, line.u.y, line.v.x, line.v.y);
        }
        if (mouse){
            draw(x, y, x0, y0);
        }

        ModelMatrix.main.popMatrix();
    }
}
