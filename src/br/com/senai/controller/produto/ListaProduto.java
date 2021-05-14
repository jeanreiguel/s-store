package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import br.com.dal.DatabaseConnection;

public class ListaProduto {
	
	private Connection connection;
	
	public ListaProduto() {
		connection = DatabaseConnection.getInstance().getConnection();
	}
	
	public ResultSet consultarProdutos() {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "SELECT * FROM produto";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultset = preparedStatement.executeQuery();
			
			System.out.println("\n----- PRODUTOS CADASTRASDOS -----\n");
			System.out.printf("| %2s | %15s | %8s | %4s | %9s2f |\n", "ID", "Produto", "Preço", "Qtd", "R$ total");
			if(!resultset.next()) {
				System.out.println("Não há produtos cadastrados");
				return null;
			}
			resultset.previous();
			while(resultset.next()) {
				System.out.printf("| %2s | %15s | %8s | %4s | %9s2f |\n",
								   resultset.getInt("codigo"),
								   resultset.getString("nomeDoProduto"),
								   resultset.getDouble("precoDoProduo"),
								   resultset.getInt("quantidade"),
								   resultset.getDouble("saldoEmEstoque"));
			}
			
			return resultset;
		} catch (Exception e){
			return null;
		}

		//if (produtos.size() <= 0) {
		//	System.out.println("Não há produtos para serem listados");
		//	return null;
		//}
		//System.out.println("\n----- PRODUTOS CADASTRASDOS -----\n");
		//System.out.printf("| %2s | %10s | %8s | %4s | %9s2f |\n", "ID", "Produto", "Preço", "Qtd", "R$ total");
		// for (ProdutoModel produtoModel : produtos) {
		// System.out.printf("| %10s | %8s | %4s | %9s |\n",
		// produtoModel.getNomdeDoProduto(),
		// produtoModel.getPrecoDoProduto(), produtoModel.getQuantidadeDeProduto(),
		// produtoModel.getSaldoEmEstoque());
		//
		// }
		// produtos.forEach(produto -> {
		// System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n",
		// ident,
		// produto.getNomeDoProduto(),
		// produto.getPrecoDoProduto(),
		// produto.getQuantidadeDeProduto(),
		// produto.getSaldoEmEstoque());
		// });
		// return produtos;
		//for (int i = 0; i < produtos.size(); i++) {
		//	System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", i + 1, produtos.get(i).getNomeDoProduto(),
		//			produtos.get(i).getPrecoDoProduto(), produtos.get(i).getQuantidadeDeProduto(),
		//			produtos.get(i).getSaldoEmEstoque());
		//}
	}
}
