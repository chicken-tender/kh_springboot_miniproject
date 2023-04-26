package com.kh.mini_sample.controller;
import com.kh.mini_sample.dao.MemberDAO;
import com.kh.mini_sample.vo.MemberVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    // GET : 회원 조회
    @GetMapping("/member")
    public ResponseEntity<List<MemberVO>> memberList(@RequestParam String name) {
        MemberDAO mdao = new MemberDAO();
        List<MemberVO> list = mdao.memberSelect();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    // GET : 가입여부 확인
    @GetMapping("/check")
    public ResponseEntity<Boolean> memberCheck(@RequestParam String id) {
        MemberDAO dao = new MemberDAO();
        boolean isTrue = dao.regMemberCheck(id);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    // POST : 회원 가입
    @PostMapping("/new")
    public ResponseEntity<Boolean> memberRegister(@RequestBody Map<String, String> regData) {
        String getId = regData.get("id");
        String getPwd = regData.get("pwd");
        String getName = regData.get("name");
        String getMail = regData.get("mail");
        MemberDAO dao = new MemberDAO();
        boolean isTrue = dao.memberRegister(getId, getPwd, getName, getMail);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }
}
