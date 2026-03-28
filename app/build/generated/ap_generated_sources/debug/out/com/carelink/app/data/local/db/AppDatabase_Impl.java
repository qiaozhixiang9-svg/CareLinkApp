package com.carelink.app.data.local.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.carelink.app.data.local.dao.AlertDao;
import com.carelink.app.data.local.dao.AlertDao_Impl;
import com.carelink.app.data.local.dao.AppointmentDao;
import com.carelink.app.data.local.dao.AppointmentDao_Impl;
import com.carelink.app.data.local.dao.CareNoteDao;
import com.carelink.app.data.local.dao.CareNoteDao_Impl;
import com.carelink.app.data.local.dao.CheckinDao;
import com.carelink.app.data.local.dao.CheckinDao_Impl;
import com.carelink.app.data.local.dao.CheckinTaskDao;
import com.carelink.app.data.local.dao.CheckinTaskDao_Impl;
import com.carelink.app.data.local.dao.ShiftAssignmentDao;
import com.carelink.app.data.local.dao.ShiftAssignmentDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile CheckinDao _checkinDao;

  private volatile CheckinTaskDao _checkinTaskDao;

  private volatile AlertDao _alertDao;

  private volatile AppointmentDao _appointmentDao;

  private volatile CareNoteDao _careNoteDao;

  private volatile ShiftAssignmentDao _shiftAssignmentDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `checkin_task` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `elderId` INTEGER NOT NULL, `type` TEXT, `title` TEXT, `plannedTime` INTEGER NOT NULL, `repeatRule` TEXT, `enabled` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `checkin_record` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `elderId` INTEGER NOT NULL, `taskId` INTEGER NOT NULL, `type` TEXT, `actualTime` INTEGER NOT NULL, `value` TEXT, `note` TEXT, `source` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `alert_rule` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `elderId` INTEGER NOT NULL, `triggerType` TEXT, `threshold` INTEGER NOT NULL, `level` INTEGER NOT NULL, `enabled` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `alert_event` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `elderId` INTEGER NOT NULL, `alertType` TEXT, `description` TEXT, `level` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `status` TEXT, `assignedTo` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `appointments` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `elderId` INTEGER NOT NULL, `title` TEXT, `category` TEXT, `startTime` INTEGER NOT NULL, `endTime` INTEGER NOT NULL, `location` TEXT, `notes` TEXT, `assignedFamilyId` INTEGER NOT NULL, `status` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `care_notes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `elderId` INTEGER NOT NULL, `authorId` INTEGER NOT NULL, `content` TEXT, `tags` TEXT, `isImportant` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `imageUrl` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `shift_assignments` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `elderId` INTEGER NOT NULL, `familyUserId` INTEGER NOT NULL, `dutyDate` TEXT, `dutyType` TEXT, `status` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'e3a70d3ae6aaa4067334a526b03b94e3')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `checkin_task`");
        db.execSQL("DROP TABLE IF EXISTS `checkin_record`");
        db.execSQL("DROP TABLE IF EXISTS `alert_rule`");
        db.execSQL("DROP TABLE IF EXISTS `alert_event`");
        db.execSQL("DROP TABLE IF EXISTS `appointments`");
        db.execSQL("DROP TABLE IF EXISTS `care_notes`");
        db.execSQL("DROP TABLE IF EXISTS `shift_assignments`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsCheckinTask = new HashMap<String, TableInfo.Column>(7);
        _columnsCheckinTask.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCheckinTask.put("elderId", new TableInfo.Column("elderId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCheckinTask.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCheckinTask.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCheckinTask.put("plannedTime", new TableInfo.Column("plannedTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCheckinTask.put("repeatRule", new TableInfo.Column("repeatRule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCheckinTask.put("enabled", new TableInfo.Column("enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCheckinTask = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCheckinTask = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCheckinTask = new TableInfo("checkin_task", _columnsCheckinTask, _foreignKeysCheckinTask, _indicesCheckinTask);
        final TableInfo _existingCheckinTask = TableInfo.read(db, "checkin_task");
        if (!_infoCheckinTask.equals(_existingCheckinTask)) {
          return new RoomOpenHelper.ValidationResult(false, "checkin_task(com.carelink.app.data.local.entity.CheckinTaskEntity).\n"
                  + " Expected:\n" + _infoCheckinTask + "\n"
                  + " Found:\n" + _existingCheckinTask);
        }
        final HashMap<String, TableInfo.Column> _columnsCheckinRecord = new HashMap<String, TableInfo.Column>(8);
        _columnsCheckinRecord.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCheckinRecord.put("elderId", new TableInfo.Column("elderId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCheckinRecord.put("taskId", new TableInfo.Column("taskId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCheckinRecord.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCheckinRecord.put("actualTime", new TableInfo.Column("actualTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCheckinRecord.put("value", new TableInfo.Column("value", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCheckinRecord.put("note", new TableInfo.Column("note", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCheckinRecord.put("source", new TableInfo.Column("source", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCheckinRecord = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCheckinRecord = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCheckinRecord = new TableInfo("checkin_record", _columnsCheckinRecord, _foreignKeysCheckinRecord, _indicesCheckinRecord);
        final TableInfo _existingCheckinRecord = TableInfo.read(db, "checkin_record");
        if (!_infoCheckinRecord.equals(_existingCheckinRecord)) {
          return new RoomOpenHelper.ValidationResult(false, "checkin_record(com.carelink.app.data.local.entity.CheckinRecordEntity).\n"
                  + " Expected:\n" + _infoCheckinRecord + "\n"
                  + " Found:\n" + _existingCheckinRecord);
        }
        final HashMap<String, TableInfo.Column> _columnsAlertRule = new HashMap<String, TableInfo.Column>(6);
        _columnsAlertRule.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlertRule.put("elderId", new TableInfo.Column("elderId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlertRule.put("triggerType", new TableInfo.Column("triggerType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlertRule.put("threshold", new TableInfo.Column("threshold", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlertRule.put("level", new TableInfo.Column("level", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlertRule.put("enabled", new TableInfo.Column("enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAlertRule = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAlertRule = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAlertRule = new TableInfo("alert_rule", _columnsAlertRule, _foreignKeysAlertRule, _indicesAlertRule);
        final TableInfo _existingAlertRule = TableInfo.read(db, "alert_rule");
        if (!_infoAlertRule.equals(_existingAlertRule)) {
          return new RoomOpenHelper.ValidationResult(false, "alert_rule(com.carelink.app.data.local.entity.AlertRuleEntity).\n"
                  + " Expected:\n" + _infoAlertRule + "\n"
                  + " Found:\n" + _existingAlertRule);
        }
        final HashMap<String, TableInfo.Column> _columnsAlertEvent = new HashMap<String, TableInfo.Column>(8);
        _columnsAlertEvent.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlertEvent.put("elderId", new TableInfo.Column("elderId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlertEvent.put("alertType", new TableInfo.Column("alertType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlertEvent.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlertEvent.put("level", new TableInfo.Column("level", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlertEvent.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlertEvent.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlertEvent.put("assignedTo", new TableInfo.Column("assignedTo", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAlertEvent = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAlertEvent = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAlertEvent = new TableInfo("alert_event", _columnsAlertEvent, _foreignKeysAlertEvent, _indicesAlertEvent);
        final TableInfo _existingAlertEvent = TableInfo.read(db, "alert_event");
        if (!_infoAlertEvent.equals(_existingAlertEvent)) {
          return new RoomOpenHelper.ValidationResult(false, "alert_event(com.carelink.app.data.local.entity.AlertEventEntity).\n"
                  + " Expected:\n" + _infoAlertEvent + "\n"
                  + " Found:\n" + _existingAlertEvent);
        }
        final HashMap<String, TableInfo.Column> _columnsAppointments = new HashMap<String, TableInfo.Column>(10);
        _columnsAppointments.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("elderId", new TableInfo.Column("elderId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("startTime", new TableInfo.Column("startTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("endTime", new TableInfo.Column("endTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("location", new TableInfo.Column("location", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("assignedFamilyId", new TableInfo.Column("assignedFamilyId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppointments = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppointments = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAppointments = new TableInfo("appointments", _columnsAppointments, _foreignKeysAppointments, _indicesAppointments);
        final TableInfo _existingAppointments = TableInfo.read(db, "appointments");
        if (!_infoAppointments.equals(_existingAppointments)) {
          return new RoomOpenHelper.ValidationResult(false, "appointments(com.carelink.app.data.local.entity.AppointmentEntity).\n"
                  + " Expected:\n" + _infoAppointments + "\n"
                  + " Found:\n" + _existingAppointments);
        }
        final HashMap<String, TableInfo.Column> _columnsCareNotes = new HashMap<String, TableInfo.Column>(8);
        _columnsCareNotes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCareNotes.put("elderId", new TableInfo.Column("elderId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCareNotes.put("authorId", new TableInfo.Column("authorId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCareNotes.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCareNotes.put("tags", new TableInfo.Column("tags", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCareNotes.put("isImportant", new TableInfo.Column("isImportant", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCareNotes.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCareNotes.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCareNotes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCareNotes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCareNotes = new TableInfo("care_notes", _columnsCareNotes, _foreignKeysCareNotes, _indicesCareNotes);
        final TableInfo _existingCareNotes = TableInfo.read(db, "care_notes");
        if (!_infoCareNotes.equals(_existingCareNotes)) {
          return new RoomOpenHelper.ValidationResult(false, "care_notes(com.carelink.app.data.local.entity.CareNoteEntity).\n"
                  + " Expected:\n" + _infoCareNotes + "\n"
                  + " Found:\n" + _existingCareNotes);
        }
        final HashMap<String, TableInfo.Column> _columnsShiftAssignments = new HashMap<String, TableInfo.Column>(6);
        _columnsShiftAssignments.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShiftAssignments.put("elderId", new TableInfo.Column("elderId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShiftAssignments.put("familyUserId", new TableInfo.Column("familyUserId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShiftAssignments.put("dutyDate", new TableInfo.Column("dutyDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShiftAssignments.put("dutyType", new TableInfo.Column("dutyType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShiftAssignments.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysShiftAssignments = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesShiftAssignments = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoShiftAssignments = new TableInfo("shift_assignments", _columnsShiftAssignments, _foreignKeysShiftAssignments, _indicesShiftAssignments);
        final TableInfo _existingShiftAssignments = TableInfo.read(db, "shift_assignments");
        if (!_infoShiftAssignments.equals(_existingShiftAssignments)) {
          return new RoomOpenHelper.ValidationResult(false, "shift_assignments(com.carelink.app.data.local.entity.ShiftAssignmentEntity).\n"
                  + " Expected:\n" + _infoShiftAssignments + "\n"
                  + " Found:\n" + _existingShiftAssignments);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "e3a70d3ae6aaa4067334a526b03b94e3", "421f4c060f365182aa3bb27dd9cfb229");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "checkin_task","checkin_record","alert_rule","alert_event","appointments","care_notes","shift_assignments");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `checkin_task`");
      _db.execSQL("DELETE FROM `checkin_record`");
      _db.execSQL("DELETE FROM `alert_rule`");
      _db.execSQL("DELETE FROM `alert_event`");
      _db.execSQL("DELETE FROM `appointments`");
      _db.execSQL("DELETE FROM `care_notes`");
      _db.execSQL("DELETE FROM `shift_assignments`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CheckinDao.class, CheckinDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CheckinTaskDao.class, CheckinTaskDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AlertDao.class, AlertDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AppointmentDao.class, AppointmentDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CareNoteDao.class, CareNoteDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ShiftAssignmentDao.class, ShiftAssignmentDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public CheckinDao checkinDao() {
    if (_checkinDao != null) {
      return _checkinDao;
    } else {
      synchronized(this) {
        if(_checkinDao == null) {
          _checkinDao = new CheckinDao_Impl(this);
        }
        return _checkinDao;
      }
    }
  }

  @Override
  public CheckinTaskDao checkinTaskDao() {
    if (_checkinTaskDao != null) {
      return _checkinTaskDao;
    } else {
      synchronized(this) {
        if(_checkinTaskDao == null) {
          _checkinTaskDao = new CheckinTaskDao_Impl(this);
        }
        return _checkinTaskDao;
      }
    }
  }

  @Override
  public AlertDao alertDao() {
    if (_alertDao != null) {
      return _alertDao;
    } else {
      synchronized(this) {
        if(_alertDao == null) {
          _alertDao = new AlertDao_Impl(this);
        }
        return _alertDao;
      }
    }
  }

  @Override
  public AppointmentDao appointmentDao() {
    if (_appointmentDao != null) {
      return _appointmentDao;
    } else {
      synchronized(this) {
        if(_appointmentDao == null) {
          _appointmentDao = new AppointmentDao_Impl(this);
        }
        return _appointmentDao;
      }
    }
  }

  @Override
  public CareNoteDao careNoteDao() {
    if (_careNoteDao != null) {
      return _careNoteDao;
    } else {
      synchronized(this) {
        if(_careNoteDao == null) {
          _careNoteDao = new CareNoteDao_Impl(this);
        }
        return _careNoteDao;
      }
    }
  }

  @Override
  public ShiftAssignmentDao shiftAssignmentDao() {
    if (_shiftAssignmentDao != null) {
      return _shiftAssignmentDao;
    } else {
      synchronized(this) {
        if(_shiftAssignmentDao == null) {
          _shiftAssignmentDao = new ShiftAssignmentDao_Impl(this);
        }
        return _shiftAssignmentDao;
      }
    }
  }
}
