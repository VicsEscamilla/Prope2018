
class ColorRGB {
    private int red;
    private int green;
    private int blue;

    public ColorRGB() {
        red = 127;
        green = 127;
        blue = 127;
    }

    public ColorRGB(int r, int g, int b) {
        red = filterLimits(r);
        green = filterLimits(g);
        blue = filterLimits(b);
    }

    public void setRed(int r) {
        red = r;
    }

    public void setGreen(int g) {
        green = g;
    }

    public void setBlue(int b) {
        blue = b;
    }

    public int getRed(){
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public ColorRGB clone() {
        return new ColorRGB(red, green, blue);
    }

    public String toString() {
        return String.format("RGB: (%d, %d, %d)", red, green, blue);
    }

    public boolean equals(Object o) {
        if (!(o instanceof ColorRGB))
            return false;

        ColorRGB c = (ColorRGB) o;
        return red == c.getRed() && green == c.getGreen() && blue == c.getBlue();
    }

    private int filterLimits(int val) {
        if (val < 0)
            return 0;

        if (val > 255)
            return 255;

        return val;
    }
}

class Converter {
    private int inc = 30;

    public void toCmy (int r, int g, int b) {
        r = 255 - r;
        g = 255 - g;
        b = 255 - b;
    }

    public void toCmy (ColorRGB c) {
        c.setRed(255 - c.getRed());
        c.setGreen(255 - c.getGreen());
        c.setBlue(255 - c.getBlue());
    }

    public ColorRGB getCmy (ColorRGB c) {
        return new ColorRGB(255-c.getRed(), 255-c.getGreen(), 255-c.getBlue());
    }
}

public class ColorRGBDemo {

    static public void main(String[] args) {
        TestColorRGB();
        TestConverter();
    }

    static public void TestColorRGB() {
        System.out.println("\nRUNNING ColorRGB TESTS");
        {
            System.out.printf("EmptyConstructor... ");
            ColorRGB c = new ColorRGB();
            assert c.getRed() == 127 : "Red is wrong";
            assert c.getGreen() == 127 : "Green is wrong";
            assert c.getBlue() == 127 : "Blue is wrong";
            System.out.println("SUCCESS");
        }

        {
            System.out.printf("ConstructorArgsWithinLimits... ");
            ColorRGB c = new ColorRGB(1, 100, 255);
            assert c.getRed() == 1 : "Red is wrong";
            assert c.getGreen() == 100 : "Green is wrong";
            assert c.getBlue() == 255 : "Blue is wrong";
            System.out.println("SUCCESS");
        }

        {
            System.out.printf("ConstructorArgsOutsideLimits... ");
            ColorRGB c = new ColorRGB(-1, 0, 256);
            assert c.getRed() == 0 : "Red is wrong";
            assert c.getGreen() == 0 : "Green is wrong";
            assert c.getBlue() == 255 : "Blue is wrong";
            System.out.println("SUCCESS");
        }

        {
            System.out.printf("Clone... ");
            ColorRGB c = new ColorRGB(1, 2, 3);
            assert c.equals(c.clone()) : "Clone is not equal";
            System.out.println("SUCCESS");
        }

        {
            System.out.printf("CompareAgainstNoInstanceOfColorRGB... ");
            assert !(new ColorRGB(1, 2, 3)).equals("Not ColorRGB") : "ColorRGB.equal doesn't work!";
            System.out.println("SUCCESS");
        }

        {
            System.out.printf("CompareDifferentColorRGB... ");
            assert !(new ColorRGB(1, 2, 3)).equals(new ColorRGB(11, 2, 3)) : "equal failed in Red";
            assert !(new ColorRGB(1, 2, 3)).equals(new ColorRGB(1, 22, 3)) : "equal failed in green";
            assert !(new ColorRGB(1, 2, 3)).equals(new ColorRGB(1, 2, 33)) : "equal failed in blue";
            System.out.println("SUCCESS");
        }

        {
            System.out.printf("toString... ");
            assert (new ColorRGB()).toString().equals("RGB: (127, 127, 127)") : "ColorRGB.toString failed";
            System.out.println("SUCCESS");
        }
        System.out.println("DONE");
    }

    public static void TestConverter() {
        System.out.println("\nRUNNING Converter TESTS");
        {
            System.out.printf("PasePorValor... ");
            ColorRGB color = new ColorRGB(1, 2, 3);
            Converter converter = new Converter();
            converter.toCmy(color.getRed(), color.getGreen(), color.getBlue());
            assert color.equals(new ColorRGB(1, 2, 3));
            System.out.println("SUCCESS");
        }

        {
            System.out.printf("PasePorReferencia1... ");
            ColorRGB color = new ColorRGB(1, 2, 3);
            Converter converter = new Converter();
            converter.toCmy(color);
            assert color.equals(new ColorRGB(254, 253, 252));
            System.out.println("SUCCESS");
        }

        {
            System.out.printf("PasePorReferencia2... ");
            ColorRGB color = new ColorRGB(1, 2, 3);
            Converter converter = new Converter();
            ColorRGB newColor = converter.getCmy(color);
            assert newColor.equals(new ColorRGB(254, 253, 252));
            assert !newColor.equals(color);
            System.out.println("SUCCESS");
        }
        System.out.println("DONE");
    }
}
