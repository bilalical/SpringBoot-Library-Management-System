package com.project.library.repository;

import com.project.library.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long>
{
    List<Issue> findByIssuedToMemberID(long memberID);
    List<Issue> findByBorrowedBookBookID(long bookID);
    List<Issue> findByReturnedFalse();

}
