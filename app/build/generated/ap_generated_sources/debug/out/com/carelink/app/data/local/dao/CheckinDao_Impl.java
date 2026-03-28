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
import com.carelink.app.data.local.entity.CheckinRecordEntity;
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
public final class CheckinDao_Impl implements CheckinDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CheckinRecordEntity> __insertionAdapterOfCheckinRecordEntity;

  private final EntityDeletionOrUpdateAdapter<CheckinRecordEntity> __updateAdapterOfCheckinRecordEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public CheckinDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCheckinRecordEntity = new EntityInsertionAdapter<CheckinRecordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `checkin_record` (`id`,`elderId`,`taskId`,`type`,`actualTime`,`value`,`note`,`source`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final CheckinRecordEntity entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.elderId);
        statement.bindLong(3, entity.taskId);
        if (entity.type == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.type);
        }
        statement.bindLong(5, entity.actualTime);
        if (entity.value == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.value);
        }
        if (entity.note == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.note);
        }
        if (entity.source == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.source);
        }
      }
    };
    this.__updateAdapterOfCheckinRecordEntity = new EntityDeletionOrUpdateAdapter<CheckinRecordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `checkin_record` SET `id` = ?,`elderId` = ?,`taskId` = ?,`type` = ?,`actualTime` = ?,`value` = ?,`note` = ?,`source` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final CheckinRecordEntity entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.elderId);
        statement.bindLong(3, entity.taskId);
        if (entity.type == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.type);
        }
        statement.bindLong(5, entity.actualTime);
        if (entity.value == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.value);
        }
        if (entity.note == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.note);
        }
        if (entity.source == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.source);
        }
        statement.bindLong(9, entity.id);
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM checkin_record WHERE elderId = ?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final CheckinRecordEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfCheckinRecordEntity.insertAndReturnId(entity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final CheckinRecordEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCheckinRecordEntity.handle(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll(final long elderId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, elderId);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public LiveData<List<CheckinRecordEntity>> getRecords(final long elderId) {
    final String _sql = "SELECT * FROM checkin_record WHERE elderId = ? ORDER BY actualTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, elderId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"checkin_record"}, false, new Callable<List<CheckinRecordEntity>>() {
      @Override
      @Nullable
      public List<CheckinRecordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfElderId = CursorUtil.getColumnIndexOrThrow(_cursor, "elderId");
          final int _cursorIndexOfTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "taskId");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfActualTime = CursorUtil.getColumnIndexOrThrow(_cursor, "actualTime");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "value");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final List<CheckinRecordEntity> _result = new ArrayList<CheckinRecordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CheckinRecordEntity _item;
            _item = new CheckinRecordEntity();
            _item.id = _cursor.getLong(_cursorIndexOfId);
            _item.elderId = _cursor.getLong(_cursorIndexOfElderId);
            _item.taskId = _cursor.getLong(_cursorIndexOfTaskId);
            if (_cursor.isNull(_cursorIndexOfType)) {
              _item.type = null;
            } else {
              _item.type = _cursor.getString(_cursorIndexOfType);
            }
            _item.actualTime = _cursor.getLong(_cursorIndexOfActualTime);
            if (_cursor.isNull(_cursorIndexOfValue)) {
              _item.value = null;
            } else {
              _item.value = _cursor.getString(_cursorIndexOfValue);
            }
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _item.note = null;
            } else {
              _item.note = _cursor.getString(_cursorIndexOfNote);
            }
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _item.source = null;
            } else {
              _item.source = _cursor.getString(_cursorIndexOfSource);
            }
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
  public LiveData<List<CheckinRecordEntity>> getTodayRecords(final long elderId) {
    final String _sql = "SELECT * FROM checkin_record WHERE elderId = ? AND date(actualTime / 1000, 'unixepoch', 'localtime') = date('now', 'localtime')";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, elderId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"checkin_record"}, false, new Callable<List<CheckinRecordEntity>>() {
      @Override
      @Nullable
      public List<CheckinRecordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfElderId = CursorUtil.getColumnIndexOrThrow(_cursor, "elderId");
          final int _cursorIndexOfTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "taskId");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfActualTime = CursorUtil.getColumnIndexOrThrow(_cursor, "actualTime");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "value");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final List<CheckinRecordEntity> _result = new ArrayList<CheckinRecordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CheckinRecordEntity _item;
            _item = new CheckinRecordEntity();
            _item.id = _cursor.getLong(_cursorIndexOfId);
            _item.elderId = _cursor.getLong(_cursorIndexOfElderId);
            _item.taskId = _cursor.getLong(_cursorIndexOfTaskId);
            if (_cursor.isNull(_cursorIndexOfType)) {
              _item.type = null;
            } else {
              _item.type = _cursor.getString(_cursorIndexOfType);
            }
            _item.actualTime = _cursor.getLong(_cursorIndexOfActualTime);
            if (_cursor.isNull(_cursorIndexOfValue)) {
              _item.value = null;
            } else {
              _item.value = _cursor.getString(_cursorIndexOfValue);
            }
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _item.note = null;
            } else {
              _item.note = _cursor.getString(_cursorIndexOfNote);
            }
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _item.source = null;
            } else {
              _item.source = _cursor.getString(_cursorIndexOfSource);
            }
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
  public CheckinRecordEntity getTodayRecordByTask(final long elderId, final long taskId) {
    final String _sql = "SELECT * FROM checkin_record WHERE elderId = ? AND taskId = ? AND date(actualTime / 1000, 'unixepoch', 'localtime') = date('now', 'localtime') LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, elderId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, taskId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfElderId = CursorUtil.getColumnIndexOrThrow(_cursor, "elderId");
      final int _cursorIndexOfTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "taskId");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfActualTime = CursorUtil.getColumnIndexOrThrow(_cursor, "actualTime");
      final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "value");
      final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
      final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
      final CheckinRecordEntity _result;
      if (_cursor.moveToFirst()) {
        _result = new CheckinRecordEntity();
        _result.id = _cursor.getLong(_cursorIndexOfId);
        _result.elderId = _cursor.getLong(_cursorIndexOfElderId);
        _result.taskId = _cursor.getLong(_cursorIndexOfTaskId);
        if (_cursor.isNull(_cursorIndexOfType)) {
          _result.type = null;
        } else {
          _result.type = _cursor.getString(_cursorIndexOfType);
        }
        _result.actualTime = _cursor.getLong(_cursorIndexOfActualTime);
        if (_cursor.isNull(_cursorIndexOfValue)) {
          _result.value = null;
        } else {
          _result.value = _cursor.getString(_cursorIndexOfValue);
        }
        if (_cursor.isNull(_cursorIndexOfNote)) {
          _result.note = null;
        } else {
          _result.note = _cursor.getString(_cursorIndexOfNote);
        }
        if (_cursor.isNull(_cursorIndexOfSource)) {
          _result.source = null;
        } else {
          _result.source = _cursor.getString(_cursorIndexOfSource);
        }
      } else {
        _result = null;
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
