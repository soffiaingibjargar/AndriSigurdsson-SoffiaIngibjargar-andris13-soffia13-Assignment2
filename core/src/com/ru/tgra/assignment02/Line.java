package com.ru.tgra.assignment02;

public class Line {
    public Point3D u;
    public Point3D v;

    public Line(float x, float y, float x0, float y0){
        u = new Point3D(x, y, 0);
        v = new Point3D(x0, y0, 0);
    }
}
