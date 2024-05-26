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

public class NRACodes implements ReferenceData {

  private final HashMap<String, String> mCodesHashMap;

  public NRACodes() {
    String[][] CODE_MEANING_DATA = {
        {"0600", "REDCOM MA NORFOLK, VA"},
        {"0601", "NRC FT DIX, NJ"},
        {"0604", "NRC BANGOR, ME"},
        {"0606", "NRC SYRACUSE, NY"},
        {"0609", "NRC WHITE RIVER JC, VT"},
        {"0610", "NRC SCHENECTADY, NY"},
        {"0611", "NRC WASHINGTON, DC"},
        {"0612", "NRC CHARLOTTE, NC"},
        {"0613", "NRC BUFFALO, NY"},
        {"0624", "NRC GREENSBORO, NC"},
        {"0628", "NRC LONG ISLAND, NY"},
        {"0630", "NRC MANCHESTER, NH"},
        {"0633", "NRC NEW YORK CITY, NY"},
        {"0643", "NRC NEWPORT, RI"},
        {"0645", "NRC RICHMOND, VA"},
        {"0646", "NRC ROANOKE, VA"},
        {"0647", "NRC PLAINVILLE, CT"},
        {"0659", "NRC RALEIGH, NC"},
        {"0663", "NRC NEW LONDON, CT"},
        {"0666", "NRC EARLE, NJ"},
        {"0667", "NRC BALTIMORE, MD"},
        {"0676", "NRC NEW CASTLE, DE"},
        {"0682", "NRC ROCHESTER, NY"},
        {"0686", "NRC NORFOLK, VA"},
        {"0696", "NRC QUINCY, MA"},
        {"2302", "SEAL TEAM 18, VA"},
        {"2502", "MERCHANT MARINE, VA"},
        {"2525", "CNRFC, VA"},
        {"2200", "REDCOM NW EVERETT, WA"},
        {"2206", "NRC BILLINGS, MT"},
        {"2207", "NRC BOISE, ID"},
        {"2211", "NRC CHEYENNE, WY"},
        {"2212", "NRC SPRINGFIELD, OR"},
        {"2213", "NRC EVERETT, WA"},
        {"2214", "NRC DES MOINES, IA"},
        {"2215", "NRC MINNEAPOLIS, MN"},
        {"2216", "NRC FARGO, ND"},
        {"2217", "NRC OMAHA, NE"},
        {"2218", "NRC SIOUX FALLS, SD"},
        {"2225", "NRC PORTLAND, OR"},
        {"2232", "NRC SPOKANE, WA"},
        {"2252", "NRC KITSAP, WA"},
        {"2253", "NRC HELENA, MT"},
        {"2289", "NRC WHIDBEY ISLAND, WA"},
        {"2299", "NRC ANCHORAGE, AK"},
        {"0800", "REDCOM SE JACKSONVILLE, FL"},
        {"0804", "NRC AUGUSTA, GA"},
        {"0809", "NRC BIRMINGHAM, AL"},
        {"0811", "NRC CHARLESTON, SC"},
        {"0813", "NRC COLUMBIA, SC"},
        {"0814", "NRC COLUMBUS, GA"},
        {"0827", "NRC GREENVILLE, SC"},
        {"0840", "NRC PUERTO RICO, PR"},
        {"0847", "NRC MIAMI, FL"},
        {"0855", "NRC ORLANDO, FL"},
        {"0858", "NRC PENSACOLA, FL"},
        {"0861", "NRC WEST PALM BEACH, FL"},
        {"0862", "NRC CHATTANOOGA, TN"},
        {"0863", "NRC KNOXVILLE, TN"},
        {"0864", "NRC MEMPHIS, TN"},
        {"0865", "NRC NASHVILLE, TN"},
        {"0867", "NRC ATLANTA, GA"},
        {"0870", "NRC TAMPA, FL"},
        {"0874", "NRC JACKSONVILLE, FL"},
        {"0897", "NRC TALLAHASSEE, FL"},
        {"1300", "REDCOM MA GREAT LAKES, IL"},
        {"1301", "NRC DETROIT, MI"},
        {"1302", "NRC AKRON, OH"},
        {"1303", "NRC CINCINNATI, OH"},
        {"1304", "NRC COLUMBUS, OH"},
        {"1307", "NRC AVOCA, PA"},
        {"1309", "NRC ERIE, PA"},
        {"1310", "NRC HARRISBURG, PA"},
        {"1311", "NRC PITTSBURGH, PA"},
        {"1312", "NRC ELEANORE, WV"},
        {"1321", "NRC LOUISVILLE, KY"},
        {"1326", "NRC GREAT LAKES, IL"},
        {"1329", "NRC ROCK ISLAND, IL"},
        {"1336", "NRC GREENBAY, WI"},
        {"1351", "NRC MADISON, WI"},
        {"1357", "NRC INDIANAPOLIS, IN"},
        {"1359", "NRC BATTLE CREEK, MI"},
        {"1367", "NRC PEORIA, IL"},
        {"1377", "NRC SAGINAW, MI"},
        {"1800", "REDCOM SE FT WORTH, TX"},
        {"1801", "NRC LITTLE ROCK, AR"},
        {"1802", "NRC WICHITA, KS"},
        {"1803", "NRC NEW ORLEANS, LA"},
        {"1804", "NRC SHREVEPORT, LA"},
        {"1805", "NRC ST LOUIS, MO"},
        {"1806", "NRC KANSAS CITY, MO"},
        {"1807", "NRC SPRINGFIELD, MO"},
        {"1808", "NRC GULFPORT, MS"},
        {"1809", "NRC MERIDIAN, MS"},
        {"1810", "NRC TULSA, OK"},
        {"1811", "NRC OKLAHOMA CITY, OK"},
        {"1812", "NRC AMARILLO, TX"},
        {"1813", "NRC AUSTIN, TX"},
        {"1814", "NRC CORPUS CHRISTI, TX"},
        {"1815", "NRC EL PASO, TX"},
        {"1816", "NRC FT WORTH, TX"},
        {"1817", "NRC HARLINGEN, TX"},
        {"1818", "NRC HOUSTON, TX"},
        {"1819", "NRC SAN ANTONIO, TX"},
        {"4000", "NRPDC, LA"},
        {"1900", "REDCOM SW SAN DIEGO, CA"},
        {"1901", "NRC LEMOORE, CA"},
        {"1902", "NRC PEARL HARBOR, HI"},
        {"1903", "NRC GUAM, GU"},
        {"1904", "NRC ALBUQUERQUE, NM"},
        {"1914", "NRC CARSON, CO"},
        {"1917", "NRC LAS VEGAS, NV"},
        {"1920", "NRC DENVER, CO"},
        {"1929", "NRC PHOENIX, AZ"},
        {"1939", "NRC MORENO VALLEY, CA"},
        {"1941", "NRC LOS ANGELES, CA"},
        {"1942", "NRC SAN DIEGO, CA"},
        {"1946", "NRC VENTURA COUNTY, CA"},
        {"1950", "NRC TUCSON, AZ"},
        {"1951", "NRC ALAMEDA, CA"},
        {"1961", "NRC FALLON, NV"},
        {"1963", "NRC SACRAMENTO, CA"},
        {"1965", "NRC SALT LAKE CITY, UT"},
        {"1976", "NRC SAN JOSE, CA"},
        {"1994", "NRC NORTH ISLAND, CA"},
        {"2300", "NSWG 11, CA"},
        {"2301", "SEAL TEAM 17, CA"},
        {"6400", "FLSW, TX"},
        {"6401", "VR-1, DC"},
        {"6404", "VR-51, HI"},
        {"6406", "VR-53, MD"},
        {"6407", "VR-54, LA"},
        {"6408", "VR-55, CA"},
        {"6409", "VR-56, VA"},
        {"6410", "VR-57, CA"},
        {"6411", "VR-58, FL"},
        {"6412", "VR-59, TX"},
        {"6413", "VR-61, WA"},
        {"6414", "VR-62, FL"},
        {"6415", "VR-64, NJ"},
        {"6500", "TSW, TX"},
        {"6503", "VFC-12, VA"},
        {"6504", "VFC-13, NV"},
        {"6506", "VFA-204, LA"},
        {"6507", "VAQ-209, WA"},
        {"6508", "VFC-111, FL"},
        {"6800", "MSW, CA"},
        {"6801", "HSM 60, FL"},
        {"6804", "HSC-85. CA"},
        {"0684", "HM-14, VA"},
        {"0686", "HM-15, VA"},
        {"2287", "VP-69, WA"},
        {"6802", "VP-62, FL"},
        {"6666", "NAF WASHINGTON, DC"}
    };
    mCodesHashMap = new HashMap<>(CODE_MEANING_DATA.length);

    for (String[] aCODE_MEANING_DATA : CODE_MEANING_DATA) {
      mCodesHashMap.put(aCODE_MEANING_DATA[0], aCODE_MEANING_DATA[1]);
    }
  }

  @Override
  public String getSourceInfo() {

    return "RESFOR (30 November 2022)";
  }

  @Override
  public String getCode() {

    return "Navy Reserve Activity Code";
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