package com.grandprix.gpline.mm.repository.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.grandprix.gpline.mm.model.Contact;
import com.grandprix.gpline.mm.model.Domain;
import com.grandprix.gpline.mm.model.Message;
import com.grandprix.gpline.mm.model.Request;
import com.grandprix.gpline.mm.repository.OperatorRepository;
import com.grandprix.gpline.mm.utils.Log;
import com.grandprix.gpline.mm.utils.MMContstants;
import com.grandprix.gpline.mm.utils.Utils;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.compile.RenderingContext;
import org.hibernate.query.criteria.internal.expression.function.BasicFunctionExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Repository;

import com.grandprix.gpline.mm.model.Contact_;
import com.grandprix.gpline.mm.model.Message_;
import com.grandprix.gpline.mm.model.Request_;
import com.grandprix.gpline.mm.model.filter.ContactFilterPost;
import com.grandprix.gpline.mm.repository.ContactFilterRepository;

@Repository
public class ContactFilterRepositoryImpl implements ContactFilterRepository {
    @Autowired
    OperatorRepository operatorRepository;

    @PersistenceContext
    EntityManager em;

    public ContactFilterRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Long countByFilter(ContactFilterPost contactFilter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Contact> rootContact = cq.from(Contact.class);
        makeQuery(contactFilter, rootContact, cq);
        return em.createQuery(cq.select(cb.count(rootContact))).getSingleResult();
    }

    @Override
    public List<Contact> findByFilter(ContactFilterPost contactFilter, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
        Root<Contact> rootContact = cq.from(Contact.class);
        try {
            cq.orderBy(QueryUtils.toOrders(pageable.getSort(), rootContact, cb));
        } catch(PropertyReferenceException exc) {
            Log.error(exc.getMessage() + " for " + pageable.getSort().toString());
            pageable = Utils.correct(pageable);
            cq.orderBy(QueryUtils.toOrders(pageable.getSort(), rootContact, cb));
        }
        makeQuery(contactFilter, rootContact, cq);
        TypedQuery<Contact> tq = em.createQuery(cq.select(rootContact));
        tq.setFirstResult((int)pageable.getOffset());
        tq.setMaxResults(pageable.getPageSize());

        return tq.getResultList();
    }

    @SuppressWarnings("unchecked")
    void makeQuery(ContactFilterPost contactFilterPost, Root<Contact> rootContact, CriteriaQuery<?> queryContact) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        Join<Contact, Request> joinContactRequest = null;
        if (contactFilterPost.getGuid() != null || contactFilterPost.getRegDateStart() != null || contactFilterPost.getRegDateEnd() != null
                || contactFilterPost.getCloseDateStart() != null || contactFilterPost.getCloseDateEnd() != null
                || contactFilterPost.getFilialIdList() != null && !contactFilterPost.getFilialIdList().isEmpty()
                || contactFilterPost.getSupervisorLoginList() != null && !contactFilterPost.getSupervisorLoginList().isEmpty()
                || contactFilterPost.getChannelIdList() != null || contactFilterPost.getCloseStatusList() != null
                || contactFilterPost.getContactNumberRel() != null && contactFilterPost.getContactNumber() != null
                || contactFilterPost.getClientClass() != null || contactFilterPost.getRequestPriority() != null
                || contactFilterPost.getClientType() != null || contactFilterPost.getDirection() != null) {
            //Учитываем наличие параметров для Request
            Set<Join<Contact, ?>> joinSet = rootContact.getJoins();
            if (!joinSet.isEmpty())
                joinContactRequest = (Join<Contact,Request>)joinSet.toArray()[0];
            else
                joinContactRequest = rootContact.join(Contact_.request, JoinType.LEFT);
        }
        List<Predicate> predicateRequest = new ArrayList<>();

        Subquery<Request> queryRequest = queryContact.subquery(Request.class);
        Root<Contact> rootRequest = queryRequest.from(Contact.class);
        queryRequest.select(rootRequest.get(Contact_.request));
        List<Predicate> predicateContact = new ArrayList<>();

        if (contactFilterPost.getGuid() != null) {
            String idString = contactFilterPost.getGuid();
            if (idString.indexOf("*") < 0 && idString.indexOf("?") < 0) {
                predicateRequest.add(cb.like(joinContactRequest.get(Request_.id), idString + '%'));
//                predicateRequest.add(cb.equal(joinContactRequest.get(Request_.id), idString));
//                predicateContact.add(cb.equal(rootRequest.get(Contact_.id), idString));
            } else {
                idString = idString.replace('*', '%');
                idString = idString.replace('?', '_');
                predicateContact.add(cb.like(rootRequest.get(Contact_.id), idString));
            }
        }
//      try {
//      Long idLong = Long.parseLong(idString);
//      predicateRequest.add(cb.equal(joinContactRequest.get(Request_.id), idLong));
//  } catch(NumberFormatException exc) {
//      idString = idString.replace('*', '%');
//      predicateContact.add(cb.like(cb.upper(rootRequest.get(Contact_.id)), idString.toUpperCase()));
//  }

        if (contactFilterPost.getRegDateStart() != null)
            predicateRequest.add(cb.greaterThanOrEqualTo(joinContactRequest.get(Request_.registrationDate), contactFilterPost.getRegDateStart()));
        if (contactFilterPost.getRegDateEnd() != null)
            predicateRequest.add(cb.lessThanOrEqualTo(joinContactRequest.get(Request_.registrationDate), contactFilterPost.getRegDateEnd()));
        if (contactFilterPost.getCloseDateStart() != null)
            predicateRequest.add(cb.greaterThanOrEqualTo(joinContactRequest.get(Request_.requestCloseDate), contactFilterPost.getCloseDateStart()));
        if (contactFilterPost.getCloseDateEnd() != null)
            predicateRequest.add(cb.lessThanOrEqualTo(joinContactRequest.get(Request_.requestCloseDate), contactFilterPost.getCloseDateEnd()));

//        if (contactFilterPost.getFilialIdList() != null && !contactFilterPost.getFilialIdList().isEmpty())
//            predicateRequest.add(joinContactRequest.get(Request_.filialId).in(contactFilterPost.getFilialIdList()));

        List<String> operatorLoginList = null;
        if (contactFilterPost.getOperatorLoginList() != null && !contactFilterPost.getOperatorLoginList().isEmpty())
            operatorLoginList = contactFilterPost.getOperatorLoginList();
        else if (contactFilterPost.getRegularGroupIdList() != null && !contactFilterPost.getRegularGroupIdList().isEmpty())
            operatorLoginList = operatorRepository.findOperatorLoginListByGroupd(contactFilterPost.getRegularGroupIdList());
        else if (contactFilterPost.getFilialIdList() != null && !contactFilterPost.getFilialIdList().isEmpty())
            operatorLoginList = operatorRepository.findOperatorLoginListByDepartement(contactFilterPost.getFilialIdList());

        //если разрешено видеть chatbot
        if(contactFilterPost.getShowChatbots() != null && !contactFilterPost.getShowChatbots())
            predicateContact.add(rootRequest.get(Contact_.operatorLogin).in("Chatbot").not());
        else predicateContact.add(rootRequest.get(Contact_.operatorLogin).in("Chatbot"));

        if (operatorLoginList != null && !operatorLoginList.isEmpty()) {
            List<String> domainLoginList = new ArrayList<>();
            for (String login : operatorLoginList) {
                domainLoginList.add(login);
                for (Domain domain : Domain.values()) {
                    Log.info(domain.name() + "\\" + login);
                    domainLoginList.add(domain.name() + "\\" + login);
                }
            }
            if (domainLoginList != null && !domainLoginList.isEmpty()) {
                if (domainLoginList.size() > MMContstants.OPERATORS_LIST_LIMIT) {
                    /*List<Predicate> orLogin = new ArrayList<>();
                    for (String login : operatorLoginList) {
                        login = '%' + login;
                        orLogin.add(cb.like(cb.upper(rootRequest.get(Contact_.operatorLogin)), login.toUpperCase()));
                    }
                    predicateContact.add(cb.or(orLogin.toArray(new Predicate[orLogin.size()])));*/
                    List<Predicate> orLogin = new ArrayList<>();
                    List<List<String>> parts = new ArrayList<>();
                    int partN = domainLoginList.size() / MMContstants.OPERATORS_LIST_LIMIT;
                    for (int i = 0; i < partN; i++) {
                        List<String> part = new ArrayList<>();
                        int pos = MMContstants.OPERATORS_LIST_LIMIT * i;
                        for (int k = pos; k < (pos + MMContstants.OPERATORS_LIST_LIMIT); k++)
                            part.add(domainLoginList.get(k));
                        parts.add(part);
                        orLogin.add(rootRequest.get(Contact_.operatorLogin).in(parts.get(i)));
                        //parts.clear();
                    }
                    predicateContact.add(cb.or(orLogin.toArray(new Predicate[orLogin.size()])));

                }
                else predicateContact.add(rootRequest.get(Contact_.operatorLogin).in(domainLoginList));
            }
        }

//        if (operatorLoginList != null && !operatorLoginList.isEmpty() ) {
//            List<Predicate> orLogin = new ArrayList<>();
//            for (String login : operatorLoginList) {
//                login = '%' + login;
//                orLogin.add(cb.like(cb.upper(rootRequest.get(Contact_.operatorLogin)), login.toUpperCase()));
//            }
//            predicateContact.add(cb.or(orLogin.toArray(new Predicate[orLogin.size()])));
//        }

        if (contactFilterPost.getOperatorLogin() != null) {
            String login = contactFilterPost.getOperatorLogin();
            login = login.replace('*', '%');
            predicateContact.add(cb.like(cb.upper(rootRequest.get(Contact_.operatorLogin)), login.toUpperCase()));
        }

        if (contactFilterPost.getSupervisorLoginList() != null && !contactFilterPost.getSupervisorLoginList().isEmpty())
            predicateContact.add(joinContactRequest.get(Request_.supervisorLogin).in(contactFilterPost.getSupervisorLoginList()));

        if (contactFilterPost.getChannelIdList() != null)
            predicateRequest.add(joinContactRequest.get(Request_.channelId).in(contactFilterPost.getChannelIdList()));

        if (contactFilterPost.getDirection() != null)
            predicateRequest.add(cb.equal(joinContactRequest.get(Request_.direction), contactFilterPost.getDirection()));
//TODO Направление теперь определяется по полю Request.direction Запрос ускорился в 5 раз.
//        if (contactFilterPost.getDirection() != null) {
//
//            Subquery<String> queryContact1 = queryRequest.subquery(String.class);
//            Root<Contact> rootContact1 = queryContact1.from(Contact.class);
//            queryContact1.where(cb.equal(rootContact1.get(Contact_.contactNumber), 1));
//            queryContact1.select(rootContact1.get(Contact_.id));
//
//            Join<Contact, Message> joinContactMessage1 = rootContact1.join(Contact_.messages);
//            Predicate p1 = cb.equal(joinContactMessage1.get(Message_.messageDirection), contactFilterPost.getDirection());
//            Predicate p2 = cb.equal(joinContactMessage1.get(Message_.messageNumber), 1);
//            joinContactMessage1.on(cb.and(p1, p2));
//
//            predicateRequest.add(rootRequest.get(Contact_.id).in(queryContact1));
//        }

        if (contactFilterPost.getCloseStatusList() != null)
            predicateRequest.add(joinContactRequest.get(Request_.requestStatus).in(contactFilterPost.getCloseStatusList()));

        if (contactFilterPost.getMessageText() != null) {

            Subquery<String> queryContact1 = queryRequest.subquery(String.class);
            Root<Message> rootContact1 = queryContact1.from(Message.class);
            queryContact1.select(rootContact1.get(Message_.contactId));
            queryContact1.where(cb.like(cb.upper(rootContact1.get(Message_.messageText)), "%" + contactFilterPost.getMessageText().toUpperCase() + "%"));

            predicateContact.add(rootRequest.get(Contact_.id).in(queryContact1));
        }

        if (contactFilterPost.getContactNumberRel() != null && contactFilterPost.getContactNumber() != null) {

            Subquery<Long> queryRequest1 = queryRequest.subquery(Long.class);
            Root<Contact> rootRequest1 = queryRequest1.from(Contact.class);
            Join<Contact, Request> joinContactRequest1 = rootRequest1.join(Contact_.request);
            queryRequest1.where(cb.equal(joinContactRequest1.get(Request_.id), joinContactRequest.get(Request_.id)));
            queryRequest1.select(cb.count(rootRequest1));

            if (">".equals(contactFilterPost.getContactNumberRel()))
                predicateContact.add(cb.gt(queryRequest1, contactFilterPost.getContactNumber() + 1));
            else if ("=".equals(contactFilterPost.getContactNumberRel()))
                predicateContact.add(cb.equal(queryRequest1, contactFilterPost.getContactNumber() + 1));
            else // "<"
                predicateContact.add(cb.lt(queryRequest1, contactFilterPost.getContactNumber() + 1));
        }

        if (contactFilterPost.getFromNumber() != null) {

            Subquery<String> queryContact1 = queryRequest.subquery(String.class);
            Root<Message> rootContact1 = queryContact1.from(Message.class);
            queryContact1.select(rootContact1.get(Message_.contactId));
            queryContact1.where(cb.equal(rootContact1.get(Message_.sender), contactFilterPost.getFromNumber()));

            predicateContact.add(rootRequest.get(Contact_.id).in(queryContact1));
        }

        if (contactFilterPost.getToNumber() != null) {

            Subquery<String> queryContact1 = queryRequest.subquery(String.class);
            Root<Message> rootContact1 = queryContact1.from(Message.class);
            queryContact1.select(rootContact1.get(Message_.contactId));
            queryContact1.where(cb.equal(rootContact1.get(Message_.recipient), contactFilterPost.getToNumber()));

            predicateContact.add(rootRequest.get(Contact_.id).in(queryContact1));
        }

        if (contactFilterPost.getClientClass() != null)
            predicateRequest.add(cb.equal(joinContactRequest.get(Request_.customerClass), contactFilterPost.getClientClass()));

        if (contactFilterPost.getRequestPriority() != null)
            predicateRequest.add(cb.equal(joinContactRequest.get(Request_.priority), contactFilterPost.getRequestPriority()));

        if (contactFilterPost.getTransferTo() != null) {
            predicateContact.add(cb.equal(rootRequest.get(Contact_.transferTo), contactFilterPost.getTransferTo()));
        }

        if (contactFilterPost.getTransferExists() != null) {
            Subquery<Request> queryRequest1 = queryContact.subquery(Request.class);
            Root<Contact> rootRequest1 = queryRequest1.from(Contact.class);
            queryRequest1.select(rootRequest1.get(Contact_.request));
            if (contactFilterPost.getTransferExists()) {
                queryRequest1.where(rootRequest1.get(Contact_.transferTo).isNotNull());
                predicateRequest.add(rootContact.get(Contact_.request).in(queryRequest1));
            } else {
                queryRequest1.where(rootRequest1.get(Contact_.transferTo).isNotNull());
                predicateRequest.add(cb.not(rootContact.get(Contact_.request).in(queryRequest1)));
            }
        }

        if (contactFilterPost.getClientType() != null)
            predicateRequest.add(cb.equal(joinContactRequest.get(Request_.customerType), contactFilterPost.getClientType()));

        if (contactFilterPost.getDurationStart() != null || contactFilterPost.getDurationEnd() != null) {
            long t1, t2;
            if (contactFilterPost.getDurationStart() == null) {
                t2 = contactFilterPost.getDurationEnd() * 1000;
                t1 = 0;
            } else if (contactFilterPost.getDurationEnd() == null) {
                t1 = contactFilterPost.getDurationStart() * 1000;
                t2 = 1000000000000l;
            } else {
                t1 = contactFilterPost.getDurationStart() * 1000;
                t2 = contactFilterPost.getDurationEnd() * 1000;
            }
            predicateContact.add(cb.and(cb.between(rootRequest.get(Contact_.duration), t1, t2), rootRequest.get(Contact_.head).isNull()));
        }

        if (!predicateContact.isEmpty()) {
            queryRequest.where(predicateContact.toArray(new Predicate[0]));
            predicateRequest.add(rootContact.get(Contact_.request).in(queryRequest));
        }
        if (!predicateRequest.isEmpty())
            queryContact.where(predicateRequest.toArray(new Predicate[0]));

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
