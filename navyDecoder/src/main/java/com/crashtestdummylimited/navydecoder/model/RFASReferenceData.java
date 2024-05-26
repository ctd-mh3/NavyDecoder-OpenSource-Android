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

@SuppressWarnings("SameReturnValue")
public interface RFASReferenceData {

  String getSourceInfo();

  String getCode();

  String[] getFirstCharacterKeys();

  String getFirstCharacterValue(String key);

  String[] getSecondAndThirdCharacterKeys();

  String getSecondAndThirdCharacterValue(String key);

  String[] getFourthCharacterKeys();

  String getFourthCharacterValue(String key);
}