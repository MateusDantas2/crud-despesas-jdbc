package br.com.codandosimples.dao;

import br.com.codandosimples.infra.ConnectionFactory;
import br.com.codandosimples.model.Categoria;
import br.com.codandosimples.model.Despesa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class DespesaDAO implements IDespesaDAO {

    @Override
    public Despesa save(Despesa despesa) {

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO Despesas (descricao, valor, data, categoria) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, despesa.getDescricao());
            preparedStatement.setDouble(2, despesa.getValor());
            preparedStatement.setDate(3, java.sql.Date.valueOf(despesa.getData()));
            preparedStatement.setString(4, despesa.getCategoria().toString());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            Long generatedId = resultSet.getLong("id");
            despesa.setId(generatedId);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return despesa;
    }

    @Override
    public Despesa update(Despesa despesa) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE Despesas SET descricao = ?, valor = ?, data = ?, categoria = ? WHERE id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, despesa.getDescricao());
            preparedStatement.setDouble(2, despesa.getValor());
            preparedStatement.setDate(3, java.sql.Date.valueOf(despesa.getData()));
            preparedStatement.setString(4, despesa.getCategoria().toString());
            preparedStatement.setLong(5, despesa.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return despesa;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Despesa> findAll() {
        String sql = "SELECT id, descricao, data, valor, categoria FROM Despesas";

        List<Despesa> despesas = new ArrayList<>();

        try(Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String description = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                double value = rs.getDouble("valor");
                Categoria category = Categoria.valueOf(rs.getString("categoria"));

                Despesa despesa = new Despesa(id, description, data, value, category);
                despesas.add(despesa);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return despesas;
    }

    @Override
    public Optional<Despesa> findById(Long id) {
        String sql = "SELECT id, descricao, data, valor, categoria FROM Despesas WHERE id = ?";

        Despesa despesa = null;
        try(Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long code = rs.getLong("id");
                String description = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                double value = rs.getDouble("valor");
                Categoria category = Categoria.valueOf(rs.getString("categoria"));

                despesa = new Despesa(code, description, data, value, category);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return Optional.ofNullable(despesa);
    }

    @Override
    public List<Despesa> findByCategoria(Categoria categoria) {
        String sql = "SELECT id, descricao, data, valor, categoria FROM Despesas WHERE categoria = ?";

        List<Despesa> despesas = new ArrayList<>();

        try(Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, categoria.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String description = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                double value = rs.getDouble("valor");
                Categoria category = Categoria.valueOf(rs.getString("categoria"));

                Despesa despesa = new Despesa(id, description, data, value, category);
                despesas.add(despesa);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return despesas;
    }
}
