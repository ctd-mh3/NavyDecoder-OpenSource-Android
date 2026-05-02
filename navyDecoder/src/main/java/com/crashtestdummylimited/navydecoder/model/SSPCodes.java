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

public class SSPCodes implements ReferenceData {

  private final HashMap<String, String> mCodesHashMap;
  private final String[] mSortedKeys;

  public SSPCodes() {
    String[][] CODE_MEANING_DATA = {
      {"2000", "National Security Studies"},
      {"2101", "Middle East, Africa and South Asia"},
      {"2102", "Far East and Pacific"},
      {"2103", "Western Hemisphere"},
      {"2104", "Europe, Russia and Associated States"},
      {"2200", "Regional Intelligence - General"},
      {"2201", "Regional Intelligence - Middle East, Africa and South Asia"},
      {"2202", "Regional Intelligence - FarEast/Pacific"},
      {"2203", "Regional Intelligence - Western Hemisphere"},
      {"2204", "Regional Intelligence - Europe, Russia"},
      {"2300", "Naval Strategy (NWC & CIV INST)"},
      {"2301", "Strategic Studies"},
      {"2400", "Strategic Intelligence"},
      {"2500", "Special Operations"},
      {"2600", "Homeland Security & Defense"},
      {"3000", "Resource Management and Analysis - General"},
      {"3100", "Financial Management - Defense Focus Distance Learning"},
      {"3105", "Financial Management - Civilian Focus"},
      {"3110", "Financial Management"},
      {"3111", "Financial Manager"},
      {"3112", "Comptroller"},
      {"3113", "Financial Management - Energy"},
      {"3120", "Logistics and Transportation Management"},
      {"3121", "Logistics Management"},
      {"3122", "Logistics and Transportation Management - Transportation"},
      {"3130", "Manpower Systems Analysis"},
      {"3150", "Education and Training Management"},
      {"3211", "Operations Research Analysis - Analysis and Assessment"},
      {"3212", "Operations Research - Logistics Analysis"},
      {"4000", "General Applied Disciplines"},
      {"4100", "Applied Mathematics"},
      {"4201", "Operational Sciences - Chemistry"},
      {"4301", "Academic Support - English"},
      {"4302", "Academic Support - History"},
      {"4400", "Public Affairs"},
      {"4500", "Leadership Education and Development"},
      {"4600", "Human Systems Integration"},
      {"4700", "Symphonic Wind Band Conducting"},
      {"5000", "Engineering and Technology (General)"},
      {"5100", "Naval Construction and Engineering"},
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
      {"5302", "Electrical Systems Engineering: Communications Systems"},
      {"5303", "Electro-Magnetic"},
      {"5304", "Guidance, Control, & Navigation Systems"},
      {"5305", "Power/Energy Systems"},
      {"5306", "Digital Signal Processing"},
      {"5307", "Electronics"},
      {"5308", "Total Ship Systems - Electrical"},
      {"5309", "Computer Systems"},
      {"5310", "Sensor Systems Engineering"},
      {"5311", "Electrical Engineering - Energy"},
      {"5312", "Network Engineering"},
      {"5313", "Cyber Systems"},
      {"5400", "Aeronautical Engineering"},
      {"5401", "Aeronautical Engineering - Avionics"},
      {"5402", "Aeronautical Engineering - Aerospace Engineering"},
      {"5403", "Naval Test Pilot"},
      {"5500", "Space Systems Engineering"},
      {"5600", "Naval/Mechanical Engineering"},
      {"5601", "Mechanical Engineering"},
      {"5602", "Total Ship System Engineering"},
      {"5603", "Mechanical Engineering - Energy"},
      {"5700", "Applied Physics of Combat Systems"},
      {"5701", "Applied Physics of Combat Systems - Sensors"},
      {"5702", "Applied Physics of Combat Systems - Weapons"},
      {"5703", "Applied Physics of Combat Systems - Physics"},
      {"5704", "Applied Physics of Combat Systems - Acoustics"},
      {"5705", "Applied Physics of Combat Systems - Total Ship Systems"},
      {"5706", "Applied Physics of Combat Systems - Missiles"},
      {"5707", "Applied Physics of Combat Systems - Software Design"},
      {"5708", "Applied Physics of Combat Systems - Robotics"},
      {"5709", "Applied Physics of Combat Systems - Strategic Weapons"},
      {"5710", "Applied Physics of Combat Systems - Strategic Navigation"},
      {"5800", "Systems Engineering"},
      {"5801", "SE - Ships Systems"},
      {"5802", "SE - Combat Systems"},
      {"5803", "SE - Network Centric Systems"},
      {"5804", "SE - Aviation Systems"},
      {"6000", "General Operations"},
      {"6202", "Modeling, Virtual Environments and Simulation"},
      {"6203", "Computer Science and Systems Design"},
      {"6206", "Space Systems Operations"},
      {"6208", "CYBER Systems and Operations"},
      {"6209", "Network Operations and Technology"},
      {"6401", "Naval Meteorology and Oceanography Operational Sciences"},
      {"6402", "Oceanography Operational Sciences"},
      {"6403", "Meteorology Operational Sciences"},
      {"6301", "Undersea Warfare"},
      {"6500", "Systems Engineering Analysis"},
      {"6501", "Systems Engineering Analysis"},
      {"6502", "Program Management/ Program Management (DL)"},
      {"6511", "Requirements Management"},
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
      {"15H0", "Orthopedic Surgery, General"},
      {"15H1", "Orthopedic Surgery, Subspecialty"},
      {"15I0", "Otolaryngology, General"},
      {"15I1", "Otolaryngology, Subspecialty"},
      {"15J0", "Urology, General"},
      {"15J1", "Urology, Subspecialty"},
      {"15K0", "Preventative Medicine, General"},
      {"15K1", "Preventative Medicine, Subspecialty"},
      {"15K2", "Occupational Medicine, General"},
      {"15L0", "Physical Medicine & Rehabilitation, General"},
      {"15L1", "Physical Medicine & Rehabilitation, Subspecialty"},
      {"15M0", "Pathology, General"},
      {"15M1", "Pathology, Subspecialty"},
      {"16N0", "Dermatology, General"},
      {"16N1", "Dermatology, Subspecialty"},
      {"16P0", "Emergency Medicine, General"},
      {"16P1", "Emergency Medicine, Subspecialty"},
      {"16Q0", "Family Medicine, General"},
      {"16Q1", "Family Medicine, Subspecialty"},
      {"16R0", "Internal Medicine, General"},
      {"16R1", "Internal Medicine Subspecialty"},
      {"16T0", "Neurology, General"},
      {"16T1", "Neurology Subspecialty"},
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
      {"1724", "Advanced Clinical Programs (ACP in General Dentistry)"},
      {"1725", "Comprehensive Dentistry"},
      {"1730", "Maxillofacial Prosthetics"},
      {"1735", "Orthodontics"},
      {"1740", "Operative Dentistry"},
      {"1745", "Oral and Maxillofacial Radiology"},
      {"1749", "Advanced Clinical Programs (ACP in Exodontia)"},
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
      {"1803", "Health Information Technology"},
      {"1804", "Health Facility Planning and Projects"},
      {"1805", "Plans, Operations, and Medical Intelligence (POMI)"},
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
      {"1860", "Environmental Health"},
      {"1861", "Industrial Hygiene"},
      {"1862", "Occupational Audiology"},
      {"1865", "Medical Laboratory Science"},
      {"1870", "Clinical Social Worker"},
      {"1873", "Physical Therapy"},
      {"1874", "Occupational Therapy"},
      {"1876", "Dietetics"},
      {"1880", "Optometry"},
      {"1887", "Pharmacy, General"},
      {"1892", "Podiatry"},
      {"1893", "Physician Assistant"},
      {"1900", "Professional Nursing"},
      {"1903", "Nursing Education"},
      {"1910", "Medical/Surgical Nursing"},
      {"1920", "Maternal and Infant Health Nursing"},
      {"1922", "Pediatric Nursing"},
      {"1930", "Psychiatric Nursing"},
      {"1940", "Public Health Nursing"},
      {"1945", "Emergency Trauma Nursing"},
      {"1950", "Perioperative Nursing"},
      {"1960", "Critical Care Nursing"},
      {"1964", "Neonatal Intensive Care Nursing"},
      {"1972", "Certified Registered Nurse Anesthetist"},
      {"1973", "Psychiatric Mental Health Nurse Practitioner"},
      {"1974", "Pediatric Nurse Practitioner"},
      {"1976", "Family Nurse Practitioner"},
      {"1980", "Women's Health Nurse Practitioner"},
      {"1981", "Nurse Midwife"},
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

    return "NAVPERS 15839I VOL I (APR 2026)";
  }

  @Override
  public String getCode() {

    return "Subspecialty Codes";
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
