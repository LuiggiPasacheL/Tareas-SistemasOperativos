package roundRobin;

public class Procesador {

    private Proceso[] procesos;
    private final int RONDAS_MAX = 2;
    private int procesoFinal = 0;

    public Procesador(Proceso[] procs) {
        if (procs[0].getLlegadaT() != 0) {
            procs[0].setLlegadaT(0);
        }
        this.procesos = procs;
    }

    public void mostrarProcesos() {
        int i = 0;
        System.out.println("---------------------");
        for (Proceso p : procesos) {
            System.out.println("Proceso " + (i + 1));
            System.out.print(p);
            System.out.println("---------------------");
            i++;
        }
    }

    private void mostrarTurnoActual(int procesoActual, int tiempo, int procesoFinal) {
        System.out.println("proceso = " + (procesoActual + 1));
        System.out.println("t = " + tiempo);
        System.out.println("proceso final = " + (procesoFinal + 1));
    }

    private int siguienteProceso(int tiempo, int proceso) {
        if (procesoFinal + 1 != procesos.length && tiempo >= procesos[procesoFinal + 1].getLlegadaT()) {
            procesoFinal++; //agregando proceso
        }
        proceso = (proceso == procesoFinal ? 0 : proceso + 1);

        return proceso;
    }

    private boolean debeFinalizar() {
        boolean terminado = true;
        for (Proceso pro : procesos) {
            if (pro.isActivo()) {
                terminado = false;
                break;
            }
        }
        return terminado;
    }

    public void roundRobin() {
        //agregar accion de procesador aca
        int t = 0;
        int p = 0; //turno 1

        boolean terminado = false;

        while (!terminado) {

            mostrarTurnoActual(p, t, procesoFinal);

            //siguiente proceso
            p = siguienteProceso(t, p);

            //Solo los procesos activos pueden avanzar
            if (procesos[p].isActivo()) {

                //avanzar
                t += procesos[p].getPrioridad();
                procesos[p].avanza();

                //verificar si se debe disminuir prioridad
                if (procesos[p].debeDisminuirPrioiridad(RONDAS_MAX)) {
                    procesos[p].disminuirPrioridad();
                }

                //verificar si algun proceso ha terminado
                if (procesos[p].debeTerminar()) {
                    procesos[p].terminar(t);
                }
            }

            //Si hay al menos un proceso activo entonces el round robin continúa
            terminado = debeFinalizar();
        }
    }

}
