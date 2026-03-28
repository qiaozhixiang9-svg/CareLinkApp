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
import com.carelink.app.data.local.entity.CareNoteEntity;
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
public final class CareNoteDao_Impl implements CareNoteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CareNoteEntity> __insertionAdapterOfCareNoteEntity;

  private final EntityDeletionOrUpdateAdapter<CareNoteEntity> __updateAdapterOfCareNoteEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public CareNoteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCareNoteEntity = new EntityInsertionAdapter<CareNoteEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `care_notes` (`id`,`elderId`,`authorId`,`content`,`tags`,`isImportant`,`createdAt`,`imageUrl`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final CareNoteEntity entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.elderId);
        statement.bindLong(3, entity.authorId);
        if (entity.content == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.content);
        }
        if (entity.tags == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.tags);
        }
        statement.bindLong(6, entity.isImportant);
        statement.bindLong(7, entity.createdAt);
        if (entity.imageUrl == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.imageUrl);
        }
      }
    };
    this.__updateAdapterOfCareNoteEntity = new EntityDeletionOrUpdateAdapter<CareNoteEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `care_notes` SET `id` = ?,`elderId` = ?,`authorId` = ?,`content` = ?,`tags` = ?,`isImportant` = ?,`createdAt` = ?,`imageUrl` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final CareNoteEntity entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.elderId);
        statement.bindLong(3, entity.authorId);
        if (entity.content == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.content);
        }
        if (entity.tags == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.tags);
        }
        statement.bindLong(6, entity.isImportant);
        statement.bindLong(7, entity.createdAt);
        if (entity.imageUrl == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.imageUrl);
        }
        statement.bindLong(9, entity.id);
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM care_notes WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final CareNoteEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfCareNoteEntity.insertAndReturnId(entity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final CareNoteEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCareNoteEntity.handle(entity);
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
  public LiveData<List<CareNoteEntity>> getNotes(final long elderId) {
    final String _sql = "SELECT * FROM care_notes WHERE elderId = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, elderId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"care_notes"}, false, new Callable<List<CareNoteEntity>>() {
      @Override
      @Nullable
      public List<CareNoteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfElderId = CursorUtil.getColumnIndexOrThrow(_cursor, "elderId");
          final int _cursorIndexOfAuthorId = CursorUtil.getColumnIndexOrThrow(_cursor, "authorId");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfIsImportant = CursorUtil.getColumnIndexOrThrow(_cursor, "isImportant");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final List<CareNoteEntity> _result = new ArrayList<CareNoteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CareNoteEntity _item;
            _item = new CareNoteEntity();
            _item.id = _cursor.getLong(_cursorIndexOfId);
            _item.elderId = _cursor.getLong(_cursorIndexOfElderId);
            _item.authorId = _cursor.getLong(_cursorIndexOfAuthorId);
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _item.content = null;
            } else {
              _item.content = _cursor.getString(_cursorIndexOfContent);
            }
            if (_cursor.isNull(_cursorIndexOfTags)) {
              _item.tags = null;
            } else {
              _item.tags = _cursor.getString(_cursorIndexOfTags);
            }
            _item.isImportant = _cursor.getInt(_cursorIndexOfIsImportant);
            _item.createdAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _item.imageUrl = null;
            } else {
              _item.imageUrl = _cursor.getString(_cursorIndexOfImageUrl);
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
  public LiveData<List<CareNoteEntity>> getImportantNotes(final long elderId) {
    final String _sql = "SELECT * FROM care_notes WHERE elderId = ? AND isImportant = 1 ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, elderId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"care_notes"}, false, new Callable<List<CareNoteEntity>>() {
      @Override
      @Nullable
      public List<CareNoteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfElderId = CursorUtil.getColumnIndexOrThrow(_cursor, "elderId");
          final int _cursorIndexOfAuthorId = CursorUtil.getColumnIndexOrThrow(_cursor, "authorId");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfIsImportant = CursorUtil.getColumnIndexOrThrow(_cursor, "isImportant");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final List<CareNoteEntity> _result = new ArrayList<CareNoteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CareNoteEntity _item;
            _item = new CareNoteEntity();
            _item.id = _cursor.getLong(_cursorIndexOfId);
            _item.elderId = _cursor.getLong(_cursorIndexOfElderId);
            _item.authorId = _cursor.getLong(_cursorIndexOfAuthorId);
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _item.content = null;
            } else {
              _item.content = _cursor.getString(_cursorIndexOfContent);
            }
            if (_cursor.isNull(_cursorIndexOfTags)) {
              _item.tags = null;
            } else {
              _item.tags = _cursor.getString(_cursorIndexOfTags);
            }
            _item.isImportant = _cursor.getInt(_cursorIndexOfIsImportant);
            _item.createdAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _item.imageUrl = null;
            } else {
              _item.imageUrl = _cursor.getString(_cursorIndexOfImageUrl);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
