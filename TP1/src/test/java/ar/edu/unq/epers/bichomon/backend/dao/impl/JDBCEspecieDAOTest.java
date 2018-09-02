package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.service.data.JDBCDataService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class JDBCEspecieDAOTest {

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
    public void alGuardarUnaEspecieLaBaseDeDatosElIdDeEspecieEsElCorrecto(){

        Especie especieEsperada = new Especie(9,"pikachu", TipoBicho.valueOf("AIRE"));

        daoEspecie.guardar(especieEsperada);

        assertEquals(this.daoEspecie.idPrimerRegistro() , 9);
    }

    @Test
    public void alRecuperarLaEspecieObtengoUnaEspecieIgualALaGuardada(){

        Especie especieEsperada = new Especie(1,"pikachu", TipoBicho.valueOf("AIRE"));

        daoEspecie.guardar(especieEsperada);
        Especie especie = daoEspecie.recuperar("pikachu");

        assertEquals(especieEsperada.getNombre(),especie.getNombre());
        assertEquals(especieEsperada.getTipo(),especie.getTipo());
    }

    @Test
    public void verificoQueSeCambieElNombreDeLaEspecie(){

        Especie especieEsperada = new Especie(9,"pikachu", TipoBicho.valueOf("AIRE"));

        daoEspecie.guardar(especieEsperada);

        especieEsperada.setNombre("raichu");

        daoEspecie.actualizar(especieEsperada);

        Especie especieRecibida = daoEspecie.recuperar("raichu");

        assertEquals("raichu",especieRecibida.getNombre());

    }



}
