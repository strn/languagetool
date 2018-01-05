/* LanguageTool, a natural language style checker
 * Copyright (C) 2017 Daniel Naber (http://www.danielnaber.de)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses LT configuration file(s)
 *
 * @author Zoltán Csala
 *
 * @since 4.1
 */
public class ConfigurationFileParser {

  private final List<String> ruleFiles = new ArrayList<>();
  private final List<String> dictionaryFiles = new ArrayList<>();

  public void parse(final File file) {
  }

  public List<String> getDictionaryFiles() {
    return dictionaryFiles;
  }

  public List<String> getRuleFiles() {
    return ruleFiles;
  }
}
