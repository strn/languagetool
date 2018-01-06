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

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UNIXConfigurationDirectoryTest {

  private UNIXConfigurationDirectory unixConf;

  @Before
  public void setUp() {
    unixConf = new UNIXConfigurationDirectory();
  }

  @Test
  public void testSystemDirectory() {
    assertEquals("/etc/languagetool", unixConf.getSystem());
  }

  @Test
  public void testUserDirectory() {
    String osName = getOSVersion();
    String userConfigHome;

    if ("unix".equals(osName)) {
      userConfigHome = System.getenv("XDG_CONFIG_HOME");
      if (userConfigHome == null || userConfigHome.trim().length() == 0) {
        userConfigHome = System.getenv("HOME") + "/.config/languagetool";
      }
      assertEquals(userConfigHome, unixConf.getUser());
    }
  }

  @NotNull
  private String getOSVersion() {
    String osName = System.getProperty("os.name").toLowerCase();
    if (osName.contains("linux") || osName.contains("sunos") || osName.contains("freebsd")) {
      return "unix";
    } else {
      return "";
    }
  }
}