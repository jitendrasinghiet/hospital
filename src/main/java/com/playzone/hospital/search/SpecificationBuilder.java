package com.playzone.hospital.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.playzone.hospital.entity.Hospital;

public class SpecificationBuilder {

	private final List<SearchCriteria> params;

    public SpecificationBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public SpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Hospital> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
          .map(HospitalSpecification::new)
          .collect(Collectors.toList());
        
        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
              .isOrPredicate()
                ? Specification.where(result)
                  .or(specs.get(i))
                : Specification.where(result)
                  .and(specs.get(i));
        }       
        return result;
    }
}
