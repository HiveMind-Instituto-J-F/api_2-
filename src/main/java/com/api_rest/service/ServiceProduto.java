package com.api_rest.service;
import com.api_rest.dto.ProdutoRequestDTO;
import com.api_rest.dto.ProdutoResponseDTO;
import com.api_rest.model.Produto;
import com.api_rest.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ServiceProduto {

    private final ProdutoRepository produtoRepository;
    private final ObjectMapper objectMapper;

    public ServiceProduto(ProdutoRepository produtoRepository, ObjectMapper objectMapper) {
        this.produtoRepository = produtoRepository;
        this.objectMapper = objectMapper;
    }

    public ProdutoResponseDTO inserirProduto(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = objectMapper.convertValue(produtoRequestDTO, Produto.class);
        Produto resposta = produtoRepository.save(produto);
        ProdutoResponseDTO produtoDTO = objectMapper.convertValue(resposta, ProdutoResponseDTO.class);
        return produtoDTO;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscarProduto(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public ProdutoResponseDTO atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequestDTO produto) {
        Produto produto1 = buscarProduto(id);

        produto1.setNome(produto.getNome());
        produto1.setDescricao(produto.getDescricao());
        produto1.setPreco(produto.getPreco());
        produto1.setQuantidadedeestoque(produto.getQuantidadeEstoque());

        Produto resposta = produtoRepository.save(produto1);
        ProdutoResponseDTO produtoDTO = objectMapper.convertValue(resposta, ProdutoResponseDTO.class);
        return produtoDTO;
    }

    public ResponseEntity<String> atualizarProdutoParcialmente(@PathVariable Long id,
                                                       @RequestBody Map<String, Object> updates) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);

        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();

            if (updates.containsKey("nome")) {
                produto.setNome( String.valueOf( updates.get("nome") ) );
            }

            if (updates.containsKey("descricao")) {
                produto.setDescricao( String.valueOf( updates.get("descricao") ));
            }

            if (updates.containsKey("preco")) {
                produto.setPreco( Double.parseDouble(String.valueOf(updates.get("preco"))) );
            }

            if (updates.containsKey("quantidadedeestoque")) {
                produto.setQuantidadedeestoque( Integer.parseInt( String.valueOf( updates.get("quantidadedeestoque") ) ) );
            }

            produtoRepository.save(produto);
            return ResponseEntity.ok("Produto atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity<String> excluirProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return ResponseEntity.ok("Produto removido com sucesso!");
    }
}
