//package com.example.loginapp._core.error;
//
//import com.example.loginapp._core.ex.ExceptionApi400;
//import com.example.loginapp._core.ex.ExceptionApi401;
//import com.example.loginapp._core.ex.ExceptionApi403;
//import com.example.loginapp._core.ex.ExceptionApi404;
//import com.example.loginapp._core.util.Resp;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice // @ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(ExceptionApi400.class)
//    public ResponseEntity<?> exApi400(ExceptionApi400 e) {
//        Resp<?> resp = Resp.fail(400, e.getMessage());
//        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(ExceptionApi401.class)
//    public ResponseEntity<?> exApi401(ExceptionApi401 e) {
//        Resp<?> resp = Resp.fail(401, e.getMessage());
//        return new ResponseEntity<>(resp, HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(ExceptionApi403.class)
//    public ResponseEntity<?> exApi403(ExceptionApi403 e) {
//        Resp<?> resp = Resp.fail(403, e.getMessage());
//        return new ResponseEntity<>(resp, HttpStatus.FORBIDDEN);
//    }
//
//    @ExceptionHandler(ExceptionApi404.class)
//    public ResponseEntity<?> exApi404(ExceptionApi404 e) {
//        Resp<?> resp = Resp.fail(404, e.getMessage());
//        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> exUnKnown(Exception e) {
//        System.out.println("관리자님 보세요 : " + e.getMessage()); // 로그를 파일에 기록해서 나중에 봐야함
//        Resp<?> resp = Resp.fail(500, "관리자에게 문의하세요");
//        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}