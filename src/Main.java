import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int izbor;
        String putanjaDatoteke = null;
        String putanjaDestinacije = null;

        try (Scanner sc = new Scanner(System.in);) {
            while (true) {
                System.out.println("Izbornik:");
                System.out.println("1 - Provjeri je li postoji datoteka");
                System.out.println("2 - Kopiranje datoteke");
                System.out.println("3 - Brisanje datoteke");
                System.out.println("4 - Izlaz");

                izbor = sc.nextInt();
                sc.nextLine();

                if (izbor == 4) {
                    return;
                }

                System.out.println("Unesite putanju" + (izbor == 2 ? " originalne " : "") + "datoteke");
                putanjaDatoteke = sc.nextLine();

                if (izbor == 2) {
                    System.out.println("Unesite putanju destinacijske datoteke");
                    putanjaDestinacije = sc.nextLine();
                }
                handleIzbor(izbor, putanjaDatoteke, putanjaDestinacije);
            }
        } catch (InputMismatchException e) {
            System.out.println("Morate unijeti cijeli broj!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleIzbor(int izbor, String putanja, String destinacija) {
        //Files + Path su standard u modernoj Javi.
        Path path = Path.of(putanja);

        switch (izbor) {
            case 1:
                provjeriPostojanje(path);
                break;
            case 2:
                kopirajDatoteku(putanja, destinacija);
                break;
            case 3:
                obrisiDatoteku(path);
                break;
            default:
                System.out.println("Nevažeći izbor");
        }
    }

    private static void provjeriPostojanje(Path path) {
        System.out.println(Files.exists(path) ? "Datoteka postoji" : "Datoteka ne postoji");
    }

    private static void kopirajDatoteku(String izvor, String destinacija) {
        try (FileInputStream fis = new FileInputStream(izvor);
             FileOutputStream fos = new FileOutputStream(destinacija)) {

//            Files.copy(Path.of(izvor), Path.of(destinacija));

            int c;
            while ((c = fis.read()) != -1) {
                fos.write(c);
            }

            System.out.println("Kopiranje završeno.");

        } catch (AccessDeniedException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Greška pri kopiranju: " + e.getMessage());
        }
    }

    private static void obrisiDatoteku(Path path) {
        if (!Files.exists(path)) {
            System.out.println("Datoteka ne postoji.");
            return;
        }

        try {
            Files.delete(path);
            System.out.println("Datoteka obrisana.");
        } catch (AccessDeniedException e) {
            System.out.println("Nemate dozvolu za brisanje datoteke.");
        } catch (IOException e) {
            System.out.println("Greška pri brisanju: " + e.getMessage());
        }
    }
}