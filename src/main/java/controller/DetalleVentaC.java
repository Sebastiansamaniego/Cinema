/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DetalleVentaImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.DetalleVenta;

@Named(value = "detalleVentaC")  //ManagedBean
@SessionScoped
public class DetalleVentaC implements Serializable {

    private DetalleVenta modelo;
    private DetalleVenta modelodlg;
    private DetalleVentaImpl dao;
    private List<DetalleVenta> lista;

    public DetalleVentaC() {
        modelo = new DetalleVenta();
        modelodlg = new DetalleVenta();
        dao = new DetalleVentaImpl();
        lista = new ArrayList<>();
    }

    @PostConstruct
    public void init() {

    }

    public void registrar() {

        try {
            dao.registrar(modelo);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
            listar();
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Registro fallido"));
            System.out.println("error en registroC" + e.getMessage());
        }
    }
        public void eliminar(DetalleVenta detven) {
        try {
            dao.eliminar(detven);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminado", "Eliminado con éxiito"));
            listar();
            limpiar();
        } catch (Exception e) {
            System.out.println("error en eliminarC" + e.getMessage());
        }
    }

    public void modificar() {
        try {
            dao.modificar(modelodlg);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Modificación exitosa"));
            listar();
            limpiar();

        } catch (Exception e) {
            System.out.println("Error en modificarC" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listar() {
        try {
            lista = dao.listar();
        } catch (Exception e) {
            System.out.println("error en listarC" + e.getMessage());
        }
    }

    public void limpiar() {
        modelo = new DetalleVenta();
    }
    
    public void calcular(){
        try {
            Float cantidad = new Float(modelo.getENTDETVEN());
            int horario = modelo.getHORDETVEN();
            int edad = modelo.getEDAD();
            System.out.println("imprime"+horario+edad+cantidad);
            modelo.setPREDETVEN(8*cantidad);
            System.out.println("precio "+modelo.getPREDETVEN());
//            if((horario == 1 || horario ==3 || horario ==4 || horario ==5)&& edad == 1){
//                modelo.setPREDETVEN(4.0f*cantidad);
//                System.out.println("imprime"+modelo.getPREDETVEN());
//            }
//            if((horario == 1 || horario ==3 || horario ==4 || horario ==5)&& edad == 2){
//                modelo.setPREDETVEN(8.0f*cantidad);
//                System.out.println("imprime"+modelo.getPREDETVEN());
//            }
//            if((horario == 1 || horario ==3 || horario ==4 || horario ==5)&& edad == 3){
//                modelo.setPREDETVEN(6.0f*cantidad);
//                System.out.println("imprime"+modelo.getPREDETVEN());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DetalleVenta getModelo() {
        return modelo;
    }

    public void setModelo(DetalleVenta modelo) {
        this.modelo = modelo;
    }

    public DetalleVenta getModelodlg() {
        return modelodlg;
    }

    public void setModelodlg(DetalleVenta modelodlg) {
        this.modelodlg = modelodlg;
    }

    public List<DetalleVenta> getLista() {
        return lista;
    }

    public void setLista(List<DetalleVenta> lista) {
        this.lista = lista;
    }

}
