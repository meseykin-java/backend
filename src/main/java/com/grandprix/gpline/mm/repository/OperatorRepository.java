package com.grandprix.gpline.mm.repository;

import java.util.List;

import com.grandprix.gpline.mm.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, String> {

    @Query(
        value = "select ut.f_login " + 
                " from t_usertable ut " + 
                " left join t_operators_groupd gd on gd.f_uid = ut.f_id " + 
                " where ut.f_blocked = 0 and gd.f_pid in :ids" + 
                "", 
        nativeQuery = true)
    public List<String> findOperatorLoginListByGroupd(
        @Param("ids") List<Long> regularGroupIdList
    );
    
    @Query(
            value = "SELECT ut.f_login\r\n" + 
                    "FROM t_usertable ut\r\n" + 
                    "JOIN t_department dp ON dp.f_id = ut.f_departmentid\r\n" + 
                    "WHERE ut.f_blocked = 0\r\n" + 
                    "AND dp.f_id in :ids", 
            nativeQuery = true)
        public List<String> findOperatorLoginListByDepartement(
            @Param("ids") List<String> departementIdList
        );
    
}
