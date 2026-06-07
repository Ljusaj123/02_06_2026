import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String igrac = null;
        Scanner sc = new Scanner(System.in);

        while (true) {
            KrizicKruzic igra = new KrizicKruzic();
            while (true) {
                int redak = -1;
                int stupac = -1;

                igra.crtaj();
                igrac = igra.getTrenutniIgrac();

                try {
                    System.out.printf("Igrač %s, unesite redak (0-2):", igrac);
                    redak = sc.nextInt();
                    sc.nextLine();

                    if (redak < 0 || redak > 2) {
                        System.out.println("Redak mora biti 0, 1 ili 2");
                        continue;
                    }

                    System.out.printf("Igrač %s, unesite stupac (0-2):", igrac);
                    stupac = sc.nextInt();
                    sc.nextLine();

                    if (stupac < 0 || stupac > 2) {
                        System.out.println("Stupac mora biti 0, 1 ili 2");
                        continue;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Unesite jedan cijeli broj od 0 do 2");
                    sc.nextLine();
                    continue;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                if (!igra.potez(redak, stupac)) {
                    System.out.println("Polje je već zauzeto.");
                    continue;
                }

                if (igra.provjeriPobjedu()) {
                    igra.crtaj();
                    System.out.printf("Igrač %s je pobjedio", igrac);
                    break;
                }

                if (igra.provjeriRemi()) {
                    igra.crtaj();
                    System.out.println("Izjednačeno");
                    break;
                }

                igra.promjeniIgrača();
            }

            System.out.println("Želite li igrati ponovno? (da/ne): ");
            String odgovor = sc.nextLine();

            if (!odgovor.equalsIgnoreCase("da")) {
                break;
            }
        }

        sc.close();
    }
}