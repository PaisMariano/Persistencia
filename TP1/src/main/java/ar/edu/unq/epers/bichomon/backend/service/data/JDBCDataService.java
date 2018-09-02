package ar.edu.unq.epers.bichomon.backend.service.data;

import ar.edu.unq.epers.bichomon.backend.dao.impl.JDBCEspecieDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCDataService implements DataService {

    private JDBCEspecieDAO daoEspecie = new JDBCEspecieDAO();

    @Override
    public void eliminarDatos() {

        this.daoEspecie.eliminarDatos();
    }


    @Override
    public void crearSetDatosIniciales() {

        this.daoEspecie.guardarInicial("AMARILLOMON", 170, 69, 100, 0, "ELECTRICIDAD");
        this.daoEspecie.guardarInicial("VANPIRON", 1050, 99, 100, 0, "AIRE");
        this.daoEspecie.guardarInicial("ROJOMON", 180, 75, 100, 0, "FUEGO");
        this.daoEspecie.guardarInicial("DIENTEMON", 1050, 99, 100, 0, "AGUA");
        this.daoEspecie.guardarInicial("TIERRAMON", 1050, 99, 100, 0, "TIERRA");
        this.daoEspecie.guardarInicial("VERDEMON", 150, 55, 100, 0, "PLANTA");
        this.daoEspecie.guardarInicial("FORTMON", 1050, 99, 100, 0, "CHOCOLATE");
        this.daoEspecie.guardarInicial("FANTASMON", 1050, 99, 100, 0, "AIRE");

    }
}
