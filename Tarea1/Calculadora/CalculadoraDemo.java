class Calculadora {
    private double a;
    public double b;

    public Calculadora() {
        a = 0;
        b = 1;
    }

    public void setValores(double a, double b) {
        if (a < 0) {
            this.a = 0;
            System.out.println("Error: a < 0. Tomando valor 0.");
        }
        else if (a > 10) {
            this.a = 10;
            System.out.println("Error: a > 10. Tomando valor 10.");
        }
        else
            this.a = a;

        this.b = b;
    }

    public double sumar() {
        return a+b;
    }

    public double restar() {
        return a-b;
    }

    public double multiplicar() {
        return a*b;
    }

    public double dividir() {
        if (b == 0) {
            System.out.println("Math Error: Division entre cero");
            return 0;
        }
        return a/b;
    }
}

public class CalculadoraDemo {
    public static void main(String[] args) {
        TestCalculadora();
    }

    public static void TestCalculadora() {
        {
            System.out.println("\nRUNNING Calculadora TESTS");

            {
                System.out.printf("ValoresPorDefecto... ");
                Calculadora calc = new Calculadora();
                assert calc.sumar() == 1;
                assert calc.restar() == -1;
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("aMayorA10... ");
                Calculadora calc = new Calculadora();
                calc.setValores(11, 1);
                assert calc.restar() == 9;
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("aMenorA0... ");
                Calculadora calc = new Calculadora();
                calc.setValores(-1, 1);
                assert calc.sumar() == 1;
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("Sumar... ");
                Calculadora calc = new Calculadora();
                calc.setValores(1, 2);
                assert calc.sumar() == 3;
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("Restar... ");
                Calculadora calc = new Calculadora();
                calc.setValores(2, 3);
                assert calc.restar() == -1;
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("Multiplicar... ");
                Calculadora calc = new Calculadora();
                calc.setValores(4, 3);
                assert calc.multiplicar() == 12;
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("Dividir... ");
                Calculadora calc = new Calculadora();
                calc.setValores(4, 2);
                assert calc.dividir() == 2;
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("DividirEntreCero... ");
                Calculadora calc = new Calculadora();
                calc.setValores(4, 0);
                assert calc.dividir() == 0;
                System.out.println("SUCCESS");
            }

            System.out.println("DONE");
        }
    }
}
