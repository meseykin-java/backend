package com.grandprix.gpline.mm.repository;

import java.util.List;

import com.grandprix.gpline.mm.model.Request;
import com.grandprix.gpline.mm.model.filter.RequestFilter;

public interface RequestFilterRepository {

    public List<Request> findByFilter(RequestFilter requestsFilter);

}
