import com.domain.Customer;
import com.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class jdbc {
    public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from Customer";
        List<Customer> list = template.query(sql, new BeanPropertyRowMapper<>(Customer.class));
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
}
