package com.project.library.controller;

import com.project.library.model.Department;
import com.project.library.model.Member;
import com.project.library.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*")


public class MemberController
{
    private final MemberService service;
    MemberController(MemberService service) {this.service = service;}

    @GetMapping
    public List<Member> getAllMembers()
    {
        return service.fetchMembers();
    }

    @GetMapping("/search")
    public List<Member> findMembers(@RequestParam String query)
    {
        return service.searchMember(query);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMember(@PathVariable long id)
    {
        Optional<Member> result = service.searchMemberbyId(id);
        if(result.isEmpty())
            return ResponseEntity.notFound().build();

        Member m = result.get();
        return ResponseEntity.ok(m);
    }

    @GetMapping("/dept/{department}")
    public List<Member> getMembersByDepartment(@PathVariable Department department)
    {
        return service.fetchMembersByDepartment(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable long id, @RequestBody Member updatedMember)
    {
        Optional<Member> result = service.updateMember(id, updatedMember);
        if(result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable long id)
    {
        Optional<String> message = service.dropMember(id);
        if(message.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(message.get());
    }

    @PostMapping
    public Member addMember(@RequestBody Member m)
    {
        return service.postMember(m);
    }

    @PostMapping("/add")
    public List<Member> addMemberList(@RequestBody List<Member> members)
    {
        return service.postMemberList(members);
    }

}
