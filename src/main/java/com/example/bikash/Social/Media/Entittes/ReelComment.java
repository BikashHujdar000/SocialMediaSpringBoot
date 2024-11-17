package com.example.bikash.Social.Media.Entittes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReelComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String comment;
    private LocalDateTime createdAt;

    @ManyToOne
    private  User user;

    @ManyToOne
    private  Reel reel;







}
