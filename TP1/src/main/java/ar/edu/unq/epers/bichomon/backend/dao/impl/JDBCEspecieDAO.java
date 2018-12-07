package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JDBCEspecieDAO implements EspecieDAO {

    private JDBCConector connector = new JDBCConector();


    @Override
    public void guardar(Especie especie) {

        this.connector.executeWithConnection(conn -> {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO especie (nombre,altura,peso,vida,cantidad,tipoBicho) VALUES (?,?,?,?,?,?)");
            ps.setString(1, especie.getNombre());
            ps.setInt(2,especie.getAltura());
            ps.setInt(3,especie.getPeso());
            ps.setInt(4,especie.getEnergiaInicial());
            ps.setInt(5,especie.getCantidadBichos());
            ps.setString(6,especie.getTipo().name());

            ps.execute();

            if (ps.getUpdateCount() != 1) {
                throw new RuntimeException("No se inserto la especie D=  " + especie);
            }
            ps.close();

            return null;
        });
    }

    @Override
    public void actualizar(Especie especie) {

        if(this.estaEnLaBaseDeDatos(especie)){

            //Si esta lo actualiza
            this.connector.executeWithConnection(conn -> {
                PreparedStatement ps = conn.prepareStatement("UPDATE especie " +
                                                                  "SET nombre = ?, altura = ?, peso = ?, vida = ?, cantidad = ?, tipoBicho = ? " +
                                                                  "WHERE id = ?; ");
                ps.setString (1,especie.getNombre());
                ps.setInt(2, especie.getAltura());
                ps.setInt(3,especie.getPeso());
                ps.setInt(4,especie.getEnergiaInicial());
                ps.setInt(5,especie.getCantidadBichos());
                ps.setString(6,especie.getTipo().name());
                ps.setInt(7,especie.getId());

                ps.executeQuery();
                ps.close();
                return null;
            });
        }else{
            //Si no esta lo agrega
            this.guardar(especie);
        }
    }

    private boolean estaEnLaBaseDeDatos(Especie especie) {
        boolean resultado;
        resultado = this.connector.executeWithConnection(conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT id FROM especie where id = ?");
            ps.setInt(1,especie.getId());

            ResultSet rs = ps.executeQuery();

            rs.next();
            ps.close();

            return (especie.getId() == rs.getInt(1));
        });

    return resultado;

    }

    @Override
    public Especie recuperar(String nombreEspecie) {
        return this.connector.executeWithConnection (conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT id,nombre,altura,peso,vida,cantidad,tipoBicho from especie WHERE nombre = ?");
            ps.setString(1,nombreEspecie);
            ResultSet resultSet = ps.executeQuery();

            Especie especie = null;
            while (resultSet.next()){
                if (especie != null ){
                    throw new RuntimeException("Existe mas de una especie con el nombre "+ nombreEspecie);
                }
                especie = new Especie(resultSet.getInt("id"),resultSet.getString("nombre"), TipoBicho.valueOf(resultSet.getString("tipoBicho")));
                especie.setAltura(resultSet.getInt("altura"));
                especie.setCantidadBichos(resultSet.getInt("cantidad"));
                especie.setPeso(resultSet.getInt("peso"));
                especie.setEnergiaIncial(resultSet.getInt("vida"));
            }
            ps.close();
            return especie;
        });

    }


    @Override
    public List<Especie> recuperarTodos() {

        ArrayList<Especie> listaEspecie = new ArrayList<Especie>();
        this.connector.executeWithConnection (conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT * from especie");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                listaEspecie.add(this.recuperar(resultSet.getString("nombre")));
            }

        ps.close();
        return null;
        });
        return listaEspecie;
    }

    public void eliminarDatos() {

        this.connector.executeWithConnection(conn -> {
            PreparedStatement ps = conn.prepareStatement("TRUNCATE TABLE especie");

            ps.execute();
            ps.close();

            return null;
        });
    }


    public void guardarInicial(String nombre,int altura, int peso, int vida, int cantidad, String tipoBicho) {

        this.connector.executeWithConnection(conn -> {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO especie (nombre,altura,peso,vida,cantidad,tipoBicho) VALUES (?,?,?,?,?,?)");
            ps.setString (1,nombre);
            ps.setInt(2, altura);
            ps.setInt(3,peso);
            ps.setInt(4,vida);
            ps.setInt(5,cantidad);
            ps.setString(6,(tipoBicho));

            ps.execute();
            ps.close();

            return null;
        });
    }
    public int idPrimerRegistro(){

        int valorPrimerRegistro = connector.executeWithConnection(conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM especie");

            ResultSet rs = ps.executeQuery();

            rs.next();
            ps.close();

            return rs.getInt(1);
        });

        return valorPrimerRegistro;

    }
}