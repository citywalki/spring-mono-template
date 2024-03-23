package pro.walkin.framework.service;

// @Transactional
public class VersionControlService {

    // @Setter(onMethod_ = {@Autowired})
    // public EntityManager em;
    //
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
