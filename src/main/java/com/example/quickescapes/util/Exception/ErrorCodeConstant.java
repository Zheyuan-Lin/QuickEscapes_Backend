package com.example.quickescapes.util.Exception;

public interface ErrorCodeConstant {

        ErrorCode SUCCESS = new ErrorCode(0, "Success");
        ErrorCode BAD_REQUEST = new ErrorCode(400, "Request Param incorrect");
        ErrorCode UNAUTHORIZED = new ErrorCode(401, "Please login first");
        ErrorCode FORBIDDEN = new ErrorCode(403, "Do not have access");
        ErrorCode NOT_FOUND = new ErrorCode(404, "Request did not exist");
        ErrorCode METHOD_NOT_ALLOWED = new ErrorCode(405, "Incorrect request method");
        ErrorCode LOCKED = new ErrorCode(423, "Request failed"); // 并发请求，不允许
        ErrorCode TOO_MANY_REQUESTS = new ErrorCode(429, "request has more than one");


    }

