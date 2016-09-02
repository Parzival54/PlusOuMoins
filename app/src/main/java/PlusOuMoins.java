/**
 * Created by merguez on 30/08/2016.
 */

import java.util.Random;
import java.util.Scanner;

public class PlusOuMoins {

    public static void main(String[] args) {

            int alea;
            char reponse = 'O';
            while (reponse == 'O') {
                alea = new Random().nextInt(100) + 1; // alea est compris entre
                // 1 et 100
                plusoumoins(alea);
                reponse = rejouer();
            }

        }


    static Scanner sc = new Scanner(System.in);

    private static char rejouer() {
        char reponse = ' ';

        System.out.println("Voulez-vous rejouer ? (O/N)");

        while (reponse != 'O' && reponse != 'N') {
            reponse = sc.next().charAt(0);
            switch (reponse) {
                case 'O':
                    return 'O';
                case 'o':
                    return 'O';
                case 'N':
                    System.out.println("Merci d'avoir joué");
                    return 'N';
                case 'n':
                    System.out.println("Merci d'avoir joué");
                    return 'N';
                default:
                    reponse = ' ';
                    System.out.println("Veuillez entrer O ou N");
                    break;
            }

        }
        return ' ';

    }

    private static void plusoumoins(int alea) {
        int choix, trouve = 0;

        System.out.println("Veuillez trouver un nombre entre 1 et 100");
        for (int i = 1; i <= 5; i++) { // Boucle sur les 5 essais
            System.out.printf("Essai n°%d/5\n", i);
            System.out.print("Votre choix :");
            choix = sc.nextInt();
            if (choix == alea) { // alea a été trouvé
                trouve = i; // trouve correspond au nombre d'essais
                break;
            } else if (choix > alea) {
                System.out.println("C'est moins");

            } else {
                System.out.println("C'est plus");
            } // end if
        }

        if (trouve == 0) { // si trouve est nul c'est que alea n'a pas été
            // trouvé en 5 essais
            System.out.printf("Dommage vous n'avez pas trouvé\n");
            System.out.printf("La valeur a trouver était %d\n", alea); // on
            // donne
            // la
            // réponse
        } else {
            System.out.printf("Bravo vous avez trouvé en %d essai(s)\n", trouve);
            System.out.printf("La valeur a trouver était bien %d\n", alea); // on
            // donne
            // la
            // réponse
        }
    }

}
