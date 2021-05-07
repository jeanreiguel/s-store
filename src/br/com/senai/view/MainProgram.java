package br.com.senai.view;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.controller.Controller;
import br.com.senai.controller.carrinho.*;
import br.com.senai.controller.produto.*;
import br.com.senai.controller.cliente.*;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class MainProgram {
	public static void main(String[] args) {
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		List<CarrinhoModel> itensNoCarrinho = new ArrayList<CarrinhoModel>();
		
		Controller Controller = new Controller();
		ListaCarrinho listaCarrinho = new ListaCarrinho();
		AdicionarCarrinho AdicionarCarrinho = new AdicionarCarrinho();
		CadastrarProduto CadastrarProduto = new CadastrarProduto();
		ListaProduto ListaProduto = new ListaProduto();
		EditarProduto EditarProduto = new EditarProduto();
		DeletarProduto DeletarProduto = new DeletarProduto();
		DefinirCliente DefinirCliente = new DefinirCliente();
		
		boolean sair = false;
		
		String cliente = DefinirCliente.definirCliente();
		
		do {
			Controller.menu();
			int opc = Controller.opcao();
		
			switch (opc) {
			case 1:
				produtos.add(CadastrarProduto.cadastrarProduto());
				break;
			case 2:
				ListaProduto.consultarProdutos(produtos);
				break;
			case 3:
				EditarProduto.editarProduto(produtos);
				break;
			case 4:
				DeletarProduto.removerProdutos(produtos);
				break;
			case 5:
				itensNoCarrinho.add(AdicionarCarrinho.cadastrarItemCarrinho(produtos));
				break;
			case 6:
				listaCarrinho.listarItensNoCarrinho(itensNoCarrinho);
				break;
			case 7:
				listaCarrinho.gerarCupom(itensNoCarrinho,cliente);
				break;
			case 9:
				sair = true;
				break;

			default:
				System.out.println("Opção inválida!!!");
				break;
			}
		} while (!sair);

		System.out.println("Sistema encerrado!!!");
	}
}
