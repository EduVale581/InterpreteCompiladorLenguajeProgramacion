import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.math.BigInteger; 


public class Interprete {
    HashMap<String, BigInteger> variables ;

    public Interprete(){
        this.variables = new HashMap<String, BigInteger>();
    }

    public void interpretar(ArrayList<String> programa) {
        for (int i = 0; i < programa.size(); i++) {
            String linea = programa.get(i);

            if (linea.contains("if") && !linea.contains("endif")){
                // Busca el fin del if
                int finIf = -1;
                int incioIf = i;
                for (int j = i; j < programa.size(); j++) {
                    String lineaAux = programa.get(j);
                    if (lineaAux.contains("endif")){
                        finIf = j;
                    }
                }
                if (finIf > 0 ){
                    i = finIf;
                    funcionIf(linea, programa, incioIf, finIf);
                }
                else{
                    System.out.println("Error de sintaxis, IF sin termino");
                    System.exit(0);
                }
                
            }else if (linea.contains("while") && !linea.contains("wend")){
                // Busca el fin del while
                int finW = -1;
                int incioW = i;
                for (int j = i; j < programa.size(); j++) {
                    String lineaAux = programa.get(j);
                    if (lineaAux.contains("wend")){
                        finW = j;
                    }
                }
                if (finW > 0 ){
                    i = finW;
                    funcionWhile(linea, programa, incioW, finW);
                }
                else{
                    System.out.println("Error de sintaxis, While sin termino");
                    System.exit(0);
                }
            } else if(linea.contains("write")){
                funcionWrite(linea);
            } else if(linea.contains("read")){
                funcionRead(linea);
            } else{
                asignacionDeVariable(linea);
            }

        }
        //this.variables.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v)); 
        //System.out.println("-------------  FIN ---------------------------");
    }

    public void funcionWhile(String linea,ArrayList<String> programa, int incioW, int finW) {
        ArrayList<String> instrucciones = new ArrayList<>();
        
        for (int i = incioW+1; i < finW; i++) {
            String instruccion = programa.get(i);
            instrucciones.add(instruccion);
        }

        /*System.out.println("Codnicion: "+ comprobarCondicion(linea));
        System.out.println(" If -----------------------------------------");
        instruccionesIf.forEach(s -> System.out.println(s));
        System.out.println("-----------------------------------------");
        System.out.println("");
        System.out.println("Else  -----------------------------------------");
        instruccionesElse.forEach(s -> System.out.println(s));
        System.out.println("-----------------------------------------");*/
        while(comprobarCondicion(linea)){
            this.interpretar(instrucciones);  
        }
    }

 

    public void funcionIf(String linea,ArrayList<String> programa, int incioIf, int finIf) {
        ArrayList<String> instruccionesIf = new ArrayList<>();
        ArrayList<String> instruccionesElse = new ArrayList<>();
        Boolean elseBoolean = false;

        for (int i = incioIf+1; i < finIf; i++) {
            String instruccion = programa.get(i);
            if (instruccion.contains("else")){
                elseBoolean = true;
            }else{
                if (elseBoolean){
                    instruccionesElse.add(instruccion);
                }else{
                    instruccionesIf.add(instruccion);
                }
            }
            
            //System.out.println(programa.get(i));
        }

        /*System.out.println("Codnicion: "+ comprobarCondicion(linea));
        System.out.println(" If -----------------------------------------");
        instruccionesIf.forEach(s -> System.out.println(s));
        System.out.println("-----------------------------------------");
        System.out.println("");
        System.out.println("Else  -----------------------------------------");
        instruccionesElse.forEach(s -> System.out.println(s));
        System.out.println("-----------------------------------------");*/
        if(comprobarCondicion(linea)){
            this.interpretar(instruccionesIf);
            //System.out.println("_-----------------------------------------");
        }else{
            this.interpretar(instruccionesElse);
           // System.out.println("_-----------------------------------------");
        }
    }

    public Boolean comprobarCondicion(String linea) {
        String condicion = linea.replace("if", "").replace("then", "").replace("while", "").replace("do", "")
                                .replaceAll(" ", "").replace("(", "").replace(")", "");
        //System.out.println(condicion);
        Boolean condicicionBoolean = false;
        
        String[] operaciones = condicion.split("<=");
        if (operaciones.length > 1){
            BigInteger[] resultados = resultadoCondiciones( operaciones) ;
            condicicionBoolean = (resultados[0].compareTo(resultados[1]) == 0 || resultados[0].compareTo(resultados[1]) == -1);
        } else{
            operaciones = condicion.split("<");
            if (operaciones.length > 1){
                BigInteger[] resultados = resultadoCondiciones( operaciones) ;
                condicicionBoolean = (resultados[0].compareTo(resultados[1]) == -1);
            }
        }

        operaciones = condicion.split(">=");
        if (operaciones.length > 1){
            BigInteger[] resultados = resultadoCondiciones( operaciones) ;
            condicicionBoolean = (resultados[0].compareTo(resultados[1]) == 0 || resultados[0].compareTo(resultados[1]) == 1);
        } else{
            operaciones = condicion.split(">");
            if (operaciones.length > 1){
                BigInteger[] resultados = resultadoCondiciones( operaciones) ;
                condicicionBoolean = (resultados[0].compareTo(resultados[1]) == 1);
            }
        }
        
        operaciones = condicion.split("==");
        if (operaciones.length > 1){
            BigInteger[] resultados = resultadoCondiciones( operaciones) ;
            condicicionBoolean = (resultados[0].compareTo(resultados[1]) == 0);
        } else{
            operaciones = condicion.split("!=");
            if (operaciones.length > 1){
                BigInteger[] resultados = resultadoCondiciones( operaciones) ;
                condicicionBoolean = (resultados[0].compareTo(resultados[1]) != 0);
            }
        }

        return condicicionBoolean;
    }

    public BigInteger[] resultadoCondiciones(String[] operaciones) {
        BigInteger[] resultados = new BigInteger[2];
        ArrayList<String> cadena =separarOperaciones(operaciones[0]);
        resultados[0] = resultadoOperacionCondicion(cadena);

        ArrayList<String> cadena2 =separarOperaciones(operaciones[1]);
        resultados[1] = resultadoOperacionCondicion(cadena2);

        return resultados;
    }

    public BigInteger resultadoOperacionCondicion(ArrayList<String> cadena) {
        BigInteger resultado = new BigInteger("0");
        
        if(validarVariablesInicializadas(cadena)){
            if (cadena.size() == 1){
                if(cadena.get(0).contains("$")){
                    resultado = this.variables.get(cadena.get(0));
                }else{
                    resultado = new BigInteger(cadena.get(0));
                }
            }else{
                resultado = calcularOperacion(cadena);
            }
            
        }else{
            System.out.println("Error de inicializacion de variable");
            System.exit(0);
        }
        return resultado;
    }

    public void funcionRead(String linea) {
        String nombreVariable  = linea.replace("read","").replaceAll(" ", "").replace(";", "");
        Scanner leer = new Scanner(System.in);
        try {
            BigInteger valor = leer.nextBigInteger(); 
            this.variables.put(nombreVariable, valor);
        } catch (Exception e) {
            System.out.println("Error de lectura por teclado, solo se aceptan Enteros");
            System.exit(0);
        }
        

    }

    public void funcionWrite(String linea) {
        String operacion = linea.replace("write","").replaceAll(" ", "").replace(";", "");
        ArrayList<String> cadena =separarOperaciones(operacion);

        if(validarVariablesInicializadas(cadena)){

            if (cadena.size() == 1){
                if(cadena.get(0).contains("$")){
                    System.out.println(this.variables.get(cadena.get(0)));
                }else{
                    System.out.println(cadena.get(0));
                }
            }else{
                BigInteger resultado = calcularOperacion(cadena);
                System.out.println(resultado);
            }
            
        }else{
            System.out.println("Error de inicializacion de variable");
            System.exit(0);
        }
    }

    public ArrayList<String> separarOperaciones(String operacion) {
        ArrayList<String> cadena = new ArrayList<>();

            cadena.add(operacion);
            cadena = separaOperacion(cadena,"*");
            cadena = separaOperacion(cadena,"/");
            cadena = separaOperacion(cadena,"%");
            cadena = separaOperacion(cadena,"+");
            cadena = separaOperacion(cadena, "-");

            return cadena;
    }

    public void asignacionDeVariable(String linea){
        if (linea.contains("=")){
            String nombreVariable = linea.split("=")[0].replaceAll(" ", "");
            String operacion = linea.split("=")[1].replaceAll(" ", "").replace(";", "");

            ArrayList<String> cadena = separarOperaciones(operacion);
            BigInteger resultado = new BigInteger("0");
           

            if(validarVariablesInicializadas(cadena)){
                if (cadena.size() == 1){
                    if(cadena.get(0).contains("$")){
                        resultado = this.variables.get(cadena.get(0));
                        variables.put(nombreVariable, resultado);
                    }else{
                        resultado = new BigInteger(cadena.get(0));
                        variables.put(nombreVariable, resultado);
                    }
                }else{
                    resultado = calcularOperacion(cadena);
                    variables.put(nombreVariable, resultado);
                }
                
            }else{
                System.out.println("Error de inicializacion de variable");
                System.exit(0);
            }
        }
        else{
            String nombreVariable = linea.replaceAll(" ", "").replaceAll(";", "");
            variables.put(nombreVariable, null);
        }
    }

    public Boolean validarVariablesInicializadas(ArrayList<String> cadena) {
        Boolean valido = true;
        for (String string : cadena) {
            if(string.contains("$")){
                if (this.variables.get(string) == null){
                    valido=false;
                } 
            }
        }
        return valido;
    }

    public ArrayList<String> separaOperacion(ArrayList<String> cadena, String operacion) {
        ArrayList<String> cadenaAux = new ArrayList<>();
        
        for (String string : cadena) {
            if ( string.split("\\"+operacion).length > 1){
                for (String string2 : string.split("\\"+operacion)) {
        
                    cadenaAux.add(string2);
                    cadenaAux.add(operacion);
                }
                cadenaAux.remove(cadenaAux.size()-1);
            }else{
                cadenaAux.add(string);
            }
        }
        return cadenaAux;
    }

    public ArrayList<String> realizarOperacion(ArrayList<String> cadena, String operacion) {
        int index = cadena.indexOf(operacion);
        while (index != -1) {

            String numeroString = cadena.get(index-1);
            String numeroString2 = cadena.get(index+1);

            BigInteger num1;
            BigInteger num2;

            if(numeroString.contains("$")){
                num1 = this.variables.get(numeroString);
            }else{
                num1 = new BigInteger(numeroString);
            }

            if(numeroString2.contains("$")){
                num2 = this.variables.get(numeroString2);
            }else{
                num2 = new BigInteger(numeroString2);
            }
            
            BigInteger resultado = operacionBigInteger(num1, num2, operacion);
            //System.out.println("------------------------");
            
            cadena.remove(index+1);
            cadena.remove(index);
            cadena.remove(index-1);
            cadena.add(index-1, ""+resultado);

            index = cadena.indexOf(operacion);
        }
        return cadena;
    }

    public BigInteger calcularOperacion(ArrayList<String> cadena) {
        ArrayList<String> resultado = cadena;
        /*System.out.println("-----------    --------------------");
        System.out.println("*");*/
        resultado = realizarOperacion(resultado, "*");
        /*for (String string : resultado) {
            System.out.println(string);
        }
        System.out.println("-------------------------------");
        System.out.println("/");*/
        resultado = realizarOperacion(resultado, "/");
        /*for (String string : resultado) {
            System.out.println(string);
        }
        System.out.println("-------------------------------");
        System.out.println("%");*/
        resultado =realizarOperacion(resultado, "%");
        /*for (String string : resultado) {
            System.out.println(string);
        }
        System.out.println("-------------------------------");
        System.out.println("+");*/
        resultado = realizarOperacion(resultado, "+");
        /*for (String string : resultado) {
            System.out.println(string);
        }
        System.out.println("-------------------------------");
        System.out.println("-");*/
        resultado = realizarOperacion(resultado, "-");
        /*for (String string : resultado) {
            System.out.println(string);
        }
        System.out.println("-------------------------------");
        System.out.println("Error : "+resultado.get(0));*/
        return new BigInteger(resultado.get(0));
    }

    public BigInteger operacionBigInteger(BigInteger num1, BigInteger num2, String operacion) {
        BigInteger resultado = new BigInteger("1");
        switch (operacion) {
            case "*":
                resultado = num1.multiply(num2);
                break;
            case "/":
                resultado = num1.divide(num2);
                break;
            case "%":
                resultado = num1.remainder(num2);
                break;
            case "+":
                resultado = num1.add(num2);
                break;
            case "-":
                resultado = num1.subtract(num2);
                break;
        }
        return resultado;
    }


    
}
