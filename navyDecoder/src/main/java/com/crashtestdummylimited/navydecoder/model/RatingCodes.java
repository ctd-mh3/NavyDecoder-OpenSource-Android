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

public class RatingCodes implements ReferenceData {

  private final HashMap<String, String> mCodesHashMap;
  private final String[] mSortedKeys;

  public RatingCodes() {
    String[][] codeData = {
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
      {"AWF", "Naval Aircrewman (Mechanical)"},
      {"AWO", "Naval Aircrewman (Operator)"},
      {"AWR", "Naval Aircrewman (Tactical Helicopter)"},
      {"AWS", "Naval Aircrewman (Helicopter)"},
      {"AWV", "Naval Aircrewman (Avionics)"},
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
      {"CSS", "Culinary Specialist (Submarine)"},
      {"CT", "Cryptologic Technician"},
      {"CTI", "Cryptologic Technician (Interpretive)"},
      {"CTM", "Cryptologic Technician (Maintenance)"},
      {"CTR", "Cryptologic Technician (Collection)"},
      {"CTT", "Cryptologic Technician (Technical)"},
      {"CWT", "Cyber Warfare Technician"},
      {"DC", "Damage Controlman"},
      {"EA", "Engineering Aide"},
      {"EM", "Electrician's Mate"},
      {"EMN", "Electrician's Mate (Nuclear Power)"},
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
      {"ITE", "Information Systems Technicians, Submarines, Network"},
      {"ITN", "Information Systems Technicians, Submarines, Electronic Warfare"},
      {"ITR", "Information Systems Technicians, Submarines, Communications"},
      {"LN", "Legalman"},
      {"LS", "Logistics Specialist"},
      {"LSS", "Logistics Specialist Submarine"},
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
      {"NCC", "Navy Counselor (Counselor)"},
      {"NCR", "Navy Counselor (Recruiter)"},
      {"ND", "Navy Diver"},
      {"OS", "Operations Specialist"},
      {"PR", "Aircrew Survival Equipmentman"},
      {"PS", "Personnel Specialist"},
      {"QM", "Quartermaster"},
      {"RP", "Religious Programs Specialist"},
      {"RS", "Retail Services Specialist"},
      {"RW", "Robotics Warfare Specialist"},
      {"SB", "Special Warfare Boat Operator "},
      {"SO", "Special Warfare Operator"},
      {"ST", "Sonar Technician"},
      {"STG", "Sonar Technician (Surface)"},
      {"STS", "Sonar Technician (Submarine)"},
      {"SW", "Steelworker"},
      {"TM", "Torpedoman's Mate"},
      {"UT", "Utilitiesman"},
      {"YN", "Yeoman"},
      {"YNS", "Yeoman (Submarine)"}
    };
    mCodesHashMap = new HashMap<>((int) (codeData.length / 0.75) + 1);

    for (String[] row : codeData) {
      mCodesHashMap.put(row[0], row[1]);
    }
    String[] keys = mCodesHashMap.keySet().toArray(new String[0]);
    Arrays.sort(keys);
    mSortedKeys = keys;
  }

  @Override
  public String getSourceInfo() {

    return "NAVPERS 18068F (APR 2026)";
  }

  @Override
  public String getCode() {

    return "Enlisted Rating Code";
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
