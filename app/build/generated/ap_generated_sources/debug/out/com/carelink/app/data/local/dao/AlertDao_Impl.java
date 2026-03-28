package com.carelink.app.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.carelink.app.data.local.entity.AlertEventEntity;
import com.carelink.app.data.local.entity.AlertRuleEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AlertDao_Impl implements AlertDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AlertEventEntity> __insertionAdapterOfAlertEventEntity;

  private final EntityInsertionAdapter<AlertRuleEntity> __insertionAdapterOfAlertRuleEntity;

  private final EntityDeletionOrUpdateAdapter<AlertEventEntity> __updateAdapterOfAlertEventEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateStatus;

  public AlertDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAlertEventEntity = new EntityInsertionAdapter<AlertEventEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `alert_event` (`id`,`elderId`,`alertType`,`description`,`level`,`createdAt`,`status`,`assignedTo`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final AlertEventEntity entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.elderId);
        if (entity.alertType == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.alertType);
        }
        if (entity.description == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.description);
        }
        statement.bindLong(5, entity.level);
        statement.bindLong(6, entity.createdAt);
        if (entity.status == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.status);
        }
        statement.bindLong(8, entity.assignedTo);
      }
    };
    this.__insertionAdapterOfAlertRuleEntity = new EntityInsertionAdapter<AlertRuleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `alert_rule` (`id`,`elderId`,`triggerType`,`threshold`,`level`,`enabled`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final AlertRuleEntity entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.elderId);
        if (entity.triggerType == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.triggerType);
        }
        statement.bindLong(4, entity.threshold);
        statement.bindLong(5, entity.level);
        statement.bindLong(6, entity.enabled);
      }
    };
    this.__updateAdapterOfAlertEventEntity = new EntityDeletionOrUpdateAdapter<AlertEventEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `alert_event` SET `id` = ?,`elderId` = ?,`alertType` = ?,`description` = ?,`level` = ?,`createdAt` = ?,`status` = ?,`assignedTo` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final AlertEventEntity entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.elderId);
        if (entity.alertType == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.alertType);
        }
        if (entity.description == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.description);
        }
        statement.bindLong(5, entity.level);
        statement.bindLong(6, entity.createdAt);
        if (entity.status == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.status);
        }
        statement.bindLong(8, entity.assignedTo);
        statement.bindLong(9, entity.id);
      }
    };
    this.__preparedStmtOfUpdateStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE alert_event SET status = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public long insertEvent(final AlertEventEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfAlertEventEntity.insertAndReturnId(entity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long insertRule(final AlertRuleEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfAlertRuleEntity.insertAndReturnId(entity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateEvent(final AlertEventEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfAlertEventEntity.handle(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateStatus(final long id, final String status) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateStatus.acquire();
    int _argIndex = 1;
    if (status == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, status);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, id);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfUpdateStatus.release(_stmt);
    }
  }

  @Override
  public LiveData<List<AlertEventEntity>> getAlertEvents(final long elderId) {
    final String _sql = "SELECT * FROM alert_event WHERE elderId = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, elderId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"alert_event"}, false, new Callable<List<AlertEventEntity>>() {
      @Override
      @Nullable
      public List<AlertEventEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfElderId = CursorUtil.getColumnIndexOrThrow(_cursor, "elderId");
          final int _cursorIndexOfAlertType = CursorUtil.getColumnIndexOrThrow(_cursor, "alertType");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfAssignedTo = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedTo");
          final List<AlertEventEntity> _result = new ArrayList<AlertEventEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AlertEventEntity _item;
            _item = new AlertEventEntity();
            _item.id = _cursor.getLong(_cursorIndexOfId);
            _item.elderId = _cursor.getLong(_cursorIndexOfElderId);
            if (_cursor.isNull(_cursorIndexOfAlertType)) {
              _item.alertType = null;
            } else {
              _item.alertType = _cursor.getString(_cursorIndexOfAlertType);
            }
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _item.description = null;
            } else {
              _item.description = _cursor.getString(_cursorIndexOfDescription);
            }
            _item.level = _cursor.getInt(_cursorIndexOfLevel);
            _item.createdAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _item.status = null;
            } else {
              _item.status = _cursor.getString(_cursorIndexOfStatus);
            }
            _item.assignedTo = _cursor.getLong(_cursorIndexOfAssignedTo);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<AlertEventEntity>> getPendingAlerts(final long elderId) {
    final String _sql = "SELECT * FROM alert_event WHERE elderId = ? AND status = 'PENDING' ORDER BY level DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, elderId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"alert_event"}, false, new Callable<List<AlertEventEntity>>() {
      @Override
      @Nullable
      public List<AlertEventEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfElderId = CursorUtil.getColumnIndexOrThrow(_cursor, "elderId");
          final int _cursorIndexOfAlertType = CursorUtil.getColumnIndexOrThrow(_cursor, "alertType");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfAssignedTo = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedTo");
          final List<AlertEventEntity> _result = new ArrayList<AlertEventEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AlertEventEntity _item;
            _item = new AlertEventEntity();
            _item.id = _cursor.getLong(_cursorIndexOfId);
            _item.elderId = _cursor.getLong(_cursorIndexOfElderId);
            if (_cursor.isNull(_cursorIndexOfAlertType)) {
              _item.alertType = null;
            } else {
              _item.alertType = _cursor.getString(_cursorIndexOfAlertType);
            }
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _item.description = null;
            } else {
              _item.description = _cursor.getString(_cursorIndexOfDescription);
            }
            _item.level = _cursor.getInt(_cursorIndexOfLevel);
            _item.createdAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _item.status = null;
            } else {
              _item.status = _cursor.getString(_cursorIndexOfStatus);
            }
            _item.assignedTo = _cursor.getLong(_cursorIndexOfAssignedTo);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public List<AlertRuleEntity> getEnabledRules(final long elderId) {
    final String _sql = "SELECT * FROM alert_rule WHERE elderId = ? AND enabled = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, elderId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfElderId = CursorUtil.getColumnIndexOrThrow(_cursor, "elderId");
      final int _cursorIndexOfTriggerType = CursorUtil.getColumnIndexOrThrow(_cursor, "triggerType");
      final int _cursorIndexOfThreshold = CursorUtil.getColumnIndexOrThrow(_cursor, "threshold");
      final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
      final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
      final List<AlertRuleEntity> _result = new ArrayList<AlertRuleEntity>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final AlertRuleEntity _item;
        _item = new AlertRuleEntity();
        _item.id = _cursor.getLong(_cursorIndexOfId);
        _item.elderId = _cursor.getLong(_cursorIndexOfElderId);
        if (_cursor.isNull(_cursorIndexOfTriggerType)) {
          _item.triggerType = null;
        } else {
          _item.triggerType = _cursor.getString(_cursorIndexOfTriggerType);
        }
        _item.threshold = _cursor.getInt(_cursorIndexOfThreshold);
        _item.level = _cursor.getInt(_cursorIndexOfLevel);
        _item.enabled = _cursor.getInt(_cursorIndexOfEnabled);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
