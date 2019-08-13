package com.bodhi.androidattributeanimat;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/5 14:19
 * desc :
 */
public class MyPoint {
    private int color;
    private float x;
    private float y;

    public MyPoint() {
    }

    public MyPoint(int color, float x, float y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
