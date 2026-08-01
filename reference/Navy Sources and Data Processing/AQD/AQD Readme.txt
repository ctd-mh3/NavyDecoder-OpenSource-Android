Download "Excel Version - Additional Qualification Designation (AQD) Codes"
from https://www.mynavyhr.navy.mil/References/NOOCS-Manual/NOOCS-VOL-1/

**** JUL20206 ****
Issues that had to be manually fixes
- DoN Excel row for CI1-6 (Major Project Manager) incorrectly has this as CH1-6 (repeat of the row able).
- DoN Excel rows for TF1, TF2, and TF3 (Engineer Officer, Motor) incorrectly has this as TEs
- DoN Excel row for SZ1 does not have 3rd title "Unmanned Undersea Vehicle Specialist"
- DoN Excel rows for 2N3 and 2N4 do not have 2nd and 3rd title

Steps (Not verified to be 100% correct):
- Use customized and added "Modified" tab to create output SQL
- Copy needed columns (at least columns AQD Code to 3rd Title from AQD A SERIES to 7 SERIES tab) to "Modified"
- Copy and ensure alignment of AQD, 2nd title, and 3rd title columns from 
   "AQD 8 AND 9 SERIES" tab to the "Modified" tab
- Remove unneeded rows passed on length column (>5, also "Joint" row)
- "Modified" tab should perform needed formatting changes
  + Remove carriage returns
  + Remove trailing and leading spaces
- Create SQL statements
