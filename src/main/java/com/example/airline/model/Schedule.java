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
public class Schedule {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String source;
    private String destination;
}
