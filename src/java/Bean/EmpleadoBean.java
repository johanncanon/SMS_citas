/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Archivos;
import Controlador.Empleado;
import Controlador.HojaVida;
import Modelo.SmsCiudad;
import Modelo.SmsEmpleado;
import Modelo.SmsHojavida;
import Modelo.SmsRol;
import Modelo.SmsUsuario;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Desarrollo_Planit
 */
public class EmpleadoBean implements Serializable {

    //objetos necesarios en vista
    protected SmsUsuario usuarioView;
    protected SmsUsuario auxUsuarioView;
    protected SmsEmpleado empleadoView;
    protected SmsCiudad ciudadView;
    protected SmsRol rolView;
    protected SmsHojavida hojavida;
    protected UploadedFile archivo;

    //Control de componentes
    protected boolean habilitado;
    protected boolean registro;

    //Relacion con el controlador
    protected Empleado empleado;
    protected HojaVida hojaVida;
    protected Archivos file;

    public EmpleadoBean() {
        usuarioView = new SmsUsuario();
        auxUsuarioView = new SmsUsuario();
        empleadoView = new SmsEmpleado();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        hojavida = new SmsHojavida();
        hojaVida = new HojaVida();

        habilitado = true;
        registro = true;
    }

    //Getters & Setters
    public SmsUsuario getUsuarioView() {
        return usuarioView;
    }

    public void setUsuarioView(SmsUsuario usuarioView) {
        this.usuarioView = usuarioView;
    }

    public SmsEmpleado getEmpleadoView() {
        return empleadoView;
    }

    public void setEmpleadoView(SmsEmpleado empleadoView) {
        this.empleadoView = empleadoView;
    }

    public SmsCiudad getCiudadView() {
        return ciudadView;
    }

    public void setCiudadView(SmsCiudad ciudadView) {
        this.ciudadView = ciudadView;
    }

    public SmsRol getRolView() {
        return rolView;
    }

    public void setRolView(SmsRol rolView) {
        this.rolView = rolView;
    }

    public SmsUsuario getAuxUsuarioView() {
        return auxUsuarioView;
    }

    public void setAuxUsuarioView(SmsUsuario auxUsuarioView) {
        this.auxUsuarioView = auxUsuarioView;
    }

    public SmsHojavida getHojavida() {
        return hojavida;
    }

    public void setHojavida(SmsHojavida hojavida) {
        this.hojavida = hojavida;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

    public HojaVida getHojaVida() {
        return hojaVida;
    }

    public void setHojaVida(HojaVida hojaVida) {
        this.hojaVida = hojaVida;
    }

    public boolean isRegistro() {
        return registro;
    }

    public void setRegistro(boolean registro) {
        this.registro = registro;
    }

    //Metodos que se comunican con el controlador    
    public void registrar() {
        usuarioView.setUsuarioRazonSocial("Planit SAS");
        usuarioView.setUsuarioNit("");
        empleado.registrarUsuario(usuarioView, ciudadView);
        empleado.registrarEmpleado(usuarioView, hojavida, empleadoView);
        auxUsuarioView = usuarioView;

        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        hojavida = new SmsHojavida();
        empleadoView = new SmsEmpleado();
    }

    public void registrarCuenta() {
        rolView.setRolNombre("Empleado");
        empleado.registrarDatosSesion(usuarioView, rolView);
        usuarioView = new SmsUsuario();
        rolView = new SmsRol();
    }

    public void modificar() {
    }

    public void eliminar() {
    }

    public void activar() {
        registro = false;
    }

    public void prueba(FileUploadEvent e) throws IOException {        
        archivo = e.getFile();
        String filePath = "c:/prueba/";
        byte[] bytes = null;
        if (null != archivo) {
            bytes = archivo.getContents();
            String filename = archivo.getFileName();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + filename)));
            stream.write(bytes);
            stream.close();
        }
    }

}
