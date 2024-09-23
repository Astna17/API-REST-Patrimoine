package com.api.rest.patrimoine.Controller;

import com.api.rest.patrimoine.Model.Patrimoine;
import com.api.rest.patrimoine.Service.PatrimoineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patrimoines")
public class PatrimoineController {
    private final PatrimoineService patrimoineService;

    public PatrimoineController(PatrimoineService patrimoineService) {
        this.patrimoineService = patrimoineService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patrimoine> createOrUpdatePatrimoine(@PathVariable String id,
                                                               @RequestBody Patrimoine patrimoine) {
            if (patrimoine.getPossesseur() == null || patrimoine.getPossesseur().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            Patrimoine updatedPatrimoine = patrimoineService.createOrUpdatePatrimoine(id, patrimoine);
            return ResponseEntity.ok(updatedPatrimoine);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patrimoine> patrimoineParId(@PathVariable String id) {
        Patrimoine patrimoine = patrimoineService.getPatrimoineById(id);
        if (patrimoine == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(patrimoine);
    }

}
