package com.github.bohnman.squiggly.context.provider;

import com.github.bohnman.squiggly.name.AnyDeepName;
import com.github.bohnman.squiggly.parser.SquigglyParser;
import net.jcip.annotations.ThreadSafe;

/**
 * Provider implementation that just takes a fixed filter expression.
 */
@ThreadSafe
public class SimpleSquigglyContextProvider extends AbstractSquigglyContextProvider {

    private final String filter;

    public SimpleSquigglyContextProvider(SquigglyParser parser, String filter) {
        super(parser);
        this.filter = filter;
    }

    @Override
    public boolean isFilteringEnabled() {
        return filter != null && !AnyDeepName.ID.equals(filter);
    }

    @Override
    protected String getFilter(Class beanClass) {
        return filter;
    }

}
