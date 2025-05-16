package com.example.airline.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Books")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Ticket {
    @Id @GeneratedValue
    private Long id;
    private String passengerName;

    @ManyToOne
    private Schedule schedule;

    private boolean cancelled = false;

    public Schedule getSchedule(){
        return schedule;
    }
}
