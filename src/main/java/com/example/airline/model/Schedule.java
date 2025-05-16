package com.example.airline.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Schedule")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Schedule {
    @Id @GeneratedValue
    private Long id;
    private LocalDate date;

    @ManyToOne
    private Flight flight;
}
