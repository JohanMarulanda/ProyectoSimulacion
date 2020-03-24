/**
 * Johan Marulanda 1556060-3743
 * Manuel Victoria 1556231-3743
 * Cristhian Rendon 1556246-3743
 */


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.ArrayList;
import proyecto.matrizSudominoku.*;


/**
 * Clase que contiene la estructura del arbol, contiene un tablero, la lista de fichas, una copia del  tablero y el nivel actual
 * en que se encuenta el arbol de recursion
 * @author Johan
 */
public class Estructura {

    private int[][] tablero;
    private int[][] copiaTablero;
    private ArrayList<Ficha> listaDeFichas = new ArrayList<>();
    private ArrayList<Estructura> listaDeHijos; 
    private int nivelActual;

    public Estructura(int[][] tablero, ArrayList<Ficha> listaDeFichas, int nivelActual) {
        this.tablero = tablero;
        this.listaDeFichas = listaDeFichas;
       copiaTablero = new int[9][9];
       this.nivelActual = nivelActual;
       listaDeHijos = new ArrayList<Estructura>();
        generarHijos(tablero, listaDeFichas, nivelActual);
    }

    public int getNivelActul(){
        return nivelActual;
    }
    
    public void setNivelActual(int nivel){
        nivelActual = nivel;
    }

    public int[][] getTablero() {
        return tablero;
    }
    
    public ArrayList<Ficha> getListaDeFichas(){
        return listaDeFichas;
    }

    public ArrayList<Estructura> getListaHijos() {
        return listaDeHijos;
    }

    public void agregarHijo(ArrayList listado, Estructura hijo) {
        listado.add(hijo);
    }
    
    
    //METODO QUE IMPRIME EL VECTOR.
    public void imprime_vecto(int[][] matriz) {
        System.out.println(" 같같같같같같같같같같같같같같같같같같?");

        for (int f = 0; f < matriz.length; f++) {
            for (int c = 0; c < matriz.length; c++) {
                System.out.print(matriz[f][c] + " ");
            }
            System.out.println();
        }
    } 

    /**
     * Recibe como parametro 2 matrices t1 y t2. Copia en t1 todo lo que
     * contiene t2
     */
    public int[][] copiaMatriz(int[][] t1, int[][] t2) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                t1[i][j] = t2[i][j];
            }
        }
        return t1;
    }
    
    
    /**
     * Funcion que expande los nodos hijos dependiendo del nivel de profundidad en el que se encuentre, solo expande los 
     * hijos cullo nivel de profundidad están un nivel por encima del nivel actual
     * @param tablero
     * @param listaDeFichas
     * @param nivelActual 
     */
    public void generarHijos(int[][] tablero, ArrayList<Ficha> listaDeFichas, int nivelActual) {     
       
        copiaMatriz(copiaTablero, tablero);
        Control.nivelActual = nivelActual;
        System.out.println("ESTA ES LA MATRIZ QUE LE LLEGA A MI ESTRUCTURA!!!: ");
        imprime_vecto(tablero);
        
        //Inicializo las matrices a usar para el copiado de datos
        int[][] copiaMatrizOriginal = new int[9][9];
        int[][] matriz2 = new int[9][9];
        
        //Copia los valores a las matrices
        copiaMatriz(copiaMatrizOriginal, copiaTablero);
      //  System.out.println("ESTE ES EL VALOR DE COPIA MATRIZ ORIGINAL : ");
       // imprime_vecto(copiaMatrizOriginal);
        
        copiaMatriz(matriz2, tablero);
      //  System.out.println("ESTE ES EL VALOR DE MATRIZ 2 ANTESS DE ENTRAR AL FOR : ");
       // imprime_vecto(matriz2);
        
        //Inicializamos vectores de copia de fichas
        ArrayList<Ficha> copiaListaFichas = new ArrayList<>(listaDeFichas);
        ArrayList<Ficha> copiaLista2 = new ArrayList<>(listaDeFichas);
        int nivelHijo = nivelActual + 1;
        Ficha f = copiaLista2.get(0);
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                
                matrizSudominoku hijo = new matrizSudominoku(copiaMatrizOriginal,copiaListaFichas);
                //int[][] copiaMatriz = copiaMatrizOriginal;
                
                 //System.out.println("Entre a los for!!");
                if (hijo.asignar(matriz2, i, j, f) == 1 && (nivelHijo - Control.nivelActual) == 1 ) {
                    System.out.println("*******************************************************************************************");
                    System.out.println("*******************************************************************************************");
                    
                    copiaListaFichas.remove(0);
                    
                    Estructura Nodohijo = new Estructura(matriz2,copiaListaFichas, nivelHijo);
                    
                    listaDeHijos.add(Nodohijo);
                    
                    System.out.println(" ");
                    System.out.println("Acabo de generar este muchachon: !");
                    imprime_vecto(Nodohijo.getTablero());
                }
            }
        }
    }
}
