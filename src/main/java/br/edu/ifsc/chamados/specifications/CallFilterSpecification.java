package br.edu.ifsc.chamados.specifications;

import br.edu.ifsc.chamados.models.call.*;
import br.edu.ifsc.chamados.models.user.User;
import br.edu.ifsc.chamados.requests.CallRequestFilter;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CallFilterSpecification implements Specification<Call> {

    private CallRequestFilter filter;

    @Override
    public Predicate toPredicate(Root<Call> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        List<Predicate> preds = new ArrayList<>();

        if (filter != null) {

            Optional.ofNullable(filter.getInicio()).ifPresent(
                localDate -> preds.add(cb.greaterThanOrEqualTo(root.get(Call.CREATIONDATE), localDate))
            );

            Optional.ofNullable(filter.getFim()).ifPresent(
                localDate -> preds.add(cb.lessThanOrEqualTo(root.get(Call.CREATIONDATE), localDate))
            );

            if (filter.getStatus() != null && !filter.getStatus().isEmpty()) {
                Join<Call, CallStatus> joinStatus = root.join(Call.STATUS, JoinType.LEFT);
                preds.add(joinStatus.get(CallStatus.ID).in(filter.getStatus()));
            }

            if (filter.getCategories() != null && !filter.getCategories().isEmpty()) {
                Join<Call, CallCategory> joinCategory = root.join(Call.CATEGORY, JoinType.LEFT);
                preds.add(joinCategory.get(CallCategory.ID).in(filter.getCategories()));
            }

            if (filter.getPriorities() != null && !filter.getPriorities().isEmpty()) {
                Join<Call, CallCategory> joinCategory = root.join(Call.CATEGORY, JoinType.LEFT);
                Join<CallCategory, CallPriority> joinPriority = joinCategory.join(CallCategory.PRIORITY, JoinType.LEFT);
                preds.add(joinPriority.get(CallPriority.ID).in(filter.getPriorities()));
            }

            if (filter.getSectors() != null && !filter.getSectors().isEmpty()) {
                Join<Call, CallCategory> joinCategory = root.join(Call.CATEGORY, JoinType.LEFT);
                Join<CallCategory, CallSector> joinSector = joinCategory.join(CallCategory.SECTOR, JoinType.LEFT);
                preds.add(joinSector.get(CallCategory.ID).in(filter.getSectors()));
            }

           if (filter.getRequester() != null && !filter.getRequester().isBlank()) {
                Join<Call, User> joinStatus = root.join(Call.REQUESTER, JoinType.LEFT);
                preds.add(cb.equal(joinStatus.get(User.EMAIL), filter.getRequester()));
           }

           if (filter.getSolver() != null && !filter.getSolver().isBlank()) {
                Join<Call, User> joinStatus = root.join(Call.SOLVER, JoinType.LEFT);
                preds.add(cb.equal(joinStatus.get(User.EMAIL), filter.getSolver()));
           }
        }

        return query.where(cb.and(preds.toArray(new Predicate[preds.size()]))).getRestriction();
    }
}
