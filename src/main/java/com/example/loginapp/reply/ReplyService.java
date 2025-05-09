package com.example.loginapp.reply;

import com.example.loginapp._core.ex.ExceptionApi403;
import com.example.loginapp._core.ex.ExceptionApi404;
import com.example.loginapp.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    @Transactional
    public void 댓글쓰기(ReplyRequest.SaveDTO reqDTO, User sessionUser) {
        replyRepository.save(reqDTO.toEntity(sessionUser));
    }

    @Transactional
    public Integer 댓글삭제(Integer id, Integer sessionUserId) {
        Reply replyPS = replyRepository.findById(id);

        // Exception404
        if (replyPS == null) throw new ExceptionApi404("존재하지 않는 리소스입니다.");

        if (!replyPS.getUser().getId().equals(sessionUserId)) {
            throw new ExceptionApi403("권한이 없습니다.");
        }

        int boardId = replyPS.getBoard().getId();
        System.out.println("삭제 전 : " + boardId);

        replyRepository.deleteById(id);

        int boardId2 = replyPS.getBoard().getId();
        System.out.println("삭제 후 : " + boardId);

        return boardId;
    }
}
