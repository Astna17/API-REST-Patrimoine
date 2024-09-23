package com.api.rest.patrimoine.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patrimoine {
    private String possesseur;
    private LocalDateTime derniereModification;

}
