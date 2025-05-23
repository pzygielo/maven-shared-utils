/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.maven.shared.utils.xml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import junit.framework.TestCase;
import org.apache.maven.shared.utils.StringUtils;
import org.apache.maven.shared.utils.WriterFactory;

/**
 * @author <a href="mailto:vincent.siveton@gmail.com">Vincent Siveton</a>
 *
 */
public class XmlWriterUtilTest extends TestCase {
    private OutputStream output;

    private Writer writer;

    private XMLWriter xmlWriter;

    /** {@inheritDoc} */
    protected void setUp() throws Exception {
        super.setUp();

        output = new ByteArrayOutputStream();
        writer = WriterFactory.newXmlWriter(output);
        xmlWriter = new PrettyPrintXMLWriter(writer);
    }

    /**
     * Test method for {@link org.apache.maven.shared.utils.xml.XmlWriterUtil#writeLineBreak(XMLWriter)}.
     *
     * @throws Exception if any
     */
    public void testWriteLineBreakXMLWriter() throws Exception {
        XmlWriterUtil.writeLineBreak(xmlWriter);
        writer.close();
        assertEquals(1, StringUtils.countMatches(output.toString(), "\r\n"));
    }

    /**
     * Test method for {@link org.apache.maven.shared.utils.xml.XmlWriterUtil#writeLineBreak(XMLWriter, int)}.
     *
     * @throws Exception if any
     */
    public void testWriteLineBreakXMLWriterInt() throws Exception {
        XmlWriterUtil.writeLineBreak(xmlWriter, 10);
        writer.close();
        assertEquals(10, StringUtils.countMatches(output.toString(), "\r\n"));
    }

    /**
     * Test method for {@link org.apache.maven.shared.utils.xml.XmlWriterUtil#writeLineBreak(XMLWriter, int, int)}.
     *
     * @throws Exception if any
     */
    public void testWriteLineBreakXMLWriterIntInt() throws Exception {
        XmlWriterUtil.writeLineBreak(xmlWriter, 10, 2);
        writer.close();
        assertEquals(10, StringUtils.countMatches(output.toString(), "\r\n"));
        assertEquals(
                1,
                StringUtils.countMatches(
                        output.toString(), StringUtils.repeat(" ", 2 * XmlWriterUtil.DEFAULT_INDENTATION_SIZE)));
    }

    /**
     * Test method for {@link org.apache.maven.shared.utils.xml.XmlWriterUtil#writeLineBreak(XMLWriter, int, int, int)}.
     *
     * @throws Exception if any
     */
    public void testWriteLineBreakXMLWriterIntIntInt() throws Exception {
        XmlWriterUtil.writeLineBreak(xmlWriter, 10, 2, 4);
        writer.close();
        assertEquals(10, StringUtils.countMatches(output.toString(), "\r\n"));
        assertEquals(1, StringUtils.countMatches(output.toString(), StringUtils.repeat(" ", 2 * 4)));
    }

    /**
     * Test method for {@link org.apache.maven.shared.utils.xml.XmlWriterUtil#writeCommentLineBreak(XMLWriter)}.
     *
     * @throws Exception if any
     */
    public void testWriteCommentLineBreakXMLWriter() throws Exception {
        XmlWriterUtil.writeCommentLineBreak(xmlWriter);
        writer.close();
        StringBuilder sb = new StringBuilder();
        sb.append("<!-- ====================================================================== -->")
                .append("\r\n");
        assertEquals(output.toString(), sb.toString());
        assertEquals(output.toString().length(), XmlWriterUtil.DEFAULT_COLUMN_LINE - 1 + "\r\n".length());
    }

    /**
     * Test method for {@link org.apache.maven.shared.utils.xml.XmlWriterUtil#writeCommentLineBreak(XMLWriter, int)}.
     *
     * @throws Exception if any
     */
    public void testWriteCommentLineBreakXMLWriterInt() throws Exception {
        XmlWriterUtil.writeCommentLineBreak(xmlWriter, 20);
        writer.close();
        assertEquals("<!-- ========== -->" + "\r\n", output.toString());
    }

    public void testWriteCommentLineBreak() throws IOException {
        XmlWriterUtil.writeCommentLineBreak(xmlWriter, 10);
        writer.close();
        assertEquals("<!--  -->" + "\r\n", output.toString());
    }

    /**
     * Test method for {@link org.apache.maven.shared.utils.xml.XmlWriterUtil#writeComment(XMLWriter, java.lang.String)}.
     *
     * @throws Exception if any
     */
    public void testWriteCommentXMLWriterString() throws Exception {
        XmlWriterUtil.writeComment(xmlWriter, "hello");
        writer.close();
        StringBuffer sb = new StringBuffer();
        sb.append("<!-- hello                                                                  -->")
                .append("\r\n");
        assertEquals(output.toString(), sb.toString());
        assertEquals(output.toString().length(), XmlWriterUtil.DEFAULT_COLUMN_LINE - 1 + "\r\n".length());
    }

    public void testWriteComment() throws IOException {
        XmlWriterUtil.writeComment(
                xmlWriter, "hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        writer.close();
        StringBuffer sb = new StringBuffer();
        sb.append("<!-- hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo -->")
                .append("\r\n");
        assertEquals(output.toString(), sb.toString());
        assertTrue(output.toString().length() >= XmlWriterUtil.DEFAULT_COLUMN_LINE);
    }

    public void testWriteComment2() throws IOException {
        XmlWriterUtil.writeComment(xmlWriter, "hello\nworld");
        writer.close();
        StringBuffer sb = new StringBuffer();
        sb.append("<!-- hello                                                                  -->")
                .append("\r\n");
        sb.append("<!-- world                                                                  -->")
                .append("\r\n");
        assertEquals(output.toString(), sb.toString());
        assertEquals(output.toString().length(), 2 * (XmlWriterUtil.DEFAULT_COLUMN_LINE - 1 + "\r\n".length()));
    }

    /**
     * Test method for {@link org.apache.maven.shared.utils.xml.XmlWriterUtil#writeComment(XMLWriter, java.lang.String, int)}.
     *
     * @throws Exception if any
     */
    public void testWriteCommentXMLWriterStringInt() throws Exception {
        String indent = StringUtils.repeat(" ", 2 * XmlWriterUtil.DEFAULT_INDENTATION_SIZE);

        XmlWriterUtil.writeComment(xmlWriter, "hello", 2);
        writer.close();
        StringBuffer sb = new StringBuffer();
        sb.append(indent);
        sb.append("<!-- hello                                                                  -->")
                .append("\r\n");
        assertEquals(output.toString(), sb.toString());
        assertEquals(
                output.toString().length(),
                XmlWriterUtil.DEFAULT_COLUMN_LINE - 1 + "\r\n".length() + 2 * XmlWriterUtil.DEFAULT_INDENTATION_SIZE);
    }

    public void testWriteComment3() throws IOException {
        String indent = StringUtils.repeat(" ", 2 * XmlWriterUtil.DEFAULT_INDENTATION_SIZE);
        XmlWriterUtil.writeComment(xmlWriter, "hello\nworld", 2);
        writer.close();
        StringBuffer sb = new StringBuffer();
        sb.append(indent);
        sb.append("<!-- hello                                                                  -->")
                .append("\r\n");
        sb.append(indent);
        sb.append("<!-- world                                                                  -->")
                .append("\r\n");
        assertEquals(output.toString(), sb.toString());
        assertEquals(
                output.toString().length(),
                2 * (XmlWriterUtil.DEFAULT_COLUMN_LINE - 1 + "\r\n".length()) + 2 * indent.length());
    }

    /**
     * Test method for {@link org.apache.maven.shared.utils.xml.XmlWriterUtil#writeComment(XMLWriter, java.lang.String, int, int)}.
     *
     * @throws Exception if any
     */
    public void testWriteCommentXMLWriterStringIntInt() throws Exception {
        String repeat = StringUtils.repeat(" ", 2 * 4);

        XmlWriterUtil.writeComment(xmlWriter, "hello", 2, 4);
        writer.close();
        StringBuffer sb = new StringBuffer();
        sb.append(repeat);
        sb.append("<!-- hello                                                                  -->")
                .append("\r\n");
        assertEquals(output.toString(), sb.toString());
        assertEquals(output.toString().length(), XmlWriterUtil.DEFAULT_COLUMN_LINE - 1 + "\r\n".length() + 2 * 4);
    }

    public void testWriteCommentXMLWriterStringIntInt2() throws IOException {
        String repeat = StringUtils.repeat(" ", 2 * 4);
        XmlWriterUtil.writeComment(xmlWriter, "hello\nworld", 2, 4);
        writer.close();
        StringBuffer sb = new StringBuffer();
        sb.append(repeat);
        sb.append("<!-- hello                                                                  -->")
                .append("\r\n");
        sb.append(repeat);
        sb.append("<!-- world                                                                  -->")
                .append("\r\n");
        assertEquals(output.toString(), sb.toString());
        assertEquals(
                output.toString().length(),
                2 * (XmlWriterUtil.DEFAULT_COLUMN_LINE - 1 + "\r\n".length()) + 2 * repeat.length());
    }

    /**
     * Test method for {@link org.apache.maven.shared.utils.xml.XmlWriterUtil#writeComment(XMLWriter, java.lang.String, int, int, int)}.
     *
     * @throws Exception if any
     */
    public void testWriteCommentXMLWriterStringIntIntInt() throws Exception {
        String indent = StringUtils.repeat(" ", 2 * 4);

        XmlWriterUtil.writeComment(xmlWriter, "hello", 2, 4, 50);
        writer.close();
        StringBuffer sb = new StringBuffer();
        sb.append(indent);
        sb.append("<!-- hello                                    -->").append("\r\n");
        assertEquals(output.toString(), sb.toString());
        assertEquals(output.toString().length(), 50 - 1 + "\r\n".length() + 2 * 4);
    }

    public void testWriteCommentXMLWriterStringIntIntInt2() throws IOException {
        String indent = StringUtils.repeat(" ", 2 * 4);
        XmlWriterUtil.writeComment(xmlWriter, "hello", 2, 4, 10);
        writer.close();
        StringBuffer sb = new StringBuffer();
        sb.append(indent);
        sb.append("<!-- hello -->").append("\r\n");
        assertEquals(output.toString(), sb.toString());
        assertTrue(output.toString().length() >= 10 + 2 * 4);
    }

    /**
     * Test method for {@link org.apache.maven.shared.utils.xml.XmlWriterUtil#writeCommentText(XMLWriter, java.lang.String, int)}.
     *
     * @throws Exception if any
     */
    public void testWriteCommentTextXMLWriterStringInt() throws Exception {
        XmlWriterUtil.writeCommentText(xmlWriter, "hello", 0);
        writer.close();
        StringBuffer sb = new StringBuffer();
        sb.append("\r\n");
        sb.append("<!-- ====================================================================== -->")
                .append("\r\n");
        sb.append("<!-- hello                                                                  -->")
                .append("\r\n");
        sb.append("<!-- ====================================================================== -->")
                .append("\r\n");
        sb.append("\r\n");
        assertEquals(output.toString(), sb.toString());
        assertEquals(output.toString().length(), 3 * (80 - 1 + "\r\n".length()) + 2 * "\r\n".length());
    }

    public void testWriteCommentTextXMLWriterStringInt2() throws IOException {
        String indent = StringUtils.repeat(" ", 2 * 2);

        XmlWriterUtil.writeCommentText(
                xmlWriter,
                "hello world with end of line\n and "
                        + "loooooooooooooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnong line",
                2);
        writer.close();
        StringBuffer sb = new StringBuffer();
        sb.append("\r\n");
        sb.append(indent)
                .append("<!-- ====================================================================== -->")
                .append("\r\n");
        sb.append(indent)
                .append("<!-- hello world with end of line                                           -->")
                .append("\r\n");
        sb.append(indent)
                .append("<!-- and                                                                    -->")
                .append("\r\n");
        sb.append(indent)
                .append("<!-- loooooooooooooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnong   -->")
                .append("\r\n");
        sb.append(indent)
                .append("<!-- line                                                                   -->")
                .append("\r\n");
        sb.append(indent)
                .append("<!-- ====================================================================== -->")
                .append("\r\n");
        sb.append("\r\n");
        sb.append(indent);
        assertEquals(output.toString(), sb.toString());
    }

    /**
     * Test method for {@link org.apache.maven.shared.utils.xml.XmlWriterUtil#writeCommentText(XMLWriter, java.lang.String, int, int)}.
     *
     * @throws Exception if any
     */
    public void testWriteCommentTextXMLWriterStringIntInt() throws Exception {
        String indent = StringUtils.repeat(" ", 2 * 4);

        XmlWriterUtil.writeCommentText(xmlWriter, "hello", 2, 4);
        writer.close();
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n");
        sb.append(indent)
                .append("<!-- ====================================================================== -->")
                .append("\r\n");
        sb.append(indent)
                .append("<!-- hello                                                                  -->")
                .append("\r\n");
        sb.append(indent)
                .append("<!-- ====================================================================== -->")
                .append("\r\n");
        sb.append("\r\n");
        sb.append(indent);
        assertEquals(output.toString(), sb.toString());
        assertEquals(output.toString().length(), 3 * (80 - 1 + "\r\n".length()) + 4 * 2 * 4 + 2 * "\r\n".length());
    }

    /**
     * Test method for {@link org.apache.maven.shared.utils.xml.XmlWriterUtil#writeCommentText(XMLWriter, java.lang.String, int, int, int)}.
     *
     * @throws Exception if any
     */
    public void testWriteCommentTextXMLWriterStringIntIntInt() throws Exception {
        String indent = StringUtils.repeat(" ", 2 * 4);

        XmlWriterUtil.writeCommentText(xmlWriter, "hello", 2, 4, 50);
        writer.close();
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n");
        sb.append(indent)
                .append("<!-- ======================================== -->")
                .append("\r\n");
        sb.append(indent)
                .append("<!-- hello                                    -->")
                .append("\r\n");
        sb.append(indent)
                .append("<!-- ======================================== -->")
                .append("\r\n");
        sb.append("\r\n");
        sb.append(indent);
        assertEquals(output.toString(), sb.toString());
        assertEquals(output.toString().length(), 3 * (50 - 1 + "\r\n".length()) + 4 * 2 * 4 + 2 * "\r\n".length());
    }

    /**
     * Test method for {@link org.apache.maven.shared.utils.xml.XmlWriterUtil#writeComment(XMLWriter, java.lang.String)}.
     *
     * @throws Exception if any
     */
    public void testWriteCommentNull() throws Exception {
        XmlWriterUtil.writeComment(xmlWriter, null);
        writer.close();
        StringBuilder sb = new StringBuilder();
        sb.append("<!-- null                                                                   -->")
                .append("\r\n");
        assertEquals(output.toString(), sb.toString());
    }

    /**
     * Test method for {@link org.apache.maven.shared.utils.xml.XmlWriterUtil#writeComment(XMLWriter, java.lang.String)}.
     *
     * @throws Exception if any
     */
    public void testWriteCommentShort() throws Exception {
        XmlWriterUtil.writeComment(xmlWriter, "This is a short text");
        writer.close();
        StringBuilder sb = new StringBuilder();
        sb.append("<!-- This is a short text                                                   -->")
                .append("\r\n");
        assertEquals(output.toString(), sb.toString());
    }

    /**
     * Test method for {@link org.apache.maven.shared.utils.xml.XmlWriterUtil#writeComment(XMLWriter, java.lang.String)}.
     *
     * @throws Exception if any
     */
    public void testWriteCommentLong() throws Exception {
        XmlWriterUtil.writeComment(
                xmlWriter,
                "Maven is a software project management and comprehension tool. "
                        + "Based on the concept of a project object model (POM), Maven can manage a project's build, reporting "
                        + "and documentation from a central piece of information.");
        writer.close();
        StringBuilder sb = new StringBuilder();
        sb.append("<!-- Maven is a software project management and comprehension tool. Based   -->")
                .append("\r\n");
        sb.append("<!-- on the concept of a project object model (POM), Maven can manage a     -->")
                .append("\r\n");
        sb.append("<!-- project's build, reporting and documentation from a central piece of   -->")
                .append("\r\n");
        sb.append("<!-- information.                                                           -->")
                .append("\r\n");
        assertEquals(output.toString(), sb.toString());
    }
}
