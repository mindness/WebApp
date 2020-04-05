package com.mindness.webappback.repository;


import com.mindness.webappback.model.Film;
import com.mindness.webappback.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SerieRepository extends JpaRepository<Serie, Long>{

}
