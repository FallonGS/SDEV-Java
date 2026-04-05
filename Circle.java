public class Circle implements Comparable<Circle> {
    private double radius;

    public Circle() {
        this(1.0);
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    //compare circles based on their radius
    @Override
    public int compareTo(Circle other) {
        return Double.compare(this.radius, other.radius);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Circle)) return false;
        Circle other = (Circle) obj;
        return Double.compare(this.radius, other.radius) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(radius);
    }

    // display circle information
    @Override
    public String toString() {
        return "Circle[radius=" + radius + "]";
    }
}
