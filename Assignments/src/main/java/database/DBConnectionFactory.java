package database;

public class DBConnectionFactory {
    public JDBConnectionWrapper getConnectionWrapper(boolean testConnection) {
        if (testConnection) {
            return new JDBConnectionWrapper(Constants.Schemas.TEST);
        }
        return new JDBConnectionWrapper(Constants.Schemas.PRODUCTION);
    }
}
