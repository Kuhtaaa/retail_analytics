package edu.school21.application.repositories;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileIORepository {
    private static final String IMPORT = "SELECT import_db(:tableName, ',')";
    private static final String EXPORT = "SELECT export_db(:tableName, ',')";
    private static final String TABLE_NAMES = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'";
    private final EntityManager entityManager;
    private final List tableNames;


    public FileIORepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
        this.tableNames = entityManager.createNativeQuery(TABLE_NAMES)
                                       .getResultList();
    }

    private void doNativeQueryByString(
            final String query,
            final String tableName
    ) {
        entityManager.createNativeQuery(query)
                     .setParameter("tableName", tableName)
                     .getSingleResult();
    }

    public boolean importToTable(final String table) {
        if(tableExists(table)) {
            doNativeQueryByString(IMPORT, table);
            return true;
        }
        return false;
    }

    public boolean exportFromTable(final String table) {
        if(tableExists(table)) {
            doNativeQueryByString(EXPORT, table);
            return true;
        }
        return false;
    }

    public boolean tableExists(final String tableName) {
        return tableNames.contains(tableName);
    }
}
