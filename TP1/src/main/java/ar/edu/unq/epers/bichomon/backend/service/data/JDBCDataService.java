package ar.edu.unq.epers.bichomon.backend.service.data;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;

public class JDBCDataService implements DataService {

    @Override
    public void eliminarDatos() {


        PreparedStatement ps = conn.prepareStatement("INSERT INTO personaje (nombre, pesoMaximo, xp, vida) VALUES (?,?,?,?)");

    }

    @Override
    public void crearSetDatosIniciales() {

    }
}
