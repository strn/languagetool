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

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import static org.junit.Assert.*;


public class ConfigurationFileParserTest {

  private ConfigurationFileParser cfgParser;
  private File cfgFile;
  private Properties props;
  private String testOutputDirectory;

  @Before
  public void setUp() throws IOException{
    props = new Properties();
    cfgParser = new ConfigurationFileParser();
    //Get file from resources folder
    ClassLoader classLoader = getClass().getClassLoader();
    props.load(classLoader.getResourceAsStream("project.properties"));
    testOutputDirectory = (String)props.get("project.testOutputDirectory");
    createTestConfigurationFile();
    cfgFile = new File(classLoader.getResource("test-configuration.xml").getFile());
    cfgParser.parse(cfgFile);
  }

  @Test
  public void getMorfologikWordFile() {
    assertEquals(testOutputDirectory + File.separator + "spelling.txt",
    cfgParser.getMorfologikWordFile());
  }

  @Test
  public void getRuleFileNames() throws IOException {
    assertEquals(
      Arrays.asList(testOutputDirectory + File.separator + "rules1.xml",
      testOutputDirectory + File.separator + "rules2.xml"), cfgParser.getRuleFiles());
  }

  private void createTestConfigurationFile() throws IOException {
    File file;
    FileWriter writer;
    file = new File(testOutputDirectory + File.separator + "test-configuration.xml");
    writer = new FileWriter(file);
    writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
      "<ltconfiguration>\n\t<grammar>\n\t\t<rulefiles>\n" +
      "\t\t\t<rulefile active=\"yes\">" + testOutputDirectory +
            File.separator +"rules1.xml</rulefile>\n" +
      "\t\t\t<rulefile active=\"yes\">" + testOutputDirectory +
            File.separator +"rules2.xml</rulefile>\n" +
      "\t\t</rulefiles>\n\t</grammar>\n\t<morfologik>\n" +
      "\t\t<wordfile>" + testOutputDirectory + File.separator +
            "spelling.txt</wordfile>\n\t</morfologik>\n</ltconfiguration>");
    writer.close();
    file = new File(testOutputDirectory + File.separator + "rules1.xml");
    writer = new FileWriter(file);
    writer.write("dummy rules file 1");
    writer.close();
    file = new File(testOutputDirectory + File.separator + "rules2.xml");
    writer = new FileWriter(file);
    writer.write("dummy rules file 2");
    writer.close();
    file = new File(testOutputDirectory + File.separator + "spelling.txt");
    writer = new FileWriter(file);
    writer.write("My God, it's full of stars!");
    writer.close();
  }
}