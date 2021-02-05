package com.alejandro.ana.modelo;


import com.alejandro.ana.core.Creador;
import com.alejandro.ana.notas.AnotacionesJava;
import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;
import com.alejandro.ana.pojos.AtributoPojo;
import com.alejandro.ana.pojos.EntidadesPojo;
import com.alejandro.ana.pojos.RelacionPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Scope("singleton")
@Component
public class CreateValidation {

    private String proyectoName;
    private String packageNames;
    private List<EntidadesPojo> entidades;
    private Creador creador;
    private String barra = "";
    private int relantizar = 100;
    private int relantizar2 = 300;
    private String clave = "pojo";
    private List<EntidadesPojo> toPojos = new ArrayList<>();
    private List<EntidadesPojo> toEntidad = new ArrayList<>();
    private AnotacionesJava anotacionesJava = new AnotacionesJava();

    protected static final Log logger = LogFactory.getLog(CreateValidation.class);

    public void startCreacion(ArchivoBaseDatosPojo archivo, Creador creadors) {
        this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.packageNames = archivo.getPackageNames();
        this.creador = creadors;
        this.barra = creador.getBarra();
        this.anotacionesJava.activateAnotacionesJava(archivo);
        this.create(this.entidades);
    }


    private void create(List<EntidadesPojo> entidadesList) {

        if (entidadesList.size() > 0) {
            try {
                Thread.sleep(relantizar);
                this.separateEntidadToPojos(entidadesList);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (EntidadesPojo entidad : entidadesList) {
                if (entidad.getIsEntity()) {
                    String nameOfClass = entidad.getNombreClase() + "Validation";
                    try {
                        if (entidad.getIsEntity()) {
                            Thread.sleep(relantizar);
                            String escritos = metods(entidad).toString();
                            Thread.sleep(relantizar);
                            createArchivo(escritos, nameOfClass);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    private void separateEntidadToPojos(List<EntidadesPojo> entidadesList) {
        logger.info("Inicia la separacion de entidades");
        for (EntidadesPojo entidad : entidadesList) {
            if (entidad.getPaquete().equals(clave) && !entidad.getIsEntity()) {
                toPojos.add(entidad);
            } else {
                toEntidad.add(entidad);
            }
        }
    }


    private  StringBuilder metods(EntidadesPojo entidad ) throws InterruptedException {

        StringBuilder validations = new StringBuilder ();
        Thread.sleep(relantizar);
        validations.append(this.anotacionesJava.creatNotaClase()+"\r\n");
        validations.append(this.createImport(entidad));
        Thread.sleep(relantizar);
        validations.append(this.cabeceraClase(entidad));
        Thread.sleep(relantizar);
        validations.append(this.metodoValidad(entidad));
        Thread.sleep(relantizar);
        validations.append(this.metodovalida_id(entidad));
        Thread.sleep(relantizar);
        validations.append(this.metodovalidation(entidad));
        validations.append("}"+"\r\n");
        validations.append(AnotacionesJava.apacheSoftwareLicensed()+"\r\n");
        return  validations;
    }

    private void createArchivo(String escrito, String nameOfClass) {
        try {
            Thread.sleep(relantizar);
            String nombreArchivo = nameOfClass+".java";
            String entidad_paquete = "validation";
            String direction = creador.getDireccionDeCarpeta() + proyectoName +barra +"src"+barra+"main"+barra+"java"+barra + creador.getCom()
                    + barra + creador.getPackageNames1() + barra + creador.getArtifact()+ barra +"validation";
            Thread.sleep(relantizar2);
            creador.crearArchivo(direction, escrito, nombreArchivo);

        } catch (Exception e) {
            logger.error(e);
        }
    }


    private StringBuilder createImport(EntidadesPojo entidad){

    StringBuilder sb0 = new StringBuilder ();
    logger.info("createService" + "  for Entity:  " + entidad.getNombreClase());

    sb0.append("package " + packageNames + ".validation ;\r\n");
    sb0.append("\r\n");
    sb0.append("import java.util.Optional;");
    sb0.append("\r\n");
    sb0.append("import java.util.ArrayList;");
    sb0.append("\r\n");
    sb0.append("import java.util.List;");
    sb0.append( "import java.util.Date;"+"\r\n");
    sb0.append("\r\n");
    sb0.append("import " + packageNames + "." + entidad.getPaquete()+"."+ entidad.getNombreClase() + ";");
    sb0.append("import java.util.regex.Pattern;");
    sb0.append("\r\n");
    sb0.append("import org.springframework.stereotype.Service;");
    sb0.append("\r\n");

    for (EntidadesPojo entidadPojo : toPojos) {
        String[] clavePojo = entidadPojo.getNombreClase().split("Pojo");
        if (entidad.getNombreClase().equals(clavePojo[0])) {
            sb0.append("import " + packageNames + "." + entidadPojo.getPaquete()+"."+ entidadPojo.getNombreClase() + ";");
        }
    }

    for (RelacionPojo relacion : entidad.getRelaciones()) {
        sb0.append("import " + packageNames + "." + entidad.getPaquete() + "." + relacion.getNameClassRelacion() + ";" + "\r\n");
        //   String[] clavePojo = pojo.getNombreClase().split("Pojo");
        sb0.append("import " + packageNames + ".pojo."+ relacion.getNameClassRelacion() +"Pojo"+ ";" + "\r\n");
    }
    return sb0;
}


    private StringBuilder cabeceraClase(EntidadesPojo entidad){
    StringBuilder sb1 = new StringBuilder ();
    String nameClass =entidad.getNombreClase()+"Validation";
    sb1.append("\r\n");
    sb1.append("\r\n");
    sb1.append("\r\n");
    sb1.append("    @Service" + "\r\n");
    sb1.append("    public class "+nameClass+" {" + "\r\n");
    sb1.append("\r\n");
    // sb1.append("private int validados = "+ entidad.getAtributos().size()+"+"+entidad.getRelaciones().size()+";" + "\r\n");
    // sb1.append("private int contadorValidados = 0;" + "\r\n");
    // sb1.append("private String fallo = \"\";" + "\r\n");
    return sb1;
}


private StringBuilder metodoValidad(EntidadesPojo entidad){

    StringBuilder sb2 = new StringBuilder ();
    String variable = entidad.getNombreClase().toLowerCase();

    sb2.append("        public "+entidad.getNombreClase()+"Pojo valida("+entidad.getNombreClase()+"Pojo "+variable+") {" + "\r\n");
    sb2.append("        "+entidad.getNombreClase()+"Pojo"+" pojo = null;"+"\r\n");
    sb2.append("        try {"+"\r\n");
    sb2.append("             if ("+variable+" != null) {"+"\r\n");
    sb2.append("              if ("+"\r\n");

    for (int i=0; i< entidad.getAtributos().size(); i++) {
        String cadenaOriginal = entidad.getAtributos().get(i).getAtributoName();
        String atributoName = cadenaOriginal.substring(0, 1).toUpperCase() + cadenaOriginal.substring(1).toLowerCase();

        sb2.append("        "+variable + ".get" + atributoName + "() != null");
        if (i < entidad.getAtributos().size() - 1) {
            sb2.append(" &&" + "\r\n");
        }
    }

    sb2.append("        ) {"+"\r\n");
    sb2.append("        pojo = "+variable+";"+"\r\n");
    sb2.append("         }"+"\r\n");
    sb2.append("        }"+"\r\n");
    sb2.append("            return pojo;"+"\r\n");
    sb2.append("        } catch (Exception e) {"+"\r\n");
    sb2.append("            e.printStackTrace();"+"\r\n");
    sb2.append("            return pojo;"+"\r\n");
    sb2.append("        }"+"\r\n");
    sb2.append("    }"+"\r\n");
    return sb2;
}


    private StringBuilder metodovalida_id(EntidadesPojo entidad){

        String datoTipo ="";
        StringBuilder sb3 = new StringBuilder ();
        String variable = entidad.getNombreClase().toLowerCase();
        for (AtributoPojo atributoID : entidad.getAtributos()) {
            if (atributoID.getsId()) { datoTipo = atributoID.getTipoDato(); }
        }
        sb3.append("// remplace de name of variable for you proyecte"+"\r\n");
        sb3.append("    public "+datoTipo+" valida_id( String poder) {"+"\r\n");
        if (datoTipo.equals("Long")) { sb3.append("             Long id_Delete = 0l;");}
        if (datoTipo.equals("Integer")){ sb3.append("       "+ datoTipo+" id_Delete = 0;"); }
        if (datoTipo.equals("Double")){sb3.append( "        "+ datoTipo+" id_Delete = 0.0;"); }
        sb3.append("        try {"+"\r\n");
        sb3.append("          if (poder != null) {"+"\r\n");
        sb3.append("          if (poder.length() > 0 && !Pattern.matches(\"[a-zA-Z]+\", poder)) {"+"\r\n");
        if (datoTipo.equals("Long")) { sb3.append("         id_Delete = Long.parseLong(poder);"); }
        if (datoTipo.equals("Integer")){ sb3.append("       id_Delete = Integer.parseInt(poder);"); }
        if (datoTipo.equals("Double")){ sb3.append("        id_Delete = Double.parseDouble(poder);"); }
        sb3.append("            }"+"\r\n");
        sb3.append("        }"+"\r\n");
        sb3.append("            return id_Delete;"+"\r\n");
        sb3.append("        } catch (Exception e) {"+"\r\n");
        sb3.append("            e.printStackTrace();"+"\r\n");
        sb3.append("            return id_Delete;"+"\r\n");
        sb3.append("        }"+"\r\n");
        sb3.append("    }"+"\r\n");
      return sb3;
    }

    private StringBuilder metodovalidation(EntidadesPojo entidad){

        StringBuilder sb4 = new StringBuilder ();
        sb4.append("    public <T> Object validation(T t) {"+"\r\n");
        sb4.append("         T elemento = null;"+"\r\n");
        sb4.append("        try {"+"\r\n");
        sb4.append("        if (t != null) {"+"\r\n");
        sb4.append("            elemento = t;"+"\r\n");
        sb4.append("        }"+"\r\n");
        sb4.append("            return elemento;"+"\r\n");
        sb4.append("        } catch (Exception e) {"+"\r\n");
        sb4.append("            e.printStackTrace();"+"\r\n");
        sb4.append("            return elemento;"+"\r\n");
        sb4.append("        }"+"\r\n");
        sb4.append("    }"+"\r\n");
        return sb4;
    }
}

















