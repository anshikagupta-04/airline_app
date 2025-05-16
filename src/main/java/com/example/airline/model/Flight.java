package com.example.airline.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Flight")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Flight {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String source;
    private String destination;
}
