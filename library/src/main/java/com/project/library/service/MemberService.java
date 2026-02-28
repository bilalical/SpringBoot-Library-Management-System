package com.project.library.service;

import com.project.library.model.Department;
import com.project.library.model.Member;
import org.springframework.stereotype.Service;
import com.project.library.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService
{
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {this.repository = repository;}

    public List<Member> fetchMembers()
    {
        return repository.findAll();
    }

    public List<Member> fetchMembersByDepartment(Department department)
    {
        return repository.findByDepartment(department);
    }

    public List<Member> searchMember(String query)
    {
        try
        {
            Optional<Member> memberByCms = repository.findByCms(Long.parseLong(query));

            if(memberByCms.isPresent())
            {
                Member m = memberByCms.get();
                return List.of(m);
            }
        } catch(NumberFormatException e)
        {

        }

        List<Member> membersByName = repository.findByNameContainingIgnoreCase(query);
        return membersByName;
    }

    public Optional<Member> searchMemberbyId(long id)
    {
        return repository.findById(id);
    }

    public Optional<String> dropMember(long id)
    {
        Optional<Member> result = repository.findById(id);

        if(result.isEmpty())
            return Optional.empty();

        Member m = result.get();
        repository.delete(m);

        return Optional.of("Member Deleted Successfully! (ID: " + id + ")");
    }

    public Member postMember(Member member)
    {
        return repository.save(member);
    }

    public List<Member> postMemberList(List<Member> members)
    {
        return repository.saveAll(members);
    }

    public Optional<Member> updateMember(long id, Member updatedMember)
    {
        Optional<Member> result = repository.findById(id);
        if(result.isEmpty())
            return Optional.empty();

        Member existingMember = result.get();

        if(updatedMember.getName() != null)
            existingMember.setName(updatedMember.getName());

        if(updatedMember.getDepartment() != null)
            existingMember.setDepartment(updatedMember.getDepartment());

        return Optional.of(repository.save(existingMember));

    }
}
