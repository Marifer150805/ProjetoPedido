package com.projetojpa.controller;

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

import com.projetojpa.entities.Pedidos;
import com.projetojpa.service.PedidosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Pedidos", description= "API REST DE GERENCIAMENTO DE PEDIDOS")
@RestController
@RequestMapping("/pedidos")

public class PedidosController {

	private final PedidosService pedidosService;

	@Autowired
	public PedidosController(PedidosService pedidosService) {
		this.pedidosService = pedidosService;
	}
	@GetMapping("/{id}")
	@Operation(summary = "Localizar pedido por ID")
	public ResponseEntity<Pedidos> buscaPedidosControlId(@PathVariable Long id){
		Pedidos pedidos = pedidosService.buscaPedidosId(id);
		if(pedidos != null) {
			return ResponseEntity.ok(pedidos);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping
	@Operation(summary = "Apresenta todos pedidos")
	public ResponseEntity<List<Pedidos>> buscaTodosPedidosControl(){
		List<Pedidos> Pedidos= pedidosService.buscaTodosPedidos();
		return ResponseEntity.ok(Pedidos);
	}
	@PostMapping
	@Operation(summary = "Cadastrar um pedido")
	public ResponseEntity<Pedidos> salvaPedidosControl(@RequestBody @Valid Pedidos pedidos) {
		Pedidos salvaPedidos = pedidosService.salvaPedidos(pedidos);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaPedidos);
	}
	@PutMapping("/{id}")
	@Operation(summary = "Alterar um pedido")
	public ResponseEntity<Pedidos> alterarPedidosControl(@PathVariable Long id, @RequestBody @Valid Pedidos pedidos){
	Pedidos alteraPedidos = pedidosService.alterarPedidos(id, pedidos);
	if(alteraPedidos != null) {
		return ResponseEntity.ok(pedidos);
	}
	else {
		return ResponseEntity.notFound().build();
	}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Excluir um pedido")
	public ResponseEntity<String> apagarPedidosControl(@PathVariable Long id) {
		boolean apagar = pedidosService.apagarPedidos(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
	}
	else {
		return ResponseEntity.notFound().build();    
	}
	}
}