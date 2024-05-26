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

public class SSPCodes implements ReferenceData {

  private final HashMap<String, String> mCodesHashMap;

  public SSPCodes() {
    String[][] CODE_MEANING_DATA = {
        {"2000", "National Security Studies"},
        {"2101", "Middle East, Africa, & South Asia"},
        {"2102", "Far East & Pacific"},
        {"2103", "Western Hemisphere"},
        {"2104", "Europe Russia & Associated States"},
        {"2200", "Regional Intelligence - General"},
        {"2201", "Regional Intelligence - Middle East, Africa, and South Asia"},
        {"2202", "Regional Intelligence - Far East/Pacific"},
        {"2203", "Regional Intelligence - Western Hemisphere"},
        {"2204", "Regional Intelligence - Europe, Russia"},
        {"2300", "Naval Strategy (via NWC)"},
        {"2301", "Naval Strategy (via NPS)"},
        {"2500", "Special Operations/Low Intensity Conflict"},
        {"2600", "Homeland Defense and Security"},
        {"3000", "Resource Management and Analysis - General"},
        {"3100", "Financial Management - Defense Focus / (Executive MBA) Distance Learning"},
        {"3105", "Financial Management - Civilian Focus / (Executive MBA)"},
        {"3110", "Financial Management - Advance Defense Force / (Executive MBA)"},
        {"3111", "Financial Manager"},
        {"3112", "Comptroller"},
        {"3113", "Financial Management - Energy"},
        {"3120", "Logistics and Transportation Management"},
        {"3121", "Logistics and Transportation Management - Logistics"},
        {"3122", "Logistics and Transportation Management - Transportation"},
        {"3130", "Manpower Systems Analysis Management"},
        {"3150", "Education and Training Management"},
        {"3210", "Operations Research Analysis"},
        {"3211", "Operations Research Analysis - Analysis and Assessment"},
        {"3212", "Operations Research Analysis - Logistics"},
        {"3213", "Operations Research Analysis - Energy"},
        {"4000", "General Applied Disciplines"},
        {"4100", "Mathematics Applied Disciplines"},
        {"4201", "Operational Sciences - Chemistry"},
        {"4301", "Academic Support - English"},
        {"4302", "Academic Support - History"},
        {"4400", "Public Affairs"},
        {"4500", "Leadership Education and Development"},
        {"4600", "Human Systems Integration"},
        {"4700", "Symphonic Wind Band Conducting"},
        {"5000", "Engineering and Technology (General)"},
        {"5100", "Naval Construction Engineering"},
        {"5101", "Naval Architecture"},
        {"5102", "Power Systems"},
        {"5103", "Acoustics"},
        {"5104", "Missiles"},
        {"5200", "Nuclear Engineering"},
        {"5201", "Naval Nuclear Engineering"},
        {"5202", "Reactors"},
        {"5203", "Plant Propulsion"},
        {"5300", "Electrical/Electronic Systems Engineering"},
        {"5301", "Electrical Systems"},
        {"5302", "Communication"},
        {"5303", "Electro-Magnetic"},
        {"5304", "Guidance & Navigation"},
        {"5305", "Power Systems & Electric Drive"},
        {"5306", "Digital Signal Processing"},
        {"5307", "Electronic"},
        {"5308", "Total Ship Systems"},
        {"5309", "Computer Science"},
        {"5311", "Electrical Engineering - Energy"},
        {"5400", "Aeronautical Engineering"},
        {"5401", "Aeronautical Engineering - Avionics"},
        {"5402", "Aeronautical Engineering - Aerospace"},
        {"5403", "Test Pilot"},
        {"5500", "Space Systems Engineering"},
        {"5600", "Mechanical Engineering (General)"},
        {"5601", "Naval Mechanical Engineering"},
        {"5602", "Total Ship Systems"},
        {"5603", "Mechanical Engineering - Energy"},
        {"5700", "Combat Systems"},
        {"5701", "Combat Systems - Sensors"},
        {"5702", "Combat Systems - Weapons"},
        {"5703", "Combat Systems - Physics"},
        {"5704", "Combat Systems - Acoustics"},
        {"5705", "Combat Systems - Total Ship Systems"},
        {"5706", "Combat Systems - Missiles"},
        {"5707", "Combat Systems - Software Design"},
        {"5708", "Combat Systems - Robotics"},
        {"5709", "Combat Systems - Strategic Weapons"},
        {"5710", "Combat Systems - Strategic Navigation"},
        {"5800", "Systems Engineering"},
        {"5801", "SE - Ship Systems"},
        {"5802", "SE - Combat Systems"},
        {"5803", "SE - Network Centric Systems"},
        {"5804", "SE - Aviation Systems"},
        {"6000", "General Operations"},
        {"6100", "Information, System and Operations"},
        {"6200", "Information Sciences, Systems and Operations"},
        {"6202", "Modeling & Simulation"},
        {"6203", "Computer Science and System Design"},
        {"6206", "Space Systems Operations"},
        {"6208", "CYBER Systems and Operations"},
        {"6209", "Network Operations and Technology"},
        {"6301", "Undersea Warfare"},
        {"6401", "Naval Meteorology and Oceanography Operational Sciences"},
        {"6402", "Oceanography Operational Sciences"},
        {"6403", "Meteorology Operational Sciences"},
        {"6500", "Systems Engineering and Analysis"},
        {"1101", "Facilities Engineering"},
        {"1103", "Ocean Engineering"},
        {"1201", "Military Justice (Advanced)"},
        {"1202", "Military Justice Litigation"},
        {"1203", "International Law"},
        {"1205", "Health Care Law"},
        {"1207", "Environmental Law"},
        {"1301", "Supply Acquisition, Distribution Management"},
        {"1302", "Supply Chain Management"},
        {"1304", "Transportation Logistics Management"},
        {"1306", "Acquisition and Contract Management"},
        {"1307", "Petroleum Management"},
        {"1309", "Logistics Information Technology"},
        {"1400", "Spiritual Leadership"},
        {"1430", "Religion in Culture"},
        {"1440", "Pastoral Counseling"},
        {"1450", "Ethics"},
        {"2300", "Naval Strategy (NWC & CIV INST)"},
        {"2301", "Naval Strategy (NPS)"},
        {"1500", "Medical"},
        {"15A0", "Aviation Medicine"},
        {"15A1", "Aerospace Medicine"},
        {"15B0", "Anesthesia, General"},
        {"15B1", "Anesthesia, Subspecialty"},
        {"15C0", "Surgery, General"},
        {"15C1", "Surgery, Subspecialty"},
        {"15D0", "Neurological Surgery, General"},
        {"15D1", "Neurological Surgery, Subspecialty"},
        {"15E0", "Obstetrics/Gynecology, General"},
        {"15E1", "Obstetrics/Gynecology, Subspecialty"},
        {"15F0", "General Medicine"},
        {"15G0", "Ophthalmology, General"},
        {"15G1", "Ophthalmology, Subspecialty"},
        {"15H0", "Orthopaedic Surgery, General"},
        {"15H1", "Orthopaedic Surgery, Subspecialty"},
        {"15I0", "Otolaryngology, General"},
        {"15I1", "Otolaryngology, Subspecialty"},
        {"15J0", "Urology, General"},
        {"15J1", "Urology, Subspecialty"},
        {"15K0", "Preventative Medicine, General"},
        {"15K1", "Preventative Medicine, Subspecialty"},
        {"15K2", "Occupational Medicine, General"},
        {"15L0", "Physical Medicine and Rehabilitation, General"},
        {"15L1", "Physical Medicine and Rehabilitation, Subspecialty"},
        {"15M0", "Pathology, General"},
        {"15M1", "Pathology, Subspecialty"},
        {"16N0", "Dermatology, General"},
        {"16N1", "Dermatology, Subspecialty"},
        {"16P0", "Emergency Medicine, General"},
        {"16P1", "Emergency Medicine, Subspecialty"},
        {"16Q0", "Family Practice, General"},
        {"16Q1", "Family Practice, Subspecialty"},
        {"16R0", "Internal Medicine, General"},
        {"16R1", "Internal Medicine, Subspecialty"},
        {"16T0", "Neurology, General"},
        {"16T1", "Neurology, Subspecialty"},
        {"16U0", "Undersea Medicine, General"},
        {"16U1", "Undersea Medicine, Subspecialty"},
        {"16V0", "Pediatrics, General"},
        {"16V1", "Pediatrics, Subspecialty"},
        {"16W0", "Nuclear Medicine"},
        {"16X0", "Psychiatry, General"},
        {"16X1", "Psychiatry, Subspecialty"},
        {"16Y0", "Diagnostic Radiology"},
        {"16Y1", "Radiology, Subspecialty"},
        {"16Y2", "Radiology Oncology"},
        {"1700", "Dentistry, General"},
        {"1710", "Endodontics"},
        {"1720", "Dental Education Programs"},
        {"1724", "Advanced Clinical Program (ACP in General Dentistry)"},
        {"1725", "Comprehensive Dentistry"},
        {"1730", "Maxillofacial Prosthetics"},
        {"1735", "Orthodontics"},
        {"1740", "Operative Dentistry"},
        {"1745", "Oral Medicine/Oral Diagnosis"},
        {"1749", "Advanced Clinical Program (ACP in Exodontia)"},
        {"1750", "Oral Surgery"},
        {"1760", "Periodontics"},
        {"1769", "Prosthodontics"},
        {"1775", "Public Health Dentistry"},
        {"1780", "Oral Pathology"},
        {"1785", "Orofacial Pain"},
        {"1790", "Dental Science and Research"},
        {"1795", "Pediatric Dentistry"},
        {"1800", "Health Care Administration"},
        {"1801", "Patient Administration"},
        {"1802", "Medical Logistics Administration"},
        {"1803", "Medical Data Services Administration"},
        {"1804", "Health Facility Planning and Projects"},
        {"1805", "Plans, Operations and Medical Intelligence (POMI)"},
        {"1810", "Biochemistry"},
        {"1815", "Microbiology"},
        {"1825", "Radiation Health"},
        {"1835", "Physiology"},
        {"1836", "Aerospace and Operational Physiology"},
        {"1840", "Clinical Psychology"},
        {"1841", "Child Psychology"},
        {"1842", "Neuropsychology"},
        {"1843", "Medical Psychology"},
        {"1844", "Aerospace Experimental Psychology"},
        {"1845", "Research Psychology"},
        {"1850", "Entomology"},
        {"1860", "Enviromental Health"},
        {"1861", "Industrial Hygiene"},
        {"1862", "Audiology"},
        {"1865", "Medical Technology"},
        {"1870", "Social Work"},
        {"1873", "Physical Therapy"},
        {"1874", "Occupational Therapy"},
        {"1876", "Dietetics"},
        {"1880", "Optometry"},
        {"1887", "Pharmacy, General"},
        {"1892", "Podiatry"},
        {"1893", "Physician Assistant"},
        {"1900", "Professional Nursing"},
        {"1901", "Nursing Administration"},
        {"1903", "Nursing Education"},
        {"1910", "Medical/Surgical Nursing"},
        {"1920", "Maternal and Infant Health Nursing"},
        {"1922", "Pediatric Nursing"},
        {"1930", "Psychiatric Nursing"},
        {"1940", "Community Health Nursing"},
        {"1945", "Emergency Trauma Nursing"},
        {"1950", "Preoperative Nursing"},
        {"1960", "Critical Care Nursing"},
        {"1964", "Neonatal Intensive Care Nursing"},
        {"1972", "Certified Registered Nurse Anesthetist"},
        {"1973", "Psychiatric Mental Health Nurse Practitioner"},
        {"1974", "Pediatric Nurse Practitioner"},
        {"1976", "Family Nurse Practitioner"},
        {"1980", "Women’s Health Nurse Practitioner"},
        {"1981", "Nurse Midwife"}
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

    return "Subspecialty Codes";
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