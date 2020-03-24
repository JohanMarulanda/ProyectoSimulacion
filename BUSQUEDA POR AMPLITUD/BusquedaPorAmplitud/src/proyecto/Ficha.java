/**
* Johan Marulanda 1556060-3743
* Manuel Victoria 1556231-3743
* Cristhian Rendon 1556246-3743
*/
package proyecto;

public class Ficha {

    private int l1;
    private int l2;
    private int angulo;
    
    //Constructor vacio
    public Ficha(){
        
    }
    
    public Ficha(int l1, int l2, int angulo) {
        this.l1 = l1;
        this.l2 = l2;
        this.angulo = angulo;
    }

    public int getL1() {
        return l1;
    }

    public void setL1(int l1) {
        this.l1 = l1;
    }

    public int getL2() {
        return l2;
    }

    public void setL2(int l2) {
        this.l2 = l2;
    }

    public int getAngulo() {
        return angulo;
    }

    public void setAngulo(int angulo) {
        this.angulo = angulo;
    }
}
