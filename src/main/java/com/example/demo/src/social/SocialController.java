//package com.example.demo.src.social;
//
//
//import lombok.RequiredArgsConstructor;
//import org.hibernate.cfg.Environment;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@Controller
//@RequestMapping("/kakao")
//public class SocialController {
//    private KakaoLogin kakao_restapi = new KakaoLogin();
//
//    @GetMapping(value = "/oauth")
//    public String kakaoConnect() {
//        StringBuffer url = new StringBuffer();
//        url.append("https://kauth.kakao.com/oauth/authorize?");
//        url.append("client_id=" + "06b245eaa08eb0933cd03aaeec68dcf5");
//        url.append("&redirect_uri=http://localhost:8080/social/kakao");
//        url.append("&response_type=code");
//
//        return "redirect:" + url.toString();
//    }
//
//    @RequestMapping(value = "/kakao", produces = "application/json", method = {RequestMethod.GET,
//            RequestMethod.POST})
//    public String kakaoLogin(@RequestParam("code") String code, RedirectAttributes ra, HttpSession session,
//                             HttpServletResponse response, Model model)throws IOException{
//        System.out.println("kakao code" + code);
//    }
//}
