package br.edu.ifsc.chamados.specifications;

import br.edu.ifsc.chamados.models.call.Call;
import br.edu.ifsc.chamados.models.call.CallCategory;
import br.edu.ifsc.chamados.models.call.CallPriority;
import br.edu.ifsc.chamados.models.call.CallStatus;
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

            if (filter.getStatusWeight() != null) {
                Join<Call, CallStatus> joinStatus = root.join(Call.STATUS, JoinType.LEFT);
                preds.add(cb.equal(joinStatus.get(CallStatus.WEIGHT), filter.getStatusWeight()));
            }

            if (filter.getPriorityID() != null) {
                Join<Call, CallCategory> joinStatus = root.join(Call.CATEGORY, JoinType.LEFT);
                Join<CallCategory, CallPriority> joinpPriority = joinStatus.join(CallCategory.PRIORITY, JoinType.LEFT);
                preds.add(cb.equal(joinpPriority.get(CallPriority.WEIGHT), filter.getStatusWeight()));
            }
            if (filter.getCallCategoryID() != null) {
                Join<Call, CallCategory> joinStatus = root.join(Call.CATEGORY, JoinType.LEFT);
                preds.add(cb.equal(joinStatus.get(CallCategory.ID), filter.getCallCategoryID()));
            }

           if (filter.getRequesterEmail() != null && !filter.getRequesterEmail().isBlank()) {
                Join<Call, User> joinStatus = root.join(Call.REQUESTER, JoinType.LEFT);
                preds.add(cb.equal(joinStatus.get(User.EMAIL), filter.getRequesterEmail()));
           }

           if (filter.getSolverEmail() != null && !filter.getSolverEmail().isBlank()) {
                Join<Call, User> joinStatus = root.join(Call.SOLVER, JoinType.LEFT);
                preds.add(cb.equal(joinStatus.get(User.EMAIL), filter.getSolverEmail()));
           }
        }

        return query.where(cb.and(preds.toArray(new Predicate[preds.size()]))).getRestriction();
    }
}
