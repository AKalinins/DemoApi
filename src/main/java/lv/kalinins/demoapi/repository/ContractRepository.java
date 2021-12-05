package lv.kalinins.demoapi.repository;

import lv.kalinins.demoapi.domain.Contract;
import lv.kalinins.demoapi.domain.enums.ContractType;
import lv.kalinins.demoapi.domain.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query("SELECT c FROM Contract c INNER JOIN c.user u " +
            "WHERE (:startDate IS NULL OR c.startDate = :startDate) " +
            "AND (:type IS NULL OR c.type = :type) " +
            "AND (:userName IS NULL OR u.name = :userName) " +
            "AND (:userType IS NULL OR u.type = :userType)")
    List<Contract> findContractByStartDateTypeUserNameAndUserType(
            @Param("startDate") LocalDate startDate,
            @Param("type") ContractType type,
            @Param("userName") String userName,
            @Param("userType") UserType userType
    );
}
