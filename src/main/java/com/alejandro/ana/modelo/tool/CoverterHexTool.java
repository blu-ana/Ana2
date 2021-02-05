package com.alejandro.ana.modelo.tool;


import com.alejandro.ana.core.Creador;
import com.alejandro.ana.notas.AnotacionesJava;
import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;
import com.alejandro.ana.pojos.EntidadesPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.List;

@Scope("singleton")
@Component
public class CoverterHexTool {

    private String proyectoName;
    private String paquete;
    private List<EntidadesPojo> entidades;
    private Creador creador;
    private String barra = "";
    private int relantizar = 100;
    private int relantizar2 = 200;
    private Boolean isServer;
    private AnotacionesJava anotacionesJava = new AnotacionesJava();


    protected static final Log logger = LogFactory.getLog(CoverterHexTool.class);

    public void startCreateCoverterHexTool(ArchivoBaseDatosPojo archivo, Creador creadors) {

        this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.paquete = creadors.getPackageNames();
        this.creador = creadors;
        this.barra = creador.getBarra();
        this.anotacionesJava.activateAnotacionesJava(archivo);
        this.createHex();
    }

    private void createHex(){
        String nameOfClass = "CoverterHex";
        try {
            Thread.sleep(relantizar);
            String escritos = metods().toString();
            Thread.sleep(relantizar);
            createArchivo(escritos, nameOfClass);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createArchivo( String escrito, String nameOfClass  ) {

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
        logger.info("Create hex converter metodos ");
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
            sb.append(this.createhexToAscii());
            sb.append("\r\n");

            Thread.sleep(relantizar);
            sb.append(this.createasciiToHex());
            sb.append("\r\n");

            Thread.sleep(relantizar);
            sb.append(this.createmetodoAsc());
            sb.append("\r\n");

            Thread.sleep(relantizar);
            sb.append(this.createascii_To_Hex());
            sb.append("\r\n");

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
        sb1.append("import org.apache.commons.logging.Log;" + "\r\n");
        sb1.append("import org.apache.commons.logging.LogFactory;" + "\r\n");
        sb1.append( "import java.util.Date;"+"\r\n");
        sb1.append("import java.net.*;" + "\r\n");
        sb1.append("import java.io.*;" + "\r\n");
        sb1.append("import java.io.BufferedReader;" + "\r\n");
        sb1.append("import java.io.IOException;" + "\r\n");
        sb1.append("import javax.xml.bind.DatatypeConverter;" + "\r\n");



        return sb1;
    }

    private  StringBuilder createTituloClass() {
        StringBuilder sb2 = new StringBuilder();

        sb2.append("" + "\r\n");
        //  sb2.append("@Component" + "\r\n");
        sb2.append("public class CoverterHex {" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("    private static final Logger LOG = LoggerFactory.getLogger(CoverterHex.class);" + "\r\n");

        sb2.append("" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("" + "\r\n");
        return sb2;
    }


    private  StringBuilder createhexToAscii() {
        StringBuilder sb3 = new StringBuilder();

        sb3.append("" + "\r\n");
        sb3.append(" private static String hexToAscii(String hexStr) {" + "\r\n");
        sb3.append(" StringBuilder output = new StringBuilder(\"\");" + "\r\n");
        sb3.append(" for (int i = 0; i < hexStr.length(); i += 2) {" + "\r\n");
        sb3.append(" String str = hexStr.substring(i, i + 2);" + "\r\n");
        sb3.append("output.append((char) Integer.parseInt(str, 16));" + "\r\n");
        sb3.append(" }" + "\r\n");
        sb3.append("return output.toString();" + "\r\n");
        sb3.append(" }" + "\r\n");
        sb3.append("" + "\r\n");
        sb3.append("" + "\r\n");
        return sb3;
    }


    private  StringBuilder createasciiToHex() {
        StringBuilder sb4 = new StringBuilder();
        sb4.append(" private static String asciiToHex(String asciiStr) {" + "\r\n");
        sb4.append(" char[] chars = asciiStr.toCharArray();" + "\r\n");
        sb4.append(" StringBuilder hex = new StringBuilder();" + "\r\n");
        sb4.append("  for (char ch : chars) {" + "\r\n");
        sb4.append("  hex.append(Integer.toHexString((int) ch));" + "\r\n");
        sb4.append(" }" + "\r\n");
        sb4.append(" return hex.toString();" + "\r\n");
        sb4.append(" }" + "\r\n");
        sb4.append("" + "\r\n");
        return sb4;
    }



    private  StringBuilder createmetodoAsc() {
        StringBuilder sb5 = new StringBuilder();
        sb5.append(" private void metodoAsc(String hex){" + "\r\n");
        sb5.append("StringBuilder output = new StringBuilder();" + "\r\n");
        sb5.append("  for (int i = 0; i < hex.length(); i+=2) { " + "\r\n");
        sb5.append(" String str = hex.substring(i, i+2);" + "\r\n");
        sb5.append(" output.append((char)Integer.parseInt(str, 16));" + "\r\n");
        sb5.append(" }" + "\r\n");
        sb5.append(" System.out.println(output);" + "\r\n");
        sb5.append(" }" + "\r\n");
        sb5.append("// cambiar esta a un return en la practica" + "\r\n");
        sb5.append("" + "\r\n");
        return sb5;
    }



    private  StringBuilder createascii_To_Hex() {
        StringBuilder sb6 = new StringBuilder();
        sb6.append("" + "\r\n");
        sb6.append(" private static String ascii_To_Hex(String asciiStr) {" + "\r\n");
        sb6.append("byte[] s = DatatypeConverter.parseHexBinary(asciiStr);" + "\r\n");
        sb6.append("String asc = new String(s);" + "\r\n");
        sb6.append(" return asc;" + "\r\n");
        sb6.append(" }" + "\r\n");
        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");

        return sb6;
    }




}
