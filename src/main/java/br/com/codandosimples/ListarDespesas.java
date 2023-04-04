package br.com.codandosimples;

import br.com.codandosimples.dao.DespesaDAO;
import br.com.codandosimples.infra.ConnectionFactory;
import br.com.codandosimples.model.Despesa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ListarDespesas {

    public static void main(String[] args) throws SQLException {

        Connection connection = ConnectionFactory.getConnection();
        DespesaDAO dao = new DespesaDAO(connection);
        List<Despesa> expenseCategory = dao.findAll();

        for (Despesa expense: expenseCategory) {
            System.out.println("ID => " + expense.getId());
            System.out.println("Date => " + expense.getData());
            System.out.println("Description => " + expense.getDescricao());
            System.out.println("Category => " + expense.getCategoria());
            System.out.println("Value => " + expense.getValor());

            System.out.println("=================================================");
        }
        connection.close();
    }
}