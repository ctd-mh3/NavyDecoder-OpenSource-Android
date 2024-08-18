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

public class ReserveProgramCodes implements ReferenceData {

  private final HashMap<String, String> mCodesHashMap;

  public ReserveProgramCodes() {
    String[][] CODE_MEANING_DATA = {
        {"00", "Reserve"},
        {"01", "Naval Submarine Forces"},
        {"02", "Mine Forces"},
        {"03", "Mobile Logistics Support"},
        {"04", "Surface Combatant Force"},
        {"05", "Air Forces"},
        {"06", "Expeditionary Log Sup Force"},
        {"07", "Construction Forces"},
        {"08", "Amphibious Forces"},
        {"09", "Marine Corps Forces"},
        {"10", "Special Warfare Forces"},
        {"11", "Numbered Fleets"},
        {"12", "Major HQ Staffs"},
        {"13", "Major Unified/Joint/Shore"},
        {"14", "Support of Allies"},
        {"15", "Telecommunications"},
        {"16", "Security Group"},
        {"17", "Intelligence"},
        {"18", "NETWARCOM"},
        {"19", "Navy Weather"},
        {"20", "Oceanography"},
        {"21", "Military Sealift"},
        {"22", "Navy Control of ShippingC"},
        {"23", "Bases and Stations"},
        {"24", "Materials Command"},
        {"25", "Air Systems Command"},
        {"26", "Space & Naval Warfare Systems Command"},
        {"27", "FACENGCOM"},
        {"28", "Civil Defense"},
        {"29", "Sea Systems Command"},
        {"30", "Supply Systems"},
        {"31", "Merchant Marine"},
        {"32", "Health Services"},
        {"33", "Training"},
        {"34", "Personnel System"},
        {"35", "Public Affairs"},
        {"36", "Law"},
        {"37", "Chaplains"},
        {"38", "Research"},
        {"39", "Selective Service System"},
        {"40", "Mobilization Assign Group MACG/GENER VTU"},
        {"41", "Variable Participation Units"},
        {"42", "Operational Support"},
        {"43", "FYDP Adjustment"},
        {"44", "NRPC ADMIN Units"},
        {"45", "Naval Reserve Support Element"},
        {"46", "Fleet Hospital"},
        {"49", "Misc. (Schools, Etc.)"},
        {"51", "New Capability"},
        {"52", "New Capability"},
        {"5B", "Wings"},
        {"70", "Air Forces"}
    };
    mCodesHashMap = new HashMap<>(CODE_MEANING_DATA.length);

    for (String[] aCODE_MEANING_DATA : CODE_MEANING_DATA) {
      mCodesHashMap.put(aCODE_MEANING_DATA[0], aCODE_MEANING_DATA[1]);
    }
  }

  @Override
  public String getSourceInfo() {

    return "NRH N12 (26 JUN 2020)";
  }

  @Override
  public String getCode() {

    return "Reserve Program Codes";
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