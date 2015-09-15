package com.ru.tgra.assignment02;

import java.util.ArrayList;

public class Box {
	public float x0;
	public float y0;
	public float x;
	public float y;
    ArrayList<Line> lines;
	
	public Box(float x0, float y0, float x, float y){
		this.x0 = x0;
		this.y0 = y0;
		this.x = x;
		this.y = y;
        lines = new ArrayList<Line>();
        lines.add(new Line(x0, y0, x0, y));
        lines.add(new Line(x0, y0, x, y0));
        lines.add(new Line(x0, y, x, y));
        lines.add(new Line(x, y, x, y0));
	}

    public ArrayList<Line> getLines(){
        return lines;
    }


}