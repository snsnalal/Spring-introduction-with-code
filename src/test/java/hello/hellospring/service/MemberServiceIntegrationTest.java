package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional //테스트 케이스에 달면 테스트를 실행 하면 DB 쿼리문을 실행하고 끝나면 롤백을 해준다.
class MemberServiceIntegrationTest { //테스트 케이스는 편하게

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;



    @Test
    void join() {
        //given 뭔가가 주어졌을떄
        Member member = new Member();
        member.setName("spring");

        //when 실행 하면
        Long saveId = memberService.join(member);

        //then 어떻게 될지
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복회원예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//예외 처리 뒤 로직이 실행될떄 앞 에러처리
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}