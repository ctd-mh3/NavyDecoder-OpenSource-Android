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

public class RBSCBilletCodes implements ReferenceData {

  private final HashMap<String, String> mCodesHashMap;

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
    mCodesHashMap = new HashMap<>(CODE_MEANING_DATA.length);

    for (String[] aCODE_MEANING_DATA : CODE_MEANING_DATA) {
      mCodesHashMap.put(aCODE_MEANING_DATA[0], aCODE_MEANING_DATA[1]);
    }
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