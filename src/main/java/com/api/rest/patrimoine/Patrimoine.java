package com.api.rest.patrimoine;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Patrimoine {
    private String possesseur;
    private LocalDateTime derniereModification;
}
