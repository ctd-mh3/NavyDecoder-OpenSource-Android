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

# Check code formatting (Spotless)
./gradlew :navyDecoder:spotlessCheck

# Auto-fix code formatting (Spotless)
./gradlew :navyDecoder:spotlessApply

# Clean build
./gradlew clean
```

Spotless covers both Java (`src/main/java/**/*.java`) and XML (`src/main/**/*.xml`). Run `spotlessApply` before committing after any Java or XML changes.

The project has no automated tests. Testing is done by running the app on a device or emulator.

## Architecture

Single `Activity` (`NavyReference.java`) with no Fragments. The UI is built on Material 3 (`Theme.Material3.DayNight.NoActionBar`, `MaterialToolbar`, `MaterialAlertDialogBuilder`), with an M3 color scheme generated from the app's brand blue (`res/values/colors.xml` `m3_color*` tokens). The app uses a two-level picker UI: a primary field selects the code category, and a secondary field selects the specific code to decode. Both are non-editable Material exposed dropdown menus (`TextInputLayout` + `AutoCompleteTextView` with `android:inputType="none"`) rather than platform `Spinner`s, so they open a Material popup on tap but don't accept typed filtering. RFAS codes use a special three-field row (`rfasSpinnerLayout`, a weighted horizontal `LinearLayout` so the fields share width evenly rather than overflow on narrow screens).

Fields stay empty (showing their floating hint) until the user actively picks a value — there is no default/auto-selected item, so `NavyReference`'s listeners (`MainDecoderItemSelectedListener`, `SecondaryDecoderItemSelectedListener`, `RFASDecoderItemSelectedListener`) implement `AdapterView.OnItemClickListener`, not `OnItemSelectedListener`. `setupSpinnerFromArray` clears a field's text before attaching a new adapter, so switching categories can't leave a stale key showing in a field that hasn't been re-picked yet.

### Package Structure

- `com.crashtestdummylimited.navydecoder` — Main activity (`NavyReference.java`)
- `com.crashtestdummylimited.navydecoder.model` — All code data and interfaces
- `com.crashtestdummylimited.navydecoder.controller` — `MenuOptions.java` (About, Open Source, Email Author menu items)
- `com.crashtestdummylimited.navydecoder.util` — `DataLoader.java`, `ChangelogBuilder.java`, `CommonUtilities.java`

### Data Model Pattern

All code lookups implement one of two interfaces:

**`ReferenceData`** (for most code types): `getKeys()` returns a sorted String array of codes; `getValue(key)` returns the description; `getSourceInfo()` returns attribution text; `getCode()` returns a type identifier.

**`RFASReferenceData`** (for RFAS codes): Uses three separate character-position HashMaps (first character, second+third characters, fourth character). The UI composes the three decoded values into one result string.

Implementations store data as hardcoded `String[][] CODE_MEANING_DATA` arrays loaded into `HashMap<String, String>` in the constructor. Use `new HashMap<>((int)(n / 0.75) + 1)` for the initial capacity — passing `n` directly causes resize during construction since HashMap's default load factor is 0.75. Keys are sorted via `Collections.sort()` before being returned as arrays.

### Model Caching

All model instances are lazily created and cached as private fields in `NavyReference` (e.g., `mRatingCodes`, `mRUICCodes`). The `MainDecoderItemSelectedListener` switch checks `if (mXxxCodes == null) mXxxCodes = new XxxCodes();` before assigning to `mReferenceData`. Do not call `new XxxCodes()` unconditionally — large models like `RUICCodes` (1,800+ entries) are expensive to construct.

### Adding a New Code Type

**For standard (non-RFAS) types:**

1. Create a new class in `model/` implementing `ReferenceData`. Use `new HashMap<>((int)(CODE_MEANING_DATA.length / 0.75) + 1)` for the HashMap.
2. Add a private cached field in `NavyReference` (e.g., `private MyCodes mMyCodes;`).
3. Append the display name to `res/values/strings.xml` → `level0_list_array`.
4. Add a `case` to the `switch` in `NavyReference.MainDecoderItemSelectedListener.onItemClick()` with the next sequential index, following the lazy-init pattern.

**Important**: The switch matches on the **position index** (`pos`) of the item in `level0_list_array`. Append new entries to the end of the array — do not reorder existing entries without updating the corresponding case numbers.

**For RFAS types:**

Follow the same steps but implement `RFASReferenceData`, use `Layouts.RFAS` in `updateLayoutDueToMainDecoderItemSelection`, assign to `mRfasReferenceData`, and call `setupSpinnerFromArray` three times (for `rfasFirstCharacter`, `rfasSecondAndThirdCharacter`, `rfasFourthCharacter`) with `RFASDecoderItemSelectedListener`.

### Versioning

- `versionCode` and `versionName` are set in `navyDecoder/build.gradle` → `defaultConfig`.
- The changelog is an HTML file at `navyDecoder/src/main/res/raw/changelog.html`.
- On first launch after an upgrade, the changelog dialog is automatically shown (`ChangelogBuilder`). The dialog uses `ChangeDialogStyle` (navy background, white text) and injects CSS into the WebView to match theme colors.

## Key Configuration

- **Min SDK**: 26 (Android 8.0)
- **Target/Compile SDK**: 36
- **Java**: 11 source/target compatibility
- **View Binding** is enabled; layouts are accessed via `mBinding` in the activity.
- Release builds use R8 with `proguard-android-optimize.txt` + `proguard-rules.pro`.
- Gradle property assignments must use `=` syntax (e.g., `viewBinding = true`, `namespace = '...'`) — the old space-separated form is deprecated in Gradle 9 and removed in Gradle 10.

## Icon Assets

The adaptive icon uses vector drawables:
- `res/drawable/ic_launcher_background.xml` — solid navy `#002855`
- `res/drawable/ic_launcher_foreground.xml` — radar rings + "ND" lettering in white on transparent
- `mipmap-anydpi/ic_launcher.xml` and `ic_launcher_round.xml` reference `@drawable/ic_launcher_foreground`

The Play Store 512×512 PNG is `store_icon_512.png` at the repo root (generated via Python/Pillow — not a build output).

## Reference Data

The `reference/` directory at the repo root contains the original Navy source documents and data processing notes used to populate the model classes. This is useful context when updating code data.
