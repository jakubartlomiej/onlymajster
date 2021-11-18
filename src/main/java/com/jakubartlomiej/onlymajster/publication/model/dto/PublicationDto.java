package com.jakubartlomiej.onlymajster.publication.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class PublicationDto {
    @Setter
    private long id;
    @Length(min = 8)
    private String subject;
    @Length(min = 8)
    private String text;
    private String photo;
    @NotNull
    @Min(value = 1)
    private long categoryId;
}