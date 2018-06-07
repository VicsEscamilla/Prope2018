import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

class Reloj {
    private int horas;
    private int minutos;
    private int segundos;
    private String diaLetra;
    private String fecha;
    private int formato;

    private String[] semana = {"L", "Ma", "Mi", "J", "V", "S", "D"};

    public Reloj(){
        this(0,0,0);
    }

    public Reloj(int hh, int mm, int ss) {
        setFormat(0);
        setFecha("");
        setDiaLetra("L");
        setSegundos(ss);
        setMinutos(mm);
        setHora(hh);
    }

    public Reloj(int hh, int mm, int ss, String fecha, String dia) {
        this(hh, mm, ss);
        setFecha(fecha);
        setDiaLetra(dia);
    }

    protected void finalize() {
        System.out.println("hora eliminada: " + toString());
    }

    public void setHora(int hh) {
        if (hh < 0) hh = 0;

        horas = hh%24;
        int index = Arrays.asList(semana).indexOf(diaLetra);
        int nuevoIndex = ((hh/24)+index)%semana.length;
        diaLetra = semana[nuevoIndex];
        if (nuevoIndex != index) {
            setFecha("");
        }
    }

    public void setMinutos(int mm) {
        if (mm < 0) mm = 0;

        minutos = mm%60;
        setHora(horas + mm/60);
    }

    public void setSegundos(int ss) {
        if (ss < 0) ss = 0;

        segundos = ss%60;
        setMinutos(minutos + ss/60);
    }

    public void setFecha(String f) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (f.equals(""))
            fecha = fmt.format(LocalDateTime.now());
        else
            fecha = fmt.format(LocalDate.parse(f, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public void setDiaLetra(String d) {
        for (int i=0; i<semana.length; ++i) {
            if (d.equals(semana[i])) {
                diaLetra = d;
                return;
            }
        }

        diaLetra = "L";
        System.out.println("dia de la semana invalido, usando valor por defecto L.");
    }
    
    public int getHora() {
        return horas;
    }
    
    public int getMinutos() {
        return minutos;
    }
    
    public int getSegundos() {
        return segundos;
    }
    
    public void setFormat(int f) {
        formato = f;
    }

    public String toString() {
        String str = "";
        LocalTime time = LocalTime.of(horas, minutos, segundos);
        switch(formato) {
            case 0:
                str = DateTimeFormatter.ofPattern("hh:mm:ss a").format(time);
                break;
            case 1:
                str = DateTimeFormatter.ofPattern("HH:mm:ss").format(time);
                break;
            case 2:
                str = diaLetra + " " + fecha + " " +
                    DateTimeFormatter.ofPattern("HH:mm:ss").format(time);
                break;
            default:
                //return empty
                break;
        }
        return str;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Reloj))
            return false;
        Reloj r = (Reloj) o;
        return horas == r.getHora() && minutos == r.getMinutos() && segundos == r.getSegundos();
    }

    public Reloj clone() {
        Reloj r = new Reloj(horas, minutos, segundos, fecha, diaLetra);
        r.setFormat(formato);
        return r;
    }

    public void next(int tipo) {
        switch(tipo) {
            case 0:
                setHora(horas+1);
                break;
            case 1:
                setMinutos(minutos+1);
                break;
            case 2:
                setSegundos(segundos+1);
                break;
            default:
                //do nothing
                break;
        }
    }
}

public class RelojDemo {
    public static void main(String[] args) {
        TestReloj();
    }

    public static void PrintAllFormats(Reloj r) {
        r.setFormat(0);
        System.out.println(r);
        r.setFormat(1);
        System.out.println(r);
        r.setFormat(2);
        System.out.println(r);
        System.out.println();
    }

    public static void TestReloj() {
        {
            System.out.println("\nRUNNING Reloj TESTS");

            {
                System.out.printf("Prueba de las diapositivas... ");
                Reloj r1 = new Reloj();
                Reloj r2 = new Reloj(23, 59, 59, "08/09/2015", "Ma");
                Reloj r3 = r2.clone();
                r3.next(2);

                Reloj r4 = new Reloj(r3.getHora(), r2.getMinutos(), 43);
                Reloj r5 = r1.clone();
                r5.setDiaLetra("J");

                if (r4.equals(r5))
                    System.out.println("\nr4 y r5 son iguales");
                else
                    System.out.println("\nr4 y r5 son diferentes");

                r5.setFecha("10/10/2015");
                System.gc();

                PrintAllFormats(r1);
                PrintAllFormats(r2);
                PrintAllFormats(r3);
                PrintAllFormats(r4);

                System.out.println("SUCCESS");
            }

            {
                System.out.printf("EmptyConstructor... ");
                Reloj r = new Reloj();
                r.setFormat(1);
                String expected = "00:00:00";
                assert r.toString().equals(expected) : String.format("\"%s\" != \"%s\"", r.toString(), expected);
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("FirstConstructor... ");
                Reloj r = new Reloj(23, 59, 59);
                r.setFormat(1);
                String expected = "23:59:59";
                assert r.toString().equals(expected) : String.format("\"%s\" != \"%s\"", r.toString(), expected);
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("SecondConstructor... ");
                Reloj r = new Reloj(1, 2, 3, "04/05/2006", "Ma");
                r.setFormat(2);
                String expected = "Ma 04/05/2006 01:02:03";
                assert r.toString().equals(expected) : String.format("\"%s\" != \"%s\"", r.toString(), expected);
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("NextSegundosOverflow... ");
                Reloj r = new Reloj(1, 2, 59, "04/05/2006", "Ma");
                r.next(2);
                r.setFormat(2);
                String expected = "Ma 04/05/2006 01:03:00";
                assert r.toString().equals(expected) : String.format("\"%s\" != \"%s\"", r.toString(), expected);
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("NextMinutosOverflow... ");
                Reloj r = new Reloj(1, 59, 3, "04/05/2006", "Ma");
                r.next(1);
                r.setFormat(2);
                String expected = "Ma 04/05/2006 02:00:03";
                assert r.toString().equals(expected) : String.format("\"%s\" != \"%s\"", r.toString(), expected);
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("NextHoraOverflow... ");
                Reloj r = new Reloj(23, 2, 3, "04/05/2006", "Ma");
                r.next(0);
                r.setFormat(2);

                Reloj expected = new Reloj(00, 02, 03);
                assert r.equals(expected) : String.format("\"%s\" != \"%s\"", r.toString(), expected);
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("SetSegundosNegativo... ");
                Reloj r = new Reloj(1, 2, 3, "04/05/2006", "Ma");
                r.setSegundos(-60);
                r.setFormat(2);
                String expected = "Ma 04/05/2006 01:02:00";
                assert r.toString().equals(expected) : String.format("\"%s\" != \"%s\"", r.toString(), expected);
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("SetMinutosNegativo... ");
                Reloj r = new Reloj(1, 2, 3, "04/05/2006", "Ma");
                r.setMinutos(-60);
                r.setFormat(2);
                String expected = "Ma 04/05/2006 01:00:03";
                assert r.toString().equals(expected) : String.format("\"%s\" != \"%s\"", r.toString(), expected);
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("SetHoraNegativo... ");
                Reloj r = new Reloj(1, 2, 3, "04/05/2006", "Ma");
                r.setHora(-24);
                r.setFormat(2);
                String expected = "Ma 04/05/2006 00:02:03";
                assert r.toString().equals(expected) : String.format("\"%s\" != \"%s\"", r.toString(), expected);
                System.out.println("SUCCESS");
            }

            {
                System.out.printf("AllOverflow... ");
                Reloj r = new Reloj(23, 59, 59, "04/05/2006", "J");
                r.next(2);
                r.setFormat(1);
                String expected = "00:00:00";
                assert r.toString().equals(expected) : String.format("\"%s\" != \"%s\"", r.toString(), expected);
                System.out.println("SUCCESS");
            }

            System.out.println("DONE");
        }
    }
}
