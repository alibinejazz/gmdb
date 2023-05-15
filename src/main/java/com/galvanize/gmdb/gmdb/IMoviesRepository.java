package com.galvanize.gmdb.gmdb;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IMoviesRepository extends JpaRepository<Movies,Long>{

    Object findById(int i);

    // void update(Movies movies);

    
}
