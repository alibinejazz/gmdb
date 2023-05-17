package com.galvanize.gmdb.gmdb;

import org.hibernate.query.sqm.sql.internal.PluralValuedSimplePathInterpretation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviewers")
public class ReviewerConroller {
    @Autowired
    private IReviewerRepository repo;

    @GetMapping("/all")
    public List<Reviewer> getAllUser() {
        return this.repo.findAll();
    }
    @PostMapping("")
    public void createReviewer(@RequestBody Reviewer reviewer) {
        repo.save(reviewer);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        this.repo.deleteById(id);;
    }
}