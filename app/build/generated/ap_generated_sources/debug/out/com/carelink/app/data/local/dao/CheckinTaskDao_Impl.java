package com.carelink.app.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.carelink.app.data.local.entity.CheckinTaskEntity;
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
public final class CheckinTaskDao_Impl implements CheckinTaskDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CheckinTaskEntity> __insertionAdapterOfCheckinTaskEntity;

  private final EntityDeletionOrUpdateAdapter<CheckinTaskEntity> __updateAdapterOfCheckinTaskEntity;

  public CheckinTaskDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCheckinTaskEntity = new EntityInsertionAdapter<CheckinTaskEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `checkin_task` (`id`,`elderId`,`type`,`title`,`plannedTime`,`repeatRule`,`enabled`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final CheckinTaskEntity entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.elderId);
        if (entity.type == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.type);
        }
        if (entity.title == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.title);
        }
        statement.bindLong(5, entity.plannedTime);
        if (entity.repeatRule == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.repeatRule);
        }
        statement.bindLong(7, entity.enabled);
      }
    };
    this.__updateAdapterOfCheckinTaskEntity = new EntityDeletionOrUpdateAdapter<CheckinTaskEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `checkin_task` SET `id` = ?,`elderId` = ?,`type` = ?,`title` = ?,`plannedTime` = ?,`repeatRule` = ?,`enabled` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final CheckinTaskEntity entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.elderId);
        if (entity.type == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.type);
        }
        if (entity.title == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.title);
        }
        statement.bindLong(5, entity.plannedTime);
        if (entity.repeatRule == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.repeatRule);
        }
        statement.bindLong(7, entity.enabled);
        statement.bindLong(8, entity.id);
      }
    };
  }

  @Override
  public long insert(final CheckinTaskEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfCheckinTaskEntity.insertAndReturnId(entity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final CheckinTaskEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCheckinTaskEntity.handle(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<CheckinTaskEntity>> getEnabledTasks(final long elderId) {
    final String _sql = "SELECT * FROM checkin_task WHERE elderId = ? AND enabled = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, elderId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"checkin_task"}, false, new Callable<List<CheckinTaskEntity>>() {
      @Override
      @Nullable
      public List<CheckinTaskEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfElderId = CursorUtil.getColumnIndexOrThrow(_cursor, "elderId");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfPlannedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "plannedTime");
          final int _cursorIndexOfRepeatRule = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatRule");
          final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
          final List<CheckinTaskEntity> _result = new ArrayList<CheckinTaskEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CheckinTaskEntity _item;
            _item = new CheckinTaskEntity();
            _item.id = _cursor.getLong(_cursorIndexOfId);
            _item.elderId = _cursor.getLong(_cursorIndexOfElderId);
            if (_cursor.isNull(_cursorIndexOfType)) {
              _item.type = null;
            } else {
              _item.type = _cursor.getString(_cursorIndexOfType);
            }
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _item.title = null;
            } else {
              _item.title = _cursor.getString(_cursorIndexOfTitle);
            }
            _item.plannedTime = _cursor.getLong(_cursorIndexOfPlannedTime);
            if (_cursor.isNull(_cursorIndexOfRepeatRule)) {
              _item.repeatRule = null;
            } else {
              _item.repeatRule = _cursor.getString(_cursorIndexOfRepeatRule);
            }
            _item.enabled = _cursor.getInt(_cursorIndexOfEnabled);
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
  public List<CheckinTaskEntity> getAllTasksSync(final long elderId) {
    final String _sql = "SELECT * FROM checkin_task WHERE elderId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, elderId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfElderId = CursorUtil.getColumnIndexOrThrow(_cursor, "elderId");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfPlannedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "plannedTime");
      final int _cursorIndexOfRepeatRule = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatRule");
      final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
      final List<CheckinTaskEntity> _result = new ArrayList<CheckinTaskEntity>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final CheckinTaskEntity _item;
        _item = new CheckinTaskEntity();
        _item.id = _cursor.getLong(_cursorIndexOfId);
        _item.elderId = _cursor.getLong(_cursorIndexOfElderId);
        if (_cursor.isNull(_cursorIndexOfType)) {
          _item.type = null;
        } else {
          _item.type = _cursor.getString(_cursorIndexOfType);
        }
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _item.title = null;
        } else {
          _item.title = _cursor.getString(_cursorIndexOfTitle);
        }
        _item.plannedTime = _cursor.getLong(_cursorIndexOfPlannedTime);
        if (_cursor.isNull(_cursorIndexOfRepeatRule)) {
          _item.repeatRule = null;
        } else {
          _item.repeatRule = _cursor.getString(_cursorIndexOfRepeatRule);
        }
        _item.enabled = _cursor.getInt(_cursorIndexOfEnabled);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public CheckinTaskEntity getById(final long id) {
    final String _sql = "SELECT * FROM checkin_task WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfElderId = CursorUtil.getColumnIndexOrThrow(_cursor, "elderId");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfPlannedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "plannedTime");
      final int _cursorIndexOfRepeatRule = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatRule");
      final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
      final CheckinTaskEntity _result;
      if (_cursor.moveToFirst()) {
        _result = new CheckinTaskEntity();
        _result.id = _cursor.getLong(_cursorIndexOfId);
        _result.elderId = _cursor.getLong(_cursorIndexOfElderId);
        if (_cursor.isNull(_cursorIndexOfType)) {
          _result.type = null;
        } else {
          _result.type = _cursor.getString(_cursorIndexOfType);
        }
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _result.title = null;
        } else {
          _result.title = _cursor.getString(_cursorIndexOfTitle);
        }
        _result.plannedTime = _cursor.getLong(_cursorIndexOfPlannedTime);
        if (_cursor.isNull(_cursorIndexOfRepeatRule)) {
          _result.repeatRule = null;
        } else {
          _result.repeatRule = _cursor.getString(_cursorIndexOfRepeatRule);
        }
        _result.enabled = _cursor.getInt(_cursorIndexOfEnabled);
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
