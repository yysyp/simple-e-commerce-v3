package ps.demo.data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.math.BigDecimal;
import java.util.UUID;

/**
 *
 CREATE TABLE abc_staff (
 id BIGINT NOT NULL AUTO_INCREMENT,
 created_by VARCHAR(255) NOT NULL,
 created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 is_active BOOLEAN NOT NULL,
 is_logical_deleted BOOLEAN NOT NULL,
 modified_by VARCHAR(255) NOT NULL,
 modified_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 first_name VARCHAR(255) NOT NULL,
 last_name VARCHAR(255) NOT NULL,
 age INT,
 score DECIMAL(10, 2),
 passed BOOLEAN,
 birthday DATE,
 comments TEXT,
 PRIMARY KEY (id)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

 */
public class TestDataGenerator {
    public static void main(String[] args) {
        for (int i = 0; i < 11; i++) {
            String sql = generateInsertSql(i);
            System.out.println(sql);
        }
    }

    private static String generateInsertSql(int index) {
        String createdBy = UUID.randomUUID().toString();
        String modifiedBy = UUID.randomUUID().toString();
        String firstName = "FirstName" + index;
        String lastName = "LastName" + index;
        Integer age = 20 + index;
        BigDecimal score = new BigDecimal(80.0 + index);
        Boolean passed = index % 2 == 0;
        LocalDate birthday = LocalDate.now().minusYears(20 + index);
        Instant createdOn = Instant.now();
        Instant modifiedOn = Instant.now();
        String comments = "Comments" + index;

        return "INSERT INTO abc_staff (id, created_by, created_on, is_active, is_logical_deleted, modified_by, modified_on, first_name, last_name, age, score, passed, birthday, comments) " +
                "VALUES ("+10000+index+", '" + createdBy + "', TIMESTAMP '" + createdOn.atZone(ZoneId.systemDefault()).toLocalDateTime() + "', true, false, '" + modifiedBy + "', TIMESTAMP '" + modifiedOn.atZone(ZoneId.systemDefault()).toLocalDateTime() + "', '" + firstName + "', '" + lastName + "', " + age + ", " + score + ", " + passed + ", DATE '" + birthday + "', '" + comments + "');";
    }
}
