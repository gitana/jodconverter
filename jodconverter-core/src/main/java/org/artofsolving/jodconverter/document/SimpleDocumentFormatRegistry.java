//
// JODConverter - Java OpenDocument Converter
// Copyright 2004-2012 Mirko Nasato and contributors
//
// JODConverter is Open Source software, you can redistribute it and/or
// modify it under either (at your option) of the following licenses
//
// 1. The GNU Lesser General Public License v3 (or later)
//    -> http://www.gnu.org/licenses/lgpl-3.0.txt
// 2. The Apache License, Version 2.0
//    -> http://www.apache.org/licenses/LICENSE-2.0.txt
//
package org.artofsolving.jodconverter.document;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class SimpleDocumentFormatRegistry implements DocumentFormatRegistry {
  private List<DocumentFormat> documentFormats = new ArrayList<>();
  private Map<String,DocumentFormat>  extensions      = new HashMap<>();
  private Map<String,DocumentFormat>  mediaTypes      = new HashMap<>();


  public void addFormat(DocumentFormat documentFormat) {
    this.documentFormats.add(documentFormat);
    extensions.put(documentFormat.getExtension().toLowerCase(), documentFormat);
    mediaTypes.put(documentFormat.getMediaType().toLowerCase(), documentFormat);
  }

  public DocumentFormat getFormatByExtension(String extension) {
    if (extension == null) {
      return null;
    }

    return extensions.get(extension.toLowerCase());
  }

  public DocumentFormat getFormatByMediaType(String mediaType) {
    if (mediaType == null) {
      return null;
    }

    return mediaTypes.get(mediaType);
  }

  public Set<DocumentFormat> getOutputFormats(DocumentFamily family) {
    Set<DocumentFormat> formats = new HashSet<DocumentFormat>();
    for (DocumentFormat format : this.documentFormats) {
      if (format.getStoreProperties(family) != null) {
        formats.add(format);
      }
    }
    return formats;
  }

}
