Download "Excel Version - Additional Qualification Designation (AQD) Codes"
from https://www.mynavyhr.navy.mil/References/NOOCS-Manual/NOOCS-VOL-1/

**** JUL20206 ****
Issues that had to be manually fixes
- DoN Excel row for CI1-6 (Major Project Manager) incorrectly has this as CH1-6 (repeat of the row able).
- DoN Excel rows for TF1, TF2, and TF3 (Engineer Officer, Motor) incorrectly has this as TEs
- DoN Excel row for SZ1 does not have 3rd title "Unmanned Undersea Vehicle Specialist"
- DoN Excel rows for 2N3 and 2N4 do not have 2nd and 3rd title

(OLD) Steps (Not verified to be 100% correct):
- Use customized and added "Modified" tab to create output SQL
- Copy needed columns (at least columns AQD Code to 3rd Title from AQD A SERIES to 7 SERIES tab) to "Modified"
- Copy and ensure alignment of AQD, 2nd title, and 3rd title columns from 
   "AQD 8 AND 9 SERIES" tab to the "Modified" tab
- Remove unneeded rows passed on length column (>5, also "Joint" row)
- "Modified" tab should perform needed formatting changes
  + Remove carriage returns
  + Remove trailing and leading spaces
- Create SQL statements


AI Prompt to Perform Processing (31OCT2026):

AQD PROCESSING

Attach these files:
  1. The previous edition's processed workbook (the baseline):  EXCEL_Spreadsheet_for_AQDs_<PrevMon>_<YY>.xlsx  (has a "Modified" tab)
  2. The new raw file:  EXCEL_Spreadsheet_for_AQDs_<Mon>_<YY>.xlsx  (tabs "AQD A SERIES TO 7 SERIES" and "AQD 8 AND 9 SERIES")
  3. OPTIONAL but recommended: the Navy's Part D change list (ADDITIONS / DELETIONS / REVISIONS) from the NAVPERS 15839I change notice, as a PDF, screenshot or pasted text. Also include the PDF's entries for any codes that are on the list but may be missing from the Excel file.
If file 1 or 2 is missing, stop and ask me for it. If file 3 is missing, ask once whether I want to provide it. If I don't, skip check 2f.
<Mon>_<YY> comes from the raw file's name. <PREV> means the baseline's month and year, for example "JUL 2026".

STEP 1 – CHECK THE INPUTS
- Confirm both raw tabs exist and their header rows exactly match the same tabs in the baseline. If they don't, stop and tell me what's different.
- Build the candidate row list, in this order: all rows of "AQD A SERIES TO 7 SERIES" (top to bottom), then all rows of "AQD 8 AND 9 SERIES".
  - Tab 1: AQD CODE = column A, 2ND TITLE = column E, 3RD TITLE = column G. Keep a row if 2ND TITLE or 3RD TITLE is not blank.
  - Tab 2: AQD CODE = column A, 2ND TITLE = column D (TITLE), 3RD TITLE = blank. Keep a row if TITLE is not blank.
  - Rows with no title are section headers (for example "ACQUISITION", "LEGAL SERVICES") or blank rows. Leave them out, but see check 2c.
  - Keep codes that aren't 3 characters, such as "CA1-6", "RDX*", "S*N0" and "SW**1".

STEP 2 – DATA-ISSUE CHECKS (find everything first, then ask me ONE question)
For each issue, propose a fix and show the evidence (raw row, baseline row, PDF entry).
  a. Duplicate AQD codes: two or more candidate rows share a code. Propose the correct code, taken from the baseline row with the same titles (for example the 2nd "CH1-6 Major Project Manager" → CI1-6; "TE1–TE3 Engineer Officer, Motor" → TF1–TF3), or else from the row's own 1ST + 2ND CODE + 3RD CODE columns.
  b. Code doesn't match its parts: on tab 1, the AQD CODE disagrees with 1ST + 2ND CODE + 3RD CODE. Ignore codes containing "*" or "-", and rows where the parts are blank. Propose a code fix only if the baseline doesn't already have this code with the same titles. Otherwise report it as a parts-column error only.
  c. Missing titles: a code that is in the baseline with titles now has a blank 2ND or 3RD TITLE in the raw file, or appears only as a title-less row (for example SZ1 missing its 3rd title; 2N3 and 2N4 missing both). Propose carrying the baseline titles forward.
  d. Codes in the baseline but not in the new raw file, after the fixes above: list them. Ask whether each was really removed.
  e. Spelling: check every 2ND and 3RD TITLE with a spell-checker and your own judgment. Ignore acronyms, program and system names, proper nouns and correctly spelled specialist terms. List only clear misspellings, with the suggested correction and the codes affected.
  f. Navy change-list cross-check (only if file 3 was provided). Compare the change list with the Excel data, after fixes a–d:
     - ADDITIONS missing from the Excel file (for example 24K): propose adding the code, taking its 2ND and 3RD TITLE from the PDF entry. Place it after the nearest preceding code in the same series (for example 24K after 24J). If the PDF entry wasn't provided, ask me for the titles.
     - Codes new in the Excel file (not in the baseline) but not on the ADDITIONS list (for example 4IN): ask whether to keep each one.
     - Likely typos in the change list (for example "KXL" when JXL is in the Excel file): note them and treat them as the matching code.
     - DELETIONS: confirm each listed code is absent, and flag any listed code that is still present.
     - REVISIONS: for each listed code, report whether anything changed between the baseline and new raw rows, and whether that change affects the 2ND or 3RD TITLE (and so the SQL). Flag listed codes with no change at all, and codes that don't exist in either file (for example 3W2). Report only; don't change anything.
     - Ignore section-header entries in the list (for example "Cyberspace Operations Community 'W'").
Ask me in ONE question listing every proposed fix and decision, grouped by type (a–f). Let me approve or reject each one, or give my own correction. Wait for my answer.
If nobody answers (for example in a scheduled run), apply no fixes, continue, and list everything in the reply.

STEP 3 – AUTOMATIC CLEAN-UP (no approval needed; record each change)
  - Line breaks (CR/LF) inside titles → a space, then TRIM (removes leading and trailing spaces and collapses double spaces).
  - Non-ASCII punctuation → plain ASCII: ’ ‘ → ', “ ” → ", – — ‑ → -, non-breaking space → space. This also applies to titles added from the PDF. Report any other non-ASCII characters (for example é) without changing them.

STEP 4 – BUILD THE WORKBOOK  EXCEL_Spreadsheet_for_AQDs_<Mon>_<YY>.xlsx
Tabs, in order:
  1. Source – copy from the baseline as it is.
  2. AQD A SERIES TO 7 SERIES – the new raw tab, completely unchanged.
  3. AQD 8 AND 9 SERIES – the new raw tab, completely unchanged.
  4. Modified – one row per candidate row (Step 1), with approved fixes applied (Step 2), and approved PDF additions inserted at their positions:
     A  AQD CODE              – raw code, or the approved corrected or added code
     B  2ND TITLE             – raw title, or the approved fix (carried-forward title, spelling correction, or PDF title)
     C  2ND TITLE (No CRs)    =+TRIM(SUBSTITUTE(SUBSTITUTE(B2,CHAR(13)," "),CHAR(10)," "))
     D  3RD TITLE             – raw title, or the approved fix
     E  3RD TITLE (No CRs)    =+TRIM(SUBSTITUTE(SUBSTITUTE(D2,CHAR(13)," "),CHAR(10)," "))
     F  Length of AQD Code    =+LEN(A2)
     G  Text for Navy Decoder Plus's fill_table_aqd_codes.sql
          ="insert into FTS_aqd_codes (suggest_text_1, suggest_text_2, source) values ('" & SUBSTITUTE(A2,"'","''") & "','" & SUBSTITUTE(TEXTJOIN("-",TRUE,C2,E2),"'","''") & "','NAVPERS 15839I VOL I (MON YYYY)');"
          (replace MON YYYY with the month and year from the raw file's name, for example OCT 2026)
     H  Change vs <PREV>      – "New" (code not in the baseline), "Changed" (2ND or 3RD TITLE differs from the baseline), or blank (unchanged).
                                Compare the column C and E text with the baseline Modified tab's columns C and E, ignoring differences that are only spaces, line breaks, or curly vs plain punctuation. Match rows by AQD code, after the Step 2 fixes.
     I  Change Details        – for "Changed": "2ND: old → new; 3RD: old → new". For "New": the source tab and row number, or "From NAVPERS 15839I PDF (not in DoN Excel)".
     J  Fixes Applied (vs raw) – every way this row differs from the raw file: code corrected, title carried forward, spelling, added from PDF, line breaks, ASCII. Blank if none.
     - Apply the Step 3 clean-up to columns B and D (line breaks are left in B and D; C and E remove them). Never change the two raw tabs.
     - Formatting: copy the baseline's Modified header style and column widths. Freeze the header row, filter A1:J<last row>, and shade light yellow any row where J is not blank.
  5. Removed Since <PREV> – baseline Modified rows whose AQD code is not in the new Modified tab: AQD CODE, 2ND TITLE, 3RD TITLE, and Note (for example "confirmed removed", "on Navy DELETIONS list", or "not answered"). If there are none, write "(none – every <PREV> AQD code is present)".

STEP 5 – RECALCULATE AND CREATE THE SQL FILE
- Recalculate the workbook (for example with LibreOffice) so every formula has a stored value, then confirm there are no errors (#NAME?, #VALUE!, and so on).
- Check that the recalculated column G values match your own expected values for every row. If any don't, list them and stop.
- Create "fill_table_aqd_codes.sql" from the workbook's recalculated column G values (one per Modified row, in order). It must start with exactly these lines:
.echo on
.mode column
.headers on
.nullvalue NULL
(followed by two blank lines), then the statements, one per line.
- Test that every statement loads into a table FTS_aqd_codes(suggest_text_1, suggest_text_2, source), and that each AQD code appears only once. List any non-ASCII characters left in the file.

REPLY
Send both files, then summarize:
- the number of candidate rows, the number of rows added from the PDF, the number of SQL statements, and the number of section-header and blank rows left out
- the fixes applied, by type (a–f): code / before → after; the proposed fixes I rejected or that weren't answered
- the automatic clean-up: the number of line-break fixes, and the ASCII fixes with their codes
- changes vs <PREV>: the number New, Changed and Removed, listing each one (code, old → new)
- Navy change-list cross-check: additions found, missing or added from the PDF; Excel additions not on the list; deletions confirmed; revisions with and without a change in the Excel file (and whether the SQL is affected); typos in the change list
- anything else that looks wrong (empty titles, very long titles, parts-column errors). Report these only; don't change them.


