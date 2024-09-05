package com.system.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.training.model.Review;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{

}
