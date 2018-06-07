
class Circle {
    double radius;
    int x, y;

    public Circle(){
        x = 0;
        y = 0;
        radius = 1.0;
    }

    public Circle(int x, int y, double radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double area() {
        return radius * radius * Math.PI;
    }

    public double perimeter() {
        return 2 * radius * Math.PI;
    }

    public int posX() {
        return x;
    }

    public int posY() {
        return y;
    }

    public void move(int dx, int dy) {
        x = checkFilters(x+dx);
        y = checkFilters(y+dy);
    }

    private int checkFilters(int val) {
        if (val < 0) return 0;
        if (val > 500) return 500;
        return val;
    }

    public void resize(double dr) {
        radius += dr;
        if (radius <= 0) radius = 1;
        if (radius > 100) radius = 100;
    }

    public String toString() {
        return String.format("Circulo\n"             +
                             "Radio = %.2f\n"        +
                             "Posicion = (%d, %d)\n" +
                             "Area = %.2f\n"         +
                             "Perímetro = %.2f)",
                             radius, x, y, area(), perimeter());
    }
}


public class CircleDemo {
    public static void main(String[] args) {
        Circle c = new Circle(5, 7, 3.5);
        System.out.println(c.toString());

        TestCircle();
    }

    public static void TestCircle() {
        {
            System.out.println("\nRUNNING Circle TESTS");

            {
                System.out.printf("Constructor... ");
                Circle c = new Circle();
                assert c.getRadius() == 1 : "Wrong default radius.";
                assert c.posX() == 0 : "Wrong default x.";
                assert c.posY() == 0 : "Wrong default y.";
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("Area... ");
                Circle c = new Circle(1, 2, 3);
                assert c.area() == 9*Math.PI : "Wrong area.";
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("Perimeter... ");
                Circle c = new Circle(1, 2, 3);
                assert c.perimeter() == 6*Math.PI : "Wrong perimeter.";
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("MoveNegative... ");
                Circle c = new Circle(1, 2, 3);
                c.move(-10, -10);
                int expX = 0;
                int expY = 0;
                assert c.posX() == expX : String.format("Wrong X move. Expected %d, got %d", expX, c.posX());
                assert c.posY() == expY : String.format("Wrong Y move. Expected %d, got %d", expY, c.posY());
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("BigMove... ");
                Circle c = new Circle(1, 2, 3);
                c.move(500, 499);
                int expX = 500;
                int expY = 500;
                assert c.posX() == expX : String.format("Wrong X move. Expected %d, got %d", expX, c.posX());
                assert c.posY() == expY : String.format("Wrong Y move. Expected %d, got %d", expY, c.posY());
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("Move... ");
                Circle c = new Circle(1, 2, 3);
                c.move(10, 100);
                int expX = 11;
                int expY = 102;
                assert c.posX() == expX : String.format("Wrong X move. Expected %d, got %d", expX, c.posX());
                assert c.posY() == expY : String.format("Wrong Y move. Expected %d, got %d", expY, c.posY());
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("ResizeNegative... ");
                Circle c = new Circle(1, 2, 3);
                c.resize(-3);
                double expR = 1;
                assert c.getRadius() == expR : String.format("Wrong resize. Expected %d, got %d", expR, c.getRadius());
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("BigResize... ");
                Circle c = new Circle(1, 2, 3);
                c.resize(98);
                double expR = 100.0;
                assert c.getRadius() == expR : String.format("Wrong resize. Expected %d, got %d", expR, c.getRadius());
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("Resize... ");
                Circle c = new Circle(1, 2, 3);
                c.resize(15);
                double expR = 18.0;
                assert c.getRadius() == expR : String.format("Wrong resize. Expected %d, got %d", expR, c.getRadius());
                System.out.println("SUCCESS");
            }

            System.out.println("DONE");
        }
    }
}
