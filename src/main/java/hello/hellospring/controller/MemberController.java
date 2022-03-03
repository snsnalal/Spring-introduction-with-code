package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //객체 생성
public class MemberController {

    private final MemberService memberService;

     //@Autowired private MemberService memberService; // 필드 주입

    /*@Autowired //setter 주입, 퍼블릭으로 열려있어야함 안좋음
    public void setMemberService(MemberService memberService)
    {
        this.memberService = memberService;
    }*/

    @Autowired//스프링 컨테이너랑 연결 시켜줌 //생성자 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm()
    {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form)
    {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //홈으로 보냄
    }

    @GetMapping("/members")
    public String list(Model model)
    {
        List<Member> members = memberService.findByMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }

}
