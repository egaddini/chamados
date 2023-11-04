package br.edu.ifsc.chamados.specifications;

import br.edu.ifsc.chamados.models.call.*;
import br.edu.ifsc.chamados.requests.CallCategoryFilter;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CallCategorySpecification implements Specification<CallCategory> {

    private CallCategoryFilter filter;

    @Override
    public Predicate toPredicate(Root<CallCategory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        List<Predicate> preds = new ArrayList<>();

        if (filter.getPriority() != null && !filter.getPriority().isEmpty()) {
            Join<Call, CallPriority> joinPriority = root.join(CallCategory.PRIORITY, JoinType.LEFT);
            preds.add(joinPriority.get(CallPriority.ID).in(filter.getPriority()));
        }

        if (filter.getSector() != null && !filter.getSector().isEmpty()) {
            Join<Call, CallSector> joinSector = root.join(CallCategory.SECTOR, JoinType.LEFT);
            preds.add(joinSector.get(CallSector.ID).in(filter.getSector()));
        }

        return query.where(cb.and(preds.toArray(new Predicate[preds.size()]))).getRestriction();
    }

}
