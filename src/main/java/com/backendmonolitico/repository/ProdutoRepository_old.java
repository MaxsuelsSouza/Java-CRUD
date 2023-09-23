package com.backendmonolitico.repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.backendmonolitico.Model.Produto;
import com.backendmonolitico.Model.exception.ResourceNotFoundException;

@Repository
public class ProdutoRepository_old {

    private List<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;

    /**
     * Metodos para retorna uma lista de produtos
     * @return lista de produtos
     */
    public List<Produto> obterTodos(){
        return produtos;
    }
    /**
     * Metodo que retorna o produto pelo seu id 
     * @param id do produto que sera localizado 
     * @return retorna um produto caso tenha encontado
     */
    public Optional <Produto> obterPorId(Integer id){
        return produtos
            .stream()
            .filter(produto-> produto.getId() == id)
            .findFirst();
    }
    /**
     * Metodo para adicionar produto na lista 
     * @param produto que sera adicionado 
     * @return o produto que foi adiconado na lista 
     */
    public Produto adicionar(Produto produto){

        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);
        return produto;
    }
    /**
     * metodo para deletar o produto por id
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){
        produtos.removeIf(produto -> produto.getId() == id);
    }
    /**
     * metodo para atualiza o produto na lista
     * @param produto que sera atualizado
     * @return retorna um produto apos atualizar a lista
     */
    public Produto atualiza(Produto produto){
        //encontar o produto na lista
        Optional <Produto> produtoEncontrado = obterPorId(produto.getId());

        if(produtoEncontrado.isEmpty()){
            throw new ResourceNotFoundException("Produto nao encontrado");
        }
        // remover o antigo produto da lista
        deletar(produto.getId());

        //depois adicionar o produto atualizado na lista
        produtos.add(produto);
        return produto;
    }
}
