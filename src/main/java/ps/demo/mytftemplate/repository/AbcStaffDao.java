

package ps.demo.mytftemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ps.demo.mytftemplate.entity.AbcStaff;

@Repository
public interface AbcStaffDao extends JpaRepository<AbcStaff, Long>, JpaSpecificationExecutor {

}


