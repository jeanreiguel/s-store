package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DatabaseConnection;
import br.com.senai.model.ProdutoModel;

public class CadastrarProduto {
	Scanner dgt = new Scanner(System.in);
	private Connection connection;
	private PreparedStatement preparedStatement;

	public CadastrarProduto() {
		connection = DatabaseConnection.getInstance().getConnection();
	}

	public ProdutoModel cadastrarProduto() {

		var produtoModel = new ProdutoModel();

		System.out.println("\n--- CADASTRAR ITENS ---\n");
		System.out.print("Produto: ");
		produtoModel.setNomeDoProduto(dgt.next());
		System.out.print("Quantidade:");
		produtoModel.setQuantidadeDeProduto(dgt.nextInt());
		System.out.print("Preço: ");
		produtoModel.setPrecoDoProduto(dgt.nextDouble());

		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDeProduto() * produtoModel.getPrecoDoProduto());

		if (!VerificaCadastro(produtoModel)) {
			System.out.println("Produto já existente, quantidade e valor atualizado");
			return produtoModel;
		}
		String sql = "INSERT INTO produto (nomeDoProduto, precoDoProduo, quantidade, saldoEmEstoque)"
				+ "VALUES(?, ?, ?, ?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, produtoModel.getNomeDoProduto());
			preparedStatement.setDouble(2, produtoModel.getPrecoDoProduto());
			preparedStatement.setInt(3, produtoModel.getQuantidadeDeProduto());
			preparedStatement.setDouble(4, produtoModel.getSaldoEmEstoque());

			preparedStatement.execute();

		} catch (Exception e) {
			System.out.println("Erro ao cadastrar os dados. Contate o suporte.");
		}
		return produtoModel;
	}

	public boolean VerificaCadastro(ProdutoModel produtoModel) {
		try {
			String sql = "SELECT * FROM produto";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultset = preparedStatement.executeQuery();
			if (!resultset.next()) {
				return true;
			}
			resultset.previous();
			while (resultset.next()) {
				if (produtoModel.getNomeDoProduto().equals(resultset.getString("nomeDoProduto"))) {
					produtoModel.setQuantidadeDeProduto(
							resultset.getInt("quantidade") + produtoModel.getQuantidadeDeProduto());
					produtoModel
							.setPrecoDoProduto(produtoModel.getPrecoDoProduto());
					produtoModel.setSaldoEmEstoque(
							produtoModel.getPrecoDoProduto() * produtoModel.getQuantidadeDeProduto());

					sql = "UPDATE produto SET quantidade = ?, precoDoProduo = ?, SaldoEmEstoque = ? WHERE nomeDoProduto = ?";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, produtoModel.getQuantidadeDeProduto());
					preparedStatement.setDouble(2, produtoModel.getPrecoDoProduto());
					preparedStatement.setDouble(3, produtoModel.getSaldoEmEstoque());
					preparedStatement.setString(4, produtoModel.getNomeDoProduto());
					preparedStatement.execute();
					return false;
				}
			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
