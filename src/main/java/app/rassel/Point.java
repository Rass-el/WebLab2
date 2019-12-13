package app.rassel;

public class Point {
    private float x;
    private float y;
    private float radius;
    private boolean isInside;

    public Point(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.isInside = false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadius() {
        return radius;
    }

    public boolean isInside() {
        return isInside;
    }

    public void setInside(boolean inside) {
        isInside = inside;
    }
}
