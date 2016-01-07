package com.example.myfirstdaogeneraotor.model;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.myfirstdaogeneraotor.model.Admin;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ADMIN".
*/
public class AdminDao extends AbstractDao<Admin, Long> {

    public static final String TABLENAME = "ADMIN";

    /**
     * Properties of entity Admin.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Company = new Property(2, String.class, "company", false, "COMPANY");
        public final static Property Rollno = new Property(3, Long.class, "rollno", false, "ROLLNO");
        public final static Property Employeeno = new Property(4, Long.class, "employeeno", false, "EMPLOYEENO");
        public final static Property EmployeeId = new Property(5, Long.class, "employeeId", false, "EMPLOYEE_ID");
    };

    private DaoSession daoSession;


    public AdminDao(DaoConfig config) {
        super(config);
    }
    
    public AdminDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ADMIN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"COMPANY\" TEXT," + // 2: company
                "\"ROLLNO\" INTEGER," + // 3: rollno
                "\"EMPLOYEENO\" INTEGER," + // 4: employeeno
                "\"EMPLOYEE_ID\" INTEGER);"); // 5: employeeId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ADMIN\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Admin entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String company = entity.getCompany();
        if (company != null) {
            stmt.bindString(3, company);
        }
 
        Long rollno = entity.getRollno();
        if (rollno != null) {
            stmt.bindLong(4, rollno);
        }
 
        Long employeeno = entity.getEmployeeno();
        if (employeeno != null) {
            stmt.bindLong(5, employeeno);
        }
 
        Long employeeId = entity.getEmployeeId();
        if (employeeId != null) {
            stmt.bindLong(6, employeeId);
        }
    }

    @Override
    protected void attachEntity(Admin entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Admin readEntity(Cursor cursor, int offset) {
        Admin entity = new Admin( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // company
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // rollno
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // employeeno
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5) // employeeId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Admin entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCompany(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setRollno(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setEmployeeno(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setEmployeeId(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Admin entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Admin entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getEmployeeDao().getAllColumns());
            builder.append(" FROM ADMIN T");
            builder.append(" LEFT JOIN EMPLOYEE T0 ON T.\"EMPLOYEE_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Admin loadCurrentDeep(Cursor cursor, boolean lock) {
        Admin entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Employee employee = loadCurrentOther(daoSession.getEmployeeDao(), cursor, offset);
        entity.setEmployee(employee);

        return entity;    
    }

    public Admin loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Admin> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Admin> list = new ArrayList<Admin>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Admin> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Admin> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
