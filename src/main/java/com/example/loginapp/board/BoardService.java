package com.example.loginapp.board;

import com.example.loginapp._core.ex.Exception403;
import com.example.loginapp._core.ex.Exception404;
import com.example.loginapp.love.Love;
import com.example.loginapp.love.LoveRepository;
import com.example.loginapp.reply.ReplyRepository;
import com.example.loginapp.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final LoveRepository loveRepository;
    private final ReplyRepository replyRepository;

    // TODO
    @Transactional
    public void 글수정하기(BoardRequest.UpdateDTO reqDTO, Integer boardId, Integer sessionUserId) {
        Board boardPS = boardRepository.findById(boardId);

        if (boardPS == null) throw new Exception404("자원을 찾을 수 없습니다.");

        if (!boardPS.getUser().equals(sessionUserId)) throw new Exception403("권한이 없습니다.");

        boardPS.update(reqDTO.getTitle(), reqDTO.getContent(), reqDTO.getIsPublic());
    } // 더티 체킹 (상태변경해서 update)

    public void 글삭제하기() {

    }


    public List<Board> 글목록보기(Integer userId) {
        if (userId == null) {
            return boardRepository.findAll();
        } else {
            return boardRepository.findAll(userId);
        }
    }

    @Transactional
    public void 글쓰기(BoardRequest.SaveDTO saveDTO, User sessionUser) {
        Board board = saveDTO.toEntity(sessionUser);
        boardRepository.save(board);
    }

    public BoardResponse.DetailDTO 글상세보기(Integer id, Integer sessionUserId) {
        Board board = boardRepository.findByIdJoinUserAndReplies(id);

        Love love = loveRepository.findByUserIdAndBoardId(sessionUserId, id);
        Long loveCount = loveRepository.findByBoardId(id);

        Integer loveId = love == null ? null : love.getId();
        Boolean isLove = love == null ? false : true;


        BoardResponse.DetailDTO detailDTO = new BoardResponse.DetailDTO(board, sessionUserId, isLove, loveCount.intValue(), loveId);
        return detailDTO;
    }


    public Board 업데이트글보기(int id, Integer sessionUserId) {
        Board boardPS = boardRepository.findById(id);
        if (boardPS == null) throw new Exception404("자원을 찾을 수 없습니다.");

        if (!boardPS.getUser().getId().equals(sessionUserId)) {
            throw new Exception403("권한이 없습니다.");
        }
        return boardPS;
    }
}
