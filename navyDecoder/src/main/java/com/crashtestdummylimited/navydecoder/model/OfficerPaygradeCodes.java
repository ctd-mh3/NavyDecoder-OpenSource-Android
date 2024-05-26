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

public class OfficerPaygradeCodes implements ReferenceData {

  private final HashMap<String, String> mCodesHashMap;

  public OfficerPaygradeCodes() {
    String[][] CODE_MEANING_DATA = {
        {"A", "Fleet Admiral (011 FADM)"},
        {"B", "Admiral (010 ADM)"},
        {"C", "Vice Admiral (09 VADM)"},
        {"D", "Rear Admiral (08 RADM)"},
        {"E", "Rear Admiral (Lower Half) (07 RDML)"},
        {"G", "Captain (06 CAPT)"},
        {"H", "Commander (05 CDR)"},
        {"I", "Lieutenant Commander (04 LCDR)"},
        {"J", "Lieutenant (03 LT)"},
        {"K", "Lieutenant (Junior Grade) (02 LTJG)"},
        {"L", "Ensign (01 ENS)"},
        {"R", "Chief Warrant Officer-5 (W5 CWO5)"},
        {"M", "Chief Warrant Officer-4 (W4 CWO4)"},
        {"N", "Chief Warrant Officer-3 (W3 CWO3)"},
        {"O", "Chief Warrant Officer-2 (W2 CWO2)"},
        {"P", "Warrant Officer-1 (W1 WO1)"}
    };
    mCodesHashMap = new HashMap<>(CODE_MEANING_DATA.length);

    for (String[] aCODE_MEANING_DATA : CODE_MEANING_DATA) {
      mCodesHashMap.put(aCODE_MEANING_DATA[0], aCODE_MEANING_DATA[1]);
    }
  }

  @Override
  public String getSourceInfo() {

    return "NAVPERS 15839I VOL I (JAN 2024)";
  }

  @Override
  public String getCode() {

    return "Officer Paygrade Codes";
  }

  @Override
  public String[] getKeys() {

    //TO-DO:  All of this is likely not needed.  We know the # of keys so
    //        we should just be able to create a static array of that
    //        size and then copy the keys over as we iterator through them
    Iterator<String> iterator = mCodesHashMap.keySet().iterator();

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

    returnValue = mCodesHashMap.getOrDefault(key, "No match for code.");

    return returnValue;
  }

}