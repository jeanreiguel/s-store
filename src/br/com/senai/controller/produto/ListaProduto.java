package br.com.senai.controller.produto;

import java.util.List;

import br.com.senai.model.ProdutoModel;

public class ListaProduto {
	public List<ProdutoModel> consultarProdutos(List<ProdutoModel> produtos) {

		if (produtos.size() <= 0) {
			System.out.println("Não há produtos para serem listados");
			return null;
		}
		System.out.println("\n----- PRODUTOS CADASTRASDOS -----\n");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s2f |\n", "ID", "Produto", "Preço", "Qtd", "R$ total");
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
		for (int i = 0; i < produtos.size(); i++) {
			System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", i + 1, produtos.get(i).getNomeDoProduto(),
					produtos.get(i).getPrecoDoProduto(), produtos.get(i).getQuantidadeDeProduto(),
					produtos.get(i).getSaldoEmEstoque());
		}

		return produtos;
	}
}
