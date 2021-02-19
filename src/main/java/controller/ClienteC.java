/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClienteImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Cliente;

@Named(value = "clienteC")  //ManagedBean
@SessionScoped
public class ClienteC implements Serializable {

    private ClienteImpl dao;
    private Cliente model;
    private Cliente modelodlg;
    private List<Cliente> lista;

    public ClienteC() {
        dao = new ClienteImpl();
        model = new Cliente();
        lista = new ArrayList<>();
        modelodlg = new Cliente();
    }

    @PostConstruct
    public void init() {
        listar();
    }

    public void registrar() {

        try {
            dao.registrar(model);
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

    public void eliminar(Cliente cli) {
        try {
            dao.eliminar(cli);
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
        model = new Cliente();
    }

    public Cliente getModel() {
        return model;
    }

    public void setModel(Cliente model) {
        this.model = model;
    }

    public Cliente getModelodlg() {
        return modelodlg;
    }

    public void setModelodlg(Cliente modelodlg) {
        this.modelodlg = modelodlg;
    }

    public List<Cliente> getLista() {
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }

}
