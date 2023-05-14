package com.galvanize.gmdb.gmdb;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class MoviesRepository
{
    private Map<Integer, Movies> repo;

    public Collection<Movies> getAllMovies() {
        return repo.values();
    }
    
}
