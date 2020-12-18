
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eduardovalenzuela
 */
public class t2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> programa = new ArrayList<>();
        ArrayList<String> palabrasReservadas = new ArrayList<>();
        ArrayList<String> simbolosReservados = new ArrayList<>();
        Interprete interprete = new Interprete();
        simbolosReservados.add("$");
        simbolosReservados.add("+");
        simbolosReservados.add("-");
        simbolosReservados.add("%");
        simbolosReservados.add("*");
        simbolosReservados.add("/");
        palabrasReservadas.add("write");
        palabrasReservadas.add("read");
        palabrasReservadas.add("if");
        palabrasReservadas.add("then");
        palabrasReservadas.add("else");
        palabrasReservadas.add("endif;");
        palabrasReservadas.add("while");
        palabrasReservadas.add("do");
        palabrasReservadas.add("wend;");
        simbolosReservados.add("<");
        simbolosReservados.add(">");
        simbolosReservados.add("<=");
        simbolosReservados.add(">=");
        simbolosReservados.add("==");
        simbolosReservados.add("!=");
        simbolosReservados.add("=");
        simbolosReservados.add("(");
        simbolosReservados.add(")");

        try {
            Scanner input = new Scanner(new File(args[0]));

            while (input.hasNextLine()) {
                String line = input.nextLine();
                //limpia los espacios en blanco antes de la primera letra y despues de la ultima letra
                line = line.trim();

                if(contadorPARENTESIS(line)){
                    if(line.contains("(") || line.contains(")")){
                        int inicioParentesis = line.indexOf("(");
                        int terminoParentesis = line.indexOf(")");
                        if(inicioParentesis == -1 || terminoParentesis == -1){

                        }
                        else{
                            String inicioLine = line.substring(0, inicioParentesis);
                            String terminoLine = line.substring(terminoParentesis, line.length());
                            String medioLine = line.substring(inicioParentesis, terminoParentesis);
                            medioLine = medioLine.replaceAll(" ", "");
                            line = inicioLine + medioLine + terminoLine;

                        }

                    }
                    else if(line.charAt(0) == '$'){
                        line = line.replaceAll(" ", "");


                    }
                    else if(line.contains("read")){
                        if(line.contains("+") || line.contains("-") || line.contains("/") || line.contains("*") || line.contains("%") || line.contains("=") || line.contains("==")|| line.contains("<=") || line.contains(">=")|| line.contains(">") || line.contains("<") || line.contains("!=") || line.contains("$$")){
                            interprete.interpretar(programa);
                            System.out.println("Error en read: " + line);
                            System.exit(0);
                        }
                        else if(line.contains("$0") || line.contains("$1") || line.contains("$2") || line.contains("$3") || line.contains("$4") || line.contains("$5") || line.contains("$6") || line.contains("$7") || line.contains("$8") || line.contains("$9")){
                            interprete.interpretar(programa);
                            System.out.println("Error en read: " + line);
                            System.exit(0);
                        }

                    }
                    else if(line.contains("write") && (line.contains("=") || line.contains("==")|| line.contains("<=") || line.contains(">=")|| line.contains(">") || line.contains("<") || line.contains("!=") || line.contains("$$"))){
                        if(line.contains("+") || line.contains("-") || line.contains("/") || line.contains("*") || line.contains("%") || line.contains("=") || line.contains("==")|| line.contains("<=") || line.contains(">=")|| line.contains(">") || line.contains("<") || line.contains("!=") || line.contains("$$")){
                            interprete.interpretar(programa);
                            System.out.println("Error en write: " + line);
                            System.exit(0);
                        }
                        else if(line.contains("$0") || line.contains("$1") || line.contains("$2") || line.contains("$3") || line.contains("$4") || line.contains("$5") || line.contains("$6") || line.contains("$7") || line.contains("$8") || line.contains("$9")){
                            interprete.interpretar(programa);
                            System.out.println("Error en write: " + line);
                            System.exit(0);
                        }
                    }
                    else if(line.charAt(0) == 'w' && line.charAt(1) == 'h'){
                        if(line.matches("while\\(\\$[a-z][a-z0-9]*==\\$[a-z][a-z0-9]*\\)do") || line.matches("while\\(\\$[a-z][a-z0-9]*<=\\$[a-z][a-z0-9]*\\)do") || line.matches("while\\(\\$[a-z][a-z0-9]*>=\\$[a-z][a-z0-9]*\\)do") || line.matches("while\\(\\$[a-z][a-z0-9]*!=\\$[a-z][a-z0-9]*\\)do") || line.matches("while\\(\\$[a-z][a-z0-9]*<\\$[a-z][a-z0-9]*\\)do") || line.matches("while\\(\\$[a-z][a-z0-9]*>\\$[a-z][a-z0-9]*\\)do") ||
                                line.matches("while\\(\\$[a-z][a-z0-9]*==[0-9][0-9]*\\)do") || line.matches("while\\(\\$[a-z][a-z0-9]*<=[0-9][0-9]*\\)do") || line.matches("while\\(\\$[a-z][a-z0-9]*>=[0-9][0-9]*\\)do") || line.matches("while\\(\\$[a-z][a-z0-9]*!=[0-9][0-9]*\\)do") || line.matches("while\\(\\$[a-z][a-z0-9]*<[0-9][0-9]*\\)do") || line.matches("while\\(\\$[a-z][a-z0-9]*>[0-9][0-9]*\\)do") ||
                                line.matches("while\\([0-9][0-9]*==[0-9][0-9]*\\)do") || line.matches("while\\([0-9][0-9]*<=[0-9][0-9]*\\)do") || line.matches("while\\([0-9][0-9]*>=[0-9][0-9]*\\)do") || line.matches("while\\([0-9][0-9]*!=[0-9][0-9]*\\)do") || line.matches("while\\([0-9][0-9]*<[0-9][0-9]*\\)do") || line.matches("while\\([0-9][0-9]*>[0-9][0-9]*\\)do") ||
                                line.matches("while\\([0-9][0-9]*==\\$[a-z][a-z0-9]*\\)do") || line.matches("while\\([0-9][0-9]*<=\\$[a-z][a-z0-9]*\\)do") || line.matches("while\\([0-9][0-9]*>=\\$[a-z][a-z0-9]*\\)do") || line.matches("while\\([0-9][0-9]*!=\\$[a-z][a-z0-9]*\\)do") || line.matches("while\\([0-9][0-9]*<\\$[a-z][a-z0-9]*\\)do") || line.matches("while\\([0-9][0-9]*>\\$[a-z][a-z0-9]*\\)do")){

                        }
                        else{
                            interprete.interpretar(programa);
                            System.out.println("Error en while: " + line);
                            System.exit(0);
                        }

                    }
                    else if(line.charAt(0) == 'i' && line.charAt(1) == 'f'){
                        if(line.matches("if\\(\\$[a-z][a-z0-9]*==\\$[a-z][a-z0-9]*\\)then") || line.matches("if\\(\\$[a-z][a-z0-9]*<=\\$[a-z][a-z0-9]*\\)then") || line.matches("if\\(\\$[a-z][a-z0-9]*>=\\$[a-z][a-z0-9]*\\)then") || line.matches("if\\(\\$[a-z][a-z0-9]*!=\\$[a-z][a-z0-9]*\\)then") || line.matches("if\\(\\$[a-z][a-z0-9]*<\\$[a-z][a-z0-9]*\\)then") || line.matches("if\\(\\$[a-z][a-z0-9]*>\\$[a-z][a-z0-9]*\\)then") ||
                                line.matches("if\\(\\$[a-z][a-z0-9]*==[0-9][0-9]*\\)then") || line.matches("if\\(\\$[a-z][a-z0-9]*<=[0-9][0-9]*\\)then") || line.matches("if\\(\\$[a-z][a-z0-9]*>=[0-9][0-9]*\\)then") || line.matches("if\\(\\$[a-z][a-z0-9]*!=[0-9][0-9]*\\)then") || line.matches("if\\(\\$[a-z][a-z0-9]*<[0-9][0-9]*\\)then") || line.matches("if\\(\\$[a-z][a-z0-9]*>[0-9][0-9]*\\)then") ||
                                line.matches("if\\([0-9][0-9]*==[0-9][0-9]*\\)then") || line.matches("if\\([0-9][0-9]*<=[0-9][0-9]*\\)then") || line.matches("if\\([0-9][0-9]*>=[0-9][0-9]*\\)then") || line.matches("if\\([0-9][0-9]*!=[0-9][0-9]*\\)then") || line.matches("if\\([0-9][0-9]*<[0-9][0-9]*\\)then") || line.matches("if\\([0-9][0-9]*>[0-9][0-9]*\\)then") ||
                                line.matches("if\\([0-9][0-9]*==\\$[a-z][a-z0-9]*\\)then") || line.matches("if\\([0-9][0-9]*<=\\$[a-z][a-z0-9]*\\)then") || line.matches("if\\([0-9][0-9]*>=\\$[a-z][a-z0-9]*\\)then") || line.matches("if\\([0-9][0-9]*!=\\$[a-z][a-z0-9]*\\)then") || line.matches("if\\([0-9][0-9]*<\\$[a-z][a-z0-9]*\\)then") || line.matches("if\\([0-9][0-9]*>\\$[a-z][a-z0-9]*\\)then")){

                        }
                        else{
                            interprete.interpretar(programa);
                            System.out.println("Error en if: " + line);
                            System.exit(0);
                        }

                    }




                    String [] programaSub = line.split(" ");
                    for (int i = 0; i < programaSub.length; i++) {
                        if(programaSub[i].matches("[a-z]+")){
                            boolean existe = false;
                            for (int j = 0; j < palabrasReservadas.size(); j++) {
                                if(programaSub[i] == null ? palabrasReservadas.get(j) == null : programaSub[i].equals(palabrasReservadas.get(j))){
                                    existe = true;
                                }

                            }
                            if(!existe){
                                interprete.interpretar(programa);
                                System.out.println("Error Palabras reservadas");
                                System.exit(0);

                            }

                        }
                        else if (programaSub[i].charAt(0) == '$') {
                            if(programaSub[i].charAt(1) == '0' || programaSub[i].charAt(1) == '1' || programaSub[i].charAt(1) == '2'|| programaSub[i].charAt(1) == '3'|| programaSub[i].charAt(1) == '4'|| programaSub[i].charAt(1) == '5'||programaSub[i].charAt(1) == '6'||programaSub[i].charAt(1) == '7'||programaSub[i].charAt(1) == '8'||programaSub[i].charAt(1) == '9'){
                                interprete.interpretar(programa);
                                System.out.println(programaSub[i]);
                                System.out.println("Error Sintaxis elemento invalido");
                                System.exit(0);
                            }
                            else if(programaSub[i].contains("+") || programaSub[i].contains("-") || programaSub[i].contains("/") || programaSub[i].contains("*") || programaSub[i].contains("%")){
                                if(programaSub[i].contains("<") || programaSub[i].contains("<=") || programaSub[i].contains(">") || programaSub[i].contains(">=") || programaSub[i].contains("==") || programaSub[i].contains("!=")){
                                    interprete.interpretar(programa);
                                    System.out.println(programaSub[i]);
                                    System.out.println("Error Sintaxis elemento invalido");
                                    System.exit(0);
                                }
                                else{
                                    for (int j = 0; j <programaSub[i].length()-1; j++) {
                                        if(programaSub[i].charAt(j) == '+' || programaSub[i].charAt(j) == '-' || programaSub[i].charAt(j) == '/' || programaSub[i].charAt(j) == '*' || programaSub[i].charAt(j) == '%'){
                                            if(programaSub[i].charAt(j+1) == '+' || programaSub[i].charAt(j+1) == '-' || programaSub[i].charAt(j+1) == '/' || programaSub[i].charAt(j+1) == '*' || programaSub[i].charAt(j+1) == '%'){
                                                interprete.interpretar(programa);
                                                System.out.println(programaSub[i]);
                                                System.out.println("Error Sintaxis elementos consecutivos");
                                                System.exit(0);
                                            }



                                        }
                                        if(programaSub[i].charAt(j) == '$' && (programaSub[i].charAt(j+1) == '0' || programaSub[i].charAt(j+1) == '1' || programaSub[i].charAt(j+1) == '2' || programaSub[i].charAt(j+1) == '3'|| programaSub[i].charAt(j+1) == '4'|| programaSub[i].charAt(j+1) == '5'|| programaSub[i].charAt(j+1) == '6'|| programaSub[i].charAt(j+1) == '7'|| programaSub[i].charAt(j+1) == '8'|| programaSub[i].charAt(j+1) == '9')){
                                            interprete.interpretar(programa);
                                            System.out.println(programaSub[i]);
                                            System.out.println("Error");
                                            System.exit(0);
                                        }

                                    }
                                    //System.exit(0);
                                    if(!programaSub[i].matches("[\\$\\+\\-\\*\\/\\%a-z0-9;=]+")){
                                        interprete.interpretar(programa);
                                        System.out.println(programaSub[i]);
                                        System.out.println("Error");
                                        System.exit(0);
                                    }
                                }
                            }
                            else{
                                if(programaSub[i].matches("\\$[a-z][a-z0-9]*=[0-9]+;") || programaSub[i].matches("\\$[a-z][a-z0-9]*;") || programaSub[i].matches("\\$[a-z][a-z0-9]*=\\$[a-z][a-z0-9]*;")){

                                }
                                else{
                                    interprete.interpretar(programa);
                                    System.out.println(programaSub[i]);
                                    System.out.println("Error Sintaxis asignacion");
                                    System.exit(0);
                                }
                            }

                        }
                        else if(programaSub[i].charAt(0) == '('){
                            String auxProgram = programaSub[i].replaceAll("\\(", "");
                            auxProgram = auxProgram.replaceAll("\\)", "");
                            if(auxProgram.matches("\\$[a-z][a-z0-9]*<[0-9]+") || auxProgram.matches("\\$[a-z][a-z0-9]*>[0-9]+")|| auxProgram.matches("\\$[a-z][a-z0-9]*>=[0-9]+") || auxProgram.matches("\\$[a-z][a-z0-9]*<=[0-9]+") || auxProgram.matches("\\$[a-z][a-z0-9]*==[0-9]+") || auxProgram.matches("\\$[a-z][a-z0-9]*!=[0-9]+") ||
                                    auxProgram.matches("[0-9]+<\\$[a-z][a-z0-9]*") || auxProgram.matches("[0-9]+>\\$[a-z][a-z0-9]*")|| auxProgram.matches("[0-9]+>=\\$[a-z][a-z0-9]*") || auxProgram.matches("[0-9]+<=\\$[a-z][a-z0-9]*") || auxProgram.matches("[0-9]+==\\$[a-z][a-z0-9]*") || auxProgram.matches("[0-9]+!=\\$[a-z][a-z0-9]*") ||
                                    auxProgram.matches("\\$[a-z][a-z0-9]*<\\$[a-z][a-z0-9]*") || auxProgram.matches("\\$[a-z][a-z0-9]*>\\$[a-z][a-z0-9]*")|| auxProgram.matches("\\$[a-z][a-z0-9]*>=\\$[a-z][a-z0-9]*") || auxProgram.matches("\\$[a-z][a-z0-9]*<=\\$[a-z][a-z0-9]*") || auxProgram.matches("\\$[a-z][a-z0-9]*==\\$[a-z][a-z0-9]*") || auxProgram.matches("\\$[a-z][a-z0-9]*!=\\$[a-z][a-z0-9]*")){

                            }
                            else{
                                interprete.interpretar(programa);
                                System.out.println(programaSub[i]);
                                System.out.println("Error Sintaxis condición");
                                System.exit(0);
                            }

                        }
                        //System.out.println(programaSub[i]);
                    }
                    //System.out.println("");
                    programa.add(line);

                }
                else{
                    System.out.println("Error, parentesis no balanceados");
                    System.exit(0);
                }
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // por ahora lo estoy ejecutando si el programa sale bien y no tiene falla de sintaxis.

        interprete.interpretar(programa);

        /*for (int i = 0; i < programa.size(); i++) {
            System.out.println(programa.get(i));
        }*/


        //System.out.println(programa.get(2));
    }

    public static boolean contadorPARENTESIS(String cadena){
        int contadorPARENTESISAB = 0;
        int contadorPARENTESISCE = 0;

        for (int j = 0; j < cadena.length(); j++) {
            if(cadena.charAt(j) == '('){
                contadorPARENTESISAB++;

            }
            if(cadena.charAt(j) == ')'){
                contadorPARENTESISCE++;
            }
        }

        if(contadorPARENTESISAB == contadorPARENTESISCE){
            return true;
        }
        else{
            return false;
        }


    }

}
