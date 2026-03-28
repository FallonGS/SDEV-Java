public class Triangle extends GeometricObject {
    private double side1 = 1.0;
    private double side2 = 1.0;
    private double side3 = 1.0;

    // no arg constructor for default triangle
    public Triangle() {
    }

    // c with specified sides
    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    // accessors fore sides
    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }

    // compute area
    @Override
    public double getArea() {
        double s = getPerimeter() / 2.0;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    // compute perimeter
    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    // string description
    @Override
    public String toString() {
        return "Triangle: side1 = " + side1 +
               ", side2 = " + side2 +
               ", side3 = " + side3;
    }
}
