package io.github.jithinsethu.superhero.utils.dtos;

public class ResponseDTO {

    public String message;

    public Boolean status;

    public Object data;

    public ResponseDTO() {
    }

    public ResponseDTO(String message, Boolean status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public ResponseDTO(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
