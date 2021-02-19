package model;

import java.util.Date;
import lombok.Data;

@Data
public class DetalleVenta {

    private int IDDETVEN;
    private Date FECDETVEN;
    private int ENTDETVEN;
    private int PELDETVEN;
    private int HORDETVEN;
    private Float PREDETVEN;
    private int COMDETVEN;
    private int EDAD;
    private Cliente cliente = new Cliente();
}
