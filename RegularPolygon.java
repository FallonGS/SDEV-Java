public class RegularPolygon {

    private int n;
    private double side;
    private double x;
    private double y;

    public RegularPolygon() {
        this(3, 1.0, 0.0, 0.0);
    }

    public RegularPolygon(int n, double side) {
        this(n, side, 0.0, 0.0);
    }

    public RegularPolygon(int n, double side, double x, double y) {
        if (n < 3) {
            throw new IllegalArgumentException("Must have at least 3 sides");
        }
        if (side <= 0) {
            throw new IllegalArgumentException("Side length cant be zero");
        }
        this.n = n;
        this.side = side;
        this.x = x;
        this.y = y;
    }

    public int getN() {
        return n;
    }

    public double getSide() {
        return side;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setN(int n) {
        if (n < 3) {
            throw new IllegalArgumentException("must have at least 3 sides");
        }
        this.n = n;
    }

    public void setSide(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Side length cant be zero");
        }
        this.side = side;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getPerimeter() {
        return n * side;
    }

    public double getArea() {
        return (n * side * side) / (4.0 * Math.tan(Math.PI / n));
    }

    @Override
    public String toString() {
        return "RegularPolygon[n=" + n + ", side=" + side + ", x=" + x + ", y=" + y + "]";
    }
}
