package com.star.bouncingball;


import android.graphics.Paint;
import android.graphics.RectF;

public class Sprite {

    private RectF mRectF = new RectF();
    private float mDx;
    private float mDy;
    private Paint mPaint;

    public Sprite() {

    }

    public Sprite(float x, float y, float width, float height) {
        setLocation(x, y);
        setSize(width, height);
    }

    public void move() {
        mRectF.offset(mDx, mDy);
    }

    public void stopMoving() {
        setVelocity(0, 0);
    }

    public void setLocation(float x, float y) {
        mRectF.offsetTo(x, y);
    }

    public void setSize(float width, float height) {
        mRectF.right = mRectF.left + width;
        mRectF.bottom = mRectF.top + height;
    }

    public void setVelocity(float dx, float dy) {
        this.mDx = dx;
        this.mDy = dy;
    }

    public void setPaint(Paint paint) {
        mPaint = paint;
    }

    public void setDx(float dx) {
        mDx = dx;
    }

    public void setDy(float dy) {
        mDy = dy;
    }

    public RectF getRectF() {
        return mRectF;
    }

    public Paint getPaint() {
        return mPaint;
    }

    public float getDx() {
        return mDx;
    }

    public float getDy() {
        return mDy;
    }
}
