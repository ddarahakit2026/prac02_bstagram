package org.example.prac02_bstagram.common.exception;

import org.example.prac02_bstagram.common.model.BaseResponseStatus;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{
    private BaseResponseStatus status;

    public BaseException(BaseResponseStatus status) {
        super(status.getMessage());
        this.status = status;
    }

    public static BaseException from(BaseResponseStatus status) {
        return new BaseException(status);
    }
}
