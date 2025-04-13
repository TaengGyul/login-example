package com.example.loginapp.love;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import com.example.loginapp.board.Board;
import com.example.loginapp.user.User;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Table(
        name = "love_tb",
        uniqueConstraints = {
                @UniqueConstraint(name = "love_unique_user_board", columnNames = {"user_id", "board_id"})
        }
)
@Entity
public class Love {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Love(Integer id, User user, Board board, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.board = board;
        this.createdAt = createdAt;
    }
}
