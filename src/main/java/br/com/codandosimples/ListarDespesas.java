package br.com.codandosimples;

import br.com.codandosimples.dao.DespesaDAO;
import br.com.codandosimples.model.Despesa;

import java.util.List;

import static br.com.codandosimples.model.Categoria.TRANSPORTE;

public class ListarDespesas {

    public static void main(String[] args) {

        DespesaDAO dao = new DespesaDAO();
//        List<Despesa> despesas = dao.findAll();
//
//        for (Despesa despesa: despesas) {
//            System.out.println("ID: " + despesa.getId());
//            System.out.println("Description: " + despesa.getDescricao());
//            System.out.println("Value: " + despesa.getValor());
//
//            System.out.println("=================================================");
//        }

//        Optional<Despesa> expenseOptional = dao.findById(10L);
//        expenseOptional.ifPresent(despesa -> {
//            System.out.println("ID: " + despesa.getId());
//            System.out.println("Description: " + despesa.getDescricao());
//            System.out.println("Value: " + despesa.getValor());
//        });

        List<Despesa> expenseCategory = dao.findByCategoria(TRANSPORTE);

        for (Despesa despesa: expenseCategory) {
            System.out.println("ID: " + despesa.getId());
            System.out.println("Description: " + despesa.getDescricao());
            System.out.println("Category: " + despesa.getCategoria());
            System.out.println("Value: " + despesa.getValor());

            System.out.println("=================================================");
        }
    }
}