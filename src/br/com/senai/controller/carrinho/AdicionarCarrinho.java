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
		ExcluirCarrinho ExcluirCarrinho = new ExcluirCarrinho();
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
			if (ExcluirCarrinho.VerificaCarrinho(cliente, idDoProduto)) {
				try {
					sql = "SELECT * FROM carrinho WHERE ID = ?";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, idDoProduto);
					resultset = preparedStatement.executeQuery();
					resultset.next();
					
					int quantidadeatual = resultset.getInt("QUANTIDADE") + produto.getQuantidadeDeProduto();
					double saldoatual = quantidadeatual * resultset.getDouble("PRECO_UNITARIO");
					sql = "UPDATE carrinho SET QUANTIDADE = ?, TOTAL_ITEM = ? WHERE CLIENTE = ? && ID = ?";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, quantidadeatual);
					preparedStatement.setDouble(2, saldoatual);
					preparedStatement.setInt(3, cliente);
					preparedStatement.setInt(4, idDoProduto);
					preparedStatement.execute();
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
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
