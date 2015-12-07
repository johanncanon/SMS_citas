/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsAgenda;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public interface IAgendaDao {
    
    //Definicion de metodos CRUD
    public List<SmsAgenda> mostrarAgenda();
    public void registrarAgenda(SmsAgenda agenda);
    public void modificarAgenda(SmsAgenda agenda);
    public void eliminarAgenda(SmsAgenda agenda);
    
}
