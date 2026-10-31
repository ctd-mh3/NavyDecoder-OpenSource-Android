Download "Excel Version - Navy Officer Billet Classification (NOBC) Codes"
from https://www.mynavyhr.navy.mil/References/NOOCS-Manual/NOOCS-VOL-1/

(OLD) Steps (Not verified to be 100% correct):
- Copy existing xlsm to new file for Mon_YY
- Replace original content from downloaded new file
- Use customized and added "Modified" tab to create output SQL
- Copy needed columns (NOBC, LONG TITLE) to "Modified"
- Look at existing rows where there is a "Yes" in "Had to Tweak" Column.  These are rows where
  the title text had to be tweaked for some reason (e.g., Microsoft Excel function messed up Acronym
  capitalization) 
- Create SQL statements and java code


Specific Prompt for AI to Perform Quarterly Update (31OCT2026):

NOBC PROCESSING – TWO PHASES
The .sql file must always be generated from values Excel calculated in the final .xlsm, never from your own calculations alone.

═══════════════ PHASE 1 – BUILD THE NEW WORKBOOK ═══════════════
Attach BOTH files:
  1. The latest raw NOBC file:  EXCEL Spreadsheet for NOBCs_<Mon>_<YY>.xlsx  (one sheet, 24 columns)
  2. The previous edition's model workbook:  EXCEL Spreadsheet for NOBCs_<PrevMon>_<YY>.xlsm
If either file is missing, stop and ask me for it.

STEP 1 – SPELLING CHECK (do this first, before building any output)
Check the LONG TITLE of every row in the raw file whose NOBC is exactly 4 characters for obvious spelling mistakes (for example "AMPIBIOUS" → "AMPHIBIOUS").
  - Use a spell-checker and your own judgment. Ignore military acronyms, program and system names, and correctly spelled specialist terms (for example Cryptologic, Maxillofacial, Sealift, Drydocking, Geospatial, Holdees). Only list words that are clearly misspelled.
  - Skip misspellings that the alias list below already corrects.
  - Ask me which corrections to make, in one question that lists each suspected misspelling, the suggested correction, and the NOBCs it appears in. Let me approve or reject each one, or give my own spelling. Wait for my answer before continuing.
  - If nobody answers (for example in a scheduled run), make no spelling corrections, continue, and list the suspected misspellings in the reply.
  - Add each approved correction to the alias list as a whole-word pair written the way PROPER would produce it (for example Ampibious→Amphibious).
  - Never change the Original tab or column B of the Modified tab.

STEP 2 – ACRONYM CHECK
Find likely acronyms that PROPER would put in mixed case (for example a new program name like "RAS" → "Ras") and that are NOT on the alias list. Ask me about them in the same question as Step 1, and add the ones I approve to the alias list.

STEP 3 – CHARACTER CLEAN-UP CHECK
Find every LONG TITLE (4-character NOBCs) that contains a non-standard hyphen, dash or space, or a run of two or more hyphens. Column D's formula (below) makes these corrections automatically, with no macro change needed:
  - non-breaking hyphen ‑ (U+2011), hyphen ‐ (U+2010), en dash – (U+2013), em dash — (U+2014) → normal hyphen "-"
  - non-breaking space (U+00A0) → normal space
  - after that, a doubled hyphen "--" → a single space
If you find any OTHER non-ASCII character, or a run of three or more hyphens, don't change it. Report it with a suggested fix.

STEP 4 – ALIAS LIST AND MACRO CHECK
Full alias list (whole-word, case-insensitive, applied in this order), followed by any spelling corrections and acronyms approved in Steps 1–2:
  Metoc→METOC, Gi&s→GI&S, Aoc→AOC, Aadc→AADC, Ship'S→Ship's, Seal→SEAL, Adp→ADP, Cno→CNO,
  Isr→ISR, Psi→PSI, Ras→RAS, Misr→MISR, Humint→HUMINT, Nfo→NFO, Heo→HEO, Cms→CMS,
  Dawia→DAWIA, Trng→TRNG, Ampibious→Amphibious
Read the pairs array in the template's ReplaceAliasesWholeWord macro (Module1) and compare it with the full list. You cannot edit macro code, so record whether a MACRO UPDATE IS NEEDED (the lists differ) or NOT NEEDED (they match).

STEP 5 – BUILD THE WORKBOOK
Using the .xlsm as the template, build EXCEL Spreadsheet for NOBCs_<Mon>_<YY>.xlsm, where <Mon>_<YY> comes from the raw file's name. Keep the template's VBA project, tab names, tab order and formatting. Save it as .xlsm.
- Source: leave it as it is in the template.
- Original: replace all data rows with the rows from the raw file (all 24 columns, unchanged, in the same order). First confirm the raw file's header row exactly matches the template's Original headers. If it doesn't, stop and tell me what's different. Keep the NOBC column as text (keep leading zeros) and freeze the header row.
- Modified: rebuild one row for every data row in Original (row 2 shown, copied down):
    A  NOBC                         – value copied from Original column A
    B  LONG TITLE                   – value copied from Original column B
    C  Corrected Capitalization     =+PROPER(B2)
    D  Tweaked Capitalization       (entered as an array formula, as in the template)
         =ReplaceAliasesWholeWord(SUBSTITUTE(SUBSTITUTE(SUBSTITUTE(SUBSTITUTE(SUBSTITUTE(SUBSTITUTE(C2,UNICHAR(8209),"-"),UNICHAR(8208),"-"),UNICHAR(8211),"-"),UNICHAR(8212),"-"),UNICHAR(160)," "),"--"," "))
    E  NOBC Row Based on Length of 4?   =+IF(LEN(A2)=4,"Y","N")
    F  Macro Tweaked?               =+IF(EXACT(C2,D2),"","YES")
    G  Text for Navy Decoder's NOBCCodes.java file
                                    =+CONCAT(REPT(" ",6),"{""",TRIM(A2),""", """,TRIM(D2),"""},")
         (6 spaces, matching the indent in NOBCCodes.java; use spaces, not tab characters, because Excel puts quotes around copied cells that contain tabs)
    H  Text for Navy Decoder Plus's fill_table_nobc_codes.sql file
                                    ="insert into FTS_nobc_codes (suggest_text_1, suggest_text_2, source) values ('" & TRIM(SUBSTITUTE(A2,"'","''")) & "','" & TRIM(SUBSTITUTE(D2,"'","''")) & "','NAVPERS 15839 VOL I (MON YYYY)');"
  - Replace MON YYYY with the month and year from the raw file's name (for example "Oct_26" becomes "OCT 2026").
  - Delete any leftover rows from the template below the new last row.
  - Turn on the filter for A1:H<last row>, filter column E to "Y", and hide the "N" rows. Freeze the header row.
- Also calculate the EXPECTED column D and H values yourself, exactly as Excel would, for use in Phase 2:
  PROPER (capitalize any letter that follows a non-letter and lowercase the rest), then the SUBSTITUTEs in order, then the full alias list, then TRIM (remove leading and trailing spaces and collapse runs of regular spaces into one).
  Check these against the template's cached values for rows whose NOBC and LONG TITLE didn't change. They must match, except where a newly added alias, spelling correction or clean-up applies.

PHASE 1 REPLY – then STOP. Do NOT create the .sql file in Phase 1.
Send the new .xlsm, then:
- At the top, in bold: "MACRO UPDATE NEEDED" or "NO MACRO UPDATE NEEDED".
- If an update is needed: list the pairs being added or changed, and give the complete replacement pairs block to paste over the existing "pairs = Array( ... )" block in ReplaceAliasesWholeWord.
- Then give these steps. Include step 2 only if a macro update is needed:
    1. Open the new .xlsm in Excel and click Enable Content (or Enable Macros).
    2. Update the macro:
         Windows: press Alt+F11.  Mac: press Option+F11, or use Tools ▸ Macro ▸ Visual Basic Editor.
         Double-click Module1, replace the pairs = Array( ... ) block, and keep the other functions as they are. Close the editor.
    3. Force a full recalculation:
         Windows: press Ctrl+Alt+F9.
         Mac (no full-recalculation shortcut exists): go to the Modified tab, select D2 down to the last row (click D2, then Cmd+Shift+Down), open Edit ▸ Find ▸ Replace, set Find what to "=" and Replace with to "=", and click Replace All. This re-enters every column D formula so it recalculates with the current macro. Columns F, G and H update automatically. If Excel complains about changing part of an array, run the same Replace All with the whole column D selected.
    4. Check that columns D and H show text (not #NAME? or #VALUE!), then save the file as .xlsm.
    5. Attach the saved .xlsm here to start Phase 2.
- Summary: the number of rows in Original, the expected number of SQL statements, and the number excluded (E = "N"); spelling, acronym and hyphen/dash corrections (NOBC, before → after); what changed compared with the previous edition (NOBCs added, NOBCs removed, titles changed old → new); anything else that looks wrong in the new data (duplicated text, odd grade ranges, other non-ASCII characters). Report these only; don't change them.

═══════════════ PHASE 2 – VERIFY AND CREATE THE SQL FILE ═══════════════
When I attach the saved .xlsm from Phase 1:
1. Read the macro's pairs array and confirm it exactly matches the full alias list from Phase 1. If it doesn't, list the differences and stop.
2. Confirm the workbook was recalculated in Excel: every row where E = "Y" must have a value Excel calculated (not empty) in columns D and H, with no #NAME?, #VALUE! or other errors. If not, tell me which rows are affected, repeat the recalculation steps for Windows and Mac from Phase 1, and stop.
3. Compare Excel's column D and H values with your expected values from Phase 1. Every row must match exactly. If any don't, list them (NOBC, Excel value, expected value), remind me to recalculate as in Phase 1 step 3, and stop. Don't create the .sql file.
4. Only when all checks pass: create "fill_table_nobc_codes.sql" from Excel's own column H values (not your calculations). It must start with exactly these lines:
.echo on
.mode column
.headers on
.nullvalue NULL
(followed by two blank lines), then the column H value for every row where E = "Y", one per line, in spreadsheet order.
5. Test that every statement loads into a table FTS_nobc_codes(suggest_text_1, suggest_text_2, source), that each NOBC appears only once, and that the file contains no non-ASCII characters.

PHASE 2 REPLY
Send the .sql file and confirm: the macro pairs match, the workbook was recalculated, all <n> rows match the expected values, the number of statements, that the test load passed, and that the file contains no non-ASCII characters. Also report the number of rows marked "YES" in column F, split by cause (alias/acronym, spelling, hyphen/dash clean-up).

