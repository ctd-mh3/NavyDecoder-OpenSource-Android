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
package com.crashtestdummylimited.navydecoder.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class MASCodes implements ReferenceData {

  // Hash for IMS Codes/Description
  //  Key = IMS Code
  //  Value = Description

  private final HashMap<String, String> mMASCodesHashMap;

  public MASCodes() {
    String[][] CODE_MEANING_DATA = {
        {"MS1", "Line of Duty initiated."},
        {"MS2", "Medical Retention Review (MRR) initiated."},
        {"MS3", "Temporarily Not Physically Qualified (TNPQ)."},
        {"MPQ", "Not qualified for retention."},
        {"MSR", "MRR periodic resubmission."},
        {"MPC", "Pregnant."},
        {"MPP", "Non-deployable mother, child less than 12 months old or postpartum convalescence."},
        {"MP1", "MRR submitted that receives Physically Qualified-Mob Limited."},
        {"MNN", "MRR submitted that receives Not Physically Qualified/Retention Recommended (NPQ/RR)."},
        {"MDF", "Dental Class IV (does not preclude mobilization)."},
        {"MDT", "Dental Class III (does not preclude mobilization)."},
        {"AKE", "Key Federal Employee."},
        {"AUP", "Unsatisfactory participant."},
        {"AAP", "Administrative action pending which would preclude mobilization."},
        {"APB", "Enlisted probationary drill status (UNSAT)."},
        {"ARR", "Retirement request submitted, pending PERS-9 approval/approved retirement request."},
        {"ACB", "Member not selected for continuation."},
        {"AS2", "Member is within six months of HYT or statutory attrition."},
        {"ACR", "Member has a ending/approved conditional release from Navy Reserve."},
        {"ASF", "Member is ineligible for Force Protection duties."},
        {"MPT", "Member failed most recent PFA."},
        {"TS1", "Active Duty/FTS member who affiliated within 0-183 days of release from Active Component (24-month deferment from involuntary mobilization)."},
        {"AS1", "Active Duty/FTS member who affiliated within 184-365 days of release from Active Component (12-month deferment from involuntary mobilization)."},
        {"AFP", "Security Clearance issue. Joint Personnel Adjudication System (JPAS) records indicate derogatory eligibility determination."},
        {"ASP", "Member is a single parent or guardian and requires a family care plan."},
        {"ASD", "Member has an Active/Reserve military spouse and requires a family care plan."},
        {"AS3", "Member is a non-custodial single parent who does not require a family care plan."},
        {"ASO", "Sole surviving son or daughter. May restrict mobilization locations."},
        {"SAD", "Approaching sanctuary. Member has 16 years or more of Active Duty."},
        {"DAP", "Pending ADSEP, package submitted."},
        {"TMS", "Attending authorized medical/dental/nursing school."},
        {"DCO", "CNRFC HQ USE ONLY.  Removed upon completion of Direct Commission Officer Indoctrination Course."},
        {"TBH", "Enlisted/Officer has not completed prescribed community specific training to be considered qualified for mobilization."},
        {"TSP", "SELRES Officer authorized early release for professional sports."},
        {"PRO", "Health Care Professional Officer in 36-month deferment window."},
        {"TS2", "RP assigned to USMC unit, but has not received NEC 2401."},
        {"TIW", "Information Officer (IW) who has not completed prescribed IW training."},
        {"TRP", "PRISE-R/designator or rate conversion training."},
        {"OAT", "Members GTCC is suspended or revoked."},
        {"VS1", "Member is on Definite (Temporary) Recall."},
        {"OWS", "Member is on ADOS."},
        {"BCG", "Member's record is frozen by PERS."},
        {"TBX", "Member has not completed prescribed training courses."},
    };

    mMASCodesHashMap = new HashMap<>(CODE_MEANING_DATA.length);

    for (String[] aCODE_MEANING_DATA : CODE_MEANING_DATA) {
      mMASCodesHashMap.put(aCODE_MEANING_DATA[0], aCODE_MEANING_DATA[1]);
    }
  }

  @Override
  public String getSourceInfo() {

    return "RESFOR N1C2 (23 JAN 2023)";
  }

  @Override
  public String getCode() {

    return "Manpower Availability Status Code";
  }

  @Override
  public String[] getKeys() {

    //TO-DO:  All of this is likely not needed.  We know the # of keys so
    //        we should just be able to create a static array of that
    //        size and then copy the keys over as we iterator through them
    Iterator<String> iterator = mMASCodesHashMap.keySet().iterator();

    ArrayList<String> mArrayList = new ArrayList<>();

    while (iterator.hasNext()) {
      mArrayList.add(iterator.next());
    }

    Collections.sort(mArrayList);

    String[] stringArray = new String[mArrayList.size()];
    stringArray = mArrayList.toArray(stringArray);

    return stringArray;
  }

  @Override
  public String getValue(String key) {

    String returnValue;

    returnValue = mMASCodesHashMap.getOrDefault(key, "No match for code.");

    return returnValue;
  }

}