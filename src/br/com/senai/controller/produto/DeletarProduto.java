package br.com.senai.controller.produto;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;
import br.com.senai.controller.produto.ListaProduto;


public class DeletarProduto {
	
	Scanner dgt = new Scanner(System.in);
	
	
	public void removerProdutos(List<ProdutoModel> produtos) {
		ListaProduto ListaProduto = new ListaProduto();
		
		System.out.println("--- REMOVER PRODUTO ---");
		if (produtos.size() <= 0) {
			System.out.println("Não há produtos cadastrados");
			return;
		}

		ListaProduto.consultarProdutos();

		System.out.println("Informe o ID do produto  ser removido");
		int id = dgt.nextInt();

		if (id > produtos.size()) {
			System.out.println("Produto não cadastrado");
			return;
		}
		produtos.remove(id - 1);
	}
}
