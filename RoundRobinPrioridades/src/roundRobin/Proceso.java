package roundRobin;

public class Proceso {

    public int llegadaT;
    public int duracion;
    public int salidaT;
    public int prioridad;
    public int turnos = 0;
    public int duracionActual = 0;
    public boolean activo = true;//proceso activo = proceso no finalizado(sin tiempo de salida)
    
    public Proceso(int llegadaT, int duracion, int prioridad) {
        this.llegadaT = llegadaT;
        this.duracion = duracion;
        this.prioridad = prioridad;
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
                "tiempo de llegada = " + llegadaT + "\n" +
                "duracion = " + duracionActual + "\n" +
                "tiempo de salida = " + salidaT + "\n";
        return mensaje;
    }
}
