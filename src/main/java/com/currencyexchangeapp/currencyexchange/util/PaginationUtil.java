package com.currencyexchangeapp.currencyexchange.util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.List;

public class PaginationUtil {
    public static <T> Page<T> paginateList(Pageable pageable, List<T> list) {
        int first = Math.min(Long.valueOf(pageable.getOffset()).intValue(), list.size());
        int last = Math.min(first + pageable.getPageSize(), list.size());

        return new PageImpl<>(list.subList(first, last), pageable, list.size());
    }
}
