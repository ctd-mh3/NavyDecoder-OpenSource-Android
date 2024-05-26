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

public class OfficerBilletCodes implements ReferenceData {

  private final HashMap<String, String> mCodesHashMap;

  public OfficerBilletCodes() {
    String[][] CODE_MEANING_DATA = {
        {"1000", "Billet which may be filled by any appropriately skilled and experienced Unrestricted Line Officer or Special Duty Officer"},
        {"1020", "Billet which may be filled by any appropriately skilled and experienced Special Duty Officer (IP) or Unrestricted Line Officer"},
        {"1050", "Unrestricted Line Officer billet requiring an officer qualified in any one of the warfare specialties (LT and above)"},
        {"1100", "Unrestricted Line Officer billet requiring Fleet Support specialty"},
        {"1110", "Unrestricted Line Officer billet requiring Surface Warfare qualification or afloat billets leading to such qualification"},
        {"1120", "Unrestricted Line Officer billet requiring Submarine Warfare qualification or afloat billets leading to such qualification"},
        {"1130", "Unrestricted Line Officer billet requiring Special Warfare (SEAL) qualification"},
        {"1140", "Unrestricted Line Officer billet requiring Explosive Ordnance Disposal (EOD) Warfare Qualification"},
        {"1160", "Unrestricted Line Officer billet for an officer in training for Surface Warfare qualification"},
        {"1170", "Unrestricted Line Officer billet for an officer in training for Submarine Warfare qualification"},
        {"1180", "Unrestricted Line Officer billet for a student in training for Special Warfare qualification"},
        {"1190", "Unrestricted Line Officer billet for an officer in training for Explosive Ordnance Disposal (EOD) qualification"},
        {"1200", "Special Duty Officer - Restricted Line Officer Billet requiring Human Resources specialty - Plan, program, and execute life-cycle management of our Navy's most important resource - people."},
        {"1210", "Restricted Line Officer Billet (Nuclear Power School Instructor) regulated by Program Authorization 100B attached to Nuclear Power School, Charleston, SC."},
        {"1230", "Restricted Line Officer Billet (Permanent Military Professor) regulated by OPNAVINST 1520.40"},
        {"1280", "Special Duty Officer-Restricted Line Officer Billet Requiring Recruiting Specialty of a permanent Recruiter (LT and LCDR)"},
        {"1300", "Unrestricted Line Officer billet, Code 0 - Other Than Operational Flying, requiring Air Warfare specialty of, or previous designation as, a pilot or NFO (LT and above)"},
        {"1301", "Unrestricted Line Officer billet, Code 1 - Operational Flying, requiring Air Warfare specialty of a pilot or NFO (LT and above)"},
        {"1302", "Unrestricted Line Officer billet, Code 2 - Operational Flying, requiring Air Warfare specialty of a pilot or NFO"},
        {"1310", "Unrestricted Line Officer billet, Code 0 - Other Than Operational Flying, requiring Aviation Warfare specialty of a pilot"},
        {"1311", "Unrestricted Line Officer billet, Code 1 - Operational Flying, requiring Aviation Warfare specialty of a pilot"},
        {"1312", "Unrestricted Line Officer billet, Code 2 - Operational Flying, requiring Aviation Warfare specialty of a pilot"},
        {"1320", "Unrestricted Line Officer billet, Code 0 - Other Than Operational Flying, requiring Aviation Warfare specialty of a Naval Flight Officer"},
        {"1321", "Unrestricted Line Officer billet, Code 1 - Operational Flying, requiring Aviation Warfare specialty of a Naval Flight Officer"},
        {"1322", "Unrestricted Line Officer billet, Code 2 - Operational Flying, requiring Aviation Warfare specialty of a Naval Flight Officer"},
        {"1372", "Unrestricted Line Officer billet, Code 2 - Operational Flying, for a student in training for Aviation Warfare (NFO) qualification"},
        {"1392", "Unrestricted Line Officer billet, Code 2 - Operational Flying, for a student in training for Aviation Warfare (pilot) qualification"},
        {"1440", "Engineering Duty Officer billet requiring specialists in Ship Engineering, including Ship and Ship Systems Engineering, Electronic Systems Engineering, Combat/Weapons Systems Engineering, and Ordnance Systems Engineering."},
        {"1460", "Engineering Duty Officer billet for an officer actively pursuing a prescribed program leading to designation as 144X"},
        {"1500", "Aerospace Engineering Duty Officer billet requiring Aerospace Engineering (AED) or Aerospace maintenance (AMD) specialties (CAPT and above)"},
        {"1510", "Aerospace Engineering Duty Officer billet requiring Aerospace Engineering (AED) specialty"},
        {"1511", "Aerospace Engineering Duty Officer billet, Code 1 - Operational Flying, requiring the specialty of an Aerospace Engineering Duty (AED) officer who is a designated Pilot or Naval Flight Officer"},
        {"1512", "Aerospace Engineering Duty Officer billet, Code 2 - Operational Flying, requiring the specialty of an Aerospace Engineering Duty (AED) Officer who is a designated Pilot or Naval Flight Officer"},
        {"1540", "Aviation Duty Officer billet, Code 0 - Other Than Operational Flying, requiring Aviation Warfare specialty of a pilot (LT thru CAPT)"},
        {"1541", "Aviation Duty Officer billet, Code 1 - Operational Flying, requiring Aviation Warfare specialty of a pilot (LT thru CAPT)"},
        {"1542", "Aviation Duty Officer billet, Code 2 - Operational Flying, requiring Aviation Warfare specialty of a pilot (LT thru CAPT)"},
        {"1650", "Special Duty Officer billet requiring Public Affairs specialty"},
        {"1660", "Special Duty Officer billet requiring a Strategic Sealift Officer"},
        {"1710", "Special Duty Officer billet requiring a qualified Foreign Area Officer or a Foreign Area Officer Under Instruction"},
        {"1712", "Special Duty Officer billet requiring a Foreign Area Officer Code 2 – Operational Flying Involved, a qualified Foreign Area Officer or a Foreign Area Officer Under Instruction"},
        {"1800", "Special Duty Officer - Oceanography"},
        {"1802", "Special Duty Officer billet Code 2 - Operational Flying, requiring Meteorology specialty of a Geophysicist who is a designated Pilot or Naval Flight Officer"},
        {"1810", "Special Duty Officer – Cryptologic Warfare Officer"},
        {"1820", "Special Duty Officer – Information Professional Officer"},
        {"1830", "Special Duty Officer – Intelligence Officer"},
        {"1840", "Special Duty Officer – Cyber Warfare Engineer"},
        {"1850", "Billets which may be filled by any appropriately skilled and experienced IWC Officer"},
        {"1860", "Special Duty Officer – IWC Flag Officer"},
        {"1870", "Warfare –qualified URL/RL officers at O3, O4, O5 and O6 level"},
        {"1880", "Special Duty Officer-Maritime Cyber Warfare Officer (O1 to O6)"},
        {"1900", "Unrestricted Line Officer student billet for a prospective Nurse Corps officer"},
        {"1910", "Unrestricted Line Officer student billet for a prospective Medical Corps officer (Senior Medical Student Program)"},
        {"1920", "Unrestricted Line Officer student billet for a prospective Dental Corps officer"},
        {"1930", "Unrestricted Line Officer student billet for a prospective Medical Service officer (Optometry)"},
        {"1950", "Unrestricted Line Officer student billet for a prospective Judge Advocate General’s Corps Officer Law Education Program (LEP)"},
        {"1960", "Unrestricted Line Officer student billet for a prospective Medical Corps officer (Medical /Osteopathic Scholarship Program)"},
        {"2000", "Medical Department (Medical Admin) Officer billet which may be filled by an appropriately skilled and experienced individual of one of the Medical Department officer communities (LCDR and above)"},
        {"2100", "Staff Corps Officer billet requiring Medical specialty"},
        {"2102", "Staff Corps Officer billet Code 2 - Operational Flying, requiring Medical specialty of a qualified Flight Surgeon"},
        {"2200", "Staff Corps Officer billet requiring Dental specialty"},
        {"2300", "Staff Corps Officer billet requiring Medical Service (Health Care Administration, Medical Allied Science, Optometry, Pharmacy, or Medical Specialist) specialty"},
        {"2302", "Staff Corps Officer billet, Code 2 - Operational Flying, requiring specialty of a qualified Aviation Physiologist, Aviation Experimental Psychologist, or Flight Physician Assistant"},
        {"2500", "Staff Corps Officer billet requiring Law specialty"},
        {"2900", "Staff Corps Officer billet requiring Nursing specialty"},
        {"3100", "Staff Corps Officer billet requiring Supply specialty"},
        {"4100", "Staff Corps Officer billet requiring Chaplain specialty"},
        {"5100", "Staff Corps Officer billet requiring Civil Engineering specialty"},
        {"6000", "Billet which may be filled by any appropriately skilled and experienced Limited Duty Officer Line (CDR and above)"},
        {"6110", "Limited Duty Officer (Line) billet requiring management in Deck specialty (Surface)"},
        {"6120", "Limited Duty Officer (Line) billet requiring management in Operations specialty (Surface)"},
        {"6130", "Limited Duty Officer (Line) billet requiring management in Engineering/Repair specialty (Surface)"},
        {"6160", "Limited Duty Officer (Line) billet requiring management in Ordnance specialty (Surface)"},
        {"6180", "Limited Duty Officer (Line) billet requiring management in Electronics specialty (Surface)"},
        {"6200", "Limited Duty Officer (Line) billet requiring management in Nuclear Power specialty"},
        {"6210", "Limited Duty Officer (Line) billet requiring management in Deck specialty (Submarine)"},
        {"6230", "Limited Duty Officer (Line) billet requiring management in Engineering/Repair specialty (Submarine)"},
        {"6260", "Limited Duty Officer (Line) billet requiring management in Ordnance specialty (Submarine)"},
        {"6280", "Limited Duty Officer (Line) billet requiring management in Electronics specialty (Submarine)"},
        {"6290", "Limited Duty Officer (Line) billet requiring management in Communications specialty (Submarine)"},
        {"6310", "Limited Duty Officer (Line) billet requiring management in Aviation Deck specialty"},
        {"6320", "Limited Duty Officer (Line) billet requiring management in Aviation Operations specialty"},
        {"6321", "Limited Duty Officer (Line) billet, Code 1 - Operational Flying, requiring the specialty of an Aviation Operations (ASW) officer who is aeronautically designated"},
        {"6330", "Limited Duty Officer (Line) billet requiring management in Aviation Maintenance specialty"},
        {"6360", "Limited Duty Officer (Line) billet requiring management in Aviation Ordnance specialty"},
        {"6390", "Limited Duty Officer (Line) billet requiring management in Air Traffic Control specialty"},
        {"6410", "Limited Duty Officer (Line) billet requiring management in Administration specialty"},
        {"6430", "Limited Duty Officer (Line) billet requiring Bandmaster specialty"},
        {"6470", "Limited Duty Officer (Line) billet requiring management in Photography specialty. SECNAV approved disestablishment 10/1/2017"},
        {"6480", "Limited Duty Officer (Line) billet requiring management in Explosive Ordnance Disposal specialty"},
        {"6490", "Limited Duty Officer (Line) billet requiring management in Security specialty"},
        {"6510", "Staff Corps Limited Duty Officer billet requiring Supply specialty"},
        {"6530", "Staff Corps Limited Duty Officer billet requiring Civil Engineering specialty"},
        {"6550", "Staff Corps Limited Duty Officer billet requiring a non-lawyer/paralegal officer"},
        {"6800", "Limited Duty Officer (Line) billet requiring management in Meteorology/Oceanography specialty"},
        {"6810", "Limited Duty Officer (Line) billet requiring management in Cryptologic Warfare specialty"},
        {"6820", "Limited Duty Officer (Line) billet requiring management in Information Professional"},
        {"6830", "Limited Duty Officer (Line) billet requiring management in Intelligence specialty"},
        {"7110", "Warrant Officer (Line) billet requiring supervision in Boatswain specialty (Surface)"},
        {"7120", "Warrant Officer (Line) billet requiring supervision in Operations Technician specialty (Surface)"},
        {"7130", "Warrant Officer (Line) billet requiring supervision in Engineering specialty (Surface)"},
        {"7150", "Warrant Officer (Line) billet requiring supervision in Special Warfare Technician specialty"},
        {"7160", "Warrant Officer (Line) billet requiring supervision in Ordnance Technician specialty (Surface)"},
        {"7170", "Warrant Officer (Line) billet requiring supervision in Naval Special Warfare Combat Crewman"},
        {"7180", "Warrant Officer (Line) billet requiring supervision in Electronics Technician specialty"},
        {"7200", "Warrant Officer (Line) billet requiring specialty of a Diving Officer"},
        {"7210", "Warrant Officer (Line) billet requiring supervision in Boatswain specialty (Submarine)"},
        {"7230", "Warrant Officer (Line) billet requiring supervision in Engineering specialty (Submarine)"},
        {"7240", "Warrant Officer (Line) billet requiring supervision in Repair Technician specialty (Submarine)"},
        {"7260", "Warrant Officer (Line) billet requiring supervision in Ordnance Technician specialty (Submarine)"},
        {"7280", "Warrant Officer (Line) billet requiring specialty of an Acoustic Technician"},
        {"7310", "Warrant Officer (Line) billet requiring supervision in Aviation Boatswain specialty"},
        {"7320", "Warrant Officer (Line) billet requiring supervision in Aviation Operations Technician specialty"},
        {"7321", "Warrant Officer (Line) billet, Code 1 - Operational Flying, requiring the specialty of an Aviation Operations (ASW) Technician who is aeronautically designated"},
        {"7330", "Warrant Officer (Line) billet requiring supervision in Aviation Maintenance Technician specialty"},
        {"7360", "Warrant Officer (Line) billet requiring supervision in Aviation Ordnance Technician specialty"},
        {"7371", "Warrant Officer (Line) billet, Code 1 – Operational Flying, requiring the specialty of piloting Unmanned Aerial Vehicles who is aeronautically designated."},
        {"7400", "Warrant Officer (Line) billet requiring supervision in Nuclear Power Technician specialty"},
        {"7410", "Warrant Officer (Line) billet requiring supervision in Ship's Clerk specialty"},
        {"7480", "Warrant Officer (Line) billet requiring supervision in Explosive Ordnance Disposal Technician specialty"},
        {"7490", "Warrant Officer (Line) billet requiring supervision in Security Technician specialty"},
        {"7510", "Warrant Officer (Staff Corps) billet requiring supervision in Supply Corps specialty"},
        {"7520", "Warrant Officer (Staff Corps) billet requiring supervision in Food Service specialty"},
        {"7530", "Warrant Officer (Staff Corps) billet requiring supervision in Civil Engineering specialty"},
        {"7560", "Warrant Officer (Staff Corps) billet requiring Technical Nurse specialty"},
        {"7800", "Warrant Officer (Line) billet requiring supervision Oceanography"},
        {"7810", "Warrant Officer (Line) billet requiring supervision in Cryptologic Warfare Technician specialty"},
        {"7820", "Warrant Officer (Line) billet requiring supervision in Information Systems Technician specialty"},
        {"7830", "Warrant Officer (Line) billet requiring supervision in Intelligence Technician specialty"},
        {"7840", "Warrant Officer (Line) billet requiring supervision in Cyber Warfare specialty"}
    };
    mCodesHashMap = new HashMap<>(CODE_MEANING_DATA.length);

    for (String[] aCODE_MEANING_DATA : CODE_MEANING_DATA) {
      mCodesHashMap.put(aCODE_MEANING_DATA[0], aCODE_MEANING_DATA[1]);
    }
  }

  @Override
  public String getSourceInfo() {

    return "NAVPERS 15839I VOL I (JAN 2023)";
  }

  @Override
  public String getCode() {

    return "Officer Billet Codes";
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