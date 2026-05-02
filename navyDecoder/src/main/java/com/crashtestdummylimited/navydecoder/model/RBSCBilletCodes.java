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

public class RBSCBilletCodes implements ReferenceData {

  private final HashMap<String, String> mCodesHashMap;
  private final String[] mSortedKeys;

  // 26JUN2020: latest RPM does not mention RBSC codes.
  public RBSCBilletCodes() {
    String[][] CODE_MEANING_DATA = {
      {"A", "Billet advertising in APPLY, JOAPPLY or CMS-ID"},
      {"C", "Commanding Officer (Operational Unit)"},
      {"K", "Commanding Officer (Readiness Unit)"},
      {"O", "Officer In Charge (Operational Unit)"},
      {"N", "Officer In Charge (Readiness Unit)"},
      {"X", "Executive Officer"},
      {"P", "Post Command"},
      {"E", "BUMED Senior Executive"},
      {"S", "Senior Enlisted Leader (E7-E9)"},
      {"M", "Milestone"},
      {"Z", "Billet suppressed from advertisement in APPLY, JOAPPLY or CMS-ID"}
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

    return "RESFOR N123 (21 JUL 2023)";
  }

  @Override
  public String getCode() {

    return "RBSC Billet Code";
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
