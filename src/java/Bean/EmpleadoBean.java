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
import java.io.IOException;
import java.io.Serializable;
import org.apache.commons.io.IOUtils;
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
    private UploadedFile archivo;

    //Relacion con el controlador
    protected Empleado empleado;
    protected HojaVida hojaVida;
    protected Archivos file;

    //Controles de componentes
    private boolean registro;
    private boolean hoja;
    private boolean nuevo;

    public EmpleadoBean() {
        usuarioView = new SmsUsuario();
        auxUsuarioView = new SmsUsuario();
        empleadoView = new SmsEmpleado();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        hojavida = new SmsHojavida();
        hojaVida = new HojaVida();
        file = new Archivos();
        empleado = new Empleado();

        registro = false;
        hoja = true;
        nuevo = true;
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

    public Archivos getFile() {
        return file;
    }

    public void setFile(Archivos file) {
        this.file = file;
    }

    public boolean isRegistro() {
        return registro;
    }

    public void setRegistro(boolean registro) {
        this.registro = registro;
    }

    public boolean isHoja() {
        return hoja;
    }

    public void setHoja(boolean hoja) {
        this.hoja = hoja;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    //Metodos que se comunican con el controlador    
    public void registrar() {

        usuarioView.setUsuarioRazonSocial("SMS Renta");
        usuarioView.setUsuarioNit("");
        rolView.setRolNombre("Empleado");
        empleado.registrarUsuario(usuarioView, ciudadView, rolView);

        auxUsuarioView = usuarioView;       

        registro = true;
        hoja = false;
        nuevo = false;
    }

    public void modificar() {
    }

    public void eliminar() {
    }

    public void registrarHojaVida() throws IOException {

        if (null != getArchivo()) {
            file.UploadFile(IOUtils.toByteArray(getArchivo().getInputstream()), getArchivo().getFileName());
        }

        hojavida.setHojaVidaNombre(getArchivo().getFileName());
        hojaVida.registrarHojaVida(hojavida);
        empleado.registrarEmpleado(auxUsuarioView, hojavida);

        hojavida = new SmsHojavida();
        auxUsuarioView = new SmsUsuario();
        empleadoView = new SmsEmpleado();
        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        
        registro = false;
        hoja = true;
        nuevo = true;

    }

    //Otros
    public void registraNuevo() {
        registro = false;
        hoja = true;
        nuevo = true;
        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
    }

}
