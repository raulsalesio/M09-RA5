public class Rot13 {

    private static final String lletres = "AÀÁBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    private static final char[] minuscules = lletres.toLowerCase().toCharArray();
    private static final char[] majuscules = lletres.toCharArray();

    public static void main(String[] args) {
        String msgs[] = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("\nXifrat\n------");

        for(int i = 0; i < msgs.length; i++){
            msgsXifrats[i] = xifraRot13(msgs[i]);
            System.out.printf("%-23s => %s%n", msgs[i], msgsXifrats[i]);
        }
        System.out.println("\nDesxifrat\n--------");

        for(String msg: msgsXifrats){
            System.out.printf("%-23s => %s%n", msg, desxifraRot13(msg));
        }
    }

    public static String xifraRot13(String cadena) {
        return transforma(cadena, 13);
    }

    public static String desxifraRot13(String cadena) {
        return transforma(cadena, majuscules.length - 13);
    }

    private static String transforma(String cadena, int desplacament) {
        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            boolean trobat = false;

            for (int j = 0; j < majuscules.length; j++) {
                if (majuscules[j] == lletra) {
                    int novaPosicio = (j + desplacament) % majuscules.length;
                    resultat.append(majuscules[novaPosicio]);
                    trobat = true;
                    break;
                }
            }

            if (!trobat) {
                for (int j = 0; j < minuscules.length; j++) {
                    if (minuscules[j] == lletra) {
                        int novaPosicio = (j + desplacament) % minuscules.length;
                        resultat.append(minuscules[novaPosicio]);
                        trobat = true;
                        break;
                    }
                }
            }

            if (!trobat) {
                resultat.append(lletra);
            }
        }

        return resultat.toString();
    }
}