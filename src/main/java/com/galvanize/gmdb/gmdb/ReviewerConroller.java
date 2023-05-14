package com.galvanize.gmdb.gmdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/reviews")
    public List<Reviewer> getAllUser() {
       return this.repo.findAll();
    }
    @PostMapping("")
    public void createReviewer(@RequestBody Reviewer reviewer) {
        repo.save(reviewer);
    }
}