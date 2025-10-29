package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService;

    //    @Autowired private MemberService memberService; //필드 주입 방식, 별로 안좋음 중간에 뭘 바꿀 수 없음.
//    @Autowired //setter 방식 처음에만 사용하지 이후에도 public일 필요가 없기 때문에 잘 안씀
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//        memberService.setMemberRepository(); // 이후에 계속 조회 가능한 것이 문제 처음에만 생성딱 하고 최대한 호출 안하는 것이 좋음
//    }

    @Autowired // controller는 어쩔 수 없이 오토와이어 사용 컴포넌트 스캔 방식, not 직접 코드로 등록 ex. config 파일
    public MemberController(MemberService memberService) { // DI 주입 방식, 필드, setter, 생성자 주입 있음, 여기서는 생성자로 멤버 서비스가 멤버 컨트롤러에 주입됨
        this.memberService = memberService; //의존 관계가 실제로 동작 중에 변하는 경우가 거의 없으므로 생성자 주입 권장.
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
