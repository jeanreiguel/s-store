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
		ExcluirCarrinho ExcluirCarrinho = new ExcluirCarrinho();
		CadastrarProduto CadastrarProduto = new CadastrarProduto();
		ListaProduto ListaProduto = new ListaProduto();
		EditarProduto EditarProduto = new EditarProduto();
		DeletarProduto DeletarProduto = new DeletarProduto();
		DefinirCliente DefinirCliente = new DefinirCliente();
		CadastrarCliente CadastrarCliente = new CadastrarCliente();
		int cliente = 0;
		boolean sair = false;
		do {
			 cliente = 0;
			Controller.menuCliente();
			int opcclient = Controller.opcao();
		
			 
			 switch (opcclient) {
			 case 1:
				 cliente = DefinirCliente.SelecionarCliente();
				 break;
			 case 2:
				 cliente = CadastrarCliente.CadastrarCliente();
				 break;
			 }
		}while(cliente == 0);
		
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
				ExcluirCarrinho.excluirItensCarrinho(cliente);
				break;
			case 8:
				ExcluirCarrinho.AlterarQuantidade(cliente);
				break;
			case 9:
				listaCarrinho.gerarCupom(cliente);
				break;
			case 10:
				sair = true;
				break;

			default:
				System.out.println("Opção inválida!!!");
				break;
			}
		} while (!sair);
		
		System.out.println("Volte Sempre!!!");
		Controller.menuCliente();
	}
}
