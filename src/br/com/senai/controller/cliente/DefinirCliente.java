package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dal.DatabaseConnection;

public class DefinirCliente {
	private Scanner dgt = new Scanner(System.in);
	private PreparedStatement preparedStatement;
	private Connection connection;
	
	public DefinirCliente() {
		connection = DatabaseConnection.getInstance().getConnection(); 
	}
	public int definirCliente() {
		String nome;
		System.out.println("Informe o nome do cliente");
		nome = dgt.next();
		
		try {
			String sql = "INSERT INTO clientes (nome) VALUES (?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nome);
			preparedStatement.execute();
			sql = "SELECT ID FROM clientes WHERE nome = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nome);
			ResultSet resultset = preparedStatement.executeQuery();
			resultset.next();
			int codigocliente = resultset.getInt("ID");
			return codigocliente;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
