package com.project.library.repository;

import com.project.library.model.Department;
import com.project.library.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
{
    Optional<Member> findByCms(long cms);
    List<Member> findByNameContainingIgnoreCase(String name);
    List<Member> findByDepartment(Department department);
}
