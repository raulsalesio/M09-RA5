public class RotX {
    
    private static final String lletres = "AÀÁBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    private static final char[] minuscules = lletres.toLowerCase().toCharArray();
    private static final char[] majuscules = lletres.toCharArray();

    public static void main(String[] args) {
        String msgs[] = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String msgsXifrats[] = new String[msgs.length];

        // MISSAATGE SORTIDA
        System.out.println("\nXifrat");
        System.out.println("------");

        
    }
    public static String xifraRotX(String cadena, int desplaçament) {
        
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {

            char lletra = cadena.charAt(i);

            for (int j = 0; j < majuscules.length; j++) {
                if (majuscules[j] == lletra) {
                    int novaPosicio = (j + desplaçament) % majuscules.length;
                    lletra = majuscules[novaPosicio];
                    break;
                }
            }

            for (int j = 0; j < minuscules.length; j++) {
                if (minuscules[j] == lletra) {
                    int novaPosicio = (j + desplaçament) % minuscules.length;
                    lletra = minuscules[novaPosicio];
                    break;
                }
            }

            resultat += lletra;
        }

        return resultat;
    }


    // public static String desxifraRotX(String cadena, int desplaçament) {

    // }

    // public static String forcaBruta(String cadenaXifrada) {

    // }
}
