package org.coursesjava.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Game {
    private int id;
    private String name;
    private LocalDate releaseDate;
    private int rating;
    private int cost;
    private String description;
}
