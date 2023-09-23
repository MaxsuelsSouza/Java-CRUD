package com.backendmonolitico.view.Controller;

import com.backendmonolitico.Model.Produto;
import com.backendmonolitico.Servercies.ProdutoService;
import com.backendmonolitico.shared.ProdutoDTO;
import com.backendmonolitico.view.model.ProdutoRequest;
import com.backendmonolitico.view.model.ProdutoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping()
    public ResponseEntity<List<ProdutoResponse>> obterTodos() {
        List<ProdutoDTO> produtos = produtoService.obterTodos();
        ModelMapper mapper = new ModelMapper();

        List<ProdutoResponse> resposta = produtos.stream()
                .map(produtoDto -> mapper.map(produtoDto, ProdutoResponse.class))
                .collect(Collectors.toList());

            return  new ResponseEntity<>(resposta, HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity< Optional<ProdutoResponse>> obterPorId(@PathVariable Integer id) {
       // try {
            Optional<ProdutoDTO> dto = produtoService.obterPorId(id);
            ProdutoResponse produto = new ModelMapper().map(dto.get(), ProdutoResponse.class);
            return new ResponseEntity<>(Optional.of(produto),HttpStatus.OK);
       // }catch (Exception e){
       //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      //  }
    }

    @PostMapping()
    public ResponseEntity< ProdutoResponse> adicionar(@RequestBody ProdutoRequest produtoReq) {
        ModelMapper mapper= new ModelMapper();
        ProdutoDTO produtoDto = mapper.map(produtoReq, ProdutoDTO.class);

       produtoDto = produtoService.adicionar(produtoDto);

       return new ResponseEntity<>(mapper.map(produtoDto, ProdutoResponse.class),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        produtoService.deletar(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@RequestBody ProdutoRequest produtoReq, @PathVariable Integer id) {
        ModelMapper mapper = new ModelMapper();
        ProdutoDTO produtoDto = mapper.map(produtoReq,ProdutoDTO.class);

        produtoDto = produtoService.atualiza(id, produtoDto);

        return new ResponseEntity<>(
                mapper.map(produtoDto, ProdutoResponse.class),
                HttpStatus.OK);
   }
}
