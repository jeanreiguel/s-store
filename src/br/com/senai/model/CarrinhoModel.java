package br.com.senai.model;

public class CarrinhoModel {
	private int quantidadeItens;
	private int idProduto;
	private ProdutoModel produto;
	private double ValorTotalPorItem;
	
	public CarrinhoModel(int quantidadeItens, int idProduto, ProdutoModel produto, double totalItens) {
		super();
		this.quantidadeItens = quantidadeItens;
		this.idProduto = idProduto;
		this.produto = produto;
		this.ValorTotalPorItem = totalItens;
	}
	public CarrinhoModel() {
		
	}
	public int getQuantidadeItens() {
		return quantidadeItens;
	}
	public void setQuantidadeItens(int quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public ProdutoModel getProduto() {
		return produto;
	}
	public void setProduto(ProdutoModel produto) {
		this.produto = produto;
	}
	public double getValorTotalPorItem() {
		return ValorTotalPorItem;
	}
	public void setValorTotalPorItem(double ValorTotalPorItem) {
		this.ValorTotalPorItem = ValorTotalPorItem;
	}
	

}
