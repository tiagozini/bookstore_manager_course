package dto;

import com.tiagozinidev.bookstoremanager.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;

    @NotBlank
    @Size(max=200)
    private String name;

    @NotNull
    private Integer pages;

    @NotNull
    private Integer chapters;

    @Pattern(regexp = "[0-9]-[0-9]{3}-[0-9]{5}-[0-9]")
    @NotBlank
    @Size(max=100)
    private String isbn;

    @NotBlank
    @Size(max=200)
    private String publisherName;

    @Valid
    @NotNull
    private AuthorDTO author;
}
