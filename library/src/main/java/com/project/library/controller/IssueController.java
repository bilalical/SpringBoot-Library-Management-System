package com.project.library.controller;

import com.project.library.model.Issue;
import com.project.library.service.IssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/issue")
public class IssueController
{
    private final IssueService service;
    IssueController(IssueService service) {this.service = service;}

    @GetMapping
    public List<Issue> getAllIssues()
    {
        return service.fetchIssues();
    }

    @GetMapping("/member/{id}")
    public List<Issue> getIssuesByMember(@PathVariable long id)
    {
        return service.fetchIssuesByMember(id);
    }

    @GetMapping("/book/{id}")
    public List<Issue> getIssuesByBook(@PathVariable long id)
    {
        return service.fetchIssuesByBook(id);
    }

    @PostMapping ("/{memberID}/{bookID}")
    public ResponseEntity<Issue> borrowBook(@PathVariable long memberID, @PathVariable long bookID)
    {
        Optional<Issue> result = service.createIssue(memberID, bookID);

        if(result.isEmpty())
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(result.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Issue> returnBook(@PathVariable long id)
    {
        Optional<Issue> result = service.returnBook(id);

        if(result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result.get());
    }

}
