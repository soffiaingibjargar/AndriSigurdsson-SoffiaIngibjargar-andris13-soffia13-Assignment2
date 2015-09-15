package com.ru.tgra.assignment02;

import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.*;

public class Line {
    private Vector2 u;
    private Vector2 v;

    public Line(float x, float y, float x0, float y0){
        u = new Vector2(x, y);
        v = new Vector2(x0, y0);
    }

    public Vector2 u(){
        return new Vector2(u);
    }

    public void scale(float x, float y){
        u.x *= x;
        v.x *= x;
        u.y *= y;
        v.y *= y;

    }

    public Vector2 v(){
        return new Vector2(v);
    }


    public Vector2 getEdge(){
        return new Vector2(u).sub(v);
    }

    public Vector2 getNormal(){
        return new Vector2(u).sub(v).rotate90(-1);
    }

    public boolean isBetween(Vector2 pHit){
        float error = 1.0e-4f;
        return (pHit.x - error <= max(u.x, v.x)) && (pHit.x + error >= min(u.x, v.x)) && ((pHit.y - error <= max(u.y, v.y)) && (pHit.y + error >= min(u.y, v.y)));
    }
}
