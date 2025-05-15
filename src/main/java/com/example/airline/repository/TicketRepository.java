package com.example.airline.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.airline.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {}

