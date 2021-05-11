package br.com.senai.controller.produto;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;

public class EditarProduto {
	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		var produto = new ProdutoModel();
		var ListaProduto = new ListaProduto();
		var dgt = new Scanner(System.in);
		
		if (produtos.size() <= 0) {
			System.out.println("Não há produtos para serem editados");
			return null;
		}
		ListaProduto.consultarProdutos();
		System.out.println("--------- EDITAR DADOS DE PRODUTOS ----------");
		System.out.println("Informe o ID do produto: ");
		int id = dgt.nextInt() - 1;
		if (id >= produtos.size()) {
			System.out.println("Produto não existe");
		} else {
			System.out.println("Informe o campo que deseja editar: ");
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
	public List<ProdutoModel> atualizarQuantidadeEValor(List<ProdutoModel> produtos, int quantidade, int idDoProduto) {
		ProdutoModel produto = new ProdutoModel();
		produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto() - quantidade);
		produto.setSaldoEmEstoque(produtos.get(idDoProduto).getPrecoDoProduto() * produto.getQuantidadeDeProduto());
		produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
		produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
		produtos.set(idDoProduto, produto);
		
		return produtos;
	}
}
