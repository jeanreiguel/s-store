package br.com.senai.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class ProdutoController {

	private Scanner dgt;

	public ProdutoController() {
		dgt = new Scanner(System.in);
	}

	public int opcao() {
		System.out.print("> ");
		return dgt.nextInt();
	}

	public void menu() {
		System.out.println("\n--- MENU ---\n");
		System.out.println("1) Cadastrar itens");
		System.out.println("2) Listar estoque");
		System.out.println("3) Editar item");
		System.out.println("4) Remover item");
		System.out.println("5) Adicionar no Carrinho");
		System.out.println("6) Exibir carrinho");
		System.out.println("9) Sair do sistema");
		System.out.println("--------------------");
	}
	public CarrinhoModel cadastrarItemCarrinho(List<ProdutoModel> produtos) {
		CarrinhoModel carrinhoModel = new CarrinhoModel();
		
		if(produtos.size() <= 0) {
			System.out.println("Não há produtos. ");
			return null;
		}
		consultarProdutos(produtos);
		
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
		
		atualizarQuantidadeEValor(produtos, carrinhoModel.getQuantidadeItens(), idProduto);
		
		
		carrinhoModel.setProduto(produtos.get(idProduto));
		carrinhoModel.setValorTotalPorItem(carrinhoModel.getQuantidadeItens() *
				produtos.get(idProduto).getPrecoDoProduto());
		
		return carrinhoModel;
	}
	public List<ProdutoModel> atualizarQuantidadeEValor(List<ProdutoModel> produtos, int quantidade, int idDoProduto) {
		ProdutoModel produto = new ProdutoModel();
		produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto() - quantidade);
		produto.setSaldoEmEstoque(produtos.get(idDoProduto).getPrecoDoProduto() * produto.getQuantidadeDeProduto());
		produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
		produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
		produtos.set(idDoProduto, produto);
		
		return produtos;
	}
	public List<CarrinhoModel> listarItensNoCarrinho(List<CarrinhoModel> itensNoCarrinho) {
		System.out.println("--- ITENS NO CARRINHO ---");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ total");
		
		if(itensNoCarrinho.size() <= 0) {
			System.out.println("Não há itens no carrinho");
			return null;
		}
		itensNoCarrinho.forEach(item -> {
			System.out.printf("| %2s | %10s | R$%6.2f | %4s | R$%7.2f |\n",
					item.getIdProduto() + 1,
					item.getProduto().getNomeDoProduto(),
					item.getProduto().getPrecoDoProduto(),
					item.getQuantidadeItens(),
					item.getValorTotalPorItem()
					);
			});
		
		/*double valorTotalDoCarrinho = itensNoCarrinho.stream().
				mapToDouble(CarrinhoModel::getValorTotalPorItem).sum();*/
		double valorTotalDoCarrinho = itensNoCarrinho.stream().
				mapToDouble(item -> item.getValorTotalPorItem()).sum();
		
		System.out.println("\nValor total: R$ " + valorTotalDoCarrinho);
		
		return itensNoCarrinho;
	}
	public ProdutoModel cadastrarProduto() {
		var produtoModel = new ProdutoModel();

		System.out.println("\n--- CADASTRAR ITENS ---\n");
		System.out.print("Produto: ");
		produtoModel.setNomeDoProduto(dgt.next());
		System.out.print("Preço: ");
		produtoModel.setPrecoDoProduto(dgt.nextDouble());
		System.out.print("Quantidade:");
		produtoModel.setQuantidadeDeProduto(dgt.nextInt());
		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDeProduto() * produtoModel.getPrecoDoProduto());

		return produtoModel;
	}

	public List<ProdutoModel> consultarProdutos(List<ProdutoModel> produtos) {

		if (produtos.size() <= 0) {
			System.out.println("Não há produtos para serem listados");
			return null;
		}
		System.out.println("\n----- PRODUTOS CADASTRASDOS -----\n");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ total");
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

	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		var produto = new ProdutoModel();

		if (produtos.size() <= 0) {
			System.out.println("Não há produtos para serem editados");
			return null;
		}
		consultarProdutos(produtos);
		System.out.println("--------- EDITAR DADOS DE PRODUTOS ----------");
		System.out.println("Informe o ID do produto: ");
		int id = dgt.nextInt() - 1;
		if (id > produtos.size()) {
			System.out.println("Produto não existe");
		} else {
			System.out.println("Informe o camzxxpo que deseja editar: ");
			System.out.println("1) Nome do produto");
			System.out.println("2) Preço unitário");
			System.out.println("3) Quantidade");
			int index = dgt.nextInt();

			switch (index) {
			case 1:
				System.out.println("Qual o nome do produto? ");
				produto.setNomeDoProduto(dgt.next());
				produto.setPrecoDoProduto(produtos.get(id).getPrecoDoProduto());
				produto.setQuantidadeDeProduto(produtos.get(id).getQuantidadeDeProduto());
				produto.setSaldoEmEstoque(produtos.get(id).getSaldoEmEstoque());
				produtos.set(id, produto);
				break;
			case 2:
				System.out.println("Qual o preço do produto? ");
				produto.setNomeDoProduto(produtos.get(id).getNomeDoProduto());
				produto.setQuantidadeDeProduto(produtos.get(id).getQuantidadeDeProduto());
				produto.setPrecoDoProduto(dgt.nextDouble());
				produto.setSaldoEmEstoque(produto.getQuantidadeDeProduto() * produto.getPrecoDoProduto());
				produtos.set(id, produto);
				break;
			case 3:
				System.out.println("Qual a quantidade do produto?");
				produto.setNomeDoProduto(produtos.get(id).getNomeDoProduto());
				produto.setPrecoDoProduto(produtos.get(id).getPrecoDoProduto());
				produto.setQuantidadeDeProduto(dgt.nextInt());
				produto.setSaldoEmEstoque(produto.getQuantidadeDeProduto() * produto.getPrecoDoProduto());
				produtos.set(id, produto);
				break;
			default:
				System.out.println("Opção inválida!!");
				break;
			}
		}
		return produto;
	}

	public void removerProdutos(List<ProdutoModel> produtos) {
		System.out.println("--- REMOVER PRODUTO ---");
		if (produtos.size() <= 0) {
			System.out.println("Não há produtos cadastrados");
			return;
		}

		consultarProdutos(produtos);

		System.out.println("Informe o ID do produto  ser removido");
		int id = dgt.nextInt();

		if (id > produtos.size()) {
			System.out.println("Produto não cadastrado");
			return;
		}
		produtos.remove(id - 1);
	}
	public ProdutoModel adicionarCarrinho(List<ProdutoModel> produtos, List<ProdutoModel> carrinho) {
		System.out.println("----- CARRINHO -----");
		
		ProdutoModel item = new ProdutoModel();
		if (produtos.size() <= 0) {
			System.out.println("Não há produtos na loja");
			return null;
		}
		consultarProdutos(produtos);
		
		System.out.println("Qual o ID do produto que deseja adicionar no carrinho?");
		int id = dgt.nextInt() - 1;
		if (id >= produtos.size()) {
			System.out.println("Produto não cadastrado");
			return null;
		}
		
		System.out.println("Produto selecionado: " + produtos.get(id).getNomeDoProduto());
		item.setNomeDoProduto(produtos.get(id).getNomeDoProduto());
		
		System.out.println("Qual a quantidade que deseja? ");
		int quantidade = dgt.nextInt();
		if( quantidade > produtos.get(id).getQuantidadeDeProduto()) {
			System.out.println("Estoque insuficiente. ( " + produtos.get(id).getQuantidadeDeProduto() + ") - Total em estoque");
		} else {
			item.setQuantidadeDeProduto(quantidade);
			produtos.get(id).setQuantidadeDeProduto(produtos.get(id).getQuantidadeDeProduto() - quantidade);
			produtos.get(id).setSaldoEmEstoque(produtos.get(id).getQuantidadeDeProduto() * produtos.get(id).getPrecoDoProduto());
		}
		item.setPrecoDoProduto(produtos.get(id).getPrecoDoProduto());
		item.setSaldoEmEstoque(produtos.get(id).getPrecoDoProduto() * item.getQuantidadeDeProduto());
		
		return item;
	}
	public List<ProdutoModel> exibirCarrinho(List<ProdutoModel> carrinho) {
		
		if (carrinho.size() <= 0) {
			System.out.println("Não há produtos para no seu carrinho");
			return null;
		}
		
		System.out.println("\n----- Carrinho -----\n");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ total");
		
		for (int i = 0; i < carrinho.size(); i++) {
			System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", i + 1, carrinho.get(i).getNomeDoProduto(),
					carrinho.get(i).getPrecoDoProduto(), carrinho.get(i).getQuantidadeDeProduto(),
					carrinho.get(i).getSaldoEmEstoque());
		}
		return carrinho;
	}
	
}
