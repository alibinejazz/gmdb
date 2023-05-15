package com.galvanize.gmdb.gmdb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MoviesController {
    @Autowired
    private  IMoviesRepository repo;
    @PostMapping("")  
    public void createMovies(@RequestBody Movies movie) {
        this.repo.save(movie);
    }

    @GetMapping("all")
    public List<Movies> getAllMovies(){
        return this.repo.findAll();
    }
    @GetMapping("{id}")
    public Movies getMOvie(@PathVariable Long id){
        return this.repo.findById(id).orElse(null);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.repo.deleteById(id);
    }
    // @PutMapping("/update")
    // public void update(@RequestBody Movies movies) {
    //     this.repo.update(movies);
    // }
}
