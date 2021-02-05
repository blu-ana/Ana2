package com.alejandro.ana.ServiceImpl.tool;

import com.alejandro.ana.core.Creador;
import com.alejandro.ana.modelo.server.*;
import com.alejandro.ana.modelo.tool.*;
import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;
import com.alejandro.ana.pojos.EntidadesPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope("singleton")
@Component
public class CreateToolImpl {

    @Autowired
    private CreateGetPostTool createGetPostTool;

    @Autowired
    private CreateArchivosManamentTool createArchivosManamentTool;

    @Autowired
    private ServersocketTCP serversocketTCP;

    @Autowired
    private ServerSocketUDP serverSocketUDP;

    @Autowired
    private CoverterHexTool coverterHexTool;

    @Autowired
    private StartServer startServer;

    @Autowired
   private ClientUDP clientUDP;

    @Autowired
    private ClienteTCP clienteTCP;

    @Autowired
    private ExecelToolLeer execelToolLeer;

    @Autowired
    private ExcelToolCrear excelToolCrear;

    private String proyectoName;
    private String paquete;
    private List<EntidadesPojo> entidades;
    private String barra = "";
    private int relantizar = 100;
    private int relantizar2 = 200;
    private ArchivoBaseDatosPojo archivo;
    private Creador creador;
    private Boolean isToolGetPost;
    private Boolean isArchivosManamentTool;


    public void inicioCreate(ArchivoBaseDatosPojo archivos, Creador creadors) {
        this.isToolGetPost = archivos.getToolClassPojo().getGetPostCreateTool();
        this.entidades = archivos.getEntidades();
        this.proyectoName = archivos.getProyectoName();
        this.paquete = creadors.getPackageNames();
        this.creador = creadors;
        this.barra = creador.getBarra();
        this.archivo = archivos;
        this.isArchivosManamentTool = archivos.getToolClassPojo().getArchivosManamentTool();
        this.startCreate();
    }


    private void startCreate() {
        if (this.isToolGetPost != null && this.isToolGetPost) {
            this.createHttpMetodo();
        }
        if (isArchivosManamentTool != null && isArchivosManamentTool) {
            this.archivosManamentTool();
        }
        if (archivo.getToolClassPojo().getServerTcp() != null && archivo.getToolClassPojo().getServerTcp()) {
            this.server_CTP_Tool();
        }

        if (archivo.getToolClassPojo().getServerUdp() != null && archivo.getToolClassPojo().getServerUdp()) {
            this.server_UDP_Tool();
        }

        if (this.archivo.getToolClassPojo().getConverterHex() != null && archivo.getToolClassPojo().getConverterHex()) {
            this.CoverterHexTool();
        }

        if (this.archivo.getToolClassPojo().getServerTcp() != null && this.archivo.getToolClassPojo().getServerUdp()) {
          this.StartServer();
        }

        if (this.archivo.getToolClassPojo().getSocketClientTcp()!= null && this.archivo.getToolClassPojo().getSocketClientTcp()) {
            this.createclienteTCP();
        }

        if (this.archivo.getToolClassPojo().getSocketClientUdp() != null && this.archivo.getToolClassPojo().getSocketClientUdp()) {
            this.createClienteUDP();
        }

        if (this.archivo.getToolClassPojo().getLeerExcel() != null && this.archivo.getToolClassPojo().getLeerExcel()) {
           this.leerExcel();
        }

        if (this.archivo.getToolClassPojo().getCrearExcel() != null &&  this.archivo.getToolClassPojo().getCrearExcel()) {
          this.createExcel();
        }

    }


    /*===============================================================================================================
     * Metodos tools para incorporar al proyecto
     * =============================================================================================================*/

    private void createHttpMetodo() {
        createGetPostTool.startCreateGetPostTool(archivo, creador);
    }

    private void archivosManamentTool() {
        createArchivosManamentTool.startCreateArchivo(archivo, creador);
    }

    private void server_CTP_Tool() {
        serversocketTCP.startCreateServersocket_TCP(archivo, creador);
    }

    private void server_UDP_Tool() {
        serverSocketUDP.startCreateServerSocketUDP(archivo, creador);
    }

    private void CoverterHexTool() {
        coverterHexTool.startCreateCoverterHexTool(archivo, creador);
    }

    private void StartServer() {
        startServer.startCreateStartServer(archivo, creador);
    }

    private void createclienteTCP() {
        clienteTCP.startCreateServerSocketTCP(archivo, creador);
    }
    private void createClienteUDP() {
        clientUDP.startCreateServerSocketUDP(archivo, creador);
    }

    private void leerExcel() {
        execelToolLeer.startCreateExecelToolLeer(archivo, creador);
    }

    private void createExcel() {
        excelToolCrear.startCreateExcelToolCrear(archivo, creador);
    }


}
