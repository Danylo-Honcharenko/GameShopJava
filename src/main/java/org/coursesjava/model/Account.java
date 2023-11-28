package org.coursesjava.model;

import lombok.Data;

@Data
public class Account {
    private int id;
    private int amount;
    private String type;
    private int user_id;
}
