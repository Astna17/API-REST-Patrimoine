package com.api.rest.patrimoine.Service;

import com.api.rest.patrimoine.Model.Patrimoine;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PatrimoineService {
    private final Map<String, Patrimoine> patrimoines = new HashMap<>();

    public Patrimoine createOrUpdatePatrimoine (String id, Patrimoine patrimoine) {
        patrimoines.put(id, patrimoine);
        return patrimoine;
    }

    public Patrimoine getPatrimoineById (String id) {
        return patrimoines.get(id);
    }

}
