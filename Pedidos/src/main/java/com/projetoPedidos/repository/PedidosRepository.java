package com.projetoPedidos.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoPedidos.entities.Pedidos;


public interface PedidosRepository extends JpaRepository<Pedidos, Long> {

}



