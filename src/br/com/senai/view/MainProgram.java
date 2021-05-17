package br.com.senai.view;


import br.com.senai.controller.Controller;
import br.com.senai.controller.carrinho.*;
import br.com.senai.controller.produto.*;
import br.com.senai.controller.cliente.*;

public class MainProgram {
	public static void main(String[] args) {
		Controller Controller = new Controller();
		ListaCarrinho listaCarrinho = new ListaCarrinho();
		AdicionarCarrinho AdicionarCarrinho = new AdicionarCarrinho();
		CadastrarProduto CadastrarProduto = new CadastrarProduto();
		ListaProduto ListaProduto = new ListaProduto();
		EditarProduto EditarProduto = new EditarProduto();
		DeletarProduto DeletarProduto = new DeletarProduto();
		DefinirCliente DefinirCliente = new DefinirCliente();
		
		boolean sair = false;
		
		int cliente = DefinirCliente.definirCliente();
		
		do {
			Controller.menu();
			int opc = Controller.opcao();
		
			switch (opc) {
			case 1:
				CadastrarProduto.cadastrarProduto();
				break;
			case 2:
				ListaProduto.consultarProdutos();
				break;
			case 3:
				EditarProduto.editarProduto();
				break;
			case 4:
				DeletarProduto.removerProdutos();
				break;
 			case 5:
				AdicionarCarrinho.cadastrarItemCarrinho(cliente);
				break;
			case 6:
				listaCarrinho.listarItensNoCarrinho(cliente);
				break;
			case 7:
				listaCarrinho.gerarCupom(cliente);
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
