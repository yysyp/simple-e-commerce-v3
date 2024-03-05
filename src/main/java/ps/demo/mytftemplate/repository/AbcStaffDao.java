

package ps.demo.mytftemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ps.demo.mytftemplate.entity.AbcStaff;
import lombok.*;
import java.util.*;
import java.math.*;
@Repository
public interface AbcStaffDao extends JpaRepository<AbcStaff, Long>, JpaSpecificationExecutor {

    @Query("SELECT DISTINCT t.firstName FROM AbcStaff t")
    List<String> findDistinctFirstNames();

    @Query("SELECT DISTINCT t.lastName FROM AbcStaff t")
    List<String> findDistinctLastNames();

    @Query("SELECT DISTINCT t.age FROM AbcStaff t")
    List<Integer> findDistinctAges();

    @Query("SELECT DISTINCT t.score FROM AbcStaff t")
    List<BigDecimal> findDistinctScores();

    @Query("SELECT DISTINCT t.passed FROM AbcStaff t")
    List<Boolean> findDistinctPasseds();

    @Query("SELECT DISTINCT t.birthday FROM AbcStaff t")
    List<Date> findDistinctBirthdays();

    @Query("SELECT DISTINCT t.comments FROM AbcStaff t")
    List<String> findDistinctCommentss();



}


