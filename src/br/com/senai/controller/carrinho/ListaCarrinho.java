package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dal.DatabaseConnection;

public class ListaCarrinho {
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	public ListaCarrinho() {
		connection = DatabaseConnection.getInstance().getConnection();
	}
	public ResultSet listarItensNoCarrinho(int cliente) {
	
		try {
			String sql = "SELECT * FROM carrinho WHERE CLIENTE = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cliente);
			ResultSet resultset = preparedStatement.executeQuery();
			if(!resultset.next()) {
				System.out.println("Não há itens no carrinho.");
				return null;
			}
			System.out.println("--- ITENS NO CARRINHO ---");
			System.out.printf("| %8s | %10s | %10s | %8s | %4s |\n", "ID", "Produto", "Preço", "Qtd", "R$ total");
			
			resultset.previous();
			
			while(resultset.next()) {
				System.out.printf("| %8s | %10s | %10s | %8s | %4s |\n",
				resultset.getInt("ID"),
				resultset.getString("PRODUTO"),
				resultset.getInt("QUANTIDADE"),
				resultset.getDouble("PRECO_UNITARIO"),
				resultset.getDouble("TOTAL_ITEM"));
			}
			return resultset;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	 public void gerarCupom(int cliente) {
	 var ListaCarrinho = new ListaCarrinho();
	 try {
		String sql = "SELECT * FROM carrinho WHERE CLIENTE = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, cliente);
		ResultSet resultset = preparedStatement.executeQuery();
		
			if(!resultset.next()) {
				System.out.println("Não há itens no carrinho.");
				return;
			}
		System.out.println("--- ITENS NO CARRINHO ---");
		System.out.printf("| %8s | %10s | %10s | %8s | %4s |\n", "ID", "Produto", "Preço", "Qtd", "R$ total");
		
		resultset.previous();
		double totalcompra = 0;
			while(resultset.next()) {
				System.out.printf("| %8s | %10s | %10s | %8s | %4s |\n",
				resultset.getInt("ID"),
				resultset.getString("PRODUTO"),
				resultset.getInt("QUANTIDADE"),
				resultset.getDouble("PRECO_UNITARIO"),
				resultset.getDouble("TOTAL_ITEM"));
				totalcompra += resultset.getDouble("TOTAL_ITEM");
			}
		 sql = "SELECT nome FROM clientes WHERE ID = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, cliente);
		resultset = preparedStatement.executeQuery();
		resultset.next();
		 System.out.println("\nCliente: " + resultset.getString("nome") + "    TOTAL = " + totalcompra); 
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	
	 }
	
}
