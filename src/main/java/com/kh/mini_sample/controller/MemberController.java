package com.kh.mini_sample.controller;
import com.kh.mini_sample.dao.MemberDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/* CrossOrigin :
    프론트엔드, 백엔드 포트가 다를 경우 반드시 넣어줘야 함. (브라우저가 해킹으로 간주해서 튕겨냄)
   '3000번 포트를 허용해달라'는 의미 😁*/
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MemberController {
    // 1️⃣ POST : 로그인
    @PostMapping("/login")
    // @RequestBody는 AxiosApi.js - memberLogin 객체의 login 객체의 key, value
    public ResponseEntity<Boolean> memberLogin(@RequestBody Map<String, String> loginData) {
        // Map은 get(key)로 불러오고, FE에서 넘어올 때는 JSON으로 오기 때문에 문자열로 들어옴.
        String id = loginData.get("id");
        String pwd = loginData.get("pwd");
        MemberDAO dao = new MemberDAO();
        boolean result = dao.loginCheck(id, pwd);
        return new ResponseEntity<>(result, HttpStatus.OK); // DB 다녀오기 전에 잘 넘어오는지 확인해보는 디버깅 코드
    }
}
