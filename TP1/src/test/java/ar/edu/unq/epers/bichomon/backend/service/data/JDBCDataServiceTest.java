package ar.edu.unq.epers.bichomon.backend.service.data;

import ar.edu.unq.epers.bichomon.backend.dao.impl.JDBCEspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.especie.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class JDBCDataServiceTest {

    private JDBCDataService dao = new JDBCDataService();
    private JDBCEspecieDAO daoEspecie = new JDBCEspecieDAO();

    @Before
    public void crearModelo() {

        dao.eliminarDatos();
        dao.crearSetDatosIniciales();
    }

    @After
    public void borrarModelo(){

        dao.eliminarDatos();

    }

    @Test
    public void alEliminarDatosVerificoQueRealmenteEsteVaciaLaBaseDeEspecies() {

        dao.eliminarDatos();
        assertEquals(this.daoEspecie.idPrimerRegistro(), 0);
    }

    @Test
    public void verificoQueElIdDelPrimerRegistroSeaOcho(){

        assertEquals(this.daoEspecie.idPrimerRegistro(), 8);

    }

}
