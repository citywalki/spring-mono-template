package pro.walkin.framework.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
public class VersionControlService {

    @Setter(onMethod_ = {@Autowired})
    public EntityManager em;

    // public <A extends VersionControl> Optional<A> getVersion(String correlationKey, int version,
    //                                                          Class<A> versionClass) {
    //     CriteriaBuilder cb = em.getCriteriaBuilder();
    //     CriteriaQuery<A> query = cb.createQuery(versionClass);
    //     Root<A> root = query.from(versionClass);
    //     Path<A> correlationKeyPath = root.get("versionCorrelationKey");
    //     Predicate predicateCorrelation = cb.equal(correlationKeyPath, correlationKey);
    //     Path<A> versionPath = root.get("version");
    //     Predicate predicateVersion = cb.equal(versionPath, version);
    //
    //     TypedQuery<A> q = em.createQuery(query.where(cb.and(predicateCorrelation, predicateVersion)));
    //
    //     List<A> results = q.getResultList();
    //
    //     return results.stream().findAny();
    // }

}
