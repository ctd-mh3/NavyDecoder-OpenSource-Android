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
import com.crashtestdummylimited.navydecoder.databinding.MainScreenBinding;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;

public class NavyReference extends AppCompatActivity {

  private static final String TAG = NavyReference.class.getSimpleName();

  private MainScreenBinding mBinding;

  /**
   * Key for latest version code preference.
   */
  private static final String LAST_VERSION_CODE_KEY = "last_version_code";

  private ReferenceData mReferenceData;
  private RFASReferenceData mRfasReferenceData;

  private enum Layouts {
    NON_RFAS,
    RFAS
  }

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

    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        this, R.array.level0_list_array, android.R.layout.simple_spinner_item);

    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    mBinding.mainDecodeSpinner.setAdapter(adapter);

    mBinding.mainDecodeSpinner.setOnItemSelectedListener(listener);
  }

  private void setupSpinnerFromArray(Spinner spinner, String[] stringArray, OnItemSelectedListener listener) {

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

    mBinding = MainScreenBinding.inflate(getLayoutInflater());
    View view = mBinding.getRoot();
    setContentView(view);

    // Set Title Bar Title to official app name
    getSupportActionBar().setTitle(R.string.app_name);

    // Setup all the spinners
    setupSpinner(new MainDecoderItemSelectedListener());
    setupSpinnerFromArray(mBinding.secondaryDecodeSpinner, (new IMSCodes()).getKeys(), new SecondaryDecoderItemSelectedListener());

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


  private void updateLayoutDueToMainDecoderItemSelection(Layouts layout) {

    if (layout == Layouts.NON_RFAS) {
      // Default layout w/ simple secondary spinner
      mBinding.secondaryDecodeSpinner.setVisibility(android.view.View.VISIBLE);
      mBinding.rfasSpinnerLayout.setVisibility(android.view.View.GONE);
    }
    else {
      // Layout option for RFAS spinner layout
      mBinding.secondaryDecodeSpinner.setVisibility(android.view.View.GONE);
      mBinding.rfasSpinnerLayout.setVisibility(android.view.View.VISIBLE);
     }
  }


  private class MainDecoderItemSelectedListener implements OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent,
                               View view, int pos, long id) {

      String selectedString = parent.getItemAtPosition(pos).toString();

      switch (selectedString) {
        case "Enlisted Rating Codes":
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = new RatingCodes();
          setupSpinnerFromArray(mBinding.secondaryDecodeSpinner, mReferenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "IMS Codes":
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = new IMSCodes();
          setupSpinnerFromArray(mBinding.secondaryDecodeSpinner, mReferenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "MAS Codes":
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = new MASCodes();
          setupSpinnerFromArray(mBinding.secondaryDecodeSpinner, mReferenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "Navy Reserve Activity Codes":
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = new NRACodes();
          setupSpinnerFromArray(mBinding.secondaryDecodeSpinner, mReferenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "NOBC Codes":
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = new NOBCCodes();
          setupSpinnerFromArray(mBinding.secondaryDecodeSpinner, mReferenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "Officer Billet Codes":
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = new OfficerBilletCodes();
          setupSpinnerFromArray(mBinding.secondaryDecodeSpinner, mReferenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "Officer Designator Codes":
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = new OfficerDesignatorCodes();
          setupSpinnerFromArray(mBinding.secondaryDecodeSpinner, mReferenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "Officer Paygrade Codes":
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = new OfficerPaygradeCodes();
          setupSpinnerFromArray(mBinding.secondaryDecodeSpinner, mReferenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "RBSC Billet Codes":
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = new RBSCBilletCodes();
          setupSpinnerFromArray(mBinding.secondaryDecodeSpinner, mReferenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "Reserve Program Codes":
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = new ReserveProgramCodes();
          setupSpinnerFromArray(mBinding.secondaryDecodeSpinner, mReferenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "Reserve Unit Identification Codes":
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = new RUICCodes();
          setupSpinnerFromArray(mBinding.secondaryDecodeSpinner, mReferenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        case "RFAS-Enlisted Codes":
          updateLayoutDueToMainDecoderItemSelection(Layouts.RFAS);
          mRfasReferenceData = new RFASEnlistedCodes();
          setupSpinnerFromArray(mBinding.rfasFirstCharacter, mRfasReferenceData.getFirstCharacterKeys(), new RFASDecoderItemSelectedListener());
          setupSpinnerFromArray(mBinding.rfasSecondAndThirdCharacter, mRfasReferenceData.getSecondAndThirdCharacterKeys(), new RFASDecoderItemSelectedListener());
          setupSpinnerFromArray(mBinding.rfasFourthCharacter, mRfasReferenceData.getFourthCharacterKeys(), new RFASDecoderItemSelectedListener());
          break;
        case "RFAS-Officer Codes":
          updateLayoutDueToMainDecoderItemSelection(Layouts.RFAS);
          mRfasReferenceData = new RFASOfficerCodes();
          setupSpinnerFromArray(mBinding.rfasFirstCharacter, mRfasReferenceData.getFirstCharacterKeys(), new RFASDecoderItemSelectedListener());
          setupSpinnerFromArray(mBinding.rfasSecondAndThirdCharacter, mRfasReferenceData.getSecondAndThirdCharacterKeys(), new RFASDecoderItemSelectedListener());
          setupSpinnerFromArray(mBinding.rfasFourthCharacter, mRfasReferenceData.getFourthCharacterKeys(), new RFASDecoderItemSelectedListener());
          break;
        case "Subspeciality Codes":
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = new SSPCodes();
          setupSpinnerFromArray(mBinding.secondaryDecodeSpinner, mReferenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
          break;
        default:
          // Unclear if this can occur, but set a default value to prevent referenceData null pointer reference
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = new RatingCodes();
          setupSpinnerFromArray(mBinding.secondaryDecodeSpinner, mReferenceData.getKeys(), new SecondaryDecoderItemSelectedListener());
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

      String firstCharacterKey = mBinding.rfasFirstCharacter.getItemAtPosition(mBinding.rfasFirstCharacter.getSelectedItemPosition()).toString();
      String firstCharacterValue = mRfasReferenceData.getFirstCharacterValue(firstCharacterKey);

      String secondAndThirdCharacterKey = mBinding.rfasSecondAndThirdCharacter.getItemAtPosition(mBinding.rfasSecondAndThirdCharacter.getSelectedItemPosition()).toString();
      String secondAndThirdCharacterValue = mRfasReferenceData.getSecondAndThirdCharacterValue(secondAndThirdCharacterKey);

      String fourthCharacterKey = mBinding.rfasFourthCharacter.getItemAtPosition(mBinding.rfasFourthCharacter.getSelectedItemPosition()).toString();
      String fourthCharacterValue = mRfasReferenceData.getFourthCharacterValue(fourthCharacterKey);


      String resultString = firstCharacterValue + "\n" + secondAndThirdCharacterValue + "\n" + fourthCharacterValue;
      mBinding.decodeDescription.setText(resultString);

      String SOURCE_INFO = mRfasReferenceData.getSourceInfo();
      mBinding.sourceDescription.setText(SOURCE_INFO);
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

      if (mReferenceData != null) {
        // Ensure user referenceData is not null.  As of 21JUL2012, there were app crashes when
        //   referenceData was used without this check.
        resultString = mReferenceData.getValue(key);
        sourceInfo = mReferenceData.getSourceInfo();
      } else {
        //  In case the referenceData has not been set yet, just show blank
        resultString = "";
        sourceInfo = "";
      }

      mBinding.decodeDescription.setText(resultString);
      mBinding.sourceDescription.setText(sourceInfo);
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
    // Get the versionCode of the Package, which must be different (incremented) in each release
    //  in AndroidManifest.xml
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