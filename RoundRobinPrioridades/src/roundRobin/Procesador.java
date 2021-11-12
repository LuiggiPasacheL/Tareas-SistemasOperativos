package roundRobin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Procesador {
    private Proceso[] procesos;
    private final int CANT_PROC;
    private final int RONDAS_MAX = 2;

    public Procesador(Proceso[] procs) {
        if(procs[0].llegadaT != 0){
            procs[0].llegadaT = 0;
        }
        this.procesos = procs;
        this.procesos[0].activo = true;
        this.CANT_PROC = procesos.length;
    }
    
    public void roundRobin(){
        //agregar accion de procesador aca
        boolean terminado = false;
        int t = 0, p = 0;
        while(!terminado){
            if(procesos[0].activo == false){
                terminado = true;
                break;
            }
            
            //tiempo actual
            t += procesos[p].prioridad;
            
            // avance de proceso
            procesos[p].duracionActual += procesos[p].prioridad;
            procesos[p].turnos++;
            if(procesos[p].turnos % RONDAS_MAX == 0 && procesos[p].prioridad > 1){
                procesos[p].prioridad--;
            }
            //--------------------------------------------
            
            //otro proceso llega
            if(procesos[p + 1] != null 
                    && !procesos[p + 1].activo && t >= procesos[p + 1].llegadaT ){
                p++;
                procesos[p].activo = true;
                continue;
            }
            //no puede avanzar
            if(procesos[p].prioridad == 1){
                p = (p != 0 ? 0 : p + 1);
                //modificar esto
            }
            
            //terminar procesos
            if(procesos[p].duracionActual == procesos[p].duracion){
                procesos[p].activo = false;
            }
        }
    }
    
}
