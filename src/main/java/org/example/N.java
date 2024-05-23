package org.example;
import java.util.Scanner;
public class N {
        // Códigos de cor ANSI
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_BLUE = "\u001B[1m";

        public static void main(String[] args) {
            String coloredText =ANSI_BLUE+ "Este texto será exibido em azul." + ANSI_RESET;

            System.out.println(coloredText);
            System.out.printf("Teste");
        }


    /*
    public static void main(String[] args) {
        System.out.println("Informe uma letra:");
        String letra = new Scanner(System.in).next();
        System.out.println(LetterToNumber(letra));
    }
    public static int LetterToNumber(String y) {
        String alf = "ABCDEFGHIJLMNOPQRSTUVXZ";
        for(int i = 0; i < alf.length(); ++i) {
            if (y.equalsIgnoreCase(String.valueOf(alf.charAt(i)))) {
                return i;
            }
        }return -1;
    }*/
    //return y.equals(alf[i]) ? i : -1;
}
