package com.freelec.book.springboot.web;

import com.freelec.book.springboot.config.auth.dto.SessionUser;
import com.freelec.book.springboot.domain.user.User;
import com.freelec.book.springboot.serviece.posts.PostsService;
import com.freelec.book.springboot.web.dto.PostsResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user"); //앞서 작성된 CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser 를 저장하도록 구현
        //즉, 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있음

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);

        return "posts-update";
    }
}
