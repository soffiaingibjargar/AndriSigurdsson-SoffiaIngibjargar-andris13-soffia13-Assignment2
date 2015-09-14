package com.ru.tgra.assignment02;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public class LineGraphic {
    ModelMatrix orientation;
    ArrayList<Box> boxes;

    Box box;

    public LineGraphic()
    {
        orientation = new ModelMatrix();
        orientation.loadIdentityMatrix();
    }

    public void update()
    {
        ModelMatrix.main.addTransformation(orientation.matrix);



    }

    public void display(int colorLoc)
    {
        ModelMatrix.main.pushMatrix();

        Gdx.gl.glUniform4f(colorLoc, 1.0f, 0.078f, 0.576f, 1);
        RectangleGraphic.drawSolidSquare(box);

        ModelMatrix.main.popMatrix();
    }
}
