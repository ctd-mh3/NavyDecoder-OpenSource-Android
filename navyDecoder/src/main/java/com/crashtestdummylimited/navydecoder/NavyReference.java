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

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.preference.PreferenceManager;
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
import com.crashtestdummylimited.navydecoder.util.ChangelogBuilder;
import com.crashtestdummylimited.navydecoder.util.CommonUtilities;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;

public class NavyReference extends AppCompatActivity {

  private static final String TAG = NavyReference.class.getSimpleName();

  private MainScreenBinding mBinding;

  /** Key for latest version code preference. */
  private static final String LAST_VERSION_CODE_KEY = "last_version_code";

  // For Play Store In-App Review
  private static final String REVIEW_PREFS = "review_prefs";
  private static final String KEY_FIRST_LAUNCH_MS = "first_launch_ms";
  private static final String KEY_LAST_PROMPT_MS = "last_prompt_ms";
  private static final long MIN_INSTALL_AGE_MS = 3L * 24L * 60L * 60L * 1000L; // 3 days
  private static final long COOLDOWN_MS = 7L * 24L * 60L * 60L * 1000L; // 7 days

  private ReviewManager mReviewManager;

  private ReferenceData mReferenceData;
  private RFASReferenceData mRfasReferenceData;

  // Lazily-created, cached model instances — constructed at most once per app session.
  private RatingCodes mRatingCodes;
  private IMSCodes mIMSCodes;
  private MASCodes mMASCodes;
  private NRACodes mNRACodes;
  private NOBCCodes mNOBCCodes;
  private OfficerBilletCodes mOfficerBilletCodes;
  private OfficerDesignatorCodes mOfficerDesignatorCodes;
  private OfficerPaygradeCodes mOfficerPaygradeCodes;
  private RBSCBilletCodes mRBSCBilletCodes;
  private ReserveProgramCodes mReserveProgramCodes;
  private RUICCodes mRUICCodes;
  private RFASEnlistedCodes mRFASEnlistedCodes;
  private RFASOfficerCodes mRFASOfficerCodes;
  private SSPCodes mSSPCodes;

  private enum Layouts {
    NON_RFAS,
    RFAS
  }

  // *************************************************************************
  //
  //  Overwritten to support menu
  //
  // *************************************************************************

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

  // *************************************************************************
  //  End Menu Support Code
  // *************************************************************************

  private void setupSpinner(OnItemSelectedListener listener) {

    ArrayAdapter<CharSequence> adapter =
        ArrayAdapter.createFromResource(
            this, R.array.level0_list_array, android.R.layout.simple_spinner_item);

    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    mBinding.mainDecodeSpinner.setAdapter(adapter);

    mBinding.mainDecodeSpinner.setOnItemSelectedListener(listener);
  }

  private void setupSpinnerFromArray(
      Spinner spinner, String[] stringArray, OnItemSelectedListener listener) {

    ArrayAdapter<CharSequence> adapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stringArray);

    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    spinner.setAdapter(adapter);

    spinner.setOnItemSelectedListener(listener);
  }

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (!BuildConfig.DEBUG) {
      mReviewManager = ReviewManagerFactory.create(this);
    }

    setTheme(R.style.ApplicationTheme);

    mBinding = MainScreenBinding.inflate(getLayoutInflater());
    View view = mBinding.getRoot();
    setContentView(view);

    // The Toolbar lives in our own layout, so we own its insets directly.
    // Pad its top by the status-bar height so it sits below the status bar on
    // Android 15+ edge-to-edge. The Toolbar background colour fills that space,
    // giving the appearance of a coloured status bar.
    ViewCompat.setOnApplyWindowInsetsListener(
        mBinding.toolbar,
        (v, windowInsets) -> {
          Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
          v.setPadding(0, insets.top, 0, 0);
          return windowInsets;
        });

    // Pad the scroll view bottom so content isn't hidden behind the nav bar.
    ViewCompat.setOnApplyWindowInsetsListener(
        mBinding.mainScrollView,
        (v, windowInsets) -> {
          Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
          v.setPadding(0, 0, 0, insets.bottom);
          return WindowInsetsCompat.CONSUMED;
        });

    setSupportActionBar(mBinding.toolbar);

    // Set Title Bar Title to official app name
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle(R.string.app_name);
    }

    // Setup all the spinners
    setupSpinner(new MainDecoderItemSelectedListener());
    setupSpinnerFromArray(
        mBinding.secondaryDecodeSpinner,
        (new IMSCodes()).getKeys(),
        new SecondaryDecoderItemSelectedListener());

    // For debugging
    //showChangelog();

    tryRequestReviewIfAppropriate();

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
    } else {
      // Layout option for RFAS spinner layout
      mBinding.secondaryDecodeSpinner.setVisibility(android.view.View.GONE);
      mBinding.rfasSpinnerLayout.setVisibility(android.view.View.VISIBLE);
    }
  }

  private class MainDecoderItemSelectedListener implements OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

      // Switch on position index to match level0_list_array order in strings.xml.
      // This avoids fragile string matching — renaming a display string no longer breaks dispatch.
      switch (pos) {
        case 0: // Enlisted Rating Codes
          if (mRatingCodes == null) mRatingCodes = new RatingCodes();
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = mRatingCodes;
          setupSpinnerFromArray(
              mBinding.secondaryDecodeSpinner,
              mReferenceData.getKeys(),
              new SecondaryDecoderItemSelectedListener());
          break;
        case 1: // IMS Codes
          if (mIMSCodes == null) mIMSCodes = new IMSCodes();
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = mIMSCodes;
          setupSpinnerFromArray(
              mBinding.secondaryDecodeSpinner,
              mReferenceData.getKeys(),
              new SecondaryDecoderItemSelectedListener());
          break;
        case 2: // MAS Codes
          if (mMASCodes == null) mMASCodes = new MASCodes();
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = mMASCodes;
          setupSpinnerFromArray(
              mBinding.secondaryDecodeSpinner,
              mReferenceData.getKeys(),
              new SecondaryDecoderItemSelectedListener());
          break;
        case 3: // Navy Reserve Activity Codes
          if (mNRACodes == null) mNRACodes = new NRACodes();
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = mNRACodes;
          setupSpinnerFromArray(
              mBinding.secondaryDecodeSpinner,
              mReferenceData.getKeys(),
              new SecondaryDecoderItemSelectedListener());
          break;
        case 4: // NOBC Codes
          if (mNOBCCodes == null) mNOBCCodes = new NOBCCodes();
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = mNOBCCodes;
          setupSpinnerFromArray(
              mBinding.secondaryDecodeSpinner,
              mReferenceData.getKeys(),
              new SecondaryDecoderItemSelectedListener());
          break;
        case 5: // Officer Billet Codes
          if (mOfficerBilletCodes == null) mOfficerBilletCodes = new OfficerBilletCodes();
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = mOfficerBilletCodes;
          setupSpinnerFromArray(
              mBinding.secondaryDecodeSpinner,
              mReferenceData.getKeys(),
              new SecondaryDecoderItemSelectedListener());
          break;
        case 6: // Officer Designator Codes
          if (mOfficerDesignatorCodes == null)
            mOfficerDesignatorCodes = new OfficerDesignatorCodes();
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = mOfficerDesignatorCodes;
          setupSpinnerFromArray(
              mBinding.secondaryDecodeSpinner,
              mReferenceData.getKeys(),
              new SecondaryDecoderItemSelectedListener());
          break;
        case 7: // Officer Paygrade Codes
          if (mOfficerPaygradeCodes == null) mOfficerPaygradeCodes = new OfficerPaygradeCodes();
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = mOfficerPaygradeCodes;
          setupSpinnerFromArray(
              mBinding.secondaryDecodeSpinner,
              mReferenceData.getKeys(),
              new SecondaryDecoderItemSelectedListener());
          break;
        case 8: // RBSC Billet Codes
          if (mRBSCBilletCodes == null) mRBSCBilletCodes = new RBSCBilletCodes();
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = mRBSCBilletCodes;
          setupSpinnerFromArray(
              mBinding.secondaryDecodeSpinner,
              mReferenceData.getKeys(),
              new SecondaryDecoderItemSelectedListener());
          break;
        case 9: // Reserve Program Codes
          if (mReserveProgramCodes == null) mReserveProgramCodes = new ReserveProgramCodes();
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = mReserveProgramCodes;
          setupSpinnerFromArray(
              mBinding.secondaryDecodeSpinner,
              mReferenceData.getKeys(),
              new SecondaryDecoderItemSelectedListener());
          break;
        case 10: // Reserve Unit Identification Codes
          if (mRUICCodes == null) mRUICCodes = new RUICCodes();
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = mRUICCodes;
          setupSpinnerFromArray(
              mBinding.secondaryDecodeSpinner,
              mReferenceData.getKeys(),
              new SecondaryDecoderItemSelectedListener());
          break;
        case 11: // RFAS-Enlisted Codes
          if (mRFASEnlistedCodes == null) mRFASEnlistedCodes = new RFASEnlistedCodes();
          updateLayoutDueToMainDecoderItemSelection(Layouts.RFAS);
          mRfasReferenceData = mRFASEnlistedCodes;
          setupSpinnerFromArray(
              mBinding.rfasFirstCharacter,
              mRfasReferenceData.getFirstCharacterKeys(),
              new RFASDecoderItemSelectedListener());
          setupSpinnerFromArray(
              mBinding.rfasSecondAndThirdCharacter,
              mRfasReferenceData.getSecondAndThirdCharacterKeys(),
              new RFASDecoderItemSelectedListener());
          setupSpinnerFromArray(
              mBinding.rfasFourthCharacter,
              mRfasReferenceData.getFourthCharacterKeys(),
              new RFASDecoderItemSelectedListener());
          break;
        case 12: // RFAS-Officer Codes
          if (mRFASOfficerCodes == null) mRFASOfficerCodes = new RFASOfficerCodes();
          updateLayoutDueToMainDecoderItemSelection(Layouts.RFAS);
          mRfasReferenceData = mRFASOfficerCodes;
          setupSpinnerFromArray(
              mBinding.rfasFirstCharacter,
              mRfasReferenceData.getFirstCharacterKeys(),
              new RFASDecoderItemSelectedListener());
          setupSpinnerFromArray(
              mBinding.rfasSecondAndThirdCharacter,
              mRfasReferenceData.getSecondAndThirdCharacterKeys(),
              new RFASDecoderItemSelectedListener());
          setupSpinnerFromArray(
              mBinding.rfasFourthCharacter,
              mRfasReferenceData.getFourthCharacterKeys(),
              new RFASDecoderItemSelectedListener());
          break;
        case 13: // Subspecialty Codes
          if (mSSPCodes == null) mSSPCodes = new SSPCodes();
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = mSSPCodes;
          setupSpinnerFromArray(
              mBinding.secondaryDecodeSpinner,
              mReferenceData.getKeys(),
              new SecondaryDecoderItemSelectedListener());
          break;
        default:
          if (mRatingCodes == null) mRatingCodes = new RatingCodes();
          updateLayoutDueToMainDecoderItemSelection(Layouts.NON_RFAS);
          mReferenceData = mRatingCodes;
          setupSpinnerFromArray(
              mBinding.secondaryDecodeSpinner,
              mReferenceData.getKeys(),
              new SecondaryDecoderItemSelectedListener());
          break;
      }
    }

    public void onNothingSelected(AdapterView<?> parent) {
      // Do nothing.
    }
  }

  private class RFASDecoderItemSelectedListener implements OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

      if (mRfasReferenceData == null) {
        mBinding.decodeDescription.setText("");
        mBinding.sourceDescription.setText("");
        return;
      }

      String firstCharacterKey =
          mBinding
              .rfasFirstCharacter
              .getItemAtPosition(mBinding.rfasFirstCharacter.getSelectedItemPosition())
              .toString();
      String firstCharacterValue = mRfasReferenceData.getFirstCharacterValue(firstCharacterKey);

      String secondAndThirdCharacterKey =
          mBinding
              .rfasSecondAndThirdCharacter
              .getItemAtPosition(mBinding.rfasSecondAndThirdCharacter.getSelectedItemPosition())
              .toString();
      String secondAndThirdCharacterValue =
          mRfasReferenceData.getSecondAndThirdCharacterValue(secondAndThirdCharacterKey);

      String fourthCharacterKey =
          mBinding
              .rfasFourthCharacter
              .getItemAtPosition(mBinding.rfasFourthCharacter.getSelectedItemPosition())
              .toString();
      String fourthCharacterValue = mRfasReferenceData.getFourthCharacterValue(fourthCharacterKey);

      String resultString =
          firstCharacterValue + "\n" + secondAndThirdCharacterValue + "\n" + fourthCharacterValue;
      mBinding.decodeDescription.setText(resultString);

      String SOURCE_INFO = mRfasReferenceData.getSourceInfo();
      mBinding.sourceDescription.setText(SOURCE_INFO);
    }

    public void onNothingSelected(AdapterView<?> parent) {
      // Do nothing.
    }
  }

  private class SecondaryDecoderItemSelectedListener implements OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

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
   * @return <code>true</code> if this is the first start (after an update) else <code>false</code>
   */
  private boolean isUpdate() {
    // Get the versionCode of the Package, which must be different (incremented) in each release
    //  in AndroidManifest.xml
    final long versionCode = CommonUtilities.getActualVersionCode(this);

    final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    final long lastVersionCode = prefs.getLong(LAST_VERSION_CODE_KEY, 0);

    if (versionCode != lastVersionCode) {
      Log.i(
          TAG,
          "versionCode "
              + versionCode
              + " is different from the last known version "
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
    ChangelogBuilder.create(
            this,
            (dialogInterface, i) -> {
              // Mark this version as read
              sp.edit().putLong(LAST_VERSION_CODE_KEY, versionCode).apply();

              dialogInterface.dismiss();
            })
        .show();
  }

  public void tryRequestReviewIfAppropriate() {
    // In debug builds show a plain dialog to confirm the prompt fires at the right
    // time. FakeReviewManager completes silently without any visible UI, so it is
    // not useful for manual timing verification.
    if (BuildConfig.DEBUG) {
      new Handler(Looper.getMainLooper())
          .postDelayed(
              () -> {
                if (isFinishing()) return;
                new AlertDialog.Builder(this)
                    .setTitle("[Debug] Review Prompt")
                    .setMessage("In a production build the Play Store review dialog appears here.")
                    .setPositiveButton("OK", null)
                    .show();
              },
              500);
      return;
    }

    SharedPreferences prefs = getSharedPreferences(REVIEW_PREFS, Context.MODE_PRIVATE);
    long now = System.currentTimeMillis();

    // Record first launch timestamp; don't prompt on the very first run.
    long firstLaunch = prefs.getLong(KEY_FIRST_LAUNCH_MS, 0L);
    if (firstLaunch == 0L) {
      prefs.edit().putLong(KEY_FIRST_LAUNCH_MS, now).apply();
      return;
    }

    // Enforce minimum install age before ever prompting.
    if (now - firstLaunch < MIN_INSTALL_AGE_MS) return;

    // Enforce cooldown between prompts.
    long lastPrompt = prefs.getLong(KEY_LAST_PROMPT_MS, 0L);
    if (now - lastPrompt < COOLDOWN_MS) return;

    // Record this attempt before launching to prevent repeated prompts if Play
    // suppresses the dialog without showing it.
    prefs.edit().putLong(KEY_LAST_PROMPT_MS, now).apply();

    promptInAppReview();
  }

  private void promptInAppReview() {
    Task<ReviewInfo> request = mReviewManager.requestReviewFlow();
    request.addOnCompleteListener(
        requestTask -> {
          if (isFinishing()) return;
          if (requestTask.isSuccessful()) {
            ReviewInfo reviewInfo = requestTask.getResult();
            mReviewManager.launchReviewFlow(this, reviewInfo);
            // The API does not indicate whether the dialog was shown or a review
            // was submitted. Continue app flow regardless of the outcome.
          }
        });
  }
}
