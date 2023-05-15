package com.galvanize.gmdb.gmdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Reviewss")
public class ReviewController {
    @Autowired
    private IReviewsRepository repo;
    @PostMapping("")  
    public void createMovies(@RequestBody Review review) {
        this.repo.save(review);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.repo.deleteById(id);
    }
}
