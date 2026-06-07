public class KrizicKruzic {
    String trenutniIgrac = "X";

    String[][] ploca = new String[3][3];

    public KrizicKruzic() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ploca[i][j] = " ";
            }
        }
    }

    public String getTrenutniIgrac() {
        return trenutniIgrac;
    }

    public void setTrenutniIgrac(String trenutniIgrac) {
        this.trenutniIgrac = trenutniIgrac;
    }

    public boolean potez(int redak, int stupac) {
        if (ploca[redak][stupac].equals(" ")) {
            ploca[redak][stupac] = trenutniIgrac;
            return true;
        }
        return false;
    }

    public boolean provjeriPobjedu() {
        for (int i = 0; i < 3; i++) {
            if (ploca[i][0].equals(trenutniIgrac) &&
                    ploca[i][1].equals(trenutniIgrac) &&
                    ploca[i][2].equals(trenutniIgrac)) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (ploca[0][j].equals(trenutniIgrac) &&
                    ploca[1][j].equals(trenutniIgrac) &&
                    ploca[2][j].equals(trenutniIgrac)) {
                return true;
            }
        }

        if (ploca[0][0].equals(trenutniIgrac) &&
                ploca[1][1].equals(trenutniIgrac) &&
                ploca[2][2].equals(trenutniIgrac)) {
            return true;
        }

        if (ploca[0][2].equals(trenutniIgrac) &&
                ploca[1][1].equals(trenutniIgrac) &&
                ploca[2][0].equals(trenutniIgrac)) {
            return true;
        }

        return false;
    }

    public boolean provjeriRemi() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ploca[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void promjeniIgrača() {
        setTrenutniIgrac(trenutniIgrac.equals("X") ? "O" : "X");
    }

    public void crtaj() {
        System.out.println("-------------");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");

            for (int j = 0; j < 3; j++) {
                System.out.print(ploca[i][j] + " | ");
            }

            System.out.println();
            System.out.println("-------------");
        }
    }

}
