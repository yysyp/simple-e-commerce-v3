package [(${packageName}+'.'+${moduleName}+'.'+${daoFolder})];

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import [(${packageName}+'.'+${moduleName}+'.'+${entityFolder})].[(${entityName})];
import lombok.*;
import java.util.*;
import java.math.*;
@Repository
public interface [(${daoName})] extends JpaRepository<[(${entityName})], Long>, JpaSpecificationExecutor {
[# th:each="attr,attrStat:${entityAttrs}" ]
    @Query("SELECT DISTINCT t.[(${attr.get('name')})] FROM [(${entityName})] t")
    List<[(${attr.get('type')})]> findDistinct[(${#strings.capitalizeWords(attr.get('name'))})]s();

[/]

}
