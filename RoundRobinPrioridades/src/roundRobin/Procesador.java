package roundRobin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Procesador {

    private Proceso[] procesos;
    private final int RONDAS_MAX = 2;

    public Procesador(Proceso[] procs) {
        if (procs[0].llegadaT != 0) {
            procs[0].llegadaT = 0;
        }
        this.procesos = procs;
        this.procesos[0].activo = true;
    }
    
    public void mostrarProcesos(){
        int i = 0;
        System.out.println("---------------------");
        for( Proceso p : procesos){
            System.out.println("Proceso " + (i + 1));
            System.out.print(p);
            System.out.println("---------------------");
            i++;
        }
    }

    public void roundRobin() {
        //agregar accion de procesador aca
        int t = 0;
        int p = 0, procesoFinal = 0; //turno 1

        boolean terminado = false;

        while (!terminado) {
            System.out.println("proceso = " + (p + 1));
            System.out.println("t = " + t);
            System.out.println("proceso final = " + (procesoFinal + 1));
            
            //siguiente proceso
            if(procesoFinal + 1 != procesos.length && t >= procesos[procesoFinal + 1].llegadaT){
                procesoFinal++; //agregando proceso
            }
            p = (p == procesoFinal ? 0 : p + 1);
            

            
            //avanzar
            t += procesos[p].prioridad;
            procesos[p].avanza();
            procesos[p].disminuirPrioridad(RONDAS_MAX);

            //verificar si algun proceso ha terminado
            if(procesos[p].debeTerminar()){
                procesos[p].terminar(t);
            }
            
            terminado = true;
            for(Proceso pro : procesos){
                if(pro.activo){
                    terminado = false;
                    break;
                }
            }
        }
    }

}
