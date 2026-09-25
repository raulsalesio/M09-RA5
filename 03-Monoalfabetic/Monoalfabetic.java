import java.util.ArrayList;
import java.util.Collections;

public class Monoalfabetic {

    private static final char[] alfabet = "AÀÁBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private static String alfabetPermutat = "";

    public static char[] permutaAlfabet(char[] alfabet) {

        ArrayList<Character> lista = new ArrayList<>();
        for (int i = 0; i < alfabet.length; i++) {
            lista.add(alfabet[i]);
        }

        Collections.shuffle(lista);

        char[] resultat = new char[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            resultat[i] = lista.get(i);
        }

        return resultat;
    }
}