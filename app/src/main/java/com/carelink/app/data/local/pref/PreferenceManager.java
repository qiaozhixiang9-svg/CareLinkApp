package com.carelink.app.data.local.pref;

import android.content.Context;
import android.content.SharedPreferences;

/** SharedPreferences 封装管理类 */
public class PreferenceManager {

    private static final String PREF_NAME = "care_pref";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_ID_STR = "user_id_str";
    private static final String KEY_ROLE = "role";
    private static final String KEY_ROLE_SELECT_TIME = "role_select_time";
    private static final String KEY_FAMILY_ID = "family_id";
    private static final String KEY_FAMILY_NAME = "family_name";
    private static final String KEY_INVITE_CODE = "invite_code";
    private static final String KEY_ELDER_ID = "elder_id";
    private static final String KEY_BIND_ELDER_COUNT = "bind_elder_count";
    private static final String KEY_BIND_FAMILY_COUNT = "bind_family_count";
    private static final String KEY_NICKNAME = "nickname";
    private static final String KEY_AVATAR_URL = "avatar_url";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_FONT_SIZE = "font_size";
    private static final String KEY_VOICE_ENABLED = "voice_enabled";
    private static final String KEY_EMERGENCY_CONTACT = "emergency_contact";
    private static final String KEY_REMINDER_ENABLED = "reminder_enabled";
    private static final String KEY_DEFAULT_CALENDAR_VIEW = "default_calendar_view";
    private static final String KEY_AUDIO_AUTOPLAY = "audio_autoplay";
    private static final String KEY_SHARE_STATUS = "share_status";
    private static final String KEY_SHARE_LAST_LOCATION = "share_last_location";
    private static final String KEY_SHARE_LAST_TIME = "share_last_time";
    private static final String KEY_SHARE_END_TIME = "share_end_time";


    private final SharedPreferences sp;
    public PreferenceManager(Context context) { sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE); }

    public void saveToken(String token) { sp.edit().putString(KEY_TOKEN, token).apply(); }
    public String getToken() { return sp.getString(KEY_TOKEN, ""); }
    public void saveUserId(long userId) { sp.edit().putLong(KEY_USER_ID, userId).apply(); }
    public long getUserId() { return sp.getLong(KEY_USER_ID, -1); }
    public void saveUserIdStr(String userId) { if (userId != null) sp.edit().putString(KEY_USER_ID_STR, userId).apply(); }
    public String getUserIdStr() { return sp.getString(KEY_USER_ID_STR, ""); }
    public void saveRole(String role) { sp.edit().putString(KEY_ROLE, role).apply(); }
    public String getRole() { return sp.getString(KEY_ROLE, ""); }
    public void saveRoleSelectTime(long timestamp) { sp.edit().putLong(KEY_ROLE_SELECT_TIME, timestamp).apply(); }
    public long getRoleSelectTime() { return sp.getLong(KEY_ROLE_SELECT_TIME, 0); }
    public boolean canSwitchRole() { return true; }
    public long getDaysUntilCanSwitch() { return 0; }
    public void saveFamilyId(long familyId) { sp.edit().putLong(KEY_FAMILY_ID, familyId).apply(); }
    public long getFamilyId() { return sp.getLong(KEY_FAMILY_ID, -1); }
    public void saveElderId(long elderId) { sp.edit().putLong(KEY_ELDER_ID, elderId).apply(); }
    public long getElderId() { return sp.getLong(KEY_ELDER_ID, -1); }
    public void saveFamilyName(String name) { sp.edit().putString(KEY_FAMILY_NAME, name).apply(); }
    public String getFamilyName() { return sp.getString(KEY_FAMILY_NAME, ""); }
    public void saveInviteCode(String code) { sp.edit().putString(KEY_INVITE_CODE, code).apply(); }
    public String getInviteCode() { return sp.getString(KEY_INVITE_CODE, ""); }
    public void saveBindElderCount(int count) { sp.edit().putInt(KEY_BIND_ELDER_COUNT, count).apply(); }
    public int getBindElderCount() { return sp.getInt(KEY_BIND_ELDER_COUNT, 0); }
    public boolean canBindMoreElder() { return getBindElderCount() < 2; }
    public void saveBindFamilyCount(int count) { sp.edit().putInt(KEY_BIND_FAMILY_COUNT, count).apply(); }
    public int getBindFamilyCount() { return sp.getInt(KEY_BIND_FAMILY_COUNT, 0); }
    public boolean canBindMoreFamily() { return getBindFamilyCount() < 5; }
    public void saveNickname(String nickname) { sp.edit().putString(KEY_NICKNAME, nickname).apply(); }
    public String getNickname() { return sp.getString(KEY_NICKNAME, ""); }
    public void saveAvatarUrl(String url) { sp.edit().putString(KEY_AVATAR_URL, url).apply(); }
    public String getAvatarUrl() { return sp.getString(KEY_AVATAR_URL, ""); }
    public void savePhone(String phone) { sp.edit().putString(KEY_PHONE, phone).apply(); }
    public String getPhone() { return sp.getString(KEY_PHONE, ""); }
    public void saveEmail(String email) { sp.edit().putString(KEY_EMAIL, email).apply(); }
    public String getEmail() { return sp.getString(KEY_EMAIL, ""); }
    public void saveFontSize(int size) { sp.edit().putInt(KEY_FONT_SIZE, size).apply(); }
    public int getFontSize() { return sp.getInt(KEY_FONT_SIZE, 18); }
    public void setVoiceEnabled(boolean enabled) { sp.edit().putBoolean(KEY_VOICE_ENABLED, enabled).apply(); }
    public boolean isVoiceEnabled() { return sp.getBoolean(KEY_VOICE_ENABLED, true); }
    public void saveEmergencyContact(String contact) { sp.edit().putString(KEY_EMERGENCY_CONTACT, contact).apply(); }
    public String getEmergencyContact() { return sp.getString(KEY_EMERGENCY_CONTACT, "120"); }
    public void setReminderEnabled(boolean enabled) { sp.edit().putBoolean(KEY_REMINDER_ENABLED, enabled).apply(); }
    public boolean isReminderEnabled() { return sp.getBoolean(KEY_REMINDER_ENABLED, true); }
    public void saveDefaultCalendarView(String value) { sp.edit().putString(KEY_DEFAULT_CALENDAR_VIEW, value).apply(); }
    public String getDefaultCalendarView() { return sp.getString(KEY_DEFAULT_CALENDAR_VIEW, "月视图"); }
    public void setAudioAutoplay(boolean enabled) { sp.edit().putBoolean(KEY_AUDIO_AUTOPLAY, enabled).apply(); }
    public boolean isAudioAutoplay() { return sp.getBoolean(KEY_AUDIO_AUTOPLAY, false); }
    public void saveShareStatus(String value) { sp.edit().putString(KEY_SHARE_STATUS, value).apply(); }
    public String getShareStatus() { return sp.getString(KEY_SHARE_STATUS, "未共享"); }
    public void saveShareLastLocation(String value) { sp.edit().putString(KEY_SHARE_LAST_LOCATION, value).apply(); }
    public String getShareLastLocation() { return sp.getString(KEY_SHARE_LAST_LOCATION, "等待主动共享"); }
    public void saveShareLastTime(String value) { sp.edit().putString(KEY_SHARE_LAST_TIME, value).apply(); }
    public String getShareLastTime() { return sp.getString(KEY_SHARE_LAST_TIME, "暂无"); }
    public void saveShareEndTime(String value) { sp.edit().putString(KEY_SHARE_END_TIME, value).apply(); }
    public String getShareEndTime() { return sp.getString(KEY_SHARE_END_TIME, "未设置"); }
    public boolean isLoggedIn() { return !getToken().isEmpty(); }
    public void clear() { sp.edit().clear().apply(); }
}
