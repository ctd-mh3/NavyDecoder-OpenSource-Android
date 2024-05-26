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
package com.crashtestdummylimited.navydecoder.controller;

import com.crashtestdummylimited.navydecoder.R;
import com.crashtestdummylimited.navydecoder.util.CommonUtilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MenuOptions {

  //*************************************************************************
  //
  //  Overwritten to support menu
  //
  //*************************************************************************

  //	@Override
  public static void onCreateOptionsMenu(Activity activity, Menu menu) {
    MenuInflater inflater = activity.getMenuInflater();
    inflater.inflate(R.menu.menu, menu);
  }

  //	@Override
  public static void onOptionsItemSelected(Activity activity, MenuItem item) {

    int itemId = item.getItemId();// For email http://mobile.tutsplus.com/tutorials/android/android-email-intent/
    if (itemId == R.id.optionsMenuAbout) {
      // http://stackoverflow.com/questions/3661524/android-modal-dialog-with-changelog-for-app-shown-only-first-time-after-install
      String message = activity.getString(R.string.aboutApplicationPurpose) +
          "\n\n" +
          activity.getString(R.string.aboutDeveloper) +
          "\n\n" +
          activity.getString(R.string.aboutComments) +
          "\n\n" +
          activity.getString(R.string.aboutDisclaimer) +
          "\n\n" +
          activity.getString(R.string.aboutVersion) +
          CommonUtilities.getAppVersionName(activity);

      AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
      alertDialog.setTitle(activity.getString(R.string.aboutTitle));
      alertDialog.setMessage(message);
      alertDialog.setPositiveButton("OK", (dialog, which) -> {
      });
      alertDialog.show();
    } else if (itemId == R.id.optionsMenuOpenSource) {
      // http://stackoverflow.com/questions/3661524/android-modal-dialog-with-changelog-for-app-shown-only-first-time-after-install
      String message = activity.getString(R.string.opensourceNoticeLine1) +
          "\n\n" +
          activity.getString(R.string.opensourceNoticeLine2) +
          "\n\n" +
          activity.getString(R.string.opensourceNoticeLine3);

      AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
      alertDialog.setTitle(activity.getString(R.string.opensourceTitle));
      alertDialog.setMessage(message);
      alertDialog.setPositiveButton("OK", (dialog, which) -> {
      });
      alertDialog.show();
    } else if (itemId == R.id.optionsMenuEmailAuthor) {
      Intent emailIntent = new Intent(Intent.ACTION_SEND);
      emailIntent.setType("message/rfc822");
      emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{activity.getString(R.string.authorEmailAddress)}); // recipients
      emailIntent.putExtra(Intent.EXTRA_SUBJECT,
          activity.getString(R.string.authorEmailSubject) +
              "(" + CommonUtilities.getAppVersionName(activity) + ")");

      // Always use string resources for UI text.
      String title = activity.getResources().getString(R.string.chooserTitle);
      // Create intent to show chooser
      Intent chooser = Intent.createChooser(emailIntent, title);

      // Try to invoke the intent.
      try {
        activity.startActivity(chooser);
      } catch (ActivityNotFoundException e) {
        // Define what your app should do if no activity can handle the intent.
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(activity);
        alertDialog2.setTitle(activity.getString(R.string.emailErrorTitle));
        alertDialog2.setMessage(R.string.emailErrorMessage);
        alertDialog2.setPositiveButton("OK", (dialog, which) -> {
        });
        alertDialog2.show();
      }
    }
  }
  //*************************************************************************
  //  End Menu Support Code
  //*************************************************************************

}
