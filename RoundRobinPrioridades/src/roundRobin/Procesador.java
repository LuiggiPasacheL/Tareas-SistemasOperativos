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
        
        int momento = 0;
        int procesoActual = 0;
        int cantidadProcesosTerminados = 0;

        boolean terminado = false;

        while (!terminado) {

            mostrarTurnoActual(procesoActual, momento, procesoFinal);

            //siguiente proceso
            procesoActual = siguienteProceso(momento, procesoActual);

            //Solo los procesos activos pueden avanzar
            if (procesos[procesoActual].isActivo()) {

                //avanzar
                momento += procesos[procesoActual].getPrioridad();
                procesos[procesoActual].avanza();

                //verificar si se debe disminuir prioridad
                if (procesos[procesoActual].debeDisminuirPrioiridad(RONDAS_MAX)) {
                    procesos[procesoActual].disminuirPrioridad();
                }

                //verificar si algun proceso ha terminado
                if (procesos[procesoActual].debeTerminar()) {
                    procesos[procesoActual].terminar(momento);
                    cantidadProcesosTerminados++;
                }
            }

            //Si los la cantidad de procesos terminados es igual al numero de procesos se termina
            terminado = (cantidadProcesosTerminados == procesos.length);
        }
    }
    
    public String[] devolverSalidas(){
        String[] salidas = new String[]{
            String.valueOf(procesos[0].getSalidaT()), 
            String.valueOf(procesos[1].getSalidaT()),
            String.valueOf(procesos[2].getSalidaT()), 
            String.valueOf(procesos[3].getSalidaT()), 
            String.valueOf(procesos[4].getSalidaT())
        };
        
        return salidas;
    }

}
