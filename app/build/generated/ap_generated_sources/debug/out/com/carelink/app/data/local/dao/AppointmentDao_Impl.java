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
import com.carelink.app.data.local.entity.AppointmentEntity;
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
public final class AppointmentDao_Impl implements AppointmentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AppointmentEntity> __insertionAdapterOfAppointmentEntity;

  private final EntityDeletionOrUpdateAdapter<AppointmentEntity> __updateAdapterOfAppointmentEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public AppointmentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAppointmentEntity = new EntityInsertionAdapter<AppointmentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `appointments` (`id`,`elderId`,`title`,`category`,`startTime`,`endTime`,`location`,`notes`,`assignedFamilyId`,`status`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final AppointmentEntity entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.elderId);
        if (entity.title == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.title);
        }
        if (entity.category == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.category);
        }
        statement.bindLong(5, entity.startTime);
        statement.bindLong(6, entity.endTime);
        if (entity.location == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.location);
        }
        if (entity.notes == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.notes);
        }
        statement.bindLong(9, entity.assignedFamilyId);
        statement.bindLong(10, entity.status);
      }
    };
    this.__updateAdapterOfAppointmentEntity = new EntityDeletionOrUpdateAdapter<AppointmentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `appointments` SET `id` = ?,`elderId` = ?,`title` = ?,`category` = ?,`startTime` = ?,`endTime` = ?,`location` = ?,`notes` = ?,`assignedFamilyId` = ?,`status` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final AppointmentEntity entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.elderId);
        if (entity.title == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.title);
        }
        if (entity.category == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.category);
        }
        statement.bindLong(5, entity.startTime);
        statement.bindLong(6, entity.endTime);
        if (entity.location == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.location);
        }
        if (entity.notes == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.notes);
        }
        statement.bindLong(9, entity.assignedFamilyId);
        statement.bindLong(10, entity.status);
        statement.bindLong(11, entity.id);
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM appointments WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final AppointmentEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfAppointmentEntity.insertAndReturnId(entity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final AppointmentEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfAppointmentEntity.handle(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteById(final long id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
    int _argIndex = 1;
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
      __preparedStmtOfDeleteById.release(_stmt);
    }
  }

  @Override
  public LiveData<List<AppointmentEntity>> getAppointments(final long elderId) {
    final String _sql = "SELECT * FROM appointments WHERE elderId = ? ORDER BY startTime ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, elderId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"appointments"}, false, new Callable<List<AppointmentEntity>>() {
      @Override
      @Nullable
      public List<AppointmentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfElderId = CursorUtil.getColumnIndexOrThrow(_cursor, "elderId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfAssignedFamilyId = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedFamilyId");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<AppointmentEntity> _result = new ArrayList<AppointmentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppointmentEntity _item;
            _item = new AppointmentEntity();
            _item.id = _cursor.getLong(_cursorIndexOfId);
            _item.elderId = _cursor.getLong(_cursorIndexOfElderId);
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _item.title = null;
            } else {
              _item.title = _cursor.getString(_cursorIndexOfTitle);
            }
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _item.category = null;
            } else {
              _item.category = _cursor.getString(_cursorIndexOfCategory);
            }
            _item.startTime = _cursor.getLong(_cursorIndexOfStartTime);
            _item.endTime = _cursor.getLong(_cursorIndexOfEndTime);
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _item.location = null;
            } else {
              _item.location = _cursor.getString(_cursorIndexOfLocation);
            }
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _item.notes = null;
            } else {
              _item.notes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item.assignedFamilyId = _cursor.getLong(_cursorIndexOfAssignedFamilyId);
            _item.status = _cursor.getInt(_cursorIndexOfStatus);
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
  public LiveData<List<AppointmentEntity>> getUpcomingAppointments(final long elderId) {
    final String _sql = "SELECT * FROM appointments WHERE elderId = ? AND date(startTime / 1000, 'unixepoch', 'localtime') >= date('now', 'localtime') AND status != 2 ORDER BY startTime ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, elderId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"appointments"}, false, new Callable<List<AppointmentEntity>>() {
      @Override
      @Nullable
      public List<AppointmentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfElderId = CursorUtil.getColumnIndexOrThrow(_cursor, "elderId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfAssignedFamilyId = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedFamilyId");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<AppointmentEntity> _result = new ArrayList<AppointmentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppointmentEntity _item;
            _item = new AppointmentEntity();
            _item.id = _cursor.getLong(_cursorIndexOfId);
            _item.elderId = _cursor.getLong(_cursorIndexOfElderId);
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _item.title = null;
            } else {
              _item.title = _cursor.getString(_cursorIndexOfTitle);
            }
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _item.category = null;
            } else {
              _item.category = _cursor.getString(_cursorIndexOfCategory);
            }
            _item.startTime = _cursor.getLong(_cursorIndexOfStartTime);
            _item.endTime = _cursor.getLong(_cursorIndexOfEndTime);
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _item.location = null;
            } else {
              _item.location = _cursor.getString(_cursorIndexOfLocation);
            }
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _item.notes = null;
            } else {
              _item.notes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item.assignedFamilyId = _cursor.getLong(_cursorIndexOfAssignedFamilyId);
            _item.status = _cursor.getInt(_cursorIndexOfStatus);
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
  public AppointmentEntity getById(final long id) {
    final String _sql = "SELECT * FROM appointments WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfElderId = CursorUtil.getColumnIndexOrThrow(_cursor, "elderId");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
      final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
      final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
      final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
      final int _cursorIndexOfAssignedFamilyId = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedFamilyId");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final AppointmentEntity _result;
      if (_cursor.moveToFirst()) {
        _result = new AppointmentEntity();
        _result.id = _cursor.getLong(_cursorIndexOfId);
        _result.elderId = _cursor.getLong(_cursorIndexOfElderId);
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _result.title = null;
        } else {
          _result.title = _cursor.getString(_cursorIndexOfTitle);
        }
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _result.category = null;
        } else {
          _result.category = _cursor.getString(_cursorIndexOfCategory);
        }
        _result.startTime = _cursor.getLong(_cursorIndexOfStartTime);
        _result.endTime = _cursor.getLong(_cursorIndexOfEndTime);
        if (_cursor.isNull(_cursorIndexOfLocation)) {
          _result.location = null;
        } else {
          _result.location = _cursor.getString(_cursorIndexOfLocation);
        }
        if (_cursor.isNull(_cursorIndexOfNotes)) {
          _result.notes = null;
        } else {
          _result.notes = _cursor.getString(_cursorIndexOfNotes);
        }
        _result.assignedFamilyId = _cursor.getLong(_cursorIndexOfAssignedFamilyId);
        _result.status = _cursor.getInt(_cursorIndexOfStatus);
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
