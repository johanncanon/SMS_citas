/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.SmsProveedor;

/**
 *
 * @author Desarrollo_Planit
 */
public class Proveedor extends Usuario{

    SmsProveedor proveedor;
    
    public Proveedor() {
        super();
    }

    public SmsProveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(SmsProveedor proveedor) {
        
        this.proveedor = proveedor;
    }
    
    
    
    
}
