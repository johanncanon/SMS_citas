    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Categoria;
import Modelo.SmsCategoria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

public class CategoriaBean implements Serializable {

    //Objeto de vista
    private SmsCategoria categoriaView;
    private List<SmsCategoria> categoriasListView;
    private List<String> nombresCategoriasListView;

    //Relacion con el controlador
    private Categoria categoriaController;

    //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;
    private String buscar;

    public CategoriaBean() {//CONSTRUCTOR
        categoriaView = new SmsCategoria();
        categoriasListView = new ArrayList<>();
        nombresCategoriasListView = new ArrayList<>();
        categoriaController = new Categoria();

        buscar = "";
        estado = 0;
        nombre = "Registrar Categoria";
    }

    @PostConstruct
    public void init() {
        categoriasListView = categoriaController.cargarCategorias();
    }

    //Getters & Setters
    public SmsCategoria getCategoriaView() {
        return categoriaView;
    }

    public void setCategoriaView(SmsCategoria categoriaView) {
        this.categoriaView = categoriaView;
    }

    public List<SmsCategoria> getCategoriasListView() {
        return categoriasListView;
    }

    public void setCategoriasListView(List<SmsCategoria> categoriasListView) {
        this.categoriasListView = categoriasListView;
    }

    public List<String> getNombresCategoriasListView() {
        nombresCategoriasListView = new ArrayList<>();
        categoriasListView = categoriaController.cargarCategorias();
        for (int i = 0; i < categoriasListView.size(); i++) {
            nombresCategoriasListView.add(categoriasListView.get(i).getCategoriaNombre());
        }
        return nombresCategoriasListView;
    }

    public void setNombresCategoriasListView(List<String> nombresCategoriasListView) {
        this.nombresCategoriasListView = nombresCategoriasListView;
    }

    public Categoria getCategoriaController() {
        return categoriaController;
    }

    public void setCategoriaController(Categoria categoriaController) {
        this.categoriaController = categoriaController;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    //METODOS QUE DEVUELVEN DATOS PARA VISTAS
    public void modificar() {
        //TRAER LA INFORMACION DE LA VISTA Y PASARLA AL PARAMETRO CORRESPODIENTE 
        //DE LA CLASE DEL PAQUETE CONTROLADOR
        categoriaController.modificarCategoria(categoriaView);
        categoriaView = new SmsCategoria();
        categoriasListView = categoriaController.cargarCategorias();

    }

    public void registrar() {
        //TRAER LA INFORMACION DE LA VISTA Y PASARLA AL PARAMETRO CORRESPODIENTE 
        //DE LA CLASE DEL PAQUETE CONTROLADOR
        categoriaController.registrarCategoria(categoriaView);
        categoriaView = new SmsCategoria();
        categoriasListView = categoriaController.cargarCategorias();

    }

    public void eliminar() {
        //TRAER LA INFORMACION DE LA VISTA Y PASARLA AL PARAMETRO CORRESPODIENTE 
        //DE LA CLASE DEL PAQUETE CONTROLADOR
        categoriaController.eliminarCategoria(categoriaView);
        categoriaView = new SmsCategoria();
        categoriasListView = categoriaController.cargarCategorias();
    }

    public void filtrar() {
        categoriasListView = new ArrayList<>();
        if (buscar.equals("")) {
            categoriasListView = categoriaController.cargarCategorias();
        } else {
            categoriasListView = categoriaController.filtrarCategoria(buscar);
        }
    }

    //Metodos Propios
    public void metodo() {
        if (estado == 0) {
            registrar();
        } else if (estado == 1) {
            modificar();
            estado = 0;
            nombre = "Registrar Categoria";
        } else if (estado == 2) {
            eliminar();
            estado = 0;
            nombre = "Registrar Categoria";
        }
    }

    public void seleccionarCRUD(int i) {
        estado = i;
        if (estado == 1) {
            nombre = "Modificar Categoria";
        } else if (estado == 2) {
            nombre = "Eliminar Categoria";
        }
    }

}
