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
package com.crashtestdummylimited.navydecoder.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import com.crashtestdummylimited.navydecoder.R;
import java.io.IOException;
import java.util.Locale;

/** Changelog builder to create the changelog screen. */
public final class ChangelogBuilder {
  /** LOG Constant. */
  private static final String TAG = "ChangelogBuilder";

  /** Private constructor. */
  private ChangelogBuilder() {}

  /**
   * Show the dialog only if not already shown for this version of the application.
   *
   * @param context the context
   * @param listener the listener to be set for the clickevent of the 'OK' button
   * @return the 'Changelog' dialog
   */
  public static AlertDialog create(final Context context, final Dialog.OnClickListener listener) {

    final View view = LayoutInflater.from(context).inflate(R.layout.changelog, null);
    WebView webView = view.findViewById(R.id.changelogcontent);
    webView.getSettings().setJavaScriptEnabled(false);

    int bgColorInt = ContextCompat.getColor(context, R.color.changeLogBackgroundColor);
    int textColorInt = ContextCompat.getColor(context, R.color.changeLogTextColor);
    String bgHex = String.format(Locale.US, "#%06X", (0xFFFFFF & bgColorInt));
    String textHex = String.format(Locale.US, "#%06X", (0xFFFFFF & textColorInt));
    String css =
        "<style>"
            + "body{background-color:"
            + bgHex
            + ";color:"
            + textHex
            + ";margin:8px;padding:0;font-family:sans-serif;}"
            + "</style>";

    try {
      String rawContent = DataLoader.loadData(context, R.raw.changelog);
      if (rawContent != null) {
        webView.loadDataWithBaseURL(null, css + rawContent, "text/html", "UTF-8", null);
      }
    } catch (IOException ioe) {
      Log.e(TAG, "Error reading changelog file!", ioe);
    }

    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.ChangeDialogStyle);
    alertDialog.setView(view);
    alertDialog.setPositiveButton(android.R.string.ok, listener);

    int hPadPx =
        (int)
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 16, context.getResources().getDisplayMetrics());
    int vPadPx =
        (int)
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 14, context.getResources().getDisplayMetrics());
    TextView titleView = new TextView(context);
    titleView.setText(context.getString(R.string.changelog_title));
    titleView.setGravity(Gravity.CENTER_HORIZONTAL);
    titleView.setTextColor(ContextCompat.getColor(context, R.color.changeLogTextColor));
    titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
    titleView.setTypeface(null, Typeface.BOLD);
    titleView.setPadding(hPadPx, vPadPx, hPadPx, vPadPx);
    alertDialog.setCustomTitle(titleView);

    return alertDialog.create();
  }
}
