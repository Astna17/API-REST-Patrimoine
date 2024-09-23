package com.api.rest.patrimoine.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Patrimoine {
    private String possesseur;
    private LocalDateTime derniereModification;
}
