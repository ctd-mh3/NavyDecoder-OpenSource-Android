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

import java.util.Arrays;
import java.util.HashMap;

public class OfficerPaygradeCodes implements ReferenceData {

  private final HashMap<String, String> mCodesHashMap;
  private final String[] mSortedKeys;

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
    mCodesHashMap = new HashMap<>((int) (CODE_MEANING_DATA.length / 0.75) + 1);

    for (String[] aCODE_MEANING_DATA : CODE_MEANING_DATA) {
      mCodesHashMap.put(aCODE_MEANING_DATA[0], aCODE_MEANING_DATA[1]);
    }
    String[] keys = mCodesHashMap.keySet().toArray(new String[0]);
    Arrays.sort(keys);
    mSortedKeys = keys;
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
    return mSortedKeys;
  }

  @Override
  public String getValue(String key) {

    String returnValue;

    returnValue = mCodesHashMap.getOrDefault(key, "No match for code.");

    return returnValue;
  }
}
