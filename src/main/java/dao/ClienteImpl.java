/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteImpl extends Conexion implements ICRUD<Cliente> {

    @Override
    public void registrar(Cliente cli) throws Exception {
        String sql = "INSERT INTO CLIENTE "
                + "(NOMCLI,APECLI,EDAD) VALUES(?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, cli.getNOMCLI());
            ps.setString(2, cli.getAPECLI());
            ps.setInt(3, cli.getEDAD());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void modificar(Cliente cli) throws Exception {
        String sql = "UPDATE CLIENTE SET "
                + "NOMCLI = ?,APECLI = ?,EDAD = ? WHERE IDCLI = ?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, cli.getNOMCLI());
            ps.setString(2, cli.getAPECLI());
            ps.setInt(3, cli.getEDAD());
            ps.setInt(4, cli.getIDCLI());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void modificarEst(Cliente arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Cliente cli) throws Exception {
        String sql = "DELETE FROM CLIENTE WHERE IDCLI = ?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, cli.getIDCLI());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public List<Cliente> listar() throws Exception {
        List<Cliente> listado = new ArrayList<>();
        Cliente cli;
        String sql = "SELECT * FROM CLIENTE ORDER BY IDCLI DESC";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cli = new Cliente();
                cli.setIDCLI(rs.getInt("IDCLI"));
                cli.setNOMCLI(rs.getString("NOMCLI"));
                cli.setAPECLI(rs.getString("APECLI"));
                cli.setEDAD(rs.getInt("EDAD"));
                listado.add(cli);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en el listadoCli" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return listado;
    }

}
