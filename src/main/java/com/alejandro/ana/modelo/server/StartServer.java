package com.alejandro.ana.modelo.server;

import com.alejandro.ana.core.Creador;
import com.alejandro.ana.modelo.tool.CreateGetPostTool;
import com.alejandro.ana.notas.AnotacionesJava;
import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;
import com.alejandro.ana.pojos.EntidadesPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope("singleton")
@Component
public class StartServer {

        private String proyectoName;
        private String paquete;
        private List<EntidadesPojo> entidades;
        private Creador creador;
        private String barra = "";
        private int relantizar = 50;
        private int relantizar2 = 60;
        private Boolean isServer;
        private ArchivoBaseDatosPojo archivo;
        private AnotacionesJava anotacionesJava = new AnotacionesJava();


        protected static final Log logger = LogFactory.getLog(CreateGetPostTool.class);

        public void startCreateStartServer(ArchivoBaseDatosPojo archivo, Creador creadors) {
            this.archivo = archivo;
            this.entidades = archivo.getEntidades();
            this.proyectoName = archivo.getProyectoName();
            this.paquete = creadors.getPackageNames();
            this.creador = creadors;
            this.barra = creador.getBarra();
            this.anotacionesJava.activateAnotacionesJava(archivo);
            this.createServersocket();
        }


        private void createServersocket(){
            String nameOfClass = "StartServer";
            try {
                Thread.sleep(relantizar);
                String escritos = metods().toString();
                Thread.sleep(relantizar);
                createArchivoGetPostTool(escritos, nameOfClass);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void createArchivoGetPostTool( String escrito, String nameOfClass  ) {

            try {
                Thread.sleep(relantizar);
                String nombreArchivo = nameOfClass + ".java";
                String entidad_paquete = "serviceImplement";
                String direction = creador.getDireccionDeCarpeta() + proyectoName + barra + "src" + barra + "main" + barra
                        + "java" + barra + creador.getCom() + barra + creador.getPackageNames1() + barra + creador.getArtifact()
                        + barra + entidad_paquete;
                Thread.sleep(relantizar2);
                creador.crearArchivo(direction, escrito, nombreArchivo);
            } catch (Exception e) {
                logger.error(e);
            }
        }


        private StringBuilder metods() {
            StringBuilder sb = new StringBuilder("\r\n");
            logger.info("Create Server socket UDP metodos ");
            try {
                Thread.sleep(relantizar);
                sb.append(this.anotacionesJava.creatNotaClase() + "\r\n");
                sb.append("\r\n");
                sb.append(this.createImport());

                Thread.sleep(relantizar);
                sb.append("\r\n");
                sb.append(this.createTituloClass());
                sb.append("\r\n");

                Thread.sleep(relantizar);
                sb.append(this.createCostructor());
                sb.append("\r\n");

                Thread.sleep(relantizar);
                sb.append(this.createRun());
                sb.append("\r\n");
                
//
//            Thread.sleep(relantizar);
//            sb.append(this.createThreadClass());
//            sb.append("\r\n");
//
//            Thread.sleep(relantizar);
//            sb.append(this.createsendPosta());
//            sb.append("\r\n");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sb.append("}"+"\r\n");
            sb.append(AnotacionesJava.apacheSoftwareLicensed() + "\r\n");
            return sb;
        }


        private  StringBuilder createImport() {
            StringBuilder sb1 = new StringBuilder();
            sb1.append("package " + paquete + ".serviceImplement;" + "\r\n");
            sb1.append("import com.google.gson.Gson;" + "\r\n");
            sb1.append("import org.slf4j.Logger;" + "\r\n");
            sb1.append("import org.slf4j.LoggerFactory;" + "\r\n");
            sb1.append("import java.net.*;" + "\r\n");
            sb1.append("import java.io.*;" + "\r\n");
            sb1.append("import java.io.BufferedReader;" + "\r\n");
            sb1.append("import org.apache.commons.logging.Log;" + "\r\n");
            sb1.append("import org.apache.commons.logging.LogFactory;" + "\r\n");
            sb1.append("import org.springframework.context.annotation.Scope;" + "\r\n");
            sb1.append("import org.springframework.stereotype.Component;" + "\r\n");
            sb1.append( "import java.util.Date;"+"\r\n");
            return sb1;
        }

        private  StringBuilder createTituloClass() {
            StringBuilder sb2 = new StringBuilder();

            sb2.append("" + "\r\n");
            sb2.append("@Scope(\"singleton\")" + "\r\n");
            sb2.append("@Component" + "\r\n");
            sb2.append(" public class StartServer {" + "\r\n");
            sb2.append("" + "\r\n");
            sb2.append("    private static final Logger loger = LoggerFactory.getLogger(StartServer.class);" + "\r\n");
            sb2.append("" + "\r\n");
            sb2.append("    private ServersocketTCP serverTCP;" + "\r\n");
            sb2.append("    private ServerUDP serverUDP;" + "\r\n");
            sb2.append(" // establecer una lista de objetos o un map para los puertos" + "\r\n");
            sb2.append("    " + "\r\n");
            sb2.append("" + "\r\n");
            return sb2;
        }

        private  StringBuilder createCostructor() {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(" public StartServer() {" + "\r\n");
            sb3.append(" }" + "\r\n");
            sb3.append("" + "\r\n");
            return sb3;
        }

        private  StringBuilder createRun() {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("" + "\r\n");
            sb4.append(" public void start() {" + "\r\n");

            if (this.archivo.getToolClassPojo().getServerTcp()) {
                sb4.append("serverTCP = new ServersocketTCP();"+"\r\n");
                sb4.append("serverTCP.start(5555);"+"\r\n");
            }

            if (this.archivo.getToolClassPojo().getServerUdp()) {
                sb4.append("serverUDP = new ServerUDP(5555);"+"\r\n");
                sb4.append("serverUDP.start();"+"\r\n");
            }
            sb4.append("}" + "\r\n");
            sb4.append("" + "\r\n");
            return sb4;
        }



    }

/**
 *
 * // Declaración de un Map (un HashMap) con clave "Integer" y Valor "String". Las claves pueden ser de cualquier tipo de objetos, aunque los más utilizados como clave son los objetos predefinidos de Java como String, Integer, Double ... !!!!CUIDADO los Map no permiten datos atómicos
 * Map<Integer, String> nombreMap = new HashMap<Integer, String>();
 * nombreMap.size(); // Devuelve el numero de elementos del Map
 * nombreMap.isEmpty(); // Devuelve true si no hay elementos en el Map y false si si los hay
 * nombreMap.put(K clave, V valor); // Añade un elemento al Map
 * nombreMap.get(K clave); // Devuelve el valor de la clave que se le pasa como parámetro o 'null' si la clave no existe
 * nombreMap.clear(); // Borra todos los componentes del Map
 * nombreMap.remove(K clave); // Borra el par clave/valor de la clave que se le pasa como parámetro
 * nombreMap.containsKey(K clave); // Devuelve true si en el map hay una clave que coincide con K
 * nombreMap.containsValue(V valor); // Devuelve true si en el map hay un Valor que coincide con V
 * nombreMap.values(); // Devuelve una "Collection" con los valores del Map
 *
 * **/





