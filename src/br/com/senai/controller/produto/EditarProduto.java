package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import br.com.dal.DatabaseConnection;
import br.com.senai.model.ProdutoModel;

public class EditarProduto {
	private ProdutoModel produto;
	private ListaProduto listaProduto;
	private Scanner dgt = new Scanner(System.in);
	private Connection connection;
	
	public EditarProduto() {
		connection = DatabaseConnection.getInstance().getConnection();
	}
	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		PreparedStatement preparedStatement;
		
		produto = new ProdutoModel();
		listaProduto = new ListaProduto();
		
		int idDoProduto, indexDoCampo;
		
		listaProduto.consultarProdutos();
		
		if (listaProduto.consultarProdutos() == null) {
			return null;
		}
		
		System.out.println("--------- EDITAR DADOS DE PRODUTOS ----------");
		System.out.println("Informe o ID do produto: ");
		idDoProduto = dgt.nextInt();
		
		try {
			String sql = "SELECT * FROM produto WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			
			ResultSet resultset = preparedStatement.executeQuery();
			
			if(!resultset.next()) {
				System.out.println("Este produto não existe");
				return null;
			} else {
				produto.setNomeDoProduto(resultset.getString("nomeDoProduto"));
				produto.setPrecoDoProduto(resultset.getDouble("PrecoDoProduo"));
				produto.setQuantidadeDeProduto(resultset.getInt("quantidade"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			System.out.println("Informe o campo que deseja editar: ");
			System.out.println("1) Nome do produto");
			System.out.println("2) Preço unitário");
			System.out.println("3) Quantidade");
			int index = dgt.nextInt();
			
			switch(index) {
			case 1:
				editarNomeDoProduto(idDoProduto);
				break;
			case 2:
				editarPreçoProduto(idDoProduto);
				break;
			case 3:
				editarQuantidadeProduto(idDoProduto);
				break;
				default:
					System.out.println("CAMPO INVÁLIDO");
			}
		return produto;
	}
	
	private ProdutoModel editarNomeDoProduto(int idDoProduto) {
		
		PreparedStatement preparedStatement;
		System.out.println("Informe o nome do produto");
		produto.setNomeDoProduto(dgt.next());
		try {
			String sql = "UPDATE produto SET nomeDoProduto = ? WHERE codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, produto.getNomeDoProduto());
			preparedStatement.setInt(2, idDoProduto);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return produto;
	}
	
	public ProdutoModel editarQuantidadeProduto(int idDoProduto) {
		PreparedStatement preparedStatement;
		
	  System.out.println("Qual a quantidade do produto?");
	  produto.setQuantidadeDeProduto(dgt.nextInt());
	  produto.setSaldoEmEstoque(produto.getPrecoDoProduto() * produto.getQuantidadeDeProduto());
	  
	  try {
		  String sql = "UPDATE produto SET quantidade = ?, saldoEmEstoque = ? WHERE codigo = ?";
		  	preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, produto.getQuantidadeDeProduto());
			preparedStatement.setDouble(2, produto.getSaldoEmEstoque());
			preparedStatement.setInt(3, idDoProduto);
				
			preparedStatement.execute();
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	  return produto;
	}
	public ProdutoModel editarPreçoProduto(int idDoProduto) {
		PreparedStatement preparedStatement;
	  System.out.println("Qual o preço do produto? ");
	  produto.setPrecoDoProduto(dgt.nextDouble());
	  
	  produto.setSaldoEmEstoque(produto.getPrecoDoProduto() * produto.getQuantidadeDeProduto());
	  
	  try {
		  String sql = "UPDATE produto SET precoDoProduo = ?, saldoEmEstoque = ? WHERE codigo = ?";
	  	preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setDouble(1, produto.getPrecoDoProduto());
		preparedStatement.setDouble(2, produto.getSaldoEmEstoque());
		preparedStatement.setInt(3, idDoProduto);
			
		preparedStatement.execute();
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	  
	  return produto;
	}
}
