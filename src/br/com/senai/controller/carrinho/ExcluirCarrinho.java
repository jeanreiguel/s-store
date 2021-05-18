package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dal.DatabaseConnection;

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
		
		
	}
	
	public void VerificaCarrinho(int cliente, int id) {
		
		try {
			String sql = "SELECT ID FROM carrinho WHERE CLIENTE = ? && ID = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cliente);
			preparedStatement.setInt(1, id);
			ResultSet resultset = preparedStatement.executeQuery();
			
			if(!resultset.next()) {
				System.out.println("Não há esse produto no carrinho");
				return;
			}
			String sql = "UPDATE produto SET quantidade = ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cliente);
			preparedStatement.setInt(1, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
