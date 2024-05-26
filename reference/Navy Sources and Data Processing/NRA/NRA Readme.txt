******************************************************************************************

    Process to update the NRA information in the Navy Decoder Applications

******************************************************************************************
    
Download "NRA and UIC Listing" doc from N123 Force Structure Page
Copy and paste content into text editor
Grep and replace "\t*" with "\t" (this should work but BBEdit was acting odd) so
- Replaced "\t\t\t\t\t" with "\t"
- Replaced "\t\t\t\t" with "\t"
Copy and paste resulting text into excel ('Raw Copied NRH Data' Tab of 'NRA List' spreadsheet)
Do minimal clean up for odd lines
- Examples: Lines with extra spaces at end, extra tabs which cause column misalignment
- TODO: Could automate the above cleanup with a script but likely not worth the effort
Copy columns of data to 'Massaged NRH Data' Tab
Keep destination formating for 'NRA UIC' column to ensure all five characters long
Update column F of "Text-For Sqlite"
Optional: Can add "SE, SW, MA etc" in REDCOM lines

Android Navy Decoder:
- Copy 'Text-For Java' into CODE_MEANING_DATA array in NRACodes.java file
- Update getSourceInfo return value (likely the same as text in column F of "Text-For Sqlite" tab)
- Ensure changelog.html is updated to reflect update

Android Navy Decoder Plus:
- Copy 'Text-For Sqlite" into 'fill_table_nra_codes.sql' file
- When finished updating all other sql files follow "readme.txt" in database directory (run createAndCopyDatabase.sh)
- Ensure to update DB_VERSION in DecoderDatabase.java file
- Ensure changelog.html is updated to reflect update

iOS Navy Decoder:
- When finished updating all other sql files follow "readme.txt" in database directory (run convertSqlScriptsToJson.pl)
- Follow instructions in XCode NavyDecoderDatabaseLoader project's readme file

