package com.example.loginapp.board;

import lombok.Data;
import com.example.loginapp.user.User;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private String title;
        private String content;
        private String isPublic;

        public Board toEntity(User user) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .isPublic(isPublic == null ? false : true)
                    .user(user) // user객체 필요
                    .build();
        }
    }

    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
        private String isPublic;

        public Board toEntity(User user) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .isPublic(isPublic == null ? false : true)
                    .user(user) // user객체 필요
                    .build();
        }
    }
}
