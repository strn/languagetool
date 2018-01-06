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

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.languagetool.language.CroatianSerbian;
import org.languagetool.language.SerbianSerbian;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class LocalConfigurationTest {

  private SerbianSerbian srRS;
  private CroatianSerbian srHR;
  private LocalConfiguration locsr;
  private String osName;
  private List<String> configFileList = new ArrayList<>();
  private String systemConfigDir, userConfigDir;
  private static Map<String,String> environment = System.getenv();

  @Before
  public void setUp() {
    srRS = new SerbianSerbian();
    srHR = new CroatianSerbian();
    locsr = new LocalConfiguration(srRS.getShortCodeWithCountryAndVariant());
    osName = getOSVersion();

    if ("unix".equals(osName)) {
      systemConfigDir = "/etc/languagetool/";
      userConfigDir = environment.get("XDG_CONFIG_HOME");
      if (userConfigDir == null || userConfigDir.trim().length() == 0) {
        userConfigDir = environment.get("HOME") + "/.config";
      }
      userConfigDir += "/languagetool/";
    }
  }

  @Test
  public void getConfigFileList() {
    // Configuration for Serbian spoken in Serbia
    if ("unix".equals(osName)) {
      configFileList.add( systemConfigDir + "configuration-sr-RS.xml" );
      configFileList.add( userConfigDir + "configuration-sr-RS.xml" );
      assertEquals(configFileList, locsr.getConfigFilesList());
    }
  }

  @NotNull
  private String getOSVersion() {
    String osName = System.getProperty("os.name").toLowerCase();
    if (osName.contains("linux") || osName.contains("sunos") || osName.contains("freebsd")) {
      return "unix";
    } else if (osName.contains("windows")) {
      return "win";
    } else if (osName.contains("mac os")) {
      return "macos";
    }
    return "";
  }
}