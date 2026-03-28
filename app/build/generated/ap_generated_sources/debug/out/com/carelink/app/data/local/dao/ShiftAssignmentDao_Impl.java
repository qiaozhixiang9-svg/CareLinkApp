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
import com.carelink.app.data.local.entity.ShiftAssignmentEntity;
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
public final class ShiftAssignmentDao_Impl implements ShiftAssignmentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ShiftAssignmentEntity> __insertionAdapterOfShiftAssignmentEntity;

  private final EntityDeletionOrUpdateAdapter<ShiftAssignmentEntity> __updateAdapterOfShiftAssignmentEntity;

  public ShiftAssignmentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfShiftAssignmentEntity = new EntityInsertionAdapter<ShiftAssignmentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `shift_assignments` (`id`,`elderId`,`familyUserId`,`dutyDate`,`dutyType`,`status`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final ShiftAssignmentEntity entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.elderId);
        statement.bindLong(3, entity.familyUserId);
        if (entity.dutyDate == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.dutyDate);
        }
        if (entity.dutyType == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.dutyType);
        }
        statement.bindLong(6, entity.status);
      }
    };
    this.__updateAdapterOfShiftAssignmentEntity = new EntityDeletionOrUpdateAdapter<ShiftAssignmentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `shift_assignments` SET `id` = ?,`elderId` = ?,`familyUserId` = ?,`dutyDate` = ?,`dutyType` = ?,`status` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final ShiftAssignmentEntity entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.elderId);
        statement.bindLong(3, entity.familyUserId);
        if (entity.dutyDate == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.dutyDate);
        }
        if (entity.dutyType == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.dutyType);
        }
        statement.bindLong(6, entity.status);
        statement.bindLong(7, entity.id);
      }
    };
  }

  @Override
  public long insert(final ShiftAssignmentEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfShiftAssignmentEntity.insertAndReturnId(entity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final ShiftAssignmentEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfShiftAssignmentEntity.handle(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<ShiftAssignmentEntity>> getShifts(final long elderId) {
    final String _sql = "SELECT * FROM shift_assignments WHERE elderId = ? ORDER BY dutyDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, elderId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"shift_assignments"}, false, new Callable<List<ShiftAssignmentEntity>>() {
      @Override
      @Nullable
      public List<ShiftAssignmentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfElderId = CursorUtil.getColumnIndexOrThrow(_cursor, "elderId");
          final int _cursorIndexOfFamilyUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "familyUserId");
          final int _cursorIndexOfDutyDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dutyDate");
          final int _cursorIndexOfDutyType = CursorUtil.getColumnIndexOrThrow(_cursor, "dutyType");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<ShiftAssignmentEntity> _result = new ArrayList<ShiftAssignmentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ShiftAssignmentEntity _item;
            _item = new ShiftAssignmentEntity();
            _item.id = _cursor.getLong(_cursorIndexOfId);
            _item.elderId = _cursor.getLong(_cursorIndexOfElderId);
            _item.familyUserId = _cursor.getLong(_cursorIndexOfFamilyUserId);
            if (_cursor.isNull(_cursorIndexOfDutyDate)) {
              _item.dutyDate = null;
            } else {
              _item.dutyDate = _cursor.getString(_cursorIndexOfDutyDate);
            }
            if (_cursor.isNull(_cursorIndexOfDutyType)) {
              _item.dutyType = null;
            } else {
              _item.dutyType = _cursor.getString(_cursorIndexOfDutyType);
            }
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
  public ShiftAssignmentEntity getTodayShift(final long elderId, final String date) {
    final String _sql = "SELECT * FROM shift_assignments WHERE elderId = ? AND dutyDate = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, elderId);
    _argIndex = 2;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfElderId = CursorUtil.getColumnIndexOrThrow(_cursor, "elderId");
      final int _cursorIndexOfFamilyUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "familyUserId");
      final int _cursorIndexOfDutyDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dutyDate");
      final int _cursorIndexOfDutyType = CursorUtil.getColumnIndexOrThrow(_cursor, "dutyType");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final ShiftAssignmentEntity _result;
      if (_cursor.moveToFirst()) {
        _result = new ShiftAssignmentEntity();
        _result.id = _cursor.getLong(_cursorIndexOfId);
        _result.elderId = _cursor.getLong(_cursorIndexOfElderId);
        _result.familyUserId = _cursor.getLong(_cursorIndexOfFamilyUserId);
        if (_cursor.isNull(_cursorIndexOfDutyDate)) {
          _result.dutyDate = null;
        } else {
          _result.dutyDate = _cursor.getString(_cursorIndexOfDutyDate);
        }
        if (_cursor.isNull(_cursorIndexOfDutyType)) {
          _result.dutyType = null;
        } else {
          _result.dutyType = _cursor.getString(_cursorIndexOfDutyType);
        }
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

  @Override
  public LiveData<List<ShiftAssignmentEntity>> getActiveShiftsByUser(final long userId) {
    final String _sql = "SELECT * FROM shift_assignments WHERE familyUserId = ? AND status = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"shift_assignments"}, false, new Callable<List<ShiftAssignmentEntity>>() {
      @Override
      @Nullable
      public List<ShiftAssignmentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfElderId = CursorUtil.getColumnIndexOrThrow(_cursor, "elderId");
          final int _cursorIndexOfFamilyUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "familyUserId");
          final int _cursorIndexOfDutyDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dutyDate");
          final int _cursorIndexOfDutyType = CursorUtil.getColumnIndexOrThrow(_cursor, "dutyType");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<ShiftAssignmentEntity> _result = new ArrayList<ShiftAssignmentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ShiftAssignmentEntity _item;
            _item = new ShiftAssignmentEntity();
            _item.id = _cursor.getLong(_cursorIndexOfId);
            _item.elderId = _cursor.getLong(_cursorIndexOfElderId);
            _item.familyUserId = _cursor.getLong(_cursorIndexOfFamilyUserId);
            if (_cursor.isNull(_cursorIndexOfDutyDate)) {
              _item.dutyDate = null;
            } else {
              _item.dutyDate = _cursor.getString(_cursorIndexOfDutyDate);
            }
            if (_cursor.isNull(_cursorIndexOfDutyType)) {
              _item.dutyType = null;
            } else {
              _item.dutyType = _cursor.getString(_cursorIndexOfDutyType);
            }
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
