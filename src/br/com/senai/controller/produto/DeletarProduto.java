package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DatabaseConnection;

public class DeletarProduto {

	private Connection connection;
	private Scanner dgt = new Scanner(System.in);

	public DeletarProduto() {
		connection = DatabaseConnection.getInstance().getConnection();
	}

	public boolean VerificaExistencia(int idDoProduto) {
		PreparedStatement preparedStatement;

		try {
			String sql = "SELECT * FROM produto WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			ResultSet resultset = preparedStatement.executeQuery();

			if (!resultset.next()) {
				System.out.println("Este produto n�o existe");
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void removerProdutos() {
		PreparedStatement preparedStatement;
		ListaProduto ListaProduto = new ListaProduto();

		System.out.println("--- REMOVER PRODUTO ---");

		if (ListaProduto.consultarProdutos() == null) {
			return;
		}

		System.out.println("Informe o ID do produto  ser removido: ");
		int id = dgt.nextInt();

		try {

			if (!VerificaExistencia(id)) {
				return;
			}

			String sql = "DELETE FROM produto WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("N�o foi poss�vel excluir essa informa��o");
			return;
		}
	}
}
