package pl.pniedziela.database.stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class ObjectSizeDao {

	@Autowired
	ComboPooledDataSource comboPooledDataSource;

	public List<ObjectSize> getAllObjectSizes() {

		Connection connection;
		List<ObjectSize> listObjectSize = new ArrayList<ObjectSize>();
		try {
			connection = comboPooledDataSource.getConnection();
			connection.setAutoCommit(false);

			String strQuery = "SELECT table_name, table_schema, table_size AS table_size, indexes_size AS indexes_size, total_size AS total_size FROM (SELECT table_name, table_schema, pg_table_size(table_fullname) AS table_size, pg_indexes_size(table_fullname) AS indexes_size, pg_total_relation_size(table_fullname) AS total_size FROM ( SELECT ('\"' || table_schema || '\".\"' || table_name || '\"') AS table_fullname,table_name,table_schema FROM information_schema.tables ) AS all_tables ORDER BY total_size DESC ) AS pretty_sizes";
			PreparedStatement prepareStatement = connection.prepareStatement(strQuery);
			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				String tableName = rs.getString("table_name");
				String tableSchema = rs.getString("table_schema");
				String tableSize = rs.getString("table_size");
				String indexesSize = rs.getString("indexes_size");
				String totalSize = rs.getString("total_size");

				ObjectSize os = new ObjectSize(tableName, tableSchema, tableSize, indexesSize, totalSize);
				listObjectSize.add(os);
			}
			return listObjectSize;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
