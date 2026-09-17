package com.android.settings.display;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;

public class DesktopPillPreferenceController extends BasePreferenceController 
        implements Preference.OnPreferenceChangeListener {

    private static final String KEY_DESKTOP_PILL = "desktop_mode_pill_style";

    public DesktopPillPreferenceController(Context context, String key) {
        super(context, key);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public void updateState(Preference preference) {
        super.updateState(preference);
        int value = Settings.Global.getInt(mContext.getContentResolver(), KEY_DESKTOP_PILL, 0);
        ((ListPreference) preference).setValue(String.valueOf(value));
        ((ListPreference) preference).setSummary(((ListPreference) preference).getEntry());
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        int value = Integer.parseInt((String) newValue);
        Settings.Global.putInt(mContext.getContentResolver(), KEY_DESKTOP_PILL, value);
        updateState(preference);
        return true;
    }
}
