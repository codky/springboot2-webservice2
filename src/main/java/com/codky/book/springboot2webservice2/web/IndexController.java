package com.codky.book.springboot2webservice2.web;

import com.codky.book.springboot2webservice2.config.auth.dto.SessionUser;
import com.codky.book.springboot2webservice2.service.posts.PostsService;
import com.codky.book.springboot2webservice2.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) { // 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        model.addAttribute("posts", postsService.findAllDesc());

        // CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성, 즉 로그인 성공시 HttpSession.getAttribute("user")로 값을 가져올 수 있음
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        
        if (user != null) { // 세션에 저장된 값이 있을 때만 model에 userName으로 등록. 세션에 저장된 값이 없으면 model엔 아무런 값도 없기때문에 로그인 버튼 노출
            model.addAttribute("userName", user.getName());
        }
        
        System.out.println("branch test");

        return "index"; // index.mustache
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save"; // posts-save.mustache
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
