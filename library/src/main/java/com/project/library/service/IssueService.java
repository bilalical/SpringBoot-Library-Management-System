package com.project.library.service;

import com.project.library.model.Book;
import com.project.library.model.Issue;
import com.project.library.model.Member;
import com.project.library.repository.BookRepository;
import com.project.library.repository.IssueRepository;
import com.project.library.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Service
public class IssueService
{
    private final IssueRepository irepo;
    private final BookRepository brepo;
    private final MemberRepository mrepo;
    IssueService(IssueRepository irepo, BookRepository brepo, MemberRepository mrepo)
    {
        this.irepo = irepo;
        this.brepo = brepo;
        this.mrepo = mrepo;
    }

    public List<Issue> fetchIssues()
    {
        return irepo.findAll();
    }

    @Transactional  // either everything happens, or nothing happens
    public Optional<Issue> createIssue(long memberID, long bookID)
    {
        Optional<Book> bResult = brepo.findById(bookID);
        Optional<Member> mResult = mrepo.findById(memberID);

        if(bResult.isEmpty() || mResult.isEmpty())
        {
            return Optional.empty();
        }

        Book b = bResult.get();
        Member m = mResult.get();

        if(b.getCopies()>0)
        {
            Issue issue = new Issue();
            issue.setIssuedTo(m);
            issue.setBorrowedBook(b);
            issue.setReturned(false);
            issue.setDueDate(LocalDateTime.now().plusDays(7));

            b.setCopies(b.getCopies()-1);
            if(b.getCopies()==0)
                b.setAvailable(false);

            brepo.save(b);

            return Optional.of(irepo.save(issue));
        }

        return Optional.empty();
    }

    @Transactional
    public Optional<Issue> returnBook(long id)
    {
        Optional<Issue> result = irepo.findById(id);
        if(result.isPresent() && !result.get().isReturned())
        {
            Issue i = result.get();
            Book b = i.getBorrowedBook();
            Member m = i.getIssuedTo();

            b.setCopies(b.getCopies() + 1);
            b.setAvailable(true);
            i.setReturned(true);
            brepo.save(b);

            return Optional.of(irepo.save(i));
        }
            return Optional.empty();
    }

    public List<Issue> fetchIssuesByMember(long id)
    {
        return irepo.findByIssuedToMemberID(id);
    }

    public List<Issue> fetchIssuesByBook(long id)
    {
        return irepo.findByBorrowedBookBookID(id);
    }


}
