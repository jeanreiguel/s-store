package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DatabaseConnection;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class ExcluirCarrinho {

	private Connection connection;
	private Scanner dgt = new Scanner(System.in);
	private PreparedStatement preparedStatement;

	public ExcluirCarrinho() {
		connection = DatabaseConnection.getInstance().getConnection();
	}

	public void excluirItensCarrinho(int cliente) {

		ListaCarrinho carrinho = new ListaCarrinho();
		carrinho.listarItensNoCarrinho(cliente);

		System.out.println("--- EXCLUIR ITEM NO CARRINHO ---");
		System.out.println("Informar o ID do produto: ");
		int idDoProduto = dgt.nextInt();

		if (!VerificaCarrinho(cliente, idDoProduto)) {
				System.out.println("Não há esse produto no carrinho");
			return;
		}
		try {
			String sql = "SELECT * FROM carrinho WHERE CLIENTE = ? && ID = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cliente);
			preparedStatement.setInt(2, idDoProduto);
			ResultSet resultset = preparedStatement.executeQuery();
			resultset.next();
			int quantretorna = (resultset.getInt("QUANTIDADE"));
			RetornarEstoque(quantretorna, idDoProduto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			String sql = "DELETE FROM carrinho WHERE CLIENTE = ? && ID = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cliente);
			preparedStatement.setInt(2, idDoProduto);
			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean VerificaCarrinho(int cliente, int id) {
		ProdutoModel carrinho = new ProdutoModel();
		try {
			String sql = "SELECT * FROM carrinho WHERE CLIENTE = ? && ID = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cliente);
			preparedStatement.setInt(2, id);
			ResultSet resultset = preparedStatement.executeQuery();
			
			if (!resultset.next()) {
				return false;
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void RetornarEstoque(int retorna, int idDoProduto) {

		ProdutoModel produto = new ProdutoModel();
		try {
			String sql = "SELECT * FROM produto WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			ResultSet resultset = preparedStatement.executeQuery();

			if (!resultset.next()) {
				System.out.println("Esse produto não existe");
				return;
			}
			produto.setPrecoDoProduto(resultset.getDouble("precoDoProduo"));
			produto.setQuantidadeDeProduto(resultset.getInt("quantidade"));
			produto.setSaldoEmEstoque(resultset.getDouble("saldoEmEstoque"));

			int quantestoque = produto.getQuantidadeDeProduto() + retorna;
			double saldoestoque = produto.getPrecoDoProduto() * quantestoque;

			sql = "UPDATE produto SET quantidade = ?, saldoEmEstoque = ? WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, quantestoque);
			preparedStatement.setDouble(2, saldoestoque);
			preparedStatement.setInt(3, idDoProduto);
			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void AlterarQuantidade(int cliente) {
		ListaCarrinho carrinho = new ListaCarrinho();
		
		carrinho.listarItensNoCarrinho(cliente);
		
		
		System.out.println("--- ALTERAR QUANTIDADE ---");
		System.out.println("Informe o ID do produto: ");
		int id= dgt.nextInt();
		System.out.println("Informe a quantidade que deseja: ");
		int quantidade = dgt.nextInt();
		
		try {
			String sql = "SELECT * FROM carrinho WHERE CLIENTE = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cliente);
			ResultSet resultset = preparedStatement.executeQuery();
			resultset.next();
			double preco = resultset.getDouble("PRECO_UNITARIO");
			double saldo = preco*quantidade;
			int retorna = (resultset.getInt("quantidade") - quantidade);
			
			sql = "UPDATE carrinho SET QUANTIDADE = ?, TOTAL_ITEM = ? WHERE CLIENTE = ? && ID = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, quantidade);
			preparedStatement.setDouble(2, saldo);
			preparedStatement.setInt(3, cliente);
			preparedStatement.setInt(4, id);
			preparedStatement.execute();
			RetornarEstoque(retorna, id);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
