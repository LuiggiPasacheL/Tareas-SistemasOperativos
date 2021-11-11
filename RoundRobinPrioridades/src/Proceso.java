
public class Proceso {
    private int duracion;
    private int prioridad;
    private int llegadaT;
    private int presenteT = 0;
    private int salidaT;
    
    public Proceso(int dur, int priori, int lleg){
        duracion = dur;
        prioridad = priori;
        llegadaT = lleg;
    }
 
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getLlegadaT() {
        return llegadaT;
    }

    public void setLlegadaT(int llegadaT) {
        this.llegadaT = llegadaT;
    }

    public int getPresenteT() {
        return presenteT;
    }

    public void setPresenteT(int presenteT) {
        this.presenteT = presenteT;
    }

    public int getSalidaT() {
        return salidaT;
    }

    public void setSalidaT(int salidaT) {
        this.salidaT = salidaT;
    }
    
    
    public void avanzar(){
        int desfase;
        if(validarAvance()){
            presenteT = presenteT + prioridad;   
        }else{
            desfase = presenteT - duracion;
            presenteT = presenteT - desfase;
        }
       
    }
    
    public boolean validarAvance(){
        return presenteT < duracion;
    }
    
}
