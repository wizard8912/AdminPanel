package pl.pniedziela.database.stat;

public class ObjectSize {

	private String tableName;
	private String tableSchema;
	private String tableSize;
	private String indexesSize;
	private String totalSize;

	public ObjectSize(String tableName, String tableSchema, String tableSize, String indexesSize, String totalSize) {
		super();
		this.tableName = tableName;
		this.tableSchema = tableSchema;
		this.tableSize = tableSize;
		this.indexesSize = indexesSize;
		this.totalSize = totalSize;
	}

	public String getTableName() {
		return tableName;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public String getTableSize() {
		return tableSize;
	}

	public String getIndexesSize() {
		return indexesSize;
	}

	public String getTotalSize() {
		return totalSize;
	}

	@Override
	public String toString() {
		return "ObjectSize [tableName=" + tableName + ", tableSchema=" + tableSchema + ", tableSize=" + tableSize
				+ ", indexesSize=" + indexesSize + ", totalSize=" + totalSize + "]";
	}

}
