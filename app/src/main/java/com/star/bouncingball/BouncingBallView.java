package com.star.bouncingball;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BouncingBallView extends View {

    private static final float BALL_SIZE = 100;
    private static final float BALL_MAX_VELOCITY = 80;

    private Sprite mBall;
    private Sprite mPacman;
    private DrawingThread mDrawingThread;

    public BouncingBallView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mBall = new Sprite();
        mBall.setLocation(200, 200);
        mBall.setSize(BALL_SIZE, BALL_SIZE);
        mBall.setVelocity(
                (float) ((Math.random() - 0.5) * 2 * BALL_MAX_VELOCITY),
                (float) ((Math.random() - 0.5) * 2 * BALL_MAX_VELOCITY)
        );

        Paint ballPaint = new Paint();
        ballPaint.setARGB(255, 255, 0, 0);

        mBall.setPaint(ballPaint);

        mPacman = new Sprite();
        mPacman.setLocation(0, 0);
        mPacman.setSize(80, 80);

        Paint pacmanPaint = new Paint();
        pacmanPaint.setARGB(255, 200, 200, 0);

        mPacman.setPaint(pacmanPaint);

        mDrawingThread = new DrawingThread(this, 50);
        mDrawingThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        float width = getWidth();
        float height = getHeight();

        if (x < width / 5) {
            mPacman.setDx(-10);
        } else if (x >= (width * 4 / 5)) {
            mPacman.setDx(10);
        } else {
            mPacman.setDx(0);
        }

        if (y < height / 5) {
            mPacman.setDy(-10);
        } else if (y >= (height * 4 / 5)) {
            mPacman.setDy(10);
        } else {
            mPacman.setDy(0);
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawOval(mBall.getRectF(), mBall.getPaint());
        canvas.drawOval(mPacman.getRectF(), mPacman.getPaint());

        updateSprites();
    }

    private void updateSprites() {
        mBall.move();
        mPacman.move();

        if (mBall.getRectF().left < 0 || mBall.getRectF().right > getWidth()) {
            mBall.setDx(-mBall.getDx());
        }

        if (mBall.getRectF().top < 0 || mBall.getRectF().bottom > getHeight()) {
            mBall.setDy(-mBall.getDy());
        }

        if (mPacman.getRectF().left < 0 || mPacman.getRectF().right > getWidth()) {
            mPacman.setDx(-mPacman.getDx());
        }

        if (mPacman.getRectF().top < 0 || mPacman.getRectF().bottom > getHeight()) {
            mPacman.setDy(-mPacman.getDy());
        }
    }
}
