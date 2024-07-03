/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:03.07.2024
 * TIME:10:43
 */
package com.example.group_project_telegram_bot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "adsTable")
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ad;
}
