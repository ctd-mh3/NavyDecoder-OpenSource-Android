Download "EXCEL Spreadsheet for all NECs (Chapter IV)"
from https://www.mynavyhr.navy.mil/References/NEOCS-Manual/NEOCS-Vol-II/

Steps (Not verified to be 100% correct):
- Only use the first two columns (NEC and NEC_LONG_TITLE) in the source xls, so delete all the 
  other columns
- Merge in the existing "Text for Navy Decoder Plus's fill_table_nec_codes.sql file" column formulas to create the needed SQL statements
- Manual edits (until these are automated)
  - Remove rows with odd NEC values in column A (using column D)  
  - Delete "8CMD - " from column B
  - Remove new lines in column B (V56A) 
- Once all data rows look good (via comparison in GitHub app), update date in sql text and save to sql statement in NavyDecoderPlus  project



