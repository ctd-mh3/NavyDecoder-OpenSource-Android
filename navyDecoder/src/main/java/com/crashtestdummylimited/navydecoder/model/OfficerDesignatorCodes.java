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

public class OfficerDesignatorCodes implements ReferenceData {

  private final HashMap<String, String> mCodesHashMap;

  public OfficerDesignatorCodes() {
    String[][] CODE_MEANING_DATA = {
        {"110X", "An Unrestricted Line Officer who is not qualified in any warfare specialty or in training for any warfare specialty"},
        {"111X", "An Unrestricted Line Officer who is qualified in Surface Warfare"},
        {"112X", "An Unrestricted Line Officer who is qualified in Submarine Warfare"},
        {"113X", "An Unrestricted Line Officer who is qualified in Special Warfare"},
        {"114X", "An Unrestricted Line Officer who is qualified in Explosive Ordnance Disposal (EOD) Warfare"},
        {"116X", "Unrestricted Line Officer who is in training for Surface Warfare qualification"},
        {"117X", "Unrestricted Line Officer who is in training for Submarine Warfare qualification"},
        {"118X", "Unrestricted Line Officer who is in training for Special Warfare qualification"},
        {"119X", "Unrestricted Line Officer who is in training for Explosive Ordnance Disposal qualification"},
        {"120X", "Special Duty Officer - Human Resource Officer A Restricted Line Officer of the Human Resources Community who will plan, program and execute life-cycle management of our Navy's most important resource - people."},
        {"121X", "Restricted Line Officer (Nuclear Power School Instructor) regulated by Program Authorization 100B."},
        {"1230", "Restricted Line Officer (Permanent Military Professor) regulated by OPNAVINST 1520.40"},
        {"128X", "Special Duty Officer-Permanent Professional Recruiter Officer (Reserve Recruiting) (LT and LCDR)"},
        {"130X", "An Unrestricted Line Officer who is a member of the aeronautical community and whose rating as a pilot or NFO has been terminated. (These officers may be assigned to 1000, 1050, 1300, 1310 or 1320 designated billets, if otherwise qualified.)"},
        {"131X", "An Unrestricted Line Officer who is qualified for duty involving flying heavier-than-air, or heavier and lighter-than-air type of aircraft as a pilot"},
        {"132X", "An Unrestricted Line Officer who is qualified for duty involving flying heavier-than-air or heavier and lighter-than-air type aircraft as a Naval Flight Officer"},
        {"137X", "An Unrestricted Line Officer who is in training for duty involving flying as a Naval Flight Officer"},
        {"139X", "An Unrestricted Line Officer who is in training for duty involving flying as a pilot"},
        {"144X", "Engineering Duty Officer who is qualified as a Ship Engineering specialist IAW MILPERSMAN 1210-190. They include specialists in Ship and Ship systems Engineering, Electronic Systems Engineering, Combat/Weapons Systems Engineering, and Ordnance Systems Engineering"},
        {"146X", "Engineering Duty Officer who is in the process of completing the prescribed program leading to designation as 144X"},
        {"150X", "A Restricted Line AED Flag Officer or Captain who was formerly either an AED officer (Aerospace Engineering--designator 151X) or an AMD officer (Aviation Maintenance--designator 152X)"},
        {"151X", "Aerospace Engineering Duty Officer (Aerospace Engineering)"},
        {"154X", "Aviation Duty Officer"},
        {"165X", "Special Duty Officer - Public Affairs"},
        {"166X", "Special Duty Officer – Strategic Sealift Officer (SSO)"},
        {"168X", "Special Duty Officer (Reserve Recruiting)"},
        {"171X", "Special Duty Officer qualified as a Foreign Area Officer"},
        {"180X", "Special Duty Officer - Oceanography"},
        {"181X", "Special Duty Officer – Cryptologic Warfare Officer"},
        {"182X", "Special Duty Officer – Information Professional Officer"},
        {"183X", "Special Duty Officer – Intelligence Officer"},
        {"184X", "Special Duty Officer – Cyber Warfare Engineer"},
        {"186X", "Special Duty Officer – IWC Flag Officer"},
        {"187X", "Special Duty Officer - Maritime Space Officer"},
        {"188X", "Special Duty Officer - Maritime Cyber Warfare Officer (O1 to O6)"},
        {"190X", "An Unrestricted Line Officer under instruction as a prospective Nurse Corps officer"},
        {"191X", "An Unrestricted Line Officer under instruction as a prospective Medical Corps officer (Senior Medical Student)"},
        {"192X", "An Unrestricted Line Officer under instruction as a prospective Dental Corps officer"},
        {"193X", "An Unrestricted Line Officer under instruction as a prospective Medical Service officer (Optometry)"},
        {"194X", "An Unrestricted Line Officer under instruction as a prospective Chaplain Corps officer"},
        {"195X", "An Unrestricted Line Officer under instruction as a prospective Judge Advocate General's Corps officer"},
        {"196X", "An Unrestricted Line Officer under instruction as a prospective Medical Corps officer (Medical /Osteopathic Scholarship Program)"},
        {"197X", "An Unrestricted Line Officer under instruction in the Armed Forces Health Professions Scholarship Program (Medical/Osteopathic)"},
        {"198X", "An Unrestricted Line Officer under instruction in the Armed Forces Health Professions Scholarship Program (Dental)"},
        {"199X", "An Unrestricted Line Officer under instruction in the Armed Forces Health Professions Scholarship Program (Medical Service Corps)"},
        {"210X", "A Medical Corps Officer"},
        {"220X", "A Dental Corps Officer"},
        {"230X", "A Medical Service Corps Officer"},
        {"250X", "A Judge Advocate General Corps Officer"},
        {"270X", "Active duty Nurse Corps, Medical Service Corps, Medical Corps, and Dental Corps officer in rank of O7"},
        {"290X", "A Nurse Corps Officer"},
        {"310X", "A Supply Corps Officer"},
        {"316X", "A direct commissioned Supply Corps Officer in training for qualification"},
        {"410X", "A Chaplain Corps Officer"},
        {"510X", "A Civil Engineer Corps Officer"},
        {"611X", "A Limited Duty Officer (Deck - Surface)"},
        {"612X", "A Limited Duty Officer (Operations - Surface)"},
        {"613X", "A Limited Duty Officer (Engineering/Repair - Surface)"},
        {"616X", "A Limited Duty Officer (Ordnance - Surface)"},
        {"618X", "A Limited Duty Officer (Electronics - Surface)"},
        {"620X", "A Limited Duty Officer (Nuclear Power); Formerly DESIG 640X"},
        {"621X", "A Limited Duty Officer (Deck - Submarine)"},
        {"623X", "A Limited Duty Officer (Engineering/Repair - Submarine)"},
        {"626X", "A Limited Duty Officer (Ordnance - Submarine)"},
        {"628X", "A Limited Duty Officer (Electronics - Submarine)"},
        {"629X", "A Limited Duty Officer (Communications - Submarine)"},
        {"631X", "A Limited Duty Officer (Aviation Deck)"},
        {"632X", "A Limited Duty Officer (Aviation Operations)"},
        {"633X", "A Limited Duty Officer (Aviation Maintenance)"},
        {"636X", "A Limited Duty Officer (Aviation Ordnance)"},
        {"639X", "A Limited Duty Officer (Air Traffic Control)"},
        {"641X", "A Limited Duty Officer (Administration)"},
        {"643X", "A Limited Duty Officer (Bandmaster)"},
        {"647X", "A Limited Duty Officer (Photography)"},
        {"648X", "A Limited Duty Officer (Explosive Ordnance Disposal)"},
        {"649X", "A Limited Duty Officer (Security)"},
        {"651X", "A Limited Duty Officer of the Supply Corps"},
        {"653X", "A Limited Duty Officer of the Civil Engineer Corps"},
        {"655X", "A Limited Duty Officer of the Judge Advocate General Corps"},
        {"680X", "A Limited Duty Officer (Meteorology/Oceanography) Formerly DESIG 646X"},
        {"681X", "A Limited Duty Officer (Cryptologic Warfare) Formerly DESIG 644X"},
        {"682X", "A Limited Duty Officer (Information Professional) Formerly DESIG 642X"},
        {"683X", "A Limited Duty Officer (Intelligence) Formerly DESIG 645X"},
        {"711X", "Boatswain (Surface)"},
        {"712X", "Operations Technician (Surface)"},
        {"713X", "Engineering Technician (Surface)"},
        {"715X", "Special Warfare Technician"},
        {"716X", "Ordnance Technician (Surface)"},
        {"717X", "A Chief Warrant Officer with sophisticated, technical knowledge in all aspects of the Naval Special Warfare Combat Crewman"},
        {"718X", "Electronics Technician (Surface)"},
        {"720X", "Diving Officer"},
        {"721X", "Boatswain (Submarine)"},
        {"723X", "Engineering Technician (Submarine)"},
        {"724X", "Repair Technician (Submarine)"},
        {"726X", "Ordnance Technician (Submarine)"},
        {"728X", "Acoustic Technician"},
        {"731X", "Aviation Boatswain"},
        {"732X", "Aviation Operations Technician"},
        {"733X", "Aviation Maintenance Technician"},
        {"736X", "Aviation Ordnance Technician"},
        {"737X", "Air Vehicle Pilot"},
        {"740X", "Nuclear Power Technician"},
        {"741X", "Ship's Clerk"},
        {"748X", "Explosive Ordnance Disposal Technician"},
        {"749X", "Security Technician"},
        {"751X", "Supply Corps Warrant"},
        {"752X", "Food Service Warrant"},
        {"753X", "Civil Engineering Warrant"},
        {"756X", "Technical Nurse"},
        {"780X", "Oceanography Warrant Officer"},
        {"781X", "Cryptologic Warfare Technician Formerly DESIG 744X"},
        {"782X", "Information Systems Technician Formerly DESIG 742X"},
        {"783X", "Intelligence Technician Formerly DESIG 745X"},
        {"784X", "Cyber Warrant Officer Formerly DESIG 743X"}
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