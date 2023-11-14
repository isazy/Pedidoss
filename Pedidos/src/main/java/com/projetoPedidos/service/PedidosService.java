package com.projetoPedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoPedidos.entities.Pedidos;
import com.projetoPedidos.repository.PedidosRepository;

@Service
public class PedidosService {

	private final PedidosRepository pedidosRepository;

	@Autowired
	public PedidosService(PedidosRepository pedidosRepository) {
		this.pedidosRepository = pedidosRepository;
	}

	public List<Pedidos> getAllPedidos() {
		return pedidosRepository.findAll();
	}
	public Pedidos getPedidosById(Long id) {
		Optional<Pedidos> pedidos = pedidosRepository.findById(id);
		return pedidos.orElse(null);
	}

	public Pedidos salvarPedidos(Pedidos pedidos) {
		return pedidosRepository.save(pedidos);
	}

	public Pedidos updatePedidos(Long id, Pedidos updatedPedidos) {
		Optional<Pedidos> existingPedidos = pedidosRepository.findById(id);
		if (existingPedidos.isPresent()) {
			return pedidosRepository.save(updatedPedidos);
		}
		return null;
	}
	public boolean deletePedidos(Long id) {
		Optional<Pedidos> existingPedidos = pedidosRepository.findById(id);
		if (existingPedidos.isPresent()) {
			pedidosRepository.deleteById(id);
			return true;
		}
		return false;
	}
}


