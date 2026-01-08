package dao;

import connection.ConnectionFactory;
import model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private Connection connection;

    public CategoriaDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public void add(Categoria categoria){

        String sql = "INSERT INTO categorias (nome) VALUES (?)";

        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, categoria.getName());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Categoria> listAll() {

        String sql = "SELECT * FROM categorias";
        List<Categoria> categorias = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setName(rs.getString("nome"));
                categorias.add(categoria);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categorias;
    }
}
