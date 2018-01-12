/* LanguageTool, a natural language style checker
 * Copyright (C) 2005 Daniel Naber (http://www.danielnaber.de)
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
package org.languagetool.tagging.sr;

import org.junit.Before;
import org.junit.Test;
import org.languagetool.TestTools;
import org.languagetool.language.JekavianSerbian;
import org.languagetool.tagging.disambiguation.MultiWordChunker;
import org.languagetool.tagging.disambiguation.rules.DisambiguationRuleTest;
import org.languagetool.tokenizers.SRXSentenceTokenizer;
import org.languagetool.tokenizers.SentenceTokenizer;
import org.languagetool.tokenizers.WordTokenizer;

import java.io.IOException;


public class JekavianDisambiguationRuleTest extends DisambiguationRuleTest {

  private JekavianTagger tagger;
  private WordTokenizer tokenizer;
  private SentenceTokenizer sentenceTokenizer;
  private MultiWordChunker disambiguator;

  @Before
  public void setUp() {
    tagger = new JekavianTagger();
    tokenizer = new WordTokenizer();
    sentenceTokenizer = new SRXSentenceTokenizer( new JekavianSerbian() );
    disambiguator = new MultiWordChunker("/sr/multiwords.txt");
  }

  @Test
  public void testChunker() throws IOException {
    TestTools.myAssert("Проба отклањања двосмислености...", "/[null]SENT_START Проба/[проба]IM:ZA:ZE:0J:NO|Проба/[проба]IM:ZA:ZE:0M:GE|Проба/[пробати]GL:GV:AO:2L:0J|Проба/[пробати]GL:GV:AO:3L:0J|Проба/[пробати]GL:GV:PZ:3L:0J  /[null]null отклањања/[отклањање]IM:ZA:SR:0J:GE|отклањања/[отклањање]IM:ZA:SR:0M:AK|отклањања/[отклањање]IM:ZA:SR:0M:GE|отклањања/[отклањање]IM:ZA:SR:0M:NO|отклањања/[отклањање]IM:ZA:SR:0M:VO  /[null]null двосмислености/[двосмисленост]IM:ZA:ZE:0J:DA|двосмислености/[двосмисленост]IM:ZA:ZE:0J:GE|двосмислености/[двосмисленост]IM:ZA:ZE:0J:IN|двосмислености/[двосмисленост]IM:ZA:ZE:0J:LO|двосмислености/[двосмисленост]IM:ZA:ZE:0J:VO|двосмислености/[двосмисленост]IM:ZA:ZE:0M:AK|двосмислености/[двосмисленост]IM:ZA:ZE:0M:GE|двосмислености/[двосмисленост]IM:ZA:ZE:0M:NO|двосмислености/[двосмисленост]IM:ZA:ZE:0M:VO ./[...]<ELLIPSIS> ./[null]null ./[...]</ELLIPSIS>", tokenizer, sentenceTokenizer, tagger, disambiguator);
  }
}
