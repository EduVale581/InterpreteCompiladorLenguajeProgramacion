

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eduardovalenzuela
 */
public class ParsearAritmetica {
    
    private String cadena;
    
    public boolean ejecucion(String valor){
        cadena =valor;
        boolean resultado = false;
        for (int i = 0; i < cadena.length(); i++) {
            resultado = parseE(i);
        }
        return resultado;
    }
    
    private boolean parseE(int i){

        if (!parseT(i)){
            
            return false;
        }
        
        if (!parseEP(i)){
            return false;
        }
        return true;
    }
    
    private boolean parseEP(int i){

        if(i+1<cadena.length() && (cadena.charAt(i+1) == '+' || cadena.charAt(i+1) == '-')){
            
            return parseEP1(i);
            
        }
        return parseEP2(i);
        
    }
    
    private boolean parseEP1(int i){

        cadena = cadena.substring(0, i) + cadena.substring(i + 1);
        
        if (!parseT(i)) return false;
        
        if (!parseEP(i)) return false;
        return true;
        
    }
    
    private boolean parseEP2(int i){
        return true;
        
    }
    
    private boolean parseT(int i){
        if (!parseF(i)) return false;
        
        if (!parseTP(i)) return false;
        return true;
    }
    
    private boolean parseTP(int i){
        if(i+1<cadena.length() && (cadena.charAt(i+1) == '/' || cadena.charAt(i+1) == '*')){
            return parseEP1(i);
            
        }
        return parseEP2(i);
        
    }
    
    private boolean parseTP1(int i){
        cadena = cadena.substring(0, i) + cadena.substring(i + 1);
        
        if (!parseF(i)) return false;
        
        if (!parseTP(i)) return false;
        return true;
        
    }
    
    private boolean parseTP2(int i){
        return true;
        
    }
    
    private boolean parseF(int i){
        if(i+1<cadena.length() && cadena.charAt(i+1) == '('){
            return parseF1(i);
            
        }
        return parseF2(i);
        
        
    }
    
    private boolean parseF1(int i){
        cadena = cadena.substring(0, i) + cadena.substring(i + 1);
        
        if (!parseE(i)) return false;
        
        if (i+1<cadena.length() && cadena.charAt(i+1) != ')') return false;
        cadena = cadena.substring(0, i) + cadena.substring(i + 1);
        
        return true;
        
    }
    
    private boolean parseF2(int i){
        return parseN(i);
        
    }
    
    private boolean parseN(int i){
        if (!parseD(i)) return false;
        
        if (!parseNP(i)) return false;
        return true;
        
    }
    
    private boolean parseNP(int i){
        if(i+1<cadena.length() && Character.isDigit(cadena.charAt(i+1))){
            return parseNP1(i);
            
        }
        return parseNP2(i);
        
    }
    
    private boolean parseNP1(int i){

        
        return parseN(i);
    }
    
    private boolean parseNP2(int i){

        return true;
    }
    
    private boolean parseD(int i){

        if(i+1<cadena.length() && Character.isDigit(cadena.charAt(i+1))){

            cadena = cadena.substring(0, i) + cadena.substring(i + 1);
            
            
            return true;
            
        }
        return false;
    }
    
}
