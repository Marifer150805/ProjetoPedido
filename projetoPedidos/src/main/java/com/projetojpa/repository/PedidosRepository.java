package com.projetojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetojpa.entities.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Long> {

}