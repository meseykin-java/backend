package com.grandprix.gpline.mm.utils;

import com.grandprix.gpline.mm.errorhandler.AppException;
import com.grandprix.gpline.mm.model.Contact_;
import com.grandprix.gpline.mm.model.Request_;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static final String DATE_PATTERN = "dd.MM.yyyy HH:mm:ss";

    /**
     * НЕ ИСПОЛЬЗУЕТСЯ
     * Возвращает дату (Timestamp) из строки с форматом даты "dd.MM.yyyy HH:mm:ss"
     * @param dateAsString
     * @return
     */
    public static Timestamp convertToTimestamp(String dateAsString) {
        if (dateAsString == null)
            return null;
        Timestamp timestamp = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
            Date parsedDate = simpleDateFormat.parse(dateAsString);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch (ParseException ex) {
            throw new AppException("Некорректный формат даты/времени ("+DATE_PATTERN+"): " + dateAsString);
        }
        return timestamp;
    }

    /**
     * Возвращает Pageable-объект
     * @param orders порядок сортировки, заданый строками с форматом <fieldName,[desc]>
     * @param limit количество строк на странице
     * @param offset номер страницы (zero-based)
     * @return
     */
    public static Pageable getPageable(int limit, long offset, String... orders) {
        Order o[] = new Order[orders.length + 4];
        o[0] = Order.desc(Contact_.REQUEST + "." + Request_.REQUEST_CLOSE_DATE);
//        o[0] = Order.desc(Contact_.REQUEST + "." + Request_.REGISTRATION_DATE);
        o[1] = Order.desc(Contact_.REQUEST + "." + Request_.ID);
        o[2] = Order.asc(Contact_.HEAD);
        int i = 3;
        for (String order : orders) {
            if (order != null && order.length() != 0) {
                String[] array = order.split(",");
                String field = array[0];
                String direction = array.length > 1 ? array[1].toUpperCase() : Sort.Direction.ASC.name();

                if (field.startsWith("request.")) {
                    if (direction.equals(Sort.Direction.DESC.name())) {
                        o[0] = Order.desc(field);
                    } else {
                        o[0] = Order.asc(field);
                    }
                    --i;
                } else { 
                    if (direction.equals(Sort.Direction.DESC.name()))
                        o[i] = Order.desc(field);
                    else
                        o[i] = Order.asc(field);
                }
            }
            ++i;
        }
        o[i] = Order.asc(Contact_.START_DATE);
        if (o[o.length - 1] == null) {
            Order o1[] = new Order[o.length - 1];
            System.arraycopy(o, 0, o1, 0, o1.length);
            o = o1;
        }
        Sort sort = Sort.by(o);
        return ScrollPageRequest.of(offset, limit, sort);
    }

    public static Pageable correct(Pageable pageable) {
        Order o[] = new Order[4];
        o[0] = Order.desc(Contact_.REQUEST + "." + Request_.REGISTRATION_DATE);
        o[1] = Order.desc(Contact_.REQUEST + "." + Request_.ID);
        o[2] = Order.asc(Contact_.HEAD);
        o[3] = Order.asc(Contact_.START_DATE);
        Sort sort = Sort.by(o);
        return ScrollPageRequest.of(pageable.getOffset(), pageable.getPageSize(), sort);
    }
    
}
