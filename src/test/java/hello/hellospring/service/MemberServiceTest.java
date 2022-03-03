package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    /*MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    이렇게 하면 다른 곳에 있는 repository를 사용하므로 문제있음*/

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach()
    {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach //테스트 하나 끝나면 실행
    public void afterEach()
    {
        memberRepository.clearStore(); //메모리 정리
    }

    @Test
    void join() {
        //given 뭔가가 주어졌을떄
        Member member = new Member();
        member.setName("hello");

        //when 실행 하면
        Long saveId = memberService.join(member);

        //then 어떻게 될지
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복회원예외()
    {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//예외 처리 뒤 로직이 실행될떄 앞 에러처리
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*
        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e)
        {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.")
        }*/

        //then
    }

    @Test
    void findByMembers() {
    }

    @Test
    void findOne() {
    }
}