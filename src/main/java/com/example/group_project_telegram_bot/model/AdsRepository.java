/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:03.07.2024
 * TIME:10:44
 */
package com.example.group_project_telegram_bot.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdsRepository extends JpaRepository<Ads,Long> {
}
