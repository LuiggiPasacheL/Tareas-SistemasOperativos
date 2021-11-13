package roundRobin;

public class Proceso {

    private int llegadaT;
    private int duracion;
    private int salidaT;
    private int prioridad;
    private int turnos = 0;
    private int duracionActual = 0;
    private boolean activo = true;//proceso activo = proceso no finalizado(sin tiempo de salida)

    public Proceso(int llegadaT, int duracion, int prioridad) {
        this.llegadaT = llegadaT;
        this.duracion = duracion;
        this.prioridad = prioridad;
    }

    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    public int getDuracionActual() {
        return duracionActual;
    }

    public void setDuracionActual(int duracionActual) {
        this.duracionActual = duracionActual;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    public void disminuirPrioridad() {
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
        mensaje
                = "tiempo de llegada = " + llegadaT + "\n"
                + "duracion = " + duracionActual + "\n"
                + "tiempo de salida = " + salidaT + "\n";
        return mensaje;
    }

    public boolean debeDisminuirPrioiridad(int RONDAS_MAX) {
        return this.turnos % RONDAS_MAX == 0 && this.prioridad > 1;
    }
}
