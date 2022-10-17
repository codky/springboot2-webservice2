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

    // 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // 기존에 (User)httpSession.getAttribute("user")로 가져오던 세션정보 개선, 어느 컨트롤러에서도 @LoginUser로 세션정보를 사용 가능
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) { // 세션에 저장된 값이 있을 때만 model에 userName으로 등록. 세션에 저장된 값이 없으면 model엔 아무런 값도 없기때문에 로그인 버튼 노출
            model.addAttribute("userName", user.getName());
        }
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
