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
public class ServerSocketUDP {

    private String proyectoName;
    private String paquete;
    private List<EntidadesPojo> entidades;
    private Creador creador;
    private String barra = "";
    private int relantizar = 50;
    private int relantizar2 = 60;
    private Boolean isServer;
    private AnotacionesJava anotacionesJava = new AnotacionesJava();


    protected static final Log logger = LogFactory.getLog(CreateGetPostTool.class);

    public void startCreateServerSocketUDP(ArchivoBaseDatosPojo archivo, Creador creadors) {

        this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.paquete = creadors.getPackageNames();
        this.creador = creadors;
        this.barra = creador.getBarra();
        this.anotacionesJava.activateAnotacionesJava(archivo);
        this.createServersocket();
    }

    private void createServersocket(){
        String nameOfClass = "ServerUDP";
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
      //  sb.append("}"+"\r\n");
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
        sb1.append( "import java.util.Date;"+"\r\n");
        return sb1;
    }

    private  StringBuilder createTituloClass() {
        StringBuilder sb2 = new StringBuilder();

        sb2.append("" + "\r\n");
        //  sb2.append("@Component" + "\r\n");
        sb2.append(" public class ServerUDP extends Thread {" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("    private static final Logger loger = LoggerFactory.getLogger(ServerUDP.class);" + "\r\n");
        sb2.append("    protected DatagramSocket socket = null;" + "\r\n");
        sb2.append("    protected boolean running;" + "\r\n");
        sb2.append("    protected byte[] buf = new byte[1024];" + "\r\n");
        sb2.append("    protected byte[] buffer = new byte[1024];" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("" + "\r\n");
        return sb2;
        }

    private  StringBuilder createCostructor() {
        StringBuilder sb3 = new StringBuilder();

        sb3.append(" public ServerUDP(int port) {" + "\r\n");
        sb3.append("    loger.info(\"Iniciado el servidor UDP\");" + "\r\n");
        sb3.append("    try {" + "\r\n");
        sb3.append("    socket = new DatagramSocket(port);" + "\r\n");
        sb3.append("    } catch (SocketException e) {" + "\r\n");
        sb3.append("    e.printStackTrace(); " + "\r\n");
        sb3.append("    socket.close();" + "\r\n");
        sb3.append("    running= false;" + "\r\n");
        sb3.append("    }" + "\r\n");
        sb3.append(" }" + "\r\n");
        sb3.append("" + "\r\n");
        return sb3;
    }





    private  StringBuilder createRun() {
        StringBuilder sb4 = new StringBuilder();
        sb4.append("" + "\r\n");
        sb4.append(" public void run() {" + "\r\n");
        sb4.append("running = true;" + "\r\n");
        sb4.append(" while (running) {" + "\r\n");
        sb4.append("try {" + "\r\n");
        sb4.append("DatagramPacket packet = new DatagramPacket(buf, buf.length);" + "\r\n");
        sb4.append("socket.receive(packet);" + "\r\n");
        sb4.append("// datos del cliente" + "\r\n");
        sb4.append(" InetAddress address = packet.getAddress();" + "\r\n");
        sb4.append("int port = packet.getPort();" + "\r\n");
        sb4.append("loger.info(\"Cliente address : \" + address);" + "\r\n");
        sb4.append(" loger.info(\"Cliente: \" + port);" + "\r\n");
        sb4.append("//Convierto lo recibido y mostrar el mensaje" + "\r\n");
        sb4.append("String mensaje = new String(packet.getData());" + "\r\n");
        sb4.append("loger.info(\"mensaje recibido desde el cliente: \" + mensaje);" + "\r\n");
        sb4.append(" // String received = new String(packet.getData(), 0, packet.getLength());" + "\r\n");
        sb4.append("// if (received.equals(\"end\")) {" + "\r\n");
        sb4.append("// running = false;" + "\r\n");
        sb4.append("//  continue;" + "\r\n");
        sb4.append("// }" + "\r\n");
        sb4.append("    // respuesta del servidos" + "\r\n");
        sb4.append("String respuetasdelserver = \"¡Hola servidor!\";" + "\r\n");
        sb4.append("buffer = respuetasdelserver.getBytes();" + "\r\n");
        sb4.append("//creo el datagrama" + "\r\n");
        sb4.append(" DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, address, port);" + "\r\n");
        sb4.append("socket.send(respuesta);" + "\r\n");
        sb4.append("} catch (IOException e) {" + "\r\n");
        sb4.append(" e.printStackTrace();" + "\r\n");
        sb4.append(" running = false;" + "\r\n");
        sb4.append(" }" + "\r\n");
        sb4.append(" }" + "\r\n");
        sb4.append("socket.close();" + "\r\n");
        sb4.append(" }" + "\r\n");
        sb4.append("}" + "\r\n");
        sb4.append("" + "\r\n");
        return sb4;
    }


//    private  StringBuilder createThreadClass() {
//        StringBuilder sb5 = new StringBuilder();
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        sb5.append("" + "\r\n");
//        return sb5;
//    }
//
//
//    private  StringBuilder createsendPosta() {
//        StringBuilder sb6 = new StringBuilder();
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        return sb6;
//    }

}


