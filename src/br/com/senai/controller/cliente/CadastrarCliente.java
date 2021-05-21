package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DatabaseConnection;

public class CadastrarCliente {
	private Scanner dgt = new Scanner(System.in);
	private PreparedStatement preparedStatement;
	private Connection connection;

	public CadastrarCliente() {
		connection = DatabaseConnection.getInstance().getConnection();
	}

	public int CadastrarCliente() {
		String nome;
		System.out.println("--- CADASTRAR CLIENTE ---");
		System.out.println("Informe seu nome: ");
		nome = dgt.next();

		if (verificarCliente(nome)) {
			System.out.println("\nCliente já existente..\n");
			return 0;
		}
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
			System.out.println("Cadastro realizado!!");
			return codigocliente;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean verificarCliente(String nome) {

		try {
			String sql = "SELECT nome FROM clientes";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				if (nome.equals(resultset.getString("nome"))) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}
}
