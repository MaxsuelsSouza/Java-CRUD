package com.backendmonolitico.Servercies;

import com.backendmonolitico.Model.Produto;
import com.backendmonolitico.Model.exception.ResourceNotFoundException;
import com.backendmonolitico.repository.ProdutoRepository;
import com.backendmonolitico.shared.ProdutoDTO;
import jakarta.persistence.EntityManagerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Service;


import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;


@Service
public class ProdutoService  {
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodos para retorna uma lista de produtos
     * @return lista de produtos
     */
    public List<ProdutoDTO> obterTodos(){
        //retorna uma lista de produto model
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
                .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
                .collect(Collectors.toList());

    }

    /**
     * Metodo que retorna o produto pelo seu id
     * @param id do produto que sera localizado
     * @return retorna um produto caso tenha encontado
     */
    public Optional <ProdutoDTO> obterPorId(Integer id){
        //obtendo optnial produto pelo id
        Optional<Produto> produtos = produtoRepository.findById(id);
        //se nao encontrar lanca exception
    if (produtos.isEmpty()){
        throw new ResourceNotFoundException( "Produto com id "+ id+ " nao encontrado");
    }
    //convertendo o meu optinal de produto em um produtoDTO
        ProdutoDTO dto = new ModelMapper().map(produtos.get(), ProdutoDTO.class);
    //criando e retornando um optinal de Produtodto
    return Optional.of(dto);
    }

    /**
     * Metodo para adicionar produto na lista
     *  produto que sera adicionado
     * @return o produto que foi adiconado na lista
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDto){
        //removendo o id para conseguir fazer o cadrasto
        produtoDto.setId(null);
        //criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();
        //converte o nosso produtoDTO em um produto
        Produto produto = mapper.map(produtoDto, Produto.class);
        //salvar o produto no banco
        produto = produtoRepository.save(produto);
            produtoDto.setId(produto.getId());
        //retorna o produtDTO atualizado
        return produtoDto;
    }

    /**
     * metodo para deletar o produto por id
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){
        //verificar se o produto existe
        Optional<Produto> produto = produtoRepository.findById(id);
        // se nao existir lança uma exception
        if (produto.isEmpty()){
            throw new ResourceNotFoundException("nao foi possivel deletar o produto com id "+ id);
        }
    // deleta o produto pelo id
        produtoRepository.deleteById(id);
}

    /**
     * metodo para atualiza o produto na lista
     *  produto que sera atualizado
     * @param id do produto
     * @return retorna um produto apos atualizar a lista
     */
    public ProdutoDTO atualiza(Integer id, ProdutoDTO produtoDto){
        //passa o id para o produtoDto
        produtoDto.setId(id);
        //criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();
        //converte o ProdutoDTO em um Produto
        Produto produto = mapper.map(produtoDto, Produto.class);
        //atualizar o produto no banco de dados
        produtoRepository.save(produto);
        //retorna o produtoDTO atualizado
        return produtoDto;
    }




}
