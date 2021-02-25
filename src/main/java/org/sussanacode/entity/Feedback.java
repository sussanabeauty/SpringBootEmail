package org.sussanacode.entity;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class Feedback {
    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Min(10)
    private String reply;
}
