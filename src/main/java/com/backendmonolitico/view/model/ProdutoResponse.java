package com.backendmonolitico.view.model;
/** o que eu vou devolver
 * Produto response e o que eu vou devolver ao client
 */
public class ProdutoResponse {
    private Integer id;
    private String nome;
    private Integer quantidade;
    private double valor;
    //private String observacao;

    //#region getter and setter
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
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
//    public String getObservacao() {
//        return observacao;
//    }
//    public void setObservacao(String observacao) {
//        this.observacao = observacao;
//    }
    //#endregion

}
