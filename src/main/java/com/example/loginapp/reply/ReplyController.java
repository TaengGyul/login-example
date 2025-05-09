package com.example.loginapp.reply;

import com.example.loginapp._core.ex.ExceptionApi401;
import com.example.loginapp.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;

    @PostMapping("/reply/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new ExceptionApi401("인증이 필요합니다");

        int boardId = replyService.댓글삭제(id, sessionUser.getId());

        return "redirect:/board/" + boardId;
    }

    @PostMapping("/reply/save")
    public String Save(ReplyRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new ExceptionApi401("인증이 필요합니다");

        replyService.댓글쓰기(reqDTO, sessionUser);

        return "redirect:/board/" + reqDTO.getBoardId();
    }
}