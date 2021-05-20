package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DatabaseConnection;

public class DefinirCliente {
	
	private Scanner dgt = new Scanner(System.in);
	private Connection connection;
	private PreparedStatement preparedStatement;
	
	
	public DefinirCliente() {
		connection = DatabaseConnection.getInstance().getConnection();
	}
	
	public int SelecionarCliente() {
		System.out.println("Informe seu nome: ");
		String nome = dgt.next();
		CadastrarCliente cliente = new CadastrarCliente();
		
		if(cliente.verificarCliente(nome)) {
			System.out.println("Bem vindo," + nome + "!");
			
			try {
				String sql = "SELECT ID from clientes WHERE nome = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, nome);
				ResultSet resultset = preparedStatement.executeQuery();
				resultset.next();
				
				return resultset.getInt("ID");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Cliente não cadastrado");
		return 0;
	}
}
