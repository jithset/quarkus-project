package io.github.jithinsethu.hibernatevalidation.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
public class BookCreateDTO {

    @Getter @Setter
    @NotBlank(message = "Book name required")
    public String bookName;

    @Getter @Setter
    @NotBlank(message = "Author name required")
    public String authorName;
}
