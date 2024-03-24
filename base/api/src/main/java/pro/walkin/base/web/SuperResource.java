package pro.walkin.base.web;

import org.springframework.beans.factory.annotation.Autowired;
import pro.walkin.framework.query.AbstractQueries;

public class SuperResource<Q extends AbstractQueries<?, ?>> {
    protected Q query;

    @Autowired
    public void setQuery(Q query) {
        this.query = query;
    }

}
