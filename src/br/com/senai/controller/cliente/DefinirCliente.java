package br.com.senai.controller.cliente;

import java.util.Scanner;

public class DefinirCliente {
	Scanner dgt = new Scanner(System.in);
	
	public String definirCliente() {
		String nome;
		System.out.println("Informe o nome do cliente");
		nome = dgt.next();
		return nome;
}
}
