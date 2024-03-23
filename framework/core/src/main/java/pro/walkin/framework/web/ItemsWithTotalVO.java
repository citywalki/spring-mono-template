package pro.walkin.framework.web;

import java.util.Collection;

public record ItemsWithTotalVO(Collection<?> items, long total) {

}
