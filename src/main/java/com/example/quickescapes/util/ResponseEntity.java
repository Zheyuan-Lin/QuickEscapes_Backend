package com.example.quickescapes.util;

import com.example.quickescapes.util.Exception.ErrorCode;
import com.example.quickescapes.util.Exception.ErrorCodeConstant;
import com.example.quickescapes.util.Exception.ServiceException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Objects;

@Data
public class ResponseEntity<T> implements Serializable {

        private Integer code;
        private T data;
        private String msg;

        public static <T> ResponseEntity<T> error(ResponseEntity<?> result) {
            return error(result.getCode(), result.getMsg());
        }

        public static <T> ResponseEntity<T> error(Integer code, String message) {
            Assert.isTrue(!ErrorCodeConstant.SUCCESS.getCode().equals(code), "a wrong code");
            ResponseEntity<T> result = new ResponseEntity<>();
            result.code = code;
            result.msg = message;
            return result;
        }

        public static <T> ResponseEntity<T> error(ErrorCode errorCode) {
            return error(errorCode.getCode(), errorCode.getMsg());
        }

        public static <T> ResponseEntity<T> success(T data) {
            ResponseEntity<T> result = new ResponseEntity<>();
            result.code = ErrorCodeConstant.SUCCESS.getCode();
            result.data = data;
            result.msg = "";
            return result;
        }

        public static boolean isSuccess(Integer code) {
            return Objects.equals(code, ErrorCodeConstant.SUCCESS.getCode());
        }

        @JsonIgnore // 避免 jackson 序列化
        public boolean isSuccess() {
            return isSuccess(code);
        }

        @JsonIgnore // 避免 jackson 序列化
        public boolean isError() {
            return !isSuccess();
        }

        public void checkError() throws ServiceException {
            if (isSuccess()) {
                return;
            }
            // 业务异常
            throw new ServiceException(code, msg);
        }


        @JsonIgnore // 避免 jackson 序列化
        public T getCheckedData() {
            checkError();
            return data;
        }

        public static <T> ResponseEntity<T> error(ServiceException serviceException) {
            return error(serviceException.getCode(), serviceException.getMessage());
        }

    }
