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

public class IMSCodes implements ReferenceData {

  // Hash for IMS Codes/Description
  //  Key = IMS Code
  //  Value = Description

  private final HashMap<String, String> mIMSCodesHashMap;

  public IMSCodes() {
    String[][] CODE_MEANING_DATA = {
        {"Blank", "No action required/pending.  Not identified for recall/mobilization"},
        {"RXX", "Precedes R##. HQ use only for planning purposes."},
        {"RYY", "Precedes R##. HQ use only for planning purposes."},
        {"R##", "Identified for recall/mobilization"},
        {"RC1", "NRA verbally contacted Reservist identified for recall/mobilization."},
        {"RC2", "Mobilizing Reservist completed medical screening"},
        {"RC2.5", "Mobilizing Reservist completed all admin, medical, dental, and training requirement"},
        {"RC3", "Mobilizing Reservist reported for activation as directed"},
        {"ROC", "Mobilization/recall orders cancelled prior to execution"},
        {"RU1", "NRA unable to verbally contact Reservist identified for mobilization/recall"},
        {"RU2", "NRA unable to verbally contact Reservist identified for mobilization/recall after 30 days"},
        {"RUA", "NRA verbally contacted Reservist identified for mobilization/recall, but Reservist did not report as ordered."},
        {"RDD", "Member declared a deserter"},
        {"RM1", "Gained to active duty - system generated"},
        {"RVC", "Serving on 12301D (volunteer) orders or other voluntary recall/mobilization orders"},
        {"RM2", "Reported to ultimate duty station"},
        {"RM3", "Remaining on active duty"},
        {"RM4", "Reassigned to another command"},
        {"RM5", "Administrative hold"},
        {"RM6", "Judicial hold"},
        {"RM7", "Med Hold, Med Delay, Med Eval"},
        {"RM8", "Discharged from active duty"},
        {"RM9", "Temporary Disability Retired List (TDRL)"},
        {"RMD", "Retired from active duty"},
        {"RML", "Released to ECRC/PSD for out-processing"},
        {"RMP", "Reported to ECRC/PSD for out-processing"},
        {"RD1", "Released from active duty"},
        {"RD2", "Reported back to NRA"},
        {"RDA", "Dwell time has expired"},
        {"D7G", "1-3 day delay granted by NRA CO"},
        {"D3P", "4-30 day delay pending (applied for)"},
        {"D3G", "4-30 day granted by CNRF N35"},
        {"D6P", "31-60 day delay pending (applied for)"},
        {"D6G", "31-60 day delay granted by PERS-9"},
        {"HPP", "Personal hardship exemption pending"},
        {"HPG", "Personal hardship exemption granted"},
        {"HCP", "Community hardship exemption pending"},
        {"HCG", "Community hardship exemption granted"},
        {"DMC", "Non-deployable mother, child less than 12 months old"},
        {"DMT", "Temporary medical deferment, awaiting adjudication"},
        {"EMP", "Pregnant"},
        {"EMN", "Not physically qualified for mobilization. (MRR package submitted and/or CNRFC N35 directed use)"},
        {"EMD", "Deceased"},
        {"DTH", "High school student, under 20 years old"},
        {"DTX", "Not completed basic/equivalent training"},
        {"DTR", "Attending validated religious leader training."},
        {"DTC", "Designator/rate conversion training"},
        {"DTM", "Attending authorized medical/dental school"},
        {"JDP", "Judicial proceedings pending (entered by Ech IV/V)"},
        {"DJP", "Judicial proceedings pending (entered by CNRF)"},
        {"EJC", "Confinement by civil authorities"},
        {"EJM", "Confinement by military authorities"},
        {"EJP", "On probation may not leave court jurisdiction"},
        {"EAK", "ADSEP-Key Employee"},
        {"EAE", "ADSEP-erroneous/defective/fraudulent enlistment"},
        {"EAD", "ADSEP-drug/alcohol abuse"},
        {"EAP", "ADSEP-unsatisfactory participation in the Ready Reserve"},
        {"EAC", "ADSEP-conscientious objector"},
        {"EAS", "ADSEP-sole surviving son/daughter"},
        {"EAM", "ADSEP-misconduct"},
        {"EAR", "Processing for retirement"},
        {"EAO", "Not extending obligated service (EOS)"},
        {"EAT", "Enlisted in active component"},
        {"NAO", "Expired DDE-fully available for activation"},
        {"NAI", "Available for INCONUS activation only"},
    };
    mIMSCodesHashMap = new HashMap<>(CODE_MEANING_DATA.length);

    for (String[] aCODE_MEANING_DATA : CODE_MEANING_DATA) {
      mIMSCodesHashMap.put(aCODE_MEANING_DATA[0], aCODE_MEANING_DATA[1]);
    }
  }


  @Override
  public String getSourceInfo() {

    return "RESFOR N1C2 (23 JAN 2023)";
  }

  @Override
  public String getCode() {

    return "Individual Mobilization Status Code";
  }

  @Override
  public String[] getKeys() {

    //TO-DO:  All of this is likely not needed.  We know the # of keys so
    //        we should just be able to create a static array of that
    //        size and then copy the keys over as we iterator through them
    Iterator<String> iterator = mIMSCodesHashMap.keySet().iterator();

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

    returnValue = mIMSCodesHashMap.getOrDefault(key, "No match for code.");

    return returnValue;
  }


}
