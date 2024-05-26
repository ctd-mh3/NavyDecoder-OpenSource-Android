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

public class RatingCodes implements ReferenceData {

  private final HashMap<String, String> mCodesHashMap;

  public RatingCodes() {
    String[][] mCodeData = {
        {"AN", "Airman"},
        {"CN", "Constructionman"},
        {"FN", "Fireman"},
        {"HN", "Hospitalman"},
        {"SN", "Seaman"},
        {"AB", "Aviation Boatswain's Mate"},
        {"ABE", "Aviation Boatswain's Mate (Launching and Recovery Equipment)"},
        {"ABF", "Aviation Boatswain's Mate (Fuels)"},
        {"ABH", "Aviation Boatswain's Mate (Aircraft Handling)"},
        {"AC", "Air Traffic Controller"},
        {"AD", "Aviation Machinist's Mate"},
        {"AE", "Aviation Electrician's Mate"},
        {"AG", "Aerographer's Mate"},
        {"AM", "Aviation Structural Mechanic"},
        {"AME", "Aviation Structural Mechanic (Safety Equipment)"},
        {"AO", "Aviation Ordnanceman"},
        {"AS", "Aviation Support Equipment Technician"},
        {"AT", "Aviation Electronics Technician"},
        {"AW", "Naval Aircrewmen"},
        {"AWF", "Naval Aircrewmen (Mechanical)"},
        {"AWO", "Naval Aircrewmen (Operator)"},
        {"AWR", "Naval Aircrewmen (Tactical Helicoptor)"},
        {"AWS", "Naval Aircrewmen (Helicoptor)"},
        {"AWV", "Naval Aircrewmen (Avionics)"},
        {"AZ", "Aviation Maintenance Administrationman"},
        {"BM", "Boatswain's Mate"},
        {"BU", "Builder"},
        {"CBCM", "Seabee Master Chief"},
        {"CE", "Construction Electrician"},
        {"CM", "Construction Mechanic"},
        {"CMC", "Command Master Chief"},
        {"CMDCM", "Command Master Chief Petty Officer"},
        {"CMDCS", "Command Senior Chief"},
        {"FLTCM", "Fleet Master Chief Petty Officer"},
        {"FORCM", "Force Master Chief Petty Officer"},
        {"MCPON", "Master Chief Petty Officer of the Navy"},
        {"CS", "Culinary Specialist"},
        {"CSS", "Culinary Specialist Submarine"},
        {"CT", "Cryptologic Technician"},
        {"CTI", "Cryptologic Technician (Interpretive)"},
        {"CTM", "Cryptologic Technician (Maintenance)"},
        {"CTR", "Cryptologic Technician (Collection)"},
        {"CTT", "Cryptologic Technician (Technical)"},
        {"CWT", "Cyber Warfare Technician"},
        {"DC", "Damage Controlman"},
        {"EA", "Engineering Aide"},
        {"EM", "Electrician's Mate"},
        {"EMN", "Electrician's Mate (Nuclear)"},
        {"EN", "Engineman"},
        {"EO", "Equipment Operator"},
        {"EOD", "Explosive Ordnance Disposal"},
        {"ET", "Electronics Technician"},
        {"ETN", "Electronics Technician (Nuclear)"},
        {"ETV", "Electronics Technician (Submarine-Navigation)"},
        {"FC", "Fire Controlman"},
        {"FCA", "Fire Controlman (AEGIS)"},
        {"FT", "Fire Control Technician"},
        {"GM", "Gunner's Mate"},
        {"GS", "Gas Turbine System Technician"},
        {"GSE", "Gas Turbine System Technician (Electrical)"},
        {"GSM", "Gas Turbine System Technician (Mechanical)"},
        {"GSCS", "Gas Turbine System Technician-Senior Chief"},
        {"GSCM", "Gas Turbine System Technician-Master Chief"},
        {"HM", "Hospital Corpsman"},
        {"HT", "Hull Maintenance Technician"},
        {"IC", "Interior Communications Electrician"},
        {"IS", "Intelligence Specialist"},
        {"IT", "Information Systems Technician"},
        {"ITS", "Information Systems Technician Submarines"},
        {"LN", "Legalman"},
        {"LS", "Logistics Specialists"},
        {"LSS", "Logistics Specialists Submarine"},
        {"MA", "Master-at-Arms"},
        {"MC", "Mass Communication Specialist"},
        {"MM", "Machinist's Mate"},
        {"MMA", "Machinist's Mate (Non-Nuclear, Submarine Auxiliary)"},
        {"MMN", "Machinist's Mate (Nuclear Power)"},
        {"MN", "Mineman"},
        {"MR", "Machinery Repairman"},
        {"MT", "Missile Technician"},
        {"MU", "Musician"},
        {"NC", "Navy Counselor"},
        {"ND", "Navy Diver"},
        {"OS", "Operations Specialist"},
        {"PR", "Aircrew Survival Equipmentman"},
        {"PS", "Personnel Specialist"},
        {"QM", "Quartermaster"},
        {"RP", "Religious Programs Specialist"},
        {"RS", "Retail Services Specialist"},
        {"SB", "Special Warfare Boat Operator "},
        {"SO", "Special Warfare Operators"},
        {"ST", "Sonar Technician"},
        {"STG", "Sonar Technician (Surface)"},
        {"STS", "Sonar Technician (Submarine)"},
        {"SW", "Steelworker"},
        {"TM", "Torpedomans Mate"},
        {"UT", "Utilitiesman"},
        {"YN", "Yeoman"},
        {"YNS", "Yeoman (Submarine)"}
    };
    mCodesHashMap = new HashMap<>(mCodeData.length);

    for (String[] aMCodeData : mCodeData) {
      mCodesHashMap.put(aMCodeData[0], aMCodeData[1]);
    }
  }

  @Override
  public String getSourceInfo() {

    return "NAVPERS 18068F (JAN 2024)";
  }

  @Override
  public String getCode() {

    return "Enlisted Rating Code";
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