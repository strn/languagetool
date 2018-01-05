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
package org.languagetool.configuration.unix;

import org.languagetool.configuration.ConfigurationDirectory;
import java.util.Map;

/**
 *
 * @author Zoltán Csala
 *
 * @since 4.1
 */
public class UNIXConfigurationDirectory extends ConfigurationDirectory {

  private static Map<String,String> environment = System.getenv();
  private static final String LT_DIR = "languagetool";

  @Override
  public String getSystem() {
    return "/etc/" + LT_DIR;
  }

  @Override
  public String getUser() {
    return getConfigHome() + "/" + LT_DIR;
  }

  private static String getConfigHome() {
    String value = environment.get("XDG_CONFIG_HOME");
    if (value == null || value.trim().length() == 0) {
      value = environment.get("HOME") + "/.config";
    }
    return value;
  }

}
