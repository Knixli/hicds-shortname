import cn.hicds.tables.Meetings;
import com.x2work.cfg.Environment;
import org.apache.commons.configuration.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by knix on 15/7/23.Ø
 */
public class Compare {
    public static void main(String[] args) throws SQLException {
        Compare compare = new Compare();
        compare.ExecuteSQL();
    }

    private DSLContext dslContext;

    public Compare() throws SQLException {
        Configuration configuration = Environment.current().getConfig();
        Connection conn = DriverManager.getConnection(configuration.getString("db.url"), configuration.getString("db.username"), configuration.getString(("db.password")));
        dslContext = DSL.using(conn, SQLDialect.MYSQL);
    }

    public void ExecuteSQL() {
        List list = dslContext.select(Meetings.MEETINGS.ID, Meetings.MEETINGS.TITLE).from(Meetings.MEETINGS).where(Meetings.MEETINGS.SHORTNAME.notEqual("")).fetch();
        System.out.print(list);
    }
}
