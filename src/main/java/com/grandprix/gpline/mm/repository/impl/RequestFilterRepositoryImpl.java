package com.grandprix.gpline.mm.repository.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.grandprix.gpline.mm.model.Contact;
import com.grandprix.gpline.mm.model.Message;
import com.grandprix.gpline.mm.model.Request;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.compile.RenderingContext;
import org.hibernate.query.criteria.internal.expression.function.BasicFunctionExpression;
import org.springframework.stereotype.Repository;

import com.grandprix.gpline.mm.model.Contact_;
import com.grandprix.gpline.mm.model.Message_;
import com.grandprix.gpline.mm.model.Request_;
import com.grandprix.gpline.mm.model.filter.RequestFilter;
import com.grandprix.gpline.mm.repository.RequestFilterRepository;

@Repository
public class RequestFilterRepositoryImpl implements RequestFilterRepository {
    @PersistenceContext
    EntityManager em;
    
    @Override
    public List<Request> findByFilter(RequestFilter requestFilter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Request> query = cb.createQuery(Request.class);
        
        Root<Request> root = query.from(Request.class);
        List<Predicate> predicates = new ArrayList<>();
        
        if (requestFilter.getRegDateStart() != null)
            predicates.add(cb.greaterThanOrEqualTo(root.get(Request_.registrationDate), requestFilter.getRegDateStart()));

        if (requestFilter.getRegDateEnd() != null)
            predicates.add(cb.lessThanOrEqualTo(root.get(Request_.registrationDate), requestFilter.getCloseDateEnd()));

        if (requestFilter.getCloseDateStart() != null)
            predicates.add(cb.greaterThanOrEqualTo(root.get(Request_.requestCloseDate), requestFilter.getCloseDateStart()));

        if (requestFilter.getCloseDateEnd() != null)
            predicates.add(cb.lessThanOrEqualTo(root.get(Request_.requestCloseDate), requestFilter.getCloseDateEnd()));

        if (requestFilter.getFilialId() != null)
            predicates.add(cb.equal(root.get(Request_.filialId), requestFilter.getFilialId()));

        if (requestFilter.getOperatorLogin() != null) {
            Subquery<Request> query1 = query.subquery(Request.class);
            Root<Contact> root1 = query1.from(Contact.class);
            query1.select(root1.get(Contact_.request));
            query1.where(cb.equal(root1.get(Contact_.operatorLogin), requestFilter.getOperatorLogin()));
            predicates.add(root.in(query1));
        }

        if (requestFilter.getSupervisorLogin() != null)
            predicates.add(cb.equal(root.get(Request_.supervisorLogin), requestFilter.getSupervisorLogin()));

        if (requestFilter.getChannelId() != null)
            predicates.add(cb.equal(root.get(Request_.channelId), requestFilter.getChannelId()));

        if (requestFilter.getDirection() != null) {
            Subquery<Request> query1 = query.subquery(Request.class);
            Root<Contact> root1 = query1.from(Contact.class);
            query1.select(root1.get(Contact_.request));
            
            Subquery<String> query2 = query1.subquery(String.class);
            Root<Message> root2 = query2.from(Message.class);
            query2.select(root2.get(Message_.contactId));
            query2.where(cb.equal(root2.get(Message_.messageDirection), requestFilter.getDirection()));

            query1.where(root1.get(Contact_.id).in(query2));
            predicates.add(root.get(Request_.id).in(query1));
        }

        if (requestFilter.getRequestStatus() != null)
            predicates.add(cb.equal(root.get(Request_.requestStatus), requestFilter.getRequestStatus()));

        if (requestFilter.getMessageText() != null) {
            Subquery<Request> query1 = query.subquery(Request.class);
            Root<Contact> root1 = query1.from(Contact.class);
            query1.select(root1.get(Contact_.request));
            
            Subquery<String> query2 = query1.subquery(String.class);
            Root<Message> root2 = query2.from(Message.class);
            query2.select(root2.get(Message_.contactId));
            query2.where(cb.like(cb.upper(root2.get(Message_.messageText)), requestFilter.getMessageText().toUpperCase()));

            query1.where(root1.get(Contact_.id).in(query2));
            predicates.add(root.get(Request_.id).in(query1));
        }
  
        if (requestFilter.getContactNumber() != null) {
            Subquery<Long> query1 = query.subquery(Long.class);
            Root<Contact> root1 = query1.from(Contact.class);
            query1.where(cb.equal(root1.get(Contact_.request), root.get(Request_.id)));
            predicates.add(cb.equal(query1.select(cb.count(root1)), requestFilter.getContactNumber()));
        }
        
        if (requestFilter.getRequestPriority() != null)
            predicates.add(cb.equal(root.get(Request_.priority), requestFilter.getRequestPriority()));

        if (requestFilter.getTransferTo() != null) {
            Subquery<Request> query1 = query.subquery(Request.class);
            Root<Contact> root1 = query1.from(Contact.class);
            query1.select(root1.get(Contact_.request));
            query1.where(cb.equal(root1.get(Contact_.transferTo), requestFilter.getTransferTo()));
            predicates.add(root.get(Request_.id).in(query1));
        }
        
        if (requestFilter.getTransferExists() != null && requestFilter.getTransferTo() == null) {
            Subquery<Request> query1 = query.subquery(Request.class);
            Root<Contact> root1 = query1.from(Contact.class);
            query1.select(root1.get(Contact_.request));
            if (requestFilter.getTransferExists())
                query1.where(root1.get(Contact_.transferTo).isNotNull());
            else 
                query1.where(cb.and(root1.get(Contact_.transferTo).isNull()), root1.get(Contact_.head).isNull());
            predicates.add(root.get(Request_.id).in(query1));
        }
        
        if (requestFilter.getDurationStart() != null || requestFilter.getDurationEnd() != null) {
            long t1, t2;
            if (requestFilter.getDurationStart() == null) {
                t2 = requestFilter.getDurationEnd();
                t1 = t2;
            } else if (requestFilter.getDurationEnd() == null) {
                t1 = requestFilter.getDurationStart();
                t2 = t1;
            } else {
                t1 = requestFilter.getDurationStart();
                t2 = requestFilter.getDurationEnd();
            }
            Subquery<Request> query1 = query.subquery(Request.class);
            Root<Contact> root1 = query1.from(Contact.class);
            query1.select(root1.get(Contact_.request));
            query1.where(cb.and(cb.between(root1.get(Contact_.duration), t1, t2), root1.get(Contact_.head).isNotNull()));
            predicates.add(root.get(Request_.id).in(query1));
        }
        
        query.where(predicates.toArray(new Predicate[0]));
        
        return em.createQuery(query).getResultList();
    }
    
    public class UnitExpression extends BasicFunctionExpression<String> implements Serializable {
        private static final long serialVersionUID = 1L;

        public UnitExpression(CriteriaBuilderImpl criteriaBuilder, Class<String> javaType, String functionName) {
            super(criteriaBuilder, javaType, functionName);
        }

        @Override
        public String render(RenderingContext renderingContext) {
            return getFunctionName();
        }
    }
}
