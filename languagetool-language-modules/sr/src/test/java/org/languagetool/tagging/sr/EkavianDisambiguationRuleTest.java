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
import org.languagetool.language.SerbianSerbian;
import org.languagetool.tagging.disambiguation.MultiWordChunker;
import org.languagetool.tagging.disambiguation.rules.DisambiguationRuleTest;
import org.languagetool.tokenizers.SRXSentenceTokenizer;
import org.languagetool.tokenizers.SentenceTokenizer;
import org.languagetool.tokenizers.WordTokenizer;

import java.io.IOException;


public class EkavianDisambiguationRuleTest extends DisambiguationRuleTest {

  private EkavianTagger tagger;
  private WordTokenizer tokenizer;
  private SentenceTokenizer sentenceTokenizer;
  private MultiWordChunker disambiguator;

  @Before
  public void setUp() {
    tagger = new EkavianTagger();
    tokenizer = new WordTokenizer();
    sentenceTokenizer = new SRXSentenceTokenizer( new SerbianSerbian() );
    disambiguator = new MultiWordChunker("/sr/multiwords.txt");
  }

  @Test
  public void testChunker() throws IOException {
    TestTools.myAssert("Нови Сад, мој град.", "/[null]SENT_START Нови/[Нови Сад]<IM:VL:MU:0J:NO>|Нови/[нов]PR:OP:PO:MU:0J:AK:ST|Нови/[нов]PR:OP:PO:MU:0J:NO:OR|Нови/[нов]PR:OP:PO:MU:0J:VO:OR|Нови/[нов]PR:OP:PO:MU:0M:NO:OR|Нови/[нов]PR:OP:PO:MU:0M:VO:OR|Нови/[нова]IM:ZA:ZE:0J:DA|Нови/[нова]IM:ZA:ZE:0J:LO  /[null]null Сад/[Нови Сад]</IM:VL:MU:0J:NO>|Сад/[сад]PL:GN:PO ,/[null]null  /[null]null мој/[мој]ZM:PS:1L:MU:0J:AK:ST|мој/[мој]ZM:PS:1L:MU:0J:NO|мој/[мој]ZM:PS:1L:MU:0J:VO  /[null]null град/[град]IM:ZA:MU:0J:AK:ST|град/[град]IM:ZA:MU:0J:NO ./[null]null", tokenizer, sentenceTokenizer, tagger, disambiguator);
    TestTools.myAssert("Остао сам у Новом Саду.", "/[null]SENT_START Остао/[остати]GL:GV:RA:0:0J:MU  /[null]null сам/[сам]PR:OP:PO:MU:0J:AK:ST|сам/[сам]PR:OP:PO:MU:0J:NO:NE|сам/[сам]PR:OP:PO:MU:0J:VO:NE|сам/[јесам]GL:PM:PZ:1L:0J  /[null]null у/[у]PE:AK|у/[у]PE:GE|у/[у]PE:LO  /[null]null Новом/[Новом Саду]<IM:VL:MU:0J:LO>|Новом/[нов]PR:OP:PO:MU:0J:DA:OR|Новом/[нов]PR:OP:PO:MU:0J:LO:OR|Новом/[нов]PR:OP:PO:SR:0J:DA:OR|Новом/[нов]PR:OP:PO:SR:0J:LO:OR|Новом/[нов]PR:OP:PO:ZE:0J:IS:OR|Новом/[нова]IM:ZA:ZE:0J:IN  /[null]null Саду/[Новом Саду]</IM:VL:MU:0J:LO> ./[null]null", tokenizer, sentenceTokenizer, tagger, disambiguator);
  }
}
