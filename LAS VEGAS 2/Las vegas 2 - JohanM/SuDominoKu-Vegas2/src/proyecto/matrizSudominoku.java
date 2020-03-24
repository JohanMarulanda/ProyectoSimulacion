/**
* Johan Marulanda 1556060-3743
* Manuel Victoria 1556231-3743
* Cristhian Rendon 1556246-3743
*/
package proyecto;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;

public class matrizSudominoku {

    private static int[][] memoriza;
    private static int[][] tablero;
    private static int[][] candidatos;
    private static ArrayList<Ficha> listaDeFichas;
    private static ArrayList<Posicion> sinUsar;
    private static ArrayList<Ficha> copiaFichas;
    public static int fichasUsadas;

    public matrizSudominoku() {
        //SE INICIALIZA EL TABLERO Y CREAN LAS FICHAS
        tablero = new int[9][9];
        listaDeFichas = new ArrayList<Ficha>(36);
        fichasUsadas = 0;
        //se crean las 36 piezas del domino
        for (int i = 1; i < 9; i++) {
            for (int k = i + 1; k < 10; k++) {
                Ficha newFicha = new Ficha(i, k, 0);
                listaDeFichas.add(newFicha);
            }
        }
    }

    /**
     * METODO QUE BUSCA EN LA FILA DADA SI EL NUMERO YA SE ENCUENTRA USADO
     */
    public static boolean ingresaFila(int t[][], int fila, int n) {
        for (int i = 0; i < 9; i++) {
            if (n == t[fila][i]) {
                return false;
            }
        }
        return true;
    }

    //Funcion usada para cargar el tablero
    public static void cargarTablero(String nombreArchivo) {
        try {
            Scanner input = new Scanner(new File("src/proyecto/" + nombreArchivo + ".txt"));
            for (int i = 0; i < 9; ++i) {//filas
                for (int j = 0; j < 9; ++j) {//columnas
                    if (input.hasNextInt()) {
                        tablero[i][j] = input.nextInt();
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(matrizSudominoku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * METODO QUE BUSCA EN LA COLUMNA DADA SI EL NUMERO YA SE ENCUENTRA USADO
     */
    public static boolean ingresaColumna(int t[][], int columna, int n) {
        for (int i = 0; i < 9; i++) {
            if (n == t[i][columna]) {
                return false;
            }
        }
        return true;
    }

    /**
     * METODO QUE BUSCA EN LA REGION EN DONDE SE ENCUENTRA EL NUMERO SI EL
     * NUMERO YA SE ENCUENTRA USADO
     */
    public static boolean ingresaRegion(int[][] matriz, int x, int y, int n1) {
        if ((x >= 0 && x <= 2) && (y >= 0 && y <= 2)) {
            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }
        if ((x >= 3 && x <= 5) && (y >= 0 && y <= 2)) {
            for (int i = 3; i <= 5; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }
        if ((x >= 6 && x <= 8) && (y >= 0 && y <= 2)) {
            for (int i = 6; i <= 8; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }

        if ((x >= 0 && x <= 2) && (y >= 3 && y <= 5)) {
            for (int i = 0; i <= 2; i++) {
                for (int j = 3; j <= 5; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }
        if ((x >= 3 && x <= 5) && (y >= 3 && y <= 5)) {
            for (int i = 3; i <= 5; i++) {
                for (int j = 3; j <= 5; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }
        if ((x >= 6 && x <= 8) && (y >= 3 && y <= 5)) {
            for (int i = 6; i <= 8; i++) {
                for (int j = 3; j <= 5; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }

        if ((x >= 0 && x <= 2) && (y >= 6 && y <= 8)) {
            for (int i = 0; i <= 2; i++) {
                for (int j = 6; j <= 8; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }
        if ((x >= 3 && x <= 5) && (y >= 6 && y <= 8)) {
            for (int i = 3; i <= 5; i++) {
                for (int j = 6; j <= 8; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }
        if ((x >= 6 && x <= 8) && (y >= 6 && y <= 8)) {
            for (int i = 6; i <= 8; i++) {
                for (int j = 6; j <= 8; j++) {
                    if (n1 == matriz[i][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * METODO QUE NOS INDICA SI ES POSIBLE ASIGNAR UN NUMERO EN UNA FICHA EN UNA
     * POSICION DEL TABLERO
     */
    public static int asignar(int[][] tabla, int fila, int columna, Ficha f) {
        int grados = f.getAngulo();
        int contenedorFila = fila;
        int contenedorColumna = columna;
        int respuesta = 0;

        //System.out.println("QUIERO COLOCAR ESTA MIERDA: |" + f.getL1() + "|" + f.getL2() + "|" + " en un angulo de " + f.getAngulo());
        //System.out.println("En la posicion" +"[" + fila + "]" +"[" + columna +"]");
        if (tabla[fila][columna] == 0) {
            if (grados == 0) {
                //System.out.println("Entre a los 0 grados wey");
                //System.out.println("La fila de esta mierda es: " + fila + " y la columna " + columna);
                //PARA 0 GRADOS CUANDO SE CUMPLE LAS CONDICIONES
                if ((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8)) {
                    //System.out.println("Cumplo con el rango de fila columna de los 0 grados");
                    if (ingresaFila(tabla, fila, f.getL1()) && ingresaColumna(tabla, columna, f.getL1())
                            && ingresaRegion(tabla, fila, columna, f.getL1())) {

                        if (fila + 1 <= 8 && fila + 1 >= 0) {
                            contenedorFila = fila + 1;
                            //System.out.println("Cumplio con las condiciones del primer numero");

                            if (ingresaFila(tabla, contenedorFila, f.getL2()) && ingresaColumna(tabla, columna, f.getL2())
                                    && ingresaRegion(tabla, contenedorFila, columna, f.getL2())) {
                                //System.out.println("Cumplio con las condiciones del segundo numero");

                                //System.out.println("Quiero imprimir el numero " + f.getL1() + "en ["+ fila +"]" + "["+ columna+ "]");
                                agregaDato(tabla, fila, columna, f.getL1());

                                //System.out.println("Quiero imprimir el numero " + f.getL2() + "en ["+ contenedorFila +"]" + "["+ columna+ "]");
                                agregaDato(tabla, contenedorFila, columna, f.getL2());
                                //imprime_vector(tabla);

                                respuesta = 1;
                            } else if (grados != 270) {
                                int nuevoAngulo = f.getAngulo() + 90;
                                f.setAngulo(nuevoAngulo);
                                //System.out.println("A la ficha " + f.getL1() + "!" + f.getL2() + " le di un angulo de " + f.getAngulo());
                                asignar(tabla, fila, columna, f);
                            }
                        }
                    } else if (grados != 270) {
                        int nuevoAngulo = f.getAngulo() + 90;
                        f.setAngulo(nuevoAngulo);
                        //System.out.println("A la ficha " + f.getL1() + "!" + f.getL2() + " le di un angulo de " + f.getAngulo());
                        asignar(tabla, fila, columna, f);
                    }
                }

            } else if (grados == 90) {
                //System.out.println("Entre a los 90 grados wey");
                //System.out.println("La fila de esta mierda es: " + fila + " y la columna " + columna);
                //PARA 90 GRADOS CUANDO SE CUMPLE LAS CONDICIONES
                //System.out.print("EL DATO l1 ES : " + f.getL1() + " Y EL DATO L2 ES " + f.getL2());
                if ((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8)) {
                    //System.out.println("Cumplo con el rango de fila columna de los 90 grados");
                    if (ingresaFila(tabla, fila, f.getL2()) && ingresaColumna(tabla, columna, f.getL2())
                            && ingresaRegion(tabla, fila, columna, f.getL2())) {
                        //System.out.println("Puedo poner el " + f.getL2() + " en la pos " + "["+ fila +"]" + "["+ columna+ "]");
                        contenedorColumna = columna + 1;

                        if ((fila <= 8 && fila >= 0) && (columna + 1 <= 8 && columna + 1 >= 0)) {
                            if (ingresaFila(tabla, fila, f.getL1()) && ingresaColumna(tabla, contenedorColumna, f.getL1())
                                    && ingresaRegion(tabla, fila, contenedorColumna, f.getL1())) {
                                //System.out.println("Puedo poner el " + f.getL1() + " en la pos " + "["+ fila +"]" + "["+ contenedorColumna + "]");
                                agregaDato(tabla, fila, columna, f.getL2());
                                agregaDato(tabla, fila, contenedorColumna, f.getL1());
                                //imprime_vector(tabla);

                                respuesta = 1;
                            } else if (grados != 270) {
                                int nuevoAngulo = f.getAngulo() + 90;
                                f.setAngulo(nuevoAngulo);
                                //System.out.println("A la ficha " + f.getL1() + "!" + f.getL2() + " le di un angulo de " + f.getAngulo());
                                asignar(tabla, fila, columna, f);
                            }
                        }
                    } else if (grados != 270) {
                        int nuevoAngulo = f.getAngulo() + 90;
                        f.setAngulo(nuevoAngulo);
                        //System.out.println("A la ficha " + f.getL1() + "!" + f.getL2() + " le di un angulo de " + f.getAngulo());
                        asignar(tabla, fila, columna, f);
                    }
                }
            } else if (grados == 180) {
                //System.out.println("Entre a los 180 grados wey");
                //System.out.println("La fila de esta mierda es: " + fila + " y la columna " + columna);
                //PARA 180 GRADOS CUANDO SE CUMPLE LAS CONDICIONES
                if ((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8)) {
                    //System.out.println("Cumplo con el rango de fila columna de los 0 grados");
                    if (ingresaFila(tabla, fila, f.getL2()) && ingresaColumna(tabla, columna, f.getL2())
                            && ingresaRegion(tabla, fila, columna, f.getL2())) {

                        contenedorColumna = columna + 1;
                        //System.out.println("Cumplio con las condiciones del primer numero");
                        if (columna + 1 <= 8 && columna + 1 >= 0) {
                            if (ingresaFila(tabla, fila, f.getL1()) && ingresaColumna(tabla, contenedorColumna, f.getL1())
                                    && ingresaRegion(tabla, fila, contenedorColumna, f.getL1())) {

                                //System.out.println("Quiero imprimir el numero " + f.getL2() + "en ["+ fila +"]" + "["+ columna+ "]");
                                agregaDato(tabla, fila, columna, f.getL2());

                                //System.out.println("Quiero imprimir el numero " + f.getL1() + "en ["+ fila +"]" + "["+ contenedorColumna+ "]");
                                agregaDato(tabla, fila, contenedorColumna, f.getL1());
                                //imprime_vector(tabla);

                                respuesta = 1;

                            } else if (grados != 270) {
                                int nuevoAngulo = f.getAngulo() + 90;
                                f.setAngulo(nuevoAngulo);
                                //System.out.println("A la ficha " + f.getL1() + "!" + f.getL2() + " le di un angulo de " + f.getAngulo());
                                asignar(tabla, fila, columna, f);
                            }
                        }
                    } else if (grados != 270) {
                        int nuevoAngulo = f.getAngulo() + 90;
                        f.setAngulo(nuevoAngulo);
                        //System.out.println("A la ficha " + f.getL1() + "!" + f.getL2() + " le di un angulo de " + f.getAngulo());
                        asignar(tabla, fila, columna, f);
                    }
                }
            } else if (grados == 270) {
                //System.out.println("Entre a los 270 grados wey");
                //System.out.println("La fila de esta mierda es: " + fila + " y la columna " + columna);
                //PARA 270 GRADOS CUANDO SE CUMPLE LAS CONDICIONES
                if ((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8)) {
                    if (ingresaFila(tabla, fila, f.getL1()) && ingresaColumna(tabla, columna, f.getL1())
                            && ingresaRegion(tabla, fila, columna, f.getL1())) {
                        //System.out.println("Puedo poner el " + f.getL1() + " en la pos " + "["+ fila +"]" + "["+ columna+ "]");
                        contenedorColumna = columna + 1;

                        if ((fila <= 8 && fila >= 0) && (columna + 1 <= 8 && columna + 1 >= 0)) {
                            if (ingresaFila(tabla, fila, f.getL2()) && ingresaColumna(tabla, contenedorColumna, f.getL2())
                                    && ingresaRegion(tabla, fila, contenedorColumna, f.getL2())) {
                                //System.out.println("Puedo poner el " + f.getL2() + " en la pos " + "["+ fila +"]" + "["+ contenedorColumna + "]");
                                agregaDato(tabla, fila, columna, f.getL1());
                                agregaDato(tabla, fila, contenedorColumna, f.getL2());

                                respuesta = 1;
                            }
                        }
                    }
                }
            }
        }
        /**
         * Si no se cumple que es 0 el dato en la matriz entonces retorna falso
         * porque ya hay algo
         */
        return respuesta;
    }

    public static void agregaDato(int[][] tabla, int fila, int columna, int dato) {
        if ((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8) && tabla[fila][columna] == 0) {
            tabla[fila][columna] = dato;
        } else {
            System.out.println("No puede poner el dato " + dato + " en [" + fila + "]" + "[" + columna + "]");
        }
    }

    /**
     * METODO PARA SABER SI EL JUEGO YA SE TERMINO. EN EL CASO EN QUE TODAS LAS
     * POSICIONES ESTEN LLENAS
     */
    public static boolean termino(int[][] matriz) {
        boolean resultado = true;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == 0) {
                    resultado = false;
                }
            }
        }
        return resultado;
    }

    public void eliminaDato(int[][] tabla, int fila, int columna) {
        tabla[fila][columna] = 0;
    }

    //METODO QUE IMPRIME EL VECTOR.
    public static void imprime_vector(int[][] matriz) {
        System.out.println(" 같같같같같같같같같같같같같같같같같같?");

        for (int f = 0; f < matriz.length; f++) {
            for (int c = 0; c < matriz.length; c++) {
                System.out.print(matriz[f][c] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Creamos una funcion que retorna la posicion del primer espacio libre en
     * mi matriz
     */
    public static Point buscaEspacio(int[][] tabla) {
        Point posicion = new Point();
        boolean resp = false;

        for (int i = 0; i < 9 && (resp != true); i++) {
            for (int j = 0; j < 9 && (resp != true); j++) {
                if (tabla[i][j] == 0) {
                    posicion.setLocation(i, j);
                    resp = true;
                }
            }
        }
        return posicion;
    }

    /**
     * Coloca en una matriz los valores que nos dan inicialmente en la entrada
     */
    public static int[][] cargar_juego(int[][] matriz) {

        matriz = new int[9][9];

        matriz[0][1] = 7;
        matriz[0][2] = 2;
        matriz[0][8] = 5;
        matriz[1][1] = 6;
        matriz[1][2] = 1;
        matriz[1][6] = 8;
        matriz[1][7] = 4;
        matriz[1][8] = 2;
        matriz[2][2] = 9;
        matriz[2][3] = 2;
        matriz[2][4] = 1;
        matriz[2][5] = 8;
        matriz[2][8] = 7;
        matriz[3][2] = 6;
        matriz[3][8] = 3;
        matriz[4][2] = 8;
        matriz[4][4] = 6;
        matriz[5][0] = 7;
        matriz[5][4] = 3;
        matriz[5][5] = 2;
        matriz[5][6] = 7;
        matriz[5][7] = 6;
        matriz[6][0] = 4;
        matriz[6][3] = 5;
        matriz[6][4] = 9;
        matriz[8][0] = 9;
        matriz[8][3] = 4;
        matriz[8][7] = 7;
        matriz[8][8] = 8;

        return matriz;
    }

    /**
     * Este metodo era usado para la funcion resuelveConCandidatos. Retorna true
     * si es posible meter una ficha en una pos dada de la matriz. (Aun falta
     * rotarla. O Tal vez meter un contador de rotaciones en que sirvio)
     */
    public static boolean sacaPosibilidad(int[][] tabla, int fila, int columna, Ficha f) {
        int grados = f.getAngulo();
        int contenedorFila = fila;
        int contenedorColumna = columna;
        boolean respuesta = false;

        if (tabla[fila][columna] == 0) {

            if (grados == 90) {
                if ((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8)) {

                    if (ingresaFila(tabla, fila, f.getL1()) && ingresaColumna(tabla, columna, f.getL1())
                            && ingresaRegion(tabla, fila, columna, f.getL1())) {

                        contenedorFila = fila - 1;
                        contenedorColumna = columna - 1;
                        if ((fila - 1 <= 8 && fila - 1 >= 0) && (columna - 1 <= 8 && columna - 1 >= 0)) {
                            if (ingresaFila(tabla, contenedorFila, f.getL2()) && ingresaColumna(tabla, contenedorColumna, f.getL2())
                                    && ingresaRegion(tabla, contenedorFila, contenedorColumna, f.getL2())) {

                                respuesta = true;
                            }
                        }
                    }
                }
            } else if (grados == 0) {
                if ((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8)) {

                    if (ingresaFila(tabla, fila, f.getL1()) && ingresaColumna(tabla, columna, f.getL1())
                            && ingresaRegion(tabla, fila, columna, f.getL1())) {

                        if (fila + 1 <= 8 && fila + 1 >= 0) {
                            contenedorFila = fila + 1;

                            if (ingresaFila(tabla, contenedorFila, f.getL2()) && ingresaColumna(tabla, columna, f.getL2())
                                    && ingresaRegion(tabla, contenedorFila, columna, f.getL2())) {

                                respuesta = true;
                            }
                        }
                    }
                }

            } else if (grados == 180) {

                if ((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8)) {

                    if (ingresaFila(tabla, fila, f.getL2()) && ingresaColumna(tabla, columna, f.getL2())
                            && ingresaRegion(tabla, fila, columna, f.getL2())) {

                        contenedorColumna = columna + 1;

                        if (columna + 1 <= 8 && columna + 1 >= 0) {
                            if (ingresaFila(tabla, fila, f.getL1()) && ingresaColumna(tabla, contenedorColumna, f.getL1())
                                    && ingresaRegion(tabla, fila, contenedorColumna, f.getL1())) {

                                respuesta = true;

                            }
                        }
                    }
                }
            } else if (grados == 270) {

                if ((fila >= 0 && fila <= 8) && (columna >= 0 && columna <= 8)) {

                    if (ingresaFila(tabla, fila, f.getL2()) && ingresaColumna(tabla, columna, f.getL2())
                            && ingresaRegion(tabla, fila, columna, f.getL2())) {

                        contenedorFila = fila - 1;
                        contenedorColumna = columna - 1;
                        if ((columna - 1 >= 0 && columna - 1 <= 8) && (fila - 1 <= 8 && fila - 1 >= 0)) {
                            if (ingresaFila(tabla, contenedorFila, f.getL1()) && ingresaColumna(tabla, contenedorColumna, f.getL1())
                                    && ingresaRegion(tabla, contenedorFila, contenedorColumna, f.getL1())) {

                                respuesta = true;
                            }
                        }
                    }
                }
            }
        }
        return respuesta;
    }

    /*
     * Crea una matriz que contiene la cantidad de candidatos en un espacio de la matriz. El que tenga 1 en la matriz
     * solo tiene una posible ficha para entrar. (Falta rotarla. Aqui solo lo hace con 0 grados)
     */
    public static int[][] creaMatrizDePosibilidades(int[][] tabla, ArrayList<Ficha> listaF, int[][] candi) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ((i >= 0 && i <= 8) && (j >= 0 && j <= 8)) {
                    for (int k = 0; k < listaF.size(); k++) {
                        Ficha f = listaF.get(k);
                        if (sacaPosibilidad(tabla, i, j, f)) {
                            candi[i][j] = candi[i][j] + 1;
                        }
                    }
                }
            }
        }
        return candi;
    }

    /**
     * Funcion que resuelve el sudoku y retorna una solucion no factible
     */
    public static void resuelveSudoku(int[][] tablero, ArrayList<Ficha> listaF) {
        //Point p = buscaEspacio(tablero);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tablero[i][j] == 0) {
                    for (int k = 0; k < listaF.size(); k++) {
                        asignar(tablero, i, j, listaF.get(k));
                    }
                }
            }
        }
    }

    /**
     * Funcion que resuelve el sudominoku teniendo en cuenta la matriz de
     * candidatos
     */
    public static void resuelveConCandidatos(int[][] tablero, int[][] candi, ArrayList<Ficha> listaF) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tablero[i][j] == 0 && candi[i][j] == 1) {
                    for (int k = 0; k < listaF.size(); k++) {
                        asignar(tablero, i, j, listaF.get(k));
                    }
                }
            }
        }
        if (termino(tablero) != true) { //Esto es algo innesesario XD
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (tablero[i][j] == 0 && candi[i][j] == 1) {
                        for (int k = 0; k < listaF.size(); k++) {
                            asignar(tablero, i, j, listaF.get(k));
                        }
                    }
                }
            }
        }
    }

    /**
     * Funcion usada para otra de las soluciones que propuse. La que solucionaba
     * una vez la matriz y almacenaba en un array de posiciones las posiciones
     * en donde en la soluci? anterior se habia quedado en 0. Esta le llega ese
     * arreglo y le da prioridad a las posiciones que estan en el para colocar
     * las fichas
     */
    public static void darlePrioridad(int[][] tabla, ArrayList<Posicion> pos, ArrayList<Ficha> listaF) {
        for (int i = 0; i < pos.size(); i++) {
            for (int k = 0; k < listaF.size(); k++) {
                Posicion p = pos.get(i);
                asignar(tabla, p.getX(), p.getY(), listaF.get(k));
            }
        }
    }

    /**
     * Recibe como parametro 2 matrices t1 y t2. Copia en t1 todo lo que
     * contiene t2
     */
    public static int[][] copiaMatriz(int[][] t1, int[][] t2) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                t1[i][j] = t2[i][j];
            }
        }
        return t1;
    }

    public static int[][] borraMatriz(int[][] t1) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                t1[i][j] = 0;
            }
        }
        return t1;
    }

    /**
     * Pone en un arreglo de posiciones. Las posiciones en donde encontro que no
     * puso nada en la tabla
     */
    public static void guardaPosicionesSinUsar(int[][] tabla) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tabla[i][j] == 0) {
                    Posicion p = new Posicion(i, j);
                    sinUsar.add(p);
                }
            }
        }
    }

    /**
     * Funcion usada para imprimir las posiciones de la funcion de arriba. Fue
     * solo una prueba
     */
    public static void imprimePosiciones(ArrayList<Posicion> p) {
        for (int i = 0; i < p.size(); i++) {
            Posicion algo = p.get(i);
            System.out.println("No usaste la posicion " + algo.getX() + "," + algo.getY());
        }
    }

    /**
     * METODO NUEVO EL CUAL RETORNA UNA POSICION EN EL TABLERO VACIA
     * ALEATORIZADA
     *
     * @param tablero
     * @return
     */
    public static Posicion buscaCasillaAzar(int[][] tablero) {
        int x = 0, y = 0;
        Posicion f = new Posicion(x, y);
        do {
            x = (int) (Math.random() * 9);
            y = (int) (Math.random() * 9);
        } while (tablero[x][y] != 0);
        f.setX(x);
        f.setY(y);
        return f;
    }

    /**
     * METODO NUEVO USADO PARA SELECCIONAR UNA FICHA AL AZAR EN UN ESPACIO LIBRE
     *
     * @param x fila
     * @param y columna
     * @return ficha
     */
    public static Ficha escogeFichaAzar(int[][] tablero, int x, int y) {
        System.out.println();
        Ficha seleccion = new Ficha();
        //Hacemos una copia de las fichas para poder disponer de ella en la posicion dada, si no se puede poner la ficha seleccionada
        // se elimina de fichasTemporal y pasamos a seleccionar otra, si la ficha si se pudo poner, se elimina de la lista original
        ArrayList<Ficha> fichasTemporal = new ArrayList<>(listaDeFichas);
        int[][] copiaTablero = tablero;

        boolean fichaEncontrada = false;

        while (fichaEncontrada == false && fichasTemporal.size() > 0) {
            int tamaño = fichasTemporal.size();
            //Seleccionamos un numeroaleatorio entre el tamaño del arreglo de fichas temporal
            int random = (int) (Math.random() * tamaño);
            seleccion = fichasTemporal.get(random);

            if (asignar(copiaTablero, x, y, seleccion) == 1) {
                fichaEncontrada = true;
                listaDeFichas.remove(random);
            } else {
                fichasTemporal.remove(random);
            }
        }

        if (fichaEncontrada) {
            //Ficha encontrada
            return seleccion;
        } else {
            return new Ficha(-1, -1, -1);
        }
    }

    public static boolean ejecucion() {
        fichasUsadas = 0;
        System.out.println("************************************************************************************");
        cargarTablero("tablero");

        System.out.println();
        System.out.println("Esta es la matriz original");
       // tablero = cargar_juego();
        imprime_vector(tablero);
        fichasUsadas = 0;
        boolean resultado = false;

        System.out.println();

        while (!termino(tablero)) {
            //sinUsar = new ArrayList<Posicion>();
            //copiaMatriz(memoriza, tablero);
            //guardaPosicionesSinUsar(memoriza);
            System.out.println();
            //imprimePosiciones(sinUsar);

            Posicion punto = buscaCasillaAzar(tablero);
            Ficha fichaAleatoria = escogeFichaAzar(tablero, punto.getX(), punto.getY());

            //Esto significa que no encontro una ficha para la posicion aleatoria generada
            if (fichaAleatoria.getL1() == -1) {
                System.out.println();
                System.out.println("Me disculpo, falle buscnado la solucion, volveré a intentar");
                System.out.println("Este fue el tablero que quedó después de que fallara: ");
                imprime_vector(tablero);
                fichasUsadas = 36 - listaDeFichas.size();
                resultado = false;
                return false;
            }

            if (listaDeFichas.isEmpty()) {
                System.out.println();
                System.out.println("Encontré una solucion!!!");
                resultado = true;
            }

            asignar(tablero, punto.getX(), punto.getY(), fichaAleatoria);
            imprime_vector(tablero);

            System.out.println("*******************************************************************************************");
            System.out.println("La ficha que obtube fue: [" + fichaAleatoria.getL1() + "," + fichaAleatoria.getL2() + "] En un angulo de:  " + fichaAleatoria.getAngulo());
            System.out.println("*******************************************************************************************");
        }
        return resultado;
    }

    public static void main(String args[]) {
        try {
            int exito = 0;
            int fracaso = 0;
            ArrayList arrayPiezasUsadas = new ArrayList();

            while(exito == 0){  
               matrizSudominoku algo = new matrizSudominoku();
                if (ejecucion() == false) {
                    fracaso++;
                } else {
                    exito++;
                }
                arrayPiezasUsadas.add(fichasUsadas);
            }
            double promedioUsadas = 0;
            System.out.println("Ejecuciones Exitosas: " + exito);
            System.out.println("Ejecuciones Fracasadas: " + fracaso);
            System.out.println("Maximas piezas usadas " + Collections.max(arrayPiezasUsadas));
            System.out.println("Minimas piezas usadas " + Collections.min(arrayPiezasUsadas));
            for (int i = 0; i < arrayPiezasUsadas.size(); i++) {
                promedioUsadas += Long.valueOf(arrayPiezasUsadas.get(i).toString());
            }
            System.out.println("Promedio piezas usadas " + promedioUsadas / arrayPiezasUsadas.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
