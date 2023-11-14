package com.projetoPedidos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoPedidos.entities.Pedidos;
import com.projetoPedidos.service.PedidosService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name = "Pedidos", description = "API REST DE GERENCIAMENTO DE PEDIDOS")
@RestController
@RequestMapping("/pedidos")	
public class PedidosController {

	private final PedidosService pedidosService;

	@Autowired
	public PedidosController(PedidosService pedidosService) {
		this.pedidosService = pedidosService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedidos> getProductById(@PathVariable Long id) {
		Pedidos pedidos = pedidosService.getPedidosById(id);
		if (pedidos != null) {
			return ResponseEntity.ok(pedidos);

		} else {
			return ResponseEntity.notFound().build();

		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Pedidos>> getAllPedidos() {
		List<Pedidos> pedidos = pedidosService.getAllPedidos();
		return ResponseEntity.ok(pedidos);
	}

	@PostMapping("/")
	public ResponseEntity<Pedidos> criarPedidos(@RequestBody @Valid Pedidos pedidos) {
		Pedidos criarPedidos = pedidosService.salvarPedidos(pedidos);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarPedidos);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pedidos> updatePedidso(@PathVariable Long id, @RequestBody @Valid Pedidos pedidos) {
		Pedidos updatedPedido = pedidosService.updatePedidos(id, pedidos);
		if (updatedPedido != null) {
			return ResponseEntity.ok(updatedPedido);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePedido(@PathVariable Long id) {
		boolean deleted = pedidosService.deletePedidos(id);
		if (deleted) {
			return ResponseEntity.ok().body("O pedido foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
