package br.com.senai.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

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
		System.out.println("5) Realizara venda");                                                                                      
		System.out.println("9) Sair do sistema");
		System.out.println("--------------------");
	}

	public ProdutoModel cadastrarProduto() {
		var produtoModel = new ProdutoModel();


		System.out.println("\n--- CADASTRAR ITENS ---\n");
		System.out.print("Produto: ");
		produtoModel.setNomdeDoProduto(dgt.next());
		System.out.print("Preço: ");
		produtoModel.setPrecoDoProduto(dgt.nextDouble());
		System.out.print("Quantidade:");
		produtoModel.setQuantidadeDeProduto(dgt.nextInt());
		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDeProduto() * produtoModel.getPrecoDoProduto());
		
		return produtoModel;
	}
	
	public List<ProdutoModel> consultarProdutos(List<ProdutoModel> produtos) {
		System.out.println("\n----- PRODUTOS CADASTRASDOS -----\n");
		System.out.printf("| %10s | %8s | %4s | %9s |\n","Produto","Preço","Qtd","R$ total");
		//for (ProdutoModel produtoModel : produtos) {
		//	System.out.printf("| %10s | %8s | %4s | %9s |\n", produtoModel.getNomdeDoProduto(), 
		//			produtoModel.getPrecoDoProduto(), produtoModel.getQuantidadeDeProduto(), produtoModel.getSaldoEmEstoque());
		//	
		//}
		produtos.forEach(produto -> {
			System.out.printf("| %10s | %8s | %4s | %9s |\n",
					produto.getNomdeDoProduto(),
					produto.getPrecoDoProduto(),
					produto.getQuantidadeDeProduto(),
					produto.getSaldoEmEstoque());
		});
		return produtos;
	}
	
	public ProdutoModel editarProduto(List<ProdutoModel> ) {
		var produto = new ProdutoModel();
		
		System.out.println("--------- EDITAR DADOS DE PRODUTOS ----------");
		System.out.println("Informe o ID do produto: ");
		int id = dgt.nextInt();
		
		System.out.println("Informe o camzxxpo que deseja editar: ");
		System.out.println("1) Nome do produto");
		System.out.println("2) Preço unitário");
		System.out.println("3) Quantidade");
		int index= dgt.nextInt();
		
		switch (index) {
		case 1:
			System.out.println("Qual o nome do produto? ");
			produto.setNomdeDoProduto(dgt.next());
			produto.setPrecoDoProduto(produtos.get(id).getPrecoDoProduto());
			produto.setQuantidadeDeProduto(produtos.get(id).getQuantidadeDeProduto());
			produto.setSaldoEmEstoque(produtos.get(id).getSaldoEmEstoque());
			produtos.set(id, produto);
			break;
		case 2:
			System.out.println("Qual o preço do produto? ");
			produto.setNomdeDoProduto(produtos.get(id).getNomdeDoProduto());
			produto.setPrecoDoProduto(dgt.nextDouble());
			produto.setQuantidadeDeProduto(produtos.get(id).getQuantidadeDeProduto());
			produto.setSaldoEmEstoque(produtos.get(id).getSaldoEmEstoque());
			produtos.set(id, produto);
			break;
		case 3:
			break;
		default:
			
			break;
		}
		
		return produto;
	}
}

