package io.github.jithinsethu.hibernatevalidation.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
public class ResponseBodyDTO {
    @Getter @Setter
    public String message;

    @Getter @Setter
    public Boolean status;

    @Getter @Setter
    public Object data;

    public ResponseBodyDTO(Set<? extends ConstraintViolation<?>> violations) {
        this.status = false;
        this.message = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
    }
}
