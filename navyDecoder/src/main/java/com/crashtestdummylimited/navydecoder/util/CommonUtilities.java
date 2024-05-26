package com.crashtestdummylimited.navydecoder.util;
/*
 * This file is part of Navy Decoder-Android.
 *
 * Navy Decoder-Android is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Navy Decoder-Android is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Navy Decoder-Android.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Copyright (c) 2011-2024 Crash Test Dummy Limited, LLC
 */

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import androidx.core.content.pm.PackageInfoCompat;

public class CommonUtilities {

  public static String getAppVersionName(Activity activity) {
    PackageInfo packageInfo;
    String strVersion;
    try {
      packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
      strVersion = packageInfo.versionName;
    } catch (NameNotFoundException e) {
      strVersion = "Unknown";
      Log.w("Unable to get ver name.", e);
    }
    return strVersion;
  }

  /**
   * get the name of the actual version.
   *
   * @param context the context
   * @return the name of the actual version
   */
  public static String getActualVersionName(final Context context) {
    // Get the versionCode of the Package, which must be different
    // (incremented) in each release on the market in the
    // AndroidManifest.xml
    try {
      return context.getPackageManager().getPackageInfo(context.getPackageName(),
          PackageManager.GET_ACTIVITIES).versionName;
    } catch (NameNotFoundException e) {
      return null;
    }
  }

  /**
   * get the code of the actual version.
   *
   * @param context the context
   * @return the code of the actual version
   */
  public static long getActualVersionCode(final Context context) {
    // Get the versionCode of the Package, which must be different
    // (incremented) in each release on the market in the
    // AndroidManifest.xml
    try {
      PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
      return PackageInfoCompat.getLongVersionCode(pInfo);
    } catch (NameNotFoundException e) {
      return 0;
    }
  }

}