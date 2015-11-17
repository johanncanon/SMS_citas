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
public class ClienteBean implements Serializable{

    //Objetos necesario para vista
    protected SmsUsuario clienteView;
    protected List<SmsUsuario> clientes;
    
    protected SmsCiudad ciudadView;
    protected SmsRol rolView;
    
    
    //Relacion con el controlador
    protected Cliente cliente;
    
    public ClienteBean() {
        clienteView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
                
    }

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

    
    
    
    
    
    

   
    
    
    
}
