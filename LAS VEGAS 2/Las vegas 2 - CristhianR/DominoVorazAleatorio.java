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
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author cristhian
 */
public class DominoVorazAleatorio {

    public ArrayList<int[][]> fichas;
    public int[][] tablero; //tblero de juego sudominoku
    public ArrayList<Integer> llenado = new ArrayList<>();

    /**
     * cre todas la fichas de juego con un estado de disponibles y en 0 grados
     */
    public void crearFichas() {
        fichas = new ArrayList<>();
        for (int i = 2; i <= 9; i++) {
            int[][] temp = new int[10 - i][3];
            for (int j = 0; j <= (9 - i); j++) {
                temp[j][0] = j + i;
            }
            fichas.add(temp);
        }
    }

    /**
     * busca si una ficha esta disponible si devuelve true es porque la ficha
     * esta disponible
     *
     * @param a
     * @param b
     * @return
     */
    public boolean busquedaAux(int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        return (fichas.get(min - 1)[max - min - 1][1] == 0);
    }

    /**
     * marca una ficha como usada y guarda la rotacion en que se uso
     *
     * @param a
     * @param b
     * @param rotacion
     */
    public void extaerFicha(int a, int b, int rotacion) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        if (busquedaAux(a, b)) {
            fichas.get(min - 1)[max - min - 1][1] = 1;
            fichas.get(min - 1)[max - min - 1][2] = rotacion;
        } else {
            System.out.println("Ficha ya utilizada pruebe otra");
        }
    }

    /**
     * marca una ficha como disponible
     *
     * @param a
     * @param b
     */
    public void ingresarFicha(int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        if (!busquedaAux(a, b)) {
            fichas.get(min - 1)[max - min - 1][1] = 0;
            fichas.get(min - 1)[max - min - 1][2] = 0;
        } else {
            System.out.println("Ficha ya disponible pruebe otra");
        }
    }

    /**
     * da informacion de la ficha
     *
     * @param a
     * @param b
     */
    public void buscarFicha(int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        if (busquedaAux(a, b)) {
            System.out.println("estado = disponible");
            System.out.println("rotacion = 0Âº");
        } else {
            System.out.println("estado = utilizada");
            System.out.println("rotacion = " + fichas.get(min - 1)[max - min - 1][2] + "Âº");
        }
    }

    /**
     * comprueba que el numero no este en la horizontal(fila)
     *
     * @param matriz
     * @param x
     * @param n1
     * @return
     */
    public boolean comprobarHorizontal(int[][] matriz, int x, int n1) {
        for (int i = 0; i < 9; i++) {
            if (n1 == matriz[x][i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * combrueba que el numero no este en la vertical(columna)
     *
     * @param matriz
     * @param y
     * @param n1
     * @return
     */
    public boolean comprobarVertical(int[][] matriz, int y, int n1) {
        for (int i = 0; i < 9; i++) {
            if (n1 == matriz[i][y]) {
                return false;
            }
        }
        return true;
    }

    /**
     * compruba que el numero no este en el cudrante
     *
     * @param matriz
     * @param x
     * @param y
     * @param n1
     * @return
     */
    public boolean comprobarCuadrante(int[][] matriz, int x, int y, int n1) {
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
     * comprueba si la ficha se pude ubicar a 0 grados de lo contrario manda un
     * valor grande para descartar la posiblidad
     *
     * @param matriz
     * @param x
     * @param y
     * @param n1
     * @param n2
     * @return
     */
    public int comprobarR0(int[][] matriz, int x, int y, int n1, int n2) {
        if ((x + 1 <= 8)
                && (matriz[x][y] == 0)
                && (matriz[x + 1][y] == 0)
                && (comprobarHorizontal(matriz, x, n2))
                && (comprobarHorizontal(matriz, x + 1, n1))
                && (comprobarVertical(matriz, y, n1))
                && (comprobarVertical(matriz, y, n2))
                && (comprobarCuadrante(matriz, x, y, n2))
                && (comprobarCuadrante(matriz, x + 1, y, n1))) {
            return 1;
        }
        return 360;
    }

    /**
     * comprueba si la ficha se pude ubicar a 90 grados de lo contrario manda un
     * valor grande para descartar la posiblidad
     *
     * @param matriz
     * @param x
     * @param y
     * @param n1
     * @param n2
     * @return
     */
    public int comprobarR90(int[][] matriz, int x, int y, int n1, int n2) {
        if ((y + 1 <= 8)
                && (matriz[x][y] == 0)
                && (matriz[x][y + 1] == 0)
                && (comprobarHorizontal(matriz, x, n2))
                && (comprobarHorizontal(matriz, x, n1))
                && (comprobarVertical(matriz, y, n2))
                && (comprobarVertical(matriz, y + 1, n1))
                && (comprobarCuadrante(matriz, x, y, n2))
                && (comprobarCuadrante(matriz, x, y + 1, n1))) {
            return 90;
        }
        return 360;
    }

    /**
     * comprueba si la ficha se pude ubicar a 180 grados de lo contrario manda
     * un valor grande para descartar la posiblidad
     *
     * @param matriz
     * @param x
     * @param y
     * @param n1
     * @param n2
     * @return
     */
    public int comprobarR180(int[][] matriz, int x, int y, int n1, int n2) {
        if ((x + 1 <= 8)
                && (matriz[x][y] == 0)
                && (matriz[x + 1][y] == 0)
                && (comprobarHorizontal(matriz, x, n2))
                && (comprobarHorizontal(matriz, x + 1, n1))
                && (comprobarVertical(matriz, y, n1))
                && (comprobarVertical(matriz, y, n2))
                && (comprobarCuadrante(matriz, x, y, n2))
                && (comprobarCuadrante(matriz, x + 1, y, n1))) {
            return 180;
        }
        return 360;
    }

    /**
     * comprueba si la ficha se pude ubicar a 270 grados de lo contrario manda
     * un valor grande para descartar la posiblidad
     *
     * @param matriz
     * @param x
     * @param y
     * @param n1
     * @param n2
     * @return
     */
    public int comprobarR270(int[][] matriz, int x, int y, int n1, int n2) {
        if ((y + 1 <= 8)
                && (matriz[x][y] == 0)
                && (matriz[x][y + 1] == 0)
                && (comprobarHorizontal(matriz, x, n2))
                && (comprobarHorizontal(matriz, x, n1))
                && (comprobarVertical(matriz, y, n1))
                && (comprobarVertical(matriz, y + 1, n2))
                && (comprobarCuadrante(matriz, x, y, n1))
                && (comprobarCuadrante(matriz, x, y + 1, n2))) {
            return 270;
        }
        return 360;
    }

    /**
     * busca la poccion del sgt espacio vacio
     *
     * @param matriz
     * @return
     */
    public int[] disponible(int[][] matriz) {
        int[] respuesta = new int[2];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (matriz[x][y] == 0) {
                    respuesta[0] = x;
                    respuesta[1] = y;
                    return respuesta;
                }
            }
        }
        respuesta[0] = 99;
        respuesta[1] = 99;
        return respuesta;
    }

    /**
     * funcion que realiza la comprobacion de las fichas en el tablero
     */
    public int comprobarFichas() {
        Random aleatorio = new Random(System.currentTimeMillis());
        int aletorio1, aletorio2, rotaciones, r0, r90, r180, r270, maxFichas = 0;
        aleatorio.setSeed(System.currentTimeMillis());

        System.out.println("maxFichas anterior " + maxFichas);
        //maxFichas = 0;
        System.out.println("maxFichas nuevo " + maxFichas);
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                //genero dos numeros aleatorios para reprentatar lña ficha
                // Producir nuevo int aleatorio entre 1 y 8
                aletorio1 = Math.round(aleatorio.nextFloat() * (8 - 1) + 1);
                aletorio2 = Math.round(aleatorio.nextFloat() * (9 - (aletorio1 + 1)) + (aletorio1 + 1));
                System.out.println("Ficha escojida |" + aletorio1 + "|" + aletorio2 + "| \n");

                if (busquedaAux(aletorio1, aletorio2)) { // compruebo si la ficha esta disponible en la bolsa de fichas
                    System.out.println("Ficha Disponible");
                    ArrayList<Integer> rotacionesPosibles = new ArrayList<>(); // array para guardar la posible rotacion de la fica en la poscion i,j del tablero 
                    // compruebo las rotaciones validas
                    r0 = comprobarR0(tablero, x, y, aletorio1, aletorio2);
                    r90 = comprobarR90(tablero, x, y, aletorio1, aletorio2);
                    r180 = comprobarR180(tablero, x, y, aletorio1, aletorio2);
                    r270 = comprobarR270(tablero, x, y, aletorio1, aletorio2);

                    //guardo en el array solo las rotaciones validas 
                    if (r0 < 360) {
                        rotacionesPosibles.add(r0);
                    }
                    if (r90 < 360) {
                        rotacionesPosibles.add(r90);
                    }
                    if (r180 < 360) {
                        rotacionesPosibles.add(r180);
                    }
                    if (r270 < 360) {
                        rotacionesPosibles.add(r270);
                    }
                    int caso = 0;

                    //si la ficha esta disponible y se puede poner en alguna poscion escojo esta aleatorioamente
                    if (rotacionesPosibles.size() > 0) {
                        rotaciones = aleatorio.nextInt(rotacionesPosibles.size());
                        caso = rotacionesPosibles.get(rotaciones);
                        System.out.println("rotacion de ficha " + caso);
                    } else {
                        System.out.println("Ficha disponible pero no se puede poner con ninguna rotacion ");
                    }
                    switch (caso) {
                        case 1:
                            tablero[x][y] = aletorio2;
                            tablero[x + 1][y] = aletorio1;
                            extaerFicha(aletorio1, aletorio2, 0);
                            maxFichas++;
                            break;
                        case 90:
                            tablero[x][y] = aletorio2;
                            tablero[x][y + 1] = aletorio1;
                            extaerFicha(aletorio1, aletorio2, 90);
                            maxFichas++;
                            break;
                        case 180:
                            tablero[x][y] = aletorio2;
                            tablero[x + 1][y] = aletorio1;
                            extaerFicha(aletorio1, aletorio2, 180);
                            maxFichas++;
                            break;
                        case 270:
                            tablero[x][y] = aletorio1;
                            tablero[x][y + 1] = aletorio2;
                            extaerFicha(aletorio1, aletorio2, 270);
                            maxFichas++;
                            break;
                        default:
                            for (int a = 0; a < 9; a++) {
                                for (int b = 0; b < 9; b++) {
                                    System.out.print(tablero[a][b] + " ");
                                }
                                System.out.print("\n");
                            }
                            System.out.print("\n\n");
                            break;

                    }
                } else {
                    System.out.println("Ficha invalida compruebe otra \n\n");
                }

            }
        }
        llenado.add(maxFichas);
        System.out.println("llene " + maxFichas);
        return maxFichas;

    }

    /**
     * imprime la fichas que no utilizo
     */
    public int imprimirFichas() {
        int cantidad = 0;
        for (int i = 1; i < 9; i++) {
            for (int j = i + 1; j <= 9; j++) {
                if (busquedaAux(i, j)) {
                    System.out.println(i + " " + j);
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DominoVorazAleatorio d = new DominoVorazAleatorio();
        int parar = 0, suma = 0, promedio = 0, maximo = 0, m = 0;
        do {
            d.crearFichas();
            System.out.println("cantidad de fichas " + d.imprimirFichas());
            d.tablero = new int[9][9];
            d.tablero[0][1] = 7;
            d.tablero[0][2] = 2;
            d.tablero[0][8] = 5;
            d.tablero[2][4] = 1;
            d.tablero[2][5] = 8;
            d.tablero[0][8] = 5;
            d.tablero[3][8] = 3;
            d.tablero[4][4] = 6;
            d.tablero[8][0] = 9;
            d.tablero[8][3] = 4;
            m = d.comprobarFichas();
            suma += m;
            System.out.println("cantidad de iteraciones " + d.llenado.size());
            if (m >= maximo) {
                maximo = m;
            }
            System.out.println("maxima de fichas " + maximo);
            System.out.println("promedio de fichas " + (suma / d.llenado.size()));
            parar++;
        } while (parar < 13);
//cambiar aqui cantidad de iteraciones o poner condicion  de parada cuando m ==36 para aseguar que solo pare cuando llene completamente el tablero 

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(d.tablero[i][j] + " ");
            }
            System.out.print("\n");
        }
        d.imprimirFichas();
    }
}
