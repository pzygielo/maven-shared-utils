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
package org.apache.maven.shared.utils;

import javax.annotation.Nonnull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.input.XmlStreamReader;

/**
 * Utility to create Readers from streams, with explicit encoding choice: platform default,
 * XML, or specified.
 *
 * @author <a href="mailto:hboutemy@apache.org">Hervé Boutemy</a>
 * @see java.nio.charset.Charset
 * @see <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html">Supported encodings</a>
 * @deprecated use JDK methods instead
 */
public class ReaderFactory {
    /**
     * ISO Latin Alphabet #1, also known as ISO-LATIN-1.
     * Every implementation of the Java platform is required to support this character encoding.
     *
     * @deprecated use {@code java.nio.charset.StandardCharset.ISO_8859_1}
     */
    @Deprecated
    public static final String ISO_8859_1 = "ISO-8859-1";

    /**
     * Seven-bit ASCII, also known as ISO646-US, also known as the Basic Latin block of the Unicode character set.
     * Every implementation of the Java platform is required to support this character encoding.
     *
     * @deprecated use {@code java.nio.charset.StandardCharset.US_ASCII}
     */
    @Deprecated
    public static final String US_ASCII = "US-ASCII";

    /**
     * Sixteen-bit Unicode Transformation Format, byte order specified by a mandatory initial byte-order mark (either
     * order accepted on input, big-endian used on output).
     * Every implementation of the Java platform is required to support this character encoding.
     *
     * @deprecated use {@code java.nio.charset.StandardCharset.UTF_16}
     */
    @Deprecated
    public static final String UTF_16 = "UTF-16";

    /**
     * Sixteen-bit Unicode Transformation Format, big-endian byte order.
     * Every implementation of the Java platform is required to support this character encoding.
     *
     * @deprecated use {@code java.nio.charset.StandardCharset.UTF_16BE}
     */
    @Deprecated
    public static final String UTF_16BE = "UTF-16BE";

    /**
     * Sixteen-bit Unicode Transformation Format, little-endian byte order.
     * Every implementation of the Java platform is required to support this character encoding.
     *
     * @deprecated use {@code java.nio.charset.StandardCharset.UTF_16LE}
     */
    @Deprecated
    public static final String UTF_16LE = "UTF-16LE";

    /**
     * Eight-bit Unicode Transformation Format.
     * Every implementation of the Java platform is required to support this character encoding.
     *
     * @deprecated use {@code java.nio.charset.StandardCharset.UTF_8}
     */
    @Deprecated
    public static final String UTF_8 = "UTF-8";

    /**
     * The <code>file.encoding</code> System Property.
     * @deprecated use {@code java.nio.charset.Charset.getDefaultCharset()}
     */
    @Deprecated
    public static final String FILE_ENCODING = Charset.defaultCharset().displayName();

    /**
     * Create a new Reader with XML encoding detection rules.
     *
     * @param in not null input stream
     * @return an XML reader instance for the input stream
     * @throws IOException if any
     * @deprecated use {@code org.apache.commons.io.input.XmlStreamReader} instead
     */
    @Deprecated
    public static Reader newXmlReader(@Nonnull InputStream in) throws IOException {
        return new XmlStreamReader(in);
    }

    /**
     * Create a new Reader with XML encoding detection rules.
     *
     * @param file not null file
     * @return an XML reader instance for the input file
     * @throws IOException if any
     * @deprecated use {}@code org.apache.commons.io.input.XmlStreamReader} instead
     */
    @Deprecated
    public static Reader newXmlReader(@Nonnull File file) throws IOException {
        return new XmlStreamReader(file);
    }

    /**
     * Create a new Reader with XML encoding detection rules.
     *
     * @param url not null URL
     * @return an XML reader instance for the input URL
     * @throws IOException if any
     * @deprecated use {@code org.apache.commons.io.input.XmlStreamReader} instead
     */
    @Deprecated
    public static Reader newXmlReader(@Nonnull URL url) throws IOException {
        return new XmlStreamReader(url);
    }

    /**
     * Create a new Reader with default platform encoding.
     *
     * @param file not null file.
     * @return a reader instance for the input file using the default platform character set
     * @throws FileNotFoundException if any
     * @see java.nio.charset.Charset#defaultCharset()
     * @deprecated always specify an encoding. Do not depend on the default platform character set.
     */
    @Deprecated
    public static Reader newPlatformReader(@Nonnull File file) throws FileNotFoundException {
        return new FileReader(file);
    }

    /**
     * Create a new Reader with specified encoding.
     *
     * @param in       not null input stream
     * @param encoding not null supported encoding
     * @return a reader instance for the input stream using the given encoding
     * @throws UnsupportedEncodingException if the JDK in use does not recognize or support the
     *    named encoding
     * @see <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html">Supported
     *         encodings</a>
     * @deprecated use {@code new InputStreamReader(in, encoding)} instead
     */
    @Deprecated
    public static Reader newReader(@Nonnull InputStream in, @Nonnull String encoding)
            throws UnsupportedEncodingException {
        return new InputStreamReader(in, encoding);
    }

    /**
     * Create a new Reader with specified encoding.
     *
     * @param file     not null file
     * @param encoding not null supported encoding
     * @return a reader instance for the input file using the given encoding
     * @throws FileNotFoundException        if any
     * @throws UnsupportedEncodingException if the JDK in use does not recognize or support the
     *    named encoding
     * @see <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html">Supported
     *         encodings</a>
     * @deprecated use {@code new InputStreamReader(new FileInputStream(file), encoding)}
     *    or {@code new Files.newBufferedReader} instead
     */
    @Deprecated
    public static Reader newReader(@Nonnull File file, @Nonnull String encoding)
            throws FileNotFoundException, UnsupportedEncodingException {
        return new InputStreamReader(new FileInputStream(file), encoding);
    }

    /**
     * Create a new Reader with specified encoding.
     *
     * @param url      not null URL
     * @param encoding not null supported encoding
     * @return a reader instance for the input URL using the given encoding
     * @throws IOException if any
     * @throws UnsupportedEncodingException if the JDK in use does not recognize or support the
     *    named encoding
     * @see <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html">Supported
     *         encodings</a>
     * @deprecated This method does not use HTTP headers to detect the resource's encoding.
     */
    @Deprecated
    public static Reader newReader(@Nonnull URL url, @Nonnull String encoding) throws IOException {
        return new InputStreamReader(url.openStream(), encoding);
    }
}
