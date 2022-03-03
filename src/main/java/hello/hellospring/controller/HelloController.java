package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController
{
    @GetMapping("hello")
    public String hello(Model model)
    {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name"/*required = false 값 안넘김 true 면 get 방식으로 주소창에 값 입력*/) String name, Model model)
    {
        model.addAttribute("name", name);

        return "hello-template"; //hello-template.html로 연결 html 방식
    }

    @GetMapping("hello-string")
    @ResponseBody//응답 body 부분에 리턴을 직접 넣음
    public String helloString(@RequestParam("name") String name)
    {
        return "hello" + name; //name이 spring이면 hello spring 그대로
    }

    @GetMapping("hello-api") //json 방식으로 나옴(키:밸류)
    @ResponseBody //기본 방식 객체 리턴 json 반환 객체면 jsonconverter 실행 데이터면 stringconverter 실행
    public Hello helloApi(@RequestParam("name") String name)
    {
        Hello hello = new Hello();
        hello.setName(name);

        return hello;
    }

    static class Hello
    {
        private String name;
        
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
