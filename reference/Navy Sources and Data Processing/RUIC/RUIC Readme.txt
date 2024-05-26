******************************************************************************************

    Process to update the RUIC information in the Navy Decoder Applications

******************************************************************************************
    
Download the latest "CNRFC N1 SELRES Manning Cube ..." spreadsheet from N123 Force Structure Page
Go to "Manning by RCC & NOSC" tab
In PivotTable Fields section on right
- COLUMNS: Remove field(s) "Record_Type"
- ROWS: Remove all field(s) except "NRA Title", "RUIC Title", and "UMUIC" 
- VALUES: Remove "Count of Records"
Copy "NRA Title", "RUIC Title", and "UMUIC" columns
Copy and paste resulting text into excel ('Raw Copied NRH Data' Tab of 'RUIC Listing' spreadsheet)
Tweak Data
- Look for (Blank) in RUIC_Title or UMUIC and remove those rows or put "Unknown" in those fields
- If (Blank) in "NRA Title", replace with "Unknown"
Copy columns of data to 'Massaged Data from NRH' Tab
Update column F of "Text-For Sqlite"

Navy Decoder-Android:
- Copy 'Text-For Java' into CODE_MEANING_DATA array in RUICCodes.java file
- Update getSourceInfo return value (likely the same as text in column F of "Text-For Sqlite" tab)
- Ensure changelog.html is updated to reflect update

Navy Decoder Plus-Android:
- Copy 'Text-For Sqlite" into 'fill_table_rui_codes.sql' file
- When finished updating all other sql files follow "readme.txt" in database directory (run createAndCopyDatabase.sh)
- Ensure to update DB_VERSION in DecoderDatabase.java file
- Ensure changelog.html is updated to reflect update

Navy Decoder-iOS:
- When finished updating all other sql files follow "readme.txt" in database directory (run convertSqlScriptsToJson.pl)
- Follow instructions in XCode NavyDecoderDatabaseLoader project's readme file

