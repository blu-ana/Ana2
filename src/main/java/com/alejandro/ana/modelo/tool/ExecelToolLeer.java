package com.alejandro.ana.modelo.tool;

import com.alejandro.ana.core.Creador;
import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;
import com.alejandro.ana.pojos.EntidadesPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope("singleton")
@Component
public class ExecelToolLeer {

        private String proyectoName;
        private String paquete;
        private List<EntidadesPojo> entidades;
        private Creador creador;
        private String barra = "";
        private int relantizar = 100;
        private int relantizar2 = 200;
        private Boolean isServer;


        protected static final Log logger = LogFactory.getLog(com.alejandro.ana.modelo.tool.ExecelToolLeer.class);

        public void startCreateExecelToolLeer(ArchivoBaseDatosPojo archivo, Creador creadors) {

            this.entidades = archivo.getEntidades();
            this.proyectoName = archivo.getProyectoName();
            this.paquete = creadors.getPackageNames();
            this.creador = creadors;
            this.barra = creador.getBarra();
            this.createEx();
        }

        private void createEx(){
            String nameOfClass ="ExecelToolLeer";
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
            logger.info("Create excel metodo de leer converter metodos ");
            try {
                Thread.sleep(relantizar);
                sb.append("\r\n");
                sb.append(this.createImport());

                Thread.sleep(relantizar);
                sb.append("\r\n");
                sb.append(this.createTituloClass());
                sb.append("\r\n");

                Thread.sleep(relantizar);
                sb.append(this.createContructor());
                sb.append("\r\n");

                Thread.sleep(relantizar);
                sb.append(this.createClas());
                sb.append("\r\n");
                

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sb.append("}"+"\r\n");
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
            sb1.append("import java.io.IOException;" + "\r\n");

            sb1.append("import java.io.File;" + "\r\n");
            sb1.append("import java.io.FileInputStream;" + "\r\n");
            sb1.append("import java.util.Iterator;" + "\r\n");
            sb1.append("import org.apache.poi.ss.usermodel.Cell;" + "\r\n");
            sb1.append("import org.apache.poi.ss.usermodel.Row;" + "\r\n");
            sb1.append("import org.apache.poi.xssf.usermodel.XSSFSheet;" + "\r\n");
            sb1.append("import org.apache.poi.xssf.usermodel.XSSFWorkbook;" + "\r\n");

            return sb1;
        }

        private  StringBuilder createTituloClass() {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("" + "\r\n");
            //  sb2.append("@Component" + "\r\n");
            sb2.append("public class LeerFicherosExcel {" + "\r\n");
            sb2.append("" + "\r\n");
            sb2.append("    private static final Logger LOG = LoggerFactory.getLogger(LeerFicherosExcel.class);" + "\r\n");
            sb2.append("" + "\r\n");
            sb2.append(" String nombreArchivo = \"Inventario.xlsx\";" + "\r\n");
            sb2.append(" String rutaArchivo = \"C:\\\\Ficheros-Excel\\\\\" + nombreArchivo;" + "\r\n");
            sb2.append(" String hoja = \"Hoja1\";" + "\r\n");
            sb2.append("" + "\r\n");
            return sb2;
        }


        private  StringBuilder createContructor() {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("" + "\r\n");
            sb3.append(" public LeerFicherosExcel(String nombreArchivo, String rutaArchivo,String hoja ){" + "\r\n");
            sb3.append(" this.nombreArchivo = nombreArchivo;" + "\r\n");
            sb3.append(" this.rutaArchivo = rutaArchivo;" + "\r\n");
            sb3.append(" this.hoja = hoja;" + "\r\n");
            sb3.append(" }" + "\r\n");
            sb3.append("" + "\r\n");
            sb3.append("" + "\r\n");
            return sb3;
        }



        private  StringBuilder createClas() {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(" private void LeerFicheros() {" + "\r\n");
            sb4.append("   try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {" + "\r\n");

            sb4.append(" //leer archivo excel" + "\r\n");
            sb4.append(" XSSFWorkbook worbook = new XSSFWorkbook(file);" + "\r\n");
            sb4.append(" //obtener la hoja que se va leer" + "\r\n");
            sb4.append("  XSSFSheet sheet = worbook.getSheetAt(0);" + "\r\n");
            sb4.append(" //obtener todas las filas de la hoja excel" + "\r\n");
            sb4.append(" Iterator<Row> rowIterator = sheet.iterator();" + "\r\n");

            sb4.append(" Row row;" + "\r\n");
            sb4.append(" // se recorre cada fila hasta el final" + "\r\n");
            sb4.append(" while (rowIterator.hasNext()) {" + "\r\n");
            sb4.append(" row = rowIterator.next();" + "\r\n");
            sb4.append(" //se obtiene las celdas por fila" + "\r\n");
            sb4.append(" Iterator<Cell> cellIterator = row.cellIterator();" + "\r\n");
            sb4.append(" Cell cell;" + "\r\n");
            sb4.append(" //se recorre cada celda" + "\r\n");

            sb4.append("  while (cellIterator.hasNext()) {" + "\r\n");
            sb4.append("  // se obtiene la celda en espec�fico y se la imprime o se guarda en donde se requiere" + "\r\n");
            sb4.append(" cell = cellIterator.next();" + "\r\n");
            sb4.append(" System.out.print(cell.getStringCellValue()+\" | \");" + "\r\n");
            sb4.append(" }" + "\r\n");
            sb4.append("  System.out.println();" + "\r\n");
            sb4.append(" }" + "\r\n");
            sb4.append(" } catch (Exception e) {" + "\r\n");
            sb4.append(" e.getMessage();" + "\r\n");
            sb4.append("}" + "\r\n");
            sb4.append("}" + "\r\n");
            sb4.append("" + "\r\n");
            sb4.append("" + "\r\n");
            return sb4;
        }

    }



//    private  StringBuilder createmetodoAsc() {
//        StringBuilder sb5 = new StringBuilder();
//        sb5.append(" private void metodoAsc(String hex){" + "\r\n");
//        sb5.append("StringBuilder output = new StringBuilder();" + "\r\n");
//        sb5.append("  for (int i = 0; i < hex.length(); i+=2) { " + "\r\n");
//        sb5.append(" String str = hex.substring(i, i+2);" + "\r\n");
//        sb5.append(" output.append((char)Integer.parseInt(str, 16));" + "\r\n");
//        sb5.append(" }" + "\r\n");
//        sb5.append(" System.out.println(output);" + "\r\n");
//        sb5.append(" }" + "\r\n");
//        sb5.append("// cambiar esta a un return en la practica" + "\r\n");
//        sb5.append("" + "\r\n");
//        return sb5;
//    }
//
//    private  StringBuilder createascii_To_Hex() {
//        StringBuilder sb6 = new StringBuilder();
//        sb6.append("" + "\r\n");
//        sb6.append(" private static String ascii_To_Hex(String asciiStr) {" + "\r\n");
//        sb6.append("byte[] s = DatatypeConverter.parseHexBinary(asciiStr);" + "\r\n");
//        sb6.append("String asc = new String(s);" + "\r\n");
//        sb6.append(" return asc;" + "\r\n");
//        sb6.append(" }" + "\r\n");
//        sb6.append("" + "\r\n");
////        sb6.append("" + "\r\n");
////        sb6.append("" + "\r\n");
//
//        return sb6;
//    }
//







