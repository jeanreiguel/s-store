package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import br.com.dal.DatabaseConnection;
import br.com.senai.model.ProdutoModel;

	public class CadastrarProduto {
		Scanner dgt = new Scanner(System.in);
		private Connection connection;
		
		public CadastrarProduto() {
			connection = DatabaseConnection.getInstance().getConnection();
		}
		public ProdutoModel cadastrarProduto() {
			
			var produtoModel = new ProdutoModel();
			
			
			
			System.out.println("\n--- CADASTRAR ITENS ---\n");
			System.out.print("Produto: ");
			produtoModel.setNomeDoProduto(dgt.next());
			System.out.print("Preço: ");
			produtoModel.setPrecoDoProduto(dgt.nextDouble());
			System.out.print("Quantidade:");
			produtoModel.setQuantidadeDeProduto(dgt.nextInt());
			produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDeProduto() * produtoModel.getPrecoDoProduto());
			
			String sql = "INSERT INTO produto (nomeDoProduto, precoDoProduo, quantidade, saldoEmEstoque)"
					+ "VALUES(?, ?, ?, ?)";
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
}
