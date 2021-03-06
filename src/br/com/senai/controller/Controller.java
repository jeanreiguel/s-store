package br.com.senai.controller;

import java.util.List;
import java.util.Scanner;

import br.com.senai.controller.carrinho.ListaCarrinho;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class Controller {

	private Scanner dgt;
	ListaProduto ListaProduto = new ListaProduto();

	public Controller() {
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
		System.out.println("7) Excluir carrinho");
		System.out.println("8) Alterar quantidade");
		System.out.println("9) Gerar Cupom");
		System.out.println("10) Sair do sistema");
		System.out.println("--------------------");
	}
	public void menuCliente() {
		System.out.println("\n--- LOGIN ---\n");
		System.out.println("1) Entrar na conta");
		System.out.println("2) Cadastrar Conta");
	}

}
