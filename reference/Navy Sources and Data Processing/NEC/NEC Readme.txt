Download "EXCEL Spreadsheet for all NECs (Chapter IV)"
from https://www.mynavyhr.navy.mil/References/NEOCS-Manual/NEOCS-Vol-II/

(OLD)Steps (Not verified to be 100% correct):
- Only use the first two columns (NEC and NEC_LONG_TITLE) in the source xls, so delete all the 
  other columns
- Merge in the existing "Text for Navy Decoder Plus's fill_table_nec_codes.sql file" column formulas to create the needed SQL statements
- Manual edits (until these are automated)
  - Remove rows with odd NEC values in column A (using column D)  
  - Delete "8CMD - " from column B
  - Remove new lines in column B (V56A) 
- Once all data rows look good (via comparison in GitHub app), update date in sql text and save to sql statement in NavyDecoderPlus  project


Specific Prompt for AI to Perform Quarterly Update (31OCT2026):
Attach: NEC_Chap_4_Excel_<Mon>_<YY>.xlsx

Process the attached NEC Chapter 4 Excel file as follows and send me back the updated .xlsx file.

SETUP
1. Copy the "qryChapter4_NEC_Published" tab to a new FIRST tab named "Chapter 4-Processing". Do not change any of the original tabs.
2. On the new tab, delete all columns except A (NEC) and B (NEC_LONG_TITLE).
3. Remove every row where LEN(A) is not exactly 4, EXCEPT language NECs whose code ends in "**"
   (obsolete DoD language trigraphs, for example "9QAL**", "9SDH**"): keep those exactly as in the
   Navy file, code and title unchanged, and note them in column G.

COLUMN LAYOUT (Chapter 4-Processing)
A  NEC                      – original, unchanged
B  NEC_LONG_TITLE           – original, NEVER modified
C  Corrected NEC_LONG_TITLE – the title after the corrections below; the same as B when nothing needed fixing
D  Text for Navy Decoder Plus's fill_table_nec_codes.sql file
E  Length of NEC
F  Title Modified?          – "Yes" or "No"
G  Review Notes             – what was changed and/or what needs a human decision

FORMULAS (row 2, copied down through every remaining data row)
D2: ="insert into FTS_nec_codes (suggest_text_1, suggest_text_2, source) values ('" & TRIM(SUBSTITUTE(A2,"'","''")) & "','" & TRIM(SUBSTITUTE(C2,"'","''")) & "','NAVPERS 18068F (MON YYYY)');"
    - The formula uses the CORRECTED title in column C, not B.
    - Replace MON YYYY with the month and year in the file name (for example "Oct_26" becomes "OCT 2026").
E2: =+LEN(A2)

CORRECTIONS TO MAKE (column C only)
Fix these and record each fix in column G, for example: Fixed typo "Sytems" → "Systems"; Removed line break; Balanced parentheses ")NAMTS)" → "(NAMTS)".
1. Line breaks: replace any line break, carriage return or "_x000D_" in the title with a single space.
2. Unbalanced or misplaced parentheses: fix a missing "(" or ")", a stray extra ")", a reversed ")X)", and spaces just inside parentheses such as "(BL-1 )". If the right fix isn't clear, leave the title as it is and flag it in G.
3. Typos: fix clear misspellings, such as "Sytems", "Cumerical" (Numerical), "GRREK", "SANSKIRIT" and "JAVENESE". Leave military acronyms, system names and model numbers alone. Only fix obvious errors.
4. Curly quotes and dashes: convert every typographic character to plain ASCII. ’ and ‘ become ', “ and ” become ", and – and — become -.
5. Double spaces: collapse runs of spaces inside the title into one, and trim spaces from the start and end.

FLAG ONLY – DO NOT CHANGE (note these in column G and leave column F as "No" unless another fix was also made)
- Titles that contain administrative notes, such as "NEC Code Disestablished", "Recode ... to ...", or text in parentheses telling people to recode.
- Duplicate titles used under different NEC codes. List the other NEC in the note.
- Titles that differ greatly from the rest: unusually long (over 150 characters), very short, or an inconsistent style. Language codes (9xxx) are all in capitals by design, so don't flag them for that.
- Any SQL text in column D that doesn't match the standard pattern of the other rows.

FORMATTING
- Keep the header styling from the source. Freeze the header row and turn on the filter for A:G.
- Shade the column C cell light yellow on every row where F = "Yes".
- Recalculate the workbook and confirm there are no formula errors before sending it.

REPLY
Send the file, then give a short summary:
- the number of rows kept and removed
- the number of titles modified, grouped by correction type, with the NEC codes in each group
- the flag-only items, grouped by type
- anything you weren't sure how to fix

Also create a text file named "fill_table_nec_codes.sql" that starts with these lines:
.echo on
.mode column
.headers on
.nullvalue NULL
(followed by two blank lines), then every SQL statement from column D of Chapter 4-Processing, one per line, in spreadsheet order. Test that all statements load before sending.
