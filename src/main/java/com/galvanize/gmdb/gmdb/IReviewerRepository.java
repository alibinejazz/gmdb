package com.galvanize.gmdb.gmdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReviewerRepository extends JpaRepository<Reviewer, Long> {
}