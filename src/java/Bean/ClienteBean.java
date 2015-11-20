/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Cliente;
import Modelo.SmsCiudad;
import Modelo.SmsRol;
import Modelo.SmsUsuario;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class ClienteBean implements Serializable {

    //Objetos necesario para vista
    protected SmsUsuario clienteView;
    protected SmsUsuario auxClienteview;
    protected List<SmsUsuario> clientes;
    protected SmsCiudad ciudadView;
    protected SmsRol rolView;

    //Control de componentes
    protected boolean habilitado;

    //Relacion con el controlador
    protected Cliente cliente;

    public ClienteBean() {
        clienteView = new SmsUsuario();
        auxClienteview = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        habilitado = true;
        cliente = new Cliente();
    }

    //Getters & Setters
    public SmsUsuario getClienteView() {
        return clienteView;
    }

    public void setClienteView(SmsUsuario clienteView) {
        this.clienteView = clienteView;
    }

    public List<SmsUsuario> getClientes() {
        return clientes;
    }

    public void setClientes(List<SmsUsuario> clientes) {
        this.clientes = clientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public SmsUsuario getAuxClienteview() {
        return auxClienteview;
    }

    public void setAuxClienteview(SmsUsuario auxClienteview) {
        this.auxClienteview = auxClienteview;
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

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    //Metodos     
    public void registrar() {
        rolView.setRolNombre("Cliente");
        cliente.registrarUsuario(clienteView, ciudadView, rolView);
        
        clienteView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        habilitado = false;
    }    

    public void modificar() {
        cliente.modificarUsuario(clienteView, ciudadView);
        clienteView = new SmsUsuario();
    }

    public void eliminar() {
        cliente.eliminarUsuario(clienteView);
        clienteView = new SmsUsuario();
    }

}
