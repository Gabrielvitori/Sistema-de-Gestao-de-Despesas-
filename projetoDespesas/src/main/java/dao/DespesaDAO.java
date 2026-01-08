package dao;

import connection.ConnectionFactory;
import model.Categoria;
import model.CategoriaSoma;
import model.Despesas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAO {

    private Connection connection;

    public DespesaDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void save(Despesas despesa) {
        String sql = "INSERT INTO despesas (descricao, valor, data, categoria_id) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, despesa.getDescricao());
            stmt.setDouble(2, despesa.getValor());


            stmt.setDate(3, Date.valueOf(despesa.getData()));

            stmt.setInt(4, despesa.getCategoria().getId());
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Despesas> findAll() {
        String sql = "SELECT d.id, d.descricao, d.data, d.valor, c.id as cat_id, c.nome as cat_nome " +
                "FROM despesas d " +
                "JOIN categorias c ON d.categoria_id = c.id";

        List<Despesas> lista = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setId(rs.getInt("cat_id"));
                cat.setName(rs.getString("cat_nome"));

                Despesas despesa = new Despesas();
                despesa.setId(rs.getInt("id"));
                despesa.setDescricao(rs.getString("descricao"));
                despesa.setValor(rs.getDouble("valor"));

                java.sql.Date dataSql = rs.getDate("data");
                despesa.setData(dataSql.toLocalDate());

                despesa.setCategoria(cat);

                lista.add(despesa);
            }

            stmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public void limparTudo() {
        String sql = "TRUNCATE TABLE despesas";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CategoriaSoma> buscarTotalPorCategoria() {
        String sql = "SELECT c.nome, SUM(d.valor) as total " +
                "FROM despesas d " +
                "JOIN categorias c ON d.categoria_id = c.id " +
                "GROUP BY c.nome";
        List<CategoriaSoma> relatorio = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                Double total = rs.getDouble("total");
                CategoriaSoma item = new CategoriaSoma(nome, total);
                relatorio.add(item);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return relatorio;
    }

}
