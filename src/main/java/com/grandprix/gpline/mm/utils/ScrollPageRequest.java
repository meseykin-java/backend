package com.grandprix.gpline.mm.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class ScrollPageRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    private final long offset;
    
    @SuppressWarnings("deprecation")
    public ScrollPageRequest(long offset, int size, Sort sort) {
        super((int)offset / size, size, sort);
        this.offset =  offset;
    }
    
    public static ScrollPageRequest of(long offset, int size, Sort sort) {
        return new ScrollPageRequest(offset, size, sort);
    }

    public static ScrollPageRequest of(long offset, int size, Direction direction, String... properties) {
        return of(offset, size, Sort.by(direction, properties));
    }
    
    public ScrollPageRequest nextRow() {
        return of(offset + 1, getPageSize(), getSort());
    }

    public ScrollPageRequest previousRow() {
        return offset == 0 ? this : of(offset - 1, getPageSize(), getSort());
    }
    
    @Override
    public long getOffset() {
        return offset;
    }

}
