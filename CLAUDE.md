# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Navy Decoder is an Android application (Java, single-module Gradle project) that helps US Navy Reservists look up administrative codes from manning documents like RUADs and CMS-ID/RFMT. It is a simple lookup/decoder app with no network access — all data is hardcoded in Java model classes.

## Build Commands

```bash
# Build debug APK
./gradlew :navyDecoder:assembleDebug

# Build release APK (requires signing config)
./gradlew :navyDecoder:assembleRelease

# Run lint
./gradlew :navyDecoder:lint

# Clean build
./gradlew clean
```

The project has no automated tests. Testing is done by running the app on a device or emulator.

## Architecture

Single `Activity` (`NavyReference.java`) with no Fragments. The app uses a two-level spinner UI: a primary spinner selects the code category, and a secondary spinner selects the specific code to decode. RFAS codes use a special three-spinner layout.

### Package Structure

- `com.crashtestdummylimited.navydecoder` — Main activity (`NavyReference.java`)
- `com.crashtestdummylimited.navydecoder.model` — All code data and interfaces
- `com.crashtestdummylimited.navydecoder.controller` — `MenuOptions.java` (About, Open Source, Email Author menu items)
- `com.crashtestdummylimited.navydecoder.ui` — `AppRater.java` (rate-the-app dialog)
- `com.crashtestdummylimited.navydecoder.util` — `DataLoader.java`, `ChangelogBuilder.java`, `CommonUtilities.java`

### Data Model Pattern

All code lookups implement one of two interfaces:

**`ReferenceData`** (for most code types): `getKeys()` returns a sorted String array of codes; `getValue(key)` returns the description; `getSourceInfo()` returns attribution text; `getCode()` returns a type identifier.

**`RFASReferenceData`** (for RFAS codes): Uses three separate character-position HashMaps (first character, second+third characters, fourth character). The UI composes the three decoded values into one result string.

Implementations store data as hardcoded `String[][] CODE_MEANING_DATA` arrays loaded into `HashMap<String, String>` in the constructor. Keys are sorted via `Collections.sort()` before being returned as arrays.

### Adding a New Code Type

**For standard (non-RFAS) types:**

1. Create a new class in `model/` implementing `ReferenceData`.
2. Add a `case` to the `switch` in `NavyReference.MainDecoderItemSelectedListener.onItemSelected()`, following the `NON_RFAS` pattern (set `mReferenceData`, call `setupSpinnerFromArray` with `SecondaryDecoderItemSelectedListener`).
3. Add the display name to `res/values/strings.xml` → `level0_list_array`.

**Important**: The switch matches on the **display string directly** (e.g., `case "My New Codes":`). The string added to `level0_list_array` must be an exact character-for-character match to the switch case label.

**For RFAS types:**

Follow the same steps but implement `RFASReferenceData`, use `Layouts.RFAS` in `updateLayoutDueToMainDecoderItemSelection`, assign to `mRfasReferenceData`, and call `setupSpinnerFromArray` three times (for `rfasFirstCharacter`, `rfasSecondAndThirdCharacter`, `rfasFourthCharacter`) with `RFASDecoderItemSelectedListener`.

### Versioning

- `versionCode` and `versionName` are set in `navyDecoder/src/main/AndroidManifest.xml`.
- The changelog is an HTML file at `navyDecoder/src/main/res/raw/changelog.html`.
- On first launch after an upgrade, the changelog dialog is automatically shown.

## Key Configuration

- **Min SDK**: 26 (Android 8.0)
- **Target/Compile SDK**: 36
- **Java**: 11 source/target compatibility
- **View Binding** is enabled; layouts are accessed via `mBinding` in the activity.
- Release builds use R8 with `proguard-android-optimize.txt` + `proguard-rules.pro`.

## Reference Data

The `reference/` directory at the repo root contains the original Navy source documents and data processing notes used to populate the model classes. This is useful context when updating code data.
