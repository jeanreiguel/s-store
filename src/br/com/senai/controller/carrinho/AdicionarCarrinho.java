package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;
import br.com.dao.DatabaseConnection;
import br.com.senai.controller.produto.DeletarProduto;
import br.com.senai.controller.produto.ListaProduto;

public class AdicionarCarrinho {

	private Connection connection;
	private Scanner dgt = new Scanner(System.in);
	private PreparedStatement preparedStatement;

	public AdicionarCarrinho() {
		connection = DatabaseConnection.getInstance().getConnection();
	}

	public void cadastrarItemCarrinho(int cliente) {

		ListaProduto ListaProduto = new ListaProduto();
		ProdutoModel produto = new ProdutoModel();
		DeletarProduto DeletarProduto = new DeletarProduto();
		ListaProduto.consultarProdutos();

		System.out.println("--- ADICIONAR ITEM NO CARRINHO ---");
		System.out.println("Informar o ID do produto: ");
		int idDoProduto = dgt.nextInt();

		if (!DeletarProduto.VerificaExistencia(idDoProduto)) {
			return;
		}
		System.out.println("Informe a quantidade desejada: ");
		produto.setQuantidadeDeProduto(dgt.nextInt());

		try {
			String sql = "SELECT nomeDoProduto, precoDoProduo, quantidade, saldoEmEstoque FROM produto WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			ResultSet resultset = preparedStatement.executeQuery();
			resultset.next();
			if (resultset.getInt("quantidade") < produto.getQuantidadeDeProduto()) {
				System.out.println("Quantidade em estoque insuficiente.");
				return;
			}
			produto.setNomeDoProduto(resultset.getString("nomeDoProduto"));
			produto.setPrecoDoProduto(resultset.getDouble("precoDoProduo"));
			produto.setSaldoEmEstoque(resultset.getDouble("saldoEmEstoque"));
			RetirarEstoque(idDoProduto, resultset.getInt("quantidade"), produto.getQuantidadeDeProduto(),
					produto.getPrecoDoProduto());

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			String sql = "INSERT INTO carrinho (CLIENTE, ID, PRODUTO, QUANTIDADE, PRECO_UNITARIO, TOTAL_ITEM)"
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cliente);
			preparedStatement.setInt(2, idDoProduto);
			preparedStatement.setString(3, produto.getNomeDoProduto());
			preparedStatement.setInt(4, produto.getQuantidadeDeProduto());
			preparedStatement.setDouble(5, produto.getPrecoDoProduto());
			preparedStatement.setDouble(6, produto.getQuantidadeDeProduto() * produto.getPrecoDoProduto());
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void RetirarEstoque(int id, int quantidade, int quantidadecarrinho, double preco) {

		try {
			int quantatual = quantidade - quantidadecarrinho;

			String sql = "UPDATE produto SET quantidade = ? WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, quantatual);
			preparedStatement.setInt(2, id);
			preparedStatement.execute();

			double saldoatual = preco * quantatual;

			sql = "UPDATE produto SET saldoEmEstoque = ? WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, saldoatual);
			preparedStatement.setInt(2, id);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
