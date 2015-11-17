/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsReferencia;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public interface IReferenciaDao {
    
    public List<SmsReferencia> mostrarReferencias();
    public void registrarReferencia(SmsReferencia referencia);
    public void modificarReferencia(SmsReferencia referencia);
    public void eliminarReferencia(SmsReferencia referencia);
    
}
