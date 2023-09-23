package com.backendmonolitico.view.model;

/** o que eu espero receber
 * Produto request e o que eu espero receber do client
 */
public class ProdutoRequest {

    private String nome;
    private Integer quantidade;
    private double valor;
    private String observacao;

    //#region getter and setter
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    //#endregion

}
