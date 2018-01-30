package com.warnerbros.ecm.platform.plugins;

import java.io.Serializable;
import java.util.Map;

import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.blobholder.BlobHolder;
import org.nuxeo.ecm.core.convert.api.ConversionException;
import org.nuxeo.ecm.core.convert.extension.Converter;
import org.nuxeo.ecm.platform.convert.plugins.Zip2HtmlConverter;
import org.nuxeo.ecm.platform.convert.plugins.ZipCachableBlobHolder;

/**
 *
 * Mitigates bug reported in NXP-24309.
 *
**/
public class Zip2HtmlConverterOverride extends Zip2HtmlConverter implements Converter {

    @Override
    public BlobHolder convert(BlobHolder blobHolder, Map<String, Serializable> parameters) throws ConversionException {
        Blob blob = blobHolder.getBlob();
        String mimeType = blob.getMimeType();
        if (!mimeType.equals("application/zip") && !mimeType.equals("application/x-zip-compressed")) {
            throw new ConversionException("not a zip file");
        }
        return new ZipCachableBlobHolder(blob);
    }

}
