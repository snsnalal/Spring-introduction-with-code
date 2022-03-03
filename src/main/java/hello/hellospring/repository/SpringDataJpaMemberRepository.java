package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //스프링 데이터 jpa가 구현체 자동으로 생성하고 빈에 자동으로 등록

    //findBy~~로 만들면 select m from Member m where m.name = ? 로 만들어줌
    @Override
    Optional<Member> findByName(String name);
}
