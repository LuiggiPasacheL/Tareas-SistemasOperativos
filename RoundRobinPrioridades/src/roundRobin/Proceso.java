package roundRobin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Proceso {

    public int llegadaT;
    public int duracion;
    public int salidaT;
    public int prioridad;

    public int permanenciaT = 0;
    public int turnos = 0;
    public int duracionActual = 0;
    public boolean activo = false;

    public Proceso(int llegadaT, int duracion, int prioridad) {
        this.llegadaT = llegadaT;
        this.duracion = duracion;
        this.prioridad = prioridad;
    }

    public void reducirPrioridad() {
        if (prioridad != 1 && permanenciaT % 2 != 0 && permanenciaT != 0) {
            prioridad -= 1;
        }
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
        return permanenciaT;
    }

    public void setPresenteT(int presenteT) {
        this.permanenciaT = presenteT;
    }

    public int getSalidaT() {
        return salidaT;
    }

    public void setSalidaT(int salidaT) {
        this.salidaT = salidaT;
    }

    public void avanza() {
        this.duracionActual += this.prioridad;
        this.turnos++;
    }
    
    public void disminuirPrioridad(final int RONDAS_MAXIMAS){
        if(this.turnos % RONDAS_MAXIMAS == 0 && this.prioridad > 1)
            this.prioridad--;
    }

    public boolean validarAvance() {
        return permanenciaT < duracion;
    }
    
    public boolean debeTerminar() {
        return duracionActual == duracion;
    }
    
    public void terminar(int tiempo) {
        activo = false;
        salidaT = tiempo;
        prioridad = 0;
    }

    @Override
    public String toString() {
        String mensaje;
        mensaje = 
                "llegada = " + llegadaT + "\n" +
                "duracion = " + duracionActual + "\n" +
                "final = " + salidaT + "\n";
        return mensaje;
    }
}
