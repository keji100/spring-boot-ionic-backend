package com.willianrosa.cursomc.services;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willianrosa.cursomc.domain.ItemPedido;
import com.willianrosa.cursomc.domain.PagamentoComBoleto;
import com.willianrosa.cursomc.domain.Pedido;
import com.willianrosa.cursomc.domain.Produto;
import com.willianrosa.cursomc.domain.enums.EstadoPagamento;
import com.willianrosa.cursomc.repositories.ClienteRepository;
import com.willianrosa.cursomc.repositories.ItemPedidoRepository;
import com.willianrosa.cursomc.repositories.PagamentoRepository;
import com.willianrosa.cursomc.repositories.PedidoRepository;
import com.willianrosa.cursomc.repositories.ProdutoRepository;
import com.willianrosa.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired 
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+id+", Tipo : "+ Pedido.class.getName()));
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstace(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstace());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			Produto newProduto = produtoService.find(ip.getProduto().getId());
			ip.setProduto(newProduto);
			ip.setPreco(newProduto.getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens()); 
		System.out.println(obj);
		return obj;
	}
	
}
