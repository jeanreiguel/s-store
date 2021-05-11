package br.com.senai.controller.carrinho;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;
import br.com.senai.controller.produto.EditarProduto;
import br.com.senai.controller.produto.ListaProduto;

public class AdicionarCarrinho {
	Scanner dgt = new Scanner(System.in);
	
	
	public CarrinhoModel cadastrarItemCarrinho(List<ProdutoModel> produtos) {
		ListaProduto ListaProduto = new ListaProduto();
		EditarProduto EditarProduto = new EditarProduto();
		CarrinhoModel carrinhoModel = new CarrinhoModel();
		
		if(produtos.size() <= 0) {
			System.out.println("Não há produtos. ");
			return null;
		}
		ListaProduto.consultarProdutos();
		
		System.out.println("--- ADICIONAR ITEM NO CARRINHO ---");
		System.out.println("Informar o ID do produto: ");
		carrinhoModel.setIdProduto(dgt.nextInt() - 1);
		
		int idProduto = (carrinhoModel.getIdProduto());
		
		if(idProduto > produtos.size()) {
			System.out.println("Este produto não está cadastrado");
			return null;
		} 
		System.out.println("Informe a quantidade desejada: ");
		carrinhoModel.setQuantidadeItens(dgt.nextInt());
		
		
		if(carrinhoModel.getQuantidadeItens() > produtos.get(carrinhoModel.getIdProduto()).getQuantidadeDeProduto()) {
			System.out.println("O produto não possui a quantidade desejada");
			
		}
		
		EditarProduto.atualizarQuantidadeEValor(produtos, carrinhoModel.getQuantidadeItens(), idProduto);
		
		carrinhoModel.setProduto(produtos.get(idProduto));
		carrinhoModel.setValorTotalPorItem(carrinhoModel.getQuantidadeItens() *
				produtos.get(idProduto).getPrecoDoProduto());
		if(produtos.get(idProduto).getQuantidadeDeProduto() == 0) {
			produtos.remove(idProduto);
		}
		return carrinhoModel;
	}
}
