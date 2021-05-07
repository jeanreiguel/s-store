package br.com.senai.controller.carrinho;

import java.util.List;

import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class ListaCarrinho {
	public List<CarrinhoModel> listarItensNoCarrinho(List<CarrinhoModel> itensNoCarrinho) {
		System.out.println("--- ITENS NO CARRINHO ---");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ total");

		if (itensNoCarrinho.size() <= 0) {
			System.out.println("Não há itens no carrinho");
			return null;
		}
		itensNoCarrinho.forEach(item -> {
			System.out.printf("| %2s | %10s | R$%6.2f | %4s | R$%7.2f |\n", item.getIdProduto() + 1,
					item.getProduto().getNomeDoProduto(), item.getProduto().getPrecoDoProduto(),
					item.getQuantidadeItens(), item.getValorTotalPorItem());
		});

		/*
		 * double valorTotalDoCarrinho = itensNoCarrinho.stream().
		 * mapToDouble(CarrinhoModel::getValorTotalPorItem).sum();
		 */
		double valorTotalDoCarrinho = itensNoCarrinho.stream().mapToDouble(item -> item.getValorTotalPorItem()).sum();

		System.out.println("\nValor total: R$ " + valorTotalDoCarrinho);

		return itensNoCarrinho;
	}
	public void gerarCupom(List<CarrinhoModel> itensNoCarrinho, String cliente) {
		var ListaCarrinho = new ListaCarrinho();
		
		if(itensNoCarrinho.size() <= 0) {
			System.out.println("Lista vazia.");
			return;
		}
		ListaCarrinho.listarItensNoCarrinho(itensNoCarrinho);
		System.out.println("Cliente: " + cliente);
	}
	
}
