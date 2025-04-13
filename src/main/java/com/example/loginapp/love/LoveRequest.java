package com.example.loginapp.love;

import lombok.Data;
import com.example.loginapp.board.Board;
import com.example.loginapp.user.User;

public class LoveRequest {

    @Data
    public static class SaveDTO {
        private Integer boardId;

        public Love toEntity(Integer sessionUser) {
            return Love.builder()
                    .board(Board.builder().id(boardId).build())
                    .user(User.builder().id(sessionUser).build())
                    .build();
        }
    }
}
