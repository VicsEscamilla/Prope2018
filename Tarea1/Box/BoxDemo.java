class Box {
    private double width;
    private double height;
    private double depth;

    public double volume() {
        return width * height * depth;
    }

    public void setDim(double w, double h, double d) {
        width = w;
        height = h;
        depth = d;
    }
}

public class BoxDemo {
    public static void testBox(double w, double h, double d) {
        Box mybox = new Box();
        mybox.setDim(w, h, d);
        System.out.println("Volume is " + mybox.volume());
    }


    public static void main(String[] args) {
        testBox(10, 20, 15);
        testBox(1, 2, 3);
        testBox(-1, 2, 40);
        testBox(44, 23, 0);
    }
}
