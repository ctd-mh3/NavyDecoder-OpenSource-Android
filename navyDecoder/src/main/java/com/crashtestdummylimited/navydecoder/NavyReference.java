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
package com.crashtestdummylimited.navydecoder;

import com.crashtestdummylimited.navydecoder.controller.MenuOptions;
import com.crashtestdummylimited.navydecoder.model.IMSCodes;
import com.crashtestdummylimited.navydecoder.model.MASCodes;
import com.crashtestdummylimited.navydecoder.model.NOBCCodes;
import com.crashtestdummylimited.navydecoder.model.NRACodes;
import com.crashtestdummylimited.navydecoder.model.OfficerBilletCodes;
import com.crashtestdummylimited.navydecoder.model.OfficerDesignatorCodes;
import com.crashtestdummylimited.navydecoder.model.OfficerPaygradeCodes;
import com.crashtestdummylimited.navydecoder.model.RBSCBilletCodes;
import com.crashtestdummylimited.navydecoder.model.RFASEnlistedCodes;
import com.crashtestdummylimited.navydecoder.model.RFASOfficerCodes;
import com.crashtestdummylimited.navydecoder.model.RFASReferenceData;
import com.crashtestdummylimited.navydecoder.model.RUICCodes;
import com.crashtestdummylimited.navydecoder.model.RatingCodes;
import com.crashtestdummylimited.navydecoder.model.ReferenceData;
import com.crashtestdummylimited.navydecoder.model.ReserveProgramCodes;
import com.crashtestdummylimited.navydecoder.model.SSPCodes;
import com.crashtestdummylimited.navydecoder.ui.AppRater;
import com.crashtestdummylimited.navydecoder.util.ChangelogBuilder;
import com.crashtestdummylimited.navydecoder.util.CommonUtilities;

import android.content.SharedPreferences;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;

public class NavyReference extends AppCompatActivity {

  private static final String TAG = NavyReference.class.getSimpleName();

  /**
   * Key for latest version code preference.
   */
  private static final String LAST_VERSION_CODE_KEY = "last_version_code";

  private ReferenceData referenceData;
  private RFASReferenceData rfasReferenceData;

  //*************************************************************************
  //
  //  Overwritten to support menu 
  //
  //*************************************************************************

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuOptions.onCreateOptionsMenu(this, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    MenuOptions.onOptionsItemSelected(this, item);
    return true;
  }
  //*************************************************************************
  //  End Menu Support Code
  //*************************************************************************


  private void setupSpinner(OnItemSelectedListener listener) {
    Spinner spinner = findViewById(R.id.mainDecodeSpinner);

    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        this, R.array.level0_list_array, android.R.layout.simple_spinner_item);

    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);

    spinner.setOnItemSelectedListener(listener);
  }

  private void setupSpinnerFromArray(int spinnerId, String[] stringArray, OnItemSelectedListener listener) {
    Spinner spinner = findViewById(spinnerId);

    ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(
        this, android.R.layout.simple_spinner_item, stringArray);

    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    spinner.setAdapter(adapter);

    spinner.setOnItemSelectedListener(listener);
  }


  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setTheme(R.style.ApplicationTheme);

    setContentView(R.layout.main_screen);

    // Setup all the spinners
    setupSpinner(new MainDecoderItemSelectedListener());
    setupSpinnerFromArray(R.id.secondaryDecodeSpinner, (new IMSCodes()).getKeys(), new SecondaryDecoderItemSelectedListener());

    // For debugging
    //AppRater.showRateDialog(this, null);
    //showChangelog();

    // For production
    AppRater.app_launched(this);

    /* show changelog */
    if (isUpdate()) {
      showChangelog();
    }
  }


  private void updateLayoutDueToMainDecoderItemSelection(int pos) {

    // TODO- Do this in a more robust manor
    switch (pos) {

      // Default layout w/ simple secondary spinner 
      case 0:
        findViewById(R.id.secondaryDecodeSpinner).setVisibility(android.view.View.VISIBLE);
        findViewById(R.id.rfasEnlistedSpinnerLayout).setVisibility(android.view.View.GONE);
        findViewById(R.id.rfasOfficerSpinnerLayout).setVisibility(android.view.View.GONE);
        break;
      // Layout option for RFAS-Enlisted spinner layout
      case 1:
        findViewById(R.id.secondaryDecodeSpinner).setVisibility(android.view.View.GONE);
        findViewById(R.id.rfasEnlistedSpinnerLayout).setVisibility(android.view.View.VISIBLE);
        findViewById(R.id.rfasOfficerSpinnerLayout).setVisibility(android.view.View.GONE);
        break;
      // Layout option for RFAS-Officer spinner layout
      case 2:
        findViewById(R.id.secondaryDecodeSpinner).setVisibility(android.view.View.GONE);
        findViewById(R.id.rfasEnlistedSpinnerLayout).setVisibility(android.view.View.GONE);
        findViewById(R.id.rfasOfficerSpinnerLayout).setVisibility(android.view.View.VISIBLE);
        break;
    }
  }


  private class MainDecoderItemSelectedListener implements OnItemSelectedListener {

    @SuppressWarnings("DuplicateBranchesInSwitch")
    public void onItemSelected(AdapterView<?> parent,
                               View view, int pos, long id) {

      String selectedString = parent.getItemAtPosition(pos).toString();

      int layoutOption = 0;
/*
          <item>IMS Codes</item>
          <item>MAS Codes</item>
          <item>Navy Reserve Activity Codes</item>
          <item>NOBC Codes</item>
          <item>Officer Billet Codes</item>
          <item>Officer Designator Codes</item>
          <item>Officer Paygrade Codes</item>
          <item>RBSC Billet Codes</item>
          <item>Reserve Program Codes</item>
          <item>Reserve Unit Identification Codes</item>
          <item>RFAS-Enlisted Codes</item>
          <item>RFAS-Officer Codes</item>
          <item>Subspeciality Codes</item>
*/
      switch (selectedString) {
        case "Enlisted Rating Codes":
          updateLayoutDueToMainDecoderItemSelection(layoutOption);
          referenceData = new RatingCodes();
          setupSpinnerFromArray(R.id.secondaryDecodeSpinner, referenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "IMS Codes":
          updateLayoutDueToMainDecoderItemSelection(layoutOption);
          referenceData = new IMSCodes();
          setupSpinnerFromArray(R.id.secondaryDecodeSpinner, referenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "MAS Codes":
          updateLayoutDueToMainDecoderItemSelection(layoutOption);
          referenceData = new MASCodes();
          setupSpinnerFromArray(R.id.secondaryDecodeSpinner, referenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "Navy Reserve Activity Codes":
          updateLayoutDueToMainDecoderItemSelection(layoutOption);
          referenceData = new NRACodes();
          setupSpinnerFromArray(R.id.secondaryDecodeSpinner, referenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "NOBC Codes":
          updateLayoutDueToMainDecoderItemSelection(layoutOption);
          referenceData = new NOBCCodes();
          setupSpinnerFromArray(R.id.secondaryDecodeSpinner, referenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "Officer Billet Codes":
          updateLayoutDueToMainDecoderItemSelection(layoutOption);
          referenceData = new OfficerBilletCodes();
          setupSpinnerFromArray(R.id.secondaryDecodeSpinner, referenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "Officer Designator Codes":
          updateLayoutDueToMainDecoderItemSelection(layoutOption);
          referenceData = new OfficerDesignatorCodes();
          setupSpinnerFromArray(R.id.secondaryDecodeSpinner, referenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "Officer Paygrade Codes":
          updateLayoutDueToMainDecoderItemSelection(layoutOption);
          referenceData = new OfficerPaygradeCodes();
          setupSpinnerFromArray(R.id.secondaryDecodeSpinner, referenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "RBSC Billet Codes":
          updateLayoutDueToMainDecoderItemSelection(layoutOption);
          referenceData = new RBSCBilletCodes();
          setupSpinnerFromArray(R.id.secondaryDecodeSpinner, referenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "Reserve Program Codes":
          updateLayoutDueToMainDecoderItemSelection(layoutOption);
          referenceData = new ReserveProgramCodes();
          setupSpinnerFromArray(R.id.secondaryDecodeSpinner, referenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "Reserve Unit Identification Codes":
          updateLayoutDueToMainDecoderItemSelection(layoutOption);
          referenceData = new RUICCodes();
          setupSpinnerFromArray(R.id.secondaryDecodeSpinner, referenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "RFAS-Enlisted Codes":
          layoutOption = 1;
          updateLayoutDueToMainDecoderItemSelection(layoutOption);
          rfasReferenceData = new RFASEnlistedCodes();
          setupSpinnerFromArray(R.id.rfasEnlistedFirstCharacter, rfasReferenceData.getFirstCharacterKeys(), new RFASDecoderItemSelectedListener());
          setupSpinnerFromArray(R.id.rfasEnlistedSecondAndThirdCharacter, rfasReferenceData.getSecondAndThirdCharacterKeys(), new RFASDecoderItemSelectedListener());
          setupSpinnerFromArray(R.id.rfasEnlistedFourthCharacter, rfasReferenceData.getFourthCharacterKeys(), new RFASDecoderItemSelectedListener());
          break;
        case "RFAS-Officer Codes":
          layoutOption = 1;
          updateLayoutDueToMainDecoderItemSelection(layoutOption);
          rfasReferenceData = new RFASOfficerCodes();
          setupSpinnerFromArray(R.id.rfasEnlistedFirstCharacter, rfasReferenceData.getFirstCharacterKeys(), new RFASDecoderItemSelectedListener());
          setupSpinnerFromArray(R.id.rfasEnlistedSecondAndThirdCharacter, rfasReferenceData.getSecondAndThirdCharacterKeys(), new RFASDecoderItemSelectedListener());
          setupSpinnerFromArray(R.id.rfasEnlistedFourthCharacter, rfasReferenceData.getFourthCharacterKeys(), new RFASDecoderItemSelectedListener());
          break;
        case "Subspeciality Codes":
          updateLayoutDueToMainDecoderItemSelection(layoutOption);
          referenceData = new SSPCodes();
          setupSpinnerFromArray(R.id.secondaryDecodeSpinner, referenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        default:
          // Unclear if this can occur, but set a default value to prevent referenceData null pointer reference
          updateLayoutDueToMainDecoderItemSelection(layoutOption);
          referenceData = new RatingCodes();
          setupSpinnerFromArray(R.id.secondaryDecodeSpinner, referenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
      }
    }

    public void onNothingSelected(AdapterView<?> parent) {
      // Do nothing.
    }
  }


  private class RFASDecoderItemSelectedListener implements OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent,
                               View view, int pos, long id) {

      Spinner tempSpinner = findViewById(R.id.rfasEnlistedFirstCharacter);
      String firstCharacterKey = tempSpinner.getItemAtPosition(tempSpinner.getSelectedItemPosition()).toString();
      String firstCharacterValue = rfasReferenceData.getFirstCharacterValue(firstCharacterKey);


      tempSpinner = findViewById(R.id.rfasEnlistedSecondAndThirdCharacter);
      String secondAndThirdCharacterKey = tempSpinner.getItemAtPosition(tempSpinner.getSelectedItemPosition()).toString();
      String secondAndThirdCharacterValue = rfasReferenceData.getSecondAndThirdCharacterValue(secondAndThirdCharacterKey);

      tempSpinner = findViewById(R.id.rfasEnlistedFourthCharacter);
      String fourthCharacterKey = tempSpinner.getItemAtPosition(tempSpinner.getSelectedItemPosition()).toString();
      String fourthCharacterValue = rfasReferenceData.getFourthCharacterValue(fourthCharacterKey);


      String resultString = firstCharacterValue + "\n" + secondAndThirdCharacterValue + "\n" + fourthCharacterValue;

      final TextView decodeTextView = findViewById(R.id.decodeDescription);

      decodeTextView.setText(resultString);

      String SOURCE_INFO = rfasReferenceData.getSourceInfo();

      final TextView sourceTextView = findViewById(R.id.sourceDescription);
      sourceTextView.setText(SOURCE_INFO);
    }

    public void onNothingSelected(AdapterView<?> parent) {
      // Do nothing.
    }
  }


  private class SecondaryDecoderItemSelectedListener implements OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent,
                               View view, int pos, long id) {

      String key = parent.getItemAtPosition(pos).toString();

      String resultString;
      String sourceInfo;

      if (referenceData != null) {
        // Only user referenceData is not null.  As of 21JUL2012, there were app crashes when 
        //   referenceData was used without this check.
        resultString = referenceData.getValue(key);
        sourceInfo = referenceData.getSourceInfo();
      } else {
        //  In case the referenceData has not been set yet, just show blank
        resultString = "";
        sourceInfo = "";
      }

      final TextView decodeTextView = findViewById(R.id.decodeDescription);
      decodeTextView.setText(resultString);

      final TextView sourceTextView = findViewById(R.id.sourceDescription);
      sourceTextView.setText(sourceInfo);
    }

    public void onNothingSelected(AdapterView<?> parent) {
      // Do nothing.
    }
  }

  /**
   * checks if the app is started for the first time (after an update).
   *
   * @return <code>true</code> if this is the first start (after an update)
   * else <code>false</code>
   */
  private boolean isUpdate() {
    // Get the versionCode of the Package, which must be different
    // (incremented) in each release on the market in the
    // AndroidManifest.xml
    final long versionCode = CommonUtilities.getActualVersionCode(this);

    final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    final long lastVersionCode = prefs.getLong(LAST_VERSION_CODE_KEY, 0);

    if (versionCode != lastVersionCode) {
      Log.i(TAG, "versionCode " + versionCode + " is different from the last known version "
          + lastVersionCode);
      return true;
    } else {
      Log.i(TAG, "versionCode " + versionCode + " is already known");
      return false;
    }
  }

  private void showChangelog() {
    final long versionCode = CommonUtilities.getActualVersionCode(this);
    final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
    ChangelogBuilder.create(this, (dialogInterface, i) -> {
      // Mark this version as read
      sp.edit().putLong(LAST_VERSION_CODE_KEY, versionCode).apply();

      dialogInterface.dismiss();
    }).show();
  }

}