package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.DetalleVenta;

public class DetalleVentaImpl extends Conexion implements ICRUD<DetalleVenta> {

    @Override
    public void registrar(DetalleVenta detven) throws Exception {
        String sql = "INSERT INTO detalle_venta (\n"
                + "    fecdetven,\n"
                + "    entdetven,\n"
                + "    peldetven,\n"
                + "    hordetven,\n"
                + "    diadetven,\n"
                + "    comdetven,\n"
                + "    idcli\n"
                + ") VALUES (\n"
                + "    (select sysdate from dual),\n"
                + "    ?,\n"
                + "    ?,\n"
                + "    ?,\n"
                + "    ?,\n"
                + "    ?,\n"
                + "    ?\n"
                + ");";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, detven.getENTDETVEN());
            ps.setInt(2, detven.getPELDETVEN());
            ps.setInt(3, detven.getHORDETVEN());
            ps.setFloat(4, detven.getPREDETVEN());
            ps.setInt(5, detven.getCOMDETVEN());
            ps.setInt(6, detven.getCliente().getIDCLI());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void modificar(DetalleVenta detven) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarEst(DetalleVenta arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(DetalleVenta detven) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DetalleVenta> listar() throws Exception {
        List<DetalleVenta> listado = new ArrayList<>();
        DetalleVenta detven;
        String sql = "SELECT * FROM DETALLE_VENTA d INNER JOIN CLIENTE c ON d.IDCLI = c.IDCLI ORDER BY IDDETVEN DESC";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                detven = new DetalleVenta();
                detven.setIDDETVEN(rs.getInt("IDDETVEN"));
                detven.setFECDETVEN(rs.getDate("fecdetven"));
                detven.setENTDETVEN(rs.getInt("entdetven"));
                detven.setPELDETVEN(rs.getInt("peldetven"));
                detven.setHORDETVEN(rs.getInt("hordetven"));
                detven.setPREDETVEN(rs.getFloat("predetven"));
                detven.setCOMDETVEN(rs.getInt("comdetven"));
                Cliente cli = new Cliente();
                cli.setIDCLI(rs.getInt("IDCLI"));
                cli.setNOMCLI(rs.getString("NOMCLI"));
                cli.setAPECLI(rs.getString("APECLI"));
                cli.setEDAD(rs.getInt("EDAD"));
                detven.setCliente(cli);
                listado.add(detven);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en el listadoDetVen" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return listado;
    }

}
